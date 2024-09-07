package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.TicketCreationRequest;
import com.devteria.identityservice.dto.response.TicketResponse;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.*;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.TicketMapper;
import com.devteria.identityservice.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private PickupLocationRepository pickupLocationRepository;

    @Autowired
    private DropoffLocationRepository dropoffLocationRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private TicketMapper ticketMapper;

    public Ticket createTicket(TicketCreationRequest request) {
        // Tìm các đối tượng liên quan từ cơ sở dữ liệu
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.valueOf("user_not_found")));
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new AppException(ErrorCode.valueOf("trip_not_found")));
        PickupLocation pickupLocation = pickupLocationRepository.findById(request.getPickupLocationId())
                .orElseThrow(() -> new AppException(ErrorCode.valueOf("pickup_location_not_found")));
        DropoffLocation dropoffLocation = dropoffLocationRepository.findById(request.getDropoffLocationId())
                .orElseThrow(() -> new AppException(ErrorCode.valueOf("dropoff_location_not_found")));

        // Tạo đối tượng Ticket
        Ticket ticket = Ticket.builder()
                .price(request.getPrice())
                .creationDate(request.getCreationDate())
                .discount(request.getDiscount())
                .status(request.isStatus())
                .busSelectedId(request.getBusId())
                .user(user)
                .trip(trip)
                .pickupLocation(pickupLocation)
                .dropoffLocation(dropoffLocation)
                .build();

        List<Seat> seats = seatRepository.findAllByIdIn(request.getSeatIds());

        for (Seat seat : seats) {
            seat.setTicket(ticket);
        }
        ticket.setSeats(seats);

        return ticketRepository.save(ticket);
    }

    public List<TicketResponse> getAllTicket() {
        log.info("In method get ticket");
        return ticketRepository.findAll().stream().map(ticketMapper::toTicketResponse).toList();
    }

    public TicketResponse getTicketById(Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket with id not found"));

        // Lấy bus theo busSelectedId
        Bus selectedBus = ticket.getTrip().getBuses().stream()
                .filter(bus -> bus.getId() == ticket.getBusSelectedId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Bus with id not found"));

        // Lọc danh sách ghế theo tripId và busId
        List<Seat> filteredSeats = seatRepository.findByTripIdAndBusId(ticket.getTrip().getId(), selectedBus.getId());
        selectedBus.setSeats(filteredSeats);

        // Chỉ hiển thị bus được chọn
        ticket.getTrip().setBuses(Collections.singletonList(selectedBus));


        return ticketMapper.toTicketResponse(ticket);
    }


}

package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.TicketCreationRequest;
import com.devteria.identityservice.dto.request.TicketUpdateRequest;
import com.devteria.identityservice.dto.response.TicketResponse;
import com.devteria.identityservice.entity.*;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.exception.ErrorCode;
import com.devteria.identityservice.mapper.TicketMapper;
import com.devteria.identityservice.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class TicketService {
    TicketRepository ticketRepository;
    UserRepository userRepository;
    TripRepository tripRepository;
    BusRepository busRepository;
    PickupLocationRepository pickupLocationRepository;
    DropoffLocationRepository dropoffLocationRepository;
    SeatRepository seatRepository;
    TicketMapper ticketMapper;

    public Ticket createTicket(TicketCreationRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        PickupLocation pickupLocation = pickupLocationRepository.findById(request.getPickupLocationId())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        DropoffLocation dropoffLocation = dropoffLocationRepository.findById(request.getDropoffLocationId())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        Ticket ticket = Ticket.builder()
                .price(request.getPrice())
                .creationDate(request.getCreationDate())
                .discount(request.getDiscount())
                .status(request.isStatus())
                .busSelectedId(request.getBusSelectedId())
                .user(user)
                .trip(trip)
                .pickupLocation(pickupLocation)
                .dropoffLocation(dropoffLocation)
                .build();

        // Lưu vé trước khi gán ghế
        Ticket savedTicket = ticketRepository.save(ticket);

        // Tìm các ghế theo ID
        List<Seat> seats = seatRepository.findAllByIdIn(request.getSeatIds());

        // Cập nhật trạng thái của mỗi ghế và gán cho vé
        for (Seat seat : seats) {
            seat.setStatus(SeatStatus.UNAVAILABLE); // Giả sử bạn có enum SeatStatus với giá trị UNAVAILABLE
            seat.setTicket(savedTicket); // Gán vé đã lưu cho ghế
        }

        // Lưu cập nhật cho các ghế vào cơ sở dữ liệu
        seatRepository.saveAll(seats);

        // Cập nhật danh sách ghế vào vé
        savedTicket.setSeats(seats);

        // Lưu lại vé với danh sách ghế đã cập nhật
        return ticketRepository.save(savedTicket);
    }


    public List<TicketResponse> getAllTicket() {
        log.info("In method get all tickets");
        return ticketRepository.findAll()
                .stream()
                .map(ticketMapper::toTicketResponse)
                .toList();
    }

    public TicketResponse getTicketById(Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket with ID " + ticketId + " not found"));

        Bus selectedBus = ticket.getTrip().getBuses().stream()
                .filter(bus -> bus.getId() == ticket.getBusSelectedId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Bus with ID " + ticket.getBusSelectedId() + " not found"));

        List<Seat> filteredSeats = seatRepository.findByTripIdAndBusId(ticket.getTrip().getId(), selectedBus.getId());
        selectedBus.setSeats(filteredSeats);

        ticket.getTrip().setBuses(Collections.singletonList(selectedBus));

        return ticketMapper.toTicketResponse(ticket);
    }

    public TicketResponse updateTicket(Integer ticketId, TicketUpdateRequest request) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));

        ticketMapper.updateTicket(ticket, request);

        return ticketMapper.toTicketResponse(ticketRepository.save(ticket));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTicket(Integer ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}

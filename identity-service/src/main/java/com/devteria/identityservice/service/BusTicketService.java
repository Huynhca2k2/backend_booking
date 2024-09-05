package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.BusTicketCreationRequest;
import com.devteria.identityservice.dto.response.BusTicketResponse;
import com.devteria.identityservice.dto.response.ChuyenXeResponse;
import com.devteria.identityservice.entity.*;
import com.devteria.identityservice.mapper.BusTicketMapper;
import com.devteria.identityservice.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class BusTicketService {
    BusTicketRepository busTicketRepository;
    BusTicketMapper busTicketMapper;
    UserRepository userRepository;

    EmailService emailService;

    ThoiGianRepository thoiGianRepository;
    ChairRepository chairRepository;
    ChuyenXeRepository chuyenXeRepository;

    VerificationTokenRepository verificationTokenRepository;
    JavaMailSender mailSender;

    public BusTicket createBusTicket(BusTicketCreationRequest request){
        BusTicket busTicket =  busTicketMapper.toBusTicket(request);
        return busTicketRepository.save(busTicket);
    }
//    public List<BusTicketResponse> getBusTicket(){
//        return busTicketRepository.findAll();
//    }
    public List<BusTicketResponse> getBusTicket() {
        log.info("In method get ChuyenXe");
        return busTicketRepository.findAll().stream().map(busTicketMapper::toBusTicketResponse).toList();
    }


    public BusTicket createTicketWithChairs(String userId, Integer thoiGianId, List<Integer> chairIds, BusTicket ticket) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ThoiGian thoiGian = thoiGianRepository.findById(thoiGianId)
                .orElseThrow(() -> new RuntimeException("ThoiGian not found"));

        List<Chair> chairs = chairRepository.findAllById(chairIds);

//        ticket.setUser(user);
        ticket.setThoiGian(thoiGian);
//        ticket.setChairs(chairs);

        return busTicketRepository.save(ticket);
    }

    public BusTicketResponse createTicket(BusTicketCreationRequest request) throws Exception {
        // Tìm kiếm user theo email
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống");
        }
        // Tạo token xác thực
        String token = UUID.randomUUID().toString();
        String confirmationUrl = "http://localhost:8080/identity/confirm?token=" + token;
        emailService.sendSimpleEmail(request.getEmail(), "Xác nhận đăng ký", "Nhấn vào link để xác nhận: " + confirmationUrl);

        // Lưu thông tin người dùng tạm thời với status 'inactive'
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getUsername());
        newUser.setPassword("123");
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setEnabled(false);
        userRepository.save(newUser);

        // Lấy chuyến xe
        ChuyenXe chuyenXe = chuyenXeRepository.findById(request.getChuyenXeId())
                .orElseThrow(() -> new Exception("Không tìm thấy"));

        // Tính giá vé
        double ticketPrice = chuyenXe.getPrice() * request.getNumberOfTickets();
        ThoiGian thoigian = chuyenXe.getThoiGian();

        // Tạo ticket mới
        BusTicket busTicket = new BusTicket();
        busTicket.setTicketPrice(ticketPrice);
        busTicket.setStatus("active");
        busTicket.setEmail(newUser.getEmail());
        busTicket.setPhoneNumber(newUser.getPhoneNumber());
        busTicket.setUsername(newUser.getUsername());
        busTicket.setChuyenXe(chuyenXe);
        busTicket.setThoiGian(thoigian);

        // Lưu ticket vào database
        busTicketRepository.save(busTicket);

        // Cập nhật status của User sau khi xác nhận
        newUser.setEnabled(true);
        userRepository.save(newUser);

        return new BusTicketResponse(busTicket);
    }
}

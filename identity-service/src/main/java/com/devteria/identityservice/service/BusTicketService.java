package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.BusTicketCreationRequest;
import com.devteria.identityservice.dto.response.BusTicketResponse;
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

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class BusTicketService {
    BusTicketRepository busTicketRepository;
    BusTicketMapper busTicketMapper;
    UserRepository userRepository;

    ThoiGianRepository thoiGianRepository;
    ChairRepository chairRepository;
    ChuyenXeRepository chuyenXeRepository;

    JavaMailSender mailSender;

    public BusTicket createBusTicket(BusTicketCreationRequest request){
        BusTicket busTicket =  busTicketMapper.toBusTicket(request);
        return busTicketRepository.save(busTicket);
    }
    public List<BusTicket> getBusTicket(){
        return busTicketRepository.findAll();
    }



    public BusTicket createTicketWithChairs(String userId, Integer thoiGianId, List<Integer> chairIds, BusTicket ticket) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ThoiGian thoiGian = thoiGianRepository.findById(thoiGianId)
                .orElseThrow(() -> new RuntimeException("ThoiGian not found"));

        List<Chair> chairs = chairRepository.findAllById(chairIds);

        ticket.setUser(user);
        ticket.setThoiGian(thoiGian);
        ticket.setChairs(chairs);

        return busTicketRepository.save(ticket);
    }

    public BusTicketResponse createTicket(BusTicketCreationRequest request) throws Exception {

        // Tìm kiếm user theo email
        User user = userRepository.findByEmail(request.getEmail()).orElseGet(() -> {
            // Nếu không tìm thấy user thì gửi email xác nhận
            sendConfirmationEmail(request.getEmail());
            // Chờ người dùng xác nhận, sau đó tạo User mới
            User newUser = new User();
            newUser.setEmail(request.getEmail());
            newUser.setUsername(request.getUsername());
            newUser.setPhoneNumber(request.getPhoneNumber());
            newUser.setStatus("pending");
            return userRepository.save(newUser);
        });

        // Lấy chuyến xe
        ChuyenXe chuyenXe = chuyenXeRepository.findById(request.getChuyenXeId())
                .orElseThrow(() -> new Exception("Không tìm thấy"));

        // Tính giá vé
        double ticketPrice = chuyenXe.getPrice() * request.getNumberOfTickets();

        // Tạo ticket mới
        BusTicket busTicket = new BusTicket();
        busTicket.setTicketPrice(ticketPrice);
        busTicket.setStatus("disable");
        busTicket.setUser(user);
        busTicket.setChuyenXe(chuyenXe);

        // Lưu ticket vào database
        busTicketRepository.save(busTicket);

        // Cập nhật status của User sau khi xác nhận
        user.setStatus("active");
        userRepository.save(user);

        return new BusTicketResponse(busTicket);
    }

    private void sendConfirmationEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Xác nhận email");
        message.setText("Vui lòng xác nhận email của bạn để hoàn tất đăng ký.");
        mailSender.send(message);
    }
}

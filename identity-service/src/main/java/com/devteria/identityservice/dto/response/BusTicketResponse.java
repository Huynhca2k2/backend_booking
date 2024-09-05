package com.devteria.identityservice.dto.response;

import com.devteria.identityservice.entity.BusTicket;
import com.devteria.identityservice.entity.ChuyenXe;
import com.devteria.identityservice.entity.ThoiGian;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusTicketResponse {
    int id;
    String username;
    String email;
    String phoneNumber;
    double ticketPrice;
    String status;
    ThoiGian thoiGian;
    ChuyenXeResponse chuyenXes;

    public BusTicketResponse(BusTicket ticket) {
        this.id = ticket.getId();
        this.username = ticket.getUsername();
        this.email = ticket.getEmail();
        this.phoneNumber = ticket.getPhoneNumber();
        this.ticketPrice = ticket.getTicketPrice();
        this.status = ticket.getStatus();
        this.thoiGian = ticket.getThoiGian();
    }
}

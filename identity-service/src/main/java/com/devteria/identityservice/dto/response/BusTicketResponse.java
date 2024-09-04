package com.devteria.identityservice.dto.response;

import com.devteria.identityservice.entity.BusTicket;
import com.devteria.identityservice.entity.ChuyenXe;
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
    String id;
    String username;
    String email;
    String phoneNumber;
    double ticketPrice;
    String status;
    ChuyenXeResponse chuyenXes;

    public BusTicketResponse(BusTicket ticket) {
        this.ticketPrice = ticket.getTicketPrice();
        this.status = ticket.getStatus();
        this.username = ticket.getUser().getUsername();
        this.email = ticket.getUser().getEmail();
        this.phoneNumber = ticket.getUser().getPhoneNumber();
    }
}

package com.devteria.identityservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusTicketCreationRequest {
    int id;
    private String username;
    private String email;
    private String phoneNumber;
    private int chuyenXeId;
    private int numberOfTickets;
    double ticketPrice;
    String status;
}

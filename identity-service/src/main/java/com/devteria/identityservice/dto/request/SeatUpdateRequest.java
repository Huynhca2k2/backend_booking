package com.devteria.identityservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatUpdateRequest {
    private String seatCode;
    private String status;
    private int busId;
    private int ticketId;
    private LocalDate creationDate;
}

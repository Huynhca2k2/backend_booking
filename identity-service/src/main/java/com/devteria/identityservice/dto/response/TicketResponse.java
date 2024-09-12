package com.devteria.identityservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketResponse {
    int id;
    double price;
    double discount;
    boolean status;
    int busSelectedId;
    UserResponse user;
    TripResponse trip;
    LocalDate creationDate;
    PickupLocationResponse pickupLocation;
    DropoffLocationResponse dropoffLocation;
    List<SeatResponse> seats;
}

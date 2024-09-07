package com.devteria.identityservice.dto.response;


import com.devteria.identityservice.entity.Seat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusResponse {
    String id;
    String name;
    String image;
    String descBus;
    String type;
    double priceReal;
    String licensePlate;
    int seatCapacity;
    String phoneNumber;
    List<SeatResponse> seats;
    double rating;
}

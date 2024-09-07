package com.devteria.identityservice.dto.request;

import com.devteria.identityservice.entity.Seat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusCreationRequest {
    Integer id;
    private String name;
    private String image;
    private String descBus;
    private String type;
    private double priceReal;
    private String licensePlate;
    private int seatCapacity;
    private String phoneNumber;
    private double rating;

}

package com.devteria.identityservice.dto.request;

import com.devteria.identityservice.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusUpdateRequest {

    String name;
    String image;
    String descBus;
    String type;
    double priceReal;
    String licensePlate;
    int seatCapacity;
    String phoneNumber;
    double rating;
}

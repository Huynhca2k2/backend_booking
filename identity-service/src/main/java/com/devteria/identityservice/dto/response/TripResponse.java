package com.devteria.identityservice.dto.response;

import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.Ticket;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripResponse {
    int id;
    String departureLocation;
    String arrivalLocation;
    double distance;
    String travelTime;
    LocalDate creationDate;
    LocalDateTime departureTime;
    List<PickupLocationResponse> pickupLocations;
    List<DropoffLocationResponse> dropoffLocations;
    List<BusResponse> buses;
}

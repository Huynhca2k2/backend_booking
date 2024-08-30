package com.devteria.identityservice.dto.response;


import com.devteria.identityservice.entity.ChuyenXe;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusResponse {
    String id;
    String busName;
    String driverName;
    String busType;
    Integer seat;
    Integer availableSeats;
    String bienSoXe;
}

package com.devteria.identityservice.dto.request;

import com.devteria.identityservice.entity.ChuyenXe;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusCreationRequest {
    Integer id;
    String busName;
    String busType;
    String driverName;
    Integer seat;
    Integer availableSeats;
    String bienSoXe;

}

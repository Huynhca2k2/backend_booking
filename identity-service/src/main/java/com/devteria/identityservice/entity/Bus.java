package com.devteria.identityservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String busName;
    String driverName;
    String busType;

    @Min(value = 1)
    @Max(value = 50)
    Integer seat;
    Integer availableSeats;

    @ManyToOne
    @JoinColumn(name = "chuyenXe_id")
    private ChuyenXe chuyenXe;


    String bienSoXe;

}

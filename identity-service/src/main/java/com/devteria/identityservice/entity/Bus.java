package com.devteria.identityservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonBackReference
    private ChuyenXe chuyenXe;


    String bienSoXe;

    @JsonManagedReference
    @OneToMany(mappedBy = "bus",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Chair> chairs = new ArrayList<>();

    public void addChair(Chair chair) {
        chairs.add(chair);
        chair.setBus(this);
    }

//    public void removeChair(Chair chair) {
//        chairs.remove(chair);
//        chair.setBus(null);
//    }

}

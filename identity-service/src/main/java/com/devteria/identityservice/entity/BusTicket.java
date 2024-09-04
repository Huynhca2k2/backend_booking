package com.devteria.identityservice.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    double ticketPrice;
    String status;
    String username;
    String email;
    String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "thoiGian_id")
    @JsonBackReference
    private ThoiGian thoiGian;

    @ManyToOne
    @JoinColumn(name = "chuyenXe_id")
    @JsonBackReference
    private ChuyenXe chuyenXe;


    @ManyToMany
    @JoinTable(
            name = "ticket_chairs",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "chair_id")
    )
    private List<Chair> chairs = new ArrayList<>();
    // Ma chuyen xe
}

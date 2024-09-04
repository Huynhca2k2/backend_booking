package com.devteria.identityservice.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChuyenXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    float distance;
    String departure;
    String destination;
    double price;
    double rating;
    String flashSale;

    @OneToMany(mappedBy = "chuyenXe",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Bus> bus;
    @OneToMany(mappedBy = "chuyenXe",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<BusTicket> tickets;

    @ManyToMany
    @JoinTable(
            name = "chuyenxe_allcode",
            joinColumns = @JoinColumn(name = "chuyenxe_id"),
            inverseJoinColumns = @JoinColumn(name = "allcode_id")
    )
    private Set<AllCode> allCodes;



//    @OneToMany(mappedBy = "chuyenXe",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JsonManagedReference
//    Set<ThoiGian> thoiGians;


//    @JsonManagedReference
//    @OneToMany(mappedBy = "chuyenXe",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    Set<ThoiGian> thoiGians;


    @ManyToOne
    @JoinColumn(name = "thoigian_id")
    @JsonBackReference
    ThoiGian thoiGian;



    public void addBus(Bus buses) {
        bus.add(buses);
        buses.setChuyenXe(this);
    }

    public void addTicket(BusTicket ticketss) {
        tickets.add(ticketss);
        ticketss.setChuyenXe(this);
    }
//    public void addTime(ThoiGian thoiGian) {
//        thoiGians.add(thoiGian);
//        thoiGian.setChuyenXe(this);
//    }
//    public void removeBus(Bus bus) {
//        buses.remove(bus);
//        bus.setChuyenXe(null);
//    }

}

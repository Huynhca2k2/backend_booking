package com.devteria.identityservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
public class ThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String hourStart;
    String dateStart;
    String hourEnd;
    String dateEnd;

    @OneToMany(mappedBy = "thoiGian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusTicket> tickets = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "thoiGian", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ChuyenXe> chuyenXes = new ArrayList<>();

    public void addChuyenXe(ChuyenXe chuyenXe) {
        chuyenXes.add(chuyenXe);
        chuyenXe.setThoiGian(this);
    }
}

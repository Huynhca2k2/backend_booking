package com.devteria.identityservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String tenGhe;
    String Status;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    @JsonBackReference
    Bus bus ;


}

package com.devteria.identityservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ThoiGian {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String hourStart;
    String dateStart;
    String hourEnd;
    String dateEnd;

}

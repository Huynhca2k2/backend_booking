package com.devteria.identityservice.dto.response;

import com.devteria.identityservice.entity.Bus;
import com.devteria.identityservice.entity.BusTicket;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChuyenXeResponse {
    int id;
    float distance;
    String departure;
    String destination;
    List<Bus> bus;
    Set<BusTicket> busTickets;
//    Set<RoleResponse> roles;
}

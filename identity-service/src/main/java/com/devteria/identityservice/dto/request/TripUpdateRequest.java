package com.devteria.identityservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripUpdateRequest {
    String departureLocation;
    String arrivalLocation;
    double distance;
    String travelTime;
    LocalDate creationDate;
    LocalDateTime departureTime;
    List<Integer> pickupLocationIds;
    List<Integer> dropoffLocationIds;
    List<Integer> busIds;

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public List<Integer> getPickupLocationIds() {
        return pickupLocationIds;
    }

    public void setPickupLocationIds(List<Integer> pickupLocationIds) {
        this.pickupLocationIds = pickupLocationIds;
    }

    public List<Integer> getDropoffLocationIds() {
        return dropoffLocationIds;
    }

    public void setDropoffLocationIds(List<Integer> dropoffLocationIds) {
        this.dropoffLocationIds = dropoffLocationIds;
    }

    public List<Integer> getBusIds() {
        return busIds;
    }

    public void setBusIds(List<Integer> busIds) {
        this.busIds = busIds;
    }
}

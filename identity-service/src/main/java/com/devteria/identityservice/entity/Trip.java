package com.devteria.identityservice.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String departureLocation;
    private String arrivalLocation;
    private double distance;
    private String travelTime;
    private LocalDate creationDate;
    private LocalDateTime departureTime;

    @OneToMany(mappedBy = "trip")
    @JsonIgnore
    private List<Seat> seats;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trip_bus",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "bus_id")
    )
    private List<Bus> buses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trip_pickup_location",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "pickup_location_id")
    )
    private List<PickupLocation> pickupLocations;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "trip_dropoff_location",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "dropoff_location_id")
    )
    private List<DropoffLocation> dropoffLocations;

    @OneToMany(mappedBy = "trip")
    @JsonIgnore
    private List<Ticket> tickets;

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<PickupLocation> getPickupLocations() {
        return pickupLocations;
    }

    public void setPickupLocations(List<PickupLocation> pickupLocations) {
        this.pickupLocations = pickupLocations;
    }

    public List<DropoffLocation> getDropoffLocations() {
        return dropoffLocations;
    }

    public void setDropoffLocations(List<DropoffLocation> dropoffLocations) {
        this.dropoffLocations = dropoffLocations;
    }
}

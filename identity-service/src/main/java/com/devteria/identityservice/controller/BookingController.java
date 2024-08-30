package com.devteria.identityservice.controller;


import com.devteria.identityservice.entity.Booking;
import com.devteria.identityservice.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingController {
    BookingService bookingService;
    @PostMapping()
    Booking createBooking(@RequestBody Booking request){
        return bookingService.createBooking(request);
    }

    @GetMapping("/findAll")
    List<Booking> getAllBooking(){
        return bookingService.getBooking();
    }
//    @GetMapping("/type")
//    public List<Booking> getBookings(@RequestParam String type) {
//        return allCodeService.getAllcodesByType(type);
//    }


}

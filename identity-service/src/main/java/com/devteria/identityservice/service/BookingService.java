package com.devteria.identityservice.service;


import com.devteria.identityservice.entity.Booking;
import com.devteria.identityservice.mapper.BookingMapper;
import com.devteria.identityservice.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Service
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;

    public Booking createBooking(Booking request){
        Booking booking = bookingMapper.toBooking((request));
        return bookingRepository.save(booking);
    }
    public List<Booking> getBooking(){
        return bookingRepository.findAll();
    }
//    public List<AllCode> getAllcodesByType(String type) {
//        return allCodeRepository.findByType(type);
//    }

}

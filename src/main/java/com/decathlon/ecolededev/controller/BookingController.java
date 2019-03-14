package com.decathlon.ecolededev.controller;


import com.decathlon.ecolededev.pojo.Booking;
import com.decathlon.ecolededev.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){

        this.bookingService = bookingService;
    }

    @PostMapping("/booking/")
    public Booking add(@RequestBody Booking booking){

        return bookingService.addBooking(booking);
    }

    @GetMapping("/booking/")
    public List<Booking> getAll(){
        return bookingService.getAll();
    }

    @GetMapping("/booking/{id}")
    public Booking getBooking(@PathVariable Long id){
        return bookingService.getOne(id);
    }
}

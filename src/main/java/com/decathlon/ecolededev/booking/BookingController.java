package com.decathlon.ecolededev.booking;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking/")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService){

        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking add(@RequestBody Booking booking){

        return bookingService.addBooking(booking);
    }

    @GetMapping
    public List<Booking> getAll(){
        return bookingService.getAll();
    }

    @GetMapping("{id}")
    public Booking getBooking(@PathVariable Long id){
        return bookingService.getOne(id);
    }

}

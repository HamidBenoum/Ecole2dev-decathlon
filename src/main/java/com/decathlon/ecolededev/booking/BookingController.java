package com.decathlon.ecolededev.booking;


import com.decathlon.ecolededev.exceptions.IncorrectSlotException;
import com.decathlon.ecolededev.exceptions.NotAvailableSlotException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings/")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {

        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking add(@RequestBody Booking booking) throws IncorrectSlotException, NotAvailableSlotException {
        return bookingService.addBooking(booking);
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }

    @GetMapping("/status/{status}")
    public List<Booking> getNotValidate(@PathVariable String status) {

        BookingModel.Status value = BookingModel.Status.valueOf(status);

        return bookingService.getByStatus(value);
    }

    @PatchMapping("{id}/validate")
    public Booking validateBooking(@PathVariable Long id) {
        return bookingService.validateBooking(id);
    }

    @GetMapping("{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.getOne(id);
    }

}

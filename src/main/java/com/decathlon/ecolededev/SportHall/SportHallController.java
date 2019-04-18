package com.decathlon.ecolededev.SportHall;

import com.decathlon.ecolededev.booking.Booking;
import com.decathlon.ecolededev.exceptions.IncorrectSlotException;
import com.decathlon.ecolededev.httpstatus.NotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/sporthalls/")
public class SportHallController {

    private SportHallService sportHallService;

    public SportHallController(SportHallService sportHallService) {

        this.sportHallService = sportHallService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public SportHall createSportHall(@RequestBody SportHall sportHall) {

        return sportHallService.create(sportHall);
    }

    @GetMapping
    public List<SportHall> getAll() {

        return sportHallService.getAll();
    }

    @GetMapping("{id}")
    public SportHall getById(@PathVariable Long id) throws NotFoundException {

        return sportHallService.getOne(id)
                .orElseThrow(() -> new NotFoundException("No sport hall for id " + id));
    }

    @PatchMapping("{id}/maintenance/{start}/{end}")
    public Booking setMaintenance(@PathVariable Long id,
                                  @PathVariable @DateTimeFormat(iso =
                                          DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) throws IncorrectSlotException {

        return sportHallService.close(id, start, end);

    }

}

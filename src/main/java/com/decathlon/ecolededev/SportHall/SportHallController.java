package com.decathlon.ecolededev.SportHall;

import com.decathlon.ecolededev.httpstatus.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sporthall/")
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

}

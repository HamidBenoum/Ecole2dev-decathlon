package com.decathlon.ecolededev.SportHall;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sporthalls/")
public class SportHallController {

    private SportHallService sportHallService;

    public SportHallController(SportHallService sportHallService) {

        this.sportHallService = sportHallService;
    }

    @PostMapping
    public SportHall createSportHall(@RequestBody SportHall sportHall) {

        return sportHallService.create(sportHall);
    }

    @GetMapping
    public List<SportHall> getAll() {

        return sportHallService.getAll();
    }

    @GetMapping("{id}")
    public SportHall getById(@PathVariable Long id) {

        return sportHallService.getOne(id);
    }
}

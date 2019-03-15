package com.decathlon.ecolededev.SportHall;

import com.decathlon.ecolededev.httpstatus.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SportHallController {

    private SportHallService sportHallService;


    public SportHallController(SportHallService sportHallService) {
        this.sportHallService = sportHallService;
    }

    @PostMapping("/sporthall/")
    public SportHall createSportHall(@RequestBody SportHall sportHall) {
        return sportHallService.create(sportHall);
    }

    @GetMapping("/sporthall/")
    public List<SportHall> getAll() {

        return sportHallService.getAll();
    }

    @GetMapping("/sporthall/{id}")
    public SportHall getById(@PathVariable Long id) throws NotFoundException {

        return sportHallService.getOne(id)
                .orElseThrow(NotFoundException::new);

    }

}

package com.decathlon.ecolededev.controller;

import com.decathlon.ecolededev.pojo.SportHall;
import com.decathlon.ecolededev.service.SportHallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SportHallController {

    private SportHallService sportHallService;


    public SportHallController(SportHallService sportHallService){
        this.sportHallService=sportHallService;
    }

    @PostMapping("/sporthall/")
    public SportHall createSportHall(@RequestBody SportHall sportHall){

        return sportHallService.create(sportHall);
    }

    @GetMapping("/sporthall/all")
    public List<SportHall> getAll(){

        return sportHallService.getAll();
    }

}

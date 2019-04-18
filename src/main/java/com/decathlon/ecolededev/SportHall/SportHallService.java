package com.decathlon.ecolededev.SportHall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SportHallService {

    private SportHallRespository sportHallRespository;

    public SportHallService(SportHallRespository sportHallRespository) {
        this.sportHallRespository = sportHallRespository;
    }

    public SportHall create(SportHall sportHall) {

        SportHallModel sportHallModel = mapSportHallToSportHallModel(sportHall);

        sportHallModel = sportHallRespository.save(sportHallModel);

        return mapSportHallModelToSportHall(sportHallModel);
    }

    public List<SportHall> getAll() {
        return sportHallRespository.findAll()
                .stream()
                .map(model -> mapSportHallModelToSportHall(model))
                .collect(Collectors.toList());
    }


    public SportHall getOne(Long id) {
        SportHallModel one = sportHallRespository.getOne(id);
        return mapSportHallModelToSportHall(one);
    }

    private SportHallModel mapSportHallToSportHallModel(SportHall sportHall) {
        return SportHallModel.builder()
                .name(sportHall.getName())
                .build();
    }

    private SportHall mapSportHallModelToSportHall(SportHallModel sportHallModel) {
        return SportHall.builder()
                .id(sportHallModel.getId())
                .name(sportHallModel.getName())
                .build();
    }

}
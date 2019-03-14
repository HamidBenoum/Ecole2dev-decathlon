package com.decathlon.ecolededev.service;

import com.decathlon.ecolededev.pojo.SportHall;
import com.decathlon.ecolededev.repository.AdresseRepository;
import com.decathlon.ecolededev.repository.SportHallRespository;
import com.decathlon.ecolededev.repository.model.AdresseModel;
import com.decathlon.ecolededev.repository.model.SportHallModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportHallService {

    private SportHallRespository sportHallRespository;
    private AdresseRepository adresseRepository;

    public SportHallService(SportHallRespository sportHallRespository, AdresseRepository adresseRepository) {
        this.sportHallRespository = sportHallRespository;
        this.adresseRepository = adresseRepository;
    }

    public SportHall create(SportHall sportHall) {

        AdresseModel adresse = AdresseModel.builder().adresse(sportHall.getAdresse()).build();

        AdresseModel adresseModel = adresseRepository.saveAndFlush(adresse);

        SportHallModel sportHallModel = SportHallModel.builder()
                .adresseModel(adresseModel)
                .name(sportHall.getName())
                .build();

        sportHallModel = sportHallRespository.save(sportHallModel);

        return SportHall.builder()
                .adresse(adresseModel.getAdresse())
                .id(sportHallModel.getId())
                .build();
    }

    public List<SportHall> getAll() {

        return sportHallRespository.findAll()
                .stream()
                .map(s -> SportHall.builder()
                        .adresse(s.getAdresseModel().getAdresse())
                        .name(s.getName())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
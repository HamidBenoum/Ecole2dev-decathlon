package com.decathlon.ecolededev.SportHall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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

        SportHallModel sportHallModel = mapSportHallToSportHallModel(sportHall, adresseModel);

        sportHallModel = sportHallRespository.save(sportHallModel);

        return mapSportHallModelToSportHall(sportHallModel, adresseModel);
    }

    public List<SportHall> getAll() {
        return sportHallRespository.findAll()
                .stream()
                .map(model -> mapSportHallModelToSportHall(model, model.getAdresseModel()))
                .map(sportHall -> {
                    sportHall.setPrice(getPrice(sportHall.getId()));
                    return sportHall;
                })
                .collect(Collectors.toList());
    }


    public Optional<SportHall> getOne(Long id) {
        try {
            return Optional.of(sportHallRespository.getOne(id))
                    .map(m -> mapSportHallModelToSportHall(m, m.getAdresseModel()));
        } catch (EntityNotFoundException e) {
            log.info("SportHall not found for the id {id}", id);
            return Optional.empty();
        }
    }

    public int getPrice(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Integer> response;
        try {
            URI uri = new URI("http://localhost:8081/price/" + id);
            response = restTemplate.getForEntity(uri, Integer.class);
        } catch (Exception e) {
            log.error("erreur lors de la récupération du prix");
            return 0;
        }
        return response.getBody();
    }

    private SportHallModel mapSportHallToSportHallModel(SportHall sportHall, AdresseModel adresseModel) {
        return SportHallModel.builder()
                .adresseModel(adresseModel)
                .name(sportHall.getName())
                .build();
    }

    private SportHall mapSportHallModelToSportHall(SportHallModel sportHallModel, AdresseModel adresseModel) {
        return SportHall.builder()
                .adresse(adresseModel.getAdresse())
                .id(sportHallModel.getId())
                .name(sportHallModel.getName())
                .build();
    }

}
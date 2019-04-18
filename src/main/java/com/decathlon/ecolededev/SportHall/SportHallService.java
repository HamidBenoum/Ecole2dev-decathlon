package com.decathlon.ecolededev.SportHall;

import com.decathlon.ecolededev.booking.Booking;
import com.decathlon.ecolededev.booking.BookingService;
import com.decathlon.ecolededev.exceptions.IncorrectSlotException;
import com.decathlon.ecolededev.slot.Slot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SportHallService {

    private SportHallRespository sportHallRespository;
    private BookingService bookingService;

    public SportHallService(SportHallRespository sportHallRespository, BookingService bookingService) {
        this.sportHallRespository = sportHallRespository;
        this.bookingService = bookingService;
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
                .map(sportHall -> {
                    sportHall.setPrice(getPrice(sportHall.getId()));
                    return sportHall;
                })
                .collect(Collectors.toList());
    }


    public Optional<SportHall> getOne(Long id) {
        try {
            return Optional.of(sportHallRespository.getOne(id))
                    .map(m -> mapSportHallModelToSportHall(m));
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

    public Booking close(Long id, LocalDateTime start, LocalDateTime end) throws IncorrectSlotException {
        Slot slot = Slot.builder()
                .start(start)
                .end(end)
                .build();

        return bookingService.addBookingForMaintenance(id, slot);
    }

    private SportHallModel mapSportHallToSportHallModel(SportHall sportHall) {
        return SportHallModel.builder()
                .name(sportHall.getName())
                .description(sportHall.getDescription())
                .telephoneNumber(sportHall.getTelephoneNumber())
                .build();
    }

    private SportHall mapSportHallModelToSportHall(SportHallModel sportHallModel) {
        return SportHall.builder()
                .id(sportHallModel.getId())
                .name(sportHallModel.getName())
                .description(sportHallModel.getDescription())
                .telephoneNumber(sportHallModel.getTelephoneNumber())
                .build();
    }

}
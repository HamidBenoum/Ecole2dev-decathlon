package com.decathlon.ecolededev.SportHall;

import com.decathlon.ecolededev.booking.Booking;
import com.decathlon.ecolededev.booking.BookingService;
import com.decathlon.ecolededev.exceptions.IncorrectSlotException;
import com.decathlon.ecolededev.slot.Slot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
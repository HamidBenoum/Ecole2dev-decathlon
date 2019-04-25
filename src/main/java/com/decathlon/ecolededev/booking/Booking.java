package com.decathlon.ecolededev.booking;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Booking {

    private Long id;
    private Long idSportHall;
    private Long idClient;
    private LocalDateTime start;
    private LocalDateTime end;
    private Status status;

}

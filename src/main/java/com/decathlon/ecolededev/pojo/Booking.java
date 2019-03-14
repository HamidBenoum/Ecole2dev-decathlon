package com.decathlon.ecolededev.pojo;

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
    private boolean validate;

}

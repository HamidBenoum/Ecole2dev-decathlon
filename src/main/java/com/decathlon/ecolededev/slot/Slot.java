package com.decathlon.ecolededev.slot;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Slot {

    String id;

    /**
     * The beggining of the booking
     */
    LocalDateTime start;

    /**
     * The end of the booking
     */
    LocalDateTime end;
}
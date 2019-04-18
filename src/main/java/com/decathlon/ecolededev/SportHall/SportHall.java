package com.decathlon.ecolededev.SportHall;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SportHall {

    private Long id;
    private String name;
    private String description;
    private String telephoneNumber;
}

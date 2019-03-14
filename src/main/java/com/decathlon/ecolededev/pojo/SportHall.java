package com.decathlon.ecolededev.pojo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SportHall {

    private Long id;
    private String adresse;
    private String name;
}

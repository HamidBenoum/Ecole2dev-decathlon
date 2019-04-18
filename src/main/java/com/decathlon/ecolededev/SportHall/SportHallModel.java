package com.decathlon.ecolededev.SportHall;

import lombok.*;

import javax.persistence.*;


@Builder
@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sporthall")
public class SportHallModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}

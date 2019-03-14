package com.decathlon.ecolededev.repository.model;

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

    @OneToOne
    private AdresseModel adresseModel;

    private String name;

}

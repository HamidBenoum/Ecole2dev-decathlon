package com.decathlon.ecolededev.SportHall;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="adresse")
public class AdresseModel {

    @Id
    @GeneratedValue
    private Long id;

    private String adresse;
}
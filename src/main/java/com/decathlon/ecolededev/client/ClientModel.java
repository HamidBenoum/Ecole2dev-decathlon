package com.decathlon.ecolededev.client;

import com.decathlon.ecolededev.SportHall.AdresseModel;
import lombok.*;

import javax.persistence.*;

@Builder
@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class ClientModel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne
    private AdresseModel adresseModel;

}

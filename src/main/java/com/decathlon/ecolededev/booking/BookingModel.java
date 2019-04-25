package com.decathlon.ecolededev.booking;

import com.decathlon.ecolededev.client.ClientModel;
import com.decathlon.ecolededev.SportHall.SportHallModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="booking")
public class BookingModel {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private SportHallModel sportHallModel;

    @OneToOne
    private ClientModel clientModel;

    private LocalDateTime start;

    @Column(name="endBooking")
    private LocalDateTime end;

    @Enumerated(EnumType.STRING)
    private Status status;

}

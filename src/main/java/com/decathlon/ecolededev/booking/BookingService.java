package com.decathlon.ecolededev.booking;

import com.decathlon.ecolededev.SportHall.SportHallModel;
import com.decathlon.ecolededev.SportHall.SportHallRespository;
import com.decathlon.ecolededev.client.ClientModel;
import com.decathlon.ecolededev.client.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;
    private SportHallRespository sportHallRespository;

    public BookingService(BookingRepository bookingRepository, ClientRepository clientRepository, SportHallRespository sportHallRespository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.sportHallRespository = sportHallRespository;
    }

    public Booking addBooking(Booking booking) {

        BookingModel bookingModel = mapBookingToBookingModel(booking);
        BookingModel model = bookingRepository.saveAndFlush(bookingModel);
        return mapBookingModelToBooking(model);
    }

    public Booking getOne(Long id) {
        return mapBookingModelToBooking(bookingRepository.getOne(id));
    }

    public List<Booking> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(b -> mapBookingModelToBooking(b))
                .collect(Collectors.toList());
    }

    private Booking mapBookingModelToBooking(BookingModel bookingModel) {

        return Booking.builder()
                .id(bookingModel.getId())
                .end(bookingModel.getEnd())
                .start(bookingModel.getStart())
                .idClient(bookingModel.getClientModel() != null ? bookingModel.getClientModel().getId() : null)
                .idSportHall(bookingModel.getSportHallModel().getId())
                .build();
    }

    private BookingModel mapBookingToBookingModel(Booking booking) {

        ClientModel client = null;
        if (booking.getIdClient() != null) {
            client = clientRepository.getOne(booking.getIdClient());
        }


        SportHallModel sportHall = sportHallRespository.getOne(booking.getIdSportHall());

        return BookingModel.builder()
                .start(booking.getStart())
                .end(booking.getEnd())
                .clientModel(client)
                .sportHallModel(sportHall)
                .build();
    }


}

package com.decathlon.ecolededev.booking;

import com.decathlon.ecolededev.SportHall.SportHallModel;
import com.decathlon.ecolededev.SportHall.SportHallRespository;
import com.decathlon.ecolededev.client.ClientModel;
import com.decathlon.ecolededev.client.ClientRepository;
import com.decathlon.ecolededev.exceptions.IncorrectSlotException;
import com.decathlon.ecolededev.exceptions.NotAvailableSlotException;
import com.decathlon.ecolededev.slot.Slot;
import com.decathlon.ecolededev.slot.SlotService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private ClientRepository clientRepository;
    private SportHallRespository sportHallRespository;
    private SlotService slotService;

    public BookingService(BookingRepository bookingRepository, ClientRepository clientRepository, SportHallRespository sportHallRespository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.sportHallRespository = sportHallRespository;
    }

    public Booking addBooking(Booking booking) throws IncorrectSlotException, NotAvailableSlotException {

        BookingModel bookingModel = mapBookingToBookingModel(booking);

        Slot slot = getSlotForBooking(booking);

        //on verifie si le slot est correcte
        if (!slotService.isCorrectSlot(slot)) {
            throw new IncorrectSlotException(slot);
        }

        //on recupere la liste des slots dont la date est supérieur à celle de départ
        List<BookingModel> byStartingDate = bookingRepository.findByStartingDate(booking.getStart());

        //on verifie si on a un conflict
        if (!slotService.isAvailable(getSlotsFromBookings(byStartingDate), slot)) {
            throw new NotAvailableSlotException(slot);
        }

        //tout va bien on sauvegarde
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
                .idClient(bookingModel.getClientModel().getId())
                .idSportHall(bookingModel.getSportHallModel().getId())
                .build();
    }

    private BookingModel mapBookingToBookingModel(Booking booking) {
        ClientModel client = clientRepository.getOne(booking.getIdClient());
        SportHallModel sportHall = sportHallRespository.getOne(booking.getIdSportHall());

        return BookingModel.builder()
                .start(booking.getStart())
                .end(booking.getEnd())
                .clientModel(client)
                .sportHallModel(sportHall)
                .build();
    }

    private Slot getSlotForBooking(Booking b) {
        return Slot.builder().start(b.getStart())
                .end(b.getEnd())
                .build();
    }

    private List<Slot> getSlotsFromBookings(List<BookingModel> bookingModelList) {
        return bookingModelList.stream()
                .map(this::mapBookingModelToBooking)
                .map(this::getSlotForBooking)
                .collect(Collectors.toList());
    }

}

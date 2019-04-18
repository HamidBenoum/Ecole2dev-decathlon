package com.decathlon.ecolededev.slot;

import java.time.LocalDateTime;
import java.util.List;

public interface SlotService {

    /**
     * This method take a list of slot and return true if the slot is available
     *
     * @param slotList
     * @param slot
     * @return true if the slot is available
     */
    boolean isAvailable(List<Slot> slotList, Slot slot);


    /**
     * This method take a list of slot and return the list of slot with a conflict
     * <p>
     * Example :
     * Slot 1 : start at 01-01-2019 10h00
     * end at 01-01-2019 12h00
     * <p>
     * Slot 2 : start at 01-01-2019 11h00
     * end at 01-01-2019 13h00
     * <p>
     * Slot 3 : start at 01-01-2019 14h00
     * end at 01-01-2019 15h00
     * <p>
     * The slot 1 and 2 are on conflict
     *
     * @return list of conflicting slot
     */
    List<Slot> conflictingSlot(List<Slot> slotList);

    /**
     * This method will check if the slot is correct
     * A correct slot have a @Slot.start > @Slot.end
     * A correct slot have an exact time without minutes ( ex : 10H00 is correct, 10H12 is incorrect)
     *
     * @param slot
     * @return
     */
    boolean isCorrectSlot(Slot slot);


    /**
     * This method will return all the available slot for a period, for the number of Hours, if the slot is correct
     * <p>
     * Exemple availableSlots(null,LocalDateTime.of(2019,01,01,10,00),LocalDateTime.of(2019,01,01,13,00),1)
     * will return 3 slots (10h00->11h00, 11h00->12h00, 12h00->13h00)
     *
     * @param slotList
     * @param startDate
     * @param endDate
     * @param hours
     * @return
     */
    List<Slot> availableSlots(List<Slot> slotList, LocalDateTime startDate, LocalDateTime endDate, int hours);

}
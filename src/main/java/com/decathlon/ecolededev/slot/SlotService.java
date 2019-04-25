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
     * This method will check if the slot is correct
     * A correct slot have a @Slot.start > @Slot.end
     * A correct slot have an exact time without minutes ( ex : 10H00 is correct, 10H12 is incorrect)
     *
     * @param slot
     * @return
     */
    boolean isCorrectSlot(Slot slot);

}
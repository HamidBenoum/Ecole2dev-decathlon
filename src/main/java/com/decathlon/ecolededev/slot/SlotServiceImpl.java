package com.decathlon.ecolededev.slot;

import java.time.LocalDateTime;
import java.util.List;

public class SlotServiceImpl implements SlotService {

    @Override
    public boolean isAvailable(List<Slot> slotList, Slot slot) {
        for (Slot s : slotList) {
            //Conflit si :
            //si le slot commence après le début du slot courant ET avant la fin du slot courant
            //OU
            //si le slot fini après le début du slot courant ET avant la fin du slot courant
            //OU
            //si le slot commence avant le début du slot courant ET fini après la fin du slot courant
            if (slot.getStart().isAfter(s.getStart()) && slot.getStart().isBefore(s.getEnd())
                    || slot.getEnd().isAfter(s.getStart()) && slot.getEnd().isAfter(s.getEnd())
                    || slot.getStart().isBefore(s.getEnd()) && slot.getEnd().isAfter(s.getEnd())) {
                return false;
            }


        }
        return true;
    }

    @Override
    public List<Slot> conflictingSlot(List<Slot> slotList) {
        return null;
    }

    @Override
    public List<Slot> add(List<Slot> slotList, Slot slot) {
        return null;
    }

    @Override
    public List<Slot> remove(List<Slot> slotList, Slot slot) {
        return null;
    }

    @Override
    public boolean isCorrectSlot(Slot slot) {
        return (slot.getEnd().isAfter(slot.getStart()));
    }

    @Override
    public List<Slot> availableSlots(List<Slot> slotList, LocalDateTime startDate, LocalDateTime endDate, int hours) {
        return null;
    }
}

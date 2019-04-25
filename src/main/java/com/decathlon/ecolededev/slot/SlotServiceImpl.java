package com.decathlon.ecolededev.slot;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SlotServiceImpl implements SlotService {

   /* @Override
    public boolean isAvailable(List<Slot> slotList, Slot slot) {
        for (Slot s : slotList) {
            //Conflit si :
            //si le slot commence après le début du slot courant ET avant la fin du slot courant
            //OU
            //si le slot fini après le début du slot courant ET avant la fin du slot courant
            //OU
            //si le slot commence avant le début du slot courant ET fini après la fin du slot courant
            if (slot.getStart().isAfter(s.getStart()) && slot.getStart().isBefore(s.getEnd())
                    || slot.getEnd().isAfter(s.getStart()) && slot.getEnd().isBefore(s.getEnd())
                    || slot.getStart().isBefore(s.getEnd()) && slot.getEnd().isAfter(s.getEnd())
                    || slot.getStart().isEqual(s.getStart()) && slot.getEnd().isEqual(s.getEnd())) {
                return false;
            }
        }
        return true;
    }*/

    @Override
    public boolean isAvailable(List<Slot> slotList, Slot slotAValider) {

        for(Slot currentSlot:slotList){
            if (
                    !slotAValider.getEnd().isAfter(currentSlot.getStart()) // avant le slot
              ||      !slotAValider.getStart().isBefore(currentSlot.getEnd())
            ) {
                continue;
            }
            else{
                return false;}

        }
        return true;
    }

    public boolean totisAvailable(List<Slot> slotList, Slot slotAValider) {

        for(Slot currentSlot:slotList){
            if(slotAValider.getStart().isAfter(currentSlot.getEnd())
            //|| slotAValider.getStart().isEqual(currentSlot.getEnd())
            ){
                continue;
            }

            if (
                slotAValider.getStart().isAfter(currentSlot.getStart())
                        && (slotAValider.getEnd().isBefore(currentSlot.getEnd())) ||
                    slotAValider.getEnd().isAfter(currentSlot.getEnd())
            ) {
                return false;
            }

            if(slotAValider.getStart().isBefore(currentSlot.getStart())&&
             slotAValider.getEnd().isBefore(currentSlot.getEnd()) &&
            slotAValider.getEnd().isAfter(currentSlot.getStart())){
                return false;
            }
            if (slotAValider.getStart().isEqual(currentSlot.getStart())&&
                    slotAValider.getEnd().isEqual(currentSlot.getEnd())){
                return false;

            }
        }

        return true;
    }

    @Override
    public boolean isCorrectSlot(Slot slot) {
        return false;
    }


}

package com.decathlon.ecolededev.exceptions;

import com.decathlon.ecolededev.slot.Slot;

public class IncorrectSlotException extends Exception {

    public IncorrectSlotException(Slot slot){
        super("Incorrect slot for "+slot.getStart()+" to "+slot.getEnd());
    }
}

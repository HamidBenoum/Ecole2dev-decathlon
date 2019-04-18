package com.decathlon.ecolededev.slot;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SlotServiceImplTest {

    SlotService cut; // class under test
    List<Slot> slotList;

    @Before
    public void init(){
        cut = new SlotServiceImpl();
        Slot s1 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,10,00))
                .end(LocalDateTime.of(2018,01,15,14,00))
                .build();
        Slot s2 = Slot.builder()
                .start(LocalDateTime.of(2018,02,15,10,00))
                .end(LocalDateTime.of(2018,02,15,14,00))
                .build();
        slotList = new ArrayList<Slot>();
        slotList.add(s1);
        slotList.add(s2);

    }

    @Test
    public void slot_isNotAvailable_when_inside_a_slot(){



        cut.isAvailable()
    }

}
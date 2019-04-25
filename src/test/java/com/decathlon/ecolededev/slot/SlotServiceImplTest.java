package com.decathlon.ecolededev.slot;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SlotServiceImplTest {

    SlotService cut; // class under test
    List<Slot> slotList;

    @Before
    public void init(){
        cut = new SlotServiceImpl();

        //slot 0 : 2018-01-13T10:00 --> 2018-01-13T14:00
        Slot s0 = Slot.builder()
                .start(LocalDateTime.of(2018,01,13,10,00))
                .end(LocalDateTime.of(2018,01,13,14,00))
                .build();

        //slot 1 : 2018-01-15T10:00 --> 2018-01-15T14:00
        Slot s1 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,10,00))
                .end(LocalDateTime.of(2018,01,15,14,00))
                .build();

        //slot 2 : 2018-02-15T10:00 --> 2018-02-15T14:00
        Slot s2 = Slot.builder()
                .start(LocalDateTime.of(2018,02,15,10,00))
                .end(LocalDateTime.of(2018,02,15,14,00))
                .build();

        slotList = new ArrayList<Slot>();
        slotList.add(s1);
        slotList.add(s2);
        slotList.add(s0);

    }


    @Test public void slot_isNotAvailable_when_inside_a_slot(){
        Slot s3 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,11,00))
                .end(LocalDateTime.of(2018,01,15,13,00))
                .build();

        boolean result = cut.isAvailable(slotList,s3);

        assertFalse(result);

    }
    @Test public void slot_isNotAvailable_when_start_inside_a_slot() {
        Slot s4 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,11,00))
                .end(LocalDateTime.of(2018,01,15,15,00))
                .build();
        boolean result = cut.isAvailable(slotList,s4);
        assertFalse(result);

    }

    @Test public void slot_isAvailable_when_after_a_slot() {
        Slot s4 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,15,00))
                .end(LocalDateTime.of(2018,01,15,16,00))
                .build();
        boolean result = cut.isAvailable(slotList,s4);
        assertTrue(result);

    }
    @Test public void slot_isnotAvailable_when_end_inside_a_slot() {
        Slot s5 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,9,00))
                .end(LocalDateTime.of(2018,01,15,11,00))
                .build();
        boolean result = cut.isAvailable(slotList,s5);
        assertFalse(result);

    }

    @Test public void slot_isNotAvailable_when_slot_start_before_and_end_after (){
        Slot s5 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,9,00))
                .end(LocalDateTime.of(2018,01,15,15,00))
                .build();
        boolean result = cut.isAvailable(slotList,s5);
        assertFalse(result);
    }


    @Test public void slot_isNotAvailable_when_already_exist (){
        Slot s6 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,10,00))
                .end(LocalDateTime.of(2018,01,15,14,00))
                .build();
        boolean result = cut.isAvailable(slotList,s6);
        assertFalse(result);
    }

    @Test public void slot_isAvailable_when_before_a_slot() {
        Slot s4 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,8,00))
                .end(LocalDateTime.of(2018,01,15,9,00))
                .build();
        boolean result = cut.isAvailable(slotList,s4);
        assertTrue(result);

    }

    @Test public void slot_isAvailable_when_end_when_a_slot_start() {
        Slot s4 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,8,00))
                .end(LocalDateTime.of(2018,01,15,10,00))
                .build();
        boolean result = cut.isAvailable(slotList,s4);
        assertTrue(result);

    }

    @Test public void slot_isAvailable_when_start_when_a_slot_end() {
        Slot s4 = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,14,00))
                .end(LocalDateTime.of(2018,01,15,15,00))
                .build();
        boolean result = cut.isAvailable(slotList,s4);
        assertTrue(result);

    }

}
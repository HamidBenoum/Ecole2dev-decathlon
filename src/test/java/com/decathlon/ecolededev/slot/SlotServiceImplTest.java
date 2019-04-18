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
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,11,00))
                .end(LocalDateTime.of(2018,01,15,12,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertFalse(available);
    }

    @Test
    public void slot_isNotAvailable_when_start_inside_a_slot(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,11,00))
                .end(LocalDateTime.of(2018,01,15,16,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertFalse(available);
    }

    @Test
    public void slot_isNotAvailable_when_end_inside_a_slot(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,8,00))
                .end(LocalDateTime.of(2018,01,15,11,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertFalse(available);
    }

    @Test
    public void slot_isNotAvailable_when_slot_start_before_and_end_after(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,8,00))
                .end(LocalDateTime.of(2018,01,15,17,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertFalse(available);
    }

    @Test
    public void slot_isNotAvailable_when_already_exist(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,10,00))
                .end(LocalDateTime.of(2018,01,15,14,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertFalse(available);
    }

    @Test
    public void slot_isAvailable_when_before_a_slot(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,8,00))
                .end(LocalDateTime.of(2018,01,15,9,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertTrue(available);
    }

    @Test
    public void slot_isAvailable_when_after_a_slot(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,16,00))
                .end(LocalDateTime.of(2018,01,15,17,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertTrue(available);
    }

    @Test
    public void slot_isAvailable_when_start_when_a_slot_end(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,14,00))
                .end(LocalDateTime.of(2018,01,15,17,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertTrue(available);
    }

    @Test
    public void slot_isAvailable_when_end_when_a_slot_start(){
        Slot conflict = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,8,00))
                .end(LocalDateTime.of(2018,01,15,10,00))
                .build();

        boolean available = cut.isAvailable(slotList, conflict);

        assertTrue(available);
    }


    @Test
    public void incorrect_slot_start_before_end(){
        Slot s = Slot.builder()
                .start(LocalDateTime.of(2018,01,15,11,00))
                .end(LocalDateTime.of(2018,01,15,10,00))
                .build();

        assertFalse(cut.isCorrectSlot(s));
    }

}
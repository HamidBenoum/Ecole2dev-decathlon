package com.decathlon.ecolededev.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel,Long> {

    @Query("select b from BookingModel b where b.start >= ?1")
    List<BookingModel> findByStartingDate(LocalDateTime startingDate);

}

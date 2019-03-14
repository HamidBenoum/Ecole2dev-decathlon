package com.decathlon.ecolededev.repository;

import com.decathlon.ecolededev.repository.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel,Long> {

}

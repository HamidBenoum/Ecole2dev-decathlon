package com.decathlon.ecolededev.SportHall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportHallRespository extends JpaRepository<SportHallModel,Long> {


}

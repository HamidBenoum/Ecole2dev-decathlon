package com.decathlon.ecolededev.repository;

import com.decathlon.ecolededev.repository.model.SportHallModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportHallRespository extends JpaRepository<SportHallModel,Long> {


}

package com.decathlon.ecolededev.repository;

import com.decathlon.ecolededev.repository.model.HelloModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<HelloModel,Long> {



}

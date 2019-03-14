package com.decathlon.ecolededev.repository;

import com.decathlon.ecolededev.repository.model.AdresseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresseRepository extends JpaRepository<AdresseModel,Long> {

}

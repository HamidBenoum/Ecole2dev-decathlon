package com.decathlon.ecolededev.client;

import com.decathlon.ecolededev.client.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel,Long> {

}

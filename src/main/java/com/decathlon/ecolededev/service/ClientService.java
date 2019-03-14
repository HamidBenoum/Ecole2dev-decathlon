package com.decathlon.ecolededev.service;

import com.decathlon.ecolededev.pojo.Client;
import com.decathlon.ecolededev.repository.ClientRepository;
import com.decathlon.ecolededev.repository.model.ClientModel;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    public Client create(Client client){
        ClientModel clientModel = clientRepository.saveAndFlush(mapClientToClientModel(client));
        return mapClientModelToClient(clientModel);
    }

    private ClientModel mapClientToClientModel(Client client){

        return ClientModel.builder()
                .name(client.getName())
                .build();
    }

    private Client mapClientModelToClient(ClientModel clientModel){
        return Client.builder()
                .name(clientModel.getName())
                .id(clientModel.getId())
                .build();
    }
}

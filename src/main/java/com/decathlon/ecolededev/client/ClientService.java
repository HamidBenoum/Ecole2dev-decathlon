package com.decathlon.ecolededev.client;

import com.decathlon.ecolededev.client.Client;
import com.decathlon.ecolededev.client.ClientRepository;
import com.decathlon.ecolededev.client.ClientModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Client getOne(Long id){
        return mapClientModelToClient(clientRepository.getOne(id));
    }

    public List<Client> getAll(){
        return clientRepository.findAll()
                .stream()
                .map(clientModel -> mapClientModelToClient(clientModel))
                .collect(Collectors.toList());
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

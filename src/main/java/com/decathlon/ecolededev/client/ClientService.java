package com.decathlon.ecolededev.client;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) {
        ClientModel clientModel = clientRepository.saveAndFlush(mapClientToClientModel(client));
        return mapClientModelToClient(clientModel);
    }

    public Client getOne(Long id) {
        return mapClientModelToClient(clientRepository.getOne(id));
    }

    public List<Client> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientModel -> mapClientModelToClient(clientModel))
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        clientRepository.deleteById(id);
    }

    public Client update(Client client){
        ClientModel clientModel = clientRepository.getOne(client.getId());
        clientModel.setName(client.getName());
        ClientModel save = clientRepository.save(clientModel);
        return mapClientModelToClient(save);
    }


    private ClientModel mapClientToClientModel(Client client) {

        return ClientModel.builder()
                .name(client.getName())
                .build();
    }

    private Client mapClientModelToClient(ClientModel clientModel) {
        return Client.builder()
                .name(clientModel.getName())
                .id(clientModel.getId())
                .build();
    }
}

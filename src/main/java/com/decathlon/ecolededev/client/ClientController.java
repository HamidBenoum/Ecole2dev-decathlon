package com.decathlon.ecolededev.client;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService=clientService;
    }

    @PostMapping("/client/")
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @GetMapping("/client/{id}")
    public Client getById(@PathVariable Long id){
        return clientService.getOne(id);
    }

    @GetMapping("/client/")
    public List<Client> getClients(){
        return clientService.getAll();
    }

}

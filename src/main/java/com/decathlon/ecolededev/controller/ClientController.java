package com.decathlon.ecolededev.controller;

import com.decathlon.ecolededev.pojo.Client;
import com.decathlon.ecolededev.service.ClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}

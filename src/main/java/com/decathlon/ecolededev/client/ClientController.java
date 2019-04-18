package com.decathlon.ecolededev.client;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/client/")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public Client getById(@PathVariable Long id) {
       return clientService.getOne(id);
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getAll();
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.delete(id);
    }

    @PatchMapping
    public Client patchClient(@RequestBody Client client) {
        return clientService.update(client);
    }

}

package com.decathlon.ecolededev.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("html/client/")
public class ClientVueController {

    private ClientService clientService;

    public ClientVueController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("delete/{id}")
    public String deleteClient(@PathVariable Long id){
        clientService.delete(id);
        return "redirect:../list";
    }

    @GetMapping("list")
    public String listClient(Model model, @RequestParam(value = "errorMessage",required = false) String message) {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("newClient",new Client());

        if(!StringUtils.isEmpty(message)){
            model.addAttribute("errorMessage",message);
        }

        return "client";
    }

    @PostMapping(value = {"/ajouterClient"})
    public String savePerson(RedirectAttributes model, @ModelAttribute("client") Client client) {

        if(!StringUtils.isEmpty(client.getName())) {
            clientService.create(client);
        } else
        {
            model.addAttribute("errorMessage","Le nom ne peut pas être vide");
        }

        return "redirect:list";
    }
}

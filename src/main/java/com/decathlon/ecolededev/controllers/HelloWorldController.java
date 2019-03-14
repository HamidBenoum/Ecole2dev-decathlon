package com.decathlon.ecolededev.controllers;

import com.decathlon.ecolededev.repository.model.HelloModel;
import com.decathlon.ecolededev.services.HelloServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private HelloServices helloServices;

    public HelloWorldController(HelloServices helloServices){
        this.helloServices=helloServices;

    }

    @GetMapping(value = "/hello/{id}")
    public String hello(@PathVariable Long id){
        return "hello you are "+helloServices.getName(id);
    }

    @PostMapping(value="/hello/{name}")
    public HelloModel saveName(@PathVariable String name){
        HelloModel helloModel = helloServices.saveName(name);
        return helloModel;
    }


}

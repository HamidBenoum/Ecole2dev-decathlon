package com.decathlon.ecolededev.services;

import com.decathlon.ecolededev.repository.HelloRepository;
import com.decathlon.ecolededev.repository.model.HelloModel;
import org.springframework.stereotype.Service;

@Service
public class HelloServices {

    private HelloRepository helloRepository;

    public HelloServices(HelloRepository helloRepository){
        this.helloRepository=helloRepository;
    }

    public String getName(Long id){
        return helloRepository.getOne(id).getName();
    }

    public HelloModel saveName(String name){
        HelloModel helloModel=new HelloModel();
        helloModel.setName(name);
        helloRepository.save(helloModel);

        return helloRepository.save(helloModel);

    }

}

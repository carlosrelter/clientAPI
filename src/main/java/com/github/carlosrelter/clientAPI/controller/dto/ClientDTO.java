package com.github.carlosrelter.clientAPI.controller.dto;

import com.github.carlosrelter.clientAPI.model.Client;
import com.github.carlosrelter.clientAPI.model.TypeClient;

public record ClientDTO(Long id, String name, String cellphone, String email, TypeClient type) {

    public Client mapperClient(){
        Client client = new Client();
        client.setName(this.name);
        client.setCellphone(this.cellphone);
        client.setEmail(this.email);
        client.setType(this.type);
        return client;
    }
}

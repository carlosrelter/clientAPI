package com.github.carlosrelter.clientAPI.service;

import com.github.carlosrelter.clientAPI.model.Client;
import com.github.carlosrelter.clientAPI.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    public Client saveClient(Client client){
        return repository  .save(client);
    }

    public Optional<Client> findClientById(Long id){
        return repository.findById(id);
    }

    public void deleteClient(Client client){
        repository.delete(client);
    }

    public List<Client> listClient(){
        return repository.findAll();
    }

    public void updateClient(Client client){
        if(client.getId() == null){
            throw new IllegalArgumentException("Update need a client on base");
        }
        repository.save(client);
    }
}

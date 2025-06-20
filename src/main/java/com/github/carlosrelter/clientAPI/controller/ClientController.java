package com.github.carlosrelter.clientAPI.controller;

import com.github.carlosrelter.clientAPI.controller.dto.ClientDTO;
import com.github.carlosrelter.clientAPI.controller.mappers.ClientMapper;
import com.github.carlosrelter.clientAPI.model.Client;
import com.github.carlosrelter.clientAPI.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientService service;

    private final ClientMapper mapper;

    @PostMapping
    public ResponseEntity<ClientDTO> saveClient(@RequestBody @Valid ClientDTO clientDTO){
        var clientEntity = mapper.toEntity(clientDTO);
        service.saveClient(clientEntity);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> getDetailsClient(@PathVariable Long id){
        Optional<Client> clientOptional = service.findClientById(id);

        return service
                .findClientById(id)
                .map(client-> {
                    ClientDTO clientDTO = mapper.toDTO(client);
                    return ResponseEntity.ok(clientDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        Optional<Client> clientOptional = service.findClientById(id);
        if(clientOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deleteClient(clientOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> listClient(){
        List<Client> resultList = service.listClient();
        List<ClientDTO> list = resultList
                .stream()
                .map(client -> new ClientDTO(
                    client.getId(),
                    client.getName(),
                    client.getCellphone(),
                    client.getEmail(),
                    client.getType())
                ).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        Optional<Client> clientOptional = service.findClientById(id);
        if(clientOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var client = clientOptional.get();
        client.setName(clientDTO.name());
        client.setCellphone(clientDTO.cellphone());
        client.setEmail(clientDTO.email());
        client.setType(clientDTO.type());

        service.updateClient(client);

        return ResponseEntity.noContent().build();
    }

}

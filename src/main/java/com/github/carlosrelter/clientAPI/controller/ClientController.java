package com.github.carlosrelter.clientAPI.controller;

import com.github.carlosrelter.clientAPI.controller.dto.ClientDTO;
import com.github.carlosrelter.clientAPI.controller.mappers.ClientMapper;
import com.github.carlosrelter.clientAPI.model.Client;
import com.github.carlosrelter.clientAPI.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Clients")
public class ClientController {

    private final ClientService service;

    private final ClientMapper mapper;

    @PostMapping
    @Operation(summary = "Save", description = "Save new client")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "successfully registered"),
            @ApiResponse(responseCode = "422", description = "Validation Error")
    })
    public ResponseEntity<ClientDTO> saveClient(@RequestBody @Valid ClientDTO clientDTO){
        var clientEntity = mapper.toEntity(clientDTO);
        service.saveClient(clientEntity);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get Client Details", description = "Get details of client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
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
    @Operation(summary = "Delete", description = "Delete client existent")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        Optional<Client> clientOptional = service.findClientById(id);
        if(clientOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.deleteClient(clientOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get list", description = "Get list of clients")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully"),
            @ApiResponse(responseCode = "404", description = "Client list not found")
    })
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
    @Operation(summary = "Update", description = "Update Client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "successfully updated"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
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

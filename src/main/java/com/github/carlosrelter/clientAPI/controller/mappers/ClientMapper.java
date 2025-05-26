package com.github.carlosrelter.clientAPI.controller.mappers;

import com.github.carlosrelter.clientAPI.controller.dto.ClientDTO;
import com.github.carlosrelter.clientAPI.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO dto);

    ClientDTO toDTO(Client client);
}

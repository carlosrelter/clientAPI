package com.github.carlosrelter.clientAPI.controller.dto;

import com.github.carlosrelter.clientAPI.model.Client;
import com.github.carlosrelter.clientAPI.model.TypeClient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientDTO(
        Long id,
        @NotBlank(message = "campo obrigatório")
        @Size(max=100, min=2)
        String name,
        @NotBlank(message = "campo obrigatório")
        @Size(max=15, min=8)
        String cellphone,
        @NotBlank(message = "campo obrigatório")
        @Email
        String email,
        @NotNull(message = "campo obrigatório")
        TypeClient type) {
}

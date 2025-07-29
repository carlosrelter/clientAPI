package com.github.carlosrelter.clientAPI.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Register")
public record RegisterDTO(String email, String password, String name) {
}

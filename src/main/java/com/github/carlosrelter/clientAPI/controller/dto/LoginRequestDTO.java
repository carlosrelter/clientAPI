package com.github.carlosrelter.clientAPI.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Login")
public record LoginRequestDTO( String email, String password) {
}

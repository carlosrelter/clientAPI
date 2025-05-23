package com.github.carlosrelter.clientAPI.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int status, String message, List<ErrorField> errors) {

    //future record to implements
}


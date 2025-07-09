package com.example.Restss.dto;

import jakarta.validation.constraints.NotNull;

public record StudentDto(
        @NotNull(message = "Firstname should not be empty")
        String firstname,
        @NotNull(message = "Lastname should not be empty")
        String lastname,
        String email,
        Integer schoolId
) {
}

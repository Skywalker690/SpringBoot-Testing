package com.example.Restss.dto;

import jakarta.validation.constraints.NotNull;

public record StudentDto(
        @NotNull
        String firstname,
        @NotNull
        String lastname,
        String email,
        Integer schoolId
) {
}

package com.example.Restss.dto;

public record StudentDto(
        String firstname,
        String lastname,
        String email,
        Integer schoolId
) {
}

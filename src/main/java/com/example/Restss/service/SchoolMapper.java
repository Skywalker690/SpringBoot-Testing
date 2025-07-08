package com.example.Restss.service;

import com.example.Restss.dto.SchoolDto;
import com.example.Restss.dto.SchoolResponseDto;
import com.example.Restss.model.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDto dto){
        var school = new School();
        school.setId(dto.id());
        school.setName(dto.name());

        return school;

    }

    public SchoolResponseDto toschoolResponseDto(School school){
        return new SchoolResponseDto(
                school.getName());
    }
}

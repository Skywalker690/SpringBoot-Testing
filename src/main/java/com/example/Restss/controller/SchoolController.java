// File: SchoolController.java
package com.example.Restss.controller;

import com.example.Restss.dto.SchoolDto;
import com.example.Restss.dto.SchoolResponseDto;
import com.example.Restss.model.School;
import com.example.Restss.repository.SchoolJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private final SchoolJpaRepository schoolJpaRepository;

    @PostMapping("/schools")
    public SchoolResponseDto postSchool(@RequestBody SchoolDto dto){

        var school =toSchool(dto);
        var SavedSchool = schoolJpaRepository.save(school);
        return  toschoolResponseDto(SavedSchool);
    }


    private School toSchool(SchoolDto dto){
        var school = new School();
        school.setId(dto.id());
        school.setName(dto.name());

        return school;

    }

    private SchoolResponseDto toschoolResponseDto(School school){
        return new SchoolResponseDto(
                school.getName());
    }

    

    @GetMapping("/schools")
    public List<SchoolResponseDto> getSchools(){
        return schoolJpaRepository.findAll()
                .stream()
                .map(this::toschoolResponseDto)
                .collect(Collectors.toList());
    }




    public SchoolController(SchoolJpaRepository schoolJpaRepository) {
        this.schoolJpaRepository = schoolJpaRepository;
    }
}

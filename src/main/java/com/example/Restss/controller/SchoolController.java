// File: SchoolController.java
package com.example.Restss.controller;

import com.example.Restss.model.School;
import com.example.Restss.repository.SchoolJpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    private final SchoolJpaRepository schoolJpaRepository;

    @PostMapping("/schools")
    public School postSchool(@RequestBody School school){
        return schoolJpaRepository.save(school);
    }

    @GetMapping("/schools")
    public List<School> getSchools(){
        return schoolJpaRepository.findAll();
    }

    public SchoolController(SchoolJpaRepository schoolJpaRepository) {
        this.schoolJpaRepository = schoolJpaRepository;
    }
}

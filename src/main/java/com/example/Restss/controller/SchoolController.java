// File: SchoolController.java
package com.example.Restss.controller;

import com.example.Restss.dto.SchoolDto;
import com.example.Restss.dto.SchoolResponseDto;

import com.example.Restss.service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class SchoolController {

    private final SchoolService schoolService;




    @PostMapping("/schools")
    public SchoolResponseDto postSchool(@RequestBody SchoolDto dto){
        return schoolService.postSchool(dto);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> getSchools(){
        return schoolService.getSchools();
    }


    //Injection
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }
}

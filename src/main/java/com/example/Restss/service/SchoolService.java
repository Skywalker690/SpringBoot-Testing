package com.example.Restss.service;

import com.example.Restss.dto.SchoolDto;
import com.example.Restss.dto.SchoolResponseDto;
import com.example.Restss.model.School;
import com.example.Restss.repository.SchoolJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolJpaRepository schoolJpaRepository;
    private final SchoolMapper schoolMapper;


    public SchoolResponseDto postSchool(SchoolDto dto){

        var school =schoolMapper.toSchool(dto);
        var SavedSchool = schoolJpaRepository.save(school);
        return  schoolMapper.toschoolResponseDto(SavedSchool);
    }

    @GetMapping("/schools")
    public List<SchoolResponseDto> getSchools(){
        return schoolJpaRepository.findAll()
                .stream()
                .map(schoolMapper::toschoolResponseDto)
                .collect(Collectors.toList());
    }


    public SchoolService(SchoolJpaRepository schoolJpaRepository, SchoolMapper schoolMapper) {
        this.schoolJpaRepository = schoolJpaRepository;
        this.schoolMapper = schoolMapper;
    }
}

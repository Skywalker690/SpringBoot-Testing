package com.example.Restss.service;

import com.example.Restss.dto.StudentDto;
import com.example.Restss.dto.StudentResponseDto;
import com.example.Restss.model.Student;
import com.example.Restss.repository.StudentJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentJpaRepository repository;
    private final StudentMapper studentMapper;



    public StudentResponseDto SaveStudent(StudentDto dto){
        var student =studentMapper.toStudent(dto);
        var SavedStudent = repository.save(student);
        return studentMapper.tostudentResponseDto(SavedStudent);
    }

    public List<StudentResponseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto getById(int studentId){
        return repository.findById(studentId)
                .map(studentMapper::tostudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findAllByFirstname(String studentName){
        return repository.findAllByFirstnameContaining(studentName)
                .stream()
                .map(studentMapper::tostudentResponseDto)
                .collect(Collectors.toList());
    }

    public String deleteStudent(@PathVariable int studentId){
        repository.deleteById(studentId);
        return "Student is Deleted";
    }




    //Injection
    public StudentService(StudentJpaRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }
}

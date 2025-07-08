// File: StudentController.java
package com.example.Restss.controller;

import com.example.Restss.dto.StudentDto;
import com.example.Restss.dto.StudentResponseDto;
import com.example.Restss.model.Student;
import com.example.Restss.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto SaveStudent(@RequestBody StudentDto dto){
        return this.studentService.SaveStudent(dto);
    }


    @GetMapping("/students")
    public List<StudentResponseDto> FindAll(){
        return this.studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public StudentResponseDto getById(@PathVariable int studentId){
        return this.studentService.getById(studentId);
    }

    @GetMapping("/students/search/{studentName}")
    public List<StudentResponseDto> findAllByFirstname(@PathVariable String studentName){
        return this.studentService.findAllByFirstname(studentName);
    }

    @DeleteMapping("students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable int studentId){
        return this.studentService.deleteStudent(studentId);
    }
}

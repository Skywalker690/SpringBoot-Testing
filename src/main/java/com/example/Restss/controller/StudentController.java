// File: StudentController.java
package com.example.Restss.controller;

import com.example.Restss.dto.StudentDto;
import com.example.Restss.dto.StudentResponseDto;
import com.example.Restss.model.School;
import com.example.Restss.model.Student;
import com.example.Restss.repository.StudentJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentJpaRepository repository;

    public StudentController(StudentJpaRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/students")
    public StudentResponseDto post(@RequestBody StudentDto dto){
        var student =toStudent(dto);
        var SavedStudent = repository.save(student);
        return tostudentResponseDto(SavedStudent);
    }



    private StudentResponseDto tostudentResponseDto(Student student){
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }

    private Student toStudent(StudentDto dto){
        var student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);

        return student;
    }

    @GetMapping("/students")
    public List<Student> FindAll(){
        return repository.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getById(@PathVariable int studentId){
        return repository.findById(studentId).orElse(new Student());
    }

    @GetMapping("/students/search/{studentName}")
    public List<Student> findAllByFirstname(@PathVariable String studentName){
        return repository.findAllByFirstnameContaining(studentName);
    }

    @DeleteMapping("students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable int studentId){
        repository.deleteById(studentId);
        return "Student is Deleted";
    }
}

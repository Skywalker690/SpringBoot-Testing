// File: StudentController.java
package com.example.Restss.controller;

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
    public Student post(@RequestBody  Student student){
        return repository.save(student);
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

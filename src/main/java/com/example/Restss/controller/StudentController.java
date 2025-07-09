// File: StudentController.java
package com.example.Restss.controller;

import com.example.Restss.dto.StudentDto;
import com.example.Restss.dto.StudentResponseDto;
import com.example.Restss.model.Student;
import com.example.Restss.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto SaveStudent(@Valid @RequestBody StudentDto dto){
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

    //Last name should not be empty
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp){
        var errors = new HashMap<String,String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError)error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}

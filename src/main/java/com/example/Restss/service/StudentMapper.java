package com.example.Restss.service;

import com.example.Restss.dto.StudentDto;
import com.example.Restss.dto.StudentResponseDto;
import com.example.Restss.model.School;
import com.example.Restss.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto){

        if(dto==null){
            throw new NullPointerException("Student DTO cannot be Null");
        }
        var student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);

        return student;
    }

    public StudentResponseDto tostudentResponseDto(Student student){
        return new StudentResponseDto(
                student.getFirstname(),
                student.getLastname(),
                student.getEmail()
        );
    }
}

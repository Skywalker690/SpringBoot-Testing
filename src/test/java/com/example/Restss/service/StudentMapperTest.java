package com.example.Restss.service;

import com.example.Restss.dto.StudentDto;
import com.example.Restss.dto.StudentResponseDto;
import com.example.Restss.model.Student;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper=new StudentMapper();
    }


    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDto dto =new StudentDto(
                "Sanjo",
                "Siby",
                "sanjo@gmail.com",
                1
        );

        Student student =mapper.toStudent(dto);

        assertEquals(dto.firstname(),student.getFirstname());
        assertEquals(dto.lastname(),student.getLastname());
        assertEquals(dto.email(),student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(),student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        //Given
        Student student = new Student(
                "sanjo",
                "siby",
                19,
                "sanjo@gmail.com"
        );
        //When
        StudentResponseDto dto =mapper.tostudentResponseDto(student);

        //Then
        assertEquals(dto.firstname(),student.getFirstname());
        assertEquals(dto.lastname(),student.getLastname());
        assertEquals(dto.email(),student.getEmail());

    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null(){

        var exp = assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        assertEquals("Student DTO cannot be Null",exp.getMessage());
    }

}
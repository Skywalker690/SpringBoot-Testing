package com.example.Restss.service;

import com.example.Restss.dto.StudentDto;
import com.example.Restss.dto.StudentResponseDto;
import com.example.Restss.model.Student;
import com.example.Restss.repository.StudentJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    // Service to Test
    @InjectMocks
    private StudentService service;

    //Dependency of Service which need to Inject

    @Mock
    private StudentJpaRepository repository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_student(){

        //Given
        StudentDto dto =new StudentDto(
                "Sanjo",
                "Siby",
                "sanjo@gmail.com",
                1
        );

        Student student = new Student(
                "sanjo",
                "siby",
                19,
                "sanjo@gmail.com"
        );

        Student savedStudent = new Student(
                "sanjo",
                "siby",
                19,
                "sanjo@gmail.com"
        );
        savedStudent.setId(1);

        //Mock the calls

        when(studentMapper.toStudent(dto))
                .thenReturn(student);
        when(repository.save(student))
                .thenReturn(savedStudent);
        when(studentMapper.tostudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto(
                        "Sanjo",
                        "Siby",
                        "sanjo@gmail.com"
                ));

        //When
        StudentResponseDto responseDto =service.SaveStudent(dto);

        //Then

        assertEquals(dto.firstname(),responseDto.firstname());
        assertEquals(dto.lastname(),responseDto.lastname());
        assertEquals(dto.email(),responseDto.email());

        verify(studentMapper,times(1))
                .toStudent(dto);
        verify(repository,times(1))
                .save(student);
        verify(studentMapper,times(1))
                .tostudentResponseDto(savedStudent);
    }

    @Test
    public void should_Get_All_Students(){

        //Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Sanjo",
                "Siby",
                10,
                "sanjo@gmail.com"
        ));

        //Mock calls
        when(repository.findAll())
                .thenReturn(students);
        when(studentMapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "Sanjo",
                        "Siby",
                        "sanjo@gmail.com"
                ));
        //When
        List<StudentResponseDto> responseDto = service.findAll();

        //Then
        assertEquals(students.size(),responseDto.size());
        verify(repository,times(1)).findAll();
    }

    @Test
    public void get_StudentBy_Id(){

        //Given
        int studentId =1;
        Student student = new Student(
                "Sanjo",
                "Siby",
                20,
                "sanjo@gmail.com"
        );

        //Mock calls
        when(repository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "Sanjo",
                        "Siby",
                        "sanjo@gmail.com"
                ));
        //When
        StudentResponseDto dto =service.getById(studentId);

        //Then
        assertEquals(dto.firstname(),student.getFirstname());
        assertEquals(dto.lastname(),student.getLastname());
        assertEquals(dto.email(),student.getEmail());
        verify(repository,times(1)).findById(studentId);
    }

    @Test
    public void get_Student_By_Id(){

        //Given
        String name="Sanjo";
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "Sanjo",
                "Siby",
                10,
                "sanjo@gmail.com"
        ));

        //Mock calls
        when(repository.findAllByFirstnameContaining(name))
                .thenReturn(students);
        when(studentMapper.tostudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                "Sanjo",
                "Siby",
                "sanjo@gmail.com"
        ));

        //When
        List<StudentResponseDto> dto =service.findAllByFirstname(name);

        //Then
        assertEquals(students.size(),dto.size());
        verify(repository,times(1)).findAllByFirstnameContaining(name);

    }
}
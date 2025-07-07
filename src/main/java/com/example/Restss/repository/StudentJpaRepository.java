// File: StudentJpaRepository.java
package com.example.Restss.repository;

import com.example.Restss.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student,Integer> {

    List<Student> findAllByFirstnameContaining(String firstname);
}

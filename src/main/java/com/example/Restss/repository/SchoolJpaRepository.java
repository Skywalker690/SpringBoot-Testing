// File: SchoolJpaRepository.java
package com.example.Restss.repository;

import com.example.Restss.model.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolJpaRepository extends JpaRepository<School, Integer> {
}

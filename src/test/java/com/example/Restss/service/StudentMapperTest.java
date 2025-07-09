package com.example.Restss.service;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    @BeforeEach
    void setUp(){
        System.out.println("Inside the before each method");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Inside the after each method");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inside the beforeAll each method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Inside the afterAll each method");
    }

    @Test
    public void testMethod1(){
        System.out.println("My first test..");
    }

    @Test
    public void testMethod2(){
        System.out.println("My Second test..");
    }

}
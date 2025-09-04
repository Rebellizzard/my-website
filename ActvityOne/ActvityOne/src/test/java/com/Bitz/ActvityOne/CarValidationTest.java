package com.Bitz.ActvityOne;

import com.Bitz.ActvityOne.exception.CarValidationException;
import com.Bitz.ActvityOne.exception.DuplicateCarException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarValidationTest {

    @Autowired
    private Validator validator;

    private Car validCar;

    @BeforeEach
    void setUp() {
        validCar = new Car();
        validCar.setLicensePlateNumber("ABC123");
        validCar.setMake("Toyota");
        validCar.setModel("Corolla");
        validCar.setYear(2020);
        validCar.setColor("Red");
        validCar.setBodyType("Sedan");
        validCar.setEngineType("Gasoline");
        validCar.setTransmission("Automatic");
    }

    @Test
    void testValidCar() {
        Set<ConstraintViolation<Car>> violations = validator.validate(validCar);
        assertTrue(violations.isEmpty(), "Valid car should have no validation errors");
    }

    @Test
    void testInvalidLicensePlate() {
        validCar.setLicensePlateNumber(""); // Empty license plate
        Set<ConstraintViolation<Car>> violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Empty license plate should cause validation error");
        
        validCar.setLicensePlateNumber("a"); // Too short
        violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Too short license plate should cause validation error");
        
        validCar.setLicensePlateNumber("ABC123!@#"); // Invalid characters
        violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Invalid characters in license plate should cause validation error");
    }

    @Test
    void testInvalidMake() {
        validCar.setMake(""); // Empty make
        Set<ConstraintViolation<Car>> violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Empty make should cause validation error");
        
        validCar.setMake("A"); // Too short
        violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Too short make should cause validation error");
        
        validCar.setMake("Toyota123"); // Invalid characters
        violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Invalid characters in make should cause validation error");
    }

    @Test
    void testInvalidYear() {
        validCar.setYear(1800); // Too old
        Set<ConstraintViolation<Car>> violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Year before 1900 should cause validation error");
        
        validCar.setYear(2040); // Too future
        violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Year after 2030 should cause validation error");
    }

    @Test
    void testInvalidBodyType() {
        validCar.setBodyType("InvalidType");
        Set<ConstraintViolation<Car>> violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Invalid body type should cause validation error");
    }

    @Test
    void testInvalidEngineType() {
        validCar.setEngineType("InvalidEngine");
        Set<ConstraintViolation<Car>> violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Invalid engine type should cause validation error");
    }

    @Test
    void testInvalidTransmission() {
        validCar.setTransmission("InvalidTransmission");
        Set<ConstraintViolation<Car>> violations = validator.validate(validCar);
        assertFalse(violations.isEmpty(), "Invalid transmission should cause validation error");
    }
}


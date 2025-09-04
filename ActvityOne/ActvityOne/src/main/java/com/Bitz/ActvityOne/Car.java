package com.Bitz.ActvityOne;

import jakarta.validation.constraints.*;
import java.time.Year;

public class Car {
    private int carID;
    
    @NotBlank(message = "License plate number is required")
    @Pattern(regexp = "^[A-Z0-9\\-\\s]{2,10}$", message = "License plate must be 2-10 characters, letters, numbers, hyphens, and spaces only")
    private String licensePlateNumber;
    
    @NotBlank(message = "Make is required")
    @Size(min = 2, max = 50, message = "Make must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z\\s\\-&]+$", message = "Make can only contain letters, spaces, hyphens, and ampersands")
    private String make;
    
    @NotBlank(message = "Model is required")
    @Size(min = 1, max = 50, message = "Model must be between 1 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-&]+$", message = "Model can only contain letters, numbers, spaces, hyphens, and ampersands")
    private String model;
    
    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be 1900 or later")
    @Max(value = 2030, message = "Year must be 2030 or earlier")
    private int year;
    
    @NotBlank(message = "Color is required")
    @Size(min = 2, max = 30, message = "Color must be between 2 and 30 characters")
    @Pattern(regexp = "^[A-Za-z\\s\\-]+$", message = "Color can only contain letters, spaces, and hyphens")
    private String color;
    
    @NotBlank(message = "Body type is required")
    @Pattern(regexp = "^(Sedan|SUV|Hatchback|Coupe|Wagon|Truck)$", message = "Body type must be one of: Sedan, SUV, Hatchback, Coupe, Wagon, Truck")
    private String bodyType;
    
    @NotBlank(message = "Engine type is required")
    @Pattern(regexp = "^(Gasoline|Diesel|Electric|Hybrid)$", message = "Engine type must be one of: Gasoline, Diesel, Electric, Hybrid")
    private String engineType;
    
    @NotBlank(message = "Transmission is required")
    @Pattern(regexp = "^(Automatic|Manual)$", message = "Transmission must be either Automatic or Manual")
    private String transmission;

    // Default constructor
    public Car() {
    }

    // Constructor with all fields
    public Car(int carID, String licensePlateNumber, String make, String model, 
               int year, String color, String bodyType, String engineType, String transmission) {
        this.carID = carID;
        this.licensePlateNumber = licensePlateNumber;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.transmission = transmission;
    }

    // Getters and Setters
    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}



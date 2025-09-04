package com.Bitz.ActvityOne;

import com.Bitz.ActvityOne.exception.*;
import org.springframework.stereotype.Service;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CarService {
    private List<Car> cars;
    private int nextCarID;
    private static final String CSV_FILE = "cars.csv";
    
    @Autowired
    private Validator validator;

    public CarService() {
        cars = new ArrayList<>();
        nextCarID = 1;
        loadCarsFromCSV();
    }

    // Add a new car
    public void addCar(Car car) {
        validateCar(car);
        checkForDuplicateLicensePlate(car.getLicensePlateNumber());
        
        car.setCarID(nextCarID);
        nextCarID++;
        cars.add(car);
        saveCarsToCSV();
    }

    // Get all cars
    public List<Car> getAllCars() {
        return cars;
    }

    // Get car by ID
    public Car getCarById(int carID) {
        for (Car car : cars) {
            if (car.getCarID() == carID) {
                return car;
            }
        }
        throw new CarNotFoundException("Car with ID " + carID + " not found");
    }

    // Update car
    public void updateCar(Car updatedCar) {
        validateCar(updatedCar);
        
        // Check if car exists
        Car existingCar = getCarById(updatedCar.getCarID());
        
        // Check for duplicate license plate (excluding current car)
        checkForDuplicateLicensePlate(updatedCar.getLicensePlateNumber(), updatedCar.getCarID());
        
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarID() == updatedCar.getCarID()) {
                cars.set(i, updatedCar);
                saveCarsToCSV();
                break;
            }
        }
    }

    // Delete car
    public void deleteCar(int carID) {
        // Check if car exists
        getCarById(carID);
        
        boolean removed = cars.removeIf(car -> car.getCarID() == carID);
        if (removed) {
            saveCarsToCSV();
        }
    }

    // Save cars to CSV file
    private void saveCarsToCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {
            // Write header
            writer.println("CarID,LicensePlateNumber,Make,Model,Year,Color,BodyType,EngineType,Transmission");
            
            // Write car data
            for (Car car : cars) {
                writer.println(String.format("%d,%s,%s,%s,%d,%s,%s,%s,%s",
                    car.getCarID(),
                    car.getLicensePlateNumber(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getColor(),
                    car.getBodyType(),
                    car.getEngineType(),
                    car.getTransmission()));
            }
        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
        }
    }

    // Load cars from CSV file
    private void loadCarsFromCSV() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Skip header
                }
                
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    try {
                        Car car = new Car(
                            Integer.parseInt(parts[0]),
                            parts[1] != null ? parts[1] : "",
                            parts[2] != null ? parts[2] : "",
                            parts[3] != null ? parts[3] : "",
                            Integer.parseInt(parts[4]),
                            parts[5] != null ? parts[5] : "",
                            parts[6] != null ? parts[6] : "",
                            parts[7] != null ? parts[7] : "",
                            parts[8] != null ? parts[8] : ""
                        );
                        cars.add(car);
                        
                        // Update nextCarID
                        if (car.getCarID() >= nextCarID) {
                            nextCarID = car.getCarID() + 1;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing car data: " + e.getMessage());
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
            throw new CarDataException("Failed to load car data from CSV file", e);
        }
    }
    
    // Validate car using Bean Validation
    private void validateCar(Car car) {
        if (car == null) {
            throw new CarValidationException("Car cannot be null");
        }
        
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        if (!violations.isEmpty()) {
            List<String> errors = new ArrayList<>();
            for (ConstraintViolation<Car> violation : violations) {
                errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
            }
            throw new CarValidationException(errors);
        }
    }
    
    // Check for duplicate license plate
    private void checkForDuplicateLicensePlate(String licensePlate) {
        checkForDuplicateLicensePlate(licensePlate, -1);
    }
    
    // Check for duplicate license plate (excluding specific car ID for updates)
    private void checkForDuplicateLicensePlate(String licensePlate, int excludeCarId) {
        for (Car car : cars) {
            if (car.getCarID() != excludeCarId && 
                car.getLicensePlateNumber() != null && 
                car.getLicensePlateNumber().equalsIgnoreCase(licensePlate)) {
                throw new DuplicateCarException("A car with license plate '" + licensePlate + "' already exists");
            }
        }
    }
}



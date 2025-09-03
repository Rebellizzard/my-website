package com.Bitz.ActvityOne;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {
    private List<Car> cars;
    private int nextCarID;
    private static final String CSV_FILE = "cars.csv";

    public CarService() {
        cars = new ArrayList<>();
        nextCarID = 1;
        loadCarsFromCSV();
    }

    // Add a new car
    public void addCar(Car car) {
        car.setCarID(nextCarID);
        nextCarID++;
        cars.add(car);
        saveCarsToCSV();
    }

    // Get all cars
    public List<Car> getAllCars() {
        return cars;
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
                    Car car = new Car(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        Integer.parseInt(parts[4]),
                        parts[5],
                        parts[6],
                        parts[7],
                        parts[8]
                    );
                    cars.add(car);
                    
                    // Update nextCarID
                    if (car.getCarID() >= nextCarID) {
                        nextCarID = car.getCarID() + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading from CSV: " + e.getMessage());
        }
    }
}



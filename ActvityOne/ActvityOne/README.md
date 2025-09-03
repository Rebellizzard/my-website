# 🚗 Car Management System

A simple Spring Boot web application to store and manage car information.

## Features

- ✅ Add new cars with all required information
- ✅ Display cars in a beautiful table
- ✅ Auto-incrementing Car ID
- ✅ Data saved to CSV file automatically
- ✅ Dark blue theme with modern design
- ✅ Responsive design for mobile and desktop

## Car Attributes

- **Car ID**: Auto-incrementing unique identifier
- **License Plate Number**: Car's license plate
- **Make**: Car manufacturer (e.g., Toyota, Ford)
- **Model**: Car model (e.g., Corolla, Mustang)
- **Year**: Manufacturing year
- **Color**: Car color
- **Body Type**: Sedan, SUV, Hatchback, etc.
- **Engine Type**: Gasoline, Diesel, Electric, Hybrid
- **Transmission**: Automatic or Manual

## How to Run

1. **Make sure you have Java installed** (Java 17 or higher recommended)

2. **Open a terminal/command prompt** in the project folder

3. **Run the application** using Maven:
   ```bash
   mvn spring-boot:run
   ```

4. **Open your web browser** and go to:
   ```
   http://localhost:8080
   ```

## How to Use

1. **Add a New Car**:
   - Fill out the form at the top of the page
   - Click "Add Car" button
   - The car will appear in the table below

2. **View Cars**:
   - All cars are displayed in the table
   - Data is automatically saved to `cars.csv` file

3. **Data Persistence**:
   - All car data is saved to `cars.csv` in the project root
   - Data persists between application restarts

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/Bitz/ActvityOne/
│   │       ├── ActvityOneApplication.java  (Main app)
│   │       ├── Car.java                    (Car model)
│   │       ├── CarService.java             (Business logic)
│   │       └── CarController.java          (Web controller)
│   └── resources/
│       └── templates/
│           └── cars.html                   (Web page)
```

## Technologies Used

- **Spring Boot**: Web framework
- **Thymeleaf**: HTML template engine
- **Maven**: Build tool
- **CSV**: Simple data storage

## Notes

- This is a beginner-friendly application
- No database required - data is stored in CSV files
- Simple and clean design with dark blue theme
- Responsive design works on all devices

Enjoy managing your cars! 🚗✨



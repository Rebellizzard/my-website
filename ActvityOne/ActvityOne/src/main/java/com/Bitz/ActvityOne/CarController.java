package com.Bitz.ActvityOne;

import com.Bitz.ActvityOne.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
public class CarController {
    
    @Autowired
    private CarService carService;

    // Test endpoint
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    // Show the main page with car list
    @GetMapping("/")
    public String showCars(Model model) {
        try {
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("newCar", new Car());
            model.addAttribute("car", null); // Ensure car is null for add mode
            return "cars";
        } catch (Exception e) {
            System.out.println("Error loading cars: " + e.getMessage());
            model.addAttribute("cars", new java.util.ArrayList<>());
            model.addAttribute("newCar", new Car());
            model.addAttribute("car", null);
            model.addAttribute("errorMessage", "Failed to load cars. Please try again.");
            return "cars";
        }
    }

    // Add a new car
    @PostMapping("/addCar")
    public String addCar(@Valid @ModelAttribute("newCar") Car newCar, 
                        BindingResult bindingResult, 
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newCar", bindingResult);
            redirectAttributes.addFlashAttribute("newCar", newCar);
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the validation errors below.");
            return "redirect:/";
        }
        
        try {
            carService.addCar(newCar);
            redirectAttributes.addFlashAttribute("successMessage", "Car added successfully!");
        } catch (CarValidationException e) {
            redirectAttributes.addFlashAttribute("validationErrors", e.getValidationErrors());
            redirectAttributes.addFlashAttribute("newCar", newCar);
            redirectAttributes.addFlashAttribute("errorMessage", "Validation failed. Please check the form.");
        } catch (DuplicateCarException e) {
            redirectAttributes.addFlashAttribute("newCar", newCar);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            System.out.println("Error adding car: " + e.getMessage());
            redirectAttributes.addFlashAttribute("newCar", newCar);
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add car. Please try again.");
        }
        return "redirect:/";
    }

    // Show edit form
    @GetMapping("/editCar")
    public String showEditForm(@RequestParam int carID, Model model, RedirectAttributes redirectAttributes) {
        try {
            Car car = carService.getCarById(carID);
            model.addAttribute("car", car);
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("newCar", car); // Use the car data for the form
            return "cars";
        } catch (CarNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/";
        } catch (Exception e) {
            System.out.println("Error loading car for edit: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to load car for editing. Please try again.");
            return "redirect:/";
        }
    }

    // Update car
    @PostMapping("/updateCar")
    public String updateCar(@Valid @ModelAttribute("newCar") Car updatedCar, 
                           BindingResult bindingResult, 
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newCar", bindingResult);
            redirectAttributes.addFlashAttribute("newCar", updatedCar);
            redirectAttributes.addFlashAttribute("car", updatedCar);
            redirectAttributes.addFlashAttribute("errorMessage", "Please correct the validation errors below.");
            return "redirect:/editCar?carID=" + updatedCar.getCarID();
        }
        
        try {
            carService.updateCar(updatedCar);
            redirectAttributes.addFlashAttribute("successMessage", "Car updated successfully!");
        } catch (CarNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (CarValidationException e) {
            redirectAttributes.addFlashAttribute("validationErrors", e.getValidationErrors());
            redirectAttributes.addFlashAttribute("newCar", updatedCar);
            redirectAttributes.addFlashAttribute("car", updatedCar);
            redirectAttributes.addFlashAttribute("errorMessage", "Validation failed. Please check the form.");
            return "redirect:/editCar?carID=" + updatedCar.getCarID();
        } catch (DuplicateCarException e) {
            redirectAttributes.addFlashAttribute("newCar", updatedCar);
            redirectAttributes.addFlashAttribute("car", updatedCar);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/editCar?carID=" + updatedCar.getCarID();
        } catch (Exception e) {
            System.out.println("Error updating car: " + e.getMessage());
            redirectAttributes.addFlashAttribute("newCar", updatedCar);
            redirectAttributes.addFlashAttribute("car", updatedCar);
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update car. Please try again.");
            return "redirect:/editCar?carID=" + updatedCar.getCarID();
        }
        return "redirect:/";
    }

    // Delete car
    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam int carID, RedirectAttributes redirectAttributes) {
        try {
            carService.deleteCar(carID);
            redirectAttributes.addFlashAttribute("successMessage", "Car deleted successfully!");
        } catch (CarNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            System.out.println("Error deleting car: " + e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete car. Please try again.");
        }
        return "redirect:/";
    }
}



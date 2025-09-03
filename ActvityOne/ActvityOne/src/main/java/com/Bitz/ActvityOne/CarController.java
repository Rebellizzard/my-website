package com.Bitz.ActvityOne;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CarController {
    
    private CarService carService = new CarService();

    // Show the main page with car list
    @GetMapping("/")
    public String showCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("newCar", new Car());
        return "cars";
    }

    // Add a new car
    @PostMapping("/addCar")
    public String addCar(@ModelAttribute Car newCar) {
        carService.addCar(newCar);
        return "redirect:/";
    }
}



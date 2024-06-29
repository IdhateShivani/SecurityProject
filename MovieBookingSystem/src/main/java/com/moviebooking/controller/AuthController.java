package com.moviebooking.controller;

import com.moviebooking.model.User;
import com.moviebooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";  // This should return the view name for registration form
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login";  // After registration, redirect to login
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // This should return the view name for login form
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userService.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "redirect:/movies";  // Redirect to movies after successful login
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";  // Stay on login page if login fails
        }
    }
}

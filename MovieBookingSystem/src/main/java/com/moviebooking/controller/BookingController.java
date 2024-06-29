package com.moviebooking.controller;

import com.moviebooking.model.Seat;
import com.moviebooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/select-seats")
    public String showSeatSelection(Model model) {
        List<Seat> seats = seatService.getAllSeats();
        model.addAttribute("seats", seats);
        return "seatselection";
    }

    @PostMapping("/confirm-booking")
    public String confirmBooking(@RequestParam List<Long> seatIds, Model model) {
        // Add logic to handle booking confirmation and payment
        return "receipt";
    }
}

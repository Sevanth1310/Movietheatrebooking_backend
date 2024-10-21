package org.ps.controller;

import org.ps.entity.ConfirmBooking;
import org.ps.entity.ConfirmRegister;
import org.ps.entity.Registration;
import org.ps.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class ApiController {

    @Autowired
    private Service service;
    
    @PostMapping("/register")
    public String register(@RequestBody Registration registrationDetails) {
    	System.out.println("Registraion request recieved: " + registrationDetails);
    	return service.storeRegistraionDetails(registrationDetails);
    }

    @PostMapping("/login")
    public String login(@RequestBody ConfirmRegister loginDetails) {
    	return service.confirmLoginDetails(loginDetails);
    }
    
    
    @PostMapping("/selectMovie")
    public String selectMovie(@RequestParam("movieName") String movieName) {
    	return service.selectByMovieName(movieName);
    }
    
    @PostMapping("/bookSeats")
    public String bookSeats(@RequestBody ConfirmBooking bookingDetails) {
    	return service.seatBooking(bookingDetails);
    }
}

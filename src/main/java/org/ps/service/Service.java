package org.ps.service;

import org.ps.entity.ConfirmBooking;
import org.ps.entity.ConfirmRegister;
import org.ps.entity.Registration;

public interface Service {
	
	String storeRegistraionDetails(Registration registration);
	
	String confirmLoginDetails(ConfirmRegister loginConfirm);
    
    Boolean checkPhoneNumber(String phoneNumber);
    
    String selectByMovieName(String movieName);
    
    Boolean checkForMovieTable(String movieName);
    
    String seatBooking(ConfirmBooking cb);
}

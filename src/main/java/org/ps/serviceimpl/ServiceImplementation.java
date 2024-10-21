package org.ps.serviceimpl;

import org.ps.entity.Registration;
import org.ps.entity.ConfirmBooking;
import org.ps.entity.ConfirmRegister;
import org.ps.repository.MovieRepository;
import org.ps.repository.MovieWithSeatsRepository;
import org.ps.repository.RegistrationRepository;
import org.ps.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServiceImplementation implements Service {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RegistrationRepository registrationRepository;
    
    @Autowired
    private MovieWithSeatsRepository movieWithSeatsRepository;

    @Override
    public String storeRegistraionDetails(Registration registration) {
        if (checkPhoneNumber(registration.getPhoneNumber())) {
            System.out.println("entered if block ");
            return "Already Data Existed";
        } else {
            System.out.println("Entered else block ");
            registrationRepository.save(registration);
            return "Registered Successfully";
        }
    }

    @Override
    public Boolean checkPhoneNumber(String phoneNumber) {
        return registrationRepository.existsByPhoneNumber(phoneNumber);
    }	

    @Override
    public String confirmLoginDetails(ConfirmRegister loginDetails) {
        Registration user = registrationRepository.findByUsername(loginDetails.getUserName());
        if (user != null && user.getPassword().equals(loginDetails.getPassword())) {
            return "Data present";
        } else {
            return "Data not present";
        }
    }

	@Override
	public String selectByMovieName(String movieName) {
		System.out.println(movieName);
		if(checkForMovieTable(movieName)) {
			return "Movie present";
		}else {
			return "Movie not found";
		}
	}

	@Override
	public Boolean checkForMovieTable(String movieName) {
		return movieRepository.existsByMovieName(movieName);
	}

	@Transactional
	@Override
	public String seatBooking(ConfirmBooking cb) {
	    seatCount(cb.getMovieName());

	    for (String seatNumber : cb.getSeatNumber()) {
	        boolean seatFound = false;
	        for (Map.Entry<String, Integer> value : seatCountMap.entrySet()) {
	            if (seatNumber != null && seatNumber.equals(value.getKey())) {
	                seatFound = true;
	                System.out.println(value.getKey());
	                
	                if (value.getValue() == 0) {
	                    seatCountMap.put(value.getKey(), value.getValue() + 1);
	                    movieWithSeatsRepository.updateSeatCount(cb.getMovieName(), value.getKey(), 1);
	                } else {
	                    return "Seat " + seatNumber + " already booked";
	                }
	            }
	        }
	        if (!seatFound) {
	            return "Seat " + seatNumber + " not found";
	        }
	    }
	    return "Booking successful";
	}
	
	Map<String, Integer> seatCountMap = new HashMap<>();
	
	public Map<String, Integer> seatCount(String movieName){
		List<Object[]> results = movieWithSeatsRepository.checkSeatCount(movieName);
		
		for(Object[] result : results) {
			String seatNumber = (String) result[0];
            Integer seatCount = (Integer) result[1];
            seatCountMap.put(seatNumber, seatCount);
		}
		return seatCountMap;
	}
}

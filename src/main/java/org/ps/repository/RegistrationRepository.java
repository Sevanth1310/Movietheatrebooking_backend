package org.ps.repository;

import org.ps.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long>{
	Boolean existsByPhoneNumber(String phoneNumber);
	
	Registration findByUsername(String username);
}

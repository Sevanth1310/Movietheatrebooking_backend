package org.ps.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectMovie {
	private String movieName;
	
	@Id
	private Integer seatNumber;
	
	private Integer seatCount;	
}

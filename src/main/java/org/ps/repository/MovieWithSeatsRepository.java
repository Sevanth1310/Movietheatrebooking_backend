package org.ps.repository;

import java.util.List;
import org.ps.entity.MovieWithSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface MovieWithSeatsRepository extends JpaRepository<MovieWithSeats, Long>{
	@Query("SELECT m.seatNumber, m.seatCount from MovieWithSeats m where m.movieName = :movieName")
	List<Object[]> checkSeatCount(String movieName);
	
	@Modifying
	@Transactional
	@Query("UPDATE MovieWithSeats m SET m.seatCount = :seatCount WHERE m.movieName = :movieName AND m.seatNumber = :seatNumber")
	void updateSeatCount(@Param("movieName") String movieName, @Param("seatNumber") String seatNumber, @Param("seatCount") Integer seatCount);
}

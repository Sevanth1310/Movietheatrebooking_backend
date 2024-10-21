package org.ps.repository;

import org.ps.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	@Query("SELECT COUNT(m) > 0 FROM Movie m WHERE m.name = :movieName")
    Boolean existsByMovieName(@Param("movieName") String movieName);
}

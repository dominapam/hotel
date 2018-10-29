package com.challenge.hotel.checkin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface checkInRepository extends JpaRepository<checkIn, Long> {
	@Query
	List<checkIn> findByHospedeId(Long hospedeId);
}

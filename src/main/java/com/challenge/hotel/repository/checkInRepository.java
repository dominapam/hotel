package com.challenge.hotel.repository;

import com.challenge.hotel.model.checkIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface checkInRepository extends JpaRepository<checkIn, Long>{
	List<checkIn> findByHospedeId(Long hospedeId);
}

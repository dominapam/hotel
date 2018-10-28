package com.challenge.hotel.hospede;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hospedeRepository extends JpaRepository<hospede, Long> {
}

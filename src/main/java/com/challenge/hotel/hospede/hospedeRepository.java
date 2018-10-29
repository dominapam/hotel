package com.challenge.hotel.hospede;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface hospedeRepository extends JpaRepository<hospede, Long> {
//	@Query("SELECT checkIn.hospede FROM checkIn where checkIn.id = :checkinId")
//	hospede findByCheckinId(Long checkinId);
	
	@Query("select h from hospede h where h.nome like %?1% order by nome")
	List<hospede> findHospedeByNome(String nome);
	
	@Query("select h from hospede h where h.telefone = ?1 order by telefone")
	List<hospede> findHospedeByTelefone(Long telefone);
}

package com.challenge.hotel.hospede;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface hospedeRepository extends JpaRepository<hospede, Long> {
	@Query("select c.hospede from checkIn c where c.id = :checkinId")
	hospede findByCheckinId(Long checkinId);
	
	@Query("select c.hospede from checkIn c where c.dataEntrada = ?1")
	List<hospede> findByDataEntrada(LocalDateTime dataEntrada);
	
	@Query("select c.hospede from checkIn c where c.dataSaida = ?1")
	List<hospede> findByDataSaida(LocalDateTime dataSaida);
	
	@Query("select c.hospede from checkIn c where c.dataEntrada <= :dataHoje and c.dataSaida >= :dataHoje")
	List<hospede> findHospedesNoHotel(LocalDateTime dataHoje);
	
	@Query("select c.hospede from checkIn c where c.dataEntrada <= :dataHoje and c.dataSaida < :dataHoje")
	List<hospede> findHospedesSaidos(LocalDateTime dataHoje);
	
	@Query 
	List<hospede> findByNome(String nome);
	
	@Query
	List<hospede> findByTelefone(Long telefone);
}

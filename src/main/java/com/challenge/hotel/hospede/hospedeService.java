package com.challenge.hotel.hospede;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.challenge.hotel.exception.ResourceNotFoundException;

@Service
public class hospedeService {
	
	@Autowired
	private hospedeRepository hospedeRepository;
	
//	@Autowired
//	private checkInRepository checkInRepository;
	

	public hospede getHospede(Long hospedeId) {
		return hospedeRepository.findById(hospedeId).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

	public Page<hospede> getHospedes(Pageable pageable) {
		return hospedeRepository.findAll(pageable);
	}

	public List<hospede> getHospedesByNome(String nome) {
		return hospedeRepository.findHospedeByNome(nome);
	}
	
	public List<hospede> getHospedesByTelefone(Long telefone) {
		return hospedeRepository.findHospedeByTelefone(telefone);
	}
	
	public hospede createHospede(hospede hospede) {
		return hospedeRepository.save(hospede);
	}

	public hospede updateHospede(Long hospedeId,
			hospede hospedeRequest) {
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					hospede.setNome(hospedeRequest.getNome());
					hospede.setDocumento(hospede.getDocumento());
					hospede.setTelefone(hospedeRequest.getTelefone());
					return hospedeRepository.save(hospede);
				}).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));	
	}

	public ResponseEntity<?> deleteHospede(Long hospedeId) {
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					hospedeRepository.delete(hospede);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}
/*
	public List<hospede> getHospedesNoHotel() {
		LocalDateTime agora = LocalDateTime.now();
		List<hospede> hospedesNoHotel = new ArrayList<hospede>();
		
//		for (checkIn checkIn : checkInRepository.findAll()) {
//			if(agora.isBefore(checkIn.getDataSaida()) && agora.isAfter(checkIn.getDataEntrada())) {
//				hospedesNoHotel.add(hospedeRepository.findByCheckinId(checkIn.getId()));
//			}
//1		}
		
//		for (checkIn checkIn : checkInRepository.findAll()) {
//			if(agora.isBefore(checkIn.getDataSaida()) && agora.isAfter(checkIn.getDataEntrada())) {
//				hospedesNoHotel.add(checkIn.getHospede());		
//			}
//		}

		
//		checkInRepository.findAll().forEach((checkIn) -> {
//			if(agora.isBefore(checkIn.getDataSaida()) && agora.isAfter(checkIn.getDataEntrada())) {
//				hospedesNoHotel.add(checkIn.getHospede());
//			}
//		});
		if(hospedesNoHotel.isEmpty()) {
			throw new ResourceNotFoundException("Não há hospedes no hotel no momento");
		}
		
		return hospedesNoHotel;
	}
*/
}

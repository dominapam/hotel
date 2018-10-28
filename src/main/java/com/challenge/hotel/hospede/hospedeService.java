package com.challenge.hotel.hospede;

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
	

	public hospede getHospede(Long hospedeId) {
		return hospedeRepository.findById(hospedeId).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

	public Page<hospede> getHospedes(Pageable pageable) {
		return hospedeRepository.findAll(pageable);
	}

	public hospede createHospede(hospede hospede) {
		return hospedeRepository.save(hospede);
	}

	public hospede updateHospede(Long hospedeId,
			hospede hospedeRequest) {
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					hospede.setNome(hospedeRequest.getNome());
					hospede.setDocumento(hospedeRequest.getDocumento());
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


}

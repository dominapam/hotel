package com.challenge.hotel.hospede;

import java.time.LocalDateTime;
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

	
	public hospede getHospede(Long hospedeId) {
		return hospedeRepository.findById(hospedeId).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

	public Page<hospede> getHospedes(Pageable pageable) {
		return hospedeRepository.findAll(pageable);
	}

	public List<hospede> getHospedesByNome(String nome) {
		return hospedeRepository.findByNome(nome);
	}
	
	public List<hospede> getHospedesByTelefone(Long telefone) {
		return hospedeRepository.findByTelefone(telefone);
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
	
	public hospede getHospedeByCheckinId(Long CheckinId) {
		return hospedeRepository.findByCheckinId(CheckinId);
	}
	
	public List<hospede> getHospedeByDataEntrada(LocalDateTime dataEntrada) {
		return hospedeRepository.findByDataEntrada(dataEntrada);
	}
	
	public List<hospede> getHospedeByDataSaida(LocalDateTime dataSaida) {
		return hospedeRepository.findByDataSaida(dataSaida);
	}
	
	public List<hospede> getHospedesNoHotel() {
		LocalDateTime dataHoje = LocalDateTime.now();
		return hospedeRepository.findHospedesNoHotel(dataHoje);
	}
	
	public List<hospede> getHospedesSaidos() {
		LocalDateTime dataHoje = LocalDateTime.now();
		return hospedeRepository.findHospedesSaidos(dataHoje);
	}
}

package com.challenge.hotel.controller;

import com.challenge.hotel.model.hospede;
import com.challenge.hotel.exception.ResourceNotFoundException;
import com.challenge.hotel.repository.checkInRepository;
import com.challenge.hotel.repository.hospedeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
public class hospedeController {

	@Autowired
	private hospedeRepository hospedeRepository;

	@GetMapping("/hospedes/{hospedeId}")
	public hospede getHospede(@PathVariable Long hospedeId) {
		return hospedeRepository.findById(hospedeId).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

	@GetMapping("/hospedes")
	public Page<hospede> getHospedes(Pageable pageable) {
		return hospedeRepository.findAll(pageable);
	}

	@PostMapping("/hospedes")
	public hospede createHospede(@Valid @RequestBody hospede hospede) {
		return hospedeRepository.save(hospede);
	}

	@PutMapping("/hospedes/{hospedeId}")
	public hospede updateHospede(@PathVariable Long hospedeId,
			@Valid @RequestBody hospede hospedeRequest) {
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					hospede.setNome(hospedeRequest.getNome());
					hospede.setDocumento(hospedeRequest.getDocumento());
					hospede.setTelefone(hospedeRequest.getTelefone());
					return hospedeRepository.save(hospede);
				}).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));	
	}

	@DeleteMapping("/hospedes/{hospedeId}")
	public ResponseEntity<?> deleteHospede(@PathVariable Long hospedeId) {
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					hospedeRepository.delete(hospede);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

}



package com.challenge.hotel.controller;

import com.challenge.hotel.model.checkIn;
import com.challenge.hotel.model.hospede;
import com.challenge.hotel.model.valorEstadia;
import com.challenge.hotel.exception.ResourceNotFoundException;
import com.challenge.hotel.repository.hospedeRepository;
import com.challenge.hotel.repository.checkInRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class checkInController {

	@Autowired
	private checkInRepository checkInRepository;

	@Autowired
	private hospedeRepository hospedeRepository;

	@GetMapping("/hospedes/{hospedeId}/checkins")
	public List<checkIn> getCheckInsByHospedeId(@PathVariable Long hospedeId) {
		return checkInRepository.findByHospedeId(hospedeId);
	}

	@PostMapping("/hospedes/{hospedeId}/checkins")
	public checkIn addCheckIn(@PathVariable Long hospedeId,
			@Valid @RequestBody checkIn checkIn) {
		valorEstadia valorEstadia = new valorEstadia();
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					checkIn.setHospede(hospede);
					checkIn.setValorEstadia(valorEstadia.calculaValorEstadia(checkIn));
					checkInRepository.findByHospedeId(hospedeId).forEach((checkInTemp) -> {
						hospede.setGastoTotal(hospede.getGastoTotal() + checkInTemp.getValorEstadia());
					});
					return checkInRepository.save(checkIn);
				}).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

	@PutMapping("/hospedes/{hospedeId}/checkins/{checkInId}")
	public checkIn updateCheckIn(@PathVariable Long hospedeId,
			@PathVariable Long checkInId,
			@Valid @RequestBody checkIn checkInRequest) {
		if(!hospedeRepository.existsById(hospedeId)) {
			throw new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId);
		}

		return checkInRepository.findById(checkInId)
				.map(checkIn -> {
					checkIn.setDataEntrada(checkInRequest.getDataEntrada());
					checkIn.setDataSaida(checkInRequest.getDataSaida());
					checkIn.setAdicionalVeiculo(checkInRequest.isAdicionalVeiculo());
					return checkInRepository.save(checkIn);
				}).orElseThrow(() -> new ResourceNotFoundException("CheckIn não encontrado com id " + checkInId));
	}

	@DeleteMapping("/hospedes/{hospedeId}/checkins/{checkInId}")
	public ResponseEntity<?> deleteCheckIn(@PathVariable Long hospedeId,
			@PathVariable Long checkInId) {
		if(!hospedeRepository.existsById(hospedeId)) {
			throw new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId);
		}

		return checkInRepository.findById(checkInId)
				.map(checkIn -> {
					checkInRepository.delete(checkIn);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("CheckIn não encontrado com id " + checkInId));

	}


	@GetMapping("/hospedes/NoHotel")
	public List<hospede> getHospedesNoHotel() {

		LocalDateTime agora = LocalDateTime.now();
		List<hospede> hospedesNoHotel = new ArrayList<hospede>();

		checkInRepository.findAll().forEach((checkIn) -> {
			if(agora.isBefore(checkIn.getDataSaida()) && agora.isAfter(checkIn.getDataEntrada())) {
				hospedesNoHotel.add(checkIn.getHospede());
			}

		});

		return hospedesNoHotel;
	}

	@GetMapping("/hospedes/QueSairamDoHotel")
	public List<hospede> getHospedesQueSairamDoHotel() {

		LocalDateTime agora = LocalDateTime.now();
		List<hospede> hospedesNoHotel = new ArrayList<hospede>();

		checkInRepository.findAll().forEach((checkIn) -> {
			if(agora.isAfter(checkIn.getDataSaida()) && agora.isAfter(checkIn.getDataEntrada())) {
				hospedesNoHotel.add(checkIn.getHospede());
			}

		});

		return hospedesNoHotel;
	}


}

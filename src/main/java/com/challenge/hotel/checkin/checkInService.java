package com.challenge.hotel.checkin;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.challenge.hotel.exception.ResourceNotFoundException;
import com.challenge.hotel.hospede.hospedeRepository;

@Service
public class checkInService {

	@Autowired
	private checkInRepository checkInRepository;

	@Autowired
	private hospedeRepository hospedeRepository;

	final float diariaSemana = 120;
	final float diariaFimdeSemana = 150;
	final float diariaGaragemSemana = 15;
	final float diariaGaragemFimdeSemana = 20;
	final LocalTime diariaExtraApos = LocalTime.of(16, 30);

	public float calculaValorEstadia(checkIn checkIn) {
		LocalDateTime saida = checkIn.getDataSaida();
		LocalDateTime entrada = checkIn.getDataEntrada();
		float valor =  0;

		LocalDateTime i = entrada;
		while (i.isBefore(saida)) {

			if (isFimdeSemana(entrada)) {
				valor =+ diariaFimdeSemana;
			} else {
				valor =+ diariaSemana;
			}

			if (saida.toLocalTime().isAfter(diariaExtraApos)) {
				if (isFimdeSemana(entrada)) {
					valor =+ diariaFimdeSemana;
				} else {
					valor =+ diariaSemana;
				}
			}
			i.plusDays(1);		
		}
		return valor;
	}

	public boolean isFimdeSemana(LocalDateTime dia) {
		return (dia.getDayOfWeek().equals(DayOfWeek.SUNDAY) || dia.getDayOfWeek().equals(DayOfWeek.SATURDAY));
	}
			
	public checkIn addCheckIn(Long hospedeId, checkIn checkIn) {
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					checkIn.setHospede(hospede);
					checkIn.setValorEstadia(calculaValorEstadia(checkIn));
					getCheckInsByHospedeId(hospedeId).forEach((checkInTemp) -> {
						hospede.setGastoTotal(hospede.getGastoTotal() + checkInTemp.getValorEstadia());
					});
					hospedeRepository.save(hospede);
					return checkInRepository.save(checkIn);
				}).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

	public List<checkIn> getCheckInsByHospedeId(Long hospedeId) {
		return checkInRepository.findByHospedeId(hospedeId);
	}

	public checkIn updateCheckIn(Long hospedeId, Long checkInId, checkIn checkInRequest) {
		if(!hospedeRepository.existsById(hospedeId)) {
			throw new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId);
		}

		return checkInRepository.findById(checkInId)
				.map(checkIn -> {
					checkIn.setDataEntrada(checkInRequest.getDataEntrada());
					checkIn.setDataSaida(checkInRequest.getDataSaida());
					checkIn.setAdicionalVeiculo(checkInRequest.isAdicionalVeiculo());
					return checkInRepository.save(checkIn);
				}).orElseThrow(() -> new ResourceNotFoundException("Check-in não encontrado com id " + checkInId));
	}


	public ResponseEntity<?> deleteCheckIn(Long hospedeId, Long checkInId) {
		if(!hospedeRepository.existsById(hospedeId)) {
			throw new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId);
		}

		return checkInRepository.findById(checkInId)
				.map(checkIn -> {
					checkInRepository.delete(checkIn);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Check-in não encontrado com id " + checkInId));

	}
	
	/*
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
	*/
	
	public checkIn getCheckIn(Long checkinId) {
		return checkInRepository.findById(checkinId).orElseThrow(() -> new ResourceNotFoundException("Check-in não encontrado com id " + checkinId));
	}
	
}

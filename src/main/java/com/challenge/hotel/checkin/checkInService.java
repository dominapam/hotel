package com.challenge.hotel.checkin;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.challenge.hotel.exception.ResourceNotFoundException;
import com.challenge.hotel.hospede.hospede;
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

		LocalDate s = saida.toLocalDate();
		LocalDate e = entrada.toLocalDate();
		if (e.equals(s)) {
			if (isFimdeSemana(e)) {
				valor += diariaFimdeSemana;
				if (checkIn.isAdicionalVeiculo()) {valor += diariaGaragemFimdeSemana;}
			} else {
				valor += diariaSemana;
				if (checkIn.isAdicionalVeiculo()) {valor += diariaGaragemSemana;}
			}
		} else {
			while (e.isBefore(s)) {
				if (isFimdeSemana(e)) {
					valor += diariaFimdeSemana;
					if (checkIn.isAdicionalVeiculo()) {valor += diariaGaragemFimdeSemana;}
				} else {
					valor += diariaSemana;
					if (checkIn.isAdicionalVeiculo()) {valor += diariaGaragemSemana;}
				}
				e = e.plusDays(1);
			}
			if (saida.toLocalTime().isAfter(diariaExtraApos)) {
				if (isFimdeSemana(e)) {
					valor += diariaFimdeSemana;
					if (checkIn.isAdicionalVeiculo()) {valor += diariaGaragemFimdeSemana;}
				} else {
					valor += diariaSemana;
					if (checkIn.isAdicionalVeiculo()) {valor += diariaGaragemSemana;}
				}
			}
		}
		return valor;
	}

	public boolean isFimdeSemana(LocalDate dia) {
		return (dia.getDayOfWeek().equals(DayOfWeek.SUNDAY) || dia.getDayOfWeek().equals(DayOfWeek.SATURDAY));
	}

	public checkIn addCheckIn(Long hospedeId, checkIn checkIn) {
		return hospedeRepository.findById(hospedeId)
				.map(hospede -> {
					float valorEstadia = calculaValorEstadia(checkIn);
					hospede.setGastoTotal(hospede.getGastoTotal() + valorEstadia);
					hospede.setValorUltimaHospedagem(valorEstadia);
					hospedeRepository.save(hospede);
					checkIn.setHospede(hospede);
					checkIn.setValorEstadia(valorEstadia);				
					return checkInRepository.save(checkIn);
				}).orElseThrow(() -> new ResourceNotFoundException("Hospede não encontrado com id " + hospedeId));
	}

	public List<checkIn> getCheckInsByHospedeId(Long hospedeId) {
		return checkInRepository.findByHospedeId(hospedeId);
	}

	// List all check-ins
	public List<checkIn> getCheckIns() {
		return checkInRepository.findAll();
	}

	// Read a check-in
	public checkIn getCheckIn(Long checkinId) {
		return checkInRepository.findById(checkinId).orElseThrow(() -> new ResourceNotFoundException("Check-in não encontrado com id " + checkinId));
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
		hospede hospede = hospedeRepository.findById(hospedeId).get();
		hospede.setGastoTotal(hospede.getGastoTotal() - getCheckIn(checkInId).getValorEstadia());
		hospedeRepository.save(hospede);

		return checkInRepository.findById(checkInId)
				.map(checkIn -> {				
					checkInRepository.delete(checkIn);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Check-in não encontrado com id " + checkInId));
	}
	
	// Find all check-ins from the guest by the guests name
	public List<checkIn> getAllCheckinsByNome(String hospedeNome) {
		List<checkIn> checkins = new ArrayList<>();
		checkInRepository.findByHospedeNome(hospedeNome).forEach(checkins::add);
		return checkins;
	}
}

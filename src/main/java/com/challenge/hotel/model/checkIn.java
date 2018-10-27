package com.challenge.hotel.model;

import java.time.LocalDateTime;

public class checkIn {
	private int id;
	private hospede hospede;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	private boolean adicionalVeiculo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public hospede getHospede() {
		return hospede;
	}
	public void setHospede(hospede hospede) {
		this.hospede = hospede;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}
	public boolean isAdicionalVeiculo() {
		return adicionalVeiculo;
	}
	public void setAdicionalVeiculo(boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}	
	
}

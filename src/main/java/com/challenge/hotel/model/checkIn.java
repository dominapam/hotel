package com.challenge.hotel.model;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="checkins", schema="hotel")
public class checkIn extends auditModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "hospede_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private hospede hospede;

	@NotBlank
	@Column
	private LocalDateTime dataEntrada;

	@NotBlank
	@Column
	private LocalDateTime dataSaida;

	@Column
	private boolean adicionalVeiculo;

	@Column
	private float valorEstadia;

	public float getValorEstadia() {
		return valorEstadia;
	}
	public void setValorEstadia(float valorEstadia) {
		this.valorEstadia = valorEstadia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

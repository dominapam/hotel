package com.challenge.hotel.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="hospedes", schema="hotel")
public class hospede extends auditModel {

	@Id
	private Long documento;

	@NotBlank
	@Size(min = 2, max = 100)
	@Column
	private String nome;

	@Column
	private int telefone;

	@Column
	private float gastoTotal;

	public float getGastoTotal() {
		return gastoTotal;
	}
	public void setGastoTotal(float gastoTotal) {
		this.gastoTotal = gastoTotal;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getDocumento() {
		return documento;
	}
	public void setDocumento(Long documento) {
		this.documento = documento;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

}

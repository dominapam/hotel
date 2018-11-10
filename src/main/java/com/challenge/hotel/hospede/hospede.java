package com.challenge.hotel.hospede;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.challenge.hotel.auditModel.auditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="hospedes", schema="hotel")
public class hospede extends auditModel {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	private Long id; //Documento

	@NotBlank
	@Size(min = 2, max = 100)
	@Column
	private String nome;

	@Column
	private Long telefone;

	@Column
	private float gastoTotal;
	
	@Column
	private float valorUltimaHospedagem;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getValorUltimaHospedagem() {
		return valorUltimaHospedagem;
	}
	public void setValorUltimaHospedagem(float valorUltimaHospedagem) {
		this.valorUltimaHospedagem = valorUltimaHospedagem;
	}
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
		return id;
	}
	public void setDocumento(Long documento) {
		this.id = documento;
	}
	public Long getTelefone() {
		return telefone;
	}
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}
}

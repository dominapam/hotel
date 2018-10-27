package com.challenge.hotel.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="hospedes", schema="hotel")
public class hospede extends auditModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 100)
	@Column
	private String nome;
	
	@Column
	private int documento;

	@Column
	private int telefone;
	
    public Long getId() {
        return id;     
    }
    public void setId(Long id) {
        this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
		
}

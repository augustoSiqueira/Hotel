package br.com.hotel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoApartamento implements Serializable{

	private static final long serialVersionUID = -6197591846926084240L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTipoApartamento;
	
	@Column(name = "Nome", length = 100)
	private String nome;
	
	@Column(name = "Descricao", length = 200)
	private String descricao;
	
	@Column(name = "ValorDoApartamento")
	private Double valorDoApartamento;
	
	@Column(name = "Ativo")
	private boolean ativo;
	
	public TipoApartamento() {
		super();
	}

	public Integer getIdTipoApartamento() {
		return idTipoApartamento;
	}

	public void setIdTipoApartamento(Integer idTipoApartamento) {
		this.idTipoApartamento = idTipoApartamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
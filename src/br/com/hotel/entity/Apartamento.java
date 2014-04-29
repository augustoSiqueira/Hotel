package br.com.hotel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Apartamento")
public class Apartamento implements Serializable {

	private static final long serialVersionUID = 325593037159254564L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idApartamento;
	
	@Column(name = "Andar", length = 15)
	private String andar;
	
	@Column(name = "NumeroApartamento")
	private Integer numeroApartamento;
	
	@ManyToOne
	@JoinColumn(name = "TipoApartamento")
	private TipoApartamento tipoApartamento;
	
	@Column(name = "Ativo", nullable = false)
	private boolean ativo;
	
	public Apartamento() {
		super();
	}

	public Integer getIdApartamento() {
		return idApartamento;
	}

	public void setIdApartamento(Integer idApartamento) {
		this.idApartamento = idApartamento;
	}

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}

	public Integer getNumeroApartamento() {
		return numeroApartamento;
	}

	public void setNumeroApartamento(Integer numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}

	public TipoApartamento getTipoApartamento() {
		return tipoApartamento;
	}

	public void setTipoApartamento(TipoApartamento tipoApartamento) {
		this.tipoApartamento = tipoApartamento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
package br.com.hotel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@Column(name = "QuantidadeQuartos")
	private Integer quantidadeQuartos;
	
	@ManyToOne
	@JoinColumn(name = "TipoApartamento", referencedColumnName = "idTipoApartamento")
	private TipoApartamento tipoApartamento;
	
	@Column(name = "Ativo", nullable = false)
	private boolean ativo;
	
	@OneToMany
	private List<ImagemApartamento> imagemApartamento;
	
	@ManyToOne
	@JoinColumn(name = "Hotel", referencedColumnName = "idHotel")
	private Hotel hotel;
	
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

	public Integer getQuantidadeQuartos() {
		return quantidadeQuartos;
	}

	public void setQuantidadeQuartos(Integer quantidadeQuartos) {
		this.quantidadeQuartos = quantidadeQuartos;
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<ImagemApartamento> getImagemApartamento() {
		return imagemApartamento;
	}

	public void setImagemApartamento(List<ImagemApartamento> imagemApartamento) {
		this.imagemApartamento = imagemApartamento;
	}
}
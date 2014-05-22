package br.com.hotel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Hotel")
public class Hotel implements Serializable{

	private static final long serialVersionUID = 4278791180718658217L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idHotel;
	
	@Column(name = "NomeHotel", length = 100)
	private String nomeHotel;
	
	@Column(name = "Cnpj", length = 18)
	private String cnpj;
	
	@Embedded
	@Column(name = "Endereco")
	private Endereco endereco;
	
	@Column(name = "Ativo")
	private boolean ativo;
	
	@OneToMany(mappedBy = "hotel")
	private List<Apartamento> apartamento;
	
	public Hotel() {
		super();
	}

	public Integer getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Integer idHotel) {
		this.idHotel = idHotel;
	}

	public String getNomeHotel() {
		return nomeHotel;
	}

	public void setNomeHotel(String nomeHotel) {
		this.nomeHotel = nomeHotel;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Apartamento> getApartamento() {
		return apartamento;
	}

	public void setApartamentos(List<Apartamento> apartamento) {
		this.apartamento = apartamento;
	}
}
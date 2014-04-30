package br.com.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ReservaHospedagem implements Serializable{

	private static final long serialVersionUID = -5518342481118973631L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idReserva;
	
	@ManyToOne
	@JoinColumn(name = "Cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "Apartamento")
	private Apartamento apartamento;
	
	@Column(name = "Ativo", nullable = false)
	private boolean ativo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraPeriodoInicial;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraPeriodoFinal;
	
	@Temporal(TemporalType.DATE)
	private Date dataHoraEntrada;
	
	@Temporal(TemporalType.DATE)
	private Date dataHoraSaida;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SituacaoDaReserva")
	private SituacaoDaReserva situacaoDaReserva;

	public ReservaHospedagem() {
		super();
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Date getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public void setDataHoraEntrada(Date dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}

	public Date getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(Date dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public SituacaoDaReserva getSituacaoDaReserva() {
		return situacaoDaReserva;
	}

	public void setSituacaoDaReserva(SituacaoDaReserva situacaoDaReserva) {
		this.situacaoDaReserva = situacaoDaReserva;
	}
}
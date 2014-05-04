package br.com.hotel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = -29388116211803348L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idCliente;
	
	@Column(name = "Cpf", nullable = false)
	private String cpf;
	
	@Column(name = "Nome", nullable = false)
	private String nome;
	
	@Column(name = "Rg", nullable = false)
	private String rg;
	
	@Column(name = "Telefone")
	private String telefone;
	
	@Column(name = "Celular")
	private String celular;
	
	@Embedded
	@Column(nullable = false)
	private Endereco endereco;
	
	@Column(name = "Ativo", nullable = false, length = 1)
	private boolean ativo;
	
	@OneToOne(mappedBy = "cliente")
	@JoinColumn(name = "Usuario")
	private Usuario usuario;
	
	public Cliente() {
		super();
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
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
}
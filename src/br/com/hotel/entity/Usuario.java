package br.com.hotel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = -1651247188651228772L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idUsuario;

	@Column(name="Ativo")
	private boolean ativo;
	
	@Column(name="Nome", length = 200)
	private String nome;
	
	@Column(name="Login", length = 50, nullable = false)
	private String login;
	
	@Column(name="Senha", length = 12, nullable = false)
	private String senha;

	@Column(name="Email", length = 100)
	private String email;
	
	//private Cliente cliente;

	public Usuario() {
		super();
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
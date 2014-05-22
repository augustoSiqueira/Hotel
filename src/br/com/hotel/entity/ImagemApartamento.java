package br.com.hotel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ImagemApartamento")
public class ImagemApartamento implements Serializable{

	private static final long serialVersionUID = -4355441334764632811L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idImagem;
	
	@Column(name = "Url")
	private String url;
	
	public ImagemApartamento() {
		super();
	}
	public Integer getIdImagem() {
		return idImagem;
	}
	public void setIdImagem(Integer idImagem) {
		this.idImagem = idImagem;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
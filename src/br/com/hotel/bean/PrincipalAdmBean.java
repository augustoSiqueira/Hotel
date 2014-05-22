package br.com.hotel.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.hotel.util.RestricaoDePaginaAdm;

@ManagedBean(name = "principalAdmBean")
@ViewScoped
public class PrincipalAdmBean implements Serializable{

	private static final long serialVersionUID = 1313729433298900707L;

	private String tela;

	
	@PostConstruct
	public void init(){
		RestricaoDePaginaAdm.redirecionar();
		RequestContext.getCurrentInstance().update(":idFormPrincipal");
		

		this.tela = "<iframe src=\"\" style=\"width:99%; height:495px;border-color:white;border:1px;border-radius:25px;\"> </iframe>";
	}
	
	public void setarTela(String tela){
		this.tela = "<iframe src=\""+tela+"\" style=\"width:99%; height:495px;border-color:white;border:1px;border-radius:25px;\"> </iframe>";
//		org.primefaces.context.RequestContext.getCurrentInstance().update("idTela");
	}
	
	public void addMessage(String summary) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(summary, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, summary));
	}

	public void addMessageErro(String summary) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				summary,""));
	}

	public void addMessageAviso(String summary) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
				summary,""));
	}
	
	public void showTelaUsuario(){
		setarTela("cadastroUsuario.xhtml");
	}
	
	public void showTelaApartamento(){
		setarTela("cadastroApartamento.xhtml");
	}
	public void showTelaHotel(){
		setarTela("cadastroHotel.xhtml");
	}
	
	public void showTelaReserva(){
		setarTela("cadastroReserva.xhtml");
	}
	
	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}
}

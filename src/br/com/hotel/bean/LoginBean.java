package br.com.hotel.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.hotel.entity.TipoUsuario;
import br.com.hotel.entity.Usuario;
import br.com.hotel.fachada.Fachada;
import br.com.hotel.util.UtilSession;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 2969369454826694638L;

	Fachada fachada;
	
	PrincipalAdmBean principalAdmBean = new PrincipalAdmBean();
	
	private Usuario usuarioLogado;
	
	private String nomeUsuario;
	private String login;
	private String senha;
	
	@PostConstruct
	public void init(){
		//senha padrão de admin : 21232f297a57a5a743894a0e4a801fc3
	}
	
	public void logar(){		
		Usuario usuario = (Usuario) UtilSession.getHttpSessionObject("usuarioLogado");
	
		this.usuarioLogado = fachada.getInstancia().logar(login, senha);
		
		if (this.usuarioLogado != null) {
			if(this.usuarioLogado.getTipoUsuario() == TipoUsuario.SUPER_ADMIN || 
					this.usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN){
				if(this.usuarioLogado != null){
					UtilSession.setHttpSessionObject("usuarioLogado",this.usuarioLogado);
					this.nomeUsuario = this.usuarioLogado.getNome();						
					try {
						FacesContext.getCurrentInstance().getExternalContext().redirect("menuPrincipal.xhtml");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}	
				else{
					addMessageAviso("Login ou senha incorreto");	
					return;
				}	
			}else{
				addMessageAviso("Você não tem permissão para logar");	
				this.usuarioLogado = new Usuario();
				return;
			}
		}else{
			addMessageAviso("Usuário não encontrado");
			return;
		}
	}
	
	
	public String efetuaLogoff() {
		 FacesContext contexto = FacesContext.getCurrentInstance();
		 try {
			  contexto.getExternalContext().getSessionMap().remove("usuarioLogado");
			  FacesContext facesContext = FacesContext.getCurrentInstance();
			  HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			  session.invalidate();

			  FacesContext.getCurrentInstance().getExternalContext().redirect("loginAdm.xhtml");
			 
			  this.login = "";
			  this.senha = "";
			  this.nomeUsuario = "";

			  this.usuarioLogado = null;			 
		} catch (Exception e) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Erro ao encerar a sessão",""));
		}
		  return "loginAdm";
	 }
	
	public void addMessage(String summary) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				summary,""));
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


	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}


	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}


	public String getNomeUsuario() {
		return nomeUsuario;
	}


	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
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
}

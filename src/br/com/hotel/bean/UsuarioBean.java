package br.com.hotel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import br.com.hotel.entity.TipoUsuario;
import br.com.hotel.entity.Usuario;
import br.com.hotel.fachada.Fachada;
import br.com.hotel.util.Criptografar;
import br.com.hotel.util.RestricaoDePaginaAdm;
import br.com.hotel.util.UtilSession;
import br.com.hotel.util.ValidarEmail;

@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = -5260727667181474983L;

	private List<Usuario> listaUsuario;
	private List<Usuario> listaFiltroUsuario;
	private Usuario usuario;
	private Usuario usuarioSelecionado;
	private List<TipoUsuario> listaTipoUsuario;
	
	private String tipoUsuarioSelecionado;
	private boolean botaoListar;

	private Fachada fachada;
	
	@PostConstruct
	public void init(){
		RestricaoDePaginaAdm.redirecionar();
		
		Usuario user= (Usuario) UtilSession.getHttpSessionObject("usuarioLogado");
		
		if (user.getTipoUsuario() == TipoUsuario.SUPER_ADMIN) {
			this.botaoListar = false;
			RequestContext.getCurrentInstance().update("formUsuario");
		}else{
			this.botaoListar = true;
			RequestContext.getCurrentInstance().update("formUsuario");
		}
		
		this.listaTipoUsuario = new ArrayList<TipoUsuario>();
		this.listaTipoUsuario.add(TipoUsuario.ADMIN);
		this.listaTipoUsuario.add(TipoUsuario.SUPER_ADMIN);	
		
		this.usuario = new Usuario();
		this.usuarioSelecionado = new Usuario();
		this.listaUsuario = new ArrayList<Usuario>();
		this.listaUsuario = this.fachada.getInstancia().consultarUsuariosClientes();
	}
	
	public void listarAdministradores(){
		this.listaUsuario = this.fachada.getInstancia().consultarUsuariosAdministradores();
	}
	
	public void listarUsuariosClientes(){
		this.listaUsuario = this.fachada.getInstancia().consultarUsuariosClientes();
	}
	
	public void detalhesUsuario(){
		RequestContext.getCurrentInstance().update("formDetalhes");
		RequestContext.getCurrentInstance().execute("detalhesDialog.show();");
	}
	
	public void editarUsuario(){
		RequestContext.getCurrentInstance().update("formAlterar");
		RequestContext.getCurrentInstance().execute("alterarDialog.show();");
	}
	
	public void abrirCadastroUsuario(){
		Usuario user= (Usuario) UtilSession.getHttpSessionObject("usuarioLogado");
		
		if(user.getTipoUsuario() == TipoUsuario.SUPER_ADMIN){
			this.listaTipoUsuario = new ArrayList<TipoUsuario>();
			this.listaTipoUsuario.add(TipoUsuario.ADMIN);
			this.listaTipoUsuario.add(TipoUsuario.SUPER_ADMIN);	
		}
		
		if(user.getTipoUsuario() == TipoUsuario.ADMIN){
			this.listaTipoUsuario = new ArrayList<TipoUsuario>();
			this.listaTipoUsuario.add(TipoUsuario.ADMIN);
		}
		
		RequestContext.getCurrentInstance().update("formCadastro");
		RequestContext.getCurrentInstance().execute("cadastroDialog.show();");
	}

	public void alterarUsuario(){
		if (validarAlterar()) {
			this.fachada.getInstancia().alterarUsuario(this.usuarioSelecionado);
			addMessage("Atualizado com sucesso");
			RequestContext.getCurrentInstance().execute("alterarDialog.hide();");
			RequestContext.getCurrentInstance().update("formUsuario");
		}
	}
	
	private boolean validarAlterar(){
		if (this.usuarioSelecionado.getNome() == null || this.usuarioSelecionado.getNome().isEmpty()) {
			addMessageAviso("Digite o nome do Usuário");
			return false;
		}
		
		if(this.usuarioSelecionado.getEmail() == null || this.usuarioSelecionado.getEmail().isEmpty()){
			addMessageAviso("Digite o Email do Usuário");
			return false;
		}
		
		if (!ValidarEmail.validarEmail(this.usuarioSelecionado.getEmail())) {
			addMessageAviso("E-mail inválido");
			return false;
		}
		
		if(this.usuarioSelecionado.getLogin() == null || this.usuarioSelecionado.getLogin().isEmpty()){
			addMessageAviso("Digite o Login do Usuário");
			return false;
		}
		
		if(this.usuarioSelecionado.getTipoUsuario() == null || this.usuarioSelecionado.getTipoUsuario().name() == ""){
			addMessageAviso("Selecione um Tipo Usuário");
			return false;
		}
		return true;
	}
	
	public void cadastrarUsuario(){
		if (validarCadastro()) {
			this.fachada.getInstancia().inserirUsuario(this.usuario);
			addMessage("Cadastrado com sucesso");
			RequestContext.getCurrentInstance().execute("cadastroDialog.hide();");
			RequestContext.getCurrentInstance().update("formUsuario");
			this.usuario = new Usuario();
		}
	}
	
	private boolean validarCadastro(){
		if (this.usuario.getNome() == null || this.usuario.getNome().isEmpty()) {
			addMessageAviso("Digite o nome do Usuário");
			return false;
		}
		
		if(this.usuario.getEmail() == null || this.usuario.getEmail().isEmpty()){
			addMessageAviso("Digite o e-mail do Usuário");
			return false;
		}
		
		if (!ValidarEmail.validarEmail(this.usuario.getEmail())) {
			addMessageAviso("E-mail inválido");
			return false;
		}
		
		if(this.usuario.getLogin() == null || this.usuario.getLogin().isEmpty()){
			addMessageAviso("Digite o login do Usuário");
			return false;
		}
		
		if(this.fachada.getInstancia().existeLogin(this.usuario.getLogin())){
			addMessageAviso("O login já existe, Digite outro login");
			return false;
		}
		
		if(this.usuario.getSenha() == null || this.usuario.getSenha().isEmpty()){
			addMessageAviso("Digite a senha do Usuário");
			return false;
		}
		
		if (this.usuario.getSenha().length() < 5) {
			addMessageAviso("Digite uma senha maior que 6 dígitos");
			return false;
		}else{
			this.usuario.setSenha(Criptografar.gerarMd5(this.usuario.getSenha()));
		}
		
		if(this.usuario.getTipoUsuario() == null || this.usuario.getTipoUsuario().name() == ""){
			addMessageAviso("Selecione um Tipo Usuário");
			return false;
		}
		
		this.usuario.setAtivo(true);
		return true;
	}
	
	public void excluirUsuario(){
		this.usuarioSelecionado.setAtivo(false);
		this.fachada.getInstancia().alterarUsuario(this.usuarioSelecionado);
		addMessage("Excluído com sucesso");
		this.listaUsuario = this.fachada.getInstancia().consultarUsuariosClientes();
		RequestContext.getCurrentInstance().update("formUsuario:tableUsuario");
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
	
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public List<Usuario> getListaFiltroUsuario() {
		return listaFiltroUsuario;
	}

	public void setListaFiltroUsuario(List<Usuario> listaFiltroUsuario) {
		this.listaFiltroUsuario = listaFiltroUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<TipoUsuario> getListaTipoUsuario() {
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<TipoUsuario> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}

	public String getTipoUsuarioSelecionado() {
		return tipoUsuarioSelecionado;
	}

	public void setTipoUsuarioSelecionado(String tipoUsuarioSelecionado) {
		this.tipoUsuarioSelecionado = tipoUsuarioSelecionado;
	}

	public boolean isBotaoListar() {
		return botaoListar;
	}

	public void setBotaoListar(boolean botaoListar) {
		this.botaoListar = botaoListar;
	}
}
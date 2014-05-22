package br.com.hotel.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import br.com.hotel.entity.Apartamento;
import br.com.hotel.entity.ImagemApartamento;
import br.com.hotel.entity.TipoApartamento;
import br.com.hotel.fachada.Fachada;
import br.com.hotel.util.MensagemUtil;
import br.com.hotel.util.RestricaoDePaginaAdm;

@ManagedBean(name = "apartamentoBean")
@ViewScoped
public class ApartamentoBean implements Serializable{

	private static final long serialVersionUID = -5180551647670268868L;
	
	private Apartamento apartamento;
	private Apartamento apartamentoSelecionado;
	private List<Apartamento> listaApartamento;
	private List<Apartamento> listaApartamentoFiltro;
	
	private List<TipoApartamento> listaTipoApartamento;
	
	private List<ImagemApartamento> listaImagensApartamento;
	private ImagemApartamento imagemApartamento;
	
	private Fachada fachada;
	
	private String destination="C://Users//Augusto Siqueira//workspace//Hotel//WebContent//imagesApartamento//";
	private String numeroApartamento;
	private String quantidadeQuartos;
	private Integer idTipoApartamento = 0;
	private boolean mudarImagem = false;
	private long dataAtual;
	
	@PostConstruct
	public void init(){
		RestricaoDePaginaAdm.redirecionar();
		this.dataAtual = System.currentTimeMillis();
		System.out.println(dataAtual);
		this.apartamento = new Apartamento();
		this.apartamentoSelecionado = new Apartamento();
		this.imagemApartamento = new ImagemApartamento();
		this.listaImagensApartamento  = new ArrayList<ImagemApartamento>();
		this.listaApartamento = this.fachada.getInstancia().consultarTodosApartamentoAtivos();
		this.listaTipoApartamento = this.fachada.getInstancia().consultarTodosTipoApartamento();
	}
	
	public void editarApartamento(){
		this.numeroApartamento = this.apartamentoSelecionado.getNumeroApartamento().toString();
		this.quantidadeQuartos = this.apartamentoSelecionado.getQuantidadeQuartos().toString();
		this.idTipoApartamento = this.apartamentoSelecionado.getTipoApartamento().getIdTipoApartamento();
		this.mudarImagem = true;
		this.listaImagensApartamento = this.apartamentoSelecionado.getImagemApartamento();
		RequestContext.getCurrentInstance().update("formAlterar");
		RequestContext.getCurrentInstance().execute("alterarDialog.show();");
	}
	
	public void abrirCadastroApartamento(){
		this.apartamento = new Apartamento();
		this.listaTipoApartamento = this.fachada.getInstancia().consultarTodosTipoApartamento();
		this.idTipoApartamento = 0;
		this.numeroApartamento = "";
		this.quantidadeQuartos = "";
		RequestContext.getCurrentInstance().update("formCadastro");
		RequestContext.getCurrentInstance().execute("cadastroDialog.show();");
	}
	
	public void detalhesApartamento(){
		this.numeroApartamento = this.apartamentoSelecionado.getNumeroApartamento().toString();
		this.quantidadeQuartos = this.apartamentoSelecionado.getQuantidadeQuartos().toString();
		this.idTipoApartamento = this.apartamentoSelecionado.getTipoApartamento().getIdTipoApartamento();
		RequestContext.getCurrentInstance().update("formApartamento");
		RequestContext.getCurrentInstance().execute("detalhesDialog.show();");
	}
	
	public void excluirApartamento(){
		this.apartamentoSelecionado.setAtivo(false);
		this.fachada.getInstancia().alterarApartamento(this.apartamentoSelecionado);
		MensagemUtil.addMessage("Excluído com sucesso!");
		this.listaApartamento = this.fachada.getInstancia().consultarTodosApartamentoAtivos();
		RequestContext.getCurrentInstance().update("formApartamento");
	}
	
	public void alterarImagem(){
		for (int i = 0; i < this.listaImagensApartamento.size(); i++) {
			File file = new File(this.destination + this.listaImagensApartamento.get(i).getUrl());

			if (file.exists()) {
				this.fachada.getInstancia().removerImagemApartamento(this.listaImagensApartamento.get(i));
				file.delete();
			}
		}
		this.mudarImagem = false;
		this.listaImagensApartamento = new ArrayList<ImagemApartamento>();
		RequestContext.getCurrentInstance().update("formAlterar:display");
	}
	
	public void alterarApartamento(){
		if (validarAlterar()) {
			this.fachada.getInstancia().alterarApartamento(this.apartamentoSelecionado);
			MensagemUtil.addMessage("Atualizado com sucesso!");
			this.apartamentoSelecionado = new Apartamento();
			this.listaApartamento = this.fachada.getInstancia().consultarTodosApartamentoAtivos();
			this.idTipoApartamento = 0;
			this.numeroApartamento = "";
			this.quantidadeQuartos = "";
			RequestContext.getCurrentInstance().update("formApartamento");
			RequestContext.getCurrentInstance().execute("alterarDialog.hide();");
		}
	}
	
	private boolean validarAlterar() {
		if (this.numeroApartamento == null || this.numeroApartamento.isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o número do Apartamento");
			return false;
		}else{
			try {
				Integer num = Integer.parseInt(this.numeroApartamento);
				this.apartamentoSelecionado.setNumeroApartamento(num);
			} catch (Exception e) {
				MensagemUtil.addMessageAviso("Informe apenas números");
				return false;
			}
		}
		
		if (this.apartamentoSelecionado.getAndar() == null || this.apartamentoSelecionado.getAndar().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o andar do Apartamento");
			return false;
		}
		
		if (this.quantidadeQuartos == null || this.quantidadeQuartos.isEmpty()) {
			MensagemUtil.addMessageAviso("Informe a quantidade de quartos do Apartamento");
			return false;
		}else{
			try {
				Integer num = Integer.parseInt(this.quantidadeQuartos);
				this.apartamentoSelecionado.setQuantidadeQuartos(num);
			} catch (Exception e) {
				MensagemUtil.addMessageAviso("Informe apenas números");
				return false;
			}
		}
		
		if (this.idTipoApartamento == 0) {
			MensagemUtil.addMessageAviso("Informe o Tipo do Apartamento");
			return false;
		}else{
			TipoApartamento tipoApartamento = this.fachada.getInstancia().consultarTipoApartamentoPorId(this.idTipoApartamento);
			if (tipoApartamento != null) {
				this.apartamentoSelecionado.setTipoApartamento(tipoApartamento);
			}else{
				MensagemUtil.addMessageAviso("Este Tipo de Apartamento foi removido, selecione outro");
				return false;
			}
		}
		return true;
	}

	public void cadastrarApartamento(){
		if (validarCadastro()) {
			this.apartamento.setAtivo(true);	
			
			for (ImagemApartamento imagem : this.apartamento.getImagemApartamento()) {
				this.fachada.getInstancia().inserirImagemApartamento(imagem);				
			}
			this.fachada.getInstancia().inserirApartamento(this.apartamento);
			MensagemUtil.addMessage("Cadastrado com sucesso!");
			this.apartamento = new Apartamento();
			this.listaImagensApartamento = new ArrayList<ImagemApartamento>();
			this.imagemApartamento = new ImagemApartamento();
			this.listaApartamento = this.fachada.getInstancia().consultarTodosApartamentoAtivos();
			this.idTipoApartamento = 0;
			this.numeroApartamento = "";
			this.quantidadeQuartos = "";
			RequestContext.getCurrentInstance().update("formApartamento:tableApartamento");
			RequestContext.getCurrentInstance().execute("cadastroDialog.hide();");
		}
	}
	
	private boolean validarCadastro() {
		if (this.numeroApartamento == null || this.numeroApartamento.isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o número do Apartamento");
			return false;
		}else{
			try {
				Integer num = Integer.parseInt(this.numeroApartamento);
				this.apartamento.setNumeroApartamento(num);
			} catch (Exception e) {
				MensagemUtil.addMessageAviso("Informe apenas números");
				return false;
			}
		}
		
		if (this.apartamento.getAndar() == null || this.apartamento.getAndar().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o andar do Apartamento");
			return false;
		}
		
		if (this.quantidadeQuartos == null || this.quantidadeQuartos.isEmpty()) {
			MensagemUtil.addMessageAviso("Informe a quantidade de quartos do Apartamento");
			return false;
		}else{
			try {
				Integer num = Integer.parseInt(this.quantidadeQuartos);
				this.apartamento.setQuantidadeQuartos(num);
			} catch (Exception e) {
				MensagemUtil.addMessageAviso("Informe apenas números");
				return false;
			}
		}
		
		if (this.idTipoApartamento == 0) {
			MensagemUtil.addMessageAviso("Informe o Tipo do Apartamento");
			return false;
		}else{
			TipoApartamento tipoApartamento = this.fachada.getInstancia().consultarTipoApartamentoPorId(this.idTipoApartamento);
			if (tipoApartamento != null) {
				this.apartamento.setTipoApartamento(tipoApartamento);
			}else{
				MensagemUtil.addMessageAviso("Este Tipo de Apartamento foi removido, selecione outro");
				return false;
			}
		}
		
		if (this.apartamento.getImagemApartamento() == null || this.apartamento.getImagemApartamento().size() == 0) {
			MensagemUtil.addMessageAviso("Selecione as imagens do Apartamento");
			return false;
		}
		
		return true;
	}

	public void cancelarCadastro(){
		this.apartamento = new Apartamento();
		for (int i = 0; i < this.listaImagensApartamento.size(); i++) {
			File file = new File(destination + this.listaImagensApartamento.get(i).getUrl());

			if (file.exists()) {
				file.delete();
			}
		}
		this.listaImagensApartamento = new ArrayList<ImagemApartamento>();
		RequestContext.getCurrentInstance().update("formApartamento");
		RequestContext.getCurrentInstance().execute("cadastroDialog.hide();");
	}
	
    public void upload(FileUploadEvent event) {    
        FacesMessage msg = new FacesMessage("Imagem armazenada!", "");    
        FacesContext.getCurrentInstance().addMessage(null, msg);  
                
        try {  		
        	this.dataAtual = System.currentTimeMillis();
            copyFile(this.dataAtual+event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }    
  
	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(destination + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			this.imagemApartamento.setUrl(fileName);
			this.listaImagensApartamento.add(this.imagemApartamento);
			this.apartamento.setImagemApartamento(this.listaImagensApartamento);
		
			this.imagemApartamento = new ImagemApartamento();
			
			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Apartamento getApartamento() {
		return apartamento;
	}

	public void setApartamento(Apartamento apartamento) {
		this.apartamento = apartamento;
	}

	public Apartamento getApartamentoSelecionado() {
		return apartamentoSelecionado;
	}

	public void setApartamentoSelecionado(Apartamento apartamentoSelecionado) {
		this.apartamentoSelecionado = apartamentoSelecionado;
	}

	public List<Apartamento> getListaApartamento() {
		return listaApartamento;
	}

	public void setListaApartamento(List<Apartamento> listaApartamento) {
		this.listaApartamento = listaApartamento;
	}

	public List<Apartamento> getListaApartamentoFiltro() {
		return listaApartamentoFiltro;
	}

	public void setListaApartamentoFiltro(List<Apartamento> listaApartamentoFiltro) {
		this.listaApartamentoFiltro = listaApartamentoFiltro;
	}

	public List<TipoApartamento> getListaTipoApartamento() {
		return listaTipoApartamento;
	}

	public void setListaTipoApartamento(List<TipoApartamento> listaTipoApartamento) {
		this.listaTipoApartamento = listaTipoApartamento;
	}

	public List<ImagemApartamento> getListaImagensApartamento() {
		return listaImagensApartamento;
	}

	public void setListaImagensApartamento(List<ImagemApartamento> listaImagensApartamento) {
		this.listaImagensApartamento = listaImagensApartamento;
	}

	public ImagemApartamento getImagemApartamento() {
		return imagemApartamento;
	}

	public void setImagemApartamento(ImagemApartamento imagemApartamento) {
		this.imagemApartamento = imagemApartamento;
	}

	public String getNumeroApartamento() {
		return numeroApartamento;
	}

	public void setNumeroApartamento(String numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}

	public String getQuantidadeQuartos() {
		return quantidadeQuartos;
	}

	public void setQuantidadeQuartos(String quantidadeQuartos) {
		this.quantidadeQuartos = quantidadeQuartos;
	}

	public Integer getIdTipoApartamento() {
		return idTipoApartamento;
	}

	public void setIdTipoApartamento(Integer idTipoApartamento) {
		this.idTipoApartamento = idTipoApartamento;
	}

	public boolean isMudarImagem() {
		return mudarImagem;
	}

	public void setMudarImagem(boolean mudarImagem) {
		this.mudarImagem = mudarImagem;
	}

	public long getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(long dataAtual) {
		this.dataAtual = dataAtual;
	}
}
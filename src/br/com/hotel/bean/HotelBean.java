package br.com.hotel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.hotel.entity.Apartamento;
import br.com.hotel.entity.Endereco;
import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.ImagemApartamento;
import br.com.hotel.fachada.Fachada;
import br.com.hotel.util.MensagemUtil;
import br.com.hotel.util.RestricaoDePaginaAdm;
import br.com.hotel.util.ValidaCNPJ;
import br.com.hotel.util.WebServiceCep;

@ManagedBean(name="hotelBean")
@ViewScoped
public class HotelBean implements Serializable{

	private static final long serialVersionUID = 9123372535862526352L;
	
	private List<Hotel> listaHotel;
	private List<Hotel> listaFiltroHotel;
	private Hotel hotel;
	private Hotel hotelSelecionado;
	private List<Apartamento> listaApartamento;
	private List<Apartamento> listaApartamentoFiltro;
	private List<Apartamento> listaApartamentoAdicionar;
	private Apartamento apartamentoSelecionado;
	private Endereco endereco;
	private Hotel cnpj;
	
	private Fachada fachada;

	private Integer idTipoApartamento;
	private String numeroApartamento, quantidadeQuartos;

	private List<ImagemApartamento> listaImagensApartamento;

	private boolean mostrarBotoes = false;

	private List<Apartamento> listaAlterarApartamento;
	
	@PostConstruct
	public void init(){
		RestricaoDePaginaAdm.redirecionar();
		this.listaHotel = this.fachada.getInstancia().consultarTodosHoteisAtivos();
		this.hotel = new Hotel();
		this.hotel.setEndereco(new Endereco());
		this.hotelSelecionado = new Hotel();
		this.apartamentoSelecionado = new Apartamento();
	}
	
	public void detalhesHotel(){
		this.listaApartamento = this.fachada.getInstancia().consultarTodosApartamentoAtivosDoHotel(this.hotelSelecionado.getIdHotel());
		RequestContext.getCurrentInstance().update("formDetalhes:panelDetalhe");
		RequestContext.getCurrentInstance().update("formDetalhes");
		RequestContext.getCurrentInstance().execute("detalhesDialog.show();");
	}
	
	public void detalhesApartamento(){
		this.numeroApartamento = this.apartamentoSelecionado.getNumeroApartamento().toString();
		this.quantidadeQuartos = this.apartamentoSelecionado.getQuantidadeQuartos().toString();
		this.idTipoApartamento = this.apartamentoSelecionado.getTipoApartamento().getIdTipoApartamento();
		this.listaImagensApartamento = this.apartamentoSelecionado.getImagemApartamento();
		RequestContext.getCurrentInstance().update("formDetalhesApartamento");
		RequestContext.getCurrentInstance().update("formDetalhesApartamento:tableApartamento");
		RequestContext.getCurrentInstance().execute("detalhesApartamentoDialog.show();");
	}
	
	public void excluirHotel(){
		this.hotelSelecionado.setAtivo(false);
		this.fachada.getInstancia().alterarHotel(this.hotelSelecionado);
		this.listaHotel = this.fachada.getInstancia().consultarTodosHoteisAtivos();
		RequestContext.getCurrentInstance().update("formHotel");
		MensagemUtil.addMessage("Removido com sucesso");
	}
	
	public void excluirApartamento(){
		for (int i = 0; i < this.listaApartamento.size(); i++) {
			this.listaApartamento.remove(this.apartamentoSelecionado);
		}
		this.hotelSelecionado.setApartamentos(this.listaApartamento);	
		this.apartamentoSelecionado.setHotel(null);
		this.fachada.getInstancia().alterarApartamento(this.apartamentoSelecionado);
		MensagemUtil.addMessage("Removido com sucesso");
	}
	
	public void editarHotel(){
		this.listaApartamento = this.hotelSelecionado.getApartamento();
		this.endereco = this.hotelSelecionado.getEndereco();
		this.cnpj = this.hotelSelecionado;
		RequestContext.getCurrentInstance().update("formAlterar:tableAlterarApartamento");
		RequestContext.getCurrentInstance().execute("alterarDialog.show();");
	}
	
	public void alterarHotel(){
		if (validarAlterar()) {
			this.fachada.getInstancia().alterarHotel(this.hotelSelecionado);
			MensagemUtil.addMessage("Atualizado com sucesso");
			RequestContext.getCurrentInstance().update("formHotel");	
			RequestContext.getCurrentInstance().execute("alterarDialog.hide();");
		}
	}
	
	public void filtrarCep(){
		if (!this.endereco.getCep().isEmpty()) {
			WebServiceCep webServiceCep = WebServiceCep.searchCep(this.endereco.getCep());
			
			if (webServiceCep.wasSuccessful()) {
				
				this.endereco.setCep(this.endereco.getCep());
				this.endereco.setLogradouro(webServiceCep.getLogradouroFull());
				this.endereco.setBairro(webServiceCep.getBairro());
				this.endereco.setCidade(webServiceCep.getCidade());
				this.endereco.setEstado(webServiceCep.getUf());
				this.setMostrarBotoes(false);
				RequestContext.getCurrentInstance().update("formAlterar:panelEndereco");
				RequestContext.getCurrentInstance().update("formCadastro:panelEndereco");
			}else{
				MensagemUtil.addMessageAviso("Informe um cep válido");
				this.hotel.setEndereco(new Endereco());
				this.hotelSelecionado.setEndereco(new Endereco());
				RequestContext.getCurrentInstance().update("formCadastro:panelEndereco");
				return;
			}
		}else{
			MensagemUtil.addMessageAviso("Informe o cep para filtrar");
			return;
		}
	}
	
	public void abrirCadastroHotel(){
		this.hotel = new Hotel();
		this.hotel.setEndereco(new Endereco());
		this.endereco = new Endereco();
		this.listaApartamento = this.fachada.getInstancia().consultarApartamentosNaoVinculados();
		RequestContext.getCurrentInstance().update("formCadastro");
		RequestContext.getCurrentInstance().update("formCadastro:tableApartamento");
		RequestContext.getCurrentInstance().execute("cadastroDialog.show();");
	}
	
	public void abrirListaApartamento(){
		this.listaAlterarApartamento = new ArrayList<Apartamento>();
		this.listaAlterarApartamento = this.fachada.getInstancia().consultarApartamentosNaoVinculados();
		RequestContext.getCurrentInstance().update("formAdicionarApartamento:tableApartamento");
		RequestContext.getCurrentInstance().execute("adicionarApartamentoDialog.show();");
	}
	
	public void adicionarApartamento(){
		this.listaApartamentoAdicionar = new ArrayList<Apartamento>();
		this.listaApartamentoAdicionar.add(this.apartamentoSelecionado);
		this.listaApartamento.remove(this.apartamentoSelecionado);
	}
	
	public void adicionarApartamento2(){
		this.listaApartamento.add(this.apartamentoSelecionado);
		this.listaAlterarApartamento.remove(this.apartamentoSelecionado);
		Apartamento a = this.fachada.getInstancia().consultarApartamentoPorId(this.apartamentoSelecionado.getIdApartamento());
		a.setHotel(this.hotelSelecionado);
		this.fachada.getInstancia().alterarApartamento(a);
		RequestContext.getCurrentInstance().update("formAlterar:tableAlterarApartamento");
		RequestContext.getCurrentInstance().update("formAdicionarApartamento:tableApartamento");
	}

	public void cadastrarHotel(){
		if (validarCadastro()) {
			this.mostrarBotoes = true;
			this.hotel.setAtivo(true);
			this.hotel.setEndereco(this.endereco);
			this.fachada.getInstancia().inserirHotel(this.hotel);
			for (int i = 0; i < this.hotel.getApartamento().size(); i++) {
				Apartamento a = new Apartamento();
				a = this.fachada.getInstancia().consultarApartamentoPorId(this.hotel.getApartamento().get(i).getIdApartamento());
				if (a != null) {

					a.setHotel(this.hotel);
					this.fachada.getInstancia().alterarApartamento(a);
				}
			}
			MensagemUtil.addMessage("Cadastrado com sucesso");
			this.hotel = new Hotel();
			this.endereco = new Endereco();
			this.listaHotel = this.fachada.getInstancia().consultarTodosHoteisAtivos();
			this.listaApartamentoAdicionar = new ArrayList<Apartamento>();
			this.mostrarBotoes = false;
			RequestContext.getCurrentInstance().update("formHotel:tableHotel");
			RequestContext.getCurrentInstance().update("formHotel");
			RequestContext.getCurrentInstance().execute("cadastroDialog.hide();");
		}
	}
	
	private boolean validarCadastro() {
		if (this.hotel.getNomeHotel() == null || this.hotel.getNomeHotel().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Nome do Hotel");
			return false;
		}
		
		if (this.hotel.getCnpj() == null || this.hotel.getCnpj().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o CNPJ do Hotel");
			return false;
		}

		if (!ValidaCNPJ.isCNPJ(this.hotel.getCnpj())) {
			MensagemUtil.addMessageAviso("Informe um CNPJ válido");
			return false;
		}
		
		if (this.fachada.getInstancia().consultarHotelPorCnpj(this.hotel.getCnpj())) {
			MensagemUtil.addMessageAviso("Este CNPJ já existe, Informe um diferente");
			return false;
		}
		
		if (this.listaApartamentoAdicionar == null || this.listaApartamentoAdicionar.size() == 0) {
			MensagemUtil.addMessageAviso("Selecione algum apartamento para o hotel");
			return false;
		}else{
			this.hotel.setApartamentos(this.listaApartamentoAdicionar);
		}
		
		if (this.endereco.getCep() == null || this.endereco.getCep().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Cep do Hotel");
			return false;
		}else{
			filtrarCep();
		}
		
		if (this.endereco.getNumero() == null || this.endereco.getNumero().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Número do Hotel");
			return false;
		}else{
			try {
				Integer num = Integer.parseInt(this.endereco.getNumero());
			} catch (Exception e) {
				MensagemUtil.addMessageAviso("Informe apenas números");
				return false;
			}
		}
		
		if (this.endereco.getComplemento() == null || this.endereco.getComplemento().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Complemento");
			return false;
		}
		
		this.hotel.setEndereco(this.endereco);
		
		return true;
	}
	
	private boolean validarAlterar() {
		if (this.hotelSelecionado.getNomeHotel() == null || this.hotelSelecionado.getNomeHotel().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Nome do Hotel");
			return false;
		}
		
		if (this.hotelSelecionado.getCnpj() == null || this.hotelSelecionado.getCnpj().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o CNPJ do Hotel");
			return false;
		}

		if (!ValidaCNPJ.isCNPJ(this.hotelSelecionado.getCnpj())) {
			MensagemUtil.addMessageAviso("Informe um CNPJ válido");
			return false;
		}
		
		if (this.fachada.getInstancia().consultarHotelPorCnpj(this.hotelSelecionado.getCnpj()) && !this.cnpj.getCnpj().equals(this.hotelSelecionado.getCnpj())) {
			MensagemUtil.addMessageAviso("Este CNPJ já existe, Informe um diferente");
			return false;
		}
		
		if (this.listaApartamento == null || this.listaApartamento.size() == 0) {
			MensagemUtil.addMessageAviso("Selecione algum apartamento para o hotel");
			return false;
		}else{
			this.hotel.setApartamentos(this.listaApartamento);
		}
		
		if (this.endereco.getCep() == null || this.endereco.getCep().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Cep do Hotel");
			return false;
		}else{
			filtrarCep();
		}
		
		if (this.endereco.getNumero() == null || this.endereco.getNumero().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Número do Hotel");
			return false;
		}else{
			try {
				Integer num = Integer.parseInt(this.endereco.getNumero());
			} catch (Exception e) {
				MensagemUtil.addMessageAviso("Informe apenas números");
				return false;
			}
		}
		
		if (this.endereco.getComplemento() == null || this.endereco.getComplemento().isEmpty()) {
			MensagemUtil.addMessageAviso("Informe o Complemento");
			return false;
		}
		
		this.hotelSelecionado.setEndereco(this.endereco);
		
		return true;
	}

	public List<Hotel> getListaHotel() {
		return listaHotel;
	}

	public void setListaHotel(List<Hotel> listaHotel) {
		this.listaHotel = listaHotel;
	}

	public List<Hotel> getListaFiltroHotel() {
		return listaFiltroHotel;
	}

	public void setListaFiltroHotel(List<Hotel> listaFiltroHotel) {
		this.listaFiltroHotel = listaFiltroHotel;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Hotel getHotelSelecionado() {
		return hotelSelecionado;
	}

	public void setHotelSelecionado(Hotel hotelSelecionado) {
		this.hotelSelecionado = hotelSelecionado;
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

	public Apartamento getApartamentoSelecionado() {
		return apartamentoSelecionado;
	}

	public void setApartamentoSelecionado(Apartamento apartamentoSelecionado) {
		this.apartamentoSelecionado = apartamentoSelecionado;
	}

	public Integer getIdTipoApartamento() {
		return idTipoApartamento;
	}

	public void setIdTipoApartamento(Integer idTipoApartamento) {
		this.idTipoApartamento = idTipoApartamento;
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

	public List<ImagemApartamento> getListaImagensApartamento() {
		return listaImagensApartamento;
	}

	public void setListaImagensApartamento(List<ImagemApartamento> listaImagensApartamento) {
		this.listaImagensApartamento = listaImagensApartamento;
	}

	public boolean isMostrarBotoes() {
		return mostrarBotoes;
	}

	public void setMostrarBotoes(boolean mostrarBotoes) {
		this.mostrarBotoes = mostrarBotoes;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Apartamento> getListaApartamentoAdicionar() {
		return listaApartamentoAdicionar;
	}

	public void setListaApartamentoAdicionar(
			List<Apartamento> listaApartamentoAdicionar) {
		this.listaApartamentoAdicionar = listaApartamentoAdicionar;
	}

	public List<Apartamento> getListaAlterarApartamento() {
		return listaAlterarApartamento;
	}

	public void setListaAlterarApartamento(List<Apartamento> listaAlterarApartamento) {
		this.listaAlterarApartamento = listaAlterarApartamento;
	}

	public Hotel getCnpj() {
		return cnpj;
	}

	public void setCnpj(Hotel cnpj) {
		this.cnpj = cnpj;
	}
}
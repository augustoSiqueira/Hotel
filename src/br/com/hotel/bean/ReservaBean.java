package br.com.hotel.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import br.com.hotel.entity.ImagemApartamento;
import br.com.hotel.entity.ReservaHospedagem;
import br.com.hotel.fachada.Fachada;
import br.com.hotel.util.RestricaoDePaginaAdm;

@ManagedBean(name = "reservaBean")
@ViewScoped
public class ReservaBean implements Serializable{
	
	private static final long serialVersionUID = -3571344792248970678L;

	private ReservaHospedagem reserva;
	private ReservaHospedagem reservaSelecionado;
	private List<ReservaHospedagem> listaReserva;
	private List<ReservaHospedagem> listaFiltroReserva;
	private List<ImagemApartamento> listaImagensApartamento;
	private Fachada fachada;
	
	private String enderecoDoClienteQueReservou = "";

	private boolean mostrarDataHoraEntrada;
	
	
	
	@PostConstruct
	public void init (){
		RestricaoDePaginaAdm.redirecionar();
		this.reserva = new ReservaHospedagem();
		this.reservaSelecionado = new ReservaHospedagem();
		this.listaReserva = new ArrayList<ReservaHospedagem>();
		this.listaReserva = fachada.getInstancia().consultarTodosReservaHospedagemCanceladas();
		this.mostrarDataHoraEntrada = false;
	}
	
	public void listarReservasCanceladas(){
		this.listaReserva = fachada.getInstancia().consultarTodosReservaHospedagemCanceladas();
		this.mostrarDataHoraEntrada = false;
	}
	
	public void listarReservasEmEspera(){
		this.listaReserva = fachada.getInstancia().consultarTodosReservaHospedagemEmEspera();
		this.mostrarDataHoraEntrada = false;
	}
	
	public void listarReservaHospedado(){
		this.listaReserva = fachada.getInstancia().consultarTodosReservaHospedagemComExito();
		this.mostrarDataHoraEntrada = true;
	}
	
	public void detalhesReserva(){	
		this.listaImagensApartamento = this.reservaSelecionado.getApartamento().getImagemApartamento();
		RequestContext.getCurrentInstance().execute("detalhesDialog.show();");
	}
	
	public void excluirReserva(){
		
	}
	
	public void abrirCadastroReserva(){
		this.reserva = new ReservaHospedagem();
		RequestContext.getCurrentInstance().update("formCadastro");
		RequestContext.getCurrentInstance().execute("cadastroDialog.show();");
	}
	
	public void mostrarImagensApartamento(){
		RequestContext.getCurrentInstance().update("formImagem");
		RequestContext.getCurrentInstance().execute("imagemDialog.show();");
	}
	
	public void cadastrarReserva(){
		
	}
	
	public void editarReserva(){
		
	}
	
	public void alterarReserva(){
		
	}

	public ReservaHospedagem getReserva() {
		return reserva;
	}

	public void setReserva(ReservaHospedagem reserva) {
		this.reserva = reserva;
	}

	public ReservaHospedagem getReservaSelecionado() {
		return reservaSelecionado;
	}

	public void setReservaSelecionado(ReservaHospedagem reservaSelecionado) {
		this.reservaSelecionado = reservaSelecionado;
	}

	public List<ReservaHospedagem> getListaReserva() {
		return listaReserva;
	}

	public void setListaReserva(List<ReservaHospedagem> listaReserva) {
		this.listaReserva = listaReserva;
	}

	public List<ReservaHospedagem> getListaFiltroReserva() {
		return listaFiltroReserva;
	}

	public void setListaFiltroReserva(List<ReservaHospedagem> listaFiltroReserva) {
		this.listaFiltroReserva = listaFiltroReserva;
	}

	public boolean isMostrarDataHoraEntrada() {
		return mostrarDataHoraEntrada;
	}

	public void setMostrarDataHoraEntrada(boolean mostrarDataHoraEntrada) {
		this.mostrarDataHoraEntrada = mostrarDataHoraEntrada;
	}

	public String getEnderecoDoClienteQueReservou() {
		return enderecoDoClienteQueReservou;
	}

	public void setEnderecoDoClienteQueReservou(
			String enderecoDoClienteQueReservou) {
		this.enderecoDoClienteQueReservou = enderecoDoClienteQueReservou;
	}

	public List<ImagemApartamento> getListaImagensApartamento() {
		return listaImagensApartamento;
	}

	public void setListaImagensApartamento(List<ImagemApartamento> listaImagensApartamento) {
		this.listaImagensApartamento = listaImagensApartamento;
	}
}
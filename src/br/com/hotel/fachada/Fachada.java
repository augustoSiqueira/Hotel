package br.com.hotel.fachada;

import java.util.List;

import br.com.hotel.entity.Apartamento;
import br.com.hotel.entity.Cliente;
import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.ReservaHospedagem;
import br.com.hotel.entity.TipoApartamento;
import br.com.hotel.entity.Usuario;
import br.com.hotel.serviceImp.ApartamentoServiceImp;
import br.com.hotel.serviceImp.ClienteServiceImp;
import br.com.hotel.serviceImp.HotelServiceImp;
import br.com.hotel.serviceImp.ReservaHospedagemServiceImp;
import br.com.hotel.serviceImp.TipoApartamentoServiceImp;
import br.com.hotel.serviceImp.UsuarioServiceImp;

public class Fachada implements IFachada{

	private static Fachada Instancia;
	
	private UsuarioServiceImp usuarioServiceImp;
	private ClienteServiceImp clienteServiceImp;
	private ApartamentoServiceImp apartamentoServiceImp;
	private HotelServiceImp hotelServiceImp;
	private ReservaHospedagemServiceImp reservaHospedagemServiceImp;
	private TipoApartamentoServiceImp tipoApartamentoServiceImp;
	
	private Fachada() {
		this.usuarioServiceImp = new UsuarioServiceImp();
		this.clienteServiceImp = new ClienteServiceImp();
		this.apartamentoServiceImp = new ApartamentoServiceImp();
		this.hotelServiceImp = new HotelServiceImp();
		this.reservaHospedagemServiceImp = new ReservaHospedagemServiceImp();
		this.tipoApartamentoServiceImp = new TipoApartamentoServiceImp();
	}

	public static synchronized Fachada getInstancia() {
		if (Instancia == null)
			Instancia = new Fachada();

		return Instancia;
	}

	
//	CONTROLE DE CLIENTE
	
	@Override
	public void inserirCliente(Cliente cliente) {
		this.clienteServiceImp.inserir(cliente);
	}

	@Override
	public void alterarCliente(Cliente cliente) {
		this.clienteServiceImp.atualizar(cliente);
	}

	@Override
	public void removerCliente(Cliente cliente) {
		this.clienteServiceImp.remover(cliente);
	}

	@Override
	public void removerClientePorId(Integer id) {
		this.clienteServiceImp.removerPorId(id);
	}

	@Override
	public List<Cliente> consultarTodosClientes() {
		return this.clienteServiceImp.listar();
	}

	@Override
	public Cliente consultarClientePorId(Integer id) {
		return this.clienteServiceImp.buscarPorId(id);
	}


	//	CONTROLE DE USUARIO
	
	@Override
	public void inserirUsuario(Usuario usuario) {
		this.usuarioServiceImp.inserir(usuario);
	}

	@Override
	public void alterarUsuario(Usuario usuario) {
		this.usuarioServiceImp.atualizar(usuario);
	}

	@Override
	public void removerUsuario(Usuario usuario) {
		this.usuarioServiceImp.remover(usuario);
	}

	@Override
	public void removerUsuarioPorId(Integer id) {
		this.usuarioServiceImp.removerPorId(id);
	}

	@Override
	public List<Usuario> consultarTodosUsuario() {
		return this.usuarioServiceImp.listar();
	}

	@Override
	public Usuario consultarUsuarioPorId(Integer id) {		
		return this.usuarioServiceImp.buscarPorId(id);
	}

	
//	CONTROLE DE APARTAMENTO
	
	@Override
	public void inserirApartamento(Apartamento apartamento) {
		this.apartamentoServiceImp.inserir(apartamento);
	}

	@Override
	public void alterarApartamento(Apartamento apartamento) {
		this.apartamentoServiceImp.atualizar(apartamento);
	}

	@Override
	public void removerApartamento(Apartamento apartamento) {
		this.apartamentoServiceImp.remover(apartamento);
	}

	@Override
	public void removerApartamentoPorId(Integer id) {
		this.apartamentoServiceImp.removerPorId(id);
	}

	@Override
	public List<Apartamento> consultarTodosApartamento() {
		return this.apartamentoServiceImp.listar();
	}

	@Override
	public Apartamento consultarApartamentoPorId(Integer id) {
		return this.apartamentoServiceImp.buscarPorId(id);
	}
	
	
//	CONTROLE DE HOTEL

	@Override
	public void inserirHotel(Hotel hotel) {
		this.hotelServiceImp.inserir(hotel);
	}

	@Override
	public void alterarHotel(Hotel hotel) {
		this.hotelServiceImp.atualizar(hotel);
	}

	@Override
	public void removerHotel(Hotel hotel) {
		this.hotelServiceImp.remover(hotel);
	}

	@Override
	public void removerHotelPorId(Integer id) {
		this.hotelServiceImp.removerPorId(id);
	}

	@Override
	public List<Hotel> consultarTodosHotel() {
		return this.hotelServiceImp.listar();
	}

	@Override
	public Hotel consultarHotelPorId(Integer id) {
		return this.hotelServiceImp.buscarPorId(id);
	}

	
//	CONTROLE DE ReservaHospedagem
	@Override
	public void inserirReservaHospedagem(ReservaHospedagem reservaHospedagem) {
		this.reservaHospedagemServiceImp.inserir(reservaHospedagem);
	}

	@Override
	public void alterarReservaHospedagem(ReservaHospedagem reservaHospedagem) {
		this.reservaHospedagemServiceImp.atualizar(reservaHospedagem);
	}

	@Override
	public void removerReservaHospedagem(ReservaHospedagem reservaHospedagem) {
		this.reservaHospedagemServiceImp.remover(reservaHospedagem);
	}

	@Override
	public void removerReservaHospedagemPorId(Integer id) {
		this.reservaHospedagemServiceImp.removerPorId(id);
	}

	@Override
	public List<ReservaHospedagem> consultarTodosReservaHospedagem() {
		return this.reservaHospedagemServiceImp.listar();
	}

	@Override
	public ReservaHospedagem consultarReservaHospedagemPorId(Integer id) {
		return this.reservaHospedagemServiceImp.buscarPorId(id);
	}

	
//	CONTROLE DE TipoApartamento
	@Override
	public void inserirTipoApartamento(TipoApartamento tipoApartamento) {
		this.tipoApartamentoServiceImp.inserir(tipoApartamento);
	}

	@Override
	public void alterarTipoApartamento(TipoApartamento tipoApartamento) {
		this.tipoApartamentoServiceImp.atualizar(tipoApartamento);
	}

	@Override
	public void removerTipoApartamento(TipoApartamento tipoApartamento) {
		this.tipoApartamentoServiceImp.remover(tipoApartamento);
	}

	@Override
	public void removerTipoApartamentoPorId(Integer id) {
		this.tipoApartamentoServiceImp.removerPorId(id);
	}

	@Override
	public List<TipoApartamento> consultarTodosTipoApartamento() {
		return this.tipoApartamentoServiceImp.listar();
	}

	@Override
	public TipoApartamento consultarTipoApartamentoPorId(Integer id) {
		return this.tipoApartamentoServiceImp.buscarPorId(id);
	}
}
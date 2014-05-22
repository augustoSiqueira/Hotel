package br.com.hotel.fachada;

import java.util.List;

import br.com.hotel.entity.Apartamento;
import br.com.hotel.entity.Cliente;
import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.ImagemApartamento;
import br.com.hotel.entity.ReservaHospedagem;
import br.com.hotel.entity.TipoApartamento;
import br.com.hotel.entity.Usuario;

public interface IFachada {

//	CRUD DE CLIENTE
	public void inserirCliente(Cliente cliente);
	public void alterarCliente(Cliente cliente);
	public void removerCliente(Cliente cliente);
	public void removerClientePorId(Integer id);
	public List<Cliente> consultarTodosClientes();
	public Cliente consultarClientePorId(Integer id);
	
	
//	CRUD DE USUARIO
	public void inserirUsuario(Usuario usuario);
	public void alterarUsuario(Usuario usuario);
	public void removerUsuario(Usuario usuario);
	public void removerUsuarioPorId(Integer id);
	public List<Usuario> consultarTodosUsuario();
	public Usuario consultarUsuarioPorId(Integer id);
	public boolean existeLogin(String login);
	public List<Usuario> consultarUsuariosClientes();
	public List<Usuario> consultarUsuariosAdministradores();
	
	
//	CRUD DE APARTAMENTO
	public void inserirApartamento(Apartamento apartamento);
	public void alterarApartamento(Apartamento apartamento);
	public void removerApartamento(Apartamento apartamento);
	public void removerApartamentoPorId(Integer id);
	public List<Apartamento> consultarTodosApartamento();
	public List<Apartamento> consultarTodosApartamentoAtivos();
	public Apartamento consultarApartamentoPorId(Integer id);
	public List<Apartamento> consultarApartamentosNaoVinculados();
	public List<Apartamento> consultarTodosApartamentoAtivosDoHotel(Integer id);
	
//	CRUD DE HOTEL
	public void inserirHotel(Hotel hotel);
	public void alterarHotel(Hotel hotel);
	public void removerHotel(Hotel hotel);
	public void removerHotelPorId(Integer id);
	public List<Hotel> consultarTodosHotel();
	public Hotel consultarHotelPorId(Integer id);
	public List<Hotel> consultarTodosHoteisAtivos();
	public boolean consultarHotelPorCnpj(String cnpj);
	public boolean consultarHotelPorCep(String cep);

	
//	CRUD DE ReservaHospedagem
	public void inserirReservaHospedagem(ReservaHospedagem reservaHospedagem);
	public void alterarReservaHospedagem(ReservaHospedagem reservaHospedagem);
	public void removerReservaHospedagem(ReservaHospedagem reservaHospedagem);
	public void removerReservaHospedagemPorId(Integer id);
	public List<ReservaHospedagem> consultarTodosReservaHospedagem();
	public ReservaHospedagem consultarReservaHospedagemPorId(Integer id);
	public List<ReservaHospedagem> consultarTodosReservaHospedagemCanceladas();
	public List<ReservaHospedagem> consultarTodosReservaHospedagemEmEspera();
	public List<ReservaHospedagem> consultarTodosReservaHospedagemComExito();
	
	
//	CRUD DE TipoApartamento
	public void inserirTipoApartamento(TipoApartamento tipoApartamento);
	public void alterarTipoApartamento(TipoApartamento tipoApartamento);
	public void removerTipoApartamento(TipoApartamento tipoApartamento);
	public void removerTipoApartamentoPorId(Integer id);
	public List<TipoApartamento> consultarTodosTipoApartamento();
	public TipoApartamento consultarTipoApartamentoPorId(Integer id);
	
	
	
//	CRUD DE ImagemApartamento
	public void inserirImagemApartamento(ImagemApartamento imagemApartamento);
	public void alterarImagemApartamento(ImagemApartamento imagemApartamento);
	public void removerImagemApartamento(ImagemApartamento imagemApartamento);
	public void removerImagemApartamentoPorId(Integer id);
	public List<ImagemApartamento> consultarTodosImagemApartamento();
	public ImagemApartamento consultarImagemApartamentoPorId(Integer id);
	
	
//	LOGAR
	public Usuario logar(String login, String senha);

}

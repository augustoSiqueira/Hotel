package br.com.hotel.fachada;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.hotel.entity.Apartamento;
import br.com.hotel.entity.Cliente;
import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.ImagemApartamento;
import br.com.hotel.entity.ReservaHospedagem;
import br.com.hotel.entity.TipoApartamento;
import br.com.hotel.entity.Usuario;
import br.com.hotel.serviceImp.ApartamentoServiceImp;
import br.com.hotel.serviceImp.ClienteServiceImp;
import br.com.hotel.serviceImp.HotelServiceImp;
import br.com.hotel.serviceImp.ImagemApartamentoServiceImp;
import br.com.hotel.serviceImp.ReservaHospedagemServiceImp;
import br.com.hotel.serviceImp.TipoApartamentoServiceImp;
import br.com.hotel.serviceImp.UsuarioServiceImp;
import br.com.hotel.util.Criptografar;

public class Fachada implements IFachada{

	private static Fachada Instancia;
	
	private UsuarioServiceImp usuarioServiceImp;
	private ClienteServiceImp clienteServiceImp;
	private ApartamentoServiceImp apartamentoServiceImp;
	private HotelServiceImp hotelServiceImp;
	private ReservaHospedagemServiceImp reservaHospedagemServiceImp;
	private TipoApartamentoServiceImp tipoApartamentoServiceImp;
	private ImagemApartamentoServiceImp imagemApartamentoServiceImp;
	
	private Fachada() {
		this.usuarioServiceImp = new UsuarioServiceImp();
		this.clienteServiceImp = new ClienteServiceImp();
		this.apartamentoServiceImp = new ApartamentoServiceImp();
		this.hotelServiceImp = new HotelServiceImp();
		this.reservaHospedagemServiceImp = new ReservaHospedagemServiceImp();
		this.tipoApartamentoServiceImp = new TipoApartamentoServiceImp();
		this.imagemApartamentoServiceImp = new ImagemApartamentoServiceImp();
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
	
	@Override
	public List<Usuario> consultarUsuariosClientes() {
		return this.usuarioServiceImp.consultarUsuariosClientes();
	}	
	
	@Override
	public List<Usuario> consultarUsuariosAdministradores() {
		return this.usuarioServiceImp.consultarUsuariosAdministradores();
	}	
	
	@Override
	public boolean existeLogin(String login) {
		String query = "FROM Usuario u WHERE u.ativo = true AND u.login=:login";
		HashMap<String,Object> parametros = new HashMap<String, Object>();
		parametros.put("login", login);
		
		List<Cliente> lista = this.clienteServiceImp.usarQuery(query, parametros);
		
		if (lista.size() == 0) {
			return false;
		}
		return true;
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
	
	//TODO: Este método retorna uma lista de apartamentos que tenha o código do hotel que o usuário selecionou
	// 		o que for vinculado ao hotel e que seja ativo, ele irá listar
	@Override
	public List<Apartamento> consultarTodosApartamentoAtivosDoHotel(Integer id) {
		return this.apartamentoServiceImp.consultarTodosApartamentosAtivosDoHotel(id);
	}

	@Override
	public List<Apartamento> consultarApartamentosNaoVinculados() {
		return this.apartamentoServiceImp.consultarTodosApartamentosNaoVinculados();
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
	
	@Override
	public List<Hotel> consultarTodosHoteisAtivos() {
		return this.hotelServiceImp.consultarHoteisAtivos();
	}

	@Override
	public boolean consultarHotelPorCnpj(String cnpj) {
		return this.hotelServiceImp.consultarHotelPorCnpj(cnpj);
	}
	
	@Override
	public boolean consultarHotelPorCep(String cep) {
		return this.hotelServiceImp.consultarHotelPorCep(cep);
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
	public List<Apartamento> consultarTodosApartamentoAtivos() {
		return this.apartamentoServiceImp.consultarTodosApartamentosAtivos();
	}

	@Override
	public TipoApartamento consultarTipoApartamentoPorId(Integer id) {
		return this.tipoApartamentoServiceImp.buscarPorId(id);
	}
	
	
	//LOGAR

	@Override
	public Usuario logar(String login, String senha) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("senha",Criptografar.gerarMd5(senha));
		parametros.put("login",login);	
		List<Usuario> usuarios = this.usuarioServiceImp.usarQuery("FROM Usuario u WHERE u.senha=:senha AND u.login=:login", parametros);

		if(usuarios != null && usuarios.size() > 0){
			return usuarios.get(0);
		}
		return null;
	}

	
//	CONTROLE DE ImagemApartamento
	@Override
	public void inserirImagemApartamento(ImagemApartamento imagemApartamento) {
		this.imagemApartamentoServiceImp.inserir(imagemApartamento);
	}

	@Override
	public void alterarImagemApartamento(ImagemApartamento imagemApartamento) {
		this.imagemApartamentoServiceImp.atualizar(imagemApartamento);
	}

	@Override
	public void removerImagemApartamento(ImagemApartamento imagemApartamento) {
		this.imagemApartamentoServiceImp.remover(imagemApartamento);
	}

	@Override
	public void removerImagemApartamentoPorId(Integer id) {
		this.imagemApartamentoServiceImp.removerPorId(id);
	}

	@Override
	public List<ImagemApartamento> consultarTodosImagemApartamento() {
		return this.imagemApartamentoServiceImp.listar();
	}

	@Override
	public ImagemApartamento consultarImagemApartamentoPorId(Integer id) {
		return this.imagemApartamentoServiceImp.buscarPorId(id);
	}

	@Override
	public List<ReservaHospedagem> consultarTodosReservaHospedagemCanceladas() {
		return this.reservaHospedagemServiceImp.consultarTodosReservaHospedagemCanceladas();
	}
	
	@Override
	public List<ReservaHospedagem> consultarTodosReservaHospedagemEmEspera() {
		return this.reservaHospedagemServiceImp.consultarTodosReservaHospedagemEmEspera();
	}
	
	@Override
	public List<ReservaHospedagem> consultarTodosReservaHospedagemComExito(){
		return this.reservaHospedagemServiceImp.consultarTodosReservaHospedagemComExito();
	}
}
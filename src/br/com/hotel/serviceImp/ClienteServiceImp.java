package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.ClienteDAO;
import br.com.hotel.daoImp.ClienteDAOImp;
import br.com.hotel.entity.Cliente;
import br.com.hotel.service.ClienteService;

public class ClienteServiceImp implements ClienteService, Serializable{

	private static final long serialVersionUID = 3970130452013349337L;
	
	private ClienteDAO clienteDAO;

	public ClienteServiceImp() {
		this.clienteDAO = new ClienteDAOImp();
	}

	@Override
	public void inserir(Cliente objeto) {
		this.clienteDAO.inserir(objeto);
	}

	@Override
	public void atualizar(Cliente objeto) {
		this.clienteDAO.alterar(objeto);
	}

	@Override
	public void remover(Cliente objeto) {
		objeto = this.clienteDAO.consultarPorId(objeto.getIdCliente());
		this.clienteDAO.remover(objeto);
	}

	@Override
	public void removerPorId(Integer id) {
		Cliente c = this.clienteDAO.consultarPorId(id);
		if(c != null){
			this.clienteDAO.removerPorId(id);
		}
	}

	@Override
	public Cliente buscarPorId(Integer id) {
		return this.clienteDAO.consultarPorId(id);
	}

	@Override
	public List<Cliente> listar() {
		return this.clienteDAO.consultarTodos();
	}

	@Override
	public List<Cliente> usarQuery(String query, Map<String, Object> parametros) {
		return this.clienteDAO.useQuery(query, parametros);
	}

	@Override
	public List<Cliente> usarQUery(String query,
			Map<String, Object> parametros, Integer indiceInicial,
			Integer indiceFinal) {
		return this.clienteDAO.useQuery(query, parametros,indiceInicial,indiceFinal);
	}
}
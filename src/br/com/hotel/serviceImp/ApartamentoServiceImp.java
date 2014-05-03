package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.ApartamentoDAO;
import br.com.hotel.entity.Apartamento;
import br.com.hotel.entity.Cliente;
import br.com.hotel.service.ApartamentoService;

public class ApartamentoServiceImp implements ApartamentoService, Serializable{

	private static final long serialVersionUID = -1767301075483866494L;

	private ApartamentoDAO apartamentoDAO;
	
	@Override
	public void inserir(Apartamento objeto) {
		this.apartamentoDAO.inserir(objeto);
	}

	@Override
	public void atualizar(Apartamento objeto) {
		this.apartamentoDAO.alterar(objeto);
	}

	@Override
	public void remover(Apartamento objeto) {
		objeto = this.apartamentoDAO.consultarPorId(objeto.getIdApartamento());
		this.apartamentoDAO.remover(objeto);
	}

	@Override
	public void removerPorId(Integer id) {
		Apartamento a = this.apartamentoDAO.consultarPorId(id);
		if(a != null){
			this.apartamentoDAO.removerPorId(id);
		}
	}

	@Override
	public Apartamento buscarPorId(Integer id) {
		return this.apartamentoDAO.consultarPorId(id);
	}

	@Override
	public List<Apartamento> listar() {
		return this.apartamentoDAO.consultarTodos();
	}

	@Override
	public List<Apartamento> usarQUery(String query,
			Map<String, Object> parametros) {
		return this.apartamentoDAO.useQuery(query, parametros);
	}
}
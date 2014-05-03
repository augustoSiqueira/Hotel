package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.TipoApartamentoDAO;
import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.TipoApartamento;
import br.com.hotel.service.TipoApartamentoService;

public class TipoApartamentoServiceImp implements TipoApartamentoService, Serializable{

	private static final long serialVersionUID = 2396306684519725666L;
	
	private TipoApartamentoDAO tipoApartamentoDAO;

	@Override
	public void inserir(TipoApartamento objeto) {
		this.tipoApartamentoDAO.inserir(objeto);
	}

	@Override
	public void atualizar(TipoApartamento objeto) {
		this.tipoApartamentoDAO.alterar(objeto);
	}

	@Override
	public void remover(TipoApartamento objeto) {
		objeto = this.tipoApartamentoDAO.consultarPorId(objeto.getIdTipoApartamento());
		this.tipoApartamentoDAO.remover(objeto);
	}

	@Override
	public TipoApartamento buscarPorId(Integer id) {
		return this.tipoApartamentoDAO.consultarPorId(id);
	}

	@Override
	public List<TipoApartamento> listar() {
		return this.tipoApartamentoDAO.consultarTodos();
	}

	@Override
	public List<TipoApartamento> usarQUery(String query,
			Map<String, Object> parametros) {
		return this.tipoApartamentoDAO.useQuery(query, parametros);
	}

	@Override
	public void removerPorId(Integer id) {
		TipoApartamento objeto = this.tipoApartamentoDAO.consultarPorId(id);
		if(objeto != null){
			this.tipoApartamentoDAO.removerPorId(id);
		}
	}
}
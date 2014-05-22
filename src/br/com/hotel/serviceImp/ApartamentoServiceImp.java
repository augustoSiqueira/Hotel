package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.ApartamentoDAO;
import br.com.hotel.daoImp.ApartamentoDAOImp;
import br.com.hotel.entity.Apartamento;
import br.com.hotel.service.ApartamentoService;

public class ApartamentoServiceImp implements ApartamentoService, Serializable{

	private static final long serialVersionUID = -1767301075483866494L;

	private ApartamentoDAO apartamentoDAO;
	
	public ApartamentoServiceImp (){
		this.apartamentoDAO = new ApartamentoDAOImp();
	}
	
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
	public List<Apartamento> usarQuery(String query,
			Map<String, Object> parametros) {
		return this.apartamentoDAO.useQuery(query, parametros);
	}

	@Override
	public List<Apartamento> usarQUery(String query,
			Map<String, Object> parametros, Integer indiceInicial,
			Integer indiceFinal) {
		return this.apartamentoDAO.useQuery(query, parametros,indiceInicial,indiceFinal);
	}

	public List<Apartamento> consultarTodosApartamentosAtivos() {
		String query = "FROM Apartamento a WHERE a.ativo=:ativo";
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("ativo", true);
		
		List<Apartamento> lista = new ArrayList<Apartamento>();

		lista = this.apartamentoDAO.useQuery(query, parametros);
		
		if (lista != null) {
			return lista;
		}
		return null;
	}

	public List<Apartamento> consultarTodosApartamentosNaoVinculados() {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("ativo", true);
		List<Apartamento> apt = this.apartamentoDAO.useQuery("FROM Apartamento a WHERE a.ativo=:ativo AND a.hotel=NULL", parametros);

		if(apt == null){
			return apt = new ArrayList<Apartamento>();
		}
		return apt;
	}

	public List<Apartamento> consultarTodosApartamentosAtivosDoHotel(Integer id) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("ativo", true);
		parametros.put("hotel", id);
		List<Apartamento> apt = this.apartamentoDAO.useQuery("FROM Apartamento a WHERE a.ativo=:ativo AND a.hotel.idHotel=:hotel", parametros);

		if(apt == null){
			return apt = new ArrayList<Apartamento>();
		}
		return apt;
	}
}
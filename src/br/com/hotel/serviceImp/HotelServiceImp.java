package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.HotelDAO;
import br.com.hotel.daoImp.HotelDAOImp;
import br.com.hotel.entity.Hotel;
import br.com.hotel.service.HotelService;

public class HotelServiceImp implements HotelService, Serializable{

	private static final long serialVersionUID = -2048417877348970732L;

	private HotelDAO hotelDAO;
	
	public HotelServiceImp (){
		this.hotelDAO = new HotelDAOImp();
	}
	
	@Override
	public void inserir(Hotel objeto) {
		this.hotelDAO.inserir(objeto);
	}

	@Override
	public void atualizar(Hotel objeto) {
		this.hotelDAO.alterar(objeto);
	}

	@Override
	public void remover(Hotel objeto) {
		objeto = this.hotelDAO.consultarPorId(objeto.getIdHotel());
		this.hotelDAO.remover(objeto);
	}

	@Override
	public Hotel buscarPorId(Integer id) {
		return this.hotelDAO.consultarPorId(id);
	}

	@Override
	public List<Hotel> listar() {
		return this.hotelDAO.consultarTodos();
	}

	@Override
	public List<Hotel> usarQuery(String query, Map<String, Object> parametros) {
		return this.hotelDAO.useQuery(query, parametros);
	}

	@Override
	public void removerPorId(Integer id) {
		Hotel objeto = this.hotelDAO.consultarPorId(id);
		if(objeto != null){
			this.hotelDAO.removerPorId(id);
		}
	}

	@Override
	public List<Hotel> usarQUery(String query, Map<String, Object> parametros,
			Integer indiceInicial, Integer indiceFinal) {
		return this.hotelDAO.useQuery(query, parametros,indiceInicial,indiceFinal);
	}

	public boolean consultarHotelPorCep(String cep) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("ativo", true);
		parametros.put("cep", cep);
		
		List<Hotel> hotel = this.hotelDAO.useQuery("FROM Hotel h WHERE h.ativo=:ativo AND h.cep=:cep", parametros);

		if(hotel == null || hotel.size() == 0){
			return false;
		}
		return true;
	}

	public boolean consultarHotelPorCnpj(String cnpj) {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("ativo", true);
		parametros.put("cnpj", cnpj);
		
		List<Hotel> hotel = this.hotelDAO.useQuery("FROM Hotel h WHERE h.ativo=:ativo AND h.cnpj=:cnpj", parametros);

		if(hotel == null || hotel.size() == 0){
			return false;
		}
		return true;
	}

	public List<Hotel> consultarHoteisAtivos() {
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("ativo", true);
		List<Hotel> hotel = this.hotelDAO.useQuery("FROM Hotel h WHERE h.ativo=:ativo", parametros);

		if(hotel == null){
			return hotel = new ArrayList<Hotel>();
		}
		return hotel;
	}
}
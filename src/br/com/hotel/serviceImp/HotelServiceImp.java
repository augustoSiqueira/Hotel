package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.HotelDAO;
import br.com.hotel.entity.Hotel;
import br.com.hotel.service.HotelService;

public class HotelServiceImp implements HotelService, Serializable{

	private static final long serialVersionUID = -2048417877348970732L;

	private HotelDAO hotelDAO;
	
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
	public List<Hotel> usarQUery(String query, Map<String, Object> parametros) {
		return this.hotelDAO.useQuery(query, parametros);
	}

	@Override
	public void removerPorId(Integer id) {
		Hotel objeto = this.hotelDAO.consultarPorId(id);
		if(objeto != null){
			this.hotelDAO.removerPorId(id);
		}
	}
}
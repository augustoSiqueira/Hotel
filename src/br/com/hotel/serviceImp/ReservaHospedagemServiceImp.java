package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.ReservaHospedagemDAO;
import br.com.hotel.entity.Hotel;
import br.com.hotel.entity.ReservaHospedagem;
import br.com.hotel.service.ReservaHospedagemService;

public class ReservaHospedagemServiceImp implements ReservaHospedagemService, Serializable{

	private static final long serialVersionUID = -325507491222683181L;

	private ReservaHospedagemDAO reservaHospedagemDAO;
	
	@Override
	public void inserir(ReservaHospedagem objeto) {
		this.reservaHospedagemDAO.inserir(objeto);
	}

	@Override
	public void atualizar(ReservaHospedagem objeto) {
		this.reservaHospedagemDAO.alterar(objeto);
	}

	@Override
	public void remover(ReservaHospedagem objeto) {
		objeto = this.reservaHospedagemDAO.consultarPorId(objeto.getIdReserva());
		this.reservaHospedagemDAO.remover(objeto);
	}

	@Override
	public ReservaHospedagem buscarPorId(Integer id) {
		return this.reservaHospedagemDAO.consultarPorId(id);
	}

	@Override
	public List<ReservaHospedagem> listar() {
		return this.reservaHospedagemDAO.consultarTodos();
	}

	@Override
	public List<ReservaHospedagem> usarQUery(String query,
			Map<String, Object> parametros) {
		return this.reservaHospedagemDAO.useQuery(query, parametros);
	}

	@Override
	public void removerPorId(Integer id) {
		ReservaHospedagem objeto = this.reservaHospedagemDAO.consultarPorId(id);
		if(objeto != null){
			this.reservaHospedagemDAO.removerPorId(id);
		}
	}
}
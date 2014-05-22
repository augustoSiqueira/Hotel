package br.com.hotel.service;

import java.util.List;

import br.com.hotel.entity.ReservaHospedagem;

public interface ReservaHospedagemService extends GenericoService<ReservaHospedagem>{

	public List<ReservaHospedagem> consultarTodosReservaHospedagemEmEspera();
	public List<ReservaHospedagem> consultarTodosReservaHospedagemCanceladas();
	public List<ReservaHospedagem> consultarTodosReservaHospedagemComExito();
}
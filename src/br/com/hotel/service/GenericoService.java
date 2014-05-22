package br.com.hotel.service;

import java.util.List;
import java.util.Map;

public interface GenericoService<T> {

	public void inserir(T objeto);

	public void atualizar(T objeto);

	public void remover(T objeto);
	
	public void removerPorId(Integer id);

	public T buscarPorId(Integer id);

	public List<T> listar();

	public List<T> usarQuery(String query, Map<String, Object> parametros);
	
	public List<T> usarQUery(String query, Map<String, Object> parametros,
			Integer indiceInicial, Integer indiceFinal);
}
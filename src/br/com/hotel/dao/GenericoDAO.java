package br.com.hotel.dao;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface GenericoDAO<Entidade> {
	  
    public void inserir(Entidade entidade);
      
    public void alterar(Entidade entidade);
      
    public void remover(Entidade entidade);
    
    public void removerPorId(Integer id);
      
    public Entidade consultarPorId(Integer id);
      
    public List<Entidade> consultarTodos();
    
	public List<Entidade> useQuery(String query,Map<String,Object> parametros);
	
	public List<Type> findParameter(String query);

	public List<Entidade> useQuery(String query, Map<String, Object> parametros,
			Integer indiceInicial, Integer indiceFinal);
}
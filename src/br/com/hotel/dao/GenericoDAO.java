package br.com.hotel.dao;

import java.util.List;

public interface GenericoDAO<Entidade> {
	  
    public void inserir(Entidade entidade);
      
    public void alterar(Entidade entidade);
      
    public void remover(Entidade entidade);
      
    public Entidade consultarPorId(Integer id);
      
    public List<Entidade> consultarTodos();
} 
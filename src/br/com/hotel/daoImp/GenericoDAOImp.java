package br.com.hotel.daoImp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.hotel.dao.GenericoDAO;
import br.com.hotel.util.JPAUtil;
  
/**
 * PSC
 * 
 * @param <Entidade, PK>
 */
public abstract class GenericoDAOImp<Entidade> implements GenericoDAO<Entidade>{
  
    protected EntityManager entityManager;
    protected Class<Entidade> classePersistente;
  
    @SuppressWarnings("unchecked")
    public GenericoDAOImp(){
        entityManager = JPAUtil.getEntityManager();
          
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();  
        classePersistente = (Class<Entidade>) parameterizedType.getActualTypeArguments()[0];  
    }
      
    /**
     * Executa o merge do objeto que se encontra em memória.
     * 
     * @param objeto
     *            a ser realizado o merge
     * @return objeto que foi executado o merge
     */
    public final void alterar(Entidade objeto) {
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
              
            objeto = getEntityManager().merge(objeto);
              
            tx.commit();
            
              
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
                
            }
        }
    }
  
    /**
     * Salva o objeto atual na base de dados.
     * 
     * @param objeto a ser salvo
     */
    public final void inserir(Entidade objeto) {
        EntityTransaction tx = getEntityManager().getTransaction();     
        try {
            tx.begin();
            getEntityManager().persist(objeto);
            tx.commit();
            
            System.out.println(classePersistente.getSimpleName() + " salvo com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
                
            }
        }
    }
  
    /**
     * Salva o objeto atual na base de dados.
     * 
     * @param objeto
     *            a ser salvo
     */
    public final void inserirColecao(Collection<Entidade> colecao) {
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
  
            for (Entidade entidade : colecao) {
                getEntityManager().persist(entidade);   
            }
              
            tx.commit();
            
            System.out.println(classePersistente.getSimpleName() + " salvos com sucesso: " + colecao.size());
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
                
            }
        }
    }
  
    /**
     * Remove o objeto da base de dados.
     * 
     * @param objeto
     *            a ser removido
     */
    public final void remover(Entidade objeto) {
        EntityTransaction tx = getEntityManager().getTransaction();
        try {
            tx.begin();
      
            refresh(objeto);
            getEntityManager().remove(objeto);
              
            tx.commit();
            
            System.out.println(classePersistente.getSimpleName() + " removido com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            if (tx != null && tx.isActive()){
                tx.rollback();
                
            }
        }
    }
  
    /**
     * Remove o objeto da base de dados pela ID.
     * 
     * @param objeto
     *            a ser removido
     */
    
	@Override
	public void removerPorId(Integer id) {
		Entidade objeto = this.consultarPorId(id);
		if (objeto != null) {
			objeto = getEntityManager().merge(objeto);
			entityManager.remove(objeto);
		}
		
	}
	
	public List<Type> findParameter(String query) {
		if (entityManager == null) {
			return null;
		}
		
		Query  q = getEntityManager().createQuery(query);
		List<Type> resultado = q.getResultList();
		
		return resultado;
	}


    /**
     * Busca o objeto uma vez passado sua chave como parâmetro.
     * 
     * @param chave
     *            identificador
     * @return Objeto do tipo T
     */
    public final Entidade consultarPorId(Integer chave) {
        Entidade instance = null;
        try {
            instance = (Entidade) getEntityManager().find(classePersistente, chave);
            
        } catch (RuntimeException re) {
            re.printStackTrace();
        }
        return instance;
    }
  
    public List<Entidade> consultarTodos() {
        try {
            String sql = "from " + classePersistente.getSimpleName();
            TypedQuery<Entidade> query = getEntityManager().createQuery(sql, classePersistente);
            List<Entidade> resultado = query.getResultList();
            
            return resultado;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Implementa uma query JPQL para fazer pesquisas
     * */
    
	@Override
	public List<Entidade> useQuery(String query, Map<String, Object> parametros) {
		Query q = getEntityManager().createQuery(query);
		
		if(parametros != null){
			Iterator<String> iterador = parametros.keySet().iterator();
			while(iterador.hasNext()){
				String chave = iterador.next();
				q.setParameter(chave,parametros.get(chave));
			}
		}
		List<Entidade> r = q.getResultList();
		
		return r;		
	}
	
	@Override
	public List<Entidade> useQuery(String query, Map<String, Object> parametros,
			Integer indiceInicial, Integer indiceFinal) {
		Query q = getEntityManager().createQuery(query);
		
		if(parametros != null){
			Iterator<String> iterador = parametros.keySet().iterator();
			while (iterador.hasNext()) {
				String chave = iterador.next();
				q.setParameter(chave, parametros.get(chave));
			}
		}
		q.setFirstResult(indiceInicial);
		q.setMaxResults(indiceFinal);
		List<Entidade> r = q.getResultList();
		
		return r;		
	}

  
    /**
     * Atualiza o objeto que se encontra em memória.
     * 
     * @param object
     *            objeto a ser atualizado
     */
    public final void refresh(Entidade object) {
        getEntityManager().refresh(object);
    }
      
    /**
     * Utilizado para se injetar o Entity manager no DAO.
     * 
     * @param entityManager
     *            entity manager
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
  
    public EntityManager getEntityManager() {
//    	entityManager = JPAUtil.getEntityManager();
        return entityManager;
    }        
} 
package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.UsuarioDAO;
import br.com.hotel.daoImp.UsuarioDAOImp;
import br.com.hotel.entity.TipoUsuario;
import br.com.hotel.entity.Usuario;
import br.com.hotel.service.UsuarioService;

public class UsuarioServiceImp implements UsuarioService,Serializable {

	private static final long serialVersionUID = -5930272730280607588L;

	private UsuarioDAO usuarioDAO;
	
	public UsuarioServiceImp (){
		this.usuarioDAO = new UsuarioDAOImp();
	}
	
	@Override
	public void inserir(Usuario objeto) {
		this.usuarioDAO.inserir(objeto);
	}

	@Override
	public void atualizar(Usuario objeto) {
		this.usuarioDAO.alterar(objeto);
	}

	@Override
	public void remover(Usuario objeto) {
		objeto = this.usuarioDAO.consultarPorId(objeto.getIdUsuario());
		this.usuarioDAO.remover(objeto);
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		return this.usuarioDAO.consultarPorId(id);
	}

	@Override
	public List<Usuario> listar() {
		return this.usuarioDAO.consultarTodos();
	}

	@Override
	public List<Usuario> usarQuery(String query, Map<String, Object> parametros) {
		System.out.println("entrou no usarQuery");
		List<Usuario> us = new ArrayList<Usuario>();
		us = this.usuarioDAO.useQuery(query, parametros);
		
		if (us != null) {
			System.out.println("entrou no if");
			return us;
		}
		System.out.println("null");
		return null;
	}
	
	@Override
	public Usuario buscarPorEmail(String email) {
		String query = "FROM Usuario u WHERE u.email=:email";
		Map<String, Object> parametros = new HashMap<String,Object>();
		parametros.put("email",email);
		List<Usuario> usuarios = this.usuarioDAO.useQuery(query,parametros,0,1);
		
		if(usuarios != null && usuarios.size() > 0){
			return usuarios.get(0);
		}
		return null;
	}

	@Override
	public void removerPorId(Integer id) {
		Usuario objeto = this.usuarioDAO.consultarPorId(id);
		if(objeto != null){
			this.usuarioDAO.removerPorId(id);
		}
	}

	@Override
	public List<Usuario> usarQUery(String query,
			Map<String, Object> parametros, Integer indiceInicial,
			Integer indiceFinal) {
		return this.usuarioDAO.useQuery(query, parametros,indiceInicial,indiceFinal);
	}
	
	
	@Override
	public List<Usuario> consultarUsuariosClientes() {
		String query = "FROM Usuario u WHERE u.ativo=true AND u.tipoUsuario=:tipoUsuario";
		Map<String, Object> parametros = new HashMap<String,Object>();
		parametros.put("tipoUsuario", TipoUsuario.CLIENTE);
		
		List<Usuario> lista = new ArrayList<Usuario>();

		lista = this.usuarioDAO.useQuery(query, parametros);
		
		if (lista != null) {
			return lista;
		}
		return null;
	}	
	
	@Override
	public List<Usuario> consultarUsuariosAdministradores() {
		String query = "FROM Usuario u WHERE u.ativo=true AND u.tipoUsuario=:tipoUsuario";
		Map<String, Object> parametros = new HashMap<String,Object>();
		parametros.put("tipoUsuario", TipoUsuario.ADMIN);
		
		List<Usuario> lista = new ArrayList<Usuario>();

		lista = this.usuarioDAO.useQuery(query, parametros);
		
		if (lista != null) {
			return lista;
		}
		return null;
	}	
}
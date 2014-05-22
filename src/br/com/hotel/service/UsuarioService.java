package br.com.hotel.service;

import java.util.List;

import br.com.hotel.entity.Usuario;

public interface UsuarioService extends GenericoService<Usuario>{

	public Usuario buscarPorEmail(String email);
	public List<Usuario> consultarUsuariosAdministradores();
	public List<Usuario> consultarUsuariosClientes();
}
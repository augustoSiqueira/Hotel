package br.com.hotel.service;

import br.com.hotel.entity.Usuario;

public interface UsuarioService extends GenericoService<Usuario>{

	public Usuario buscarPorEmail(String email);
}
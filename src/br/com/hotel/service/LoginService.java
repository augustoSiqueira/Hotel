package br.com.hotel.service;

import br.com.hotel.entity.Usuario;

public interface LoginService {
	public Usuario logar(String login, String senha);
}

package br.com.hotel.daoImp;

import java.io.Serializable;

import br.com.hotel.dao.UsuarioDAO;
import br.com.hotel.entity.Usuario;

public class UsuarioDAOImp extends GenericoDAOImp<Usuario> implements UsuarioDAO, Serializable{
	private static final long serialVersionUID = 7333498335708097305L;
}
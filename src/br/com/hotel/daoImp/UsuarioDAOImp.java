package br.com.hotel.daoImp;

import java.io.Serializable;

import br.com.hotel.dao.GenericoDAO;
import br.com.hotel.entity.Usuario;

public class UsuarioDAOImp extends GenericoDAOImp<Usuario> implements GenericoDAO<Usuario>, Serializable{
	private static final long serialVersionUID = 7333498335708097305L;
}
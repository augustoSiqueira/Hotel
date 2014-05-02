package br.com.hotel.daoImp;

import java.io.Serializable;

import br.com.hotel.dao.GenericoDAO;
import br.com.hotel.entity.Cliente;

public class ClienteDAOImp extends GenericoDAOImp<Cliente> implements GenericoDAO<Cliente>, Serializable{
	private static final long serialVersionUID = -3340303466438263926L;
}
package br.com.hotel.daoImp;

import java.io.Serializable;

import br.com.hotel.dao.ClienteDAO;
import br.com.hotel.entity.Cliente;

public class ClienteDAOImp extends GenericoDAOImp<Cliente> implements ClienteDAO, Serializable{
	private static final long serialVersionUID = -3340303466438263926L;
}
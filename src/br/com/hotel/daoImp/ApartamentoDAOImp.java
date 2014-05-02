package br.com.hotel.daoImp;

import java.io.Serializable;

import br.com.hotel.dao.GenericoDAO;
import br.com.hotel.entity.Apartamento;

public class ApartamentoDAOImp extends GenericoDAOImp<Apartamento> implements GenericoDAO<Apartamento>, Serializable{
	private static final long serialVersionUID = -5240826442510117557L;
}
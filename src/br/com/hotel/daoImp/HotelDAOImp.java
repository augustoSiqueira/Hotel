package br.com.hotel.daoImp;

import java.io.Serializable;

import br.com.hotel.dao.GenericoDAO;
import br.com.hotel.entity.Hotel;

public class HotelDAOImp extends GenericoDAOImp<Hotel> implements GenericoDAO<Hotel>, Serializable{
	private static final long serialVersionUID = -532846016703919509L;
}
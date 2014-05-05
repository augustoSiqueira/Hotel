package br.com.hotel.daoImp;

import java.io.Serializable;

import br.com.hotel.dao.HotelDAO;
import br.com.hotel.entity.Hotel;

public class HotelDAOImp extends GenericoDAOImp<Hotel> implements HotelDAO, Serializable{
	private static final long serialVersionUID = -532846016703919509L;
}
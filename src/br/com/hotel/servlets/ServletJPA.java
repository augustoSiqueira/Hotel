package br.com.hotel.servlets;

import javax.servlet.http.HttpServlet;

import br.com.hotel.util.JPAUtil;

public class ServletJPA extends HttpServlet {

	private static final long serialVersionUID = 4656692081121457210L;

	public ServletJPA () throws ClassNotFoundException{
		JPAUtil.gerarTabelas();
	}
}

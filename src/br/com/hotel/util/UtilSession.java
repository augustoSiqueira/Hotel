package br.com.hotel.util;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class UtilSession {

	public static HttpSession getHttpSession() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession s = (HttpSession) externalContext.getSession(true);				
		return s;
	}

	public static void setHttpSessionObject(String nomeSessao, Object o) {
		getHttpSession().setAttribute(nomeSessao, o);		
	}

	public static Object getHttpSessionObject(String nomeSessao) {
		return getHttpSession().getAttribute(nomeSessao);
	}
}

package br.com.hotel.util;


import javax.faces.context.FacesContext;

import br.com.hotel.entity.Usuario;

public class RestricaoDePaginaAdm {
	/**
	 * se não tiver usuario logado ele redireciona para index2.xhtml
	 */
	public static void redirecionar(){
		try{
			Usuario usuario = new Usuario();
			usuario = (Usuario) UtilSession.getHttpSessionObject("usuarioLogado");
			if(usuario == null){
				FacesContext.getCurrentInstance().getExternalContext().redirect("loginAdm.xhtml");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		/**
		 * redireciona para página que estiver no parâmetro
		 * @param pagina
		 */
	public static void redirecionarPara(String pagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(pagina);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
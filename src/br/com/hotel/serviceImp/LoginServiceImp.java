package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.UsuarioDAO;
import br.com.hotel.entity.Usuario;
import br.com.hotel.service.LoginService;
import br.com.hotel.util.Criptografar;

public class LoginServiceImp implements LoginService, Serializable {

	private static final long serialVersionUID = 4697829206903264141L;

	private UsuarioDAO usuarioDAO;
	
	@Override 
	public Usuario logar(String login,String senha) {
		
		Map<String,Object> parametros = new HashMap<String,Object>();
		
		//ele criptografa a senha, e pega exatamente o que está no banco e loga
		parametros.put("senha",Criptografar.gerarMd5(senha));
		parametros.put("login",login);	
		List<Usuario> usuarios = this.usuarioDAO.useQuery("FROM Usuario u WHERE u.senha=:senha AND u.login=:login", parametros,0,1);

		if(usuarios != null && usuarios.size() > 0){
			return usuarios.get(0);
		}
		return null;
	}

}

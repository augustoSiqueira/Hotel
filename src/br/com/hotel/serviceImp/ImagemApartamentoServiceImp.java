package br.com.hotel.serviceImp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.hotel.dao.ImagemApartamentoDAO;
import br.com.hotel.daoImp.ImagemApartamentoDAOImp;
import br.com.hotel.entity.ImagemApartamento;
import br.com.hotel.service.ImagemApartamentoService;

public class ImagemApartamentoServiceImp implements ImagemApartamentoService, Serializable{

	private static final long serialVersionUID = -2722290348992435182L;

	private ImagemApartamentoDAO imagemApartamentoDAO;
	
	public ImagemApartamentoServiceImp (){
		this.imagemApartamentoDAO = new ImagemApartamentoDAOImp();
	}	
	
	@Override
	public void inserir(ImagemApartamento objeto) {
		this.imagemApartamentoDAO.inserir(objeto);
	}

	@Override
	public void atualizar(ImagemApartamento objeto) {
		this.imagemApartamentoDAO.alterar(objeto);
	}

	@Override
	public void remover(ImagemApartamento objeto) {
		objeto = this.imagemApartamentoDAO.consultarPorId(objeto.getIdImagem());
		this.imagemApartamentoDAO.remover(objeto);
	}

	@Override
	public void removerPorId(Integer id) {
		ImagemApartamento a = this.imagemApartamentoDAO.consultarPorId(id);
		if(a != null){
			this.imagemApartamentoDAO.removerPorId(id);
		}
	}

	@Override
	public ImagemApartamento buscarPorId(Integer id) {
		return this.imagemApartamentoDAO.consultarPorId(id);
	}

	@Override
	public List<ImagemApartamento> listar() {
		return this.imagemApartamentoDAO.consultarTodos();
	}

	@Override
	public List<ImagemApartamento> usarQuery(String query,
			Map<String, Object> parametros) {
		return this.imagemApartamentoDAO.useQuery(query, parametros);
	}

	@Override
	public List<ImagemApartamento> usarQUery(String query,
			Map<String, Object> parametros, Integer indiceInicial,
			Integer indiceFinal) {
		return this.imagemApartamentoDAO.useQuery(query, parametros,indiceInicial,indiceFinal);
	}
}
package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Video;

public class DAOvideo extends DAO<Video> {

	@Override
	public Video read(Object chave) {
		String link = (String) chave; // casting para o tipo da chave

		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("link").constrain(link);
		List<Video> resultados = q.execute();
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}

	public List<Video> consultarVideosPorAssunto(String palavra) {
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("assuntos").descend("palavra").constrain(palavra);
		List<Video> resultado = q.execute();
		if (resultado.size() == 0) {
			return null;
		} else {
			return resultado;
		}
	}

	public List<Video> consultarVideosPorUsuario(String email) {
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("visualizacoes").descend("usuario").descend("email").constrain(email);
		List<Video> resultados = q.execute();
		if (resultados.size() == 0) {
			return null;
		} else {
			return resultados;
		}
	}

}

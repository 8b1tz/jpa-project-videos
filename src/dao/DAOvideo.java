package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import modelo.Usuario;
import modelo.Video;

public class DAOvideo extends DAO<Video> {

	@Override
	public Video read(Object chave) {

		try {
			String link = (String) chave; // casting para o tipo da chave
			TypedQuery<Video> q = manager.createQuery("SELECT v FROM Video v WHERE v.link =:v", Video.class);
			q.setParameter("v", link);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;

		}
	}

	public List<Video> consultarVideosPorAssunto(String palavra) {
		try {
			TypedQuery<Video> q = manager.createQuery("select v from Video v join Assunto a where a.palavra=:p",
					Video.class);
			q.setParameter("p", palavra);
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

//	public List<Video> consultarVideosPorUsuario(String email) {
//		Query q = manager.query();
//		q.constrain(Video.class);
//		q.descend("visualizacoes").descend("usuario").descend("email").constrain(email);
//		List<Video> resultados = q.execute();
//		if (resultados.size() == 0) {
//			return null;
//		} else {
//			return resultados;
//		}
//	}

}

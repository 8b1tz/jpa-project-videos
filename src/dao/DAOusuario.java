package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


import modelo.Assunto;
import modelo.Usuario;

public class DAOusuario extends DAO<Usuario> {

	@Override
	public Usuario read(Object chave) {
		try {
			String email = (String) chave; // casting para o tipo da chave
			TypedQuery<Usuario> q = manager.createQuery("SELECT u FROM Usuario u WHERE u.email =:e", Usuario.class);
			q.setParameter("e", email);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;

		}
	}

//	public List<Usuario> consultarUsuariosPorVideo(String link) {
//		Query q = manager.query();
//		q.constrain(Usuario.class);
//		q.descend("visualizacoes").descend("video").descend("link").constrain(link);
//		List<Usuario> resultados = q.execute();
//		if (resultados.size() == 0) {
//			return null;
//		} else {
//			return resultados;
//		}
//	}

}

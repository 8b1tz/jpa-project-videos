package dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Visualizacao;

public class DAOVisualizacao extends DAO<Visualizacao> {

	@Override
	public Visualizacao read(Object chave) {

		try {
			Integer id = (Integer) chave; // casting para o tipo da chave
			TypedQuery<Visualizacao> q = manager.createQuery("SELECT v FROM Visualizacao v WHERE v.id =:i",
					Visualizacao.class);
			q.setParameter("i", id);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;

		}
	}

}

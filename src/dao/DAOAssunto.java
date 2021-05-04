package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Assunto;

public class DAOAssunto extends DAO<Assunto> {

	@Override
	public Assunto read(Object chave) {
		try {
			String palavra = (String) chave;
			TypedQuery<Assunto> q = manager.createQuery("select a from Assunto a where a.palavra=:p", Assunto.class);
			q.setParameter("p", palavra);
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	// //pode-se sobrescrever o metodo readAll da classe DAO para ordenar o
	// resultado
	public List<Assunto> readAll() {
		TypedQuery<Assunto> q = manager.createQuery("select a from Assunto a order by a.id", Assunto.class);
		return q.getResultList();
	}

}

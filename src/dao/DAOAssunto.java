package dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Assunto;

public class DAOAssunto extends DAO<Assunto> {

	@Override
	public Assunto read(Object chave) {
		try {
		String palavra = (String) chave; 
		TypedQuery<Assunto> q = manager.createQuery("SELECT a FROM Assunto a WHERE p.palavra =:p", Assunto.class);
		q.setParameter("p", palavra);
		return q.getSingleResult();
		}
		catch(NoResultException e){
			return null;
		}
	}

}

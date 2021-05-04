package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


import modelo.Assunto;
import modelo.Usuario;

public class DAOusuario extends DAO<Usuario> {

	@Override
	public Usuario read (Object chave){
		try{
			String email = (String) chave;
			TypedQuery<Usuario> q = manager.createQuery("select u from Usuario u where u.email=:e", Usuario.class);
			q.setParameter("u", email);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	//  //pode-se sobrescrever o metodo readAll da classe DAO para ordenar o resultado 
	public List<Usuario> readAll(){
		TypedQuery<Usuario> q = manager.createQuery("select u from Usuario u order by u.id", Usuario.class);
		return  q.getResultList();
	}

	public List<Usuario> consultarUsuariosPorVideo(String link) {
		try {
			TypedQuery<Usuario> q = manager.createQuery("SELECT u FROM Usuario JOIN Video v WHERE v.link =:l", Usuario.class);
			q.setParameter("l", link);
			return q.getResultList();
		} catch (NoResultException e) {
			return null;

		}
	}

}

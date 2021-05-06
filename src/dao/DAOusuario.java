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
            String email = (String) chave;
            TypedQuery<Usuario> q = manager.createQuery("select u from Usuario u where u.email=:e", Usuario.class);
            q.setParameter("e", email);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Usuario> consultarUsuariosPorVideo(String link) {
        try {
            TypedQuery<Usuario> q = manager.createQuery(
                    "SELECT u FROM Usuario u JOIN u.visualizacoes vi join vi.video v where v.link =: l", Usuario.class);
            q.setParameter("l", link);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;

        }
    }

}

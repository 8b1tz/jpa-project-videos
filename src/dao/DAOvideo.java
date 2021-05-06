package dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Video;

public class DAOvideo extends DAO<Video> {
    @Override
    public Video read(Object chave) {
        try {
            String link = (String) chave;
            TypedQuery<Video> q = manager.createQuery("select v from Video v where v.link =: l", Video.class);
            q.setParameter("l", link);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Video> consultarVideosPorAssunto(String palavra) {
        try {
            TypedQuery<Video> q = manager.createQuery("select v from Video v join v.assuntos a where a.palavra =:p",
                    Video.class);
            q.setParameter("p", palavra);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Video> consultarVideosPorUsuario(String email) {
        try {
            TypedQuery<Video> q = manager.createQuery(
                    "select v from Video v join v.visualizacoes vi join vi.usuario u where u.email=:e", Video.class);
            q.setParameter("e", email);
            return q.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

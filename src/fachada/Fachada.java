package fachada;

import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import dao.DAOAssunto;
import dao.DAOVisualizacao;
import dao.DAOusuario;
import dao.DAOvideo;
import modelo.Assunto;
import modelo.Usuario;
import modelo.Video;
import modelo.Visualizacao;

public class Fachada {
	private static DAOvideo daovideo = new DAOvideo();
	private static DAOAssunto daoassunto = new DAOAssunto();
	private static DAOusuario daousuario = new DAOusuario();
	private static DAOVisualizacao daovisualizacao = new DAOVisualizacao();
	private static int id ;

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

// ------------------------- CADASTROS --------------------------------------------
	public static Assunto cadastrarAssunto(String palavra) throws Exception {
		DAO.begin();
		Assunto a = daoassunto.read(palavra);
		if (a != null) {
			DAO.rollback();
			throw new Exception("Assunto já cadastrado!");
		}
		a = new Assunto(palavra);
		daoassunto.create(a);
		DAO.commit();
		return a;

	}

	public static Usuario cadastrarUsuario(String email) throws Exception {
		DAO.begin();
		Usuario u = daousuario.read(email);
		if (u != null) {
			DAO.rollback();
			throw new Exception("Usuario já cadastrado!");
		}
		u = new Usuario(email);
		daousuario.create(u);
		DAO.commit();
		return u;

	}

	public static Video cadastrarVideo(String link, String nome) throws Exception {
		DAO.begin();
		if (nome.isEmpty()) {
			DAO.rollback();
			throw new Exception("O video precisa de um nome!");
		}
		if (link.isEmpty()) {
			DAO.rollback();
			throw new Exception("Precisa-se de um link ! ");
		}
		Video v = daovideo.read(link);
		if (v != null) {
			DAO.rollback();
			throw new Exception("Link ja cadastrado: " + link);
		}
		v = new Video(link, nome);
		daovideo.create(v);
		DAO.commit();
		return v;
	}

	public static Video cadastrarVideo(String link, String nome, String palavra) throws Exception {
		DAO.begin();
		if (link.isEmpty()) {
			DAO.rollback();
			throw new Exception("Precisa-se de um link ! ");
		}
		if (nome.isEmpty()) {
			DAO.rollback();
			throw new Exception("O video precisa de um nome!");
		}
		Video v = daovideo.read(link);
		if (v != null) {
			DAO.rollback();
			throw new Exception("Link ja cadastrado: " + link);
		}
		Assunto a = daoassunto.read(palavra);
		v = new Video(link, nome);
		if (a != null) {
			v.adicionar(a);
			a.adicionar(v);
			daoassunto.update(a);
			daovideo.update(v);
			daovideo.create(v);
			DAO.commit();
			return v;
		} else {
			Assunto asu = cadastrarAssunto(palavra);
			v.adicionar(asu);
			asu.adicionar(v);
			daoassunto.create(asu);
			daoassunto.update(asu);
			daovideo.update(v);
			daovideo.create(v);
			DAO.commit();
			return v;
		}
	}

	public static Visualizacao registrarVisualizacao(String link, int nota) throws Exception {
		DAO.begin();
		if (link.isEmpty()) {
			DAO.rollback();
			throw new Exception("Precisa-se de um link ! ");
		}
		Video v = daovideo.read(link);
		if (v == null) {
			DAO.rollback();
			throw new Exception("Video não encontrado");
		}
		id = localizarMaiorIdVisualizacao() + 1;
		Visualizacao visu = new Visualizacao(id, nota, null, v);
		v.adicionar(visu);
		v.fazerMedia();
		daovideo.update(v);
		
		daovisualizacao.create(visu);
		DAO.commit();
		return visu;
	}

	public static Visualizacao registrarVisualizacao(String link, String email, int nota) throws Exception {
		DAO.begin();
		if (link.isEmpty()) {
			DAO.rollback();
			throw new Exception("Precisa-se de um link ! ");
		}
		Video v = daovideo.read(link);
		if (v == null) {
			DAO.rollback();
			throw new Exception("Video não encontrado");
		}
		Usuario u = daousuario.read(email);
		if (u != null) {
			id = localizarMaiorIdVisualizacao() + 1;
			Visualizacao visu = new Visualizacao(id, nota, u, v);
			v.adicionar(visu);
			v.fazerMedia();
			daovideo.update(v);
			
			u.adicionar(visu);
			daousuario.update(u);
			daovisualizacao.create(visu);
			DAO.commit();
			return visu;
		} else {
			id = localizarMaiorIdVisualizacao() + 1;
			Usuario usu = cadastrarUsuario(email);
			Visualizacao visu = new Visualizacao(id, nota, usu, v);
			v.adicionar(visu);
			v.fazerMedia();
			daovideo.update(v);
			daovideo.create(v);
			usu.adicionar(visu);
			daousuario.update(usu);
			
			daovisualizacao.create(visu);
			DAO.commit();
			return visu;
		}
	}

//--------------------------------------- ATUALIZACAO ---------------------------------------------------------
	public static void adicionarAssunto(String link, String palavra) throws Exception {
		DAO.begin();
		if (palavra.isEmpty()) {
			DAO.rollback();
			throw new Exception("Palavra Vazia !");
		}
		Video v = daovideo.read(link);
		if (v == null) {
			DAO.rollback();
			throw new Exception("Video inexistente");
		}
		
	    Assunto a = daoassunto.read(palavra);
		if (a == null) {
            Assunto as = cadastrarAssunto(palavra);
            as.adicionar(v);
            v.adicionar(as);
            daovideo.update(v);
            daoassunto.update(as);
            daoassunto.create(as);
            DAO.commit();
        } else {
            a.adicionar(v);
            v.adicionar(a);
            daovideo.update(v);
            daoassunto.update(a);
            daoassunto.create(a);
            DAO.commit();
        }
    }
	

// ---------------------------------------------------------------------------------------------------
	public static Visualizacao localizarVisualizacao(int id) throws Exception {
		List<Visualizacao> visu = listarVisualizacoes();
		for (Visualizacao v : visu) {
			if (v.getId() == id) {
				return daovisualizacao.read(v);
			}
		}
		DAO.rollback();
		throw new Exception("id não existe !");
	};

	public static Integer localizarMaiorIdVisualizacao() throws Exception {
		List<Visualizacao> visu = listarVisualizacoes();
		id = 0;
		for (Visualizacao v : visu) {
			if (v.getId() > id) {
				id = v.getId();
			}
		}
		return id;

	};
	public static void apagarVisualizacao(int id) throws Exception {
		DAO.begin();
		Visualizacao vi = daovisualizacao.read(id);
		if (vi == null) {
			DAO.rollback();
			throw new Exception("visualizacao não existe !");
		}
		Video v = vi.getVideo();
		Usuario u = vi.getUsuario();
		v.remover(vi);
		u.remover(vi);
		vi.setUsuario(null);
		;
		vi.setVideo(null);
		daousuario.update(u);
		daovideo.update(v);
		daovisualizacao.delete(vi);
		DAO.commit();
	}

	// ------------ LISTAGEM ----------------------------------------

	public static List<Visualizacao> listarVisualizacoes() {
		return daovisualizacao.readAll();
	}

	public static List<Video> listarVideos() {
		return daovideo.readAll();
	}

	public static List<Usuario> listarUsuarios() {
		return daousuario.readAll();
	}

	public static List<Assunto> listarAssuntos() {
		return daoassunto.readAll();
	}

// ------------ CONSULTAS -------------------------------------------
	public static List<Video> consultarVideosPorAssunto(String palavra) throws Exception {
		boolean existe = false;
		List<Video> lista = new ArrayList<Video>();
		if (palavra.isEmpty())
			return daovideo.readAll();
		for (Assunto a : listarAssuntos()) {
			if (a.getPalavra().equals(palavra)) {
				lista = daovideo.consultarVideosPorAssunto(palavra);
				existe = true;
				break;
			}
		}
		if (existe == false) {
			throw new Exception("Assunto com a palavra {   " + palavra + "     } nao existe ");
		}
		return lista;
	}

	public static List<Video> consultarVideosPorUsuario(String email) throws Exception {
		boolean existe = false;
		List<Video> lista = new ArrayList<Video>();
		if (email.isEmpty())
			return daovideo.readAll();
		for (Usuario u : listarUsuarios()) {
			if (u.getEmail().equals(email)) {
				lista = daovideo.consultarVideosPorUsuario(email);
				existe = true;
				break;
			}
		}
		if (existe == false) {
			throw new Exception("Usuario com email {   " + email + "     } nao existe ");
		}
		return lista;
	}

	public static List<Usuario> consultarUsuariosPorVideo(String link) throws Exception {
		boolean existe = false;
		List<Usuario> lista = new ArrayList<Usuario>();
		if (link.isEmpty())
			return daousuario.readAll();
		
		for (Visualizacao v : listarVisualizacoes()) {
			if (v.getVideo().getLink().equals(link)) {
				lista = daousuario.consultarUsuariosPorVideo(link);
				existe = true;
				break;
			}
		}
		if (existe == false) {
			throw new Exception("Link {   " + link + "     } nao tem visualizaçoes ");
		}
		return lista;
	}
}

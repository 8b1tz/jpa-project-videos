package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String versao;
	private String link;
	private String nome;
	private double media;
	
	@ManyToMany
	private List<Assunto> assuntos = new ArrayList<>();
	
	@OneToMany(mappedBy = "video")
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	public Video() {

	}

	public Video(String link, String nome) {
		this.link = link;
		this.nome = nome;
	}

	public double getMedia() {
		return media;
	}

	public String getNome() {
		return nome;
	}

	public String getLink() {
		return link;
	}

	public void adicionar(Assunto a) {
		assuntos.add(a);
	}

	public void adicionar(Visualizacao vis) {
		visualizacoes.add(vis);
	}

	public void remover(Visualizacao vis) {
		vis.setVideo(null);
		visualizacoes.remove(vis);
	}

	public List<Assunto> getListaAssuntos() {
		return assuntos;
	}

	public String getListaAssuntosPretty() {
		String novosassuntos = "";
		for (Assunto assunto : getListaAssuntos()) {
			novosassuntos += assunto.getPalavra() + " ; ";
		}

		return novosassuntos;
	}

	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}

	public void fazerMedia() {
		double soma = 0;
		for (Visualizacao v : visualizacoes) {
			soma += v.getNota();
		}
		this.media = soma / visualizacoes.size();

	}

	@Override
	public String toString() {
		String texto = "Video [" + (link != null ? "link=" + link + ", " : "")
				+ (nome != null ? "nome=" + nome + ", " : "") + "media=" + media;

		texto += ", assuntos= ";
		for (Assunto a : assuntos) {
			texto += a.getPalavra() + "; ";
		}
		texto += "\n visualizacoes=";
		for (Visualizacao vis : visualizacoes) {
			texto += vis;
		}
		return texto;
	}

}
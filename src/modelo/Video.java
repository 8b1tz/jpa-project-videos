package modelo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.engine.internal.CascadePoint;

@Entity
@Table(name = "video20191370002")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Version
	private int versao;
	private String link;
	private String nome;
	private double media;
	

	@ManyToMany(mappedBy="videos",cascade={CascadeType.ALL}) 
	private List<Assunto> assuntos = new ArrayList<>();
	
	@OneToMany(mappedBy="video", cascade={CascadeType.PERSIST,CascadeType.MERGE}) 	
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
		String aux = "";
		
		for (Visualizacao v : visualizacoes) {
			soma += v.getNota();
		}
		
		aux = String.format("%.2f",(soma / visualizacoes.size())).replace(",",".");
		this.media = Double.parseDouble(aux);

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
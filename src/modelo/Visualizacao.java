package modelo;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visualizacao")
public class Visualizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String datahora = String.valueOf((LocalDateTime.now()));
	private int nota;
	private String versao;
	

	@ManyToOne(cascade={CascadeType.ALL})
	private Usuario usuario;
	
	@ManyToOne(cascade={CascadeType.ALL})
	private Video video;

	public Visualizacao() {

	}

	public Visualizacao( int nota, Usuario usuario, Video video) throws Exception {
		if (nota > 5 || nota < 1) {
			throw new Exception("Nota invalida!");
		}
		this.nota = nota;
		this.usuario = usuario;
		this.video = video;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	@Override
	public String toString() {
		return "Visualizacao [id=" + id + ", datahora=" + datahora + ", nota=" + nota + "\n usuario="
				+ usuario.getEmail() + ", video=" + video.getNome() + "]";
	}

	public void setUsuario(Usuario usu) {
		this.usuario = usu;
	}

}

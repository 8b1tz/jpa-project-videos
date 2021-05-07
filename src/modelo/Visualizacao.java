package modelo;

import java.sql.Timestamp;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import  java.util.Random;
import dao.TriggerListener;

@Entity
@EntityListeners( TriggerListener.class ) 
@Table(name = "visualizacao20191370002")
public class Visualizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDateTime datahora =  LocalDateTime.now().minusHours(5).minusMinutes(15).minusSeconds(40);
	private int nota;
	@Version
	private int versao;
	private String idade;
	

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
	
	public LocalDateTime getDatahora() {
		return datahora;
	}

	public void setDatahora(LocalDateTime datahora) {
		this.datahora = datahora;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
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

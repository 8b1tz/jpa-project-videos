package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import dao.TriggerListener;


@Entity
@Table(name = "usuario20191370003")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	@Version
	private int versao;
	
	@OneToMany(mappedBy = "usuario")
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	
	public Usuario() {
	
	}

	public Usuario(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void adicionar(Visualizacao vis) {
		visualizacoes.add(vis);
	}
	public void remover(Visualizacao vis) {
		vis.setUsuario(null);
		visualizacoes.remove(vis);
	}
	
	



	@Override
	public String toString() {
		String texto = "Usuario [email=" + email + "]";

		texto += "\n visualizacoes=";
		for (Visualizacao vis : visualizacoes) {
			texto += vis;
		}
		return texto;
	}

}

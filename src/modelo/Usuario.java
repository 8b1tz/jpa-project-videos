package modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String email;
	private List<Visualizacao> visualizacoes = new ArrayList<>();

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

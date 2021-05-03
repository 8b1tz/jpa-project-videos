package aplicacao_console;

import fachada.Fachada;

public class Consultar {

	String aux = "-------------------------------------------";

	public Consultar() {

		try {

			Fachada.inicializar();
			System.out.println(aux);
			System.out.println(Fachada.consultarUsuariosPorVideo(
					"https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1"));
			System.out.println(aux);
			System.out.println(Fachada.consultarVideosPorAssunto("rock"));
			System.out.println(aux);
			System.out.println(Fachada.consultarVideosPorAssunto("aula"));
			System.out.println(aux);
			System.out.println(Fachada.consultarVideosPorUsuario("ana@gmail.com"));
			System.out.println(aux);
			System.out.println(Fachada.consultarVideosPorUsuario("julia@gmail.com"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
		}
		System.out.println("\nfim do programa");
	}

	// =================================================
	public static void main(String[] args) {
		new Consultar();
	}
}
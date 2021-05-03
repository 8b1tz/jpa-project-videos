package aplicacao_console;

import fachada.Fachada;

public class Atualizar {

	public Atualizar() {
		Fachada.inicializar();
		try {
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1",
					"fama");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1",
					"cool");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=0-7IHOXkiV8&list=RDMMXXYlFuWEuKI&index=2",
					"fama");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1",
					"love");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=0-7IHOXkiV8&list=RDMMXXYlFuWEuKI&index=2",
					"lovely");
			Fachada.adicionarAssunto("https://www.youtube.com/watch?v=0-7IHOXkiV8&list=RDMMXXYlFuWEuKI&index=2",
					"rock");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
			System.out.println("fim do programa");
		}
	}

	// =================================================
	public static void main(String[] args) {
		new Atualizar();
	}
}
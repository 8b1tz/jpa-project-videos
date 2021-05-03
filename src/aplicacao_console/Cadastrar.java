package aplicacao_console;

import fachada.Fachada;
import modelo.Video;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();

			System.out.println("cadastrando...");
			
			Fachada.cadastrarVideo(
					"https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1",
					"the weekend - save your tears", "pop");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=0-7IHOXkiV8&list=RDMMXXYlFuWEuKI&index=2",
					"KALEO - Way Down We Go", "rock");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=cHHLHGNpCSA&list=RDMMXXYlFuWEuKI&index=3",
					"Avicii - Waiting For Love", "eletronica");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=KlIL63MeyMY",
					"Curso POO Teoria  - Aula - 01A", "aula");
			Fachada.cadastrarVideo(
					"https://www.youtube.com/watch?v=Ucyx_QPfDng&list=PLHz_AreHm4dkqe2aR0tQK74m8SFe-aGsY&index=2",
					"Curso POO Teoria  - Aula - 01B", "aula");
			Fachada.cadastrarVideo(
					"https://www.youtube.com/watch?v=aR7CKNFECx0&list=PLHz_AreHm4dkqe2aR0tQK74m8SFe-aGsY&index=3",
					"Curso POO Teoria  - Aula - 02A", "aula");
			
			
			Fachada.cadastrarVideo(
					"https://www.youtube.com/watch?v=isCh4kCeNYU&list=RDa5uQMwRMHcs&index=7",
					"Guns N' Roses - Civil War", "rock");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=Zi_XLOBDo_Y&list=RDa5uQMwRMHcs&index=13",
					"Michael Jackson - Billie Jean", "pop");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=v2AC41dglnM&list=RDa5uQMwRMHcs&index=15",
					"AC/DC - Thunderstruck", "rock");
			Fachada.cadastrarVideo("https://www.youtube.com/watch?v=gOMhN-hfMtY",
					"Eminem - Stan (Long Version) ft. Dido", "rap");
			Fachada.cadastrarVideo(
					"https://www.youtube.com/watch?v=a5uQMwRMHcs",
					"Daft Punk ft. Julian Casablancas - Instant Crush", "eletronica");
			Fachada.cadastrarVideo(
					"https://www.youtube.com/watch?v=RQ9_TKayu9s",
					"Eminem - Cleanin' Out My Closet", "rap");

			
			Fachada.registrarVisualizacao(
					"https://www.youtube.com/watch?v=aR7CKNFECx0&list=PLHz_AreHm4dkqe2aR0tQK74m8SFe-aGsY&index=3", 
					"yohanna@gmail.com",5);
			Fachada.registrarVisualizacao(
					"https://www.youtube.com/watch?v=Ucyx_QPfDng&list=PLHz_AreHm4dkqe2aR0tQK74m8SFe-aGsY&index=2", 
					"julia@gmail.com",3);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=aR7CKNFECx0&list=PLHz_AreHm4dkqe2aR0tQK74m8SFe-aGsY&index=3",
					"ana@gmail.com", 4);
			
			Fachada.registrarVisualizacao(
					"https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1", 
					"joao@gmail.com",5);
			Fachada.registrarVisualizacao(
					"https://www.youtube.com/watch?v=XXYlFuWEuKI&list=RDMMXXYlFuWEuKI&start_radio=1", 
					"maria@gmail.com",2);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=cHHLHGNpCSA&list=RDMMXXYlFuWEuKI&index=3",
					"yohanna@gmail.com", 5);
			
			Fachada.registrarVisualizacao(
					"https://www.youtube.com/watch?v=a5uQMwRMHcs", 
					"yohanna@gmail.com",2);
			Fachada.registrarVisualizacao(
					"https://www.youtube.com/watch?v=v2AC41dglnM&list=RDa5uQMwRMHcs&index=15", 
					"julia@gmail.com",3);
			Fachada.registrarVisualizacao("https://www.youtube.com/watch?v=RQ9_TKayu9s",
					"ana@gmail.com", 4);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Fachada.finalizar();
		}
		System.out.println("fim do programa de cadastros");
	}

	public void cadastrar() {

	}

	// =================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}
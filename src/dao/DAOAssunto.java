package dao;

import java.util.List;

import com.db4o.query.Query;

import modelo.Assunto;

public class DAOAssunto extends DAO<Assunto> {

	@Override
	public Assunto read(Object chave) {
		String palavra = (String) chave; // casting para o tipo da chave

		Query q = manager.query();
		q.constrain(Assunto.class);
		q.descend("palavra").constrain(palavra);
		List<Assunto> resultados = q.execute();
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}

}

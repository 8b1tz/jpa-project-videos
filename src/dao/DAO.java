/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.query.Query;

import modelo.Assunto;
import modelo.Usuario;
import modelo.Video;
import modelo.Visualizacao;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public static void open() {
		if (manager == null) {
			abrirBancoLocal();
		}
	}

	public static void abrirBancoLocal() {
		// new File("banco.db4o").delete(); //apagar o banco
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().messageLevel(0); // 0,1,2,3...
		config.common().objectClass(Video.class).cascadeOnUpdate(true);
		config.common().objectClass(Video.class).cascadeOnDelete(true);
		config.common().objectClass(Video.class).cascadeOnActivate(true);
		config.common().objectClass(Assunto.class).cascadeOnUpdate(true);
		config.common().objectClass(Assunto.class).cascadeOnDelete(true);
		config.common().objectClass(Assunto.class).cascadeOnActivate(true);
		config.common().objectClass(Usuario.class).cascadeOnUpdate(true);
		config.common().objectClass(Usuario.class).cascadeOnDelete(true);
		config.common().objectClass(Usuario.class).cascadeOnActivate(true);
		config.common().objectClass(Visualizacao.class).cascadeOnUpdate(true);
		config.common().objectClass(Visualizacao.class).cascadeOnDelete(false);
		config.common().objectClass(Visualizacao.class).cascadeOnActivate(true);
		// indices
		config.common().objectClass(Video.class).objectField("link").indexed(true);
		config.common().objectClass(Assunto.class).objectField("palavra").indexed(true);
		config.common().objectClass(Usuario.class).objectField("email").indexed(true);
		config.common().objectClass(Visualizacao.class).objectField("id").indexed(true);

		manager = Db4oEmbedded.openFile(config, "bancorepy.db4o");
	}

	public static void close() {
		if (manager != null) {
			manager.close();
			manager = null;
		}
	}

	// ----------CRUD-----------------------

	public void create(T obj) {
		manager.store(obj);
	}

	public abstract T read(Object chave);

	public T update(T obj) {
		manager.store(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.delete(obj);
	}

	public void refresh(T obj) {
		manager.ext().refresh(obj, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		Query q = manager.query();
		q.constrain(type);
		return (List<T>) (q.execute());
	}

	// --------transação---------------
	public static void begin() {
	} // tem que ser vazio

	public static void commit() {
		manager.commit();
	}

	public static void rollback() {
		manager.rollback();
	}

}

package dao;

import java.io.FileInputStream;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static EntityManager manager;
	protected static EntityManagerFactory factory;

	public DAO() {
	}

	public static void open() {
		if (manager == null) {
			abrirBancoLocal();
		}
	}

	public static void abrirBancoLocal() {
		/*****************************************************************************
		 * Determinar o nome da unidade de persistencia a ser processada no
		 * persistence.xml Este nome é a concatenacao dos nomes provedor+sgbd lidos do
		 * arquivo dados.properties
		 *****************************************************************************/
		String nomeUnidadePersistencia = null;
		Properties dados = new Properties();
		String provedor;
		String sgbd = "";
		String ip;
		try {
			dados.load(new FileInputStream("src/dados.properties"));
			provedor = dados.getProperty("provedor");
			sgbd = dados.getProperty("sgbd");
			nomeUnidadePersistencia = provedor + "-" + sgbd;

		} catch (Exception e) {

			System.exit(0);
		}

		/*****************************************************************************
		 * Substituir o ip do persistence.xml pelo ip do dados.properties
		 *****************************************************************************/
		ip = dados.getProperty("ip");
		Properties prop = new Properties();
		prop.setProperty("javax.persistence.jdbc.url", "jdbc:postgresql://127.0.0.1:5432/projeto3");
		factory = Persistence.createEntityManagerFactory(nomeUnidadePersistencia, prop);
		manager = factory.createEntityManager();
	}

	public static void close() {
		if (manager != null && manager.isOpen()) {
			manager.close();
			factory.close();
			manager = null;
		}
	}

	// ----------CRUD-----------------------

	public void create(T obj) {
		manager.persist(obj);
	}

	public abstract T read(Object chave);

	public T update(T obj) {
		manager.merge(obj);
		return obj;
	}

	public void delete(T obj) {
		manager.remove(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		TypedQuery<T> query = manager.createQuery("select x from " + type.getSimpleName() + " x", type);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> readAllPagination(int firstResult, int maxResults) {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		return manager.createQuery("select x from " + type.getSimpleName() + " x", type).setFirstResult(firstResult - 1)
				.setMaxResults(maxResults).getResultList();
	}

	public void deleteAll() {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		String tabela = type.getSimpleName();
		Query query = manager.createQuery("delete from " + tabela);
		query.executeUpdate();

		// resetar a sequencia de ids depende do SGBD
		String nomesgbd = "";
		try {
			Connection con = getConnection();
			if (con == null)
				throw new RuntimeException("DAO - falha ao obter conexao");

			nomesgbd = con.getMetaData().getDatabaseProductName();

		
			query = manager.createNativeQuery("ALTER SEQUENCE " + tabela + "_id_seq RESTART WITH 1");
			
			query.executeUpdate();
		} catch (Exception ex) {
			throw new RuntimeException("DAO - Nome de SGBD invalido:" + nomesgbd);
		}
	}

	public static Connection getConnection() {
		try {
			String driver = (String) manager.getProperties().get("javax.persistence.jdbc.driver");
			String url = (String) manager.getProperties().get("javax.persistence.jdbc.url");
			String user = (String) manager.getProperties().get("javax.persistence.jdbc.user");
			String pass = (String) manager.getProperties().get("javax.persistence.jdbc.password");
			Class.forName(driver);
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception ex) {
			return null;
		}
	}

	// --------transação---------------
	public static void begin() {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}

	public static void commit() {
		if (manager.getTransaction().isActive()) {
			manager.getTransaction().commit();
			manager.clear(); // ---- esvazia o cache de objetos ----
		}
	}

	public static void rollback() {
		if (manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}


}

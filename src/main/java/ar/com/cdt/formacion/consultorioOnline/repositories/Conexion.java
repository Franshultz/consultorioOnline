package ar.com.cdt.formacion.consultorioOnline.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static Conexion instancia;
	private static final String URL = "jdbc:mysql://localhost:3306/consultorio_online";
	//private static final String URL = "jdbc:mysql://localhost:3306/consultorio_onlinee";

	private static final String USUARIO = "root";
	//private static final String PASSWORD = "canela2022";
	private static final String PASSWORD = "cdt123";

	private Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Asegura que se cargue el driver
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se pudo cargar el driver de MySQL", e);
		}
	}

	public static Conexion getInstancia() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getConexion() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, PASSWORD);
	}

	public static void probarConexion() {
		try (Connection conn = getInstancia().getConexion()) {
			if (conn != null && !conn.isClosed()) {
				System.out.println("✅ Conexión a la base de datos exitosa!");
			} else {
				System.out.println("❌ No se pudo establecer la conexión.");
			}
		} catch (SQLException e) {
			System.out.println("❌ Error al conectar a la base de datos: " + e.getMessage());
		}
	}
}


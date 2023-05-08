package co.edu.uptc.coneccion;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class BDConeccion {

	private String esquema = "productosinvestigacion";
	// Definir la ruta de la base de datos
	private String dbUrl = "jdbc:mysql://20.55.103.27:3306/" + esquema;
	// Definir el nombre de usuario de la base de datos
	private String dbUser = "daniel";
	// Definir la contrase�a de la base de datos
	private String dbPassword = "D@niel1927";
	// Definir controlador de carga
	private String jdbcName = "com.mysql.jdbc.Driver";

	// Con�ctate a la base de datos
	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(jdbcName);
		} catch (Exception e) {
			System.err.println("Error controlador");
			System.err.println(e);
		}
		try {
			conn = (Connection) DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException ex) {
			System.err.println("Error conexion");
			System.err.println(ex);
		}
		return conn;
	}

}

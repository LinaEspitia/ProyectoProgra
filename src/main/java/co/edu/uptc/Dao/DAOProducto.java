package co.edu.uptc.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import co.edu.uptc.coneccion.BDConeccion;
import co.edu.uptc.modelo.Producto;

public class DAOProducto {

	private Connection conector;

	public DAOProducto() {

		conector = new BDConeccion().getConn();
	}

	public void AgregarProducto(Producto p) {

		String sentenciaSQL = "INSERT INTO Producto (nombreProducto, tipoProducto, descripcionProducto, anioPublicacion) VALUES ('"
				+ p.getNombreProducto() + "', '" + p.getTipoProducto() + "', '" + p.getDescripcionProducto() + "', '"
				+ p.getAnioPublicacion() + "')";

		Statement ejecucion = null;

		try {
			ejecucion = (Statement) conector.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ejecucion.execute(sentenciaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public ArrayList<Producto> ListaProductos(){
		ArrayList<Producto> lista= new ArrayList<>();
		String SentenciaSQL="SELECT * FROM Producto;";
		
		Statement ejecucion = null;
		ResultSet resultados = null;
		
		
		try {
			ejecucion = (Statement) conector.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultados=ejecucion.executeQuery(SentenciaSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (resultados.next()) {
				Producto p = new Producto();
				
				p.setIdProducto(resultados.getInt("idProducto"));
				p.setTipoProducto(resultados.getString("tipoProducto"));
				p.setNombreProducto(resultados.getString("nombreProducto"));
				p.setDescripcionProducto(resultados.getString("descripcionProducto"));
				p.setAnioPublicacion(resultados.getString("anioPublicacion"));
				
				lista.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return lista;
	}

}

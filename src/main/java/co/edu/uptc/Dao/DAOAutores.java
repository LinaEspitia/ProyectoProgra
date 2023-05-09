package co.edu.uptc.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import co.edu.uptc.coneccion.BDConeccion;
import co.edu.uptc.modelo.Autores;
import co.edu.uptc.modelo.Producto;

public class DAOAutores {
	private Connection conector;

	public DAOAutores() {

		conector = new BDConeccion().getConn();
	}

	public void AgregarAutor(Autores a) {
		String sentenciaSQL = "INSERT INTO Autores (nombreAutores, tipoDocumento, numeroDocumento, productoId) VALUES ('"
				+ a.getNombreAutores() + "', '" + a.getTipoDocumento() + "', '" + a.getNumeroDocumento() + "', '" + a.getProductoId() + "')";

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
	
	
	public int obtenerIdUltimoProducto () {
		
		String sentenciaSQL = "SELECT idProducto from Producto ORDER BY idProducto DESC limit 1";
		
		Statement ejecucion = null;
		ResultSet resultados = null;
		int idP = 0;
		
		try {
			ejecucion = (Statement) conector.createStatement();
			resultados=ejecucion.executeQuery(sentenciaSQL);
			resultados.next();
			idP = resultados.getInt("idProducto");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return idP;

	}
	
	public ArrayList<Autores> ListaAutores(){
		ArrayList<Autores> lista= new ArrayList<>();
		//String SentenciaSQL="SELECT * FROM Autores where productoId = "+ id + ";";
		String SentenciaSQL="SELECT * FROM Autores ;";	
		
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
				Autores a = new Autores();
	
				a.setIdAutores(resultados.getInt("idAutores"));
				a.setNombreAutores(resultados.getString("nombreAutores"));
				a.setTipoDocumento(resultados.getString("tipoDocumento"));
				a.setNumeroDocumento(resultados.getString("numeroDocumento"));
				
				lista.add(a);
				

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return lista;
	}

}

package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import co.edu.uptc.Dao.DAOAutores;
import co.edu.uptc.modelo.Autores;


/**
 * Servlet implementation class RegistrarProductos
 */

@MultipartConfig
public class llenarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public llenarAutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("productoAutor"));
		DAOAutores da = new DAOAutores();
		
		ArrayList<Autores> listaA = new DAOAutores().ListaAutores();
		for (Autores autores : listaA) {
			System.out.println(autores.getIdAutores());
		}
		response.sendRedirect("MostrarDatos");
	}
}


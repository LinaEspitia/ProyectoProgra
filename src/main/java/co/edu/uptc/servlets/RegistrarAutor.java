package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import co.edu.uptc.Dao.DAOAutores;
import co.edu.uptc.modelo.Autores;

/**
 * Servlet implementation class RegistrarAutor
 */
public class RegistrarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarAutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombreAutores = request.getParameter("nombreAutores");
		String tipoDocumento = request.getParameter("tipoDocumento");
		String numeroDocumento = request.getParameter("numeroDocumento");

		Autores a = new Autores();
		DAOAutores da = new DAOAutores();
		a.setNombreAutores(nombreAutores);
		a.setNumeroDocumento(numeroDocumento);
		a.setTipoDocumento(tipoDocumento);
		System.out.println(da.obtenerIdUltimoProducto());
		a.setProductoId(da.obtenerIdUltimoProducto());
		da.AgregarAutor(a);
		
		response.sendRedirect("Autores.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

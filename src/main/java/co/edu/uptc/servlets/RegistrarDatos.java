package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import co.edu.uptc.Dao.DAOAutores;
import co.edu.uptc.Dao.DAOProducto;
import co.edu.uptc.modelo.Autores;
import co.edu.uptc.modelo.Producto;

/**
 * Servlet implementation class RegistrarDatos
 */
public class RegistrarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarDatos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nombreAutores = request.getParameter("nombreAutores");
		String tipoDocumento = request.getParameter("tipoDocumento");
		String numeroDocumento = request.getParameter("numeroDocumento");

		String nombreProducto = request.getParameter("nombreProducto");
		String tipoProducto = request.getParameter("tipoProducto");
		String descripcionProducto = request.getParameter("descripcionProducto");
		String fechaTexto = request.getParameter("fechaPublicacion");

		Producto p = new Producto();
		p.setAnioPublicacion(fechaTexto);
		p.setDescripcionProducto(descripcionProducto);
		p.setNombreProducto(nombreProducto);
		p.setTipoProducto(tipoProducto);

		Autores a = new Autores();
		a.setNombreAutores(nombreAutores);
		a.setNumeroDocumento(numeroDocumento);
		a.setTipoDocumento(tipoDocumento);
		DAOAutores da = new DAOAutores();
		DAOProducto dp = new DAOProducto();
		dp.AgregarProducto(p);
		da.AgregarAutor(a);
		
	}

}

package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import co.edu.uptc.Dao.DAOProducto;
import co.edu.uptc.modelo.Producto;


/**
 * Servlet implementation class ActualizarProducto
 */
public class ActualizarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("productoId"));
		System.out.println(request.getParameter("nombreProducto"));
		int idProducto = Integer.parseInt(request.getParameter("productoId"));
		System.out.println(idProducto);
		String nombreProducto = request.getParameter("nombreProducto");
		String tipoProducto = request.getParameter("tipoProducto");
		String descripcionProducto = request.getParameter("descripcionProducto");
		String fechaTexto = request.getParameter("fechaPublicacion");
		
		Producto p = new Producto();
		p.setIdProducto(idProducto);
		p.setAnioPublicacion(fechaTexto);
		p.setDescripcionProducto(descripcionProducto);
		p.setNombreProducto(nombreProducto);
		p.setTipoProducto(tipoProducto);
		
		DAOProducto dp = new DAOProducto();
		dp.ActualizarProducto(p);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

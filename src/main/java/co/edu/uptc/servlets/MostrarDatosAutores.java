package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import co.edu.uptc.Dao.DAOAutores;
import co.edu.uptc.Dao.DAOProducto;
import co.edu.uptc.modelo.Autores;
import co.edu.uptc.modelo.Producto;

/**
 * Servlet implementation class MostrarDatosAutores
 */
public class MostrarDatosAutores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarDatosAutores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Autores> ListaDatos = new DAOAutores().ListaAutores();
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> tabla de datos</title>");
		out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		
		//out.println("<ul class =\"nav .nav-pills\">");
		//out.println("<li><a href =\"RegistroProductos.html\">Registrar</a></li>");
		//out.println("<li class =\"nav=item\"><a href =\"RegistroProductos.html\" class=\"nav-link\">Registrar</a></li>");
		
	//	out.println("<li><a href =\"ListaProductos\">Lista de datos</a></li>");
		//out.println("</ul>");
		
		out.println("<table>");
		out.println("<tr><th>Id Autores</th><th>Nombre Autores</th><th>Tipo Documento</th><th>Numero Documento</th></tr>");
		
		for (Autores elemento : ListaDatos) {
			out.println("<tr>" + "<td>" + elemento.getIdAutores() + "</td>" + "<td>" + elemento.getNombreAutores() + "</td>" +"<td>" + elemento.getTipoDocumento() + "</td>" +"<td>" + elemento.getNumeroDocumento() + "</td>" + "</tr>");
		}
		
		out.println("</table>");
		out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe\" crossorigin=\"anonymous\"></script>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

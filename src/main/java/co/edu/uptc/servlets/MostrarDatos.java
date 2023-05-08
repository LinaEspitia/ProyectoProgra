package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import co.edu.uptc.Dao.DAOProducto;
import co.edu.uptc.modelo.Producto;

/**
 * Servlet implementation class MostrarDatos
 */
public class MostrarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarDatos() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Producto> ListaDatos = new DAOProducto().ListaProductos();
		DAOProducto p = new DAOProducto();
		
		

		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		MostrarDatos mostrarDatos = new MostrarDatos();

		out.println("<script> "
		    + "function eliminar() {\r\n"
		    + "alert('Eliminar');"
		    + "mostrarDatos.eliminar1();" // Llamar el método desde la instancia
		    + "}"
		    + "</script>");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> tabla de datos</title>");
		out.println(
				"<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ\" crossorigin=\"anonymous\">");
		out.println(
				"<link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap\" rel=\"stylesheet\">");
		out.println(
				"<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\"\r\n" + "rel=\"stylesheet\">");
		out.println("<script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>");
		out.println("</head>");
		out.println("<body>");

		// out.println("<ul class =\"nav .nav-pills\">");
		// out.println("<li><a href =\"RegistroProductos.html\">Registrar</a></li>");
		// out.println("<li class =\"nav=item\"><a href =\"RegistroProductos.html\"
		// class=\"nav-link\">Registrar</a></li>");

		// out.println("<li><a href =\"ListaProductos\">Lista de datos</a></li>");
		// out.println("</ul>");

		out.println("<table class='table'>");
		out.println(
				"<tr><th>Id producto</th><th>Tipo Producto</th><th>Nombre Producto</th><th>Año de publicacion</th><th>Descripcion del producto</th></tr>");

		for (Producto elemento : ListaDatos) {
			out.println("<tr>" + "<td>" + elemento.getIdProducto() + "</td>" + "<td>" + elemento.getTipoProducto()
					+ "</td>" + "<td>" + elemento.getNombreProducto() + "</td>" + "<td>" + elemento.getAnioPublicacion()
					+ "</td>" + "<td>" + elemento.getDescripcionProducto() + "</td>" + "<td>" + "<button  id="
					+ elemento.getIdProducto() + " onclick=\"editardatos(" + elemento.getIdProducto() + ", '"
					+ elemento.getNombreProducto() + "','" + elemento.getTipoProducto() + "','"
					+ elemento.getDescripcionProducto() + "','" + elemento.getAnioPublicacion()
					+ "');\" class=\"btn btn-link\" title=\"Editar\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">\r\n "
					+ "<span class=\"material-icons\">&#xe3c9;</span>\r\n" + "<i class=\"fa fa-pencil\"></i>\r\n"
					+ "</button>\r\n" 
					+ " <form action=\"RegistrarProductos\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
					+ "<input type=\"text\" id=\"productoId\" name=\"productoId\" class=\"form-control\" value =  "+ elemento.getIdProducto()+"  style= \" display:none\"  required>\r\n"
					+ "<input type=\"text\" id=\"eliminar\" name=\"eliminar\" class=\"form-control\" value=1  style= \" display:none\" required>\r\n"
					+ "<button class=\"btn btn-link\" title=\"Eliminar\">\r\n"
					+ "<span class=\"material-icons\">&#xe872;</span>\r\n" + "<i class=\"fa fa-trash\"></i>\r\n"
					+ "</button>"+ "    </form>" + "</td>" + "</tr>");
		}

		out.println("</table>");

		out.println("<!-- Button trigger modal -->\r\n"
				+ "<div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
				+ "  <div class=\"modal-dialog\">\r\n" + "    <div class=\"modal-content\">\r\n"
				+ "      <div class=\"modal-header\">\r\n"
				+ "        <h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\">Modal title</h1>\r\n"
				+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
				+ "      </div>\r\n" + "      <div class=\"modal-body\">\r\n"
				+ "           <form action=\"RegistrarProductos\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
				+ "      <div class=\"mb-3\">\r\n"
				+ "        <input type=\"text\" id=\"productoId\" name=\"productoId\" class=\"form-control\" style= \" display:none\" required>\r\n"
				+ "        <label for=\"nombreProducto\" class=\"form-label\">Nombre del producto:</label>\r\n"
				+ "        <input type=\"text\" id=\"nombreProducto\" name=\"nombreProducto\" class=\"form-control\" required>\r\n"
				+ "      </div>\r\n" + "\r\n" + "      <div class=\"mb-3\">\r\n"
				+ "        <label for=\"tipoProducto\" class=\"form-label\">Tipo de producto:</label>\r\n"
				+ "        <select class=\"form-control\" id=\"tipoProducto\" name=\"tipoProducto\" required>\r\n"
				+ "          <option value=\"\">Seleccione una categoria</option>\r\n"
				+ "          <option value=\"libro cientifico\">Libro cientifico</option>\r\n"
				+ "          <option value=\"capitulo de libro cientifico\">Capitulo de libro cientifico</option>\r\n"
				+ "    <option value=\"articulo cientifico\">Articulo cientifico</option>\r\n"
				+ "    <option value=\"articulo de reflexion\">Articulo de reflexion</option>\r\n"
				+ "    <option value=\"articulo de revision\">Articulo de revision</option>\r\n"
				+ "    <option value=\"articulo de divulgacion cientifica\">Articulo de divulgacion cientifica</option>\r\n"
				+ "    <option value=\"documento de trabajo\">Documento de trabajo</option>\r\n"
				+ "    <option value=\"tesis doctoral\">Tesis doctoral</option>\r\n"
				+ "    <option value=\"tesis de maestria\">Tesis de maestria</option>\r\n"
				+ "    <option value=\"trabajo de grado\">Trabajo de grado</option>\r\n"
				+ "    <option value=\"software cientifico\">Software cientifico</option>\r\n"
				+ "    <option value=\"patente\">Patente</option>\r\n"
				+ "    <option value=\"modelos de utilidad\">Modelos de utilidad</option>\r\n"
				+ "    <option value=\"disenos industriales\">Disenos industriales</option>\r\n"
				+ "    <option value=\"cultivar registrado\">Cultivar registrado</option>\r\n"
				+ "    <option value=\"variedad animal registrada\">Variedad animal registrada</option>\r\n"
				+ "    <option value=\"variedad vegetal registrada\">Variedad vegetal registrada</option>\r\n"
				+ "    <option value=\"prototipo\">Prototipo</option>\r\n"
				+ "    <option value=\"producto tecnologico\">Producto tecnologico</option>\r\n"
				+ "          <option value=\"producto de apropiacion social del conocimiento\">Producto de apropiacion social del conocimiento</option>\r\n"
				+ "        </select>\r\n" + "      </div>\r\n" + "\r\n" + "      <div class=\"mb-3\">\r\n"
				+ "        <label for=\"descripcionProducto\" class=\"form-label\">Descripción del producto:</label>\r\n"
				+ "        <textarea id=\"descripcionProducto\" name=\"descripcionProducto\" rows=\"4\" cols=\"50\" class=\"form-control\" required></textarea>\r\n"
				+ "      </div>\r\n" + "\r\n" + "      <div class=\"mb-3\">\r\n"
				+ "        <label for=\"fechaPublicacion\" class=\"form-label\">Fecha de publicación:</label>\r\n"
				+ "        <input type=\"date\" id=\"fechaPublicacion\" name=\"fechaPublicacion\" class=\"form-control\" required>\r\n"
				+ "      </div>\r\n" + "      \r\n"
				+ "    <button type=\"submit\" class=\"btn btn-primary\">Agregar</button>\r\n" + "    </form>"
				+ "      </div>\r\n" + "      <div class=\"modal-footer\">\r\n"
				+ "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n" + "</div>");

		out.println("<script>\r\n" + "      function editardatos(id,nombre, tipo, descripcion, publicacion) {\r\n"
				+ "			var idForm = document.getElementById(\"productoId\");\r\n" + "    		idForm.value = id;"
				+ "			var nombreForm = document.getElementById(\"nombreProducto\");\r\n"
				+ "    		nombreForm.value = nombre;"
				+ "			var tipoForm = document.getElementById(\"tipoProducto\");\r\n"
				+ "    		tipoForm.value = tipo;"
				+ "			var descripcionForm = document.getElementById(\"descripcionProducto\");\r\n"
				+ "    		descripcionForm.value = descripcion;"
				+ "			var publicacionForm = document.getElementById(\"fechaPublicacion\");\r\n"
				+ "    		publicacionForm.value = publicacion;" + "      }\r\n" + "    </script>");
		
		
		
		out.println(
				"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe\" crossorigin=\"anonymous\"></script>");
		out.println("</body>");
		out.println("</html>");
		
		out.println("<script> "
			    + "function cargarId(Id) {\r\n"
			    + "alert('id');"
			    + "}"
			    + "</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	public void eliminar1(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    DAOProducto p = new DAOProducto();
	    p.eliminar("1");
	    response.getWriter().write("Producto eliminado!");
	}

}

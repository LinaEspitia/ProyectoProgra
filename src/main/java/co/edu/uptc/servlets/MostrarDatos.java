package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import co.edu.uptc.Dao.DAOAutores;
import co.edu.uptc.Dao.DAOProducto;
import co.edu.uptc.modelo.Autores;
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
		ArrayList<Producto> listaProductos = new DAOProducto().ListaProductos();

		
		DAOProducto p = new DAOProducto();

		String filtro = request.getParameter("filtro");
		String valor = request.getParameter("valor");

		ArrayList<Producto> listaFiltrada = new ArrayList<Producto>();

		if (filtro != null && valor != null) {
			if (filtro.equals("nombre")) {
				String tipo = valor;
				for (Producto producto : listaProductos) {
					if (producto.getNombreProducto().equals(tipo)) {
						listaFiltrada.add(producto);
					}
				}
				listaProductos = listaFiltrada;
			} else if (filtro.equals("anio")) {
				String tipo = valor;
				for (Producto producto : listaProductos) {
					if (producto.getAnioPublicacion().equals(tipo)) {
						listaFiltrada.add(producto);
					}
				}
				listaProductos = listaFiltrada;

			} else if (filtro.equals("tipo")) {
				String tipo = valor;
				for (Producto producto : listaProductos) {
					if (producto.getTipoProducto().equals(tipo)) {
						listaFiltrada.add(producto);
					}
				}
				listaProductos = listaFiltrada;
			} else {
			}

		} else {

		}
		// --------------------
		HashMap<String, Integer> estadisticas = new HashMap<>();

		for (Producto producto : listaProductos) {
			estadisticas.put(producto.getTipoProducto(), 0);
		}

		for (Producto producto : listaProductos) {
			int cantidad = estadisticas.get(producto.getTipoProducto()) + 1;
			estadisticas.put(producto.getTipoProducto(), cantidad);
		}

		HashMap<String, Integer> estadisticasAño = new HashMap<>();

		for (Producto producto1 : listaProductos) {
			estadisticasAño.put(producto1.getAnioPublicacion(), 0);
		}

		for (Producto producto1 : listaProductos) {
			int cantidad = estadisticasAño.get(producto1.getAnioPublicacion()) + 1;
			estadisticasAño.put(producto1.getAnioPublicacion(), cantidad);
		}
	
		
		// -----------------

		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		MostrarDatos mostrarDatos = new MostrarDatos();

		out.println("<script> " + "function eliminar() {\r\n" + "alert('Eliminar');" + "mostrarDatos.eliminar1();" + "}"
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
		// filtrar

		out.println("<div class=\"container\">");
		out.println("<form method=\"get\">");
		out.println("<div class=\"row mt-4\">");
		out.println("<div class=\"col-md-4\">");
		out.println("<select class=\"form-select\" name=\"filtro\">");
		out.println("<option value=\"\">Seleccionar filtro...</option>");
		out.println("<option value=\"nombre\">Nombre Producto</option>");
		
		out.println("<option value=\"anio\">Año de publicación</option>");
		out.println("<option value=\"tipo\">Tipo de producto</option>");
		out.println("</select>");
		out.println("</div>");
		out.println("<div class=\"col-md-4\">");
		out.println("<input type=\"text\" class=\"form-control\" name=\"valor\" placeholder=\"Valor de búsqueda...\">");
		out.println("</div>");
		out.println("<div class=\"col-md-4\">");
		out.println("<button type=\"submit\" class=\"btn btn-primary\">Filtrar</button>");
		out.println("</div>");
		out.println("</div>");
		out.println("</form>");

		out.println("<table class='table'>");
		out.println(
				"<tr><th>Id producto</th><th>Tipo Producto</th><th>Nombre Producto</th><th>Año de publicacion</th><th>Descripcion del producto</th></tr>");

		for (Producto elemento : listaProductos) {
			out.println("<tr>" + "<td>" + elemento.getIdProducto() + "</td>" + "<td>" + elemento.getTipoProducto()
					+ "</td>" + "<td>" + elemento.getNombreProducto() + "</td>" + "<td>" + elemento.getAnioPublicacion()
					+ "</td>" + "<td>" + elemento.getDescripcionProducto() + "</td>" + "<td>" + "</td>" + "<td>" + "<button  id="
					+ elemento.getIdProducto() + " onclick=\"editardatos('" + elemento.getIdProducto() + "', '"
					+ elemento.getNombreProducto() + "','" + elemento.getTipoProducto() + "','"
					+ elemento.getDescripcionProducto() + "','" + elemento.getAnioPublicacion()
					+ "');\" class=\"btn btn-link\" title=\"Editar\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\">\r\n "
					+ "<span class=\"material-icons\">&#xe3c9;</span>\r\n" + "<i class=\"fa fa-pencil\"></i>\r\n"
					+ "</button>\r\n"
					+ " <form action=\"CrudProductos\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
					+ "<input type=\"text\" id=\"productoId\" name=\"productoId\" class=\"form-control\" value =  "
					+ elemento.getIdProducto() + "  style= \" display:none\"  required>\r\n"
					+ "<input type=\"text\" id=\"eliminar\" name=\"eliminar\" class=\"form-control\" value=1  style= \" display:none\" required>\r\n"
					+ "<button class=\"btn btn-link\" title=\"Eliminar\">\r\n"
					+ "<span class=\"material-icons\">&#xe872;</span>\r\n" + "<i class=\"fa fa-trash\"></i>\r\n"
					+ "</button>" + "    </form>"
					+ " <form action=\"llenarAutor\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
					+ "<input type=\"text\" id=\"productoAutor\" name=\"productoAutor\" class=\"form-control\" value="+elemento.getIdProducto()+" style= \" display:none\" required>\r\n"
					+ "<button class=\"btn btn-link\" title=\"Autores\" data-bs-toggle=\"modal\" data-bs-target=\"#autoresmodal\">\r\n"
					+ "<span class=\"material-icons\">&#xe7ef;</span>\r\n" + "<i class=\"fa fa-users\"></i>\r\n"
					+ "</button> </form>"
					+ "</td>" + "</tr>");
		}

		out.println("</table>");
		Producto pro = new Producto();
		System.out.println(pro.getIdProducto());
	    String id = request.getParameter("productoAutor");
		ArrayList<Autores> ListaDatosAutores = new DAOAutores().ListaAutores();

		System.out.println(id);
		out.println("<!-- Modal -->\r\n"
				+ "<div class=\"modal fade\" id=\"autoresmodal\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
				+ "  <div class=\"modal-dialog modal-xl\">\r\n"
				+ "    <div class=\"modal-content\">\r\n"
				+ "      <div class=\"modal-header\">\r\n"
				+ "        <h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\">Modal title</h1>\r\n"
				+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-body\">\r\n"
				+ "     <table class= \"table table-striped\"> \r\n"
				+ "		<tr><th scope=\" col \">Id Autores</th><th>Nombre Autores</th><th>Tipo Documento</th><th>Numero Documento</th></tr>\r\n"
				+ "		\r\n");
					for (Autores elemento : ListaDatosAutores) {
						out.println("<tr>" + "<td>" + elemento.getIdAutores() + "</td>" + "<td>" + elemento.getNombreAutores() + "</td>" +"<td>" + elemento.getTipoDocumento() + "</td>" +"<td>" + elemento.getNumeroDocumento() + "</td>" + "</tr>");
						};
				out.println("\r\n"
				+ "		</table>\r\n"
				+ "      </div>\r\n"
				+ "      <div class=\"modal-footer\">\r\n"
				+ "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\r\n"
				+ "      </div>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "</div>");		

		out.println("<!-- Button trigger modal -->\r\n"
				+ "<div class=\"modal fade\" id=\"exampleModal\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
				+ "  <div class=\"modal-dialog\">\r\n" + "    <div class=\"modal-content\">\r\n"
				+ "      <div class=\"modal-header\">\r\n"
				+ "        <h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\">Modal title</h1>\r\n"
				+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
				+ "      </div>\r\n" + "      <div class=\"modal-body\">\r\n"
				+ "           <form action=\"CrudProductos\" method=\"post\">\r\n"
				+ "      <div class=\"mb-3\">\r\n"
				+ "        <input type=\"text\" id=\"productoIdActu\" name=\"productoIdActu\" class=\"form-control\" style = \"display: none;\" required>\r\n"
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
				+ "    <button type=\"submit\" class=\"btn btn-primary\">Actualizar</button>\r\n" + "    </form>"
				+ "      </div>\r\n" + "      <div class=\"modal-footer\">\r\n" + "      </div>\r\n" + "    </div>\r\n"
				+ "  </div>\r\n" + "</div>");

		out.println("<script>\r\n" + "      function editardatos(id,nombre, tipo, descripcion, publicacion) {\r\n"
		
				+ "			var idForm = document.getElementById(\"productoIdActu\");\r\n" + "    		idForm.value = id;"
				+ "			var nombreForm = document.getElementById(\"nombreProducto\");\r\n"
				+ "    		nombreForm.value = nombre;"
				+ "			var tipoForm = document.getElementById(\"tipoProducto\");\r\n"
				+ "    		tipoForm.value = tipo;"
				+ "			var descripcionForm = document.getElementById(\"descripcionProducto\");\r\n"
				+ "    		descripcionForm.value = descripcion;"
				+ "			var publicacionForm = document.getElementById(\"fechaPublicacion\");\r\n"
				+ "    		publicacionForm.value = publicacion;" + "      }\r\n" + "    </script>");
		
		out.println("<script>\r\n" + " function obtenerIdProducto() {\r\n"
				
				+ "			var idProductoOb = document.getElementById(\"productoIdActu\");\r\n" 
				+ "    		return idProductoOb" + "      }\r\n" + "    </script>");
		
	/*	out.println("<script>\r\n"
				+ "		function mostrarAutores() {\r\n");
				ArrayList<Autores> ListaDatosAutores = new DAOAutores().ListaAutores(77);

				out.println("<!-- Modal -->\r\n"
						+ "<div class=\"modal fade\" id=\"autoresmodal\" tabindex=\"-1\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
						+ "  <div class=\"modal-dialog modal-xl\">\r\n"
						+ "    <div class=\"modal-content\">\r\n"
						+ "      <div class=\"modal-header\">\r\n"
						+ "        <h1 class=\"modal-title fs-5\" id=\"exampleModalLabel\">Modal title</h1>\r\n"
						+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
						+ "      </div>\r\n"
						+ "      <div class=\"modal-body\">\r\n"
						+ "     <table class= \"table table-striped\"> \r\n"
						+ "		<tr><th scope=\" col \">Id Autores</th><th>Nombre Autores</th><th>Tipo Documento</th><th>Numero Documento</th></tr>\r\n"
						+ "		\r\n");
							for (Autores elemento : ListaDatosAutores) {
								out.println("<tr>" + "<td>" + elemento.getIdAutores() + "</td>" + "<td>" + elemento.getNombreAutores() + "</td>" +"<td>" + elemento.getTipoDocumento() + "</td>" +"<td>" + elemento.getNumeroDocumento() + "</td>" + "</tr>");
								};
						out.println("\r\n"
						+ "		</table>\r\n"
						+ "      </div>\r\n"
						+ "      <div class=\"modal-footer\">\r\n"
						+ "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\r\n"
						+ "        <button type=\"button\" class=\"btn btn-primary\">Save changes</button>\r\n"
						+ "      </div>\r\n"
						+ "    </div>\r\n"
						+ "  </div>\r\n"
						+ "</div>");
						out.println(
				 "		}\r\n"
				+ "	</script>"); */
		

		// -----------------------------------------prueba
		out.println("<table class='table'>");
		out.println("<tr><th>Tipo de producto</th><th>Cantidad</th></tr>");
		int totalCantidad = 0;
		for (Entry<String, Integer> entry : estadisticas.entrySet()) {
		    totalCantidad += entry.getValue();
		}
		for (Entry<String, Integer> entry : estadisticas.entrySet()) {
		    int cantidad = entry.getValue();
		    double porcentaje = (double) cantidad / totalCantidad * 100;
		    out.println("<tr><td>" + entry.getKey() + "</td><td>" + cantidad + " / " + totalCantidad + " (" + porcentaje + "%)</td></tr>");
		}
		out.println("</table>");
		
		

		// -----------------------------------------prueba
		//---------año
		out.println("<table class='table'>");
		out.println("<tr><th>Tipo de producto</th><th>Cantidad</th></tr>");
		int totalCantidad1 = 0;
		for (Entry<String, Integer> entry : estadisticasAño.entrySet()) {
		    totalCantidad += entry.getValue();
		}
		for (Entry<String, Integer> entry : estadisticasAño.entrySet()) {
		    int cantidad = entry.getValue();
		    double porcentaje = (double) cantidad / totalCantidad * 100;
		    out.println("<tr><td>" + entry.getKey() + "</td><td>" + cantidad + " / " + totalCantidad + " (" + porcentaje + "%)</td></tr>");
		}
		out.println("</table>");
		//------
	
		
		
		out.println(
				"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe\" crossorigin=\"anonymous\"></script>");
		out.println("</body>");
		out.println("</html>");

		out.println("<script> " + "function cargarId(Id) {\r\n" + "alert('id');" + "}" + "</script>");
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

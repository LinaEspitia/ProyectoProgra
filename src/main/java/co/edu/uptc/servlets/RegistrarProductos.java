package co.edu.uptc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;



/**
 * Servlet implementation class RegistrarProductos
 */

@MultipartConfig
public class RegistrarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarProductos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	/*String eliminarId = null;
		String id = null;
		id=request.getParameter("productoId");
		eliminarId = request.getParameter("eliminar");
		if (eliminarId != null){
			
			DAOProducto dp = new DAOProducto();
			dp.eliminar(id);
			
			response.sendRedirect("MostrarDatos");
		} else {
			
			if (id==null) {
				/* 
				
				String nombreProducto = request.getParameter("nombreProducto");
				String tipoProducto = request.getParameter("tipoProducto");
				String descripcionProducto = request.getParameter("descripcionProducto");
				String fechaTexto = request.getParameter("fechaPublicacion");
				
				Producto p = new Producto();
				p.setAnioPublicacion(fechaTexto);
				p.setDescripcionProducto(descripcionProducto);
				p.setNombreProducto(nombreProducto);
				p.setTipoProducto(tipoProducto);

				
				DAOProducto dp = new DAOProducto();
				try {
					dp.AgregarProducto(p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response.sendRedirect("Autores.html"); 
			} else {
				
				int idProducto = Integer.parseInt(request.getParameter("productoId"));
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
				
				response.sendRedirect("MostrarDatos");
			}
			
		}*/

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Obtener el archivo enviado en el formulario
        Part filePart = request.getPart("archivoPDF");

        // Obtener el nombre del archivo
        String archivoNombre = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        

        // Obtener el contenido del archivo
        InputStream fileContent = filePart.getInputStream();
		
        byte[] archivoContenido = fileContent.readAllBytes();   
    	String esquema = "productosinvestigacion";
    	// Definir la ruta de la base de datos
    	String dbUrl = "jdbc:mysql://20.55.103.27:3306/" + esquema;
        String user = "daniel";
        String password = "D@niel1927";
        try (Connection conn = (Connection) DriverManager.getConnection(dbUrl, user, password)) {
            String sql = "INSERT INTO Producto (tipoProducto, nombreProducto, descripcionProducto, anioPublicacion, archivoNombre, archivoContenido) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = (PreparedStatement) conn.prepareStatement(sql);
            
			String nombreProducto = request.getParameter("nombreProducto");
			String tipoProducto = request.getParameter("tipoProducto");
			String descripcionProducto = request.getParameter("descripcionProducto");
			String fechaTexto = request.getParameter("fechaPublicacion");
			
		    statement.setString(1, tipoProducto);
			statement.setString(2, nombreProducto);
		    statement.setString(3, descripcionProducto);
		    statement.setString(4, fechaTexto);
            statement.setString(5, archivoNombre);
            statement.setBytes(6, archivoContenido);
            statement.executeUpdate();
            
			response.sendRedirect("RegistrarAutor");
            
        } catch (Exception ex) {
            response.getWriter().println("Error: " + ex.getMessage());
        }
	}
}

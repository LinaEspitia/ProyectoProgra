package co.edu.uptc.modelo;

public class Autores {

	private int idAutores;
	private String nombreAutores;
	private String tipoDocumento;
	private String numeroDocumento;
	private int productoId;

	public int getIdAutores() {
		return idAutores;
	}

	public void setIdAutores(int idAutores) {
		this.idAutores = idAutores;
	}

	public String getNombreAutores() {
		return nombreAutores;
	}

	public void setNombreAutores(String nombreAutores) {
		this.nombreAutores = nombreAutores;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public int getProductoId() {
		return productoId;
	}

	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
}

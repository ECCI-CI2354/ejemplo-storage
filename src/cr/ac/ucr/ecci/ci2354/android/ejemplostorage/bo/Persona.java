package cr.ac.ucr.ecci.ci2354.android.ejemplostorage.bo;

public class Persona {
	private int id;
	private String nombre;
	private String apellido;
	private String telefono;
	private DireccionFisica direccion;
	private String correo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public DireccionFisica getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionFisica direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
}

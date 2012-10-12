package cr.ac.ucr.ecci.ci2354.android.ejemplostorage.bo;

import cr.ac.ucr.ecci.ci2354.android.ejemplostorage.DBConstants.PersonColumns;
import android.content.ContentValues;

public class Persona {
	private long id;
	private String nombre;
	private String apellido;
	private String telefono;
	private DireccionFisica direccion;
	private String correo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public ContentValues getValues() {
		ContentValues values = new ContentValues();
		values.put(PersonColumns.FIRSTNAME, getNombre());
		values.put(PersonColumns.LASTNAME, getApellido());
		return values;
	}
}

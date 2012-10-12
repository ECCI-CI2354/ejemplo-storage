package cr.ac.ucr.ecci.ci2354.android.ejemplostorage.data;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import cr.ac.ucr.ecci.ci2354.android.ejemplostorage.DBConstants;
import cr.ac.ucr.ecci.ci2354.android.ejemplostorage.EjemploOpenHelper;
import cr.ac.ucr.ecci.ci2354.android.ejemplostorage.bo.Persona;

public class PersonaDao implements DBConstants {
	EjemploOpenHelper openHelper;

	public PersonaDao(Context context) {
		openHelper = new EjemploOpenHelper(context);
	}

	public Persona getPersona(long id) {
		Persona persona = new Persona();

		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(PERSON_TABLE_NAME, new String[] {
				PersonColumns.FIRSTNAME, PersonColumns.LASTNAME },
				PersonColumns.ID + " = ?", new String[] { String.valueOf(id) },
				null, null, null);

		if (cursor.moveToFirst()) {
			String nombre = cursor.getString(cursor
					.getColumnIndex(PersonColumns.FIRSTNAME));
			String apellido = cursor.getString(cursor
					.getColumnIndex(PersonColumns.LASTNAME));

			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setId(id);
		}
		cursor.close();
		db.close();
		return persona;
	}

	public Persona getPersonaPorNombre(String consultaNombre) {
		Persona persona = new Persona();

		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(PERSON_TABLE_NAME, new String[] {
				PersonColumns.ID, PersonColumns.FIRSTNAME,
				PersonColumns.LASTNAME }, PersonColumns.FIRSTNAME + " like ?",
				new String[] { consultaNombre }, null, null, null);

		if (cursor.moveToFirst()) {
			String nombre = cursor.getString(cursor
					.getColumnIndex(PersonColumns.FIRSTNAME));
			String apellido = cursor.getString(cursor
					.getColumnIndex(PersonColumns.LASTNAME));
			long id = cursor.getLong(cursor.getColumnIndex(PersonColumns.ID));
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setId(id);
		}
		cursor.close();
		db.close();
		return persona;
	}

	public List<Persona> getAllPersona() {
		List<Persona> personas = new ArrayList<Persona>();
		SQLiteDatabase db = openHelper.getReadableDatabase();
		Cursor cursor = db.query(PERSON_TABLE_NAME, new String[] {
				PersonColumns.ID, PersonColumns.FIRSTNAME,
				PersonColumns.LASTNAME }, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				String nombre = cursor.getString(cursor
						.getColumnIndex(PersonColumns.FIRSTNAME));
				String apellido = cursor.getString(cursor
						.getColumnIndex(PersonColumns.LASTNAME));
				Persona persona = new Persona();
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				long id = cursor.getLong(cursor
						.getColumnIndex(PersonColumns.ID));
				persona.setId(id);
				personas.add(persona);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return personas;
	}

	public boolean salvarPersona(Persona persona) {
		SQLiteDatabase db = openHelper.getWritableDatabase();

		long id = db.insert(PERSON_TABLE_NAME, null, persona.getValues());

		if (id != -1) {
			Log.d("", "Datos insertados");
			persona.setId(id);
			return true;
		}
		db.close();
		return false;
	}

	public void actualizarPersona(Persona persona) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int affectedRows = db.update(PERSON_TABLE_NAME, persona.getValues(),
				PersonColumns.ID + " = ?",
				new String[] { String.valueOf(persona.getId()) });
		if (affectedRows > 0) {
			Log.d("", "Datos actualizados");
		}
		db.close();
	}

	public boolean removerPersona(Persona persona) {
		SQLiteDatabase db = openHelper.getWritableDatabase();
		int affectedRows = db.delete(PERSON_TABLE_NAME, PersonColumns.ID
				+ " = ?", new String[] { String.valueOf(persona.getId()) });
		if (affectedRows > 0) {
			Log.d("", "Datos borrados");
			return true;
		}
		return false;
	}
}

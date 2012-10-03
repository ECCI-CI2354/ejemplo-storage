package cr.ac.ucr.ecci.ci2354.android.ejemplostorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends Activity implements DBConstants {
	TextView textToSave;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textToSave = (TextView) findViewById(R.id.text_to_save);

		// SharedPreferences prefs =
		// getSharedPreferences("misprefs",MODE_PRIVATE);
		//
		// String mensaje = prefs.getString("mensaje", "Hola");
		// textToSave.setText(mensaje);
	}

	public void saveText(View view) {
		String text = textToSave.getText().toString();

		// SHARED PREFERENCES
		// SharedPreferences prefs =
		// getSharedPreferences("misprefs",MODE_PRIVATE);
		// Editor editor = prefs.edit();
		// editor.putString("mensaje", text);
		// editor.commit();

		// INTERNAL STORAGE
		// Preferencias prefs = new Preferencias();
		// prefs.setMensaje(text);
		// Gson gson = new Gson();
		// String json = gson.toJson(prefs);
		//
		// try {
		// FileOutputStream out = openFileOutput("myprefs",
		// Context.MODE_PRIVATE);
		//
		// out.write(json.getBytes());
		// } catch (FileNotFoundException e) {
		// Log.e("", e.getMessage());
		// } catch (IOException e) {
		// Log.e("", e.getMessage());
		// }

		// EXTERNAL STORAGE
		Preferencias prefs = new Preferencias();
		prefs.setMensaje(text);
		Gson gson = new Gson();
		String json = gson.toJson(prefs);

		try {
			// external storage api 7-
			// File root = Environment.getExternalStorageDirectory();

			// external storage api 8+
			File root = getExternalFilesDir(null);

			Log.d("", "Raiz del external storage" + root.getAbsolutePath());

			File fout = new File(root, "external_prefs_api8");

			FileOutputStream out = new FileOutputStream(fout);

			out.write(json.getBytes());
		} catch (FileNotFoundException e) {
			Log.e("", e.getMessage());
		} catch (IOException e) {
			Log.e("", e.getMessage());
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		textToSave.setText("");
	}

	@Override
	protected void onResume() {
		super.onResume();
		EjemploOpenHelper helper = new EjemploOpenHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query(PERSON_TABLE_NAME, new String[] {
				PersonColumns.FIRSTNAME, PersonColumns.LASTNAME }, null, null,
				null, null, null);

		if (cursor.moveToFirst()) {
			String nombre = cursor.getString(cursor
					.getColumnIndex(PersonColumns.FIRSTNAME));
			String apellido = cursor.getString(cursor
					.getColumnIndex(PersonColumns.LASTNAME));
			Toast.makeText(this, "Persona: " + nombre + " " + apellido,
					Toast.LENGTH_LONG).show();
		}

		// INternal storage
		// try {
		// FileInputStream in = openFileInput("myprefs");
		// Gson gson = new Gson();
		// Preferencias prefs = gson.fromJson(new InputStreamReader(in),
		// Preferencias.class);
		// if (!TextUtils.isEmpty(prefs.getMensaje())) {
		// textToSave.setText(prefs.getMensaje());
		// }
		// } catch (FileNotFoundException e) {
		// Log.e("", e.getMessage());
		// }

		// external storage api 7-
		// File root = Environment.getExternalStorageDirectory();

		// external storage api 8+
		File root = getExternalFilesDir(null);
		File fin = new File(root, "external_prefs_api8");
		try {
			FileInputStream in = new FileInputStream(fin);
			Gson gson = new Gson();
			Preferencias prefs = gson.fromJson(new InputStreamReader(in),
					Preferencias.class);
			if (!TextUtils.isEmpty(prefs.getMensaje())) {
				textToSave.setText(prefs.getMensaje());
			}
		} catch (FileNotFoundException e) {
			Log.e("", e.getMessage());
		}

	}

}

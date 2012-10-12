package cr.ac.ucr.ecci.ci2354.android.ejemplostorage;

import java.util.List;

import android.R.menu;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cr.ac.ucr.ecci.ci2354.android.ejemplostorage.bo.Persona;
import cr.ac.ucr.ecci.ci2354.android.ejemplostorage.data.PersonaDao;

public class MainActivity extends Activity implements DBConstants {
	TextView nombre;
	TextView apellido;
	ListView personasLista;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nombre = (TextView) findViewById(R.id.nombre);
		apellido = (TextView) findViewById(R.id.apellido);
		personasLista = (ListView) findViewById(R.id.personas_lista);
		personasLista.setAdapter(new PersonaListAdapter());

		personasLista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, int position, long id) {
				Log.d("", "Borrando persona");
				Persona persona = (Persona) adapterView.getAdapter().getItem(
						position);
				new PersonaTask(true).execute(persona);
				return true;
			}
		});
		// SharedPreferences prefs =
		// getSharedPreferences("misprefs",MODE_PRIVATE);
		//
		// String mensaje = prefs.getString("mensaje", "Hola");
		// textToSave.setText(mensaje);
	}

	public void saveText(View view) {
		Persona persona = new Persona();
		persona.setNombre(nombre.getText().toString());
		persona.setApellido(apellido.getText().toString());
		new PersonaTask(false).execute(persona);
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
		// Preferencias prefs = new Preferencias();
		// prefs.setMensaje(text);
		// Gson gson = new Gson();
		// String json = gson.toJson(prefs);
		//
		// try {
		// // external storage api 7-
		// // File root = Environment.getExternalStorageDirectory();
		//
		// // external storage api 8+
		// File root = getExternalFilesDir(null);
		//
		// Log.d("", "Raiz del external storage" + root.getAbsolutePath());
		//
		// File fout = new File(root, "external_prefs_api8");
		//
		// FileOutputStream out = new FileOutputStream(fout);
		//
		// out.write(json.getBytes());
		// } catch (FileNotFoundException e) {
		// Log.e("", e.getMessage());
		// } catch (IOException e) {
		// Log.e("", e.getMessage());
		// }

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		new ActualizarPersona().execute();
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
		// File root = getExternalFilesDir(null);
		// File fin = new File(root, "external_prefs_api8");
		// try {
		// FileInputStream in = new FileInputStream(fin);
		// Gson gson = new Gson();
		// Preferencias prefs = gson.fromJson(new InputStreamReader(in),
		// Preferencias.class);
		// if (!TextUtils.isEmpty(prefs.getMensaje())) {
		// textToSave.setText(prefs.getMensaje());
		// }
		// } catch (FileNotFoundException e) {
		// Log.e("", e.getMessage());
		// }

	}

	private class PersonaTask extends AsyncTask<Persona, Void, Boolean> {
		boolean success;
		boolean borrar;

		public PersonaTask(boolean borrar) {
			this.borrar = borrar;
		}

		@Override
		protected Boolean doInBackground(Persona... params) {
			PersonaDao dao = new PersonaDao(MainActivity.this);

			Persona persona = params[0];

			if (persona != null) {
				if (borrar) {
					return dao.removerPersona(persona);
				} else {
					return dao.salvarPersona(persona);
				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result != null && result) {
				String mensaje = "Persona Salvada";

				if (borrar) {
					mensaje = "Persona borrada";
				}
				Toast.makeText(MainActivity.this, mensaje, Toast.LENGTH_SHORT)
						.show();

				ActualizarPersona task = new ActualizarPersona();
				task.execute();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
		}

	}

	private class ActualizarPersona extends
			AsyncTask<Void, Void, List<Persona>> {

		@Override
		protected List<Persona> doInBackground(Void... params) {
			PersonaDao dao = new PersonaDao(MainActivity.this);
			return dao.getAllPersona();
		}

		@Override
		protected void onPostExecute(List<Persona> result) {
			PersonaListAdapter adapter = (PersonaListAdapter) personasLista
					.getAdapter();
			adapter.setPersonas(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

	}
}

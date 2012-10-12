package cr.ac.ucr.ecci.ci2354.android.ejemplostorage;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cr.ac.ucr.ecci.ci2354.android.ejemplostorage.bo.Persona;

public class PersonaListAdapter extends BaseAdapter {
	List<Persona> personas;

	@Override
	public int getCount() {
		if (personas != null) {
			return personas.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (personas != null) {
			return personas.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PersonaHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) parent.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.persona_lista_item, parent,
					false);
			holder = new PersonaHolder();
			convertView.setTag(holder);
			holder.nombre = (TextView) convertView
					.findViewById(R.id.persona_nombre);
			holder.apellido = (TextView) convertView
					.findViewById(R.id.persona_apellido);
		} else {
			holder = (PersonaHolder) convertView.getTag();
		}
		Persona persona = personas.get(position);
		holder.nombre.setText(persona.getNombre());
		holder.apellido.setText(persona.getApellido());

		return convertView;
	}

	static class PersonaHolder {
		TextView nombre;
		TextView apellido;
	}
}

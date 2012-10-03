package cr.ac.ucr.ecci.ci2354.android.ejemplostorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EjemploOpenHelper extends SQLiteOpenHelper implements DBConstants {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ejemplo.db";

	public EjemploOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + PERSON_TABLE_NAME + " ( "
				+ PersonColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ PersonColumns.FIRSTNAME + " TEXT, "
				+ PersonColumns.LASTNAME
				+ " TEXT" + ")");
		Log.d("", "Base de datos creada");
		
		db.execSQL("INSERT INTO " + PERSON_TABLE_NAME +"("
				+ PersonColumns.FIRSTNAME+","+PersonColumns.LASTNAME+")"
				+ " VALUES(\"John\",\"Doe\")");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

package cr.ac.ucr.ecci.ci2354.android.ejemplostorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EjemploOpenHelper extends SQLiteOpenHelper implements DBConstants {
	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "ejemplo.db";

	public EjemploOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + PERSON_TABLE_NAME + " ( "
				+ PersonColumns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ PersonColumns.FIRSTNAME + " TEXT, " + PersonColumns.LASTNAME
				+ " TEXT" + PersonColumns.EMAIL + " TEXT" + PersonColumns.PHONE
				+ " TEXT" + ")");
		Log.d("", "Base de datos creada");

		db.execSQL("INSERT INO " + PERSON_TABLE_NAME + "("
				+ PersonColumns.FIRSTNAME + "," + PersonColumns.LASTNAME + ")"
				+ " VALUES(\"John\",\"Doe\")");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		int i = oldVersion;
		if (newVersion > i) {
			updateDBVersion1(db);
		}
		i++;
		if (newVersion > i) {
			updateDBVersion2(db);
		}
	}

	private void updateDBVersion1(SQLiteDatabase db) {
		db.execSQL("ALTER TABLE " + PERSON_TABLE_NAME + " ADD COLUMN "
				+ PersonColumns.EMAIL + " TEXT");
	}

	private void updateDBVersion2(SQLiteDatabase db) {
		db.execSQL("ALTER TABLE " + PERSON_TABLE_NAME + " ADD COLUMN "
				+ PersonColumns.PHONE + " TEXT");
	}

}

package cr.ac.ucr.ecci.ci2354.android.ejemplostorage;

public interface DBConstants {
	String PERSON_TABLE_NAME = "persona";

	static interface PersonColumns {
		String ID = "id";
		String FIRSTNAME = "nombre";
		String LASTNAME = "appellidos";
		String EMAIL = "correo";
		String PHONE = "telefono";
		String CITY = "ciudad";
		String STATEPROVINCE = "estadoprovincia";
		String COUNTRY = "pais";
	}

	String[] PERSON_COLUMNS = new String[] { PersonColumns.ID,
			PersonColumns.FIRSTNAME, PersonColumns.LASTNAME,
			PersonColumns.EMAIL, PersonColumns.PHONE, PersonColumns.CITY,
			PersonColumns.STATEPROVINCE, PersonColumns.COUNTRY };

	String CITY_TABLE_NAME = "ciudad";

	static interface CityColumns {
		String ID = "id";
		String NAME = "name";
	}

	String STATE_PROVINCE_TABLE_NAME = "estadoprovincia";

	static interface ProvinceColumns {
		String ID = "id";
		String NAME = "name";
	}

	String COUNTRY_TABLE_NAMME = "pais";

	static interface CountryColumns {
		String ID = "id";
		String NAME = "name";
	}

}

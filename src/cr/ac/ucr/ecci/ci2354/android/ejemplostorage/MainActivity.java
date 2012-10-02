package cr.ac.ucr.ecci.ci2354.android.ejemplostorage;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
		File root = getExternalFilesDir(null);
		Log.d("", root.getAbsolutePath());

		super.onResume();
	}

}

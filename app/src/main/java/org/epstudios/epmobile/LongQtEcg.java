package org.epstudios.epmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class LongQtEcg extends EpActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.longqtecg);
        super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent parentActivityIntent = new Intent(this, LongQtList.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

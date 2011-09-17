/*  EP Mobile -- Mobile tools for electrophysiologists
    Copyright (C) 2011 EP Studios, Inc.
    www.epstudiossoftware.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */   

package org.epstudios.epmobile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;


public class Chads extends EpActivity implements OnClickListener {
		@Override
		protected void onCreate(Bundle savedInstanceState)  {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.chads);
			
			View calculateButton = findViewById(R.id.calculate_button);
	        calculateButton.setOnClickListener(this);
	        View clearButton = findViewById(R.id.clear_button);
	        clearButton.setOnClickListener(this);
			
			checkBox = new CheckBox[5];
			
			checkBox[0] = (CheckBox) findViewById(R.id.chf);
			checkBox[1] = (CheckBox) findViewById(R.id.hypertension);
			checkBox[2] = (CheckBox) findViewById(R.id.age75);
			checkBox[3] = (CheckBox) findViewById(R.id.diabetes);
			checkBox[4] = (CheckBox) findViewById(R.id.stroke);
		}
		
		private CheckBox[] checkBox;
		
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.calculate_button:
				calculateResult();
				break;
			case R.id.clear_button:
				clearEntries();
				break;
			}
		}
		
		private void calculateResult() {
			int result = 0;
			for (int i = 0; i < checkBox.length; i++) {
				if (checkBox[i].isChecked()) {
					if (i == 4)		// stroke = 2 points
						result = result + 2;
					else 
						result++;
				}
			}
			displayResult(result);
		}

		private void displayResult(int result) {
			AlertDialog dialog = new AlertDialog.Builder(this).create();
			String message;
			if (result < 1)
				message = getString(R.string.low_chads_message);
			else if (result == 1)
				message = getString(R.string.medium_chads_message);
			else
				message = getString(R.string.high_chads_message);
			String risk = "";
			switch (result) {
			case 0:
				risk = "1.9";
				break;
			case 1:
				risk = "2.8";
				break;
			case 2:
				risk = "4.0";
				break;
			case 3:
				risk = "5.9";
				break;
			case 4:
				risk = "8.5";
				break;
			case 5:
				risk = "12.5";
				break;
			case 6:
				risk = "18.2";
				break;
			}
			risk = "Annual stroke risk is " + risk + "%";
			
			dialog.setMessage("CHADS\u2082 score = " + result
					+ "\n" + message + "\n" + risk + 
					"\nReference: Gage BF et al. JAMA 2001 285:2864.");
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Reset",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							clearEntries();
						}
					});
			dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Don't Reset",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {}
					});
			dialog.setTitle(getString(R.string.chads_title));
		
			dialog.show();
		}
		
		private void clearEntries() {
			for (int i = 0; i < checkBox.length; i++)
				checkBox[i].setChecked(false);
		}

}
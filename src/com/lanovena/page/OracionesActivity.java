package com.lanovena.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lanovena.DashboardActivity;
import com.lanovena.R;
import com.lanovena.android.LaNovenaSimpleAbstractActivity;

public class OracionesActivity extends LaNovenaSimpleAbstractActivity {

	public Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_oraciones);

		setUpAds();
	}

	// Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.section_menu, menu);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.go_home),
				MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.go_back),
				MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.go_home:
			mIntent = new Intent(this, DashboardActivity.class);
			finish();
			startActivityForResult(mIntent, 0);
			return true;
		case R.id.go_back:
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}

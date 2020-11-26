package com.lanovena.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.adsdk.sdk.Ad;
import com.lanovena.DashboardActivity;
import com.lanovena.NovenaActivity;
import com.lanovena.R;
import com.lanovena.android.LaNovenaSimpleAbstractActivity;

public class GozosActivity extends LaNovenaSimpleAbstractActivity {

	public Bundle bExtras;
	public TextView title;
	public Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gozos);

		// MobFox Ad integration
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
			mIntent = new Intent(this, NovenaActivity.class);
			finish();
			startActivityForResult(mIntent, 0);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void adClicked() {
		// TODO Auto-generated method stub

	}

	@Override
	public void adClosed(Ad arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void adLoadSucceeded(Ad arg0) {
		layout.setVisibility(View.VISIBLE);

	}

	@Override
	public void adShown(Ad arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void noAdFound() {
		// TODO Auto-generated method stub

	}
}

package com.lanovena.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.adsdk.sdk.Ad;
import com.adsdk.sdk.AdListener;
import com.adsdk.sdk.banner.AdView;
import com.lanovena.DashboardActivity;
import com.lanovena.R;
import com.lanovena.util.LaNovenaConstants;

public abstract class LaNovenaAbstractListActivity extends ListActivity
		implements AdListener {
	
	public static final String TAG = "LaNovenaAbstractListActivity";

	// Reusable intent
	public Intent mIntent;
	public Bundle bundle;
	public AdView mAdView;
	public LinearLayout layout;
	
	// MobFox Ad integration
	public void setUpAds(){
		if (!LaNovenaConstants.IS_PAID_VERSION) {
			Log.d(TAG, "setUpAds");			
			layout = (LinearLayout) findViewById(R.id.adsdkContent);
			if (mAdView == null) {
				mAdView = new AdView(this, "http://my.mobfox.com/request.php",
						getResources().getString(R.string.publisherId), true,
						true);
				mAdView.setAdListener(this);
				layout.addView(mAdView);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.category_menu, menu);
		MenuItemCompat.setShowAsAction(menu.findItem(R.id.go_home),
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

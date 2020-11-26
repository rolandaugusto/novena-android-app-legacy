package com.lanovena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.adsdk.sdk.Ad;
import com.adsdk.sdk.AdListener;
import com.adsdk.sdk.banner.AdView;
import com.lanovena.util.LaNovenaConstants;

public class DashboardActivity extends Activity implements AdListener {

    private Intent mIntent;
    private AdView mAdView;
    private RelativeLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.dashboard);

	layout = (RelativeLayout) findViewById(R.id.adsdkContent);
	
	if(!LaNovenaConstants.IS_PAID_VERSION)
		if (mAdView == null) {
		    setUpAds();
		}

	// attach event handler to dash buttons
	DashboardClickListener dBClickListener = new DashboardClickListener();
	findViewById(R.id.dashboard_button_novena).setOnClickListener(
		dBClickListener);
	findViewById(R.id.dashboard_button_villancicos).setOnClickListener(
		dBClickListener);
	findViewById(R.id.dashboard_button_recetas).setOnClickListener(
		dBClickListener);
	findViewById(R.id.dashboard_button_info).setOnClickListener(
		dBClickListener);

    }
    
    private void setUpAds(){
		mAdView = new AdView(this, "http://my.mobfox.com/request.php",
				getResources().getString(R.string.publisherId), true, true);
		mAdView.setAdListener(this);
		layout.addView(mAdView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	MenuInflater inflater = getMenuInflater();
	inflater.inflate(R.menu.dashboard, menu);
	MenuItemCompat.setShowAsAction(menu.findItem(R.id.menu_share),
		MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle item selection
	switch (item.getItemId()) {

	case R.id.menu_share:
	    mIntent = new Intent(android.content.Intent.ACTION_SEND);
	    mIntent.setType("text/plain");
	    mIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
		    getResources().getString(R.string.recommendation_subject));
	    mIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources()
		    .getString(R.string.recommendation_body));
	    startActivity(mIntent);

	    return true;
	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
	if (keyCode == KeyEvent.KEYCODE_BACK) {
	    moveTaskToBack(true);
	    return true;
	}
	return false;
    }

    private class DashboardClickListener implements OnClickListener {
	@Override
	public void onClick(View v) {
	    Intent i = null;
	    switch (v.getId()) {
	    case R.id.dashboard_button_novena:
		i = new Intent(DashboardActivity.this, NovenaActivity.class);
		break;
	    case R.id.dashboard_button_villancicos:
		i = new Intent(DashboardActivity.this, VillancicoActivity.class);
		break;
	    case R.id.dashboard_button_recetas:
		i = new Intent(DashboardActivity.this, RecipeActivity.class);
		break;
	    case R.id.dashboard_button_info:
		i = new Intent(DashboardActivity.this, InfoActivity.class);
		break;
	    default:
		break;
	    }
	    if (i != null) {
		startActivity(i);
	    }
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

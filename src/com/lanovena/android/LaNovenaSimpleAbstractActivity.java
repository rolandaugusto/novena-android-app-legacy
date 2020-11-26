/**
 * 
 */
package com.lanovena.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.adsdk.sdk.Ad;
import com.adsdk.sdk.AdListener;
import com.adsdk.sdk.banner.AdView;
import com.lanovena.R;
import com.lanovena.util.LaNovenaConstants;

/**
 * @author Roland
 * 
 */
public class LaNovenaSimpleAbstractActivity extends Activity implements
		AdListener {
	
	public static final String TAG = "LaNovenaSimpleAbstractActivity";

	public Bundle bExtras;
	public AdView mAdView;
	public LinearLayout layout;

	public void setUpAds() {
		if (!LaNovenaConstants.IS_PAID_VERSION) {
			Log.d(TAG, "setUpAds");
			// MobFox Ad integration
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adsdk.sdk.AdListener#adClicked()
	 */
	@Override
	public void adClicked() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adsdk.sdk.AdListener#adClosed(com.adsdk.sdk.Ad, boolean)
	 */
	@Override
	public void adClosed(Ad arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adsdk.sdk.AdListener#adLoadSucceeded(com.adsdk.sdk.Ad)
	 */
	@Override
	public void adLoadSucceeded(Ad arg0) {
		layout.setVisibility(View.VISIBLE);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adsdk.sdk.AdListener#adShown(com.adsdk.sdk.Ad, boolean)
	 */
	@Override
	public void adShown(Ad arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.adsdk.sdk.AdListener#noAdFound()
	 */
	@Override
	public void noAdFound() {
		// TODO Auto-generated method stub

	}

}

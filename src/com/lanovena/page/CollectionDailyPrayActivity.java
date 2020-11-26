package com.lanovena.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lanovena.R;
import com.lanovena.android.LaNovenaAbstractFragmentActivity;
import com.lanovena.util.LaNovenaConstants;

public class CollectionDailyPrayActivity extends
		LaNovenaAbstractFragmentActivity {

	public static String[] dailyTitles;
	public static String[] dailyBodies;
	public static Button oracionesButton;

	DaysCollectionPagerAdapter mDaysCollectionPagerAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_daily_pray);		
		
		// MobFox Ad integration
		setUpAds();		
		
		dailyTitles = getResources().getStringArray(R.array.daily_names);
		dailyBodies = getResources().getStringArray(R.array.daily_bodies);
		bundle = getIntent().getExtras();
		if (bundle != null) {
			selectedValue = bundle
					.getInt(LaNovenaConstants.SELECTED_DAILY_PRAY);
		}

		// Create an adapter that when requested, will return a fragment
		// representing an object in
		// the collection.
		//
		// ViewPager and its adapters use support library fragments, so we must
		// use
		// getSupportFragmentManager.
		mDaysCollectionPagerAdapter = new DaysCollectionPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager, attaching the adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mDaysCollectionPagerAdapter);
		mViewPager.setCurrentItem(selectedValue);
	}

	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public static class DaysCollectionPagerAdapter extends
			FragmentStatePagerAdapter {

		public DaysCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new DayObjectFragment();
			Bundle args = new Bundle();
			args.putInt(DayObjectFragment.ARG_OBJECT, i);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return dailyTitles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return dailyTitles[position];
		}
	}

	/**
	 * A fragment representing a section of the app, displays the correspondent
	 * content.
	 */
	public static class DayObjectFragment extends Fragment {

		public static final String ARG_OBJECT = "object";
		public TextView reusableText;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_daily_pray,
					container, false);
			Bundle args = getArguments();
			final int position = args.getInt(ARG_OBJECT);

			reusableText = (TextView) rootView.findViewById(R.id.daily_body);
			reusableText.setText(dailyBodies[position].toString());
			
			oracionesButton = (Button) rootView.findViewById(R.id.button_oraciones);
			oracionesButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent mIntent = new Intent(v.getContext(),
							OracionesActivity.class);
						
						startActivityForResult(mIntent, 0);
				}
			});

			return rootView;
		}
	}

}

package com.lanovena.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanovena.R;
import com.lanovena.android.LaNovenaAbstractFragmentActivity;
import com.lanovena.util.LaNovenaConstants;

public class CollectionDaysActivity extends LaNovenaAbstractFragmentActivity {

	public static String[] dayNames;
	public static String[] dayTexts;

	DaysCollectionPagerAdapter mDaysCollectionPagerAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_days);

		// MobFox Ad integration
		setUpAds();

		dayNames = getResources().getStringArray(R.array.diasNovena);
		dayTexts = getResources().getStringArray(R.array.textoDelDia);

		bundle = getIntent().getExtras();
		if (bundle != null) {
			selectedValue = bundle.getInt(LaNovenaConstants.SELECTED_DAY);
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
			return dayNames.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return dayNames[position];
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
			View rootView = inflater.inflate(R.layout.fragment_day, container,
					false);
			Bundle args = getArguments();
			final int position = args.getInt(ARG_OBJECT);

			reusableText = (TextView) rootView.findViewById(R.id.current_day_p);
			reusableText.setText(dayTexts[position].toString());

			return rootView;
		}
	}

}

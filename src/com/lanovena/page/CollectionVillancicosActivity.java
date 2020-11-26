package com.lanovena.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lanovena.R;
import com.lanovena.android.LaNovenaAbstractFragmentActivity;
import com.lanovena.util.LaNovenaConstants;
import com.lanovena.util.Tool;

public class CollectionVillancicosActivity extends
		LaNovenaAbstractFragmentActivity {

	public static View linkToYoutube;

	public static String[] villancicosTitles;
	public static String[] villancicoLyrics;
	public static String[] villancicoLinks;

	VillancicosCollectionPagerAdapter mVillancicosCollectionPagerAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_villancicos);

		// MobFox Ad integration
		setUpAds();

		villancicosTitles = getResources().getStringArray(
				R.array.villancicos_titles);
		villancicoLyrics = getResources().getStringArray(
				R.array.villancicos_lyrics);
		villancicoLinks = getResources().getStringArray(
				R.array.villancicos_links);
		mContext = getApplicationContext();

		bundle = getIntent().getExtras();
		if (bundle != null) {
			selectedValue = bundle
					.getInt(LaNovenaConstants.SELECTED_VILLANCICO);
		}

		// Create an adapter that when requested, will return a fragment
		// representing an object in
		// the collection.
		//
		// ViewPager and its adapters use support library fragments, so we must
		// use
		// getSupportFragmentManager.
		mVillancicosCollectionPagerAdapter = new VillancicosCollectionPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager, attaching the adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mVillancicosCollectionPagerAdapter);
		mViewPager.setCurrentItem(selectedValue);
	}

	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public static class VillancicosCollectionPagerAdapter extends
			FragmentStatePagerAdapter {

		public VillancicosCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new VillancicoObjectFragment();
			Bundle args = new Bundle();
			args.putInt(VillancicoObjectFragment.ARG_OBJECT, i);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return villancicosTitles.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return villancicosTitles[position];
		}
	}

	/**
	 * A fragment representing a section of the app, displays the correspondent
	 * content.
	 */
	public static class VillancicoObjectFragment extends Fragment {

		public static final String ARG_OBJECT = "object";
		public TextView reusableText;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_villancico,
					container, false);
			Bundle args = getArguments();
			final int position = args.getInt(ARG_OBJECT);
			linkToYoutube = rootView.findViewById(R.id.linkToVideo);

			linkToYoutube.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (Tool.isOnline(mContext)) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri
								.parse("vnd.youtube://"
										+ villancicoLinks[position].toString())));

					} else {
						Toast.makeText(
								mContext,
								getResources().getString(
										R.string.no_internet_conection),
								Toast.LENGTH_SHORT).show();
					}

				}
			});

			reusableText = (TextView) rootView.findViewById(R.id.villancico_p);
			reusableText.setText(villancicoLyrics[position].toString());

			return rootView;
		}
	}

}

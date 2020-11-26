package com.lanovena.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanovena.R;
import com.lanovena.android.LaNovenaAbstractFragmentActivity;
import com.lanovena.util.LaNovenaConstants;

public class CollectionRecipesActivity extends LaNovenaAbstractFragmentActivity {

	public static ImageView imageView;
	static final int[] recetas_ims = LaNovenaConstants.mRecetasImgIds;
	public static String[] recipes_names;
	public static String[] recipes_ingredientes;
	public static String[] recipes_preparation;

	RecipesCollectionPagerAdapter mRecipesCollectionPagerAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection_recipes);

		// MobFox Ad integration
		setUpAds();

		recipes_names = getResources().getStringArray(R.array.lista_recetas);
		recipes_ingredientes = getResources().getStringArray(
				R.array.recetas_ingredientes);
		recipes_preparation = getResources().getStringArray(
				R.array.recetas_preparacion);

		bundle = getIntent().getExtras();
		if (bundle != null) {
			selectedValue = bundle.getInt(LaNovenaConstants.SELECTED_RECIPE);
		}

		// Create an adapter that when requested, will return a fragment
		// representing an object in
		// the collection.
		//
		// ViewPager and its adapters use support library fragments, so we must
		// use
		// getSupportFragmentManager.
		mRecipesCollectionPagerAdapter = new RecipesCollectionPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager, attaching the adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mRecipesCollectionPagerAdapter);
		mViewPager.setCurrentItem(selectedValue);
	}

	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public static class RecipesCollectionPagerAdapter extends
			FragmentStatePagerAdapter {

		public RecipesCollectionPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = new RecipeObjectFragment();
			Bundle args = new Bundle();
			args.putInt(RecipeObjectFragment.ARG_OBJECT, i);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			return recipes_names.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return recipes_names[position];
		}
	}

	/**
	 * A fragment representing a section of the app, displays the correspondent
	 * content.
	 */
	public static class RecipeObjectFragment extends Fragment {

		public static final String ARG_OBJECT = "object";
		public TextView reusableText;

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_recipe,
					container, false);
			Bundle args = getArguments();
			final int position = args.getInt(ARG_OBJECT);

			imageView = (ImageView) rootView
					.findViewById(R.id.receta_image_big);
			imageView.setImageResource(recetas_ims[position]);

			reusableText = (TextView) rootView
					.findViewById(R.id.receta_ingredientes);
			reusableText.setText(recipes_ingredientes[position].toString());

			reusableText = (TextView) rootView
					.findViewById(R.id.receta_preparacion);
			reusableText.setText(recipes_preparation[position].toString());

			return rootView;
		}
	}

}

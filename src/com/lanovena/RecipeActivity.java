package com.lanovena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lanovena.adapter.ListImgArrowAdapter;
import com.lanovena.android.LaNovenaAbstractListActivity;
import com.lanovena.page.CollectionRecipesActivity;
import com.lanovena.util.LaNovenaConstants;

public class RecipeActivity extends LaNovenaAbstractListActivity {
    
    public String[] listaRecetas;
    public int[] mRecetasImgIds;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_recetas);	
	
	//Initialize the ads
	setUpAds();
	
	listaRecetas = getResources().getStringArray(
		R.array.lista_recetas);
	
	mRecetasImgIds = LaNovenaConstants.mRecetasImgIds;
	
	setListAdapter(new ListImgArrowAdapter(this, R.layout.row_list_img_arrow, R.id.text1, R.id.list_icon_image, R.id.right_arrow, listaRecetas, mRecetasImgIds, null, true ));

	ListView lv = getListView();
	
	lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		mIntent = new Intent(view.getContext(),
			CollectionRecipesActivity.class);
		bundle = new Bundle();
		bundle.putInt(LaNovenaConstants.SELECTED_RECIPE, position);
		mIntent.putExtras(bundle);
		startActivityForResult(mIntent, 0);
		

	    }
	});
	
    }    
    
  
}

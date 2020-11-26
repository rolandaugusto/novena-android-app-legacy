package com.lanovena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lanovena.adapter.ListImgArrowAdapter;
import com.lanovena.android.LaNovenaAbstractListActivity;
import com.lanovena.page.CollectionVillancicosActivity;
import com.lanovena.util.LaNovenaConstants;

public class VillancicoActivity extends LaNovenaAbstractListActivity {
    
    public String[] listaVillancicos;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_villancicos);	
	
	//MobFox Ad integration
	setUpAds();
	
	listaVillancicos = getResources().getStringArray(
		R.array.villancicos_titles);
	
		
	setListAdapter(new ListImgArrowAdapter(this, R.layout.row_list_img_arrow, R.id.text1, R.id.list_icon_image, R.id.right_arrow, listaVillancicos, null, R.drawable.ic_music, false ));

	ListView lv = getListView();
	
	lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		mIntent = new Intent(view.getContext(),
			CollectionVillancicosActivity.class);
		
		bundle = new Bundle();
		bundle.putInt(LaNovenaConstants.SELECTED_VILLANCICO, position);
		mIntent.putExtras(bundle);
		startActivityForResult(mIntent, 0);
		
	    }
	});
	
    }
    
}

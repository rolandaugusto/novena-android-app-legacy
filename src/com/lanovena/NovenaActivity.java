package com.lanovena;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.lanovena.adapter.ListDiasNovenaAdapter;
import com.lanovena.android.LaNovenaAbstractListActivity;
import com.lanovena.page.CollectionDailyPrayActivity;
import com.lanovena.page.CollectionDaysActivity;
import com.lanovena.page.GozosActivity;
import com.lanovena.util.LaNovenaConstants;

public class NovenaActivity extends LaNovenaAbstractListActivity{
    
    public View dailyView;
    public View gozosView;
    public String[] diasNovena;
    public String[] fechasNovena = new String[9];
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_novena);
	
	//MobFox Ad integration
	setUpAds();	
	
	diasNovena = getResources().getStringArray(
		R.array.diasNovena);
	
	fechasNovena = getResources().getStringArray(
		R.array.fechasNovena);
	
	setListAdapter(new ListDiasNovenaAdapter(this, R.layout.row_dias, R.id.text1, R.id.text2, R.id.right_arrow, diasNovena, fechasNovena ));
	ListView lv = getListView();	
	
	NovenaClickListener nClickListener = new NovenaClickListener();
	dailyView = (View) findViewById(R.id.oraciones_diarias);
	dailyView.setOnClickListener(nClickListener);
	gozosView = (View) findViewById(R.id.gozos);
	gozosView.setOnClickListener(nClickListener);
	
		
	lv.setOnItemClickListener(new OnItemClickListener() {
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		mIntent = new Intent(view.getContext(),
			CollectionDaysActivity.class);
		bundle = new Bundle();
		bundle.putInt(LaNovenaConstants.SELECTED_DAY, position);
		mIntent.putExtras(bundle);
		startActivityForResult(mIntent, 0);
		
	    }
	});
	
    }
    
    private class NovenaClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent i = null;
	        switch (v.getId()) {
	            case R.id.oraciones_diarias:
	                i = new Intent(NovenaActivity.this, CollectionDailyPrayActivity.class);
	                break;
	            case R.id.gozos:
	                i = new Intent(NovenaActivity.this, GozosActivity.class);
	                break;
	           
	            default:
	                break;
	        }
	        if(i != null) {
	            startActivity(i);
	        }
			
		}
    	
    }

}

package com.lanovena;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lanovena.android.LaNovenaSimpleAbstractActivity;
import com.lanovena.util.LaNovenaConstants;

public class InfoActivity extends LaNovenaSimpleAbstractActivity{
    
   public TextView textView;
   private Intent mIntent;
   public Button donate1;
   public Button donate2;
   public Button donate3;
  
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_info);
	
	//MobFox Ad integration
	setUpAds();
	
	textView = (TextView) findViewById(R.id.version_info);
	donate1 = (Button) findViewById(R.id.donate1);
	donate2 = (Button) findViewById(R.id.donate2);
	donate3 = (Button) findViewById(R.id.donate3);
	
	try {
	    textView.setText("Version: " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
	    
	} catch (NameNotFoundException e) {
	   textView.setText(" - ");
	}
	
	donate1.setOnClickListener(new View.OnClickListener() {	    
	    @Override
	    public void onClick(View v) {
		openWebURL(LaNovenaConstants.DONATE_1);		
	    }
	});
	
	donate2.setOnClickListener(new View.OnClickListener() {	    
	    @Override
	    public void onClick(View v) {
		openWebURL(LaNovenaConstants.DONATE_2);		
	    }
	});
	
	donate3.setOnClickListener(new View.OnClickListener() {	    
	    @Override
	    public void onClick(View v) {
		openWebURL(LaNovenaConstants.DONATE_3);		
	    }
	});
	
    }
    
    public void openWebURL( String inURL ) {
	    Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );
	    startActivity( browse );
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.category_menu, menu);
        MenuItemCompat.setShowAsAction(menu.findItem(R.id.go_home), MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.go_home:
             mIntent = new Intent(this,
			DashboardActivity.class);
             finish();
            startActivityForResult(mIntent, 0);
            return true;
       
        default:
            return super.onOptionsItemSelected(item);
        }
    }


}

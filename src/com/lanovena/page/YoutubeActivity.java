package com.lanovena.page;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lanovena.DashboardActivity;
import com.lanovena.R;
import com.lanovena.util.LaNovenaConstants;

public class YoutubeActivity extends Activity {

    public TextView textView;
    public WebView webView;
    private Intent mIntent;
    public String link;
    private String villancico_title;
    private String villancico_lyrics;
    public ProgressDialog dialog;
    public Dialog dLyrics;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_youtube_video);
	
	Bundle extras = getIntent().getExtras();
	if(extras !=null) {
	   link = extras.getString(LaNovenaConstants.SELECTED_VILLANCICO_LINK);
	   villancico_title = extras.getString(LaNovenaConstants.SELECTED_VILLANCICO);
	   villancico_lyrics = extras.getString(LaNovenaConstants.SELECTED_VILLANCICO_LYRICS);
	}
	
	textView = (TextView) findViewById(R.id.villancico_title);
	textView.setText(villancico_title + " (video)");
	
	webView = (WebView) findViewById(R.id.youtube_view);
	webView.setWebViewClient(new MyWebViewClient());
	webView.getSettings().setJavaScriptEnabled(true);	
	webView.loadUrl(link);
	
	dLyrics = new Dialog(this);
	dLyrics.setContentView(R.layout.custom_dialog);
	dLyrics.setTitle(villancico_title);
	dLyrics.setCancelable(true);
	TextView text = (TextView) dLyrics.findViewById(R.id.text_vill_lyrics);
	text.setText(villancico_lyrics);
	
	dialog = ProgressDialog.show(this, "", 
	            "Cargando... Por favor espere", true);
	dialog.setCancelable(true);
	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.youtube_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.go_back:
            mIntent = new Intent(this, CollectionVillancicosActivity.class);
           startActivityForResult(mIntent, 0);
           return true;
           
        case R.id.go_home:
             mIntent = new Intent(this, DashboardActivity.class);
            startActivityForResult(mIntent, 0);
            return true;
            
        case R.id.menu_share:
            mIntent = new Intent(android.content.Intent.ACTION_SEND);
            mIntent.setType("text/plain");
            mIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.recommendation_subject));
            mIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.recommendation_body));
            startActivity(mIntent);

            return true;
            
        case R.id.menu_lyrics:
           dLyrics.show();
           return true;    
        
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    //Nested class
    private class MyWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	    
	    @Override
	    public void onPageFinished(WebView view, String url) {	    
	    super.onPageFinished(view, url);
	    dialog.hide();
	    }
	}

}

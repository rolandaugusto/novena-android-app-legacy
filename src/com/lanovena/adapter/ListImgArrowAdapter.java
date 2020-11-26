package com.lanovena.adapter;
 

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanovena.R;
 
public class ListImgArrowAdapter extends ArrayAdapter<Object>
{
    public Activity context;
    public String[] items;
    public int[] iconRowItems;
    public int layoutId;
    public int textId;
    public int imageId;
    public int iconId;
    public Integer singleIconId;
    public boolean border;
    
   
    /**
     * Constructor
     * @param context
     * @param layoutId
     * @param textViewId
     * @param iconViewId
     * @param imgArrowViewId
     * @param items
     * @param iconImgIds
     * @param singleIconId
     */
    public ListImgArrowAdapter(Activity context, int layoutId, int textViewId, int iconViewId, int imgArrowViewId, String[] items, int[] iconImgIds, Integer singleIconId, boolean border)
    {
        super(context, layoutId, items);
 
        this.context = context;
        this.items = items;
        this.layoutId = layoutId;
        this.textId = textViewId;
        this.imageId = imgArrowViewId;
        this.iconId = iconViewId;
        this.iconRowItems = iconImgIds;
        this.singleIconId = singleIconId;
        this.border = border;
    }
 
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View row=inflater.inflate(layoutId, null);
        
        ImageView icon =(ImageView)row.findViewById(iconId); 
        
        if(iconRowItems != null){
            icon.setImageResource(iconRowItems[pos]);
        }else if(singleIconId != null){
            icon.setImageResource(singleIconId);
	}
        if(border){
            icon.setBackgroundColor(context.getResources().getColor(R.color.black));
            icon.setPadding(1, 1, 1, 1);
        }
        TextView label=(TextView)row.findViewById(textId); 
        label.setText(items[pos]);        
        
        ImageView right_arrow=(ImageView)row.findViewById(imageId); 
        right_arrow.setImageResource(R.drawable.right_arrow);
          
 
        return(row);
    }
}
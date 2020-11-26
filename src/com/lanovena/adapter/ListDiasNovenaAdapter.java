package com.lanovena.adapter;
 

import java.util.Calendar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanovena.R;
import com.lanovena.util.LaNovenaConstants;
 
public class ListDiasNovenaAdapter extends ArrayAdapter<Object>{
	
    Activity context;
    String[] items;
    String[] fechasNovena;
    boolean[] arrows;
    int layoutId;
    int textId;
    int textSmallId;
    int imageId;
    TextView labelTop;
    TextView labelBottom;
    
    Calendar calendar;
    String mCurrentDateDisplay;
    int mDay;

    public ListDiasNovenaAdapter(Activity context, int layoutId, int textId, int textSmallId, int imageId, String[] items, String[] fechasNovena)
    {
        super(context, layoutId, items);
 
        this.context = context;
        this.items = items;
        this.layoutId = layoutId;
        this.textId = textId;
        this.textSmallId = textSmallId;
        this.imageId = imageId;
        this.fechasNovena = fechasNovena;
    }
 
    @SuppressWarnings("deprecation")
	public View getView(int pos, View convertView, ViewGroup parent){
	
		calendar = Calendar.getInstance();
		mDay = calendar.get(Calendar.DAY_OF_MONTH);
		mCurrentDateDisplay = mDay + " de Diciembre";

		LayoutInflater inflater = context.getLayoutInflater();
		View row = inflater.inflate(layoutId, null);

		labelTop = (TextView) row.findViewById(textId);
		labelTop.setText(items[pos]);

		labelBottom = (TextView) row.findViewById(textSmallId);
		labelBottom.setText(fechasNovena[pos]);
        
        if(mCurrentDateDisplay.equals(fechasNovena[pos]) && calendar.get(Calendar.MONTH) == 11){
        	row.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.list_selector_current_day));
            labelTop.append(" (" + LaNovenaConstants.HOY + ")");
            labelTop.setTextColor(context.getResources().getColor(R.color.currentDay));
            labelBottom.setTextColor(context.getResources().getColor(R.color.currentDaySmall));
        }
               
        ImageView icon=(ImageView)row.findViewById(imageId); 
        icon.setImageResource(R.drawable.right_arrow);
          
 
        return(row);
    }
}
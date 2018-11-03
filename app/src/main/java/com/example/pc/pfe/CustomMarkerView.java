package com.example.pc.pfe;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;


/**
 * Created by pc on 10/09/2018.
 */

public class CustomMarkerView extends MarkerView {
    private TextView tvContent;
    public CustomMarkerView (Context context, int layoutResource) {
        super(context, layoutResource);
        // this markerview only displays a textview
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    /*public TextView getTextView(){
        return this.tvContent;
    }
    public void setTextViewValue(String text){
        this.tvContent.setText(text);
    }
    @Override
    public void refreshContent(Entry e, int dataSetIndex) {

    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)

    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText("" + e.getVal()); // set the entry-value as the display text
    }

    @Override
    public int getXOffset() {
        // this will center the marker-view horizontally
        return -(getWidth() / 2);
    }

    @Override
    public int getYOffset() {
        // this will cause the marker-view to be above the selected value
        return -getHeight();
    }*/
}

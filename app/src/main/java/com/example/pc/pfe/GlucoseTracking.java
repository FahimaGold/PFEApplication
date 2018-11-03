package com.example.pc.pfe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GlucoseTracking extends AppCompatActivity {

    public Button sauvGlucose;
    private Button visualG;
    public void sauvegarderValeurDuGlucose(View v)
    {
        // TODO Auto-generated method stub
        sauvGlucose.setPaintFlags(sauvGlucose.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        //Save glucose value

    }


    public void visualiserGlucose(View v){
       Intent i = new Intent(getApplicationContext(),GlucoseGraphs.class);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_tracking);
        sauvGlucose = (Button)findViewById( R.id.sauvegarderGlucose );
        visualG = (Button) findViewById(R.id.btnVisualisationGlucose);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (toolbar != null)
        {
            toolbar.setTitle("PFEApp");
            setSupportActionBar(toolbar);//To display toolbar
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(true);
                ab.setDisplayShowHomeEnabled(true);
                ab.setDisplayShowTitleEnabled(false);


            }

        }
        GlucoseTrackingFragment glucoseTrackingFragment = new GlucoseTrackingFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.suiviGlucoseSection
                ,glucoseTrackingFragment)
                .commit();
    }
}

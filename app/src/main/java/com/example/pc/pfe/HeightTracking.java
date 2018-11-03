package com.example.pc.pfe;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HeightTracking extends AppCompatActivity {

    private Button sauvHeight;
    private Button visualT;
    public void sauvegarderValeurDeTaille(View v)
    {
        // TODO Auto-generated method stub
        sauvHeight.setPaintFlags(sauvHeight.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        //Save glucose value

    }

    public void visualiserTaille(View v){
        Intent i = new Intent(getApplicationContext(),HeightGraphs.class);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_height_tracking );
        visualT = (Button) findViewById(R.id.btnVisualisationTaille);
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
       HeightTrackingFragment heightTrackingFragment = new HeightTrackingFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.suiviTailleSection
                ,heightTrackingFragment)
                .commit();
    }
}

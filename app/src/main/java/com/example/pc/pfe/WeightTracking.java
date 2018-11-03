package com.example.pc.pfe;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class WeightTracking extends AppCompatActivity {

    public void visualiserPoids(View v){
        Intent i = new Intent(getApplicationContext(),WeightGraphs.class);
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
        setContentView( R.layout.activity_weight_tracking );
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
       WeightTrackingFragment weightTrackingFragment = new WeightTrackingFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.suiviPoidsSection
                ,weightTrackingFragment)
                .commit();
    }
}

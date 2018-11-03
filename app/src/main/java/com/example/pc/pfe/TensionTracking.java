package com.example.pc.pfe;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class TensionTracking extends AppCompatActivity {


    public Button sauvTension;
    private Button visualG;
    public void sauvegarderValeurDeTension(View v)
    {
        // TODO Auto-generated method stub
        sauvTension.setPaintFlags(sauvTension.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        //Save glucose value

    }

    public void visualiserTension(View v){
        Intent i = new Intent(getApplicationContext(),TensionGraphs.class);
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
        setContentView( R.layout.activity_tension_tracking );
         sauvTension = findViewById( R.id.sauvegarderTension );
        //visualG = (Button) findViewById(R.id.btnVisualisationGlucose);
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
        TensionTrackingFragment tensionTrackingFragment = new TensionTrackingFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.suiviTensionSection
                ,tensionTrackingFragment)
                .commit();

    }
}

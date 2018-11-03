package com.example.pc.pfe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Regime extends AppCompatActivity {


    public  void btnSuivantPetit(View v) {
        Intent i = new Intent(getApplicationContext(),Collation.class);
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
        setContentView( R.layout.activity_regime );

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //toolbar.setTitleTextColor(0xFFFFFFFF);


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
        BreakfastFragment breakfastFragmentFragment = new BreakfastFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.regimeSection
                ,breakfastFragmentFragment)
                .commit();


        AlertDialog.Builder builder = new AlertDialog.Builder(Regime.this);

        builder.setCancelable(true);
        builder.setTitle("Collation");
        builder.setMessage("Temps pour collation! Vous pouvez prendre de fruits ou d'oeufs");


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //alertTextView.setVisibility(View.VISIBLE);
            }
        });
        builder.show();


    }
}

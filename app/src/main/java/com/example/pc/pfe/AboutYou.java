package com.example.pc.pfe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class AboutYou extends AppCompatActivity {
    private Button aboutSec ;
    private Button suivreIns;
    public  Button getBtnForPart2(){
        return  this.suivreIns;
    }

    public void afficherAboutYouSecondPart(View v){
        aboutSec.setPaintFlags(aboutSec.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
       /* Intent i = new Intent(getApplicationContext(),AboutYouSecondPart.class);
        startActivity(i);*/

        /*AboutYouSecondPartFragment aboutYouSecondPartFragment = new AboutYouSecondPartFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.santeSectionPartTwo,aboutYouSecondPartFragment)
                .commit();*/
    }

    private Toolbar toolbar ;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_you);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        suivreIns = (Button) findViewById(R.id.suivantSecondPart);
        //toolbar.setTitleTextColor(0xFFFFFFFF);
        aboutSec = (Button) findViewById(R.id.suivantSecondPart);

        if (toolbar != null)
        {
            //toolbar.setTitle("PFEApp");
            setSupportActionBar(toolbar);//To display toolbar
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
               ab.setDisplayHomeAsUpEnabled(true);
               ab.setDisplayShowHomeEnabled(true);
               ab.setDisplayShowTitleEnabled(false);


            }

        }
       AboutYouFragment aboutYouFragment = new AboutYouFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.santeSection,aboutYouFragment)
                .commit();



    }
    }


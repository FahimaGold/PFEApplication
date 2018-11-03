package com.example.pc.pfe;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Paint;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {



    private  InscriptionFragment inscriptionFragment;
    private Button inscrEnd;
   public void allerAccueil(View v)
    {
        // TODO Auto-generated method stub

        inscrEnd.setPaintFlags(inscrEnd.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        inscriptionFragment.postDataToSQLite();



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        inscrEnd = (Button) findViewById( R.id.insrciptionTerm );

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

        inscriptionFragment = new InscriptionFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.inscriptionSection
                ,inscriptionFragment)
                .commit();



    }



}

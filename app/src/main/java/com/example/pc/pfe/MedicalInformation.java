package com.example.pc.pfe;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MedicalInformation extends AppCompatActivity {


    private Button modifier;

    public Button getModifierButton(){
        return this.modifier;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_medical_information );
        modifier = findViewById( R.id.informationsModif );
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (toolbar != null)
        {

            setSupportActionBar(toolbar);//To display toolbar

            toolbar.showOverflowMenu();
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(true);
                ab.setDisplayShowHomeEnabled(true);
                ab.setDisplayShowTitleEnabled(false);


            }

        }

        MedicalInformationFragment medicalInformationFragment = new MedicalInformationFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.informationsSection
                ,medicalInformationFragment)
                .commit();
    }
}

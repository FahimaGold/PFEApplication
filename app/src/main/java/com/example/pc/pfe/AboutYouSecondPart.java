package com.example.pc.pfe;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AboutYouSecondPart extends AppCompatActivity {
    private Button sante ;
    private Button suivreIns;
    private AboutYouSecondPartFragment aboutYouSecondPartFragment;
    public Button getBtnInsc(){
        return this.suivreIns;
    }
   /* public void afficherSanteSection(View v){
        Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();
        AboutYouFragment aboutYouFragment = new AboutYouFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.santeSection,aboutYouFragment)
                .commit();
        sante.setPaintFlags(sante.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }*/
    public void inscrire(View v)
    {
        // TODO Auto-generated method stub
        suivreIns.setPaintFlags(suivreIns.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


    }


    private Toolbar toolbar ;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about_you_second_part );
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        suivreIns = (Button) findViewById(R.id.suivantInscription);
        //toolbar.setTitleTextColor(0xFFFFFFFF);
        sante = (Button) findViewById(R.id.diabete);

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
        AboutYouSecondPartFragment aboutYouSecondPartFragment = new AboutYouSecondPartFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.santeSectionPartTwo,aboutYouSecondPartFragment)
                .commit();


    }
}

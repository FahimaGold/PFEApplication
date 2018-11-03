package com.example.pc.pfe;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CreateAccount extends AppCompatActivity {

   /* public void afficher(View v)
    {
        // TODO Auto-generated method stub

        Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();

    }*/
   private Button diabete ;
   private Button suivant;
   private DiabeteFragment diabeteFragment;

    public Button getBtnPart() {
        return this.suivant;
    }
   //Passer vers la prochaine section (suivi de santé _A propos de vous_ )
   public void suivreSante(View v)
   {
       // TODO Auto-generated method stub
       suivant.setPaintFlags(suivant.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
       //Intent i = new Intent(getApplicationContext(),AboutYou.class);
       //startActivity(i);

   }

   public void afficherDiabeteSection(View v){
       Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();
       diabeteFragment = new DiabeteFragment();
       android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
       manager.beginTransaction().replace(R.id.diabeteSection,diabeteFragment)
               .commit();
       diabete.setPaintFlags(diabete.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
   }
   private Toolbar toolbar ;
   private Button annuler;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //toolbar.setTitleTextColor(0xFFFFFFFF);
        diabete = (Button) findViewById(R.id.diabete);
        suivant = (Button) findViewById(R.id.suivant);

        if (toolbar != null)
        {
            //toolbar.setTitle("Suivi du Diabète");
            setSupportActionBar(toolbar);//To display toolbar
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(true);
                ab.setDisplayShowHomeEnabled(true);
                ab.setDisplayShowTitleEnabled(false);



            }

        }
        diabeteFragment = new DiabeteFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.diabeteSection,diabeteFragment)
                .commit();
    }

    protected void showFragment(DiabeteFragment fragment) {

        String TAG = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }


}

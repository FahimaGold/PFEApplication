package com.example.pc.pfe;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class InfoOnDiabete extends AppCompatActivity {

  private Button info2;
  private Button info3;
  public void allerInfo2(View v){
      Info2Fragment info2Fragment = new Info2Fragment();
      android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
      manager.beginTransaction().replace(R.id.infoOnDiabeteSection
              ,info2Fragment)
              .commit();
  }

    public void allerInfo3(View v){
        Info3Fragment info3Fragment = new Info3Fragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.infoOnDiabeteSection
                ,info3Fragment)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_info_on_diabete );
        info2 = (Button) findViewById( R.id.btnPlusInfo1 );
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
        InfoOnDiabeteFragment infoOnDiabeteFragment = new InfoOnDiabeteFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.infoOnDiabeteSection
                ,infoOnDiabeteFragment)
                .commit();
    }
}

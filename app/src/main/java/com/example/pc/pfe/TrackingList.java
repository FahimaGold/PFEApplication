package com.example.pc.pfe;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrackingList extends AppCompatActivity {
    private Toolbar toolbar ;
    private List<TrackingListContent> trackingListContentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private  DatabaseHelper databaseHelper;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tracking_list );
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        databaseHelper = new DatabaseHelper( this );
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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ListAdapter(trackingListContentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareTrackingData();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                TrackingListContent trackingListContent = trackingListContentList.get(position);
                Toast.makeText(getApplicationContext(), trackingListContent.getTrackingType() + " is selected!", Toast.LENGTH_SHORT).show();
               /* if(position == 0) {
                    Intent i = new Intent(getApplicationContext(),GlucoseTracking.class);
                    startActivity(i);
                }
                */
                switch (position){
                    case 0:
                    {
                        Intent i = new Intent(getApplicationContext(),GlucoseTracking.class);
                        startActivity(i);
                    }
                    break;
                    case 1:
                    {
                        Intent i = new Intent(getApplicationContext(),TensionTracking.class);
                        startActivity(i);
                    }
                    break;
                    case 2:
                    {
                        Intent i = new Intent(getApplicationContext(),WeightGraphs.class);
                        startActivity(i);
                    }
                    break;
                    case 3:
                    {
                        Intent i = new Intent(getApplicationContext(),WaistGraphs.class);
                        startActivity(i);
                    }
                    break;
                    case 4:
                    {
                        Intent i = new Intent(getApplicationContext(),HeightGraphs.class);
                        startActivity(i);
                    }
                    break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void prepareTrackingData() {

        InfoMed infoMed = databaseHelper.getMyInfo();
        String email = "";
        if(Login.currentUserEmail != null)
            email = Login.currentUserEmail;
        else
            email = InscriptionFragment.currentEmailUser;
        int id = databaseHelper.getCurrentUserId( email );
        String type = databaseHelper.isDiabetic( id );
        String birthdate = databaseHelper.getCurrentUserBirthDate(email );
        Log.e("Current birth date:", birthdate);
        String dob = birthdate;
        String month = "";
        String dd = "";
        String yer = "";
        String  age = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date d = sdf.parse(dob);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            month = checkDigit(cal.get(Calendar.MONTH)+1);
            dd = checkDigit(cal.get(Calendar.DATE));
            yer = checkDigit(cal.get(Calendar.YEAR));
            Log.e("Jour", dd);
            Log.e("Mois", month);
            Log.e("Annee", yer);

            age=getAge( Integer.parseInt( yer ), Integer.parseInt( month ),  Integer.parseInt( dd ) );
            Toast.makeText( this,"My age is " + age, Toast.LENGTH_SHORT ).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int a = Integer.parseInt( age );
        TrackingListContent cList = new TrackingListContent( "Suivi du glucose" );
         if(type.equals( "Type 1" ) || type.equals( "Type 2" )) {

             trackingListContentList.add( cList );
         }
        cList = new TrackingListContent("Suivi de la tension");
        trackingListContentList.add(cList);

        cList = new TrackingListContent("Suivi du poids");
        trackingListContentList.add(cList);

        cList = new TrackingListContent("Suivi de tour de taille");
        trackingListContentList.add(cList);
        if(a < 22) {
            cList = new TrackingListContent( "Suivi de la taille" );
            trackingListContentList.add( cList );
        }

        mAdapter.notifyDataSetChanged();
    }


    private String getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return ageS;
    }


    // ADDS 0  e.g - 02 instead of 2
    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}

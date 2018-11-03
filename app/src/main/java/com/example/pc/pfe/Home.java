package com.example.pc.pfe;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity {

    // Constants
    // The authority for the sync adapter's content provider
    public static final String AUTHORITY = "com.example.pc.pfe.provider";
    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "example.com";
    // The account name
    public static final String ACCOUNT = "dummyaccount";
    // Instance fields
    public static Account mAccount;


    public static final String URL_SAVE_NAME = "http://192.168.8.100:8080/updateData.php";
    //a broadcast to know weather the data is synced or not
    public static final String DATA_SAVED_BROADCAST = "net.simplifiedcoding.datasaved";

    //Broadcast receiver to know the sync status
    private BroadcastReceiver broadcastReceiver;

    private NotificationManager notificationManager;
    private final static int NOTIFICATION_ID = 0;
    private InfoMed infoMed;

    ImageButton btnSuiviG;
        ImageButton btnInfo;
        ImageButton btnRegime;
        Button btnPrediction;

        private DatabaseHelper databaseHelper;
    public void allerSuiviSection(View v){

        Intent i = new Intent(getApplicationContext(),TrackingList.class);
        startActivity(i);
    }
    public void allerInfoSection(View v){

        Intent i = new Intent(getApplicationContext(),InfoOnDiabete.class);
        startActivity(i);
    }

    public void allerRegimeSection(View v){

        Intent i = new Intent(getApplicationContext(),Regime.class);
        startActivity(i);
    }

    public void allerConseilsSection(View v){

        Intent i = new Intent(getApplicationContext(),Advice.class);
        startActivity(i);
    }

    public void predire(View v){

        Intent i = new Intent(getApplicationContext(),Prediction.class);
        startActivity(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        //return super.onCreateOptionsMenu(menu);

       /* menu.add("hello").setIcon(R.drawable.ic_person_black_24dp);
        menu.add("patel").setIcon(R.drawable.ic_heartbeat);
        menu.add("abc").setIcon(R.drawable.ic_person_black_24dp);
        menu.add("hello").setIcon(R.drawable.ic_heartbeat);
        menu.add("").setIcon(R.drawable.ic_person_black_24dp);
        menu.add("").setIcon(R.drawable.ic_heartbeat);*/

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menuIdInfos:
                Toast msg = Toast.makeText(Home.this, "Infos", Toast.LENGTH_LONG);
                msg.show();
                Intent i = new Intent(getApplicationContext(),MedicalInformation.class);
                startActivity(i);
                return true;

            case R.id.menuIdRecettes:
                Toast msg1 = Toast.makeText(Home.this, "Recettes", Toast.LENGTH_LONG);
                msg1.show();
                 i = new Intent(getApplicationContext(),ReceipeList.class);
                startActivity(i);
                return true;
            case R.id.menuSeDeconnecter:
                Toast msg2 = Toast.makeText(Home.this, "Se déconnecter", Toast.LENGTH_LONG);
                msg2.show();
                 i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                return true;

            case 2:

                Toast msg3 = Toast.makeText(Home.this, "Menu 1", Toast.LENGTH_LONG);
                msg3.show();
                return true;

        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

            setContentView( R.layout.activity_home );


            databaseHelper = new DatabaseHelper( this );
            String emailC = "";
            int idUser = 0;
            if (Login.currentUserEmail != null) {
                emailC = Login.currentUserEmail;
                idUser = databaseHelper.getCurrentUserId( emailC );
            }
            else {
                emailC = InscriptionFragment.currentEmailUser;
                idUser = databaseHelper.getCurrentUserId( emailC );

            }
            final boolean updated = databaseHelper.getUpdatedValue();
            final boolean inserted = databaseHelper.getInsertedValue();
            Log.e( "updated or not", "" + updated );
            try {
                ViewConfiguration config = ViewConfiguration.get( this );
                Field menuKeyField = ViewConfiguration.class.getDeclaredField( "sHasPermanentMenuKey" );
                if (menuKeyField != null) {
                    menuKeyField.setAccessible( true );
                    menuKeyField.setBoolean( config, false );
                }
            } catch (Exception ignored) {
            }
            btnSuiviG = (ImageButton) findViewById( R.id.btnSuiviGlucose );
            btnInfo = (ImageButton) findViewById( R.id.btnInfoDiabete );
            btnPrediction = (Button) findViewById( R.id.btnPrediction );
            btnRegime = (ImageButton) findViewById( R.id.btnRegime );
            Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
            if (toolbar != null) {

                setSupportActionBar( toolbar );//To display toolbar
                toolbar.setTitle( "PFEApp" );
                toolbar.showOverflowMenu();
                ActionBar ab = getSupportActionBar();
                if (ab != null) {
                    ab.setDisplayHomeAsUpEnabled( true );
                    ab.setDisplayShowHomeEnabled( true );
                    ab.setDisplayShowTitleEnabled( false );


                }

            }

            HomeFragment homeFragment = new HomeFragment();
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace( R.id.homeSection
                    , homeFragment )
                    .commit();


            databaseHelper = new DatabaseHelper( getApplicationContext() );
            infoMed = new InfoMed();
            infoMed = databaseHelper.getMyInfo();
        String birthdate = databaseHelper.getCurrentUserBirthDate(emailC );
        Log.e("Current birth date:", birthdate);

        //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" ); // here set the pattern as you date in string was containing like date/month/year
            Date d = sdf.parse( birthdate );
            Log.e("Date Format:", ""+d);

        } catch (ParseException e) {
            e.printStackTrace();
        }


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
        final  String a = age;
        Toast.makeText( this,"Age is " + a, Toast.LENGTH_SHORT ).show();
        //int a = Integer.parseInt( age );
            final String type = (infoMed.getType() != null) ? infoMed.getType() : "empty";
            final String fumeur = (infoMed.getFumeur() != null) ? infoMed.getFumeur() : "empty";
            final String sport = (infoMed.getSport() != null) ? infoMed.getSport() : "empty";
            final String stress = (infoMed.getStress() != null) ? infoMed.getStress() : "empty";
            final String tourDeTaille = (infoMed.getTourDeTaille() != null) ? infoMed.getTourDeTaille() : "empty";
            String poids = (infoMed.getPoids() != null) ? infoMed.getPoids() : "empty";
            String taille = (infoMed.getTaille() != null) ? infoMed.getTaille() : "empty2";
            final String systolic = (infoMed.getSystolic() != null) ? infoMed.getSystolic() : "empty";
            final String diastolic = (infoMed.getDistolic() != null) ? infoMed.getDistolic() : "empty";
            final String historique = (infoMed.getHistorique() != null) ? infoMed.getHistorique() : "empty";
            final String idUtilisateur = "" + ((idUser != 0) ? idUser : 0);
            final String updatedVal = "" + ((infoMed.getModifie() != 0) ? infoMed.getModifie() : 0);
            final String insertedVal = "" + ((infoMed.getInsere() != 0) ? infoMed.getInsere() : 0);
            final String idInfoMed = "" + ((infoMed.getIdInfoMed() != 0) ? infoMed.getIdInfoMed() : 0);

            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {


                }
            };

            //registering the broadcast receiver to update sync status
            registerReceiver( broadcastReceiver, new IntentFilter( DATA_SAVED_BROADCAST ) );


            if (databaseHelper.getInsertedValue() || databaseHelper.getUpdatedValue()) {


                databaseHelper = new DatabaseHelper( this );
                final String finalPoids = poids;
                final String finalTaille = taille;
                final String finalEmailC = emailC;
                StringRequest stringRequest = new StringRequest( Request.Method.POST, URL_SAVE_NAME,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject obj = new JSONObject( response );
                                    if (!obj.getBoolean( "error" )) {
                                        //if there is a success
                                        //storing the name to sqlite with status synced
                                        if (databaseHelper.getInsertedValue()) {
                                            databaseHelper.updateInserted( 0 );

                                        }

                                        if (databaseHelper.getUpdatedValue()) {
                                            databaseHelper.updateUpdated( 0 );

                                        }


                                    } else {
                                        //if there is some error
                                        //saving the name to sqlite with status unsynced
                                        Toast.makeText( Home.this, "Didn't work", Toast.LENGTH_SHORT ).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText( Home.this, "Didn't work", Toast.LENGTH_SHORT ).show();
                            }
                        } ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put( "type", type );
                        map.put( "fumeur", fumeur );
                        map.put( "sport", sport );
                        map.put( "stress", stress );
                        map.put( "tourDeTaille", tourDeTaille );
                        map.put( "poids", finalPoids );
                        map.put( "taille", finalTaille );
                        map.put( "systolic", systolic );
                        map.put( "diastolic", diastolic );
                        map.put( "historique", historique );
                        map.put( "updated", updatedVal );
                        map.put( "inserted", insertedVal );
                        map.put( "idUtilisateur", idUtilisateur );
                        map.put( "idInfoMed", idInfoMed );
                        map.put( "sexe", databaseHelper.getCurrentUserGender( finalEmailC ) );
                        map.put( "wilaya", databaseHelper.getCurrentUserWilaya( finalEmailC ) );
                        map.put( "age",a );
                        Log.e( "updated or not", "" + updatedVal );
                        Log.e( "inserted or not", "" + insertedVal );
                        return map;
                    }
                };

                VolleySingleton.getInstance( this ).addToRequestQueue( stringRequest );
            }




        infoMed = new InfoMed();
        infoMed = databaseHelper.getMyInfo();
            poids = infoMed.getPoids();
            taille = infoMed.getTaille();
        Float weight = Float.parseFloat( poids );
       /* if (taille != null && poids !=null) {
            Float size = Float.parseFloat( taille );
            float bmi = weight / (size * size);

            String bmiR = String.format( "%.4f", bmi );
            String bmiResult = bmiR.replace( ",", "." );

            if (Float.parseFloat( bmiResult ) >= 25) {
                notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder( Home.this, "Test" )
                        .setSmallIcon( R.drawable.ic_suivi_sant )
                        .setContentTitle( "Vous êtes surpoids" )
                        .setContentText( "On vous propose de faire de la marche 20 mn chaque jour. Ceci vous permettra d'avoir un poids équilibré" )
                        .setPriority( NotificationCompat.PRIORITY_DEFAULT )

                        .setAutoCancel( true )  //  Une fois on clique sur la notifcation, elle disparaît, only works when we're not using PendingIntent
                        // .addAction(R.drawable.ic_heartbeat,"OK",pendingIntent)
                        .setDefaults( Notification.DEFAULT_SOUND );
                //  .setDeleteIntent()   to delete the intent after using the notification
                // .setSmallIcon(R.drawable.ic_stat_name);
                notificationManager.notify( NOTIFICATION_ID, mBuilder.build() );
            }
            if ("Type 1".equals( infoMed.getType() ) || "Type 2".equals( infoMed.getType() )) {

                notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder( Home.this, "Test" )
                        .setSmallIcon( R.drawable.ic_suivi_diabte )
                        .setContentTitle( "Suivez votre diabète" )
                        .setContentText( "Cette application vous permet de sauvegarder les mesures de votre glucose et puis les montrer à votre médecin. Cette application vous offre aussi des recettes et un régime" )
                        .setPriority( NotificationCompat.PRIORITY_DEFAULT )

                        .setAutoCancel( true )  //  Une fois on clique sur la notifcation, elle disparaît, only works when we're not using PendingIntent
                        // .addAction(R.drawable.ic_heartbeat,"OK",pendingIntent)
                        .setDefaults( Notification.DEFAULT_SOUND );
                //  .setDeleteIntent()   to delete the intent after using the notification
                // .setSmallIcon(R.drawable.ic_stat_name);
                notificationManager.notify( NOTIFICATION_ID, mBuilder.build() );
            }*/

            Calendar calendar = Calendar.getInstance();
            calendar.set( Calendar.HOUR_OF_DAY, 4 );
            calendar.set( Calendar.MINUTE, 40 );
            calendar.set( Calendar.SECOND, 0 );
            Intent intent1 = new Intent( Home.this, AlarmReceiver.class );
            PendingIntent pendingIntent = PendingIntent.getBroadcast( Home.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT );
            AlarmManager am = (AlarmManager) Home.this.getSystemService( Home.this.ALARM_SERVICE );
            am.setRepeating( AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent );

            databaseHelper = new DatabaseHelper( this );
            List<Poids> list = new ArrayList<>();
            list = databaseHelper.getMesuresPoids();
            Poids poidsT = new Poids();
            if(list.size()>0) {
                poidsT = (Poids) list.get( 0 );
                Log.e( "Hey there", "" + poidsT.getValeur() );
                Toast.makeText( this, "" + poidsT.getValeur(), Toast.LENGTH_SHORT ).show();
            }
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

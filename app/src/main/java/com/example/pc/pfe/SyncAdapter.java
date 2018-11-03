package com.example.pc.pfe;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.SyncResult;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by pc on 31/08/2018.
 */

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    // Global variables
    private  InfoMed infoMed;
    private DatabaseHelper databaseHelper;
    // Define a variable to contain a content resolver instance
    ContentResolver mContentResolver;

    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super( context, autoInitialize );
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();


    }

    // User table name
    private static final String TABLE_USER = "utilisateur";

    //DataBase Helper
    private DatabaseHelper mOpenHelper;


    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_SURNAME = "user_surname";
    private static final String COLUMN_USER_BIRTH = "user_birth_date";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_GENDER = "user_gender";
    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_SURNAME + " TEXT,"
            + COLUMN_USER_BIRTH + " TEXT,"
            + COLUMN_USER_GENDER + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    private static final String TABLE_INFO_MEDICALE = "infoMedicale";

    private static final String COLUMN_INFO_MEDICALE_ID = "info_id";
    private static final String COLUMN_TAILLE = "taille";
    private static final String COLUMN_USER_POIDS = "poids";
    private static final String COLUMN_TOUR_DE_TAILLE = "tour_taille";
    private static final String COLUMN_ANTECEDENTS = "antecedants";
    private static final String COLUMN_SPORT = "sport";
    private static final String COLUMN_STRESS = "stress";
    private static final String COLUMN_FUMER = "fumeur";
    private static final String COLUMN_TYPE_DE_DIABETE = "type";
    private static final String COLUMN_TENSION_SYSTOLIC = "tension_systolique";
    private static final String COLUMN_TENSION_DIASTOLIC = "tension_diastolique";
    private static final String COLUMN_ID_UTILISATEUR = "id_utilisateur";
    private static final String COLUMN_UPDATED = "modifie";
    private static final String COLUMN_INSERTED = "insere";

    private String CREATE_INFO_MEDICALE_TABLE = "CREATE TABLE " + TABLE_INFO_MEDICALE + "("
            + COLUMN_INFO_MEDICALE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TAILLE + " TEXT, "
            + COLUMN_UPDATED + " INTEGER, "
            + COLUMN_INSERTED + " INTEGER, "
            + COLUMN_FUMER + " TEXT,"
            + COLUMN_TYPE_DE_DIABETE + " TEXT,"
            + COLUMN_USER_POIDS + " TEXT,"
            + COLUMN_TOUR_DE_TAILLE + " TEXT,"
            + COLUMN_ANTECEDENTS + " TEXT,"
            + COLUMN_SPORT + " TEXT,"
            + COLUMN_STRESS + " TEXT,"
            + COLUMN_TENSION_SYSTOLIC + " TEXT,"
            + COLUMN_TENSION_DIASTOLIC + " TEXT,"
            + COLUMN_ID_UTILISATEUR + " INTEGER," + " FOREIGN KEY (" + COLUMN_ID_UTILISATEUR + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";
    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super( context, autoInitialize, allowParallelSyncs );
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();
    }




    /*
     * Specify the code you want to run in the sync adapter. The entire
     * sync adapter runs in a background thread, so you don't have to set
     * up your own background processing.
     */
    @Override
    public void onPerformSync(
            Account account,
            Bundle extras,
            String authority,
            ContentProviderClient provider,
            SyncResult syncResult) {
    /*
     * Put the data transfer code here.
     */


       /* databaseHelper = new DatabaseHelper( getContext() );
        infoMed = new InfoMed();
        infoMed = databaseHelper.getMyInfo();*/

        /*
        Here, you have to reimplement getMyInfo, i mean you copy the code of the function
        here and reget the values using the ContentProviderClient variable that given in the parameters
        infoMed = ... provider.query() ....
        */
/*        final String type = (infoMed.getType()!=null) ?infoMed.getType() : "empty";
        final String fumeur = (infoMed.getFumeur()!=null) ?infoMed.getFumeur() : "empty" ;
        final String sport = (infoMed.getSport()!=null) ? infoMed.getSport(): "empty" ;
        final String stress = (infoMed.getStress()!=null) ? infoMed.getStress() : "empty";
        final String tourDeTaille = (infoMed.getTourDeTaille()!=null) ? infoMed.getTourDeTaille(): "empty" ;
        final String poids = (infoMed.getPoids() !=null) ? infoMed.getPoids(): "empty";
        final String taille = (infoMed.getTaille() !=null) ?infoMed.getTaille() : "empty2";
        final String systolic = (infoMed.getSystolic() !=null) ? infoMed.getSystolic(): "empty";
        final String diastolic = (infoMed.getDistolic() !=null) ?infoMed.getDistolic() : "empty" ;
        final String historique = (infoMed.getHistorique() !=null) ? infoMed.getHistorique(): "empty";
        final String updated = "" + ((infoMed.getModifie()!=0) ? infoMed.getModifie() : 0);
        final String inserted = "" + ((infoMed.getInsere()!=0) ? infoMed.getInsere() : 0);
        final String idUtilisateur = "" + ((infoMed.getIdUtilisateur()!=0) ? infoMed.getIdUtilisateur(): 0 );
        final String idInfoMed = "" + ((infoMed.getIdInfoMed() !=0) ? infoMed.getIdInfoMed(): 0) ;

        String uri = String.format("http://192.168.1.4/updateData.php",
                type,fumeur,sport, stress, tourDeTaille, poids, taille, systolic, diastolic, historique, updated,inserted, idUtilisateur, idInfoMed);
        String url = "http://192.168.1.4/updateData.php";
        Log.e( "start sync", "Starting synchronization..." );

        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        RequestQueue queue = Volley.newRequestQueue( getContext() );
        StringRequest request =
                new StringRequest( Request.Method.POST, uri, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null && s.length() > 0) {
                            Toast.makeText( getContext(), s, Toast.LENGTH_LONG ).show();
                        }else {
                            Toast.makeText( getContext(), "s : null", Toast.LENGTH_LONG ).show();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText( getContext(), "this is the error "+volleyError.toString(), Toast.LENGTH_SHORT ).show();
                    }

                } ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();

                        map.put( "type",type);
                        map.put("fumeur", fumeur);
                        map.put( "sport",sport );
                        map.put( "stress",stress );
                        map.put( "tourDeTaille",tourDeTaille );
                        map.put( "poids",poids );
                        map.put( "taille", taille );
                        map.put( "systolic",systolic );
                        map.put( "diastolic",diastolic );
                        map.put( "historique",historique );
                        map.put( "updated", updated);
                        map.put( "inserted",inserted);
                        map.put( "idUtilisateur",idUtilisateur);
                        map.put( "idInfoMed", idInfoMed);
                        return map;
                    }
                };

        queue.add(request);*/
        Log.w(TAG, "Finished synchronization!");

    }

    /**
     * Manual force Android to perform a sync with our SyncAdapter.
     */
    public static void performSync() {

        Bundle b = new Bundle();
        b.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        b.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        ContentResolver.requestSync( Home.mAccount, Home.AUTHORITY, b);
    }


}
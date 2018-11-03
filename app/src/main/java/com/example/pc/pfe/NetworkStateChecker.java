package com.example.pc.pfe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 13/09/2018.
 */

public class NetworkStateChecker extends BroadcastReceiver {
    //context and database helper object
    private Context context;
    private DatabaseHelper db;



    @Override
    public void onReceive(final Context context, Intent intent) {

        this.context = context;

        db = new DatabaseHelper(context);

        InfoMed infoMed = new InfoMed();
        infoMed = db.getMyInfo();
        String emailC = "";
        int idUser = 0;
        if(Login.currentUserEmail != null)
            idUser  = db.getCurrentUserId( emailC);
        else
            idUser = db.getCurrentUserId( InscriptionFragment.currentEmailUser );
        final String type = (infoMed.getType()!=null) ?infoMed.getType() : "empty";
        final String fumeur = (infoMed.getFumeur()!=null) ?infoMed.getFumeur() : "empty" ;
        final String sport = (infoMed.getSport()!=null) ? infoMed.getSport(): "empty" ;
        final String stress = (infoMed.getStress()!=null) ? infoMed.getStress() : "empty";
        final String tourDeTaille = (infoMed.getTourDeTaille()!=null) ? infoMed.getTourDeTaille(): "empty" ;
        final String poids = (infoMed.getPoids() !=null) ? infoMed.getPoids(): "empty";
        final String taille = (infoMed.getTaille() !=null) ?infoMed.getTaille() : "empty2";
        final String systolic = (infoMed.getSystolic() !=null) ? infoMed.getSystolic(): "empty";
        final String diastolic = (infoMed.getDistolic() !=null) ?infoMed.getDistolic() : "empty" ;
        final String historique = (infoMed.getHistorique() !=null) ? infoMed.getHistorique(): "empty";
        final String idUtilisateur = "" + ((idUser!=0) ? idUser: 0 );
        final String idInfoMed = "" + ((infoMed.getIdInfoMed() !=0) ? infoMed.getIdInfoMed(): 0) ;
        final String updatedVal = "" + ((infoMed.getModifie() !=0) ? infoMed.getModifie(): 0) ;
        final String insertedVal = "" + ((infoMed.getInsere() !=0) ? infoMed.getInsere(): 0) ;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        //if there is a network
        if (activeNetwork != null) {
            //if connected to wifi or mobile data plan
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                //getting all the unsynced names

                if (db.getUpdatedValue() || db.getInsertedValue()) {

                    StringRequest stringRequest = new StringRequest( Request.Method.POST, Home.URL_SAVE_NAME,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        if (!obj.getBoolean("error")) {
                                            //updating the status in sqlite
                                            if(db.getUpdatedValue())
                                                db.updateUpdated( 0 );
                                            else
                                                db.updateInserted( 0 );

                                            //sending the broadcast to refresh the list
                                            context.sendBroadcast(new Intent(Home.DATA_SAVED_BROADCAST));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
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
                            map.put( "updated", updatedVal);
                            map.put( "inserted",insertedVal);
                            map.put( "idUtilisateur",idUtilisateur);
                            map.put( "idInfoMed", idInfoMed);
                            return map;
                        }
                    };

                    VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
                }
            }
        }
    }


}

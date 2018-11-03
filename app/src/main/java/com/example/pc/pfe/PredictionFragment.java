package com.example.pc.pfe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.content.ContentValues.TAG;
import static com.android.volley.DefaultRetryPolicy.DEFAULT_MAX_RETRIES;


public class PredictionFragment extends Fragment {


    private DatabaseHelper databaseHelper;
    private  AdviceFragment adviceFragment;
    private  InfoMed infoMed;
    private  TextView textView;
    private ProgressBar progessBar;
    private Button btnPrediction;
    private String url ="http://192.168.8.100:8000/recep/?format=json";
    public PredictionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myInflatedView = inflater.inflate( R.layout.fragment_prediction, container, false );
        final TextView syst = (TextView)myInflatedView.findViewById(R.id.syst);
        final NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.systolic);
        btnPrediction = (Button)myInflatedView.findViewById( R.id.btnPrediction );
        progessBar = (ProgressBar)(myInflatedView).findViewById(R.id.progressBar);
        textView = (TextView)(myInflatedView).findViewById( R.id.predictionResult );

        final TextView diast = (TextView)myInflatedView.findViewById(R.id.diast);
        final NumberPicker piko = (NumberPicker)myInflatedView.findViewById(R.id.diastolic);
        np.setMinValue(20);
        np.setMaxValue(220);
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

                syst.setText(""+newVal+"mmHg") ;
                //stmp.setTextColor(Color.parseColor("#0a2150"));
            }
        });

        piko.setMinValue(20);
        piko.setMaxValue(220);
        piko.setWrapSelectorWheel(true);

        piko.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

                diast.setText(""+newVal+"mmHg") ;
                //stmp.setTextColor(Color.parseColor("#0a2150"));
            }
        });




        databaseHelper = new DatabaseHelper( getContext() );
        infoMed = new InfoMed(  );
        infoMed = databaseHelper.getMyInfo();

        String poids = infoMed.getPoids();
        Log.e( "Mon poids", poids );
        String taille = infoMed.getTaille();
        String type = infoMed.getType();
        if(taille == null)
              taille = "1.6";
        Float weight = Float.parseFloat(poids);

        Float size = Float.parseFloat( taille );
        Utilisateur user = new Utilisateur(  );
        String email = "";
        if(Login.currentUserEmail != null)
            email = Login.currentUserEmail;
        else
            email = InscriptionFragment.currentEmailUser;
        String birthdate = databaseHelper.getCurrentUserBirthDate(email );
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
            Toast.makeText( getContext(),"My age is " + age, Toast.LENGTH_SHORT ).show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int a = Integer.parseInt( age );
        float bmi = weight / (size*size);
        String bmiR = String.format("%.4f", bmi);
        final String bmiResult = bmiR.replace(",",".");



        progessBar.setVisibility( View.INVISIBLE);
        final String finalAge = age;
        final String finalEmail = email;
        final String finalAge1 = age;
        btnPrediction.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String systolic = "" + np.getValue();
                final String diastolic = "" + piko.getValue();
                databaseHelper.ajouterTension( systolic, diastolic );
                //post data to server
                progessBar.setVisibility( View.VISIBLE);
                textView.setText( "Veuillez patientez SVP" );
                textView.setTextColor( Color.parseColor("#0a2150") );
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String urlP = "http://192.168.8.100:8000/recep/";
                StringRequest postRequest = new StringRequest(Request.Method.POST, urlP,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", "dunno" + error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        //patient = [BMI,ponds,taille, tension systolique, tension dyastolique, age, sexe, diabétique ou pas]
                        params.put("bmi", bmiResult);
                        params.put("poids", infoMed.getPoids());
                        params.put("taille", infoMed.getTaille());
                        params.put("systolic", systolic);
                        params.put("diastolic", diastolic);
                        params.put("age", finalAge1 );
                        params.put("gender", databaseHelper.getCurrentUserGender( finalEmail ));

                        //params.put("historique", infoMed.getHistorique());



                        return params;
                    }
                };
                queue.add(postRequest);

                //Receving prediction results from the server


                //RequestQueue queue = Volley.newRequestQueue(getContext());
                JsonObjectRequest myRequest = new JsonObjectRequest( Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Log.e("Reponse", response.toString());
                                progessBar.setVisibility( View.INVISIBLE);
                                try {
                                    String value = response.getString( "Res" );
                                    if(value.equals( "1" )) {
                                        textView.setText( "Vous avez un grand risque d'être diabétique" );
                                        textView.setTextColor( Color.parseColor("#830303") );
                                        textView.setTextSize( 30 );
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                        builder.setCancelable(true);
                                        builder.setTitle("Faites attention");
                                        builder.setMessage("Nous recommandons fortement d'aller faire les analyses.");


                                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                //alertTextView.setVisibility(View.VISIBLE);
                                            }
                                        });
                                        builder.show();
                                    }
                                    else {
                                        textView.setText( "Vous êtes sain" );
                                        textView.setTextColor( Color.parseColor("#089455") );
                                        textView.setTextSize( 30 );
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("pas de reponse", "Error: " + error.getMessage());
                    }
                });

                //Set a retry policy in case of SocketTimeout & ConnectionTimeout Exceptions.
//Volley does retry for you if you have specified the policy.
                myRequest.setRetryPolicy(new DefaultRetryPolicy(100000,
                        5,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(myRequest);

            }
        } );






        // Inflate the layout for this fragment
        return myInflatedView;
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

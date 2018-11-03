package com.example.pc.pfe;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdviceFragment extends Fragment {

     private TextView textView;
     private DatabaseHelper databaseHelper;



    public AdviceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate( R.layout.fragment_advice, container, false );
        textView = (myInflatedView).findViewById( R.id.adviceContainer );
        InfoMed infoMed = new InfoMed(  );
        databaseHelper = new DatabaseHelper( getContext() );
        infoMed = databaseHelper.getMyInfo();
        String poids = infoMed.getPoids();
        String taille = infoMed.getTaille();
        String type = infoMed.getType();
           if(taille == null){
               taille ="1.60";
           }
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
        String bmiResult = bmiR.replace(",",".");
        /*Toast.makeText( getContext(), "Taille " + size, Toast.LENGTH_SHORT ).show();
        Toast.makeText( getContext(), "Poids " + weight, Toast.LENGTH_SHORT ).show();
        Toast.makeText( getContext(), "BMI " + bmiResult, Toast.LENGTH_SHORT ).show();
        Toast.makeText( getContext(), "Age " + a, Toast.LENGTH_SHORT ).show();*/

        if(( a >= 45) || ((Float.parseFloat( bmiResult ) > 25) && (Float.parseFloat( bmiResult ) < 29.9) )
                && (( !type.equals( "Type 1" )) && (!type.equals( "Type 2" ))))
        {
            if( a >= 45) {
                textView.setText( "On vous propose de suivre un régime équilibré. Moins de surce et moins de sel." );
            }
            else {
                textView.setText( "Faite attention à votre poids, vous êtes pré-obèse. On vous suggère de suivre un régime" +
                        "équlibré et de faire du sport si vous n'avez pas une maladie respiratoire. Vous devez consulter votre" +
                        "médecin sinon" );
                textView.setBackgroundColor( Color.parseColor("#830303") );
                textView.setTextColor( Color.parseColor( "#ffffff" ) );

            }
            }
            else{
            if( type.equals( "Type 1" ) || type.equals( "Type 2" )){
                textView.setText( "N'oubliez jamais les injections avant les repas. " +
                        "Ne faîtes jamais les injections sans repas. " +
                        "N'oubliez pas de faire des contrôles ophtalmologiques et cardiologues." );
            }

            if(Float.parseFloat( bmiResult ) >= 25){
                textView.setText("On vous propose de suivre un régime équilibré. Voir Partie régime. Vous pouvez également consulter des recettes. On vous propose également de faire la marche si vous n'avez pas une maladie respiratoire. Vous devez consulter votre médecin sinon." );
            }
        }

        if(Float.parseFloat( bmiResult ) > 18.5 && Float.parseFloat( bmiResult ) <24.9){
            textView.setText("Votre poids est normal. Gardez ce bon état de santé");
            textView.setBackgroundColor( Color.parseColor("#00c85d") );
            textView.setTextColor( Color.parseColor( "#ffffff" ) );

        }





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

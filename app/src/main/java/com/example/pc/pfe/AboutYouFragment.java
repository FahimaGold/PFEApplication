package com.example.pc.pfe;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.zip.Inflater;


public class AboutYouFragment extends Fragment {

    public String poidsK ;
    public String poidsG ;
    public String hauteurMetre;
    public String hauteurCenti;
    private AboutYou aboutYou;



    public AboutYouFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_about_you, container,false);
        final Spinner spinner = (Spinner)myInflatedView.findViewById(R.id.genre);
        final Spinner sportSpinner = (Spinner)myInflatedView.findViewById(R.id.sport);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.Gender, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        // Set the Text to try this out

        final TextView tv = (TextView)myInflatedView.findViewById(R.id.tv);
        final NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.poidsActuel);
        final NumberPicker gramme = (NumberPicker)myInflatedView.findViewById(R.id.poidsGramme);

        //Set TextView text color
        //tv.setTextColor(Color.parseColor("#ffd32b3b"));

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        np.setMinValue(30);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(230);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);

        gramme.setMinValue(0);
        gramme.setMaxValue(99);
        gramme.setWrapSelectorWheel(true);
        final TextView hauteur = (TextView)myInflatedView.findViewById(R.id.hauteur);
        final NumberPicker hauteurM = (NumberPicker)myInflatedView.findViewById(R.id.hauteurM);
        hauteurM.setMinValue(1);
        hauteurM.setMaxValue(2);
        hauteurM.setWrapSelectorWheel(true);
        final NumberPicker hauteurC = (NumberPicker)myInflatedView.findViewById(R.id.hauteurC);
        hauteurC.setMinValue(0);
        hauteurC.setMaxValue(99);
        hauteurC.setWrapSelectorWheel(true);


        aboutYou = (AboutYou) getContext();
        Button btn = aboutYou.getBtnForPart2();
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String taille = hauteurM.getValue()+ "." + hauteurC.getValue();
                String poids = np.getValue() + "." + gramme.getValue();
                String spinnerValue = spinner.getSelectedItem().toString();
                Log.d("Pourquoi", "Pourquoi la taille est nulle");
                Log.d("Taille ",taille);
                Log.d("Poids ",poids);
                String sportValue = sportSpinner.getSelectedItem().toString();
                String typeDiab = getActivity().getIntent().getExtras().getString("typeDiab");
                //Toast.makeText( aboutYou,"Hello" + typeDiab, Toast.LENGTH_SHORT ).show();
                Bundle b=new Bundle();
                b.putStringArray("values", new String[]{poids, taille, spinnerValue, sportValue, typeDiab});
                Intent i=new Intent(getContext(), AboutYouSecondPart.class);
                i.putExtras(b);
                startActivity( i );
                //Intent i = new Intent(getContext(),Inscription.class);
               //i.putExtra("dateDeNaissance", strDateTime);

                //startActivity(i);
                // String dateDeN=this.date.toString();
                ; //Generate a toast only if you want
                // If you want to continue on that TimeDateActivity
                // If you want to go to new activity that code you can also write here
            }});



        //Set a value change listener for NumberPicker
      poidsK = tv.getText().toString()+".";
        hauteurMetre = hauteur.getText().toString()+".";
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
             //  tv.setText(poidsK + newVal);
               // tv.setTextColor(Color.parseColor("#0a2150"));
            }
        });
         poidsG = poidsK;
        gramme.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {


            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

               // tv.setText(poidsG + newVal+"kg");
               // tv.setTextColor(Color.parseColor("#0a2150"));

            }
        });
               //Hauteur

        hauteurM.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
               // hauteur.setText(hauteurMetre + newVal);
               // hauteur.setTextColor(Color.parseColor("#0a2150"));
            }
        });
        hauteurCenti = hauteurMetre;
        hauteurC.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {


            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

               // hauteur.setText(hauteurCenti + newVal+"m");
             //  hauteur.setTextColor(Color.parseColor("#0a2150"));

            }
        });


        return myInflatedView;

        //return inflater.inflate(R.layout.fragment_about_you, container, false);
    }




}

package com.example.pc.pfe;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlucoseTrackingFragment extends Fragment  {

   private Button btnNote;
   GlucoseTracking glucoseTracking;
   private  Button sauvG;
   private Note note;
   private Glucose glucose;
   private  DatabaseHelper databaseHelper;
    public GlucoseTrackingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_glucose_tracking, container, false);

        // Set the Text to try this out
        glucoseTracking = (GlucoseTracking) getContext();
        final TextView stmp = (TextView)myInflatedView.findViewById(R.id.stmp);
        final NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.glucoseStamp);
        final TextView mgDL = (TextView)myInflatedView.findViewById(R.id.mgDl);
        final NumberPicker piko = (NumberPicker)myInflatedView.findViewById(R.id.glucoseValue);
        note = new Note(  );
        glucose = new Glucose(  );

        //Set TextView text color
        //tv.setTextColor(Color.parseColor("#ffd32b3b"));

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker
        np.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(5);
        final String[] arrayString = new String[] {"Avant petit-déjeuner", "Après petit-déjeuner", "Avant déjeuner",
                "Après déjeuner", "Avant dinner", "Après dinner"};

        np.setDisplayedValues(arrayString);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

                stmp.setText(""+arrayString[picker.getValue()]) ;
                //stmp.setTextColor(Color.parseColor("#0a2150"));
            }
        });

        piko.setMinValue(100);
        piko.setMaxValue(2000);
        piko.setWrapSelectorWheel(true);

        piko.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

                mgDL.setText(""+newVal+"mg/L") ;
                //stmp.setTextColor(Color.parseColor("#0a2150"));
            }
        });

        btnNote = (Button) myInflatedView.findViewById( R.id.btnNote );
        btnNote.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),Note.class);
                startActivity(i);

            }
        } );

        sauvG = glucoseTracking.sauvGlucose;
        sauvG.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                glucose = new Glucose(  );
                 databaseHelper = new DatabaseHelper(getContext()  );
                 String stamp = "" + np.getValue();
                 Log.e("Stamp is ",stamp );

                 String glucoseVal = "" + piko.getValue();
                Log.e("Value is ",glucoseVal );
                 glucose.setTauxGlucose( glucoseVal );
                 glucose.setStamp(stamp);
                 databaseHelper.ajouterTauxGlucose( glucose );
                 Toast.makeText( glucoseTracking, "Votre valeur a été bien sauvegardée! " + glucoseVal, Toast.LENGTH_SHORT ).show();
                Toast.makeText( glucoseTracking, "Vous pouvez ajouter une note s'il y avait quelque chose aujourd'hui et vous jugez qu'il a infulencé  votre glcuose", Toast.LENGTH_SHORT ).show();
            }
        } );
       // loadBitmapFromView( myInflatedView );
        return myInflatedView;
    }


}

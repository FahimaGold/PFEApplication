package com.example.pc.pfe;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutYouSecondPartFragment extends Fragment {

    private static String date;
    public String tour ;
    private InscriptionFragment inscriptionFragment;
    private int year;
    private int month;
    private int day;
    private Date d;
    private SimpleDateFormat dateFormatter ;
    private AboutYouSecondPart aboutYouSecondPart;
    static final int DATE_DIALOG_ID = 999;
    public AboutYouSecondPartFragment() {
        // Required empty public constructor
    }
    public String getDDN(){
        return this.date;
    }

    public void setDDN(String date){
        this.date = date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate(R.layout.fragment_about_you_second_part, container,false);

        Bundle b=getActivity().getIntent().getExtras();
        final String[] array=b.getStringArray("values");
        Log.d( "array",array[1] );
        final DatePicker datePicker = (myInflatedView).findViewById( R.id.dateDeNaissance );
        final Spinner historique = (myInflatedView).findViewById( R.id.historique );
        final Spinner fumeur = (myInflatedView).findViewById( R.id.fumeur );
        final Spinner stress = (myInflatedView).findViewById( R.id.stress );
        final NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.tourDeTaille);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        aboutYouSecondPart = (AboutYouSecondPart) getContext();
        Button btn = aboutYouSecondPart.getBtnInsc();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDateTime = datePicker.getDayOfMonth() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getYear()   ;
                Log.d( "Hopefully",strDateTime);
                String tourT = "" + np.getValue();
                String historiqeuValue = historique.getSelectedItem().toString();
                String fumeurValue = fumeur.getSelectedItem().toString();
                String stressValue = stress.getSelectedItem().toString();
                Bundle b=new Bundle();
                b.putStringArray("inscriptionInfos", new String[]{array[0], array[1], array[2], array[3], array[4], strDateTime, tourT, historiqeuValue,
                        fumeurValue, stressValue});
                Intent i=new Intent(getContext(), Inscription.class);
                i.putExtras(b);
                startActivity( i );


                // String dateDeN=this.date.toString();
              ; //Generate a toast only if you want
                 // If you want to continue on that TimeDateActivity
                // If you want to go to new activity that code you can also write here
            }});






        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setMinValue(25);
        np.setMaxValue(200);
        np.setWrapSelectorWheel(true);


        return myInflatedView;
    }


}

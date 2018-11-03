package com.example.pc.pfe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;


public class WeightTrackingFragment extends Fragment {

    public String poidsKilo;
    public String poidsGram;

    public WeightTrackingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate( R.layout.fragment_weight_tracking, container, false );
        final TextView tv = (TextView)myInflatedView.findViewById(R.id.valeurPoids);
        NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.poidsSm);
        NumberPicker cm = (NumberPicker)myInflatedView.findViewById(R.id.poidsSc);


        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setMinValue(1);
        np.setMaxValue(2);
        np.setWrapSelectorWheel(true);

        cm.setMinValue(0);
        cm.setMaxValue(99);
        cm.setWrapSelectorWheel(true);



        poidsKilo = tv.getText().toString()+".";
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                tv.setText(poidsKilo + newVal);

            }
        });
        poidsGram = poidsKilo;
        cm.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {


            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

                tv.setText(poidsGram + newVal+"m");


            }
        });
        return myInflatedView;
    }


}

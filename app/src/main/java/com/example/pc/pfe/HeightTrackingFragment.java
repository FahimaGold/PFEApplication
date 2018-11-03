package com.example.pc.pfe;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeightTrackingFragment extends Fragment {

    public String hauteurMetre;
    public String hauteurCenti;
    public HeightTrackingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View myInflatedView = inflater.inflate( R.layout.fragment_height_tracking, container, false );
        final TextView tv = (TextView)myInflatedView.findViewById(R.id.valeurTaille);
        NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.hauteurSm);
        NumberPicker cm = (NumberPicker)myInflatedView.findViewById(R.id.hauteurSc);


        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setMinValue(1);
        np.setMaxValue(2);
        np.setWrapSelectorWheel(true);

        cm.setMinValue(0);
        cm.setMaxValue(99);
        cm.setWrapSelectorWheel(true);



        hauteurMetre = tv.getText().toString()+".";
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                tv.setText(hauteurMetre + newVal);

            }
        });
        hauteurCenti = hauteurMetre;
        cm.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {


            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker

                tv.setText(hauteurCenti + newVal+"m");


            }
        });
        return myInflatedView;
    }

}

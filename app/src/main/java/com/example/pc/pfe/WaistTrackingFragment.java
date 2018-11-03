package com.example.pc.pfe;


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
public class WaistTrackingFragment extends Fragment {

    public String tour;

    public WaistTrackingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate( R.layout.fragment_waist_tracking, container, false );
        final TextView tv = (TextView)myInflatedView.findViewById(R.id.valeurTourDeTaille);
        NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.tourDeTailleSm);



        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setMinValue(50);
        np.setMaxValue(300);
        np.setWrapSelectorWheel(true);




        tour = tv.getText().toString()+"";
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                tv.setText(tour+ newVal);

            }
        });

        return myInflatedView;
    }

}

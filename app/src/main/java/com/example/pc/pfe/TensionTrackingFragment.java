package com.example.pc.pfe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class TensionTrackingFragment extends Fragment {

     private Button saveTension;
     private TensionTracking tensionTracking;
     private DatabaseHelper databaseHelper;

    public TensionTrackingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myInflatedView = inflater.inflate( R.layout.fragment_tension_tracking, container, false );
        final TextView syst = (TextView)myInflatedView.findViewById(R.id.systS);
        final NumberPicker np = (NumberPicker)myInflatedView.findViewById(R.id.systolicS);

        final TextView diast = (TextView)myInflatedView.findViewById(R.id.diastS);
        final NumberPicker piko = (NumberPicker)myInflatedView.findViewById(R.id.diastolicS);
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

         tensionTracking =(TensionTracking) getContext();
         saveTension = tensionTracking.sauvTension;
         saveTension.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String systolic ="" + np.getValue();
                 String diastolic = "" + piko.getValue();
                 Systolic sysT = new Systolic();
                 Diastolic diasT = new Diastolic();

                 sysT.setValeur( systolic );
                 diasT.setValeur( diastolic );

                 databaseHelper.ajouterSystolic( sysT );
                 databaseHelper.ajouterDiastolic( diasT );
                 Toast.makeText( tensionTracking, "systo est" + systolic, Toast.LENGTH_SHORT ).show();
                 Toast.makeText( tensionTracking, "diasto est" + diastolic, Toast.LENGTH_SHORT ).show();
             }
         } );
        // Inflate the layout for this fragment
        return myInflatedView;
    }

}

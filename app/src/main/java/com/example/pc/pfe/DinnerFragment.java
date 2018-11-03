package com.example.pc.pfe;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class DinnerFragment extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private Random randomGenerator;

    public DinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate( R.layout.fragment_dinner, container, false );

        textView1 = (myInflatedView).findViewById( R.id.dinner1 );
        textView2= (myInflatedView).findViewById( R.id.dinner2 );

        randomGenerator = new Random(  );

        ArrayList<String> poss1 = new ArrayList<>(  );
        ArrayList<String> poss2= new ArrayList<>(  );

        poss1.add("Salade");
        poss1.add("œufs");
        poss1.add("Légumes");
        poss2.add("Moins de pattes");
        poss2.add("Viande");
        poss2.add("Sardine");
        int index1 = randomGenerator.nextInt(poss1.size());
        textView1.setText( poss1.get(index1) );



        int index2 = randomGenerator.nextInt(poss2.size());
        textView2.setText( poss2.get(index2) );

        return myInflatedView;
    }

}

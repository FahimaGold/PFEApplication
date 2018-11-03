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
public class BreakfastFragment extends Fragment {

     private TextView textView1;
     private TextView textView2;
    private Random randomGenerator;

    public BreakfastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflatedView = inflater.inflate( R.layout.fragment_breakfast, container, false );
        textView1 = (myInflatedView).findViewById( R.id.breakfast1 );
        textView2= (myInflatedView).findViewById( R.id.breakfast2 );

        randomGenerator = new Random(  );

        ArrayList<String> poss1 = new ArrayList<>(  );
        ArrayList<String> poss2= new ArrayList<>(  );

        poss1.add("Fromage");
        poss1.add("Lait");

        int index1 = randomGenerator.nextInt(poss1.size());
        textView1.setText( poss1.get(index1) );

        poss2.add("1/4 du pain + 2 cuillère à caffé de beurre");
        poss2.add("1/2 croissant + 2 cuillère à caffé de beurre");

        int index2 = randomGenerator.nextInt(poss2.size());
        textView2.setText( poss2.get(index2) );




        return myInflatedView;
    }

}

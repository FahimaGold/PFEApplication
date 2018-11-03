package com.example.pc.pfe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class DiabeteFragment extends Fragment {

   // private RadioGroup radioButtonGroup;
    private CreateAccount createAccount;
    private View view;

    public DiabeteFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_diabete, container, false);
        final RadioGroup radioGroup = (view).findViewById(R.id.typeDiabete);


        // find the radiobutton by returned id

        createAccount = (CreateAccount) getContext();
        Button btn = createAccount.getBtnPart();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton  = (RadioButton)(view).findViewById(selectedId);
                String typeD = radioButton.getText().toString();
                Intent i=new Intent(getContext(), AboutYou.class);
                i.putExtra("typeDiab",typeD);
                startActivity( i );


                // String dateDeN=this.date.toString();
                ; //Generate a toast only if you want
                // If you want to continue on that TimeDateActivity
                // If you want to go to new activity that code you can also write here
            }});

        return view;
    }




}

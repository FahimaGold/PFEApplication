package com.example.pc.pfe;


import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MedicalInformationFragment extends Fragment {

    private EditText typeDiabeteInfoMed;
    private EditText fumeurInfoMed;
    private EditText stressInfoMed;
    private EditText sportInfoMed;
    private MedicalInformation medicalInformation;
    private EditText poidsInfoMed;
    private EditText tourDeTailleInfoMed;
    private EditText tailleInfoMed;
    private EditText historiqueInfoMed;
    private EditText tensionSystolicInfoMed;
    private EditText tensionDiastolicInfoMed;
    private Button enregistrerInfoMed;
    private Button annulerInfoMed;
    private DatabaseHelper databaseHelper;
    private InfoMed infoMed;
    private InfoMed infoMed1;
    private Poids poidsT;
    private TourDeTaille tourDeTaille;
    private Taille tailleSuiv;

    public MedicalInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myInflatedView = inflater.inflate( R.layout.fragment_medical_information, container, false );
        typeDiabeteInfoMed = (EditText) myInflatedView.findViewById( R.id.typeDiabeteInfoMed );
        fumeurInfoMed = (EditText) myInflatedView.findViewById( R.id.fumeurInfoMed );
        stressInfoMed = (EditText) myInflatedView.findViewById( R.id.stressInfoMed );
        sportInfoMed = (EditText) myInflatedView.findViewById( R.id.sportInfoMed );
        poidsInfoMed = (EditText) myInflatedView.findViewById( R.id.poidsInfoMed );
        tourDeTailleInfoMed = (EditText) myInflatedView.findViewById( R.id.tourDeTailleInfoMed );
        tailleInfoMed = (EditText) myInflatedView.findViewById( R.id.tailleInfoMed );
        historiqueInfoMed = (EditText) myInflatedView.findViewById( R.id.historiqueInfoMed );
        tensionSystolicInfoMed = (EditText) myInflatedView.findViewById( R.id.tensionSystolicInfoMed );
        tensionDiastolicInfoMed = (EditText) myInflatedView.findViewById( R.id.tensionDiastolicInfoMed );
        enregistrerInfoMed = (Button) myInflatedView.findViewById( R.id.enregistrerModificationInfoMed );
        annulerInfoMed = (Button) myInflatedView.findViewById( R.id.annulerModificationInfoMed);

        enregistrerInfoMed.setVisibility( myInflatedView.GONE );
        annulerInfoMed.setVisibility( myInflatedView.GONE );

        databaseHelper = new DatabaseHelper( getContext() );
        poidsT = new Poids();
        tourDeTaille = new TourDeTaille();
        tailleSuiv = new Taille();


        infoMed = new InfoMed(  );
        typeDiabeteInfoMed.setFocusable( false );
        fumeurInfoMed.setFocusable( false );
        stressInfoMed.setFocusable( false );
        sportInfoMed.setFocusable( false );
        historiqueInfoMed.setFocusable( false );
        poidsInfoMed.setFocusable( false );
        tailleInfoMed.setFocusable( false );
        tourDeTailleInfoMed.setFocusable( false );
        tensionDiastolicInfoMed.setFocusable( false );
        tensionSystolicInfoMed.setFocusable( false );
        String emailAddress = "";
        if(Login.currentUserEmail!= null) {
            emailAddress = Login.currentUserEmail;
            Log.e("current email:",emailAddress);
        }
        else
        {
            emailAddress = InscriptionFragment.currentEmailUser;
            Log.e("current email:",emailAddress);
        }

        final int idCurrentUser = databaseHelper.getCurrentUserId( emailAddress );
        infoMed = databaseHelper.getMyInfo(  );
        typeDiabeteInfoMed.setText( infoMed.getType() );
        fumeurInfoMed.setText( infoMed.getFumeur() );
        stressInfoMed.setText( infoMed.getStress() );
        sportInfoMed.setText( infoMed.getSport() );
        historiqueInfoMed.setText( infoMed.getHistorique() );
        tourDeTailleInfoMed.setText( infoMed.getTourDeTaille() );
        poidsInfoMed.setText(infoMed.getPoids());
        tailleInfoMed.setText( infoMed.getTaille() );
        tensionSystolicInfoMed.setText( infoMed.getSystolic() );
        tensionDiastolicInfoMed.setText( infoMed.getDistolic(  ));

        medicalInformation = (MedicalInformation)getContext();
        Button modifier = medicalInformation.getModifierButton();
        modifier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                typeDiabeteInfoMed.setFocusableInTouchMode( true );
                fumeurInfoMed.setFocusableInTouchMode( true );
                stressInfoMed.setFocusableInTouchMode( true );
                sportInfoMed.setFocusableInTouchMode( true );
                historiqueInfoMed.setFocusableInTouchMode( true );
                tourDeTailleInfoMed.setFocusableInTouchMode( true );
                poidsInfoMed.setFocusableInTouchMode( true );
                tailleInfoMed.setFocusableInTouchMode( true );
                tensionSystolicInfoMed.setFocusableInTouchMode( true );
                tensionDiastolicInfoMed.setFocusableInTouchMode( true );
                enregistrerInfoMed.setVisibility( myInflatedView.VISIBLE );
                annulerInfoMed.setVisibility( myInflatedView.VISIBLE );

            }
        });

        annulerInfoMed.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeDiabeteInfoMed.setFocusable( false );
                fumeurInfoMed.setFocusable( false );
                stressInfoMed.setFocusable( false );
                sportInfoMed.setFocusable( false );
                historiqueInfoMed.setFocusable( false );
                poidsInfoMed.setFocusable( false );
                tailleInfoMed.setFocusable( false );
                tourDeTailleInfoMed.setFocusable( false );
                tensionDiastolicInfoMed.setFocusable( false );
                tensionSystolicInfoMed.setFocusable( false );
                enregistrerInfoMed.setVisibility( myInflatedView.GONE );
                annulerInfoMed.setVisibility( myInflatedView.GONE );
            }
        } );

        enregistrerInfoMed.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoMed1 = new InfoMed(  );

                infoMed1.setType( typeDiabeteInfoMed.getText().toString() );
                infoMed1.setFumeur(fumeurInfoMed.getText().toString()  );
                infoMed1.setStress( stressInfoMed.getText().toString() );
                infoMed1.setSport( sportInfoMed.getText().toString() );
                infoMed1.setHistorique( historiqueInfoMed.getText().toString() );
                infoMed1.setTourDeTaille( tourDeTailleInfoMed.getText().toString() );
                infoMed1.setPoids( poidsInfoMed.getText().toString() );
                infoMed1.setTaille( tailleInfoMed.getText().toString() );
                infoMed1.setSystolic( tensionSystolicInfoMed.getText().toString() );
                infoMed1.setDistolic( tensionDiastolicInfoMed.getText().toString() );

                boolean diff = verifierModificationInfoMed( infoMed, infoMed1 );
                if(diff) {
                    databaseHelper.updateInfoMed( infoMed1 );
                    poidsT.setValeur( poidsInfoMed.getText().toString() );
                    poidsT.setUserId( idCurrentUser );
                    databaseHelper.ajouterPoids( poidsT );
                    tourDeTaille.setValeur(tourDeTailleInfoMed.getText().toString()  );
                    tourDeTaille.setUserId( idCurrentUser );
                    databaseHelper.ajouterTourDeTaille( tourDeTaille );
                    tailleSuiv.setValeur( tailleInfoMed.getText().toString() );
                    tailleSuiv.setUserId( idCurrentUser );
                    databaseHelper.ajouterTaille( tailleSuiv );
                }
                Toast.makeText( medicalInformation, "Vos modifications ont été enregistrées", Toast.LENGTH_SHORT ).show();
                typeDiabeteInfoMed.setFocusable( false );
                fumeurInfoMed.setFocusable( false );
                stressInfoMed.setFocusable( false );
                sportInfoMed.setFocusable( false );
                historiqueInfoMed.setFocusable( false );
                poidsInfoMed.setFocusable( false );
                tailleInfoMed.setFocusable( false );
                tourDeTailleInfoMed.setFocusable( false );
                tensionDiastolicInfoMed.setFocusable( false );
                tensionSystolicInfoMed.setFocusable( false );
                enregistrerInfoMed.setVisibility( myInflatedView.GONE );
                annulerInfoMed.setVisibility( myInflatedView.GONE );
            }
        } );

        return  myInflatedView;
    }

     //Cette méthode a pour objectif de vérifier s'il y a eu une modification dans les informations médicales de l'utilisateur
    public boolean verifierModificationInfoMed(InfoMed infoMed1, InfoMed infoMed2){

        boolean diff = false;

         if( !infoMed1.getType().equals(infoMed2.getType()  )){
             diff = true;
             return diff;
         }

        if( !infoMed1.getStress().equals(infoMed2.getStress()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getSport().equals(infoMed2.getSport()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getHistorique().equals(infoMed2.getHistorique()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getFumeur().equals(infoMed2.getFumeur()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getTourDeTaille().equals(infoMed2.getTourDeTaille()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getPoids().equals(infoMed2.getPoids()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getTaille().equals(infoMed2.getTaille()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getSystolic().equals(infoMed2.getSystolic()  )){
            diff = true;
            return diff;
        }

        if( !infoMed1.getDistolic().equals(infoMed2.getDistolic()  )){
            diff = true;
            return diff;
        }

        return diff;
    }
}

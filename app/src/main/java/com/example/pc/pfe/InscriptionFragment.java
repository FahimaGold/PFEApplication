package com.example.pc.pfe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.List;

import static android.content.Intent.getIntent;


public class InscriptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String currentEmailUser;
    private TextInputEditText prenom;
    private TextInputEditText nom;
    private TextInputEditText email;
    private TextInputEditText motDePasse;
    private TextInputEditText motDePasseConfirm;
    private String date;
    private String genre;
    private String sport;
    private String fumeur;
    private String stress;
    private String typeDiab;
    private String taille;
    private String poids;
    private String tourT;
    private String historique;


    private static final String[] WILAYAS = new String[] {
            "Adrar", "Chlef", "Laghouat", "Oum El Bouaghi", "Batna", "Béjaïa", "Biskra", "Béchar", "Blida", "Bouira", "Tamanrasset", "Tébessa", "Tlemcen",
            "Tiaret", "Tizi Ouzou", "Alger", "Djelfa", "Jijel", "Sétif", "Saïda", "Skikda", "Sidi Bel Abbès", "Annaba", "Guelma", "Constantine", "Médéa",
            "Mostaganem", "MSila", "Mascara", "Ouargla", "Oran", "El Bayadh", "Illizi", "Bordj Bou Arreridj", "Boumerdès", "El Tarf", "Tindouf", "Tissemsilt",
            "El Oued", "Khenchela", "Souk Ahras", "Tipaza", "Mila", "Ain Defla", "Naâma", "Témouchent", "Ghardaïa", "Relizane"
    };


    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private  AutoCompleteTextView textView;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Utilisateur user;
    private InfoMed infoMed;
    private Poids poidsT;
    private TourDeTaille tourDeTaille;
    private Taille tailleSuiv;

    public InscriptionFragment() {

    }

    public TextInputEditText getEmail() {
        return email;
    }

    public TextInputEditText getMotDePasse() {
        return motDePasse;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_inscription, container, false);
        Intent intent = getActivity().getIntent();
       // date = intent.getExtras().getString("dateDeNaissance");
        Bundle b=getActivity().getIntent().getExtras();
        final String[] array=b.getStringArray("inscriptionInfos");
        poids = array[0];
        taille = array[1];
        genre = array[2];
        sport = array[3];
        typeDiab = array[4];
        date = array[5];
        tourT = array[6];
        historique = array[7];
        fumeur = array[8];
        stress = array[9];

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_dropdown_item_1line, WILAYAS);
        textView = (AutoCompleteTextView) (inf).findViewById(R.id.wilaya);
        textView.setAdapter(adapter);
        textView.setThreshold(1);
        Log.d("La date", "marche!: " + date);
        Log.d("Le genre", "bbbbbb: " + genre);
        //List<Utilisateur> list = databaseHelper.getAllUser();
        //Log.d("User 1", list.get( 0 ).getEmail());
        initViews(inf);
        //  initListeners();
        initObjects();

        return inf;
    }


    private void initViews(View v) {

       /* prenom = new TextInputEditText( this );
        nom = new TextInputEditText( this );
        email = new TextInputEditText( this );
        motDePasse = new TextInputEditText( this );
        motDePasseConfirm = new TextInputEditText( this );
        textInputLayoutFirstName = new TextInputLayout( this );
        textInputLayoutName = new TextInputLayout( this );
        textInputLayoutEmail = new TextInputLayout( this );
        textInputLayoutPassword = new TextInputLayout( this );
        textInputLayoutConfirmPassword = new TextInputLayout( this );*/

        nestedScrollView = (NestedScrollView) (v).findViewById( R.id.nestedScrollView );
        prenom = (TextInputEditText)(v). findViewById( R.id.prenom );
        nom = (TextInputEditText)(v). findViewById( R.id.nom );
        email = (TextInputEditText)(v). findViewById( R.id.email );
        motDePasse = (TextInputEditText)(v). findViewById( R.id.motDePasse );
        motDePasseConfirm = (TextInputEditText)(v). findViewById( R.id.confirmMotDePasse );
        textInputLayoutFirstName = (TextInputLayout)(v). findViewById( R.id.textInputLayoutFirstName);
        textInputLayoutName = (TextInputLayout)(v). findViewById( R.id.textInputLayoutName );
        textInputLayoutEmail = (TextInputLayout)(v). findViewById( R.id.textInputLayoutEmail );
        textInputLayoutPassword = (TextInputLayout)(v). findViewById( R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout)(v). findViewById( R.id.textInputLayoutConfirmPassword);

    }

    /**
     * This method is to initialize listeners
     */

   /* private void initListeners() {
        inscrEnd.setOnClickListener(this);
        //appCompatTextViewLoginLink.setOnClickListener(this);

    }*/
    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.insrciptionTerm: {
                inscrEnd.setPaintFlags(inscrEnd.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                postDataToSQLite();
                Intent i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                break;
            }


        }
    }*/
    /**
     * This method is to initialize objects to be used
     */


    private void initObjects() {
       inputValidation = new InputValidation(getContext());
        databaseHelper = new DatabaseHelper(getContext());
        user = new Utilisateur();
        infoMed = new InfoMed(  );
        poidsT = new Poids(  );
        tourDeTaille = new TourDeTaille();
        tailleSuiv = new Taille();

    }



    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    public void postDataToSQLite() {


        if (!inputValidation.isInputEditTextFilled(email, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(email, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(motDePasse, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(motDePasse, motDePasseConfirm,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

            if (!databaseHelper.checkUser(email.getText().toString().trim())) {

                user.setPrenom(prenom.getText().toString().trim());
                user.setNom(nom.getText().toString().trim());
                user.setEmail(email.getText().toString().trim());
                user.setMotDePasse(motDePasse.getText().toString().trim());
                user.setDateDeNaissance( date );
                user.setGenre( genre );
                user.setWilaya( textView.getText().toString() );

                Log.e( "Wilaya", textView.getText().toString() );

                InscriptionFragment.currentEmailUser = email.getText().toString().trim();
                long idCurrentUser = databaseHelper.addUser(user);
                Log.e("Coucou mes amis1", "" + databaseHelper.getCurrentUserId( email.getText().toString().trim() ));
                infoMed.setIdUtilisateur( databaseHelper.getCurrentUserId( email.getText().toString().trim() ) );
                Log.e("Coucou mes amis2", "" + infoMed.getIdUtilisateur());
                infoMed.setHistorique( historique );
                infoMed.setStress( stress );
                infoMed.setType( typeDiab );
                infoMed.setFumeur( fumeur );
                infoMed.setSport( sport );
                infoMed.setTourDeTaille( tourT );
                infoMed.setPoids( poids );
                infoMed.setTaille( taille );
                poidsT.setValeur( poids );
                poidsT.setUserId( databaseHelper.getCurrentUserId( InscriptionFragment.currentEmailUser ) );
                tourDeTaille.setUserId( databaseHelper.getCurrentUserId( InscriptionFragment.currentEmailUser ) );
                tourDeTaille.setValeur( tourT );
                tailleSuiv.setValeur( taille );
                tailleSuiv.setUserId(databaseHelper.getCurrentUserId( InscriptionFragment.currentEmailUser )   );
                databaseHelper.addInfo( infoMed );
                databaseHelper.ajouterPoids( poidsT );
                databaseHelper.ajouterTourDeTaille( tourDeTaille );
                databaseHelper.ajouterTaille( tailleSuiv );
                // Snack Bar to show success message that record saved successfully
                Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                emptyInputEditText();
                String type = infoMed.getType();
                if(type.equals( "Type 1" ) || type.equals( "Type 2" )) {
                    Intent i = new Intent( getContext(), HomeDiabetic.class );
                    startActivity( i );
                }
                else
                {
                    Intent i = new Intent( getContext(), Home.class );
                    startActivity( i );
                }




            }else {
            // Snack Bar to show error message that record already exists
            if(nestedScrollView != null) {
                Snackbar.make( nestedScrollView, getString( R.string.error_email_exists ), Snackbar.LENGTH_LONG ).show();
            }
        }


    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        prenom.setText(null);
        nom.setText(null);
        email.setText(null);
        motDePasse.setText(null);
        motDePasseConfirm.setText(null);
    }

}

package com.example.pc.pfe;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Note extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText noteContent;
    private static Note note;
    private int idNote;
    private Button sauvNote;
    private String contenu;
    private String dateCreation;
    private int idGlucose;
    private Button btnAff;
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void goToNotesList(View view){
        Log.e( "going", "Note List Activity" );
        Intent i = new Intent(getApplicationContext(),NoteList.class);
        startActivity(i);
    }

    public Note(){

    }



    public Note(int idNote, String contenu, String dateCreation) {
        this.idNote = idNote;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getIdGlucose() {
        return idGlucose;
    }

    public void setIdGlucose(int idGlucose) {
        this.idGlucose = idGlucose;
    }

    public void sauvNote(View v) {

        databaseHelper = new DatabaseHelper( this );
        noteContent = findViewById( R.id.note );

        int idGlucose = databaseHelper.getLastInsertedGlucose();
        int exi = databaseHelper.checkIdGlucose( idGlucose );
        Date currentTime = Calendar.getInstance().getTime();
        Log.e( "note content", "" + exi);
        note = new Note();
            note.setContenu( noteContent.getText().toString() );
            Log.e( "le contenu de la note", note.getContenu() );
            note.setDateCreation( currentTime.toString() );
            note.setIdGlucose( idGlucose );
            databaseHelper.addNote( note );
            //Toast.makeText( note, "new note" + note.getContenu(), Toast.LENGTH_SHORT ).show();
            Intent i = new Intent( getApplicationContext(), NoteList.class );
            startActivity( i );

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_note );

        btnAff = findViewById( R.id.afficherListeNotes );
        Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
        if (toolbar != null) {
            toolbar.setTitle( "PFEApp" );
            setSupportActionBar( toolbar );//To display toolbar
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled( true );
                ab.setDisplayShowHomeEnabled( true );
                ab.setDisplayShowTitleEnabled( false );


            }

        }



        }
    }


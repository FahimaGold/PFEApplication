package com.example.pc.pfe;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteList extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    private List<Note> trackingListContentList = new ArrayList<>();
    private  DatabaseHelper databaseHelper;
    private Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_note_list );

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (toolbar != null)
        {
            toolbar.setTitle("PFEApp");
            setSupportActionBar(toolbar);//To display toolbar
            ActionBar ab = getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(true);
                ab.setDisplayShowHomeEnabled(true);
                ab.setDisplayShowTitleEnabled(false);


            }

        }

        recyclerView = (RecyclerView) findViewById( R.id.recycler_view_note );
        mAdapter = new NoteAdapter( trackingListContentList );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( mLayoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.addItemDecoration( new DividerItemDecoration( this, LinearLayoutManager.VERTICAL ) );
        recyclerView.setAdapter( mAdapter );

        prepareTrackingData();

        recyclerView.addOnItemTouchListener( new RecyclerTouchListener( getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Note trackingListContent = trackingListContentList.get( position );
                Toast.makeText( getApplicationContext(), trackingListContent.getContenu() + " is selected!", Toast.LENGTH_SHORT ).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        } ) );
    }

    private void prepareTrackingData() {
       Note n;
        List<Note> list = new ArrayList<Note>();

      Log.e( "Let us", "note list" );
         databaseHelper = new DatabaseHelper( this );
        list = databaseHelper.getNotesList();
        Log.e( "taille", "de la liste " + list.size() );
      if(list.size() > 0) {
          Log.e( "koukou", "chaima " + list.size() );
          for(int i=0; i< list.size();i++){
              n = new Note();
              n.setContenu( list.get(i).getContenu() );
              n.setDateCreation( list.get(i).getDateCreation() );
              trackingListContentList.add( n );
          }
      }
      else
          Toast.makeText( this, "Pas de notes", Toast.LENGTH_SHORT ).show();

        /*Note cList = new Note( 1, "Shit", "26-09-2018");
      Log.e( "item1", "" + cList.getContenu() );
            trackingListContentList.add( cList );
        cList = new Note( 2, "Hell no", "26-09-2018");

        trackingListContentList.add( cList );

        cList =  new Note( 3, "Shit", "26-09-2018");

        trackingListContentList.add( cList );

        cList = new Note ( 4, "Shit", "26-09-2018");
        trackingListContentList.add( cList );

        cList = new Note ( 5, "Shit", "26-09-2018");
            trackingListContentList.add( cList );*/


        mAdapter.notifyDataSetChanged();
    }

}



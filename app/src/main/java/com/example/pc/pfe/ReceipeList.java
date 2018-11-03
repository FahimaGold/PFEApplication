package com.example.pc.pfe;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReceipeList extends AppCompatActivity {

    private Toolbar toolbar ;
    private List<ReceipeListContent> receipeListContents = new ArrayList<>();
    private RecyclerView recyclerView;
    private ReceipeAdapter mAdapter;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_receipe_list );
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



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_receipe);

        mAdapter = new ReceipeAdapter(receipeListContents);



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        recyclerView.setAdapter(mAdapter);

        prepareTrackingData();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ReceipeListContent receipe = receipeListContents.get(position);
                Toast.makeText(getApplicationContext(), receipe.getReceipeName() + " is selected!", Toast.LENGTH_SHORT).show();
               /* if(position == 0) {
                    Intent i = new Intent(getApplicationContext(),GlucoseTracking.class);
                    startActivity(i);
                }
                */
                switch (position){
                    case 0:
                    {
                        Intent i = new Intent(getApplicationContext(),GlucoseTracking.class);
                        startActivity(i);
                    }
                    break;
                    case 1:
                    {
                        Intent i = new Intent(getApplicationContext(),TensionTracking.class);
                        startActivity(i);
                    }
                    break;
                    case 2:
                    {
                        Intent i = new Intent(getApplicationContext(),WeightGraphs.class);
                        startActivity(i);
                    }
                    break;
                    case 3:
                    {
                        Intent i = new Intent(getApplicationContext(),ReceipeOverview.class);
                        startActivity(i);
                    }
                    break;
                    case 4:
                    {
                        Intent i = new Intent(getApplicationContext(),HeightGraphs.class);
                        startActivity(i);
                    }
                    break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

                return true;
        }



    private void prepareTrackingData() {
        ReceipeListContent cList = new ReceipeListContent("Calzones de maïs et de brocoli");
        receipeListContents.add(cList);

        cList = new ReceipeListContent("Trempette aux haricots superposés du sud-ouest");
        receipeListContents.add(cList);

        cList = new ReceipeListContent("Frites au four pour deux");
        receipeListContents.add(cList);

        cList = new ReceipeListContent("Macque Choux");
        receipeListContents.add(cList);

        cList = new ReceipeListContent("Maïs grillé mexicain");
        receipeListContents.add(cList);
        cList = new ReceipeListContent("Salade de couscous et de fruits");
        receipeListContents.add(cList);
        cList = new ReceipeListContent("Popsicles aux pêches épaisses");
        receipeListContents.add(cList);
        cList = new ReceipeListContent("Salade de pommes de terre de campagne");
        receipeListContents.add(cList);
        cList = new ReceipeListContent("Le gingembre de Haymaker");
        receipeListContents.add(cList);
        cList = new ReceipeListContent("Crevettes Po'Boy");
        receipeListContents.add(cList);
        cList = new ReceipeListContent("Crème de coriandre");
        receipeListContents.add(cList);
        cList = new ReceipeListContent("Ketchup aux cerises");
        receipeListContents.add(cList);


         mAdapter.notifyDataSetChanged();

    }
}

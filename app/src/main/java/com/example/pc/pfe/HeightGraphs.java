package com.example.pc.pfe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class HeightGraphs extends AppCompatActivity {
   private Button suiviTaille;
   private Taille taille;
   private DatabaseHelper databaseHelper;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_height_graphs );
        suiviTaille= findViewById( R.id.btnVisualisationTaille );
        taille = new Taille();
        databaseHelper = new DatabaseHelper( this );
       List<Taille> liste = new ArrayList<>(  );

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
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


        LineChart chart = (LineChart) findViewById(R.id.chartTaille);
        liste = new ArrayList<>();
        List<String> labels = new ArrayList<>(  );
        liste = databaseHelper.getMesuresTaille();
        ArrayList<Entry> entries = new ArrayList<>();

        for(int i=0; i < liste.size();i++){
            taille = new Taille();
            taille = (Taille) liste.get(i);
            Float val = Float.parseFloat(taille.getValeur() );
            entries.add(new Entry(i, val));
            labels.add("Prise "+ (i + 1));
        }



        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter( new IAxisValueFormatter()
        { @Override
        public String getFormattedValue(float value, AxisBase axis) { return null; }

        });

        LineDataSet dataset = new LineDataSet(entries, "Poids");
        LineData data = new LineData(dataset);
        chart.setData(data);

        dataset.setColor( Color.parseColor( "#00c85d" ));
        dataset.setLineWidth( 5 );
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        chart.getAxisLeft().setStartAtZero(false);
        chart.getAxisRight().setStartAtZero(false);



        //chart.setData(new LineData(labels, lines));
        chart.animateY(600);

    }
}

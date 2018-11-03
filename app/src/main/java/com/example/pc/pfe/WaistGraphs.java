package com.example.pc.pfe;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

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

public class WaistGraphs extends AppCompatActivity {

    private TourDeTaille tourDeTaille;
    private DatabaseHelper databaseHelper;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_waist_graphs );
        databaseHelper = new DatabaseHelper( this );
        tourDeTaille = new TourDeTaille();
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


        LineChart chart = (LineChart) findViewById(R.id.chartTourDeTaille);
        List<TourDeTaille> liste = new ArrayList<>(  );
        List<String> labels = new ArrayList<>(  );
        liste = databaseHelper.getMesuresTourDeTaille();
        ArrayList<Entry> entries = new ArrayList<>();


        Toast.makeText( this, "List size" + liste.size(), Toast.LENGTH_SHORT ).show();
        for(int i=0; i < liste.size();i++){
            tourDeTaille = new TourDeTaille();
            tourDeTaille = (TourDeTaille) liste.get(i);

            Float val = Float.parseFloat(tourDeTaille.getValeur() );
            entries.add(new Entry(i, val));
            Toast.makeText( this, "Hey there" + val, Toast.LENGTH_SHORT ).show();
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

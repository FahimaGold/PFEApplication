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

public class TensionGraphs extends AppCompatActivity {

    private double tensionSystolic;
    private double  tensionDiastolic;

    private Systolic systolic;
    private Diastolic diastolic;
    private DatabaseHelper databaseHelper;

    public TensionGraphs(){

    }
    public TensionGraphs( double tensionSystolic,double  tensionDiastolic){
        this.tensionSystolic = tensionSystolic;
        this.tensionDiastolic = tensionDiastolic;
    }

    public double getTensionSystolic(){
        return this.tensionSystolic;
    }

    public double getTensionDiastolic(){
        return this.tensionDiastolic;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_tension_graphs );
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

        databaseHelper = new DatabaseHelper( this );
        systolic = new Systolic();
        diastolic = new Diastolic();
        LineChart chart = (LineChart) findViewById(R.id.chartTension);
        List<Systolic> liste1 = new ArrayList<>(  );
        List<Diastolic> liste2 = new ArrayList<>(  );
        List<String> labels = new ArrayList<>(  );
        liste1 = databaseHelper.getMesuresSystolic();
        liste2 = databaseHelper.getMesuresDiastolic();
        ArrayList<Entry> entries1 = new ArrayList<>();
        ArrayList<Entry> entries2 = new ArrayList<>();

        Toast.makeText( this, "List size" + liste1.size(), Toast.LENGTH_SHORT ).show();
        for(int i=0; i < liste1.size();i++){
            systolic = new Systolic();
            systolic = (Systolic) liste1.get(i);

            Float val = Float.parseFloat(systolic.getValeur() );
            entries1.add(new Entry(i, val));
            Toast.makeText( this, "Hey there" + val, Toast.LENGTH_SHORT ).show();
            labels.add("Prise "+ (i + 1));

        }
        for(int j=0; j < liste2.size();j++){
            diastolic = new Diastolic();
            diastolic = (Diastolic) liste2.get(j);

            Float val = Float.parseFloat(diastolic.getValeur() );
            entries2.add(new Entry(j, val));
            Toast.makeText( this, "Hey there" + val, Toast.LENGTH_SHORT ).show();
            labels.add("Prise "+ (j + 1));

        }

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter( new IAxisValueFormatter()
        { @Override
        public String getFormattedValue(float value, AxisBase axis) { return null; }

        });

        LineDataSet dataset1 = new LineDataSet(entries1, "Tension systolique");
        LineDataSet dataset2 = new LineDataSet(entries2, "Tension diastolique");
        LineData data = new LineData(dataset1, dataset2);
        chart.setData(data);

        dataset1.setColor( Color.parseColor( "#00c85d" ));
        dataset1.setLineWidth( 5 );

        dataset2.setColor( Color.parseColor( "#ff6d08" ));
        dataset2.setLineWidth( 5 );
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        chart.getAxisLeft().setStartAtZero(false);
        chart.getAxisRight().setStartAtZero(false);



        //chart.setData(new LineData(labels, lines));
        chart.animateY(600);

    }
}

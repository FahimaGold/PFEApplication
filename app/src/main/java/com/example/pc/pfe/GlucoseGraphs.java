package com.example.pc.pfe;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.opengl.GLU;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

//import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.listener.OnDrawListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.LabelFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GlucoseGraphs extends AppCompatActivity implements OnChartValueSelectedListener {
    private String xStamp;
    private double  mesure;
    private BarChart chart;
    private DatabaseHelper databaseHelper;
    private  List listGlucose;
    public GlucoseGraphs(){

    }
    public GlucoseGraphs( String xStamp,double  mesure){
        this.xStamp = xStamp;
        this.mesure = mesure;
    }

    public String getStamp(){
        return this.xStamp;
    }

    public double getMesure(){
        return this.mesure;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void exporterImageGlucose(View v){
        chart = (BarChart) findViewById( R.id.chart );
        //getViewBitmap( v );
        Toast.makeText( this, "Trying to save image", Toast.LENGTH_SHORT ).show();
        //chart.saveToGallery("suivi_glucose.png", 100);
        chart.saveToGallery( "koukou.png",50 );
        Toast.makeText( this, "should be saved now", Toast.LENGTH_SHORT ).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_glucose_graphs );
        ScrollView scrollView = (ScrollView) findViewById( R.id.scrollView2 );
        databaseHelper = new DatabaseHelper( this );
        List<Glucose> glucoseList = new ArrayList<Glucose>();
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

        listGlucose = databaseHelper.getMesuresGlucose();

        Glucose glu1 = new Glucose();
        if (listGlucose.size() > 0) {
            glu1 = (Glucose) listGlucose.get( 0 );
            Log.e( "Let us finnd out2 ", "" + glu1.getTauxGlucose() );
            // Toast.makeText( this, "Here is val " + val1, Toast.LENGTH_SHORT ).show();
            chart = (BarChart) findViewById( R.id.chart );
            boolean sameDay = false;


            int jour = 1;

            List<String> xValues = new ArrayList<>();
            ArrayList<BarDataSet> dataSets = new ArrayList<>();

            float groupSpace = 0.01f;
            float barSpace = 0.2f;
            float barWidth = 0.001f;
            chart.setDrawBarShadow( false );
            chart.setDrawValueAboveBar( true );

            chart.setOnChartValueSelectedListener( this );
            //chart.setOnDrawListener( (OnDrawListener) this );
            chart.setMaxVisibleValueCount( 200 );
            chart.setPinchZoom( false );
            chart.setDrawGridBackground( false );
            XAxis xl = chart.getXAxis();

            ArrayList<BarEntry> entries0 = new ArrayList<>();
            ArrayList<BarEntry> entries1 = new ArrayList<>();
            ArrayList<BarEntry> entries2 = new ArrayList<>();
            ArrayList<BarEntry> entries3 = new ArrayList<>();
            ArrayList<BarEntry> entries4 = new ArrayList<>();
            ArrayList<BarEntry> entries5 = new ArrayList<>();


            Date d1 = null;
            Date d2;
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            BarDataSet set0 = null;
            BarDataSet set1 = null;
            BarDataSet set2 = null;
            BarDataSet set3 = null;
            BarDataSet set4 = null;
            BarDataSet set5 = null;
            BarData data = null;

            String stamp = null;
            // refresh

            ArrayList<String> stamps = new ArrayList<>();
            ArrayList<BarDataSet> ens = new ArrayList<>();
            for (int i = 0; i < listGlucose.size(); i++) {
                Log.e( "cal1", "" + cal1 );
                Glucose glucose1 = new Glucose();
                glucose1 = (Glucose) listGlucose.get( i );
                String dateMesure1 = glucose1.getDateMesure();

                Glucose glucose2 = new Glucose();
                if (i + 1 < listGlucose.size()) {
                    glucose2 = (Glucose) listGlucose.get( i + 1 );
                }
                String dateMesure2 = glucose2.getDateMesure();


                Log.e( "dateMesure1", "" + dateMesure1 );
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
                    d1 = sdf.parse( dateMesure1 );
                    cal1.setTime( d1 );
                    Log.d( "after date", "" + d1 );

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (listGlucose.size() >= 2 && dateMesure2 != null) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
                        d2 = sdf.parse( dateMesure2 );
                        cal2.setTime( d2 );
                        Log.e( "second date", "" + d2 );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    sameDay = cal1.get( Calendar.YEAR ) == cal2.get( Calendar.YEAR ) &&
                            cal1.get( Calendar.DAY_OF_YEAR ) == cal2.get( Calendar.DAY_OF_YEAR );
                    //Toast.makeText( this, "sameDay is " + sameDay, Toast.LENGTH_SHORT ).show();
                }
                Toast.makeText( this, "sameDay is " + sameDay, Toast.LENGTH_SHORT ).show();
                if (sameDay || listGlucose.size() < 2) {
                    String taux = glucose1.getTauxGlucose();
                    Float tauxF = Float.parseFloat( taux );
                    Log.e( "tauxF", "" + tauxF );

                    stamp = glucose1.getStamp();
                    int stampI = Integer.parseInt( stamp );
                    Log.e( "stampI", "" + stampI );

                    if (stampI == 0) {

                        entries0.add( new BarEntry( i, tauxF ) );
                        xValues.add( glucose1.getDateMesure() );
                   /* set0 = new BarDataSet( entries, "Avant petit-déjeuner" );
                    stamps.add( "" + stampI );
                    set0.setColor( Color.BLUE );
                    ens.add( set1 );*/

                    }
                    if (stampI == 1) {

                        entries1.add( new BarEntry( i, tauxF ) );
                        xValues.add( glucose1.getDateMesure() );
                    /*set1 = new BarDataSet( entries1, "Après déjeuner" );
                    stamps.add( "" + stampI );
                    set1.setColor( Color.GREEN );
                    ens.add( set1 );*/

                    }
                    if (stampI == 2) {
                        entries2.add( new BarEntry( i, tauxF ) );
                        xValues.add( glucose1.getDateMesure() );
                   /* stamps.add( "" + stampI );
                    set2 = new BarDataSet( entries, "déjeuner" );
                    set2.setColor( Color.parseColor( "#00c3f3" ) );
                    ens.add( set1 );*/

                    }
                    if (stampI == 3) {
                        entries3.add( new BarEntry( i, tauxF ) );
                        xValues.add( glucose1.getDateMesure() );
                    /*stamps.add( "" + stampI );
                    set1 = new BarDataSet( entries, "Après déjeuner" );
                    set1.setColor( Color.parseColor( "#ff6d08" ) );
                    ens.add( set1 );*/

                    }
                    if (stampI == 4) {
                        entries4.add( new BarEntry( i, tauxF ) );
                        xValues.add( glucose1.getDateMesure() );
                   /* stamps.add( "" + stampI );
                    set1 = new BarDataSet( entries, "Avant dinner" );
                    set1.setColor( Color.parseColor( "#0a2150" ) );
                    ens.add( set1 );*/

                    }

                    if (stampI == 5) {
                        entries5.add( new BarEntry( i, tauxF ) );
                        xValues.add( glucose1.getDateMesure() );
                    /*stamps.add( "" + stampI );
                    set1 = new BarDataSet( entries, "Après dinner" );
                    set1.setColor( Color.GRAY );
                    ens.add( set1 );*/

                    }

                    //data = new BarData( set1 );
                    //chart.setData(data);


                } else {
                    //set1 = new BarDataSet(entries, "Glucose");
               /* data = new BarData(  );
                for(int j=0; j< ens.size();j++) {
                    data.addDataSet( ens.get(j) );
                    data.setBarWidth(barWidth);
                    chart.setData(data);
                    // perform the "explicit" grouping
                    chart.invalidate();*/

                }
                //hart.groupBars(0f, groupSpace, barSpace);

                //set1.setColor( Color.GREEN );

                // xValues.add( "jour " + jour );
                // jour++;
                // entries = new ArrayList<>();
                XAxis xAxis = chart.getXAxis();
                xAxis.setValueFormatter( new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return null;
                    }

                } );

                BarDataSet dataset0 = new BarDataSet( entries0, "Avant petit déjeûner" );
                BarDataSet dataset1 = new BarDataSet( entries1, "Après petit déjêuner" );
                BarDataSet dataset2 = new BarDataSet( entries2, "Avant déjeûner" );
                BarDataSet dataset3 = new BarDataSet( entries3, "Après déjeûner" );
                BarDataSet dataset4 = new BarDataSet( entries4, "Avant dinner" );
                BarDataSet dataset5 = new BarDataSet( entries5, "Après dinner" );

                BarData dat = new BarData( dataset0, dataset1, dataset2, dataset3, dataset4, dataset5 );
                // dat.groupBars(0f, groupSpace, barSpace);

                chart.setData( dat );

                dataset0.setColor( Color.parseColor( "#00c85d" ) );
                dataset1.setColor( Color.parseColor( "#ff6d08" ) );
                dataset2.setColor( Color.parseColor( "#f9a825" ) );
                dataset3.setColor( Color.parseColor( "#eceff1" ) );
                dataset4.setColor( Color.parseColor( "#830303" ) );
                dataset5.setColor( Color.parseColor( "#0a2150" ) );


                //dataset.setBarWidth( 5 );
                chart.getXAxis().setValueFormatter( new IndexAxisValueFormatter( xValues ) );

                // dat.groupBars(0, 0.45f, 0.1f);
                chart.getAxisLeft().setStartAtZero( false );
                chart.getAxisRight().setStartAtZero( false );


            }


            //chart.setData(new LineData(labels, lines));
            chart.animateY( 600 );


        }

    }






    public String checkDigit (int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }






    @Override
    public void onValueSelected(Entry e, Highlight h) {



    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

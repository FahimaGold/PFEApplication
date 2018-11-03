package com.example.pc.pfe;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView term;

    public void creerCompte(View v) {
        // TODO Auto-generated method stub
        Intent i = new Intent( getApplicationContext(), CreateAccount.class );
        startActivity( i );

    }

    public void login(View v) {
        // TODO Auto-generated method stub
        Intent i = new Intent( getApplicationContext(), Login.class );
        startActivity( i );

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        term = findViewById( R.id.termesCond );
        /*List<Utilisateur> list = new ArrayList<Utilisateur>();
        Utilisateur user = new Utilisateur(  );
        InfoMed infoMed = new InfoMed(  );*/
        // list = databaseHelper.getAllUser();
        // Create the dummy account

        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setCancelable(true);
                builder.setTitle("Termes et conditions");
                builder.setMessage("En utilisant cette application, vous devez agréer que votre données médicales seront envoyés au serveur de ministère de santé. Néanmoins, nous vous garantissons la protection de vos informations contre toute mauvaise utilisation et que votre identité reste toujours cachée et vos seront seulement utilisées pour des statistiques et pour le bien de la santé générale");

                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dialogInterface.cancel();
                        finish();
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //alertTextView.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            }
        });


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 4);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        //Pour le nombre de pas








    }


}
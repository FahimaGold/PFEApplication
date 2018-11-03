package com.example.pc.pfe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.opengl.GLU;
import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pc on 04/05/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super( context, name, factory, version );
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super( context, name, factory, version, errorHandler );
    }


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "diabteDB.db";

    // User table name
    private static final String TABLE_USER = "utilisateur";

    //DataBase Helper
    private DatabaseHelper mOpenHelper;


    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_SURNAME = "user_surname";
    private static final String COLUMN_USER_BIRTH = "user_birth_date";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_GENDER = "user_gender";
    private static final String COLUMN_WILAYA = "user_wilaya";
    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_SURNAME + " TEXT,"
            + COLUMN_USER_BIRTH + " TEXT,"
            + COLUMN_USER_GENDER + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_WILAYA + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";


    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    private static final String TABLE_INFO_MEDICALE = "infoMedicale";

    private static final String COLUMN_INFO_MEDICALE_ID = "info_id";
    private static final String COLUMN_TAILLE = "taille";
    private static final String COLUMN_USER_POIDS = "poids";
    private static final String COLUMN_TOUR_DE_TAILLE = "tour_taille";
    private static final String COLUMN_ANTECEDENTS = "antecedants";
    private static final String COLUMN_SPORT = "sport";
    private static final String COLUMN_STRESS = "stress";
    private static final String COLUMN_FUMER = "fumeur";
    private static final String COLUMN_TYPE_DE_DIABETE = "type";
    private static final String COLUMN_TENSION_SYSTOLIC = "tension_systolique";
    private static final String COLUMN_TENSION_DIASTOLIC = "tension_diastolique";
    private static final String COLUMN_ID_UTILISATEUR = "id_utilisateur";
    private static final String COLUMN_UPDATED = "modifie";
    private static final String COLUMN_INSERTED = "insere";

    private String CREATE_INFO_MEDICALE_TABLE = "CREATE TABLE " + TABLE_INFO_MEDICALE + "("
            + COLUMN_INFO_MEDICALE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TAILLE + " TEXT, "
            + COLUMN_UPDATED + " INTEGER, "
            + COLUMN_INSERTED + " INTEGER, "
            + COLUMN_FUMER + " TEXT,"
            + COLUMN_TYPE_DE_DIABETE + " TEXT,"
            + COLUMN_USER_POIDS + " TEXT,"
            + COLUMN_TOUR_DE_TAILLE + " TEXT,"
            + COLUMN_ANTECEDENTS + " TEXT,"
            + COLUMN_SPORT + " TEXT,"
            + COLUMN_STRESS + " TEXT,"
            + COLUMN_TENSION_SYSTOLIC + " TEXT,"
            + COLUMN_TENSION_DIASTOLIC + " TEXT,"
            + COLUMN_ID_UTILISATEUR + " INTEGER," + " FOREIGN KEY (" + COLUMN_ID_UTILISATEUR + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";


    private String DROP_INFO_MEDICALE_TABLE = "DROP TABLE IF EXISTS " + TABLE_INFO_MEDICALE;


    /*    Table Note     */

    //Note table name

    private static final String TABLE_NOTE= "note";

    private static final String COLUMN_NOTE_CONTENT = "contenu";
    private static final String COLUMN_CREATION_DATE = "creation_date";
    private static final String COLUMN_NOTE_ID = "note_id";
    private static final String COLUMN_GLUCOSE_ID = "glucose_id";
    private static final String COLUMN_NOTE_WRITER = "user_id";

    // create table sql query
    private String CREATE_NOTE_TABLE = "CREATE TABLE " + TABLE_NOTE + "("
            + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOTE_CONTENT + " TEXT,"
            + COLUMN_CREATION_DATE + " TEXT,"
            + COLUMN_NOTE_WRITER + " INTEGER,"
            + COLUMN_GLUCOSE_ID + " INTEGER," + " FOREIGN KEY (" + COLUMN_GLUCOSE_ID + ") REFERENCES "
            + TABLE_GLUCOSE + "(" + COLUMN_MESURE_ID + "));";



    // drop table sql query
    private String DROP_NOTE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NOTE;


      // Table glucose

    private static final String TABLE_GLUCOSE = "glucose";



    // Glucose Table Columns names
    private static final String COLUMN_MESURE_ID = "mesure_id";
    private static final String COLUMN_MESURE_VALUE = "valeur_glucose";
    private static final String COLUMN_STAMP = "stamp";
    private static final String COLUMN_DATE_MESURE = "date_mesure";
    private static final String COLUMN_MESURE_USER_ID = "user_id";
    // create table sql query
    private String CREATE_GLUCOSE_TABLE = "CREATE TABLE " + TABLE_GLUCOSE + "("
            + COLUMN_MESURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MESURE_VALUE + " TEXT,"
            + COLUMN_STAMP + " TEXT,"
            + COLUMN_DATE_MESURE + " TEXT,"
            + COLUMN_MESURE_USER_ID + " INTEGER," + " FOREIGN KEY (" + COLUMN_MESURE_USER_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";



    // drop table sql query
    private String DROP_GLUCOSE_TABLE = "DROP TABLE IF EXISTS " + TABLE_GLUCOSE;



    //Poids Table

    private static final String TABLE_POIDS = "poids";

    private static final String COLUMN_POIDS_ID = "poids_id";
    private static final String COLUMN_POIDS_VALUE = "valeur_poids";
    private static final String COLUMN_DATE_POIDS = "date_mesure";
    private static final String COLUMN_POIDS_USER_ID = "user_id";
    // create table sql query
    private String CREATE_POIDS_TABLE = "CREATE TABLE " + TABLE_POIDS + "("
            + COLUMN_POIDS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_POIDS_VALUE  + " TEXT,"
            + COLUMN_DATE_POIDS + " TEXT,"
            + COLUMN_POIDS_USER_ID + " INTEGER," + " FOREIGN KEY (" + COLUMN_POIDS_USER_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";



    // drop table sql query
    private String DROP_POIDS_TABLE = "DROP TABLE IF EXISTS " + TABLE_POIDS;



    //Tour de taille table


    private static final String TABLE_TOUR_DE_TAILLE = "tourDeTaille";

    private static final String COLUMN_TOUR_DE_TAILLE_ID = "tour_id";
    private static final String COLUMN_TOUR_DE_TAILLE_VALUE = "valeur_tour";
    private static final String COLUMN_DATE_TOUR_DE_TAILLE = "date_mesure";
    private static final String COLUMN_TOUR_DE_TAILLE_USER_ID = "user_id";
    // create table sql query
    private String CREATE_TOUR_DE_TAILLE_TABLE = "CREATE TABLE " + TABLE_TOUR_DE_TAILLE + "("
            + COLUMN_TOUR_DE_TAILLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TOUR_DE_TAILLE_VALUE  + " TEXT,"
            + COLUMN_DATE_TOUR_DE_TAILLE + " TEXT,"
            + COLUMN_TOUR_DE_TAILLE_USER_ID + " INTEGER," + " FOREIGN KEY (" + COLUMN_TOUR_DE_TAILLE_USER_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";



    // drop table sql query
    private String DROP_TOUR_DE_TAILLE_TABLE = "DROP TABLE IF EXISTS " + TABLE_POIDS;



    //Taille table


    private static final String TABLE_TAILLE = "taille";

    private static final String COLUMN_TAILLE_ID = "taille_id";
    private static final String COLUMN_TAILLE_VALUE = "valeur_taille";
    private static final String COLUMN_DATE_TAILLE = "date_mesure";
    private static final String COLUMN_TAILLE_USER_ID = "user_id";
    // create table sql query
    private String CREATE_TAILLE_TABLE = "CREATE TABLE " + TABLE_TAILLE + "("
            + COLUMN_TAILLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_TAILLE_VALUE  + " TEXT,"
            + COLUMN_DATE_TAILLE + " TEXT,"
            + COLUMN_TAILLE_USER_ID + " INTEGER," + " FOREIGN KEY (" + COLUMN_TAILLE_USER_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";



    // drop table sql query
    private String DROP_TAILLE_TABLE = "DROP TABLE IF EXISTS " + TABLE_TAILLE;





    //Systolic table


    private static final String TABLE_SYSTOLIC = "systolic";

    private static final String COLUMN_SYSTOLIC_ID = "systolic_id";
    private static final String COLUMN_SYSTOLIC_VALUE = "valeur_systolic";
    private static final String COLUMN_DATE_SYSTOLIC = "date_mesure";
    private static final String COLUMN_SYSTOLIC_USER_ID = "user_id";
    // create table sql query
    private String CREATE_SYSTOLIC_TABLE = "CREATE TABLE " + TABLE_SYSTOLIC + "("
            + COLUMN_SYSTOLIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SYSTOLIC_VALUE  + " TEXT,"
            + COLUMN_DATE_SYSTOLIC + " TEXT,"
            + COLUMN_SYSTOLIC_USER_ID + " INTEGER," + " FOREIGN KEY (" + COLUMN_SYSTOLIC_USER_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";



    // drop table sql query
    private String DROP_SYSTOLIC_TABLE = "DROP TABLE IF EXISTS " + TABLE_SYSTOLIC;



    //Diastolic table


    private static final String TABLE_DIASTOLIC = "diastolic";

    private static final String COLUMN_DIASTOLIC_ID = "diastolic_id";
    private static final String COLUMN_DIASTOLIC_VALUE = "valeur_diastolic";
    private static final String COLUMN_DATE_DIASTOLIC = "date_mesure";
    private static final String COLUMN_DIASTOLIC_USER_ID = "user_id";
    // create table sql query
    private String CREATE_DIASTOLIC_TABLE = "CREATE TABLE " + TABLE_DIASTOLIC + "("
            + COLUMN_DIASTOLIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DIASTOLIC_VALUE  + " TEXT,"
            + COLUMN_DATE_DIASTOLIC + " TEXT,"
            + COLUMN_DIASTOLIC_USER_ID + " INTEGER," + " FOREIGN KEY (" + COLUMN_DIASTOLIC_USER_ID + ") REFERENCES "
            + TABLE_USER + "(" + COLUMN_USER_ID + "));";



    // drop table sql query
    private String DROP_DIASTOLIC_TABLE = "DROP TABLE IF EXISTS " + TABLE_SYSTOLIC;


    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_USER_TABLE );
        db.execSQL( CREATE_INFO_MEDICALE_TABLE );
        db.execSQL( CREATE_GLUCOSE_TABLE );
        db.execSQL( CREATE_POIDS_TABLE );
        db.execSQL( CREATE_NOTE_TABLE );
        db.execSQL( CREATE_TOUR_DE_TAILLE_TABLE);
        db.execSQL( CREATE_TAILLE_TABLE);
        db.execSQL( CREATE_SYSTOLIC_TABLE);
        db.execSQL( CREATE_DIASTOLIC_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL( DROP_USER_TABLE );
        db.execSQL( DROP_INFO_MEDICALE_TABLE );
        db.execSQL( DROP_NOTE_TABLE );
        db.execSQL( DROP_GLUCOSE_TABLE);
        db.execSQL( DROP_POIDS_TABLE);
        db.execSQL( DROP_TOUR_DE_TAILLE_TABLE);
        db.execSQL( DROP_TAILLE_TABLE);
        db.execSQL( DROP_SYSTOLIC_TABLE);
        db.execSQL( DROP_DIASTOLIC_TABLE);
        // Create tables again
        onCreate( db );

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public long addUser(Utilisateur user) {
        SQLiteDatabase db = this.getWritableDatabase();
        String dat = user.getDateDeNaissance();
        ContentValues values = new ContentValues();
        values.put( COLUMN_USER_NAME, user.getPrenom() );
        values.put( COLUMN_USER_SURNAME, user.getNom() );
        values.put( COLUMN_USER_EMAIL, user.getEmail() );
        values.put( COLUMN_USER_PASSWORD, user.getMotDePasse() );
        values.put( COLUMN_USER_GENDER, user.getGenre() );
        values.put( COLUMN_WILAYA, user.getWilaya() );
        values.put( COLUMN_USER_BIRTH, dat );

        // Inserting Row
        long lastInsertedId = db.insertOrThrow( TABLE_USER, null, values );

        return lastInsertedId;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Utilisateur> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_NAME,
                COLUMN_USER_SURNAME,
                COLUMN_USER_BIRTH,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_GENDER,
                COLUMN_WILAYA

        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<Utilisateur> userList = new ArrayList<Utilisateur>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Utilisateur user = new Utilisateur();
                user.setIdUtilisateur( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_USER_ID ) ) ) );
                user.setPrenom( cursor.getString( cursor.getColumnIndex( COLUMN_USER_NAME ) ) );
                user.setNom( cursor.getString( cursor.getColumnIndex( COLUMN_USER_SURNAME ) ) );
                user.setDateDeNaissance( cursor.getString( cursor.getColumnIndex( COLUMN_USER_BIRTH ) ) );
                user.setEmail( cursor.getString( cursor.getColumnIndex( COLUMN_USER_EMAIL ) ) );
                user.setMotDePasse( cursor.getString( cursor.getColumnIndex( COLUMN_USER_PASSWORD ) ) );
                user.setGenre( cursor.getString( cursor.getColumnIndex( COLUMN_USER_GENDER ) ) );
                user.setWilaya( cursor.getString( cursor.getColumnIndex( COLUMN_WILAYA ) ) );
                // Log.d("User email", user.getEmail());
                // Adding user record to list
                userList.add( user );

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(Utilisateur user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put( COLUMN_USER_NAME, user.getPrenom() );
        values.put( COLUMN_USER_SURNAME, user.getNom() );
        values.put( COLUMN_USER_BIRTH, user.getDateDeNaissance() );
        values.put( COLUMN_USER_GENDER, user.getGenre() );
        values.put( COLUMN_WILAYA, user.getWilaya());
        values.put( COLUMN_USER_EMAIL, user.getEmail() );
        values.put( COLUMN_USER_PASSWORD, user.getMotDePasse() );

        // updating row
        db.update( TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf( user.getIdUtilisateur() )} );
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(Utilisateur user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete( TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf( user.getIdUtilisateur() )} );
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        if (email != null) {
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursor = db.query( TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null );                      //The sort order
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();

            if (cursorCount > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query( TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null );                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    //get ID of current User

    public int getCurrentUserId(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        int d = 0;
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        if (email != null) {
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor c = db.query( TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null );                      //The sort order


            if (c != null && c.moveToFirst()) {
                String idS = c.getString( c.getColumnIndex( COLUMN_USER_ID ) );
                d = Integer.parseInt( idS );
                c.close();

            }
           /* int cursorCount = c.getCount();
            String idS = c.getString( c.getColumnIndex( COLUMN_USER_ID ) );*/
            ;





        }
        return d;
    }


    //Get current user Wilaya


    public String getCurrentUserWilaya(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_WILAYA
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String w = "";
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        if (email != null) {
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor c = db.query( TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null );                      //The sort order


            if (c != null && c.moveToFirst()) {
                w = c.getString( c.getColumnIndex( COLUMN_WILAYA ) );

                c.close();

            }
           /* int cursorCount = c.getCount();
            String idS = c.getString( c.getColumnIndex( COLUMN_USER_ID ) );*/
            ;





        }
        return w;
    }




    //Vérifier si la table "infoMedicale" vient d'être mise à jour

    public boolean getUpdatedValue() {
        // array of columns to fetch
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        String selectQuery = "SELECT modifie FROM " + TABLE_INFO_MEDICALE +" WHERE id_utilisateur = " +  idUtilisateur + ";";
        Log.e( "Select query", selectQuery );
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery( selectQuery, null );
        boolean modifie = false;
        int d = 0;
        // selection criteria

        if (c != null && c.moveToFirst()) {
            String idS = c.getString( c.getColumnIndex( COLUMN_UPDATED ) );
            d = Integer.parseInt( idS );
            c.close();
            if(d == 1)
                modifie = true;
        }
        // selection argument
        return modifie;
    }

    //Vérifier si une information vient d'être insérée

    public boolean getInsertedValue() {
        // array of columns to fetch
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        String selectQuery = "SELECT insere FROM " + TABLE_INFO_MEDICALE + " WHERE id_utilisateur = " + idUtilisateur + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery( selectQuery, null );
        boolean inserted = false;
        int d = 0;
        // selection criteria

        if (c != null && c.moveToFirst()) {
            String idS = c.getString( c.getColumnIndex( COLUMN_INSERTED ) );
            d = Integer.parseInt( idS );
            c.close();
           if(d == 1)
               inserted = true;

        }
        // selection argument
        return inserted;
    }



// Update inserted

    public void updateInserted(int newInserted) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        ContentValues values = new ContentValues();
        values.put( COLUMN_INSERTED, newInserted);

        // updating row
        db.update(TABLE_INFO_MEDICALE, values, COLUMN_ID_UTILISATEUR + " = ?",
                new String[]{String.valueOf(""+idUtilisateur)});
        db.close();
    }


    // Update update

    public void updateUpdated(int newUpdated) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        ContentValues values = new ContentValues();
        values.put( COLUMN_UPDATED, newUpdated);

        // updating row
        db.update(TABLE_INFO_MEDICALE, values, COLUMN_ID_UTILISATEUR + " = ?",
                new String[]{String.valueOf(""+idUtilisateur)});
        db.close();
    }

    public void ajouterTension(String systo, String diasto) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        ContentValues values = new ContentValues();
        values.put( COLUMN_TENSION_SYSTOLIC, systo);
        values.put( COLUMN_TENSION_DIASTOLIC, diasto);
        // updating row
        db.update(TABLE_INFO_MEDICALE, values, COLUMN_ID_UTILISATEUR + " = ?",
                new String[]{String.valueOf(""+idUtilisateur)});
        db.close();
    }

    // Info part

    public void addInfo(InfoMed infoMed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TAILLE, infoMed.getTaille());
        values.put(COLUMN_USER_POIDS, infoMed.getPoids());
        values.put(COLUMN_TOUR_DE_TAILLE, infoMed.getTourDeTaille());
        values.put(COLUMN_ANTECEDENTS, infoMed.getHistorique());
        values.put(COLUMN_STRESS, infoMed.getStress());
        values.put(COLUMN_FUMER, infoMed.getFumeur());
        values.put(COLUMN_SPORT, infoMed.getSport());
        values.put(COLUMN_TYPE_DE_DIABETE, infoMed.getType());
        values.put(COLUMN_TENSION_SYSTOLIC, infoMed.getSystolic());
        values.put(COLUMN_TENSION_DIASTOLIC, infoMed.getDistolic());
        values.put(COLUMN_ID_UTILISATEUR, getCurrentUserId( InscriptionFragment.currentEmailUser ));
        values.put(COLUMN_UPDATED, 0);
        values.put(COLUMN_INSERTED, 1);
        Log.e("Id user pour info", "" + getCurrentUserId( InscriptionFragment.currentEmailUser ));
        // Inserting Row
        db.insert(TABLE_INFO_MEDICALE, null, values);
        //db.close();
    }

    public InfoMed getMyInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        InfoMed infoMed = new InfoMed();
        int idUtilisateur = 0;
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selectQuery = "SELECT * FROM " + TABLE_INFO_MEDICALE + " WHERE "
                + COLUMN_ID_UTILISATEUR + " = " + idUtilisateur;
           if(idUtilisateur > 0) {
               Log.e( "ahem", selectQuery );

               Cursor c = db.rawQuery( selectQuery, null );

               if (c != null && c.moveToFirst()) {

                   infoMed.setIdInfoMed(c.getInt(c.getColumnIndex(COLUMN_INFO_MEDICALE_ID)));
                   infoMed.setTaille( (c.getString( c.getColumnIndex( COLUMN_TAILLE ) )) );
                   infoMed.setPoids( (c.getString( c.getColumnIndex( COLUMN_USER_POIDS ) )) );
                   infoMed.setTourDeTaille( (c.getString( c.getColumnIndex( COLUMN_TOUR_DE_TAILLE ) )) );
                   infoMed.setHistorique( (c.getString( c.getColumnIndex( COLUMN_ANTECEDENTS ) )) );
                   infoMed.setSport( (c.getString( c.getColumnIndex( COLUMN_SPORT ) )) );
                   infoMed.setStress( (c.getString( c.getColumnIndex( COLUMN_STRESS ) )) );
                   infoMed.setFumeur( (c.getString( c.getColumnIndex( COLUMN_FUMER ) )) );
                   infoMed.setType( (c.getString( c.getColumnIndex( COLUMN_TYPE_DE_DIABETE ) )) );
                   infoMed.setSystolic( (c.getString( c.getColumnIndex( COLUMN_TENSION_SYSTOLIC ) )) );
                   infoMed.setDistolic( (c.getString( c.getColumnIndex( COLUMN_TENSION_DIASTOLIC ) )) );
                   infoMed.setIdUtilisateur(Integer.parseInt (c.getString( c.getColumnIndex( COLUMN_ID_UTILISATEUR ) )) );
                   infoMed.setInsere(Integer.parseInt (c.getString( c.getColumnIndex( COLUMN_INSERTED ) )) );
                   infoMed.setModifie(Integer.parseInt (c.getString( c.getColumnIndex( COLUMN_UPDATED ) )) );
               }
           }
        return infoMed;
    }


    //Update InfoMed record


    public void updateInfoMed(InfoMed infoMed) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        ContentValues values = new ContentValues();
        values.put(COLUMN_SPORT, infoMed.getSport());
        values.put(COLUMN_TYPE_DE_DIABETE, infoMed.getType());
        values.put(COLUMN_FUMER, infoMed.getFumeur());
        values.put(COLUMN_STRESS, infoMed.getStress());
        values.put(COLUMN_ANTECEDENTS, infoMed.getHistorique());
        values.put(COLUMN_TOUR_DE_TAILLE, infoMed.getTourDeTaille());
        values.put( COLUMN_USER_POIDS, infoMed.getPoids() );
        values.put( COLUMN_TAILLE, infoMed.getTaille() );
        values.put( COLUMN_TENSION_SYSTOLIC, infoMed.getSystolic() );
        values.put( COLUMN_TENSION_DIASTOLIC, infoMed.getDistolic() );
        values.put( COLUMN_INSERTED, 0);
        values.put( COLUMN_UPDATED, 1);
        // updating row
        db.update(TABLE_INFO_MEDICALE, values, COLUMN_ID_UTILISATEUR + " = ?",
                new String[]{String.valueOf(""+idUtilisateur)});
        db.close();
    }


    // Add note

    public void addNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        DateFormat date = new SimpleDateFormat("dd MMM yyyy, h:mm");
        String dateFormatted = date.format( Calendar.getInstance().getTime());
        note.setDateCreation(dateFormatted);
        ContentValues values = new ContentValues();
        Log.e( "note", "just inserted is " + note.getContenu() );
        values.put( COLUMN_NOTE_CONTENT, note.getContenu());
        values.put( COLUMN_CREATION_DATE, note.getDateCreation());
        values.put( COLUMN_NOTE_WRITER, idUtilisateur);
        // updating row
        db.insert(TABLE_NOTE, null, values);

        db.close();


        /*String email = "";
        if(Login.currentUserEmail != null)
            email = Login.currentUserEmail;
        else
            email = InscriptionFragment.currentEmailUser;
        Log.e( "Writer", " note writer is" + email );
        int currentUser = this.getCurrentUserId( email );
        Log.e( "Writer", " note writer id is" + currentUser );

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_CONTENT, note.getContenu());
        values.put(COLUMN_CREATION_DATE, note.getDateCreation());
        values.put(COLUMN_GLUCOSE_ID, note.getIdGlucose());
        values.put(COLUMN_NOTE_WRITER, currentUser);
        Log.e("the inserted note is", "" + note.getContenu());
        // Inserting Row
        db.insert(TABLE_NOTE, null, values);*/
        //db.close();
    }


    //Get note


//  Get Wether diabetic or not

    public String isDiabetic(int id) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_TYPE_DE_DIABETE
        };
        SQLiteDatabase db = this.getReadableDatabase();
        int d = 0;
        // selection criteria
        String selection = COLUMN_ID_UTILISATEUR + " = ?";

        // selection argument
        if (id > 0) {
            String[] selectionArgs = {""+id};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor c = db.query( TABLE_INFO_MEDICALE, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null );                      //The sort order


            if (c != null && c.moveToFirst()) {
                String type = c.getString( c.getColumnIndex( COLUMN_TYPE_DE_DIABETE ) );

                c.close();
                return type;
            }
           /* int cursorCount = c.getCount();
            String idS = c.getString( c.getColumnIndex( COLUMN_USER_ID ) );*/
            ;





        }
        return "Something went wrong";
    }





    //Get current user birth date

    public String getCurrentUserBirthDate(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_BIRTH
        };
        SQLiteDatabase db = this.getReadableDatabase();
        int d = 0;
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        if (email != null) {
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor c = db.query( TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null );                      //The sort order


            if (c != null && c.moveToFirst()) {
                String birthdate = c.getString( c.getColumnIndex( COLUMN_USER_BIRTH ) );

                c.close();
                return birthdate;
            }
           /* int cursorCount = c.getCount();
            String idS = c.getString( c.getColumnIndex( COLUMN_USER_ID ) );*/
            ;





        }
        return "Something went wrong";
    }


    //Get current user gender


    public String getCurrentUserGender(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_GENDER
        };
        SQLiteDatabase db = this.getReadableDatabase();
        int d = 0;
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        if (email != null) {
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor c = db.query( TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null );                      //The sort order


            if (c != null && c.moveToFirst()) {
                String gender = c.getString( c.getColumnIndex( COLUMN_USER_GENDER ) );

                c.close();
                return gender;
            }
           /* int cursorCount = c.getCount();
            String idS = c.getString( c.getColumnIndex( COLUMN_USER_ID ) );*/
            ;





        }
        return "Something went wrong";
    }

   //Ajouter poids

    public void ajouterPoids(Poids poids) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        DateFormat date = new SimpleDateFormat("dd MMM yyyy, h:mm");
        String dateFormatted = date.format( Calendar.getInstance().getTime());
        poids.setDateMesure(dateFormatted);
        ContentValues values = new ContentValues();
        values.put( COLUMN_POIDS_VALUE, poids.getValeur());
        values.put( COLUMN_DATE_POIDS, poids.getDateMesure());
        values.put( COLUMN_POIDS_USER_ID, idUtilisateur);
        // updating row
        db.insert(TABLE_POIDS, null, values);

        db.close();
    }


    //Sauvegarder le taux du glucose

    public void ajouterTauxGlucose(Glucose glucose) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        DateFormat date = new SimpleDateFormat("dd MMM yyyy, h:mm");
        String dateFormatted = date.format( Calendar.getInstance().getTime());
        glucose.setDateMesure(dateFormatted);
        ContentValues values = new ContentValues();
        values.put( COLUMN_MESURE_VALUE, glucose.getTauxGlucose());
        values.put( COLUMN_STAMP, glucose.getStamp());
        values.put( COLUMN_DATE_MESURE, glucose.getDateMesure());
        values.put( COLUMN_MESURE_USER_ID, idUtilisateur);
        // updating row
        db.insert(TABLE_GLUCOSE, null, values);

        db.close();
    }

    // Récupérer toutes les mesures de glucose du patient

    public List<Glucose> getMesuresGlucose() {
        // array of columns to fetch
        int idUtilisateur = 0;
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selection = COLUMN_MESURE_USER_ID + " = ?";


        String[] selectionArgs = {"" + idUtilisateur};

        String[] columns = {
                COLUMN_MESURE_ID,
                COLUMN_MESURE_VALUE,
                COLUMN_STAMP,
                COLUMN_DATE_MESURE

        };
        // sorting orders
        String sortOrder =
                COLUMN_MESURE_ID + " ASC";
        List<Glucose> glucoseList = new ArrayList<Glucose>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_GLUCOSE, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                Glucose glucose = new Glucose();
                glucose.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_ID ) ) ) );
                glucose.setTauxGlucose( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_VALUE ) ) );
                glucose.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_MESURE ) ) );
                glucose.setStamp( cursor.getString( cursor.getColumnIndex( COLUMN_STAMP) ) );
                glucoseList.add( glucose );
                cursor.moveToNext();


            }
        }
        // Traversing through all rows and adding to list
       /*  if (cursor != null && cursor.moveToFirst()) {
            do {
                Glucose glucose = new Glucose();
                glucose.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_ID ) ) ) );
                glucose.setTauxGlucose( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_VALUE ) ) );
                glucose.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_MESURE ) ) );
                glucose.setStamp( cursor.getString( cursor.getColumnIndex( COLUMN_STAMP) ) );

                // Log.d("User email", user.getEmail());
                // Adding user record to list
               glucoseList.add( glucose );

            } while (cursor.moveToNext());
        }*/
        cursor.close();
        db.close();

        // return user list
        return glucoseList;
    }


    // Afficher note


    public Note getNote(int idGlucose) {
       // SQLiteDatabase db = this.getReadableDatabase();


        // array of columns to fetch
        String[] columns = {
                COLUMN_NOTE_ID,COLUMN_NOTE_CONTENT,COLUMN_CREATION_DATE, COLUMN_GLUCOSE_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_GLUCOSE_ID + " = ?";

        // selection argument

            String[] selectionArgs = {""+idGlucose};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor c = db.query( TABLE_NOTE, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null );                      //The sort order


        Note note = new Note();


            if (c != null && c.moveToFirst()) {

                note.setIdNote(c.getInt(c.getColumnIndex(COLUMN_NOTE_ID)));
                note.setContenu( (c.getString( c.getColumnIndex( COLUMN_NOTE_CONTENT) )) );
                note.setDateCreation( (c.getString( c.getColumnIndex( COLUMN_CREATION_DATE ) )) );
                note.setIdGlucose( idGlucose );

            }
        return note;
        }





    // Récupérer toutes les mesures de poids de l'utilisateur

    public List<Poids> getMesuresPoids() {
        // array of columns to fetch

        int idUtilisateur = 0;
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selection = COLUMN_POIDS_USER_ID + " = ?";


            String[] selectionArgs = {"" + idUtilisateur};
        String[] columns = {
                COLUMN_POIDS_ID,
                COLUMN_POIDS_VALUE,
                COLUMN_DATE_POIDS

        };
        // sorting orders
        String sortOrder =
                COLUMN_POIDS_ID + " ASC";
        List<Poids> poidsList = new ArrayList<Poids>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_POIDS, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                Poids poids = new Poids();
                poids.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_POIDS_ID ) ) ) );
                poids.setValeur( cursor.getString( cursor.getColumnIndex( COLUMN_POIDS_VALUE ) ) );
                poids.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_POIDS ) ) );
                poidsList.add( poids );
                cursor.moveToNext();


            }
        }
        // Traversing through all rows and adding to list
       /*  if (cursor != null && cursor.moveToFirst()) {
            do {
                Glucose glucose = new Glucose();
                glucose.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_ID ) ) ) );
                glucose.setTauxGlucose( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_VALUE ) ) );
                glucose.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_MESURE ) ) );
                glucose.setStamp( cursor.getString( cursor.getColumnIndex( COLUMN_STAMP) ) );

                // Log.d("User email", user.getEmail());
                // Adding user record to list
               glucoseList.add( glucose );

            } while (cursor.moveToNext());
        }*/
        cursor.close();
        db.close();

        // return user list
        return poidsList;
    }


    //Ajouter tour de taille


    public void ajouterTourDeTaille(TourDeTaille tourDeTaille) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        DateFormat date = new SimpleDateFormat("dd MMM yyyy, h:mm");
        String dateFormatted = date.format( Calendar.getInstance().getTime());
        tourDeTaille.setDateMesure(dateFormatted);
        ContentValues values = new ContentValues();
        values.put( COLUMN_TOUR_DE_TAILLE_VALUE, tourDeTaille.getValeur());
        values.put( COLUMN_DATE_TOUR_DE_TAILLE, tourDeTaille.getDateMesure());
        values.put( COLUMN_TOUR_DE_TAILLE_USER_ID, idUtilisateur);
        // updating row
        db.insert(TABLE_TOUR_DE_TAILLE, null, values);

        db.close();
    }


    // Récupérer toutes les mesures de tour de taille de l'utilisateur

    public List<TourDeTaille> getMesuresTourDeTaille() {
        // array of columns to fetch

        int idUtilisateur = 0;
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selection = COLUMN_TOUR_DE_TAILLE_USER_ID + " = ?";


        String[] selectionArgs = {"" + idUtilisateur};
        String[] columns = {
                COLUMN_TOUR_DE_TAILLE_ID,
                COLUMN_TOUR_DE_TAILLE_VALUE,
                COLUMN_DATE_TOUR_DE_TAILLE

        };
        // sorting orders
        String sortOrder =
                COLUMN_TOUR_DE_TAILLE_ID + " ASC";
        List<TourDeTaille> tourList = new ArrayList<TourDeTaille>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_TOUR_DE_TAILLE, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                TourDeTaille tourDeTaille = new TourDeTaille();
                tourDeTaille.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_TOUR_DE_TAILLE_ID ) ) ) );
                tourDeTaille.setValeur( cursor.getString( cursor.getColumnIndex( COLUMN_TOUR_DE_TAILLE_VALUE ) ) );
                tourDeTaille.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_TOUR_DE_TAILLE ) ) );
                tourList.add( tourDeTaille );
                cursor.moveToNext();


            }
        }
        // Traversing through all rows and adding to list
       /*  if (cursor != null && cursor.moveToFirst()) {
            do {
                Glucose glucose = new Glucose();
                glucose.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_ID ) ) ) );
                glucose.setTauxGlucose( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_VALUE ) ) );
                glucose.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_MESURE ) ) );
                glucose.setStamp( cursor.getString( cursor.getColumnIndex( COLUMN_STAMP) ) );

                // Log.d("User email", user.getEmail());
                // Adding user record to list
               glucoseList.add( glucose );

            } while (cursor.moveToNext());
        }*/
        cursor.close();
        db.close();

        // return user list
        return tourList;
    }




    //Ajouter taille


    public void ajouterTaille(Taille taille) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        DateFormat date = new SimpleDateFormat("dd MMM yyyy, h:mm");
        String dateFormatted = date.format( Calendar.getInstance().getTime());
        taille.setDateMesure(dateFormatted);
        ContentValues values = new ContentValues();
        values.put( COLUMN_TAILLE_VALUE, taille.getValeur());
        values.put( COLUMN_DATE_TAILLE, taille.getDateMesure());
        values.put( COLUMN_TAILLE_USER_ID, idUtilisateur);
        // updating row
        db.insert(TABLE_TAILLE, null, values);

        db.close();
    }


    // Récupérer toutes les mesures de la taille de l'utilisateur

    public List<Taille> getMesuresTaille() {
        // array of columns to fetch

        int idUtilisateur = 0;
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selection = COLUMN_TAILLE_USER_ID + " = ?";


        String[] selectionArgs = {"" + idUtilisateur};
        String[] columns = {
                COLUMN_TAILLE_ID,
                COLUMN_TAILLE_VALUE,
                COLUMN_DATE_TAILLE

        };
        // sorting orders
        String sortOrder =
                COLUMN_TAILLE_ID + " ASC";
        List<Taille> tailleList = new ArrayList<Taille>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_TAILLE, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                Taille taille = new Taille();
                taille.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_TAILLE_ID ) ) ) );
                taille.setValeur( cursor.getString( cursor.getColumnIndex( COLUMN_TAILLE_VALUE ) ) );
                taille.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_TAILLE ) ) );
                tailleList.add( taille );
                cursor.moveToNext();


            }
        }
        // Traversing through all rows and adding to list
       /*  if (cursor != null && cursor.moveToFirst()) {
            do {
                Glucose glucose = new Glucose();
                glucose.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_ID ) ) ) );
                glucose.setTauxGlucose( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_VALUE ) ) );
                glucose.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_MESURE ) ) );
                glucose.setStamp( cursor.getString( cursor.getColumnIndex( COLUMN_STAMP) ) );

                // Log.d("User email", user.getEmail());
                // Adding user record to list
               glucoseList.add( glucose );

            } while (cursor.moveToNext());
        }*/
        cursor.close();
        db.close();

        // return user list
        return tailleList;
    }



    //Ajouter Systolic


    public void ajouterSystolic(Systolic systolic) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        DateFormat date = new SimpleDateFormat("dd MMM yyyy, h:mm");
        String dateFormatted = date.format( Calendar.getInstance().getTime());
        systolic.setDateMesure(dateFormatted);
        ContentValues values = new ContentValues();
        values.put( COLUMN_SYSTOLIC_VALUE, systolic.getValeur());
        values.put( COLUMN_DATE_SYSTOLIC, systolic.getDateMesure());
        values.put( COLUMN_SYSTOLIC_USER_ID, idUtilisateur);
        // updating row
        db.insert(TABLE_SYSTOLIC, null, values);

        db.close();
    }


    // Récupérer toutes les mesures de la tension systolique de l'utilisateur

    public List<Systolic> getMesuresSystolic() {
        // array of columns to fetch

        int idUtilisateur = 0;
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selection = COLUMN_SYSTOLIC_USER_ID + " = ?";


        String[] selectionArgs = {"" + idUtilisateur};
        String[] columns = {
                COLUMN_SYSTOLIC_ID,
                COLUMN_SYSTOLIC_VALUE,
                COLUMN_DATE_SYSTOLIC

        };
        // sorting orders
        String sortOrder =
                COLUMN_SYSTOLIC_ID + " ASC";
        List<Systolic> systolicList = new ArrayList<Systolic>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_SYSTOLIC, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                Systolic systolic = new Systolic();
                systolic.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_SYSTOLIC_ID ) ) ) );
                systolic.setValeur( cursor.getString( cursor.getColumnIndex( COLUMN_SYSTOLIC_VALUE ) ) );
                systolic.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_SYSTOLIC ) ) );
                systolicList.add( systolic );
                cursor.moveToNext();


            }
        }
        // Traversing through all rows and adding to list
       /*  if (cursor != null && cursor.moveToFirst()) {
            do {
                Glucose glucose = new Glucose();
                glucose.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_ID ) ) ) );
                glucose.setTauxGlucose( cursor.getString( cursor.getColumnIndex( COLUMN_MESURE_VALUE ) ) );
                glucose.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_MESURE ) ) );
                glucose.setStamp( cursor.getString( cursor.getColumnIndex( COLUMN_STAMP) ) );

                // Log.d("User email", user.getEmail());
                // Adding user record to list
               glucoseList.add( glucose );

            } while (cursor.moveToNext());
        }*/
        cursor.close();
        db.close();

        // return user list
        return systolicList;
    }


    //Tension diastolique


    //Ajouter diastolic

    public void ajouterDiastolic(Diastolic diastolic) {
        SQLiteDatabase db = this.getWritableDatabase();
        int idUtilisateur = 0;
        if(Login.currentUserEmail != null)
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        else
            idUtilisateur = getCurrentUserId( InscriptionFragment.currentEmailUser );
        DateFormat date = new SimpleDateFormat("dd MMM yyyy, h:mm");
        String dateFormatted = date.format( Calendar.getInstance().getTime());
        diastolic.setDateMesure(dateFormatted);
        ContentValues values = new ContentValues();
        values.put( COLUMN_DIASTOLIC_VALUE, diastolic.getValeur());
        values.put( COLUMN_DATE_DIASTOLIC, diastolic.getDateMesure());
        values.put( COLUMN_DIASTOLIC_USER_ID, idUtilisateur);
        // updating row
        db.insert(TABLE_DIASTOLIC, null, values);

        db.close();
    }


    // Récupérer toutes les mesures de la tension diastolique de l'utilisateur

    public List<Diastolic> getMesuresDiastolic() {
        // array of columns to fetch

        int idUtilisateur = 0;
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selection = COLUMN_DIASTOLIC_USER_ID + " = ?";


        String[] selectionArgs = {"" + idUtilisateur};
        String[] columns = {
                COLUMN_DIASTOLIC_ID,
                COLUMN_DIASTOLIC_VALUE,
                COLUMN_DATE_DIASTOLIC

        };
        // sorting orders
        String sortOrder =
                COLUMN_DIASTOLIC_ID + " ASC";
        List<Diastolic> diastolicList = new ArrayList<Diastolic>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_DIASTOLIC, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                Diastolic diastolic = new Diastolic();
                diastolic.setId( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_DIASTOLIC_ID ) ) ) );
                diastolic.setValeur( cursor.getString( cursor.getColumnIndex( COLUMN_DIASTOLIC_VALUE ) ) );
                diastolic.setDateMesure( cursor.getString( cursor.getColumnIndex( COLUMN_DATE_DIASTOLIC ) ) );
                diastolicList.add( diastolic );
                cursor.moveToNext();


            }
        }


        cursor.close();
        db.close();

        // return user list
        return diastolicList;
    }

   //Get last inserted ID glucose

  public  int getLastInsertedGlucose(){

      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(TABLE_GLUCOSE, new String[]{COLUMN_MESURE_ID},null, null, null, null, null);
      cursor.moveToLast();
      int id = 0;
     id = cursor.getInt( cursor.getColumnIndex( COLUMN_MESURE_ID) ) ;
        return id;
  }


  //Search if ID glucose exists already in Note. If it doesn't, a note can be associated to that ID

    //Returns false if ID doesn't exist.True else;


    public int checkIdGlucose(int id){



        String selection = COLUMN_GLUCOSE_ID + " = ?";


        String[] selectionArgs = {"" + id};
        String[] columns = {
                COLUMN_GLUCOSE_ID


        };
      boolean exist = true;
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(TABLE_NOTE, new String[]{COLUMN_GLUCOSE_ID},selection, selectionArgs, null, null, null);
                         //The sort order
           int idG = -1;

        if (cursor != null && cursor.moveToFirst()) {
            idG = cursor.getInt( cursor.getColumnIndex( COLUMN_GLUCOSE_ID ) );

            cursor.close();
            return idG;
        }
     return idG;
    }



    //Get notes list


    public List<Note> getNotesList() {
        // array of columns to fetch
        //Note= new ArrayList<>(  )
        int idUtilisateur = 0;
        Log.e("hey","hey");
        if(getCurrentUserId( Login.currentUserEmail ) > 0) {
            idUtilisateur = getCurrentUserId( Login.currentUserEmail  );
        }
        else
            idUtilisateur =  getCurrentUserId( InscriptionFragment.currentEmailUser  );
        String selection = COLUMN_NOTE_WRITER + " = ?";


        String[] selectionArgs = {"" + idUtilisateur};
        String[] columns = {
                COLUMN_NOTE_ID,
                COLUMN_NOTE_WRITER,
                COLUMN_NOTE_CONTENT,
                COLUMN_CREATION_DATE


        };
        // sorting orders
        String sortOrder =
                COLUMN_NOTE_ID + " DESC";
        List<Note> noteList = new ArrayList<Note>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query( TABLE_NOTE, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder ); //The sort order


        if (cursor.moveToFirst()) {
            while ( !cursor.isAfterLast() ) {
                Note note = new Note();
                note.setIdNote( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_NOTE_ID ) ) ) );
                note.setContenu( cursor.getString( cursor.getColumnIndex( COLUMN_NOTE_CONTENT ) ) );
                note.setDateCreation( cursor.getString( cursor.getColumnIndex( COLUMN_CREATION_DATE ) ) );
                note.setIdUser( Integer.parseInt( cursor.getString( cursor.getColumnIndex( COLUMN_NOTE_WRITER ) ) ) );
                noteList.add( note );
                cursor.moveToNext();


            }
        }
        // Traversing through all rows and adding to list

        cursor.close();
        db.close();

        // return user list
        return noteList;
    }



}


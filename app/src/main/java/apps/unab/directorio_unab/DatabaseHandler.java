package apps.unab.directorio_unab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rene on 14/03/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    private static final int DATABASE_VERSION = 1;

    private static String DB_PATH = "/data/data/apps.unab.directorio_unab/databases/";

    // Database Name
    private static final String DATABASE_NAME = "docentes.db";

    // Contacts table name
    private static final String TABLE_DOCENTES = "DatosDocentes";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nombre";
    private static final String KEY_PHONE = "telefono";
    private static final String KEY_CARGO = "cargo";

    private final Context contextoooo;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.contextoooo = context;
    }



    public List<DatosDocentes> getAllContacts() {
        List<DatosDocentes> contactList = new ArrayList<DatosDocentes>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + '"'+TABLE_DOCENTES+'"';

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            DatosDocentes contact = new DatosDocentes();

                Log.i("db",cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4));
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setNombre(cursor.getString(3));
                contact.setTelefono(cursor.getInt(4));
                contact.setCorreo(cursor.getString(2));
                contact.setCargo(cursor.getString(1));

                // Adding contact to list
                contactList.add(contact);
                } while (cursor.moveToNext());
            Log.i("identificador","aca voy ");

            }

        // return contact list
        return contactList;
    }

    // Adding new contact
    public void addContact(DatosDocentes contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("identificador","aca voy 2 ");
        ContentValues values = new ContentValues();
        values.put(KEY_ID,contact.getId());
        values.put(KEY_CARGO,contact.getCargo());
        values.put(KEY_NAME, contact.getNombre()); // Contact Name
        values.put(KEY_PHONE, contact.getTelefono()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_DOCENTES, null, values);
        db.close(); // Closing database connection
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        /*String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DOCENTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PHONE + " INTEGER,"+ KEY_CARGO + " TEXT"+ ")";

        db.execSQL(CREATE_CONTACTS_TABLE);*/



    }

    public void create() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - database already exist
        }else{

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCENTES);

        // Create tables again
        //onCreate(db);
        if (newVersion > oldVersion)
        {
            Log.v("Database Upgrade", "Database version higher than old.");
            db_delete();
        }

    }


    private boolean checkDataBase(){

        /*SQLiteDatabase checkDB = null;

        try{


            String path = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            // database don't exist yet.
            e.printStackTrace();

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;*/
        boolean checkDB = false;
        try
        {
            String myPath = DB_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkDB = dbfile.exists();
        }
        catch(SQLiteException e)
        {
        }
        return checkDB;
    }

    // copy your assets db to the new system DB
    private void copyDataBase() throws IOException {
/*
        //Open your local db as the input stream
        InputStream myInput = contextoooo.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
*/

        String outFileName = DB_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        InputStream myInput = contextoooo.getAssets().open(DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();
    }

    //Open the database
    public boolean open() {

        try {
            String myPath = DB_PATH + DATABASE_NAME;
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            return true;

        } catch(SQLException sqle) {
            db = null;
            return false;
        }

    }

    public synchronized void closeDataBase()throws SQLException
    {
        if(db != null)
            db.close();
        super.close();
    }

    public void db_delete()
    {
        File file = new File(DB_PATH + DATABASE_NAME);
        if(file.exists())
        {
            file.delete();
            System.out.println("delete database file.");
        }
    }



}

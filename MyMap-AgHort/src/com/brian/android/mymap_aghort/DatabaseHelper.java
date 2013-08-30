package com.brian.android.mymap_aghort;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static String DB_PATH = "/data/data/com.example.wifidata/databases/";
	
	private static String DB_NAME = "Sample_databse-1";//name of your Database

	private SQLiteDatabase myDataBase;

	private final Context myContext;
	
	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}
	
	/**
	  * Creates a empty database on the system and rewrites it with your own database.
	  * */
	public void createDataBase() throws IOException{

	    boolean dbExist = checkDataBase();




	if(dbExist)
	   {
	    //do nothing - database already exist
	    
	    //Toast.makeText(this.myContext,"Db Exists",Toast.LENGTH_LONG).show();
	    
	    
	    }
	else{

	        //By calling this method and empty database will be created into the default system path
	        //of your application so we are gonna be able to overwrite that database with our database.
	        this.getReadableDatabase();
	        
	        //Toast.makeText(this.myContext,"Create new DB",Toast.LENGTH_LONG).show();
	        
	        try {
	        
	        copyDataBase();
	        
	        //Toast.makeText(this.myContext,"Copy DB",Toast.LENGTH_LONG).show();
	        
	        } catch (IOException e) {
	        
	        throw new Error("Error copying database");
	        
	        }
	    }
	}
	
	 public String[] ReadFromDB(String Selecteditem) {
	       // Retrieve a string array of all our Data
	    //Toast.makeText(this.myContext,"Read From DB",Toast.LENGTH_LONG).show();
	    
	    ArrayList temp_array = new ArrayList();
	    String[] notes_array = new String[0];
	    //The SQL Query
	  
	   
	    String sqlQuery = "SELECT * FROM data where room = '"+ Selecteditem +"'"  ;       
	    
	    //Here we are querying the databse with the selected item from the spinner. Only the data with the selected item will be retrieved from the database           
	    
	    //Define database and cursor
	    
	    //Toast.makeText(this.myContext,sqlQuery,Toast.LENGTH_LONG).show();
	    
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor c = db.rawQuery(sqlQuery, null);

	    //Loop through the results and add it to the temp_array
	        //You shoud give the column names in the Database exactly here below         
	              
	              if (c.moveToFirst()){
	                  do{
	                        temp_array.add(  c.getString(c.getColumnIndex("room")) +
	                                       "," + c.getString(c.getColumnIndex("r_b2")) +
	                                       "," + c.getString(c.getColumnIndex("r_12"))                                      
	                                     );
	                        
	              //Toast.makeText(this.myContext,c.getString(c.getColumnIndex("TIME")),Toast.LENGTH_LONG).show();
	              
	              
	               
	              
	        }while(c.moveToNext());
	     }
	    //Close the cursor
	    c.close();
	    //Transfer from the ArrayList to string array
	    notes_array = (String[]) temp_array.toArray(notes_array);
	    //Return the string array
	    return notes_array;
	  }
	 
	 /**
	  * Check if the database already exist to avoid re-copying the file each time you open the application.
	  * @return true if it exists, false if it doesn't
	  */
	private boolean checkDataBase(){

	        SQLiteDatabase checkDB = null;
	        
	        //Toast.makeText(this.myContext,"check db",Toast.LENGTH_LONG).show();
	        
	        try{
	            String myPath = DB_PATH + DB_NAME;
	            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	        
	        //Toast.makeText(this.myContext,myPath,Toast.LENGTH_LONG).show();
	            
	            
	        }catch(SQLiteException e){
	        
	            //Toast.makeText(this.myContext,"DB Does not exists",Toast.LENGTH_LONG).show();
	            
	        //database does't exist yet.
	        
	        }
	        
	        if(checkDB != null){
	        
	            checkDB.close();
	        
	        }
	        
	        return checkDB != null ? true : false;
	}

	/**
	  * Copies your database from your local assets-folder to the just created empty database in the
	  * system folder, from where it can be accessed and handled.
	  * This is done by transfering bytestream.
	  * */
	private void copyDataBase() throws IOException{

	        //Open your local db as the input stream
	        InputStream myInput = myContext.getAssets().open(DB_NAME);
	        
	        // Path to the just created empty db
	        String outFileName = DB_PATH + DB_NAME;
	        
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

	}

	public void openDataBase() throws SQLException{

	        //Open the database
	        String myPath = DB_PATH + DB_NAME;
	        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	        



	}

	@Override
	public synchronized void close() {

	    if(myDataBase != null)
	    myDataBase.close();
	    
	    super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	    // Add your public helper methods to access and get content from the database.
	    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
	    // to you to create adapters for your views.

	}

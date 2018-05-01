package net.a6te.lazycoder.savejourney.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import net.a6te.lazycoder.savejourney.modelClass.Emergency;

import java.util.ArrayList;

/**
 * Created by ashraf on 18/5/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "safeJourney";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    //database em phone number
    public static final String TABLE_EM="emTable";
    public static final String COL_EM_ID="emId";
    public static final String COL_EM_LOCATION="emLocation";
    public static final String COL_EM_CATEGORY="emCategory";
    public static final String COL_EM_NAME="emName";
    public static final String COL_EM_PHONE="emPhone";


    private static final String emTableQuery = "Create Table "+TABLE_EM+" ( "
            +COL_EM_ID+" Integer primary key, "+COL_EM_LOCATION+" Text, "
            +COL_EM_CATEGORY+" Text, "+COL_EM_NAME+" Text, "+COL_EM_PHONE+" Text);";




    //table user authentication
    public static final String TABLE_USER = "tblUser";
    public static final String COL_ID = "id";
    public static final String COL_USERNAME = "userName";
    public static final String COL_USEREMAIL = "userEmail";
    public static final String COL_USERPHONE = "userPhone";
    public static final String COL_USERPASS = "userPsss";
    public static final String COL_EMERGENCYCONTACT = "EmergencyContact";
    public static final String COL_ADDRESS = "address";

    private static final String userTable = "Create Table "+TABLE_USER+" ( "
            +COL_ID+" Integer primary key, "+COL_USERNAME+" Text, "
            +COL_USEREMAIL+" Text, "+COL_USERPHONE+" Text, "+COL_USERPASS
            +" Text, "+COL_EMERGENCYCONTACT+" Text, "+COL_ADDRESS+" Text);";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(userTable);
        sqLiteDatabase.execSQL(emTableQuery);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("Drop table if exist "+TABLE_USER);
        sqLiteDatabase.execSQL("Drop table if exist "+TABLE_EM);
        onCreate(sqLiteDatabase);

    }


}

package net.a6te.lazycoder.savejourney.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.a6te.lazycoder.savejourney.modelClass.Emergency;
import net.a6te.lazycoder.savejourney.userLogin.UserModelClass;

import java.util.ArrayList;


public class EmergencyDatabase {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private UserModelClass user;


    public EmergencyDatabase(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){

        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }



    public boolean setSomeData(){//this is only for testing purpose

        this.Open();

        ArrayList<Emergency> emergencyData = new Emergency().getAllData();

        long id = -1;
        for (Emergency emergency:emergencyData){
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_EM_LOCATION,emergency.getLocation());
            contentValues.put(dbHelper.COL_EM_CATEGORY,emergency.getCategory());
            contentValues.put(dbHelper.COL_EM_NAME,emergency.getName());
            contentValues.put(dbHelper.COL_EM_PHONE,emergency.getPhoneNo());

            id = database.insert(dbHelper.TABLE_EM,null,contentValues);

            Log.d("SafeJourney", "setSomeData: ----------"+id);
        }

        database.close();
        if (id < 0) return false;
        else return true;

    }



    public ArrayList<Emergency> getEmergencyData(String cityName){

        Emergency emergency;
        ArrayList<Emergency> cityEmergencyNumbers = new ArrayList<>();
        this.Open();

        Cursor cursor = database.rawQuery("Select *from "+dbHelper.TABLE_EM
                        +" where "+dbHelper.COL_EM_LOCATION +" = ? "
                ,new String[]{cityName});

        int userId = 0;
        int id = cursor.getCount();
        cursor.moveToFirst();
        if (id != 0 & id > 0){
            while (cursor.moveToNext()){
                String location = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_LOCATION));
                String category = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_CATEGORY));
                String name = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_PHONE));

                emergency = new Emergency(location,category,name,phoneNumber);
                cityEmergencyNumbers.add(emergency);
            }

        }

        cursor.close();
        this.Close();
        return cityEmergencyNumbers;

    }

    public ArrayList<Emergency> getEmergencyData(String cityName,String emergencyCategory){

        Emergency emergency;
        ArrayList<Emergency> cityEmergencyNumbers = new ArrayList<>();
        this.Open();

        Cursor cursor = database.rawQuery("Select *from "+dbHelper.TABLE_EM
                        +" where "+dbHelper.COL_EM_LOCATION +" = ? and "
                        +dbHelper.COL_EM_CATEGORY+" = ? "
                ,new String[]{cityName,emergencyCategory});

        int userId = 0;
        int id = cursor.getCount();
        cursor.moveToFirst();
        if (id != 0 & id > 0){
            while (cursor.moveToNext()){
                String location = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_LOCATION));
                String category = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_CATEGORY));
                String name = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(dbHelper.COL_EM_PHONE));

                emergency = new Emergency(location,category,name,phoneNumber);
                cityEmergencyNumbers.add(emergency);
            }

        }

        cursor.close();
        this.Close();
        return cityEmergencyNumbers;

    }



}

package net.a6te.lazycoder.savejourney.userLogin;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ashraf on 18/5/17.
 */

public class LogInfo {
    public static final String USERLOGSTATUS = "Status";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public LogInfo(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("Status",Context.MODE_PRIVATE);
        editor =preferences.edit();
    }
    public void addStatus(int id){

        editor.putInt(USERLOGSTATUS,id);
        editor.commit();
    }
    public int getStatus(){
        return preferences.getInt(USERLOGSTATUS,0);
    }

}

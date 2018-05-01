package net.a6te.lazycoder.savejourney;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ashraf on 20/5/17.
 */

public class DataSaveToSp {

    private Context context;
    public static final String PREFERENCE_NAME = "sharePreference";
    public static final String IS_Login = "islogin";
    public DataSaveToSp(Context context){
        this.context = context;
    }
    public void saveLoginStatus(boolean isLogin){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(this.IS_Login,isLogin);
        editor.commit();
    }

    public boolean isLogin(){
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE);
        return preferences.getBoolean(IS_Login,false);
    }
}

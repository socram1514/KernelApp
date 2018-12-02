package com.innovandoapps.library.kernel.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by desarrollo on 23/11/17.
 */

public abstract class BasePreferences {

    protected SharedPreferences preferences;

    public void init(Context context){
        preferences = context.getSharedPreferences(getFileName(),Context.MODE_PRIVATE);
    }

    public abstract  String getFileName();

    public void writeKey(String key,String valor){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,valor);
        editor.commit();
    }

    public String getValueKey(String key){
        return preferences.getString(key,"");
    }

    public void writeIntKey(String key,int valor){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key,valor);
        editor.commit();
    }


    public int getIntValueKey(String key){
        return preferences.getInt(key,0);
    }


    public void writeBooleanKey(String key,boolean valor){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key,valor);
        editor.commit();
    }


    public boolean getBooleanValueKey(String key){
        return preferences.getBoolean(key,false);
    }

}

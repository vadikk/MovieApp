package com.example.vadym.moviedirectoryapp.Constant;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Vadym on 20.01.2018.
 */

public class Prefs {

    SharedPreferences sharedPreferences;

    public Prefs(Activity activity){
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
    }

    public void setSearch(String search){
        sharedPreferences.edit().putString("search",search).commit();
    }

    public String getSearch(){return sharedPreferences.getString("search","Batman");}
}

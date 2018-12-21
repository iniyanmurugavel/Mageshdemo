package com.example.bcs.visualstore.PojoDatas;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharedpreference {


    public static String mpreference = "preference";
    public static SharedPreferences sharedpreference;
    public static SharedPreferences.Editor editor;

    public static String  order_id = "order_id";
    public static String Order="completedorder";
    public static String OrderType="ordertype";

    public static void onStorePreference(Context activity, String key, String value){
        sharedpreference = activity.getSharedPreferences(mpreference, Context.MODE_PRIVATE);
        editor = sharedpreference.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getPreferenceValue(Context activity,String key,String value){
        sharedpreference = activity.getSharedPreferences(mpreference,Context.MODE_PRIVATE);
        String val = sharedpreference.getString(key,value);
        return val;

}

}

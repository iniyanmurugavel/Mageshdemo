package com.example.bcs.visualstore.FireChat;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FireChatRetrofit {

    public  static String BaseUrl = "https://androidchatapp-76776.firebaseio.com";

    public  static Retrofit retrofit =  null;


    public static   Retrofit getClient(Context context){


        if(retrofit ==  null) {
            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BaseUrl)
                    .build();
        }
            return retrofit;
        }


}

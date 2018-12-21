package com.example.bcs.visualstore;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.bcs.visualstore.View.Activity.DashBoard;
import com.example.bcs.visualstore.View.Activity.LoginActivity;


public class LogoutServices extends Service {

    public static CountDownTimer timer;
    SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",0);

    Boolean aTrue=preferences.getBoolean("true",false);



    @Override
    public void onCreate(){
        super.onCreate();

        timer=new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (!aTrue) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    stopSelf();
                }

            }
        };
        timer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

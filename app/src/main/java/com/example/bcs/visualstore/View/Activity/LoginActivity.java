package com.example.bcs.visualstore.View.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.example.bcs.visualstore.PojoDatas.Data;
import com.example.bcs.visualstore.MyDeserializer;
import com.example.bcs.visualstore.PojoDatas.Pojo;
import com.example.bcs.visualstore.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Pojo pojo;
    ProgressDialog progressBar;
    EditText username, password;
    SharedPreferences.Editor editor1;
    String name, pass;
    APIService mAPIService;
    String share;
    SharedPreferences prefs;
    Data c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.darkgrey)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.darkgrey)); //status bar or the time bar at the top
        }
        SharedPreferences pref=getApplicationContext().getSharedPreferences("MyPref", 0); ;
        String emailPhone= pref.getString("Login",null);

        if (!TextUtils.isEmpty(emailPhone)){
            startActivity(new Intent(LoginActivity.this, DashBoard.class));
            finish();

        }else {
            setContentView(R.layout.activity_login);
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            login = (Button) findViewById(R.id.btn_login);
            mAPIService = ApiUtils.getLoginService();

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = username.getText().toString().trim();
                    pass = password.getText().toString().trim();
                    Log.e("name", name);
                    Log.e("pass", pass);
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass)) {

                       boolean network= isNetworkAvailable();

                       if(network) {

                           pojo = new Pojo(name, pass);
                           progressBar = new ProgressDialog(v.getContext(),R.style.MyAlertDialogStyle);
                           progressBar.setIndeterminate(true);
                           progressBar.setMessage("Loading...");
                           progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                           progressBar.setProgress(0);
                           progressBar.setMax(100);
                           progressBar.setCancelable(true);
                           progressBar.setCanceledOnTouchOutside(true);
                           progressBar.show();

                           mAPIService.loginPost(pojo).enqueue(new Callback<JsonElement>() {
                               @Override
                               public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {


                                   Gson gson =
                                           new GsonBuilder()
                                                   .registerTypeAdapter(Data.class, new MyDeserializer())
                                                   .create();

                                   if (response.code() == 200) {

                                       // JSONObject jsonObject = new JSONObject(String.valueOf(response.body()));
                                       String dd = response.body().toString();

                                        c = gson.fromJson(new Gson().toJson(response.body()), Data.class);
                                       System.out.println(c.id);
                                       System.out.println(c.auth_id);
                                       System.out.println(dd);

                                       SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                       SharedPreferences.Editor editor = pref.edit();
                                       editor.putString("id", c.id);
                                       editor.putString("auth_id", c.auth_id);
                                       editor.putBoolean("true",true);
                                       editor.putString("userName", name);
                                       editor.apply();

                                       Intent intent = new Intent(LoginActivity.this, DashBoard.class);
                                       // intent.putExtras(bundle);
                                       startActivity(intent);
                                       progressBar.dismiss();

                                   } else {
                                       if (response.code()==406){
                                           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                                           alertDialogBuilder.setMessage("Please check username and password");
//
                                           AlertDialog alertDialog = alertDialogBuilder.create();
                                           alertDialog.show();

                                       }
                                       progressBar.dismiss();
                                       System.out.println(response.code());
                                   }
                               }

                               @Override
                               public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                                   System.out.println(t.getMessage());
                                   progressBar.dismiss();
                               }
                           });
                       }else {
                           Toast.makeText(LoginActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
                       }

                    } else {
                        Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        }

    protected void onRestart(){
        super.onRestart();
        prefs = getApplicationContext().getSharedPreferences("MyPref", 0);
        share = prefs.getString("Login", null);
        String auth_id=prefs.getString("auther_id",null);
        Log.e("auther id is",""+c.auth_id);

        startActivity(new Intent(LoginActivity.this, DashBoard.class));
        finish();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

}

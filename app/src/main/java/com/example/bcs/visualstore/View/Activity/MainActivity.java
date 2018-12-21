package com.example.bcs.visualstore.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bcs.visualstore.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=new Intent(this,DashBoard.class);
        startActivity(intent);
       // setContentView(R.layout.activity_main);
    }
}

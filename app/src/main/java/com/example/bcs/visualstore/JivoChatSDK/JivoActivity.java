package com.example.bcs.visualstore.JivoChatSDK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

//************************

import com.example.bcs.visualstore.R;



//**********
public class JivoActivity extends Activity implements JivoDelegate {

    //**************
    JivoSdk jivoSdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jivo);

//        String lang = Locale.getDefault().getLanguage().indexOf("ru") >= 0 ? "ru": "en";

        //*********************************************************
        jivoSdk = new JivoSdk((WebView) findViewById(R.id.webview));
        jivoSdk.delegate = this;
        jivoSdk.prepare();

        Log.d("JIVO-DD","---DELEGATE----" + jivoSdk.delegate);
    }

    //*********************************************
    @Override
    public void onEvent(String name, String data) {
        if(name.equals("url.click")){
            if(data.length() > 2){
                String url = data.substring(1, data.length() - 1);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                Log.d("JIVO-DD","---URL----" + url);
                startActivity(browserIntent);
            }
        }
    }


}

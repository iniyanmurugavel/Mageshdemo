package com.example.bcs.visualstore.View.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bcs.visualstore.FireChat.FireChatChat;
import com.example.bcs.visualstore.JivoChatSDK.JivoActivity;
import com.example.bcs.visualstore.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactInformation extends Fragment {

    private Activity activity;

    ImageView toolimg;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_contact_information, container, false);

        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        toolimg=(ImageView) toolbar.findViewById(R.id.menuLogo);
//        tooltxt.setTextSize(15);
        tooltxt.setText("CONTACT INFORMATION");


        toolimg.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));


        ButterKnife.bind(this,v);
        return v;

    }

    @OnClick(R.id.chat_withus)
    public void onChatwithAdmin(){
        startActivity(new Intent(getActivity(), JivoActivity.class));
    }


}
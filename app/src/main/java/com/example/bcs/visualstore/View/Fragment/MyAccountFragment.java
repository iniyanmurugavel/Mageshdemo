package com.example.bcs.visualstore.View.Fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyAccountFragment extends Fragment {

    APIService apiService;
    String auth_id="a3ezatyge8ere6an";
    SharedPreferences preferences;
    ProgressDialog progressBar;
    EditText edit_customerName,edit_customerPhone,edit_email;
    ImageView imageView;
    APIService mAPIService;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_account, container, false);

        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        imageView=(ImageView)toolbar.findViewById(R.id.menuLogo);
        // imageView.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));
        tooltxt.setText("MY ACCOUNT");
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));

        preferences=getActivity().getSharedPreferences("MyPref",0);
        auth_id=preferences.getString("auth_id","1");



        mAPIService= ApiUtils.getMyprofile(auth_id);
        edit_customerName=(EditText)v.findViewById(R.id.customer_name);
        edit_customerPhone=(EditText)v.findViewById(R.id.edit_phone);
        edit_email=(EditText)v.findViewById(R.id.edit_email);


        init();

        return v;

    }

    public void init() {


        progressBar = new ProgressDialog(getContext(),R.style.MyAlertDialogStyle);
        progressBar.setMessage("Loading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.setCancelable(true);
        progressBar.setCanceledOnTouchOutside(true);
        progressBar.show();
        mAPIService.myprofile().enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {

                //JsonElement=response.body();

                if (response.code() == 200) {
                    System.out.println("response of profile"+response.body());
                    String dd = response.body().toString();
                    try {
                        JSONObject object=new JSONObject(dd);
                        for (int i=0;i<object.length();i++){
                            JSONObject c=object.getJSONObject("data");
                            System.out.println(c);

                            String id=c.getString("id");
                            String name1=c.getString("name");
                            String phone=c.getString("phone");
                            String email=c.getString("email");
                            String username=c.getString("username");
                            edit_customerName.setText(name1);
                            edit_customerPhone.setText(phone);
                            edit_email.setText(email);
                        }
                    }catch (Exception e){

                    }


                    progressBar.dismiss();

                } else {
                    progressBar.dismiss();
                    System.out.println(response.code());
                    if (response.code() == 406) {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
                progressBar.dismiss();

            }
        });
    }



}
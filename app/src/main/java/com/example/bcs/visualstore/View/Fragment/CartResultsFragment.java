package com.example.bcs.visualstore.View.Fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcs.visualstore.ObjectSerializer;
import com.example.bcs.visualstore.PojoDatas.AdvanceInput;
import com.example.bcs.visualstore.PojoDatas.DeleteOrder;
import com.example.bcs.visualstore.PojoDatas.GetOrder;
import com.example.bcs.visualstore.PojoDatas.Sharedpreference;
import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.example.bcs.visualstore.View.Adapter.CustomAdapter;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.PojoDatas.RowItem;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartResultsFragment extends Fragment {

    ListView listView;
    ArrayList<String> order_U_id, order_id, order_date, lenstype, status, CustName, OrderType,AddedDate;
    ArrayList<Integer> id;
    List<RowItem> rowItems;
    Button lens_order;
    String id1,auth_id;
    String PREFS_NAME = "MyPrefName";
    String PREFS_KEY = "MyPrefKey";
    SharedPreferences preferences;
    ProgressDialog progressBar;
    String stat,toolBarName;
    String admin="&admin_id=";
    TextView txt_content,txt_cartResult;
    ImageView edit, delete;
    public static final String[] titles = new String[]{"Date",
            "Date", "Date", "Date", "Date", "Date"};

    GetOrder getOrder=new GetOrder();

    APIService mAPIService;
    public EditText editsearch;
    public ImageView search;
    ImageView toolimg;

    public CartResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //container.removeAllViews();
        View view = (View) inflater.inflate(R.layout.fragment_cart_results, container, false);
        txt_cartResult=(TextView)view.findViewById(R.id.cart_results);

        Log.d("ORDER", stat = "?s=0" + Sharedpreference.getPreferenceValue(getActivity(),Sharedpreference.Order,""));
        if(Sharedpreference.getPreferenceValue(getActivity(),Sharedpreference.Order,"").equals("3"))
        {
            stat  = "";
        }else{
            stat = "?s=0" + Sharedpreference.getPreferenceValue(getActivity(),Sharedpreference.Order,"");
        }

        System.out.println("state"+stat);

        edit=(ImageView)view.findViewById(R.id.edit);
        txt_content=(TextView)view.findViewById(R.id.txt_content);
//        Bundle b= new Bundle();
//        b=getArguments();
//        if(b!=null) {
//            stat = "?s=0"+b.getString("status");
//            System.out.println("stats" + stat);
//        }else{
//            stat="";
//        }
        preferences=getContext().getSharedPreferences("MyPref",0);
        id1= preferences.getString("id","1");
        auth_id=preferences.getString("auth_id","1");
        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        toolimg=(ImageView) toolbar.findViewById(R.id.menuLogo);
        toolimg.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));


        delete = (ImageView) view.findViewById(R.id.delete);



        if (stat.equals("?s=01")){
            tooltxt.setText("CART");
            txt_cartResult.setText("Incomplete orders");
        }else if (stat.equals("?s=02")){
            tooltxt.setText("CART");
            txt_cartResult.setText("Complete orders");
        }else {
            admin="?admin_id=";
            tooltxt.setText("CART");
            txt_cartResult.setText("All orders");
        }

        editsearch = (EditText) view.findViewById(R.id.editsearch);
        search = (ImageView) view.findViewById(R.id.searchfilter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editsearch.getText().toString().trim().length() == 0) {
                  //  Toast.makeText(getActivity(), "Enter the search field", Toast.LENGTH_SHORT).show();
                } else {

                    GetFiltereordersList orderApi = new GetFiltereordersList();
                    orderApi.execute();
                }
            }
        });

        /*method created*/
        listerner();

        listView = (ListView) view.findViewById(R.id.list_view);
//        lens_order=(Button)view.findViewById(R.id.new_order);
//        txt_content=(TextView)view.findViewById(R.id.txt_content);
        SentOrderApi orderApi =new SentOrderApi();
        orderApi.execute();



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (Sharedpreference.getPreferenceValue(getActivity(), Sharedpreference.order_id, "").equals("")) {
                if (ApiUtils.INCOMPLETE==0) {
                    Toast.makeText(getActivity(), "Please select the value", Toast.LENGTH_SHORT).show();
                } else {

                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogDanger);
                    } else {
                        builder = new AlertDialog.Builder(getActivity());
                    }
                    builder.setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?")

                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete

                                    onDelete();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });


        return view;
    }



    private void listerner() {
        /*On click edit button api will call and redirect to lens order screen */
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ApiUtils.INCOMPLETE!=0) {
                    SharedPreferences setting = getActivity().getSharedPreferences("hi", Context.MODE_PRIVATE);
                    setting.edit().clear().apply();
                    editMethod();

                }else {
                    AlertDialog1();
                }
            }

        });
    }

    public class SentOrderApi extends AsyncTask<String, Void, String> {
        String server_response = "";
        ArrayList<String> prgmName;
        Dialog dialog;


        @Override
        protected void onPreExecute() {
            progressBar = new ProgressDialog(getContext(),R.style.MyAlertDialogStyle);
            progressBar.setCancelable(true);
            progressBar.setMessage("Loading...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();
        }


        @Override
        protected String doInBackground(String... strings) {

            URL url;
            String postUrl= "http://103.21.59.241/carl_visustore/master_api/get_orders"+stat+admin+id1;
            System.out.println("url1"+postUrl);
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(postUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                urlConnection.setDoInput(true);
                urlConnection.setConnectTimeout(20 * 1000);
                urlConnection.setReadTimeout(20 * 1000);
                urlConnection.addRequestProperty("Admin-Service","visustore-RESTApi");
                urlConnection.addRequestProperty("Auth-Key","BwebRestAPI");
                urlConnection.addRequestProperty("Content-Type","application/json; charset=UTF-8");
//                urlConnection.addRequestProperty("Auth-Id",auth_id);
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                System.out.println("Response Code of Image frag: " + responseCode);

                if (responseCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    server_response = String.valueOf(response);
                    System.out.println("respon of image"+server_response);
                } else {
                    server_response = "";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                progressBar.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
                progressBar.dismiss();
            }

            return server_response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();

            order_U_id = new ArrayList<>();
            order_id = new ArrayList<>();
            order_date = new ArrayList<>();
            lenstype=new ArrayList<>();
            status=new ArrayList<>();
            CustName=new ArrayList<>();
            OrderType=new ArrayList<>();
            AddedDate=new ArrayList<>();
            id=new ArrayList<>();

            String image;
            Log.e("Response","" + server_response);
            System.out.println("Server response : " + server_response);
            try {
                JSONObject jsonObj = new JSONObject(server_response);

                // Getting JSON Array node
                JSONArray gallery = jsonObj.getJSONArray("data");
                System.out.println(gallery.length());
                if(gallery.length()==0){
                    txt_content.setVisibility(View.VISIBLE);
                }else {

                    for (int j = 0; j < gallery.length(); j++) {
                        JSONObject jobj = gallery.getJSONObject(j);
                        String OrdUId = jobj.getString("admin_id");
                        String OrderId = jobj.getString("order_id");
                        String OrderDat = jobj.getString("order_date");
                        Integer Id = jobj.getInt("id");
                        String Stat = jobj.getString("status");
                        String CustomerName = jobj.getString("consumer_name");
//                    String OrderTyp=jobj.getString("order_type");
//                    String adddate=jobj.getString("order_date");

//                    AddedDate.add(adddate);
                        order_U_id.add(OrdUId);
                        order_id.add(OrderId);
                        order_date.add(OrderDat);
                        id.add(Id);
                        CustName.add(CustomerName);
                        status.add(Stat);

                    }
                }
//                }

                listView.setAdapter(new CustomAdapter(getActivity(),CartResultsFragment.this,order_U_id, order_id, order_date, status, CustName,id));
//                progressBar.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
    public class Getorders extends AsyncTask<String, Void, String> {
        String server_response = "";
        ArrayList<String> prgmName;
        Dialog dialog;

        @Override
        protected void onPreExecute() {
            progressBar = new ProgressDialog(getContext(),R.style.AlertDialogDanger);
            progressBar.setCancelable(true);
            progressBar.setMessage("Loading...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.show();
        }


        @Override
        protected String doInBackground(String... strings) {

            URL url;
            String postUrl= "http://103.21.59.241/carl_visustore/master_api/get_orders"+stat+admin+id1+"&id="+ApiUtils.INCOMPLETE;
            System.out.println("url of e"+postUrl);
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(postUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type","application/json");
                urlConnection.addRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                urlConnection.setDoInput(true);
                urlConnection.setConnectTimeout(20 * 1000);
                urlConnection.setReadTimeout(20 * 1000);
                urlConnection.addRequestProperty("Admin-Service","visustore-RESTApi");
                urlConnection.addRequestProperty("Auth-Key","BwebRestAPI");
                urlConnection.addRequestProperty("Content-Type","application/json; charset=UTF-8");
//                urlConnection.addRequestProperty("Auth-Id",auth_id);
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                System.out.println("getOrders for particular checkId: " + responseCode);


                if (responseCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    server_response = String.valueOf(response);

                    try {
                        JSONObject jsonObj = new JSONObject(server_response);


                        JSONObject data =jsonObj.getJSONObject("data");
                        String content =data.optString("content");
                        System.out.println("content___ "+content);

                        JSONObject jsonObjectContent = new JSONObject(content);
                        System.out.println("jsonObjectContent{___ "+jsonObjectContent);


//                        Additing else condition for content json result

                        if(jsonObjectContent.has("order_reference")){
                            getOrder.setOrder_reference(jsonObjectContent.optString("order_reference"));
                        } else if(!jsonObjectContent.has("order_reference")){
                            getOrder.setOrder_reference("");
                        }


                        if(jsonObjectContent.has("patient_firstName")){
                            getOrder.setPatient_firstName(jsonObjectContent.optString("patient_firstName"));
                        } else if(!jsonObjectContent.has("patient_firstName")){
                            getOrder.setPatient_firstName("");
                        }

                        if(jsonObjectContent.has("patient_lastName")){
                            getOrder.setPatient_lastName(jsonObjectContent.optString("patient_lastName"));
                        } else if(!jsonObjectContent.has("patient_lastName")){
                            getOrder.setPatient_lastName("");

                        }


                        if(jsonObjectContent.has("patient_term")){
                            getOrder.setPatient_term(jsonObjectContent.optString("patient_term"));
                        } else if(!jsonObjectContent.has("patient_term")){
                            getOrder.setPatient_term("");
                        }



                        if(jsonObjectContent.has("side")){
                            getOrder.setSide(jsonObjectContent.optString("side"));
                        } else if(!jsonObjectContent.has("side")){
                            getOrder.setSide("");
                        }


                        if(jsonObjectContent.has("lens_r_sphere")){
                            getOrder.setLens_r_sphere(jsonObjectContent.optString("lens_r_sphere"));
                        } else if(!jsonObjectContent.has("lens_r_sphere")){
                            getOrder.setLens_r_sphere("");
                        }

                        if(jsonObjectContent.has("lens_r_power")){
                            getOrder.setLens_r_power(jsonObjectContent.optString("lens_r_power"));
                        } else if(!jsonObjectContent.has("lens_r_power")){
                            getOrder.setLens_r_power("");
                        }


                        if(jsonObjectContent.has("lens_r_addition")){
                            getOrder.setLens_r_addition(jsonObjectContent.optString("lens_r_addition"));
                        }else if(!jsonObjectContent.has("lens_r_addition")){
                            getOrder.setLens_r_addition("");
                        }


                        if(jsonObjectContent.has("lens_r_axis")){
                            getOrder.setLens_r_axis(jsonObjectContent.optString("lens_r_axis"));
                        } else if(!jsonObjectContent.has("lens_r_axis")){
                            getOrder.setLens_r_axis("");
                        }

                        if(jsonObjectContent.has("lens_r_commercialCode")){
                            getOrder.setLens_r_commercialCode(jsonObjectContent.optString("lens_r_commercialCode"));
                            System.out.println("code is "+getOrder.getLens_r_commercialCode());
                        } else if(!jsonObjectContent.has("lens_r_commercialCode")) {
                            getOrder.setLens_r_commercialCode("");
                        }

                        if(jsonObjectContent.has("lens_r_dia")){
                            getOrder.setLens_r_dia(jsonObjectContent.optString("lens_r_dia"));
                        } else   if(!jsonObjectContent.has("lens_r_dia")) {
                            getOrder.setLens_r_dia("");
                        }


                        if(jsonObjectContent.has("employee_name")){
                            getOrder.setEmployee_name(jsonObjectContent.optString("employee_name"));
                        } else if(!jsonObjectContent.has("employee_name")){
                            getOrder.setEmployee_name("");
                        }


                        if(jsonObjectContent.has("portfolio")){
                            getOrder.setPortfolio(jsonObjectContent.optString("portfolio"));
                        } else if(!jsonObjectContent.has("portfolio")){
                            getOrder.setPortfolio("");
                        }

                        getOrder.setCons("Vision Center");

                        if(jsonObjectContent.has("lens_hard")){
                            getOrder.setLens_hard(jsonObjectContent.optString("lens_r_dia"));
                        } else if(!jsonObjectContent.has("lens_hard")) {
                            getOrder.setLens_hard("");
                        }


                        if(jsonObjectContent.has("coating_commercialType")){
                            getOrder.setCoating_commercialType(jsonObjectContent.optString("coating_commercialType"));
                        } else if(!jsonObjectContent.has("coating_commercialType")) {
                            getOrder.setCoating_commercialType("");
                        }

                        if(jsonObjectContent.has("coating_commercialCode")){
                            getOrder.setCoating_commercialCode(jsonObjectContent.optString("coating_commercialCode"));
                        } else if(!jsonObjectContent.has("coating_commercialCode")) {
                            getOrder.setCoating_commercialCode("");
                        }

                        if (jsonObjectContent.has("coating_commercialCodeName")){
                            getOrder.setCoating_commercialCodeName(jsonObjectContent.optString("coating_commercialCodeName"));

                        }else if (!jsonObjectContent.has("coating_commercialCodeName")){
                            getOrder.setCoating_commercialCodeName("");
                        }

                        Log.e("coating name is ",getOrder.getCoating_commercialCodeName());

                        if(jsonObjectContent.has("coating_commercialTintName")){
                            getOrder.setCoating_commercialTintName(jsonObjectContent.optString("coating_commercialTintName"));
                            System.out.println("tint_name"+getOrder.getCoating_commercialTintName());
                        } else if(!jsonObjectContent.has("coating_commercialTintName")){
                            getOrder.setCoating_commercialTintName(""); }


                        if(jsonObjectContent.has("lens_l_dia")){
                            getOrder.setLens_l_dia(jsonObjectContent.optString("lens_l_dia"));
                        } else if(!jsonObjectContent.has("lens_l_dia")){
                            getOrder.setLens_l_dia(""); }


                        if(jsonObjectContent.has("centration_r_pdz")){
                            getOrder.setCentration_r_pdz(jsonObjectContent.optString("centration_r_pdz"));
                        } else if(!jsonObjectContent.has("centration_r_pdz")){
                            getOrder.setCentration_r_pdz(""); }

                        if(jsonObjectContent.has("lens_lr_lens")){
                            System.out.println("radio"+jsonObjectContent.optString("lens_lr_lens"));
                            String sides=jsonObjectContent.optString("lens_lr_lens");
                            String rs = sides.replaceAll("\\[", "").replaceAll("\\]","")
                                    .replaceAll("\"","");
                            System.out.println("remove"+rs);
                            getOrder.setLens_lr_lens(rs);
                            System.out.println("setting"+getOrder.getLens_lr_lens());
                        } else if(!jsonObjectContent.has("lens_lr_lens")){
                            getOrder.setLens_lr_lens("");
                        }


                        if(jsonObjectContent.has("centration_r_bvd")){
                            getOrder.setCentration_r_bvd(jsonObjectContent.optString("centration_r_bvd"));
                        } else if(!jsonObjectContent.has("centration_r_bvd")){
                            getOrder.setCentration_r_bvd("");}

                        if(jsonObjectContent.has("frameNamedef")){
                            getOrder.setFrameNamedef(jsonObjectContent.optString("frameNamedef"));
                        } else if(!jsonObjectContent.has("frameNamedef")){
                            getOrder.setFrameNamedef("");
                            }


                        if(jsonObjectContent.has("geometry_r_optimavsm")){
                            getOrder.setGeometry_r_optimavsm(jsonObjectContent.optString("geometry_r_optimavsm"));
                        } else if(!jsonObjectContent.has("geometry_r_optimavsm")) {
                            getOrder.setGeometry_r_optimavsm("");
                        }


                        if(jsonObjectContent.has("coating_commercialTint")){
                            getOrder.setCoating_commercialTint(jsonObjectContent.optString("coating_commercialTint"));
                        } else if(!jsonObjectContent.has("coating_commercialTint")){
                            getOrder.setCoating_commercialTint(""); }

                        if(jsonObjectContent.has("coating_no_uv")){
                            getOrder.setCoating_no_uv(jsonObjectContent.optString("coating_no_uv"));
                        } else  if(!jsonObjectContent.has("coating_no_uv")) {
                            getOrder.setCoating_no_uv("");
                        }

                        if(jsonObjectContent.has("lens_l_sphere")){
                            getOrder.setLens_l_sphere(jsonObjectContent.optString("lens_l_sphere"));
                        } else if(!jsonObjectContent.has("lens_l_sphere")) {
                            getOrder.setLens_l_sphere("");
                        }

                        if(jsonObjectContent.has("lens_l_power")){
                            getOrder.setLens_l_power(jsonObjectContent.optString("lens_l_power"));
                        } else if(!jsonObjectContent.has("lens_l_power")) {
                            getOrder.setLens_l_power("");
                        }

                        if(jsonObjectContent.has("lens_l_axis")){
                            getOrder.setLens_l_axis(jsonObjectContent.optString("lens_l_axis"));
                        } else  if(!jsonObjectContent.has("lens_l_axis")) {
                            getOrder.setLens_l_axis("");
                        }


                        if(jsonObjectContent.has("lens_l_addition")){
                            getOrder.setLens_l_addition(jsonObjectContent.optString("lens_l_addition"));
                        } else if(!jsonObjectContent.has("lens_l_addition")){
                            getOrder.setLens_l_addition(""); }

                        if(jsonObjectContent.has("lens_l_commercialCode")){
                            getOrder.setLens_l_commercialCode(jsonObjectContent.optString("lens_l_commercialCode"));
                            System.out.println("code is ..."+getOrder.getLens_l_commercialCode());
                        } else if(!jsonObjectContent.has("lens_l_commercialCode")) {
                            getOrder.setLens_l_commercialCode("");
                        }

                        if(jsonObjectContent.has("centration_l_pdz")){
                            getOrder.setCentration_l_pdz(jsonObjectContent.optString("centration_l_pdz"));
                        } else  if(!jsonObjectContent.has("centration_l_pdz")){
                            getOrder.setCentration_l_pdz("");}


                        if(jsonObjectContent.has("centration_l_height")){
                            getOrder.setCentration_l_height(jsonObjectContent.optString("centration_l_height"));
                        } else if(!jsonObjectContent.has("centration_l_height")){
                            getOrder.setCentration_l_height("");}


                        if(jsonObjectContent.has("centration_r_height")){
                            getOrder.setCentration_r_height(jsonObjectContent.optString("centration_r_height"));
                        } else if(!jsonObjectContent.has("centration_r_height")){
                            getOrder.setCentration_r_height("");}


                        if(jsonObjectContent.has("centration_l_bvd")){
                            getOrder.setCentration_l_bvd(jsonObjectContent.optString("centration_l_bvd"));
                        } else  if(!jsonObjectContent.has("centration_l_bvd")){
                            getOrder.setCentration_l_bvd("");}


                        if(jsonObjectContent.has("geometry_l_optimavsm")){
                            getOrder.setGeometry_l_optimavsm(jsonObjectContent.optString("geometry_l_optimavsm"));
                        } else if(!jsonObjectContent.has("geometry_l_optimavsm")){
                            getOrder.setGeometry_l_optimavsm(""); }


                        if(jsonObjectContent.has("frm_width")){
                            getOrder.setFrm_width(jsonObjectContent.optString("frm_width"));
                        } else   if(!jsonObjectContent.has("frm_width")){
                            getOrder.setFrm_width(""); }

                        if(jsonObjectContent.has("frm_height")){
                            getOrder.setFrm_height(jsonObjectContent.optString("frm_height"));
                        } else  if(!jsonObjectContent.has("frm_height")){
                            getOrder.setFrm_height("");}

                        if(jsonObjectContent.has("frm_material")){
                            getOrder.setFrm_material(jsonObjectContent.optString("frm_material"));
                        } else if(!jsonObjectContent.has("frm_material")){
                            getOrder.setFrm_material(""); }

                        if(jsonObjectContent.has("frm_model")){
                            getOrder.setFrm_model(jsonObjectContent.optString("frm_model"));
                        } else  if(!jsonObjectContent.has("frm_model")){
                            getOrder.setFrm_model("");}

                        if(jsonObjectContent.has("frm_dbl")){
                            getOrder.setFrm_dbl(jsonObjectContent.optString("frm_dbl"));
                        } else if(!jsonObjectContent.has("frm_dbl")){
                            getOrder.setFrm_dbl("");}

                        if(jsonObjectContent.has("panangle")){
                            getOrder.setPanangle(jsonObjectContent.optString("panangle"));
                        } else if(!jsonObjectContent.has("panangle")){
                            getOrder.setPanangle("");}

                        if(jsonObjectContent.has("bowangle")){
                            getOrder.setBowangle(jsonObjectContent.optString("bowangle"));
                        } else if(!jsonObjectContent.has("bowangle")){
                            getOrder.setBowangle("");}

                        if(jsonObjectContent.has("lens_r_commercialName")){
                            getOrder.setLens_r_commercialName(jsonObjectContent.optString("lens_r_commercialName"));
                        } else if(!jsonObjectContent.has("lens_r_commercialName")){
                            getOrder.setLens_r_commercialName(""); }

                        if(jsonObjectContent.has("lens_l_commercialName")){
                            getOrder.setLens_l_commercialName(jsonObjectContent.optString("lens_l_commercialName"));
                        } else if(!jsonObjectContent.has("lens_l_commercialName")) {
                            getOrder.setLens_l_commercialName("");
                        }

                        progressBar.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    server_response = "";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                progressBar.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
                progressBar.dismiss();
            }

            return server_response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);





        }

    }





    public class GetFiltereordersList extends AsyncTask<String, Void, String> {
        String server_response = "";
        ArrayList<String> prgmName;
        Dialog dialog;

        @Override
        protected void onPreExecute() {
            progressBar = new ProgressDialog(getContext(),R.style.AlertDialogDanger);
            progressBar.setMessage("Loading...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.setCancelable(true);
            progressBar.setCanceledOnTouchOutside(true);
            progressBar.show();
        }


        @Override
        protected String doInBackground(String... strings) {

            URL url;
            String postUrl = "http://103.21.59.241/carl_visustore/master_api/get_orders"+stat+admin+id1+"&t="+editsearch.getText().toString().trim();
            System.out.println("url" + postUrl);
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(postUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
                urlConnection.setDoInput(true);
                urlConnection.setConnectTimeout(20 * 1000);
                urlConnection.setReadTimeout(20 * 1000);
                urlConnection.addRequestProperty("Admin-Service", "visustore-RESTApi");
                urlConnection.addRequestProperty("Auth-Key", "BwebRestAPI");
                urlConnection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                urlConnection.addRequestProperty("Auth-Id",auth_id);
                urlConnection.connect();

                int responseCode = urlConnection.getResponseCode();
                System.out.println("getOrders for particular checkId: " + responseCode);

                if (responseCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    server_response = String.valueOf(response);
                    System.out.println("respon of image" + server_response);
                } else {
                    server_response = "";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                progressBar.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
                progressBar.dismiss();
            }

            return server_response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();

            progressBar.dismiss();

            order_U_id = new ArrayList<>();
            order_id = new ArrayList<>();
            order_date = new ArrayList<>();
            lenstype = new ArrayList<>();
            status = new ArrayList<>();
            CustName = new ArrayList<>();
            OrderType = new ArrayList<>();
            AddedDate = new ArrayList<>();
            id = new ArrayList<>();

            String image;
            Log.e("Response", "" + server_response);
            System.out.println("Server response : " + server_response);
            try {
                JSONObject jsonObj = new JSONObject(server_response);

                // Getting JSON Array node
                JSONArray gallery = jsonObj.getJSONArray("data");
                System.out.println(gallery.length());
                if (gallery.length() == 0) {
                    txt_content.setVisibility(View.VISIBLE);
                } else {

                    for (int j = 0; j < gallery.length(); j++) {
                        JSONObject jobj = gallery.getJSONObject(j);
                        String OrdUId = jobj.getString("admin_id");
                        String OrderId = jobj.getString("order_id");
                        String OrderDat = jobj.getString("order_date");
                        Integer Id = jobj.getInt("id");
                        String Stat = jobj.getString("status");
                        String CustomerName = jobj.getString("consumer_name");
//                    String OrderTyp=jobj.getString("order_type");
//                    String adddate=jobj.getString("order_date");

//                    AddedDate.add(adddate);
                        order_U_id.add(OrdUId);
                        order_id.add(OrderId);
                        order_date.add(OrderDat);
                        id.add(Id);
                        CustName.add(CustomerName);
                        status.add(Stat);

                    }
                }
//                }

                listView.setAdapter(new CustomAdapter(getActivity(), CartResultsFragment.this, order_U_id, order_id, order_date, status, CustName, id));
//                progressBar.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    private void onDelete() {
//         onProgress();
        mAPIService = ApiUtils.getLoginService();
        DeleteOrder deleteOrder = new DeleteOrder();
        deleteOrder.setOrder_id(Sharedpreference.getPreferenceValue(getActivity(), Sharedpreference.order_id, ""));

//        System.out.println("oreder id"+Sharedpreference.order_id);

        mAPIService.deleteOrder(deleteOrder).enqueue(new Callback<JsonElement>() {


            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {

                Fragment fragment = new CartResultsFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Sharedpreference.onStorePreference(getActivity(),Sharedpreference.order_id,"");
            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
//                System.out.println(t.getMessage());
//                Log.d("ERR", t.getLocalizedMessage());
                progressBar.dismiss();

            }
        });
    }




//    @Override
//    public void onDestroyView() {
//        //mContainer.removeAllViews();
//        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.fragmentContainer);
//        mContainer.removeAllViews();
//        super.onDestroyView();
//    }



    public void editMethod(){


        CartResultsFragment.Getorders orderApi1 = new CartResultsFragment.Getorders();
        try {
            orderApi1.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("id of checked"+ApiUtils.INCOMPLETE);
        System.out.println("reference name"+getOrder.getOrder_reference());
        Bundle bundle=new Bundle();
        bundle.putString("state","fromCartLens");
        String serializedData = getOrder.serialize();
        System.out.println("serializied data"+serializedData);
        SharedPreferences preferencesReader = Objects.requireNonNull(getActivity()).getSharedPreferences("hione", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesReader.edit();
        editor.putString(PREFS_KEY, serializedData);
        editor.apply();


        Fragment fragment = new LensOrderingFragment();
        System.out.println("bundle is "+bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void AlertDialog1() {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogDanger);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle("Alert")
                .setMessage("Please Select the Order")

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // editMethod();
                        dialog.dismiss();

                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
package com.example.bcs.visualstore.View.Fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
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


import com.example.bcs.visualstore.PojoDatas.RowItem;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.View.Adapter.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchOrdersFragment extends Fragment {
    List<RowItem> list;
    ArrayList<String> order_reference, order_id, order_date, lenstype, status, employee, OrderType, AddedDate;
    Button lens_order;
    ListAdapter adapter;
    SharedPreferences preferences;
    String id1;
    ListView listView;
    TextView txt_content;
    Button newButton;
    String searchTextName;
    ProgressDialog progressBar;
    private EditText mSearchOrderEdit;
    private ImageView mSearchFilterOrder;
    ImageView toolimg;
    public static final String[] titles = new String[]{"Date",
            "Date", "Date", "Date", "Date", "Date"};


    DateFormat format = new SimpleDateFormat("dd-mm-yyyy");

    public SearchOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_orders, container, false);


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView tooltxt = (TextView) toolbar.findViewById(R.id.tooltxt);
        toolimg=(ImageView) toolbar.findViewById(R.id.menuLogo);

        tooltxt.setText("SENT ORDERS");
        toolimg.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));

        preferences = getContext().getSharedPreferences("MyPref", 0);
        id1 = preferences.getString("id", "1");

        txt_content = (TextView) view.findViewById(R.id.txt_content1);
        Bundle b = new Bundle();
        b = getArguments();
        if (b != null) {
            searchTextName = b.getString("sentOrders");


        }


        listView = (ListView) view.findViewById(R.id.list);

//        lens_order=(Button) view.findViewById(R.id.new_order);

        SentOrderApi sentOrderApi = new SentOrderApi();
        try {
            sentOrderApi.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


//        lens_order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lens_order.setBackground(getResources().getDrawable(R.drawable.buttomshapeup));
//                lens_order.setTextColor(Color.rgb( 69,159 ,219));
//
//                LensOrderingFragment fragment= new LensOrderingFragment();
//                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragmentContainer, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
//                transaction.addToBackStack(null);  // this will manage backstack
//                transaction.commit();
//            }
//        });

        mSearchOrderEdit = (EditText) view.findViewById(R.id.editsearchorder);
        mSearchFilterOrder = (ImageView) view.findViewById(R.id.searchorderfilter);

        mSearchFilterOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mSearchOrderEdit.getText().toString().trim().length() == 0) {
                    Toast.makeText(getActivity(), "Enter the search field", Toast.LENGTH_SHORT);
                } else {

                    GetFiltereordersList orderApi = new GetFiltereordersList();
                    orderApi.execute();
                }
            }
        });

        return view;
    }


    public class SentOrderApi extends AsyncTask<String, Void, String> {
        String server_response = "";
        ArrayList<String> prgmName;
        Dialog dialog;


        @Override
        protected void onPreExecute() {
            progressBar = new ProgressDialog(getContext(),R.style.MyAlertDialogStyle);
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
            String postUrl = "http://103.21.59.241/carl_visustore/master_api/get_success_orders?admin_id=" + id1;
            // String postUrl= "http://103.21.59.241/carl_visustore/master_api/get_success_orders?admin_id="+id1;
            System.out.println("url " + postUrl);
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
                    System.out.println("respon of image" + server_response);
                } else {
                    server_response = "";
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return server_response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();

            order_reference = new ArrayList<>();
            order_id = new ArrayList<>();
            order_date = new ArrayList<>();
            lenstype = new ArrayList<>();
            status = new ArrayList<>();
            employee = new ArrayList<>();
            OrderType = new ArrayList<>();
            AddedDate = new ArrayList<>();

            String image;
            Log.e("Response", "" + server_response);
            System.out.println("Server response : " + server_response);
            try {
                JSONObject jsonObj = new JSONObject(server_response);

                // Getting JSON Array node
                JSONArray gallery = jsonObj.getJSONArray("data");

                if (gallery.length() == 0) {
                    txt_content.setVisibility(View.VISIBLE);
                } else {
                    for (int j = 0; j < gallery.length(); j++) {
                        JSONObject jobj = gallery.getJSONObject(j);
                        String OrdRef = jobj.getString("order_reference");
                        String OrderId = jobj.getString("order_id");
                        String OrderDat = jobj.getString("order_date");
                        String LensTy = jobj.getString("lenstype");
                        String Stat = jobj.getString("status");
                        String emp = jobj.getString("employee");
                        String OrderTyp = jobj.getString("order_type");
                        String adddate = jobj.getString("added_date");

                        // Date date=(Date)format.parse(OrderDat);
//                    long dateFormet = Long.parseLong(OrderDat);
//                    Date date = new Date(dateFormet * 1000);
//                    @SuppressLint("SimpleDateFormat")
//                    SimpleDateFormat format1 = new SimpleDateFormat(String.valueOf(format), Locale.getDefault());
                        AddedDate.add(adddate);
                        order_reference.add(OrdRef);
                        order_id.add(OrderId);
                        order_date.add(adddate);
                        // lenstype.add(LensTy);
                        status.add(Stat);
//                        dialog.dismiss();

                    }
                }

                listView.setAdapter(new ListAdapter(SearchOrdersFragment.this, order_reference, order_id, order_date, lenstype, status, employee, OrderType, AddedDate));
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
    }


    public class GetFiltereordersList extends AsyncTask<String, Void, String> {
        String server_response = "";
        ArrayList<String> prgmName;
        Dialog dialog;

        @Override
        protected void onPreExecute() {
            progressBar = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
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
            String postUrl = "http://103.21.59.241/carl_visustore/master_api/get_success_orders?admin_id="+id1+"&t="+mSearchOrderEdit.getText().toString().trim();
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

            order_reference = new ArrayList<>();
            order_id = new ArrayList<>();
            order_date = new ArrayList<>();
            lenstype = new ArrayList<>();
            status = new ArrayList<>();
            employee = new ArrayList<>();
            OrderType = new ArrayList<>();
            AddedDate = new ArrayList<>();

            String image;
            Log.e("Response", "" + server_response);
            System.out.println("Server response : " + server_response);
            try {
                JSONObject jsonObj = new JSONObject(server_response);

                // Getting JSON Array node
                JSONArray gallery = jsonObj.getJSONArray("data");

                if (gallery.length() == 0) {
                    txt_content.setVisibility(View.VISIBLE);
                } else {
                    for (int j = 0; j < gallery.length(); j++) {
                        JSONObject jobj = gallery.getJSONObject(j);
                        String OrdRef = jobj.getString("order_reference");
                        String OrderId = jobj.getString("order_id");
                        String OrderDat = jobj.getString("order_date");
                        String LensTy = jobj.getString("lenstype");
                        String Stat = jobj.getString("status");
                        String emp = jobj.getString("employee");
                        String OrderTyp = jobj.getString("order_type");
                        String adddate = jobj.getString("added_date");

                        // Date date=(Date)format.parse(OrderDat);
//                    long dateFormet = Long.parseLong(OrderDat);
//                    Date date = new Date(dateFormet * 1000);
//                    @SuppressLint("SimpleDateFormat")
//                    SimpleDateFormat format1 = new SimpleDateFormat(String.valueOf(format), Locale.getDefault());
                        AddedDate.add(adddate);
                        order_reference.add(OrdRef);
                        order_id.add(OrderId);
                        order_date.add(adddate);
                        // lenstype.add(LensTy);
                        status.add(Stat);
//                        dialog.dismiss();
                    }
                }

                listView.setAdapter(new ListAdapter(SearchOrdersFragment.this, order_reference, order_id, order_date, lenstype, status, employee, OrderType, AddedDate));
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
    }
}
package com.example.bcs.visualstore.View.Fragment;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcs.visualstore.PojoDatas.EmployeeDatas;
import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.example.bcs.visualstore.PojoDatas.Data;
import com.example.bcs.visualstore.PojoDatas.DataModel;
import com.example.bcs.visualstore.View.Adapter.EmployeeAdapter;
import com.example.bcs.visualstore.PojoDatas.EmployeeData;
import com.example.bcs.visualstore.View.Adapter.EmployeeGetAdapter;
import com.example.bcs.visualstore.MyDeserializer;
import com.example.bcs.visualstore.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeManagementFragment extends DialogFragment {

    Button addEmp,addBtn,cancelBtn;
    DataModel dataModel,dataModelone;
    APIService apiService,apiServiceEmpName,apiCotingService;
    String auth_id,empName;
    ListView listView;
    ProgressDialog progressBar;
    List<DataModel> dataModels,number;
    View view;
    EditText id1,search;
    EmployeeAdapter customAdapter;
    EmployeeGetAdapter adapter;
    String num;
    List<DataModel>list;
    ArrayList<EmployeeDatas.Data> data;
    EmployeeData.ProfileData data1;
    int i;
    TextView name;
    ImageView toolimg;

    public EmployeeManagementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View) inflater.inflate(R.layout.fragment_employee_management, container, false);

        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        toolimg=(ImageView) toolbar.findViewById(R.id.menuLogo);
//        tooltxt.setTextSize(15);
        tooltxt.setText("EMPLOYEE MANAGEMENT");
        toolimg.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));

        auth_id=getArguments().getString("auth_id");
        Log.e("Auth_id",auth_id);
        apiService=ApiUtils.getEmployeeService(auth_id);
        apiServiceEmpName=ApiUtils.getEmployeeNameService();



        listView = (ListView) view.findViewById(R.id.list_view);
        //  search=(EditText)view.findViewById(R.id.search);



        dataModels=new ArrayList<>();
//        dataModels.add(new DataModel("aaaaaaa"));
//        dataModels.add(new DataModel("bbbbbbb"));
//        dataModels.add(new DataModel("ccccccc"));


        listView.setAdapter(customAdapter);
        addEmp=view.findViewById(R.id.addEmployee);
        init();
        listener();

        return view;

    }

    public void init() {

        progressBar = new ProgressDialog(getContext(),R.style.MyAlertDialogStyle);

        progressBar.setMessage("Loading...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.setCancelable(true);
        progressBar.setCanceledOnTouchOutside(true);
//        progressBar.show();
        dataModelone=new DataModel();


        apiServiceEmpName.employeegetData().enqueue(new Callback<EmployeeDatas>() {
            @Override
            public void onResponse(@NonNull Call<EmployeeDatas> call, @NonNull Response<EmployeeDatas> response) {
//                data=response.body().getData();
                // System.out.println(data);

                if (response.code()==200){
                    progressBar.dismiss();
                    System.out.println("success");
                    data=response.body().getData();
                    list=new ArrayList<DataModel>();

                    for ( i=0;i<data.size();i++){
                        System.out.println("name"+data.get(i).getName());
                        list.add(new DataModel(data.get(i).getName(),data.get(i).getId()));
                    }


                    adapter=new EmployeeGetAdapter(getActivity(),list,auth_id,EmployeeManagementFragment.this);
                    // customAdapter.add(dataModel);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            adapter.notifyDataSetChanged();
                        }
                    });


                }else {
                    System.out.println(response.code());
                    progressBar.dismiss();
                }
            }

            @Override
            public void onFailure(@NonNull Call<EmployeeDatas> call, @NonNull Throwable t) {
                progressBar.dismiss();

            }
        });

    }

    private void listener() {
        addEmp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.addemployee,null);
                addBtn=(Button)dialogView.findViewById(R.id.save);
                cancelBtn=(Button)dialogView.findViewById(R.id.cancel);

                id1=(EditText)dialogView.findViewById(R.id.name);

                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;

                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(dialogView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                cancelBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        cancelBtn.setBackground(getResources().getDrawable(R.drawable.buttomshapeup));
                        cancelBtn.setTextColor(Color.rgb( 69,159 ,219));
                        addEmp.setBackground(getResources().getDrawable(R.drawable.buttonshape));
                        addEmp.setTextColor(getResources().getColor(R.color.button_text));

                        popupWindow.dismiss();
                    }
                });


                addBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        addBtn.setBackground(getResources().getDrawable(R.drawable.buttomshapeup));
                        addBtn.setTextColor(Color.rgb( 69,159 ,219));


                        addEmp.setBackground(getResources().getDrawable(R.drawable.buttonshape));
                        addEmp.setTextColor(getResources().getColor(R.color.button_text));


                        empName=id1.getText().toString().trim();
                        System.out.println("");
                        if (!empName.isEmpty()){
                            addEmp.setBackground(getResources().getDrawable(R.drawable.buttonshape));
                            addEmp.setTextColor(getResources().getColor(R.color.button_text));
                            addBtn.setBackground(getResources().getDrawable(R.drawable.buttomshapeup));
                            addBtn.setTextColor(Color.rgb( 69,159 ,219));
                            dataModel=new DataModel(empName);

                            progressBar = new ProgressDialog(v.getContext(),R.style.MyAlertDialogStyle);
                            progressBar.setMessage("Loading...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.setCancelable(true);
                            progressBar.setCanceledOnTouchOutside(true);
                            progressBar.show();

                            if (auth_id!=null){
                                apiService.addEmployee(dataModel).enqueue(new Callback<JsonElement>() {
                                    @Override
                                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                        Gson gson =
                                                new GsonBuilder()
                                                        .registerTypeAdapter(Data.class, new MyDeserializer())
                                                        .create();
                                        if (response.code()==200){
                                            progressBar.dismiss();
                                            System.out.println(response.body());
                                            Data c = gson.fromJson(new Gson().toJson(response.body()), Data.class);
                                            System.out.println("ID"+c.name);
                                            init();
                                            adapter.notifyDataSetChanged();


                                        }else {
                                            Toast.makeText(getActivity(),"Already Employee name Exists",Toast.LENGTH_LONG).show();
                                            System.out.println(response.code());
                                            progressBar.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<JsonElement> call, Throwable t) {

                                        System.out.println(t.getMessage());
                                        progressBar.dismiss();

                                    }
                                });

                            }else {
                                Toast.makeText(getActivity(),"login properly",Toast.LENGTH_LONG).show();
                            }


                        }
                        popupWindow.dismiss();

                    }
                });

            }
        });
    }
}
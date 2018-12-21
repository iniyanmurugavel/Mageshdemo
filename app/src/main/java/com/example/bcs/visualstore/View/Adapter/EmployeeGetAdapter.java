package com.example.bcs.visualstore.View.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Selection;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.bcs.visualstore.PojoDatas.Data;
import com.example.bcs.visualstore.PojoDatas.DataModel;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.example.bcs.visualstore.View.Fragment.EmployeeManagementFragment;
import com.google.gson.JsonElement;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeGetAdapter extends BaseAdapter implements View.OnClickListener {
    private LayoutInflater mInflater;
    private Context context;
    private List<DataModel> items;
    APIService apiService,updateApiService;
    String authId,empName;
    ListView listView;
    String num,num2;
    Data data;
    DataModel dataModel;
    Button addEmp,addBtn,cancelBtn;
    EditText id1;
    ProgressDialog progressBar;
    TextView name;

    EmployeeManagementFragment fragment;
    public EmployeeGetAdapter(Context context, List<DataModel> items, String authId, EmployeeManagementFragment fragment){
        this.context = context;
        this.items = items;
        this.authId=authId;
        this.fragment=fragment;
        Log.e("authi",this.authId);

    }

    public EmployeeGetAdapter(Context context,List<DataModel>items){
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        apiService= ApiUtils.getEmployeeDeleteService(authId);
        updateApiService=ApiUtils.getUpdateService(authId);

        if (convertView==null){
            convertView = mInflater.inflate(R.layout.employee_items, null);
        }



         dataModel= (DataModel) getItem(position);

         name=convertView.findViewById(R.id.name);
        ImageView edit = (ImageView) convertView.findViewById(R.id.edit);
        ImageView delete = (ImageView) convertView.findViewById(R.id.bin);

        name.setText(dataModel.getName());

        edit.setImageResource(R.drawable.svg_edit);
        delete.setImageResource(R.drawable.svg_delete);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("cliked","edit");
                LayoutInflater mInflater = (LayoutInflater) context
                        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                View dialogView = mInflater.inflate(R.layout.addemployee,null);
                addBtn=(Button)dialogView.findViewById(R.id.save);
                cancelBtn=(Button)dialogView.findViewById(R.id.cancel);



                id1=(EditText)dialogView.findViewById(R.id.name);
                id1.setText(items.get(position).getName());
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                Editable etext = id1.getText();
                Selection.setSelection(etext,id1.getText().toString().length());

                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(dialogView, width, height, focusable);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                cancelBtn.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        //cancelBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttomshapeup));
                        cancelBtn.setTextColor(Color.rgb( 69,159 ,219));
                        popupWindow.dismiss();
                    }
                });

                addBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //addBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttomshapeup));
                        addBtn.setTextColor(Color.rgb( 69,159 ,219));

                        num2=items.get(position).getId();
                        empName=id1.getText().toString().trim();
                        System.out.println("id"+num2);
                        if (!empName.isEmpty()){
                            data=new Data(num2,empName);
                            System.out.println("name"+empName);

                            progressBar = new ProgressDialog(v.getContext());
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Loading...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();
                            apiService.update(data).enqueue(new Callback<JsonElement>() {
                                @Override
                                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                    if (response.code()==200){
                                        System.out.println(response.body());
                                        System.out.println("success");
                                        fragment.init();
                                        progressBar.dismiss();
                                        notifyDataSetChanged();
                                    }else {
                                        System.out.println(response.code());
                                        progressBar.dismiss();
                                    }
                                }

                                @Override
                                public void onFailure(Call<JsonElement> call, Throwable t) {

                                }
                            });

                        }
                        popupWindow.dismiss();

                    }
                });

            }
        });





        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=items.get(position).getName();
                num=items.get(position).getId();
                System.out.println("name"+name);
                System.out.println("id"+num);
                data=new Data(num);


                AlertDialog.Builder alertDialog=new AlertDialog.Builder(context,R.style.AlertDialogDanger);

                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Do you like to delete");
                alertDialog.setPositiveButton("YES",null);
                alertDialog.setNegativeButton("NO",null);
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        apiService.deleteEmployee(data).enqueue(new Callback<Data>() {
                            @Override
                            public void onResponse(Call<Data> call, Response<Data> response) {
                                if (response.code()==200){
                                    System.out.println(response.body());
                                    System.out.println("success");
                                    items.remove(position);
                                    notifyDataSetChanged();
                                    // progressBar.dismiss();
                                }else {
                                    System.out.println(response.code());
                                    // progressBar.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Data> call, Throwable t) {
                            }
                        });

                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                alertDialog.show();


            }
        });

        return convertView;
    }

    @Override
    public void onClick(View v) {

    }


    public void alertDialog(String title){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context,R.style.AlertDialogDanger);

        alertDialog.setTitle("Do you like to delete");
        alertDialog.setPositiveButton("YES",null);
        alertDialog.setNegativeButton("NO",null);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        alertDialog.show();

    }


}

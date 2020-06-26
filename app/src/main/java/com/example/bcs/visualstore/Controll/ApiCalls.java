package com.example.bcs.visualstore.Controll;

import android.content.Context;
import androidx.annotation.NonNull;

import com.example.bcs.visualstore.PojoDatas.CallBackData;

import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.google.gson.JsonElement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalls {

    Context context;
    String auth_id;

    APIService apiService= ApiUtils.getLensTypeCodeService();
    APIService apiEmployeeService=ApiUtils.getEmployeeNameService();
    APIService apiCoatingService=ApiUtils.getCoteService();
    APIService apiTintService=ApiUtils.getTintList();

    static APIService apiCoatingServiceTwo=ApiUtils.getCoteService();

    static APIService apiService1= ApiUtils.getLensTypeCodeService();
    APIService profileService=ApiUtils.getMyprofile(auth_id);
    String name;

    public void getZeiiiss(final CallBack<List<CallBackData>> callBack){

        final List<CallBackData.Data> list=new ArrayList<CallBackData.Data>();

        Map<String,Double> addAddition=new HashMap<>();
        Map<String,Integer> addLensCode=new HashMap<>();

        addAddition.put("addition",1.00);
        addLensCode.put("lens_type",1);


        apiService1.lensCodeLists(addAddition,addLensCode).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {

                if (response.code()==200){
                    String data=response.body().toString();
                    System.out.println("return Zeiss Zero"+data);
                    try {

                        JSONObject object=new JSONObject(data);
                        JSONArray jsonArray=object.getJSONArray("data");

                        for (int j=0;j<jsonArray.length();j++) {
                            JSONObject object1 = jsonArray.getJSONObject(j);

                            String lens_code = object1.getString("lens_code");
                             name = object1.getString("name");
                            String tint = object1.getString("tint");
                            String individual=object1.getString("individual");

                            list.add(new CallBackData.Data(lens_code,name,tint,individual));

                        }

                        System.out.println("JsonList "+list.size());
                        callBack.next(list);

                    }catch (Exception e){
                        System.out.println(e);
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {

            }
        });

    }

public void getDataSet(final CallBack<List<CallBackData>> callback){

        final List<CallBackData.Data> list=new ArrayList<CallBackData.Data>();

    Map<String,Double> addAddition=new HashMap<>();
    Map<String,Integer> addLensCode=new HashMap<>();

    addAddition.put("addition",0.00);
    addLensCode.put("lens_type",1);

    apiService.returnList(addAddition,addLensCode).enqueue(new Callback<CallBackData>() {

        @Override
        public void onResponse(@NonNull Call<CallBackData> call, @NonNull Response<CallBackData> response) {
            List<CallBackData.Data> data;
            if (response.body().getData() != null) {
                data = response.body().getData();
                System.out.println("ddata is " + data);


                for (int i = 0; i < data.size(); i++) {
                    list.add(new CallBackData.Data(data.get(i).getLens_code(), data.get(i).getName(), data.get(i).getTint(),data.get(i).getIndividual()));
                }
                System.out.println("list is " + list.size());
                callback.next(list);
            }
        }


        @Override
        public void onFailure(@NonNull Call<CallBackData> call, @NonNull Throwable t) {

        }
    });

}

public void getSyncronyDefault(final CallBack<List<CallBackData>> callBack){

    final List<CallBackData.Data> list=new ArrayList<CallBackData.Data>();

    Map<String,Double> addAddition=new HashMap<>();
    Map<String,Integer> addLensCode=new HashMap<>();

    addAddition.put("addition",0.00);
    addLensCode.put("lens_type",2);

    apiService.returnList(addAddition,addLensCode).enqueue(new Callback<CallBackData>() {

        List<CallBackData.Data> data;
        @Override
        public void onResponse(@NonNull Call<CallBackData> call, @NonNull Response<CallBackData> response) {

            if (response.body().getData() != null) {
                data = response.body().getData();
                System.out.println("respose from synchroniZeroes" + data);
                for (int i = 0; i < data.size(); i++) {
                    list.add(new CallBackData.Data(data.get(i).getLens_code(), data.get(i).getName(), data.get(i).getTint(),data.get(i).getIndividual()));
                }
                System.out.println("syncroni default size" + list.size());
                callBack.next(list);
            }
        }

        @Override
        public void onFailure(@NonNull Call<CallBackData> call, @NonNull Throwable t) {

        }
    });
}


public void getSyncroniOnce(final CallBack<List<CallBackData>> callBack){
    final List<CallBackData.Data> list=new ArrayList<CallBackData.Data>();

    Map<String,Double> addAddition=new HashMap<>();
    Map<String,Integer> addLensCode=new HashMap<>();

    addAddition.put("addition",1.00);
    addLensCode.put("lens_type",2);

    apiService.returnList(addAddition,addLensCode).enqueue(new Callback<CallBackData>() {

        List<CallBackData.Data> data;
        @Override
        public void onResponse(@NonNull Call<CallBackData> call, @NonNull Response<CallBackData> response) {

            if (response.body().getData() != null) {
                data = response.body().getData();
                System.out.println("respose from synchroniZeroes" + data);
                try {
                    for (int i = 0; i < data.size(); i++) {
                        list.add(new CallBackData.Data(data.get(i).getLens_code(), data.get(i).getName(), data.get(i).getTint(),data.get(i).getIndividual()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }

                System.out.println("syncroni default size" + list.size());
                callBack.next(list);
            }
        }

        @Override
        public void onFailure(@NonNull Call<CallBackData> call, @NonNull Throwable t) {

        }
    });

}

public void getEmployeeName(final CallBack<List<CallBackData>> callBack) {

    final List<CallBackData.Data>employeeDataList=new ArrayList<CallBackData.Data>();

    apiEmployeeService.employeeName().enqueue(new Callback<CallBackData>() {

        List<CallBackData.Data> data;
        @Override
        public void onResponse(@NonNull Call<CallBackData> call, @NonNull Response<CallBackData> response) {

            if (response.body().getData() != null) {
                data = response.body().getData();
                System.out.println("respose from synchroniZeroes" + data);
                try {
                    for (int i = 0; i < data.size(); i++) {
                        employeeDataList.add(new CallBackData.Data(data.get(i).getName()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }

                System.out.println("employee size" + employeeDataList.size());
                callBack.next(employeeDataList);

            }
        }

        @Override
        public void onFailure(@NonNull Call<CallBackData> call, @NonNull Throwable t) {


        }
    });

}

public void getTintDatas(final CallBack<List<CallBackData>> callBack){

    final List<CallBackData.Data>tintCodesList=new ArrayList<CallBackData.Data>();

       apiTintService.tintList().enqueue(new Callback<CallBackData>() {
           List<CallBackData.Data> data;
           @Override
           public void onResponse(@NonNull Call<CallBackData> call, @NonNull Response<CallBackData> response) {

               if (response.body().getData() != null) {
                   data = response.body().getData();
                   System.out.println("respose from tint code" + data);
                   try {
                       for (int i = 0; i < data.size(); i++) {
                           tintCodesList.add(new CallBackData.Data(data.get(i).getCode(), data.get(i).getName()));
                       }
                   } catch (Exception e) {
                       e.printStackTrace();

                   }
                   System.out.println("tint  size" + tintCodesList.size());
                   callBack.next(tintCodesList);
               }
           }



           @Override
           public void onFailure(@NonNull Call<CallBackData> call, @NonNull Throwable t) {

           }
       });
    }

}

package com.example.bcs.visualstore.Utils;

import com.example.bcs.visualstore.PojoDatas.CallBackData;
import com.example.bcs.visualstore.PojoDatas.Data;
import com.example.bcs.visualstore.PojoDatas.DataModel;
import com.example.bcs.visualstore.PojoDatas.DeleteOrder;
import com.example.bcs.visualstore.PojoDatas.EmployeeDatas;
import com.example.bcs.visualstore.PojoDatas.LogoutData;
import com.example.bcs.visualstore.PojoDatas.OrderPlace;
import com.example.bcs.visualstore.PojoDatas.Pojo;

import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import retrofit2.http.PUT;

public interface APIService {

    @POST("master_api/login_app")
    Call<JsonElement> loginPost(@Body Pojo login);


    @GET("master_api/my_profile")
    Call<JsonElement> myprofile();

    @GET("master_api/lencode_list2")
    Call<JsonElement> lenscodelist(@QueryMap Map<String, Double> params, Map<String, Integer> lentype);

    @POST("master_api/logout_app")
    Call<LogoutData> logout(@Body LogoutData logoutData);


    @GET("master_api/coatingcode_list2")
    Call<CallBackData> coatinglist(@QueryMap Map<String,String> params);



    @POST("master_api/add_employee")
    Call<JsonElement> addEmployee(@Body DataModel login);

    @GET("master_api/get_employee")
    Call<EmployeeDatas> employeegetData();




    @GET("master_api/get_employee")
    Call<CallBackData> employeeName();




//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "master_api/delete_employee", hasBody = true)
    @HTTP(method = "DELETE", path = "master_api/delete_employee", hasBody = true)
    Call<Data> deleteEmployee(@Body Data id);


    @PUT("master_api/update_employee")
    Call<JsonElement> update(@Body Data data);



    @GET("master_api/lencode_list3")
    Call<JsonElement> lensCodeLists(@QueryMap Map<String,Double> params, @QueryMap Map<String,Integer> lensType);

    @GET("master_api/lencode_list3")
    Call<ArrayList<EmployeeDatas.Data>> lensCodeLists1(@QueryMap Map<String,Double> params, @QueryMap Map<String,Integer> lensType);


    @GET("master_api/tintcode_list_lookup")
    Call<CallBackData> tintList();


    @GET("master_api/lencode_list3")
    Call<CallBackData> returnList(@QueryMap Map<String,Double> params, @QueryMap Map<String,Integer> lensType);

//    @POST("master_api/place_order")
//    Call<JsonElement> placeOrder(@Body String data);

    @HTTP(method = "DELETE", path = "master_api/delete_order", hasBody = true)
    Call<JsonElement> deleteOrder(@Body DeleteOrder deleteOrder);

}

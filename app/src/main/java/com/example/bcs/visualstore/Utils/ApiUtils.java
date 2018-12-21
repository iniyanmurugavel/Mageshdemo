package com.example.bcs.visualstore.Utils;

import com.example.bcs.visualstore.Controll.RetrofitClient;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://103.21.59.241/carl_visustore/";

    private static String SUB_URL=BASE_URL+"master_api/";

    /*for stroring id*/
    public static int INCOMPLETE=0;


    public static APIService getLoginService(){

        return RetrofitClient.getLoginClient(BASE_URL).create(APIService.class);

    }
    public static APIService getLogoutService(String authId){

        return RetrofitClient.getLogoutClient(BASE_URL,authId).create(APIService.class);
    }

    public static APIService getMyprofile(String authId){

        return RetrofitClient.getMyprofile(BASE_URL,authId).create(APIService.class);
    }
    public static APIService getLensCodeList(){

        return RetrofitClient.getLensCodeList(BASE_URL).create(APIService.class);

    }



    public static APIService getEmployeeDeleteService(String authId){

        return  RetrofitClient.deleteEmployee(BASE_URL,authId).create(APIService.class);
    }

    public static APIService getUpdateService(String authId){
        return RetrofitClient.getUpdateClient(BASE_URL,authId).create(APIService.class);
    }

    public static APIService getTintList(){

        return RetrofitClient.getTintList(BASE_URL).create(APIService.class);

    }
    public static APIService getEmployeeNameService(){

        return  RetrofitClient.getEmployeeNameClient(BASE_URL).create(APIService.class);
    }
    public static APIService getEmployeeService(String authId){

        return RetrofitClient.getEmployeeClient(BASE_URL,authId).create(APIService.class);
    }

    public static APIService getCoteService(){
        return RetrofitClient.getCotingData(BASE_URL).create(APIService.class);
    }


    public static APIService getLensDetails(){
        return RetrofitClient.getLensTypeCode(BASE_URL).create(APIService.class);
    }


    public static APIService getLensTypeCodeService(){
        return RetrofitClient.getLensTypeCodes(BASE_URL).create(APIService.class);
    }


    public static APIService getPlaceorderService(){
        return RetrofitClient.getPlaceOrder(BASE_URL).create(APIService.class);
    }
}
package com.example.bcs.visualstore.Controll;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bcs.visualstore.PojoDatas.Data;
import com.example.bcs.visualstore.MyDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    public static Retrofit getLogoutClient(String url, final String authId) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                Log.e("auth_id", authId);



                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }





    public static Retrofit getLoginClient(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);


                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();

        Retrofit retrofit;
        Gson gson =
                new GsonBuilder()
                        .setLenient()
                        .registerTypeAdapter(Data.class, new MyDeserializer())
                        .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit;
    }
    public static Retrofit getMyprofile(String url,final  String authId) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
              //  Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
    public static Retrofit getLensCodeList(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
              //  Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
//                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static Retrofit getTintList(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                //Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
//                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static Retrofit getEmployeeClient(String url, final String authId) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                System.out.println("auth_id"+authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static Retrofit getEmployeeNameClient(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
               // System.out.println("auth_id"+authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                      //  .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }


    public static Retrofit deleteEmployee(String url, final String authId) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }


    public static Retrofit getUpdateClient(String url, final String authId) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "fundmitra-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }



    public static Retrofit getCotingData(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
             //   Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                      //  .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }



    public static Retrofit getLensTypeCode(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                //   Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
                        //  .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }



    public static Retrofit getLensTypeCodes(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                //  Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
//                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }




    public static Retrofit getPlaceOrder(String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Response response = chain.proceed(original);
                //  Log.e("auth_id", authId);

                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Admin-Service", "visustore-RESTApi")
                        .header("Auth-Key", "BwebRestAPI")
//                        .header("Auth-Id", authId)
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });


        //   MyProfileData c = gson.fromJson(myDeserializer, MyProfileData.class);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }





}




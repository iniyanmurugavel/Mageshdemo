package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CallBackData implements Serializable{
    @SerializedName("data")
    private ArrayList<Data> data;


    public CallBackData(){
        this.data=new ArrayList<>();
    }

    public  ArrayList<Data> getData(){
        return data;
    }
    public CallBackData(ArrayList<Data> data){
        this.data=data;
    }

    public static class Data {

        @SerializedName("lens_code")
        public String lens_code;
        @SerializedName("name")
        public String name;
        @SerializedName("tint")
        public String tint;

        @SerializedName("type")
        public String type;


        @SerializedName("code")
        String code;

        @SerializedName("display_name")
        String display_name;

        @SerializedName("id")
        String id;
        @SerializedName("status")
        String status;

        public String getIndividual() {
            return individual;
        }

        public void setIndividual(String individual) {
            this.individual = individual;
        }

        @SerializedName("individual")
        String individual;





        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }







        public String getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setStatus(String status) {
            this.status = status;
        }


        public Data(String name){
            this.name=name;
        }


        public String getLens_code() {
            return lens_code;
        }

        public String getName() {
            return name;
        }

        public String getTint() {
            return tint;
        }

        public void setLens_code(String lens_code) {
            this.lens_code = lens_code;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setTint(String tint) {
            this.tint = tint;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDisplay_name() {
            return display_name;
        }
        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public Data(String lens_code, String name, String tint,String individual) {
            this.lens_code = lens_code;
            this.name = name;
            this.tint = tint;
            this.individual=individual;
        }

        public Data(String code,String name){
            this.code=code;
            this.name=name;
        }
        public Data( String code, String name, String type) {
           // this.id = id;
            this.code = code;
            this.name = name;
            this.type = type;
        }

        public Data() {
        }
    }
}

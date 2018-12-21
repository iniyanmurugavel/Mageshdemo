package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeDatas implements Serializable {


    @SerializedName("data")
    private ArrayList<Data> data;


    public EmployeeDatas(){
        this.data=new ArrayList<>();
    }

    public  ArrayList<Data> getData(){
        return data;
    }
    public EmployeeDatas(ArrayList<Data> data){
        this.data=data;
    }

    public class Data {

        public Data(String id, String name, String status) {
            this.id = id;
            this.name = name;
            this.status = status;
        }
        public Data(String name){
            this.name=name;
        }

        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("status")
        public String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

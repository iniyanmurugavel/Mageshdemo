package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmployeeData implements Serializable {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("status")
    String status;

    public EmployeeData() {

    }



    public EmployeeData(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }


    public class ProfileData {
    }
}

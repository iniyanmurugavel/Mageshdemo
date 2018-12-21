package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    public Data(String id) {
        this.id = id;
    }

    public Data(String id,String name) {
        this.id = id;
        this.name=name;
    }

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("auth_id")
    @Expose
    public String auth_id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("status")
    @Expose
    public String status;




    // Getter Methods




}


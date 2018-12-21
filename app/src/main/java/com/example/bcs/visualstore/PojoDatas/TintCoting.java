package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TintCoting implements Serializable {

    @SerializedName("id")
    String id;
    @SerializedName("code")
    String code;
    @SerializedName("name")
    String name;

    public TintCoting(){

    }

    public TintCoting(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }


}

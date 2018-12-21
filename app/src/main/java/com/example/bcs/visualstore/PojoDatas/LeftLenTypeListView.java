package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LeftLenTypeListView implements Serializable {

    @SerializedName("lens_code")
    String lens_code;
    @SerializedName("name")
    String name;
    @SerializedName("type")
    String type;

    public LeftLenTypeListView() {

    }

    public LeftLenTypeListView(String lens_code, String name, String type) {
        this.lens_code = lens_code;
        this.name = name;
        this.type = type;
    }
    public LeftLenTypeListView(String name){
        this.name=name;
    }

    public String getLens_code() {
        return lens_code;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setLens_code(String lens_code) {
        this.lens_code = lens_code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}

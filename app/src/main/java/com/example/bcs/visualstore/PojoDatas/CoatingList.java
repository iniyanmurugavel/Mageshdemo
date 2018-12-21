package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CoatingList implements Serializable {


    @SerializedName("id")
    private String id;
    @SerializedName("code")
    private String code;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;

    public CoatingList(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }



    public CoatingList(){

    }

    public CoatingList(String id, String code, String name, String type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
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

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
    }


}

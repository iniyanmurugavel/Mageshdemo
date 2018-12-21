package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RightLenTypeListView implements Serializable {

    @SerializedName("lens_code")
    String lens_code;
    @SerializedName("name")
    String name;
    @SerializedName("type")
    String type;

    @SerializedName("id")
    String id;
    @SerializedName("status")
    String status;

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



    public RightLenTypeListView() {


}

    public RightLenTypeListView(String lens_code, String name, String type) {
        this.lens_code = lens_code;
        this.name = name;
        this.type = type;
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

    public RightLenTypeListView(String name){
        this.name=name;
    }

    public String serialize(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }

    static public RightLenTypeListView create(String serializedData){
        Gson gson=new Gson();
        return gson.fromJson(serializedData,RightLenTypeListView.class);
    }

}

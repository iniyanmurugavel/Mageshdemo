package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShapeAndBevelData implements Serializable {



    @SerializedName("frame")
     String frame;
    @SerializedName("id")
     String id;
    @SerializedName("selectedRadioButton")
    String selectedRadioButton;

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSelectedRadioButton() {
        return selectedRadioButton;
    }

    public void setSelectedRadioButton(String selectedRadioButton) {
        this.selectedRadioButton = selectedRadioButton;
    }

    public String serialize(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }


    public ShapeAndBevelData(String frame, String id, String selectedRadioButton ) {
        this.frame = frame;
        this.id = id;
        this.selectedRadioButton=selectedRadioButton;
    }

    static public ShapeAndBevelData create(String serializedData){
        Gson gson=new Gson();
        return gson.fromJson(serializedData,ShapeAndBevelData.class);
    }

    public ShapeAndBevelData(){

    }


}

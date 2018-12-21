package com.example.bcs.visualstore.PojoDatas;

import android.widget.ImageView;

public class Employee_Pojo {


    public Employee_Pojo(String name) {
        this.name = name;
    }

    private String name;
    Integer edit;
    Integer bin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Employee_Pojo(Integer edit, Integer bin,String name) {
        this.edit = edit;
        this.bin = bin;
    }

    public Integer getEdit() {

        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }

    public Integer getBin() {
        return bin;
    }

    public void setBin(Integer bin) {
        this.bin = bin;
    }
}

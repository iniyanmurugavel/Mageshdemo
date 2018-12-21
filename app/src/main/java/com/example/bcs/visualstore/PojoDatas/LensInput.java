package com.example.bcs.visualstore.PojoDatas;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LensInput implements Serializable {



    @SerializedName("OrderRef")
    static String OrderRef;
    @SerializedName("conFstNme")
    static String conFstNme;
    @SerializedName("conLstNme")
    static String conLstNme;
    @SerializedName("consignee")
    static String consignee;
    @SerializedName("employee")
    static String employee;
    @SerializedName("rightSphere")
    static String rightSphere;
    @SerializedName("rightCylind")
    static String rightCylind;
    @SerializedName("rightAxis")
    static String rightAxis;
    @SerializedName("rightAddition")
    static String rightAddition;
    @SerializedName("leftSphere")
    static String leftSphere;
    @SerializedName("leftCylind")
    static String leftCylind;
    @SerializedName("leftAxis")
    static String leftAxis;
    @SerializedName("leftAddition")
    static String leftAddition;
    @SerializedName("portofolio")
    static String portofolio;
    @SerializedName("lensTypeName")
    static String lensTypeName;
    @SerializedName("lensTypeCode")
    static String lensTypeCode;
    @SerializedName("rightDiameter")
    static String rightDiameter;
    @SerializedName("leftDiamter")
    static String leftDiamter;
    @SerializedName("coating")
    static String coating;
    @SerializedName("cotingCode")
    static String cotingCode;
    @SerializedName("coatingType")
    static String coatingType;
    @SerializedName("switch1")
    static String switch1;
    @SerializedName("tint")
    static String tint;
    @SerializedName("tintName")
    static String tintName;
    @SerializedName("sides")
    static String sides;


    @SerializedName("virtualSide")
    static String virtualSide;

    @SerializedName("individual")
    static String individual;

    public static String getIndividual() {
        return individual;
    }

    public static void setIndividual(String individual) {
        LensInput.individual = individual;
    }



    public  String getVirtualSide() {
        return virtualSide;
    }

    public  void setVirtualSide(String virtualSide) {
        LensInput.virtualSide = virtualSide;
    }

    public  String getHard() {
        return hard;
    }

    public  void setHard(String hard) {
        this.hard = hard;
    }

    @SerializedName("hard")

    static String hard;

    public String getCotingCode() {
        return cotingCode;
    }

    public  String getCoatingType() {
        return coatingType;
    }

    public  void setCoatingType(String coatingType) {
        LensInput.coatingType = coatingType;
    }
    public void setCotingCode(String cotingCode) {
        this.cotingCode = cotingCode;
    }




    public String getOrderRef() {
        return OrderRef;
    }

    public String getConFstNme() {
        return conFstNme;
    }

    public String getConLstNme() {
        return conLstNme;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getEmployee() {
        return employee;
    }

    public String getRightSphere() {
        return rightSphere;
    }

    public String getRightCylind() {
        return rightCylind;
    }

    public String getRightAxis() {
        return rightAxis;
    }

    public String getRightAddition() {
        return rightAddition;
    }

    public String getLeftSphere() {
        return leftSphere;
    }

    public String getLeftCylind() {
        return leftCylind;
    }

    public String getLeftAxis() {
        return leftAxis;
    }

    public String getLeftAddition() {
        return leftAddition;
    }

    public String getPortofolio() {
        return portofolio;
    }

    public String getLensTypeName() {
        return lensTypeName;
    }

    public String getLensTypeCode() {
        return lensTypeCode;
    }

    public String getRightDiameter() {
        return rightDiameter;
    }

    public String getLeftDiamter() {
        return leftDiamter;
    }

    public String getCoating() {
        return coating;
    }

    public String getTint() {
        return tint;
    }

    public void setOrderRef(String orderRef) {
        OrderRef = orderRef;
    }

    public void setConFstNme(String conFstNme) {
        this.conFstNme = conFstNme;
    }

    public void setConLstNme(String conLstNme) {
        this.conLstNme = conLstNme;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setRightSphere(String rightSphere) {
        this.rightSphere = rightSphere;
    }

    public void setRightCylind(String rightCylind) {
        this.rightCylind = rightCylind;
    }

    public void setRightAxis(String rightAxis) {
        this.rightAxis = rightAxis;
    }

    public void setRightAddition(String rightAddition) {
        this.rightAddition = rightAddition;
    }

    public void setLeftSphere(String leftSphere) {
        this.leftSphere = leftSphere;
    }

    public void setLeftCylind(String leftCylind) {
        this.leftCylind = leftCylind;
    }

    public void setLeftAxis(String leftAxis) {
        this.leftAxis = leftAxis;
    }

    public void setLeftAddition(String leftAddition) {
        this.leftAddition = leftAddition;
    }

    public void setPortofolio(String portofolio) {
        this.portofolio = portofolio;
    }

    public void setLensTypeName(String lensTypeName) {
        this.lensTypeName = lensTypeName;
    }

    public void setLensTypeCode(String lensTypeCode) {
        this.lensTypeCode = lensTypeCode;
    }

    public void setRightDiameter(String rightDiameter) {
        this.rightDiameter = rightDiameter;
    }

    public void setLeftDiamter(String leftDiamter) {
        this.leftDiamter = leftDiamter;
    }

    public void setCoating(String coating) {
        this.coating = coating;
    }

    public void setTint(String tint) {
        this.tint = tint;
    }

    public String getSides() {
        return sides;
    }

    public void setSides(String sides) {
        this.sides = sides;
    }

    public  void setTintName(String tintName) {
        LensInput.tintName = tintName;
    }


    public  String getTintName() {
        return tintName;
    }


    public  String getSwitch1() {
        return switch1;
    }

    public  void setSwitch1(String switch1) {
        LensInput.switch1 = switch1;
    }

    public LensInput() {
    }

    public LensInput(String orderRef, String conFstNme, String conLstNme, String consignee, String employee, String rightSphere, String rightCylind, String rightAxis, String rightAddition, String leftSphere, String leftCylind,String hard,String leftAxis, String leftAddition, String portofolio, String lensTypeName, String lensTypeCode, String rightDiameter, String leftDiamter, String coating,String cotingCode, String tint,String tintName,String switch1,String virtualSide) {
        OrderRef = orderRef;
        this.conFstNme = conFstNme;
        this.conLstNme = conLstNme;
        this.consignee = consignee;
        this.employee = employee;
        this.rightSphere = rightSphere;
        this.rightCylind = rightCylind;
        this.rightAxis = rightAxis;
        this.rightAddition = rightAddition;
        this.leftSphere = leftSphere;
        this.leftCylind = leftCylind;
        this.leftAxis = leftAxis;
        this.leftAddition = leftAddition;
        this.portofolio = portofolio;
        this.lensTypeName = lensTypeName;
        this.lensTypeCode = lensTypeCode;
        this.rightDiameter = rightDiameter;
        this.leftDiamter = leftDiamter;
        this.coating = coating;
        this.cotingCode=cotingCode;
        this.tint = tint;
        this.tintName=tintName;
        this.hard=hard;
        this.switch1=switch1;
        this.virtualSide=virtualSide;


    }


    public String serialize(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }

    static public LensInput create(String serializedData){
        Gson gson=new Gson();
        return gson.fromJson(serializedData,LensInput.class);
    }

}

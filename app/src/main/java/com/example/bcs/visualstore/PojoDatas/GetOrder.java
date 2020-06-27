package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetOrder implements Serializable {


    @SerializedName("order_reference")
    private static String order_reference;
    @SerializedName("patient_term")
    private static String patient_term;
    @SerializedName("patient_firstName")
    private static String patient_firstName;
    @SerializedName("patient_lastName")
    private static String patient_lastName;
    @SerializedName("side")
    private static String side;
    @SerializedName("lens_lr_lens")
    private static String lens_lr_lens;
    @SerializedName("lens_r_sphere")
    private static String lens_r_sphere;
    @SerializedName("lens_r_power")
    private static String lens_r_power;
    @SerializedName("lens_r_axis")
    private static String lens_r_axis;
    @SerializedName("lens_r_addition")
    private static String lens_r_addition;
    @SerializedName("lens_r_commercialCode")
    private static String lens_r_commercialCode;
    @SerializedName("lens_r_dia")
    private static String lens_r_dia;
    @SerializedName("centration_r_pdz")
    private static String centration_r_pdz;
    @SerializedName("centration_r_height")
    private static String centration_r_height;
    @SerializedName("centration_r_bvd")
    private static String centration_r_bvd;
    @SerializedName("geometry_r_optimavsm")
    private static String geometry_r_optimavsm;
    @SerializedName("lens_hard")
    private static String lens_hard;
    @SerializedName("coating_commercialCode")
    private static String coating_commercialCode;
    @SerializedName("coating_commercialType")
    private static String coating_commercialType;
    @SerializedName("coating_commercialName")
    private static String coating_commercialName;
    @SerializedName("coating_commercialTint")
    private static String coating_commercialTint;
    @SerializedName("coating_no_uv")
    private static String coating_no_uv;
    @SerializedName("lens_l_sphere")
    private static String lens_l_sphere;
    @SerializedName("lens_l_power")
    private static String lens_l_power;
    @SerializedName("lens_l_axis")
    private static String lens_l_axis;
    @SerializedName("lens_l_addition")
    private static String lens_l_addition;
    @SerializedName("lens_l_commercialCode")
    private static String lens_l_commercialCode;
    @SerializedName("lens_l_dia")
    private static String lens_l_dia;
    @SerializedName("centration_l_pdz")
    private static String centration_l_pdz;
    @SerializedName("centration_l_height")
    private static String centration_l_height;
    @SerializedName("centration_l_bvd")
    private static String centration_l_bvd;
    @SerializedName("geometry_l_optimavsm")
    private static String geometry_l_optimavsm;
    @SerializedName("frm_width")
    private static String frm_width;
    @SerializedName("frm_height")
    private static String frm_height;
    @SerializedName("frm_material")
    private static String frm_material;
    @SerializedName("frm_model")
    private static String frm_model;
    @SerializedName("frm_dbl")
    private static String frm_dbl;
    @SerializedName("panangle")
    private static String panangle;
    @SerializedName("bowangle")
    private static String bowangle;
    @SerializedName("lens_r_commercialName")
    private static String lens_r_commercialName;
    @SerializedName("lens_l_commercialName")
    private static String lens_l_commercialName;
    @SerializedName("coating_commercialTintName")
    private static String coating_commercialTintName;
    @SerializedName("employee_name")
    private static String employee_name;
    @SerializedName("portfolio")
    private static String portfolio;
    @SerializedName("cons")
    private static String cons;



    @SerializedName("frameNamedef")
    private static String frameNamedef;

    @SerializedName("coating_commercialCodeName")
    private static String coating_commercialCodeName;

    public  String getCoating_commercialCodeName() {
        return coating_commercialCodeName;
    }

    public  void setCoating_commercialCodeName(String coating_commercialCodeName) {
        GetOrder.coating_commercialCodeName = coating_commercialCodeName;
    }





    public  String getPortfolio() {
        return portfolio;
    }

    public  void setPortfolio(String portfolio) {
        GetOrder.portfolio = portfolio;
    }

    public  String getCons() {
        return cons;
    }

    public  void setCons(String cons) {
        GetOrder.cons = cons;
    }

    public  String getEmployee_name() {
        return employee_name;
    }

    public  void setEmployee_name(String employee_name) {
        GetOrder.employee_name = employee_name;
    }

    public  String getCoating_commercialTintName() {
        return coating_commercialTintName;
    }

    public  void setCoating_commercialTintName(String coating_commercialTintName) {
        GetOrder.coating_commercialTintName = coating_commercialTintName;
    }

    public  String getLens_r_commercialName() {
        return lens_r_commercialName;
    }

    public  void setLens_r_commercialName(String lens_r_commercialName) {
        GetOrder.lens_r_commercialName = lens_r_commercialName;
    }

    public  String getLens_l_commercialName() {
        return lens_l_commercialName;
    }

    public  void setLens_l_commercialName(String lens_l_commercialName) {
        GetOrder.lens_l_commercialName = lens_l_commercialName;
    }

    public static String getFrameNamedef() {
        return frameNamedef;
    }

    public void setFrameNamedef(String frameNamedef) {
        GetOrder.frameNamedef = frameNamedef;
    }

    public  String getOrder_reference() {
        return order_reference;
    }

    public  void setOrder_reference(String order_reference) {
        GetOrder.order_reference = order_reference;
    }

    public  String getPatient_term() {
        return patient_term;
    }

    public  void setPatient_term(String patient_term) {
        GetOrder.patient_term = patient_term;
    }

    public  String getPatient_firstName() {
        return patient_firstName;
    }

    public  void setPatient_firstName(String patient_firstName) {
        GetOrder.patient_firstName = patient_firstName;
    }

    public  String getPatient_lastName() {
        return patient_lastName;
    }

    public  void setPatient_lastName(String patient_lastName) {
        GetOrder.patient_lastName = patient_lastName;
    }

    public  String getSide() {
        return side;
    }

    public  void setSide(String side) {
        GetOrder.side = side;
    }

    public  String getLens_lr_lens() {
        return lens_lr_lens;
    }

    public  void setLens_lr_lens(String lens_lr_lens) {
        GetOrder.lens_lr_lens = lens_lr_lens;
    }

    public  String getLens_r_sphere() {
        return lens_r_sphere;
    }

    public  void setLens_r_sphere(String lens_r_sphere) {
        GetOrder.lens_r_sphere = lens_r_sphere;
    }

    public  String getLens_r_power() {
        return lens_r_power;
    }

    public  void setLens_r_power(String lens_r_power) {
        GetOrder.lens_r_power = lens_r_power;
    }

    public  String getLens_r_axis() {
        return lens_r_axis;
    }

    public  void setLens_r_axis(String lens_r_axis) {
        GetOrder.lens_r_axis = lens_r_axis;
    }

    public  String getLens_r_addition() {
        return lens_r_addition;
    }

    public  void setLens_r_addition(String lens_r_addition) {
        GetOrder.lens_r_addition = lens_r_addition;
    }

    public  String getLens_r_commercialCode() {
        return lens_r_commercialCode;
    }

    public  void setLens_r_commercialCode(String lens_r_commercialCode) {
        GetOrder.lens_r_commercialCode = lens_r_commercialCode;
    }

    public  String getLens_r_dia() {
        return lens_r_dia;
    }

    public  void setLens_r_dia(String lens_r_dia) {
        GetOrder.lens_r_dia = lens_r_dia;
    }

    public  String getCentration_r_pdz() {
        return centration_r_pdz;
    }

    public  void setCentration_r_pdz(String centration_r_pdz) {
        GetOrder.centration_r_pdz = centration_r_pdz;
    }

    public  String getCentration_r_height() {
        return centration_r_height;
    }

    public  void setCentration_r_height(String centration_r_height) {
        GetOrder.centration_r_height = centration_r_height;
    }

    public  String getCentration_r_bvd() {
        return centration_r_bvd;
    }

    public  void setCentration_r_bvd(String centration_r_bvd) {
        GetOrder.centration_r_bvd = centration_r_bvd;
    }

    public  String getGeometry_r_optimavsm() {
        return geometry_r_optimavsm;
    }

    public  void setGeometry_r_optimavsm(String geometry_r_optimavsm) {
        GetOrder.geometry_r_optimavsm = geometry_r_optimavsm;
    }

    public  String getLens_hard() {
        return lens_hard;
    }

    public  void setLens_hard(String lens_hard) {
        GetOrder.lens_hard = lens_hard;
    }

    public  String getCoating_commercialCode() {
        return coating_commercialCode;
    }

    public  void setCoating_commercialCode(String coating_commercialCode) {
        GetOrder.coating_commercialCode = coating_commercialCode;
    }

    public  String getCoating_commercialType() {
        return coating_commercialType;
    }

    public  void setCoating_commercialType(String coating_commercialType) {
        GetOrder.coating_commercialType = coating_commercialType;
    }

    public  String getCoating_commercialTint() {
        return coating_commercialTint;
    }

    public  void setCoating_commercialTint(String coating_commercialTint) {
        GetOrder.coating_commercialTint = coating_commercialTint;
    }

    public  String getCoating_no_uv() {
        return coating_no_uv;
    }

    public  void setCoating_no_uv(String coating_no_uv) {
        GetOrder.coating_no_uv = coating_no_uv;
    }

    public  String getLens_l_sphere() {
        return lens_l_sphere;
    }

    public  void setLens_l_sphere(String lens_l_sphere) {
        GetOrder.lens_l_sphere = lens_l_sphere;
    }

    public  String getLens_l_power() {
        return lens_l_power;
    }

    public  void setLens_l_power(String lens_l_power) {
        GetOrder.lens_l_power = lens_l_power;
    }

    public  String getLens_l_axis() {
        return lens_l_axis;
    }

    public  void setLens_l_axis(String lens_l_axis) {
        GetOrder.lens_l_axis = lens_l_axis;
    }

    public  String getLens_l_addition() {
        return lens_l_addition;
    }

    public  void setLens_l_addition(String lens_l_addition) {
        GetOrder.lens_l_addition = lens_l_addition;
    }

    public  String getLens_l_commercialCode() {
        return lens_l_commercialCode;
    }

    public  void setLens_l_commercialCode(String lens_l_commercialCode) {
        GetOrder.lens_l_commercialCode = lens_l_commercialCode;
    }

    public  String getLens_l_dia() {
        return lens_l_dia;
    }

    public  void setLens_l_dia(String lens_l_dia) {
        GetOrder.lens_l_dia = lens_l_dia;
    }

    public  String getCentration_l_pdz() {
        return centration_l_pdz;
    }

    public  void setCentration_l_pdz(String centration_l_pdz) {
        GetOrder.centration_l_pdz = centration_l_pdz;
    }

    public  String getCentration_l_height() {
        return centration_l_height;
    }

    public  void setCentration_l_height(String centration_l_height) {
        GetOrder.centration_l_height = centration_l_height;
    }

    public  String getCentration_l_bvd() {
        return centration_l_bvd;
    }

    public  void setCentration_l_bvd(String centration_l_bvd) {
        GetOrder.centration_l_bvd = centration_l_bvd;
    }

    public  String getGeometry_l_optimavsm() {
        return geometry_l_optimavsm;
    }

    public  void setGeometry_l_optimavsm(String geometry_l_optimavsm) {
        GetOrder.geometry_l_optimavsm = geometry_l_optimavsm;
    }

    public  String getFrm_width() {
        return frm_width;
    }

    public  void setFrm_width(String frm_width) {
        GetOrder.frm_width = frm_width;
    }

    public  String getFrm_height() {
        return frm_height;
    }

    public  void setFrm_height(String frm_height) {
        GetOrder.frm_height = frm_height;
    }

    public  String getFrm_material() {
        return frm_material;
    }

    public  void setFrm_material(String frm_material) {
        GetOrder.frm_material = frm_material;
    }

    public  String getFrm_model() {
        return frm_model;
    }

    public  void setFrm_model(String frm_model) {
        GetOrder.frm_model = frm_model;
    }

    public  String getFrm_dbl() {
        return frm_dbl;
    }

    public  void setFrm_dbl(String frm_dbl) {
        GetOrder.frm_dbl = frm_dbl;
    }

    public  String getPanangle() {
        return panangle;
    }

    public  void setPanangle(String panangle) {
        GetOrder.panangle = panangle;
    }

    public  String getBowangle() {
        return bowangle;
    }

    public  void setBowangle(String bowangle) {
        GetOrder.bowangle = bowangle;
    }

    public String serialize(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }


    static public GetOrder create(String serializedData){
        Gson gson=new Gson();
        return gson.fromJson(serializedData,GetOrder.class);
    }
}
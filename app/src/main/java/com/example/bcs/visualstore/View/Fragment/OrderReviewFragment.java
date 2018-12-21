package com.example.bcs.visualstore.View.Fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcs.visualstore.PojoDatas.AdvanceInput;
import com.example.bcs.visualstore.PojoDatas.GetOrder;
import com.example.bcs.visualstore.PojoDatas.LensInput;
import com.example.bcs.visualstore.PojoDatas.ShapeAndBevelData;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.View.Activity.DashBoard;
import com.example.bcs.visualstore.View.Activity.LoginActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OrderReviewFragment extends Fragment {

    String postDataBody;
    String server_response = "";
    String hard;
    String tintName="";
    String lens_nameR="";
    String lens_nameL="";
    //    BufferedReader br;
    int responseCode;

    Button btn_InComplete,btn_Complete,btn_placeOrder;
    String strDate, formattedDate, pantAngle, bowangle;

    TextView txt_orderReference, txt_RSphere, txt_RCylinder, txt_RAxis, txt_RAddition, txt_Lsphere, txt_LCylinder,

    txt_LAxis, txt_LAddition, txt_customerName, txt_Rpdz, txt_RrefHeight, txt_RHeight, txt_Rbvd, txt_Lpdz, txt_LrefHeight, txt_Lheight, txt_Lbvd;
    TextView lfLength, lfHeigth, lfDBL, rfLength, rfHeigth, rfDBL, leftLens, rightLens, tine_Code, hardCode, leftDiameter, rightDiameter;
    String storeCustOrderId, pantU, BowA, btn_id;
    SharedPreferences pref;
    View v;
    TextView refereNumber, customerName, consigneeName, employeeName, orderType,txt_frameName,txt_frameNum;
    String frameName,frameId;
    String id_num;
    AdvanceInput advanceInput=new AdvanceInput();
    LensInput lensInput=new LensInput();
    ShapeAndBevelData shapeAndBevelData;
    GetOrder shapeOrder;

    SharedPreferences preferences;
    String portfolio="";
    String PREFS_NAME = "MyPrefName";
    String PREFS_KEY = "MyPrefKey";
    String order_type;
    String lineIs,id;
    StringBuilder result;
    ProgressDialog progressBar;
    SharedPreferences preferencesReader;

    ImageView toolImg;
    Toolbar toolbar;
    TextView tooltxt;
    String frameModelName;



    String pantaAngle,bowAngle, rightFLength,rightFheight,rightFDBL,leftFLength,leftFHeight,leftFDBL,rightPdz,leftPdz,
            rightRefFHeight,leftRefFHeight,rightBvd,leftBvd,rightOptima,leftOptima;


    public OrderReviewFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_order_review, container, false);
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        tooltxt = (TextView) toolbar.findViewById(R.id.tooltxt);
        toolImg=(ImageView)toolbar.findViewById(R.id.menuLogo);
        tooltxt.setText("ORDER REVIEW");


        preferences=getActivity().getSharedPreferences("MyPref",0);
        id= preferences.getString("id","1");
        Log.e("id****** ",id);

        txt_orderReference = (TextView) v.findViewById(R.id.referenceNumber);

        txt_RSphere = (TextView) v.findViewById(R.id.txt_Rsphere);
        txt_RCylinder = (TextView) v.findViewById(R.id.txt_RCylinder);
        txt_RAxis = (TextView) v.findViewById(R.id.txt_Raxis);
        txt_RAddition = (TextView) v.findViewById(R.id.txt_Raddition);
        txt_Lsphere = (TextView) v.findViewById(R.id.txt_Lsphere);
        txt_LCylinder = (TextView) v.findViewById(R.id.txt_Lcylinder);
        txt_LAxis = (TextView) v.findViewById(R.id.txt_LAxis);
        txt_LAddition = (TextView) v.findViewById(R.id.txt_Laddition);
        txt_Rpdz = (TextView) v.findViewById(R.id.txt_Rpdz);
        txt_RrefHeight = (TextView) v.findViewById(R.id.txt_RrefHeight);
        txt_Rbvd=(TextView)v.findViewById(R.id.txt_Rbvd);
        txt_frameName=(TextView)v.findViewById(R.id.frame_type);
        txt_frameNum=(TextView)v.findViewById(R.id.standard);

        txt_Lpdz = (TextView) v.findViewById(R.id.txt_Lpdz);
        txt_LrefHeight = (TextView) v.findViewById(R.id.txt_LrefHeight);
        txt_Lbvd = (TextView) v.findViewById(R.id.txt_Lbvd);


        rfLength = (TextView) v.findViewById(R.id.rfLength);
        rfHeigth = (TextView) v.findViewById(R.id.rfHeight);
        rfDBL = (TextView) v.findViewById(R.id.rfDBL);
        lfLength = (TextView) v.findViewById(R.id.lfLength);
        lfHeigth = (TextView) v.findViewById(R.id.lfHeight);
        lfDBL = (TextView) v.findViewById(R.id.lfDBL);

        leftLens = (TextView) v.findViewById(R.id.lLens);
        rightLens = (TextView) v.findViewById(R.id.rLens);

        hardCode = (TextView) v.findViewById(R.id.hardCode);
        tine_Code = (TextView) v.findViewById(R.id.tintCode);

        leftDiameter = (TextView) v.findViewById(R.id.lDiameter);
        rightDiameter = (TextView) v.findViewById(R.id.rDiameter);

        customerName = (TextView) v.findViewById(R.id.customerName);
        consigneeName = (TextView) v.findViewById(R.id.consignee);
//        visionCenter = (TextView) v.findViewById(R.id.visionCenter);
        orderType = (TextView) v.findViewById(R.id.orderTye);
        employeeName = (TextView) v.findViewById(R.id.employee);
        btn_Complete=(Button)v.findViewById(R.id.btn_Complete);
        btn_InComplete=(Button)v.findViewById(R.id.btn_InComplete);
        btn_placeOrder=(Button)v.findViewById(R.id.btn_placeOrder);


        preferences=getActivity().getSharedPreferences("MyPref",0);
        id_num= preferences.getString("id","1");
        /*ShapeAndBavel data*/


        preferencesReader = getActivity().getSharedPreferences("fromShapre", Context.MODE_PRIVATE);
        String serializedDataFromPreference=preferencesReader.getString(PREFS_KEY,null);
        String shapId=preferencesReader.getString("fromShap","");
        shapeAndBevelData=ShapeAndBevelData.create(serializedDataFromPreference);


        SharedPreferences shapeandBevel=getActivity().getSharedPreferences("hione",Context.MODE_PRIVATE);
        String shap=shapeandBevel.getString(PREFS_KEY,null);
        shapeOrder=GetOrder.create(shap);

        if (shapeAndBevelData!=null && shapId.equals("Shape")){
//            frameName=shapeAndBevelData.getFrame();
            frameId=shapeAndBevelData.getId();
//            txt_frameName.setText(shapeAndBevelData.getFrame());
//            txt_frameNum.setText("No."+shapeAndBevelData.getId());
            frameModelName=shapeAndBevelData.getSelectedRadioButton();
            System.out.println("radio id"+shapeAndBevelData.getSelectedRadioButton());
        }else {

            if (shapeOrder!=null){
//                frameName=shapeOrder.getFrm_material();
                frameId=shapeOrder.getFrm_model();
            }


        }

//        txt_frameName.setText(frameName);
       // txt_frameNum.setText(frameId);


        SharedPreferences preferencesReader = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String serializedDataFromLens=preferencesReader.getString(PREFS_KEY,null);
        lensInput=LensInput.create(serializedDataFromLens);


        txt_orderReference.setText(lensInput.getOrderRef());
        customerName.setText(lensInput.getConFstNme() +" "+ lensInput.getConLstNme());

        txt_frameNum.setText("No."+frameModelName);
        consigneeName.setText(lensInput.getConsignee());
        employeeName.setText(lensInput.getEmployee());
        txt_RSphere.setText(lensInput.getRightSphere());
        txt_RCylinder.setText(lensInput.getRightCylind());
        txt_RAddition.setText(lensInput.getRightAddition());
        txt_RAxis.setText(lensInput.getRightAxis());
        txt_Lsphere.setText(lensInput.getLeftSphere());
        txt_LCylinder.setText(lensInput.getLeftCylind());
        txt_LAxis.setText(lensInput.getLeftAxis());
        txt_LAddition.setText(lensInput.getLeftAddition());

        hard=lensInput.getHard();
        if(lensInput.getSides().equals("right")){
            rightLens.setText(lensInput.getLensTypeName());
            leftLens.setText("");
            leftDiameter.setText("");
        } else if (lensInput.getSides().equals("left") ){
            leftLens.setText(lensInput.getLensTypeName());
            rightLens.setText("");
            rightDiameter.setText("");
        }else{

            leftLens.setText(lensInput.getLensTypeName());
            rightLens.setText(lensInput.getLensTypeName());
        }

        rightDiameter.setText(lensInput.getRightDiameter());
        leftDiameter.setText(lensInput.getLeftDiamter());
        hardCode.setText(lensInput.getCoating());
        tine_Code.setText(lensInput.getTintName());


        /*Advance data is */

        fromAdvance();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        strDate = formatter.format(date);

        DateFormat dateFormat1 = new SimpleDateFormat("HHmmss");
        storeCustOrderId = dateFormat1.format(date);

        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        formattedDate = dateFormat.format(date);


        listener();
        return v;
    }

    private void listener() {

        btn_Complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlConnection1 urlConnection1 = new UrlConnection1(2);
                urlConnection1.execute();

                order_type="2";

            }
        });


        btn_InComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlConnection1 urlConnection1 = new UrlConnection1(1);
                urlConnection1.execute();
                order_type="1";

            }
        });

        btn_placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlConnection1 urlConnection1 = new UrlConnection1(3);
                urlConnection1.execute();
                order_type="3";

            }
        });
    }




    public class UrlConnection1 extends AsyncTask<String, Void, String> {
        int status;

        BufferedReader br;

        public UrlConnection1(int status) {
            this.status=status;
        }
        public Dialog dialog;

        @Override
        protected void onPreExecute() {

            progressBar = new ProgressDialog(getContext(),R.style.MyAlertDialogStyle);
            progressBar.setMessage("Loading...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);
            progressBar.setMax(100);
            progressBar.setCancelable(true);
            progressBar.setCanceledOnTouchOutside(true);
            progressBar.show();
        }


        @Override
        protected String doInBackground(String... strings) {

            String patient, commercial_code, Right_sphere, Right_cylinder, Right_Axis, Right_Add, Coating_code, Tint_code, Right_diameter, Left_sphere, Left_cylinder, Left_Axis, Left_Add, Left_Coating_Name, Left_Coating_code, Left_Tint_code, Left_diameter;
            String Material = "";
            String Order_reference;
            String Coating_Name = "";
            String Model = "";
            String boxwidth = "";
            String boxlength = "";
            String DBL = "";
            String PantoAngle = "";
            String BowAng = "";
            String Left_Dist = "";
            String Left_Reference = "";
            String Left_BVD = "";
            String frame_data = "";
            String CustomerFirst="";
            String CustomerLast="";
            String Right_Dist = "";
            String Right_Reference = "";
            String Right_BVD = "";
            String Centration = "";
            String Right_Centration = "";
            String lens_typeR="";
            String lens_typeL="";
            String hardType;

            String switch_val;


            if(lensInput.getSides().equals("right")){
                lens_typeR=","+"\"lens_r_commercialCode\""+":"+"\""+lensInput.getLensTypeCode()+"\"";
                lens_nameR=","+"\"lens_r_commercialName\""+":"+"\""+lensInput.getLensTypeName()+"\"";
            }else if(lensInput.getSides().equals("left")){
                lens_typeL=","+"\"lens_l_commercialCode\""+":"+"\""+lensInput.getLensTypeCode()+"\"";
                lens_nameL=","+"\"lens_l_commercialName\""+":"+"\""+lensInput.getLensTypeName()+"\"";
            }
            else{
                lens_typeR=","+"\"lens_r_commercialCode\""+":"+"\""+lensInput.getLensTypeCode()+"\"";
                lens_typeL=","+"\"lens_l_commercialCode\""+":"+"\""+lensInput.getLensTypeCode()+"\"";
                lens_nameL=","+"\"lens_l_commercialName\""+":"+"\""+lensInput.getLensTypeName()+"\"";
                lens_nameR=","+"\"lens_r_commercialName\""+":"+"\""+lensInput.getLensTypeName()+"\"";
            }
//                hardType="";
//
//            }else{
//                hardType= ","+"\"lens_hard\":"+"\""+hard+"\"";
//            }

            if(lensInput.getSwitch1().equals("OFF")){
                switch_val=","+"\"coating_no_uv\"" +":"+"\"1\"";
            }else{
                switch_val="";
            }

            if(lensInput.getOrderRef()==null){
                Order_reference="";
            }else{
                Order_reference="\"order_reference\""+":"+"\"" + lensInput.getOrderRef() + "\"";
                System.out.println("Reference"+Order_reference);
            }

            System.out.println("name"+lensInput.getConFstNme());
            if(lensInput.getConFstNme().equals("") && lensInput.getConLstNme().equals("")){
                CustomerFirst="";

            }else{
                CustomerFirst=","+"\"patient_term\""+":"+"1"+","+"\"patient_firstName\""+":"+"\"" +lensInput.getConFstNme()+ "\""+","+"\"patient_lastName\""+":"+"\""+lensInput.getConLstNme()+ "\"";
            }

            if(lensInput.getPortofolio()!=null && lensInput.getPortofolio().equals("ZEISS")){
                portfolio=","+"\"portfolio\""+":"+"\"0\"";
            }else{
                portfolio=","+"\"portfolio\""+":"+"\"1\"";
            }
//
//            if (lensInput.getConLstNme().equals("")){
//                CustomerLast="";
//            }else{
//                CustomerLast=","+"\"patient_lastName\""+":"+"\""+lensInput.getConLstNme()+ "\"";
//            }
            System.out.println("right"+lensInput.getRightSphere());
            if (lensInput.getRightSphere().equals("")) {
                Right_sphere = "";
            } else {
                Right_sphere = ","+"\"lens_r_sphere\""+":" +"\""+lensInput.getRightSphere()+ "\"";
            }
            System.out.println("right"+lensInput.getRightCylind());
            if (lensInput.getRightCylind().equals("")||lensInput.getRightAxis().equals("")) {
                Right_cylinder = "";
            } else {
                Right_cylinder =","+"\"lens_r_power\""+":"+"\"" +lensInput.getRightCylind()+ "\""+","+"\"lens_r_axis\""+":"+"\""+lensInput.getRightAxis()+"\"";
            }

            if (lensInput.getRightAddition().equals("")) {
                Right_Add = "";
            } else {
                Right_Add = ","+"\"lens_r_addition\""+":"+"\""+lensInput.getRightAddition()+"\"";
            }



            if ( lensInput.getCoating()==null && lensInput.getCotingCode()==null) {
                Coating_code = "";
            } else {

                Coating_code = ","+"\"coating_commercialType\""+":"+"\"" + lensInput.getCoatingType()+"\""+","+"\"coating_commercialCode\""+":"+"\"" +lensInput.getCotingCode()+"\""+","+"\"coating_commercialCodeName\""+":"+"\""+lensInput.getCoating()+"\"";
//                Coating_code= "<commercialCode>"+coting+"</commercialCode>\n";
            }
            if (lensInput.getTint()==null) {
                Tint_code = "";
                tintName = "";
            } else {

                Tint_code =  ","+"\"coating_commercialTint\""+":"+"\"" +lensInput.getTint()+"\"";
                tintName=","+"\"coating_commercialTintName\""+":"+"\"" +lensInput.getTintName()+"\"";
                System.out.println("tintName in OrderReview"+tintName);
//                Tint_code= "<commercialCode>"+tineCode+"</commercialCode>\n";
            }
            if (rightPdz==null) {
                Right_Dist = "";
            } else {
                Right_Dist = ","+"\"centration_r_pdz\""+":"+"\""+rightPdz+"\"";
            }



            if (rightRefFHeight==null) {
                Right_Reference = "";
            } else {
                Right_Reference = ","+"\"centration_r_height\""+":"+"\""+rightRefFHeight+"\"";
            }

            if (rightBvd==null) {
                Right_BVD = "";
            } else {
                Right_BVD = ","+"\"centration_r_bvd\""+":"+"\""+rightBvd+"\"";
            }


            if (rightPdz==null && rightRefFHeight==null && rightBvd==null) {

                Right_Centration = "";

            } else {
                Right_Centration = Right_Dist + Right_Reference + Right_BVD ;
            }
            if (lensInput.getRightDiameter().equals("")) {
                Right_diameter = "";
            } else {

                Right_diameter =","+"\"lens_r_dia\""+":"+"\""+lensInput.getRightDiameter()+"\"";
            }

            if (lensInput.getLeftSphere().equals("")) {
                Left_sphere = "";
            } else {
                Left_sphere = ","+"\"lens_l_sphere\""+":"+"\""+lensInput.getLeftSphere()+"\"";
            }

            if (lensInput.getLeftCylind().equals("")||lensInput.getLeftAxis().equals("")) {
                Left_cylinder = "";
            } else {
                Left_cylinder =  ","+"\"lens_l_power\""+":"+"\""+lensInput.getLeftCylind()+"\""+","+"\"lens_l_axis\""+":"+"\""+lensInput.getLeftAxis()+"\"";
            }


            if (lensInput.getLeftAddition().equals("")) {
                Left_Add = "";
            } else {
                Left_Add = ","+"\"lens_l_addition\""+":"+"\""+lensInput.getLeftAddition()+"\"";
            }


            if (leftPdz==null) {
                Left_Dist = "";
            } else {
                Left_Dist = ","+"\"centration_l_pdz\""+":"+"\""+leftPdz+"\"";
            }

            if (leftRefFHeight==null) {
                Left_Reference = "";
            } else {
                Left_Reference = ","+"\"centration_l_height\""+":"+"\""+leftRefFHeight+"\"";
            }

            if (leftBvd==null) {
                Left_BVD = "";

            } else {

                Left_BVD = ","+"\"centration_l_bvd\""+":"+"\""+leftBvd+"\"";

            }

            if (leftPdz==null && leftRefFHeight==null && leftBvd==null) {

                Centration = "";
            } else {
                Centration = Left_Dist + Left_Reference + Left_BVD ;
            }

            if (lensInput.getLeftDiamter().equals("")) {
                Left_diameter = "";
            } else {

                Left_diameter = ","+"\"lens_l_dia\""+":"+"\""+lensInput.getLeftDiamter()+"\"";

            }


            if (frameName==null) {
                Material = "";
            } else {
                Material = ","+"\"frm_material\""+":"+"\""+frameName+"\"";
            }

            if (frameId==null) {
                Model = "";
            } else {
                Model = ","+"\"frm_model\""+":"+"\""+frameId+"\"";
            }

            if (rightFLength==null) {
                boxwidth = "";
            } else {
                boxwidth = ","+"\"frm_width\""+":"+"\""+rightFLength+"\"";
            }

            if (rightFheight==null) {
                boxlength = "";
            } else {
                boxlength = ","+"\"frm_height\""+":"+"\""+rightFheight+"\"";
            }
            if (rightFDBL==null) {
                DBL = "";
            } else {
                DBL = ","+"\"frm_dbl\""+":"+"\""+rightFDBL+"\"";
            }

            if (pantaAngle==null) {
                PantoAngle = "";
            } else {
                PantoAngle =","+ "\"panangle\""+":"+"\""+pantaAngle+"\"";
            }

            if (bowAngle==null) {
                BowAng = "";
            } else {
                BowAng = ","+"\"bowangle\""+":"+"\""+bowAngle+"\"";
            }


            if (frameName==null && frameId==null&& rightFLength==null&& rightFheight==null&& rightFDBL==null && pantaAngle==null && bowAngle==null) {
                frame_data = "";
            } else {

                frame_data = Material + Model +boxwidth + boxlength + DBL + PantoAngle + BowAng;
            }

            String lensSide;
            if(lensInput.getSides().equals("right")){
                lensSide=","+"\"side\""+":"+"\"1\""+","+"\"lens_lr_lens\""+":"+"[\"R\"]";
            }else if(lensInput.getSides().equals("left")){
                lensSide=","+"\"side\""+":"+"\"1\""+","+"\"lens_lr_lens\""+":"+"[\"L\"]";
            }else if(lensInput.getSides().equals("both")){
                lensSide=","+"\"side\""+":"+"\"2\""+","+"\"lens_lr_lens\""+":"+"[\"R\",\"L\"]";
            }else{
                if(lensInput.getVirtualSide().equals("virtualLeft")){
                    lensSide=","+"\"side\""+":"+"\"3\""+","+"\"lens_lr_lens\""+":"+"[\"L\"]";
                }else{
                    lensSide=","+"\"side\""+":"+"\"3\""+","+"\"lens_lr_lens\""+":"+"[\"R\"]";
                }
            }
            String right_optima,left_optima;
            if(rightOptima==null){
                right_optima="";
            }else{
                right_optima=","+"\"geometry_r_optimavsm\""+":"+"\""+rightOptima+"\"";
            }


            String emp_name="";
            if(lensInput.getEmployee()==null){
                emp_name="";

            }else{
                emp_name=","+"\"employee_name\""+":"+"\""+lensInput.getEmployee()+"\"";
            }

            if(leftOptima==null){
                left_optima="";
            }else{
                left_optima=","+"\"geometry_l_optimavsm\""+":"+"\""+leftOptima+"\"";
            }

            String framName;

            if(frameModelName==null){
                framName="";
            }else{
                framName=","+"\"frameNamedef\""+":"+"\""+frameModelName+"\"";
            }

            String postURL = "http://103.21.59.241/carl_visustore/master_api/place_order";
            postDataBody = "{\"admin_id\":\""+id+"\",\"order_type\" :\""+status+"\","+Order_reference +portfolio+emp_name+CustomerFirst+lensSide+ Right_sphere+ Right_cylinder+ Right_Add+ lens_typeR+
                    lens_nameR+Right_diameter+
                    Right_Centration+ right_optima +
                    Tint_code+tintName+
                    Coating_code+
                    switch_val +
                    Left_sphere+
                    Left_cylinder+
                    Left_Add+
                    lens_typeL+lens_nameL+
                    Left_diameter+ Centration+left_optima+
                    frame_data +framName+"}";


            System.out.println("body"+postDataBody);
            result = new StringBuilder();
            try {


                URL url = new URL(postURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(30000);
                conn.setConnectTimeout(40000);
                conn.setRequestMethod("POST");

                // see section 3.1 Content-Type

                conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                conn.setRequestProperty("Admin-Service", "visustore-RESTApi");
                conn.setRequestProperty("Auth-Key", "BwebRestAPI");
                conn.setRequestProperty("Envi","Test");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();

                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(postDataBody);
                System.out.println("Post data body : " + postDataBody.toString());
                wr.flush();
                wr.close();
                os.close();

                responseCode = conn.getResponseCode();
                System.out.println("code of response"+responseCode);
//                System.out.println("response string"+conn.getErrorStream().toString());
                // Log.e("rescode=", "" + responseCode);ok


                System.out.println("Post data body : " + postDataBody.toString());



                if (responseCode == 200) {
                    String line;
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while ((line = br.readLine()) != null) {
                        server_response += line;
                        result.append(line);
                        Handler handler = new Handler(getContext().getMainLooper());
                        handler.post(new Runnable() {
                            public void run() {
                                Log.e("response body is ",""+br.toString());

                            }
                        });
                    }

                    Log.e("response line is",""+result.toString());

                }else if (responseCode==409){
                    String line;
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    while ((line = br.readLine()) != null) {
                        server_response += line;
                        result.append(line);
                        Handler handler = new Handler(getContext().getMainLooper());
                        handler.post(new Runnable() {
                            public void run() {
                                Log.e("error response body is ",""+br.toString());
                            }
                        });
                    }
                    Log.e("error response line is",""+result.toString());
                }else {
                    System.out.println("login data");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();
            System.out.println("Output : " + s);
            System.out.println("result : "+result.toString() );

            if(responseCode==200){

                try {
                    JSONObject jsonObject =new JSONObject(s);

                    String Message= jsonObject.getString("message");
                    Alert1(Message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if(responseCode==500){
//                Alert("Internal Server Problem");
                Toast.makeText(getContext(),"Internal Server Problem",Toast.LENGTH_SHORT).show();
                switch (order_type) {
                    case "1": {
                        UrlConnection1 urlConnection1 = new UrlConnection1(1);
                        urlConnection1.execute();
                        break;
                    }
                    case "2": {
                        UrlConnection1 urlConnection1 = new UrlConnection1(2);
                        urlConnection1.execute();
                        break;
                    }
                    default: {
                        UrlConnection1 urlConnection1 = new UrlConnection1(3);
                        urlConnection1.execute();
                        break;
                    }
                }

            }
            else if(responseCode==409){
                String data;
                System.out.println("esponse is "+result.toString());

                try {
                    JSONObject jsonObject =new JSONObject(s);
                    JSONObject jsonObject1=jsonObject.getJSONObject("message");
                    String errorMessage= jsonObject1.getString("errorText");
                    Alert(errorMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }

    }



    public void Alert(String subS){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage(subS);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    public void Alert1(String subS){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext(),R.style.AlertDialogDanger);
        builder1.setMessage(subS);
        builder1.setCancelable(true);
        builder1.setPositiveButton("OK",null);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LensInput lensInput=new LensInput();

                        Fragment fragment=new DashBoardFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        toolImg.setImageDrawable(getResources().getDrawable(R.drawable.menulogo));

                        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                        settings.edit().clear().apply();

                        SharedPreferences setting = getActivity().getSharedPreferences("hi", Context.MODE_PRIVATE);
                        setting.edit().clear().apply();

                        AdvanceOptionFragment.selectedItem=0;
                        ShapeAndBevelFragment.selectedRadioButton="";


                    }
                });
        AlertDialog alert12 = builder1.create();
        alert12.show();
    }


    public void fromAdvance(){

        /*for new Adavance data*/
        preferencesReader=getActivity().getSharedPreferences("hi",Context.MODE_PRIVATE);
        String serializedDataFromAdvance=preferencesReader.getString(PREFS_KEY,null);
        String id=preferencesReader.getString("id","");
        System.out.println("id is "+id);
        AdvanceInput advanceInput=AdvanceInput.create(serializedDataFromAdvance);


        /*for edit Advance data */
        SharedPreferences preferencesReader1 = getActivity().getSharedPreferences("hione", Context.MODE_PRIVATE);
        String serializedDataFromLens1=preferencesReader1.getString(PREFS_KEY,null);
        GetOrder getOrder=GetOrder.create(serializedDataFromLens1);

        if (advanceInput!=null && id.equals("advance")){

            Log.e("from without advance","success");


            System.out.println("success");
            //  rightPdz=advanceInput.getRightPdz();

            rfLength.setText(advanceInput.getRightFrmLegt());
            rfHeigth.setText(advanceInput.getRightFrmHigt());
            lfLength.setText(advanceInput.getLeftFrmLegt());
            lfHeigth.setText(advanceInput.getLeftFrmHigt());
            rfDBL.setText(advanceInput.getRightDbl());
            lfDBL.setText(advanceInput.getLeftDbl());

            txt_Rpdz.setText(advanceInput.getRightPdz());
            txt_Lpdz.setText(advanceInput.getLeftPdz());
            txt_RrefHeight.setText(advanceInput.getRightYfh());
            txt_LrefHeight.setText(advanceInput.getLeftYfh());
            txt_Rbvd.setText(advanceInput.getRightBvd());
            txt_Lbvd.setText(advanceInput.getLeftBvd());


            pantaAngle=advanceInput.getPantoAngle();
            bowAngle=advanceInput.getBowAngle();
            rightFLength=advanceInput.getRightFrmLegt();
            rightFheight=advanceInput.getRightFrmHigt();
            rightFDBL=advanceInput.getRightDbl();
            leftFLength=advanceInput.getLeftFrmLegt();
            leftFHeight=advanceInput.getLeftFrmHigt();
            leftFDBL=advanceInput.getLeftDbl();

            rightPdz=advanceInput.getRightPdz();
            leftPdz=advanceInput.getLeftPdz();
            rightRefFHeight=advanceInput.getRightYfh();
            leftRefFHeight=advanceInput.getLeftYfh();
            frameName=advanceInput.getFrame();

            txt_frameName.setText(frameName);

            rightBvd=advanceInput.getRightBvd();
            leftBvd=advanceInput.getLeftBvd();

            rightOptima=advanceInput.getRightCheck();
            leftOptima=advanceInput.getLeftCheck();

        }else {
            if (getOrder!=null) {


                Log.e("from edit advance","success");
                if (getOrder.getPanangle()!=null){
                    pantAngle=getOrder.getPanangle();
                }
                if (getOrder.getBowangle()!=null){
                    bowangle=getOrder.getBowangle();
                }
                if (getOrder.getLens_lr_lens() != null) {
                    String lens = getOrder.getLens_lr_lens();
                    if (lens.equals("R")) {
                        if (getOrder.getFrm_width() != null) {
                            rfLength.setText(getOrder.getFrm_width());
                            rightFLength=getOrder.getFrm_width();


                            if (getOrder.getFrm_height() != null) {
                                rfHeigth.setText(getOrder.getFrm_height());
                                rightFheight=getOrder.getFrm_height();
                            }
                            if (getOrder.getFrm_dbl() != null) {
                                rfDBL.setText(getOrder.getFrm_dbl());
                                rightFDBL=getOrder.getFrm_dbl();
                            }
                        }
                    } else if (lens.equals("L")) {
                        if (getOrder.getFrm_width() != null) {
                            lfLength.setText(getOrder.getFrm_width());
                            leftFLength=getOrder.getFrm_width();

                            if (getOrder.getFrm_height() != null) {
                                lfHeigth.setText(getOrder.getFrm_height());
                                leftFHeight=getOrder.getFrm_height();
                            }
                            if (getOrder.getFrm_dbl() != null) {
                                lfDBL.setText(getOrder.getFrm_dbl());
                                leftFDBL=getOrder.getFrm_dbl();
                            }
                        }
                    } else if (lens.equals("R,L")) {
                        if (getOrder.getFrm_width() != null) {
                            rfLength.setText(getOrder.getFrm_width());
                            lfLength.setText(getOrder.getFrm_width());

                            rightFLength=getOrder.getFrm_width();
                            leftFLength=getOrder.getFrm_width();
                        }
                        if (getOrder.getFrm_height() != null) {
                            rfHeigth.setText(getOrder.getFrm_height());
                            lfHeigth.setText(getOrder.getFrm_height());
                            rightFheight=getOrder.getFrm_height();
                            leftFHeight=getOrder.getFrm_height();
                        }
                        if (getOrder.getFrm_dbl() != null) {
                            rfDBL.setText(getOrder.getFrm_dbl());
                            lfDBL.setText(getOrder.getFrm_dbl());
                            rightFDBL=getOrder.getFrm_dbl();
                            leftFDBL=getOrder.getFrm_dbl();
                        }

                    }
                }
                consigneeName.setText(getOrder.getCons());

                if (getOrder.getCentration_r_pdz() != null) {
                    txt_Rpdz.setText(getOrder.getCentration_r_pdz());
                    rightPdz=getOrder.getCentration_r_pdz();
                }
                if (getOrder.getCentration_r_height() != null) {
                    txt_RrefHeight.setText(getOrder.getCentration_r_height());
                    rightRefFHeight=getOrder.getCentration_r_height();
                }
                if (getOrder.getCentration_r_bvd() != null) {
                    txt_Rbvd.setText(getOrder.getCentration_r_bvd());
                    rightBvd=getOrder.getCentration_r_bvd();
                }
                if (getOrder.getCentration_l_pdz() != null) {
                    txt_Lpdz.setText(getOrder.getCentration_l_pdz());
                    leftPdz=getOrder.getCentration_l_pdz();
                }
                if (getOrder.getCentration_l_height() != null) {
                    txt_LrefHeight.setText(getOrder.getCentration_l_height());
                    leftRefFHeight=getOrder.getCentration_l_height();
                }
                if (getOrder.getCentration_l_bvd() != null) {
                    txt_Lbvd.setText(getOrder.getCentration_l_bvd());
                    leftBvd=getOrder.getCentration_l_bvd();
                }
                if (getOrder.getGeometry_r_optimavsm() != null) {
                    rightOptima = getOrder.getGeometry_r_optimavsm();
                }
                if (getOrder.getGeometry_l_optimavsm() != null) {
                    leftOptima = getOrder.getGeometry_l_optimavsm();
                }
            }
        }

    }


}
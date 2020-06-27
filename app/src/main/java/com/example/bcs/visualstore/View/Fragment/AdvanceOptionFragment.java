package com.example.bcs.visualstore.View.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bcs.visualstore.PojoDatas.AdvanceInput;
import com.example.bcs.visualstore.PojoDatas.GetOrder;
import com.example.bcs.visualstore.PojoDatas.LensInput;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.RangesDisplay1;
import com.example.bcs.visualstore.Validation1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvanceOptionFragment extends Fragment {

    EditText edit_framelength,edit_frameheight,edit_dbl,edit_Lframelength,edit_Lframeheight,edit_Ldbl,edit_Rpdz,edit_Ryfh,edit_Rbvd,edit_Lpdz,edit_Lyfh,edit_Lbvd;


    String tineCode;
    SharedPreferences preferences;
    String customerId,coatName, rfLength,rfheight,rDBL,lfLength,lfRight,lDBL,rRpdz,rRyfh,rRbvd,lRpdz,lRyfh,lRbvd;
    EditText pAngle,bAngle;
    Button btn_confirm,btn_cancel;
    String pAng,bAng;
    View v;
    CheckBox centerRight,centerLeft;
    String PREFS_NAME = "MyPrefName";
    String PREFS_KEY = "MyPrefKey";
    AdvanceInput advanceInput;
    TextView values_individual,values_frame,values_centration,errorMsgIndiviual,errorMsgFrame,errorMsgCentrat;
    String rightCheck="0",leftCheck="0";
    String side;
    ImageView imageView;
    GetOrder getOrder;
    Toolbar toolbar;
    TextView tooltxt;
    String button_confirm;
    Spinner spinner_type;
    String name;
    public static int shape=0;
    int shapeSpinner=0;

    public static int selectedItem = 0;

    LensInput lensInput;

    public AdvanceOptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_advance_option, container, false);
        toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        imageView=(ImageView)toolbar.findViewById(R.id.menuLogo);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.backbtn));
        tooltxt.setText(R.string.advanceOption);

//        String name=getClass().getSimpleName();
//        Log.e("fragment",name);

        btn_confirm=(Button)v.findViewById(R.id.order);
        btn_cancel=(Button)v.findViewById(R.id.btn_advanceOption);
        pAngle=(EditText)v.findViewById(R.id.pAngle);
        bAngle=(EditText)v.findViewById(R.id.bAngle);

        values_individual=(TextView)v.findViewById(R.id.values_individual);
        errorMsgIndiviual=(TextView)v.findViewById(R.id.error_msg);
        errorMsgFrame=(TextView)v.findViewById(R.id.error_msgFrame);
        errorMsgCentrat=(TextView)v.findViewById(R.id.error_msgCentrat);

        values_frame=(TextView)v.findViewById(R.id.values_frame);
        values_centration=(TextView)v.findViewById(R.id.values_centration);



        edit_framelength =(EditText)v.findViewById(R.id.edit_1) ;
        edit_frameheight=(EditText)v.findViewById(R.id.edit_height);
        edit_dbl=(EditText)v.findViewById(R.id.edit_Rdbl);

        edit_Lframelength=(EditText)v.findViewById(R.id.edit_Llength);
        edit_Lframeheight=(EditText)v.findViewById(R.id.edit_Lheight);
        edit_Ldbl=(EditText)v.findViewById(R.id.edit_Ldbl);


        edit_Rpdz=(EditText)v.findViewById(R.id.edit_Rpdz);
        edit_Ryfh=(EditText)v.findViewById(R.id.edit_Ryfh);
        edit_Rbvd=(EditText)v.findViewById(R.id.edit_Rbvd);
        centerRight=(CheckBox)v.findViewById(R.id.checkboxCentrationRight);


        edit_Lpdz=(EditText)v.findViewById(R.id.edit_Lpdz);
        edit_Lyfh=(EditText)v.findViewById(R.id.edit_Lyfh);
        edit_Lbvd=(EditText)v.findViewById(R.id.edit_Lbvd);
        centerLeft=(CheckBox)v.findViewById(R.id.checkboxCentrationLeft);
        spinner_type=(Spinner) v.findViewById(R.id.spinner_type);


        SharedPreferences preferencesReader1 = getActivity().getSharedPreferences("hione", Context.MODE_PRIVATE);
        String serializedDataFromLens1=preferencesReader1.getString(PREFS_KEY,null);
        getOrder=GetOrder.create(serializedDataFromLens1);

        SharedPreferences preferencesReaderOne = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String serializedDataFromLensOne=preferencesReaderOne.getString(PREFS_KEY,null);
        lensInput=LensInput.create(serializedDataFromLensOne);

        System.out.println("indiv"+ LensInput.getIndividual());



//        System.out.println("panta"+getOrder.getPanangle());

        if (getOrder!=null){
            if(getOrder.getLens_lr_lens()!=null){
                String lens=getOrder.getLens_lr_lens();
                if(lens.equals("R")){
                    if(getOrder.getFrm_width()!=null) {
                        edit_framelength.setText(getOrder.getFrm_width());

                        if(getOrder.getFrm_height()!=null) {
                            edit_frameheight.setText(getOrder.getFrm_height());
                        }
                        if(getOrder.getFrm_dbl()!=null) {
                            edit_dbl.setText(getOrder.getFrm_dbl());
                        }
                    }
                }else if(lens.equals("L")){
                    if(getOrder.getFrm_width()!=null) {
                        edit_Lframelength.setText(getOrder.getFrm_width());

                        if(getOrder.getFrm_height()!=null) {
                            edit_Lframeheight.setText(getOrder.getFrm_height());
                        }
                        if(getOrder.getFrm_dbl()!=null) {
                            edit_Ldbl.setText(getOrder.getFrm_dbl());
                        }
                    }
                }else if(lens.equals("R,L")){
                    if(getOrder.getFrm_width()!=null){
                        edit_framelength.setText(getOrder.getFrm_width());
                        edit_Lframelength.setText(getOrder.getFrm_width());
                    }
                    if(getOrder.getFrm_height()!=null){
                        edit_frameheight.setText(getOrder.getFrm_height());
                        edit_Lframeheight.setText(getOrder.getFrm_height());
                    }
                    if(getOrder.getFrm_dbl()!=null){
                        edit_dbl.setText(getOrder.getFrm_dbl());
                        edit_Ldbl.setText(getOrder.getFrm_dbl());
                    }
                }else if( getOrder.getFrm_material()!=null && !getOrder.getFrm_material().equals("") ){
                    name=getOrder.getFrm_material();
                    if(name.equals("METAL")){
                    shapeSpinner=0;
                    }else if(name.equals("PLASTIC")){

                    shapeSpinner=1;

                    }else if(name.equals("NYLOR")){

                    shapeSpinner=2;

                    }else {

                    shapeSpinner=3;
                }
                spinner_type.setSelection(shapeSpinner);
//                }
                }
            }

            pAngle.setText(getOrder.getPanangle());
            bAngle.setText(getOrder.getBowangle());

            edit_Rpdz.setText(getOrder.getCentration_r_pdz());
            edit_Ryfh.setText(getOrder.getCentration_r_height());
            edit_Rbvd.setText(getOrder.getCentration_r_bvd());
            edit_Lpdz.setText(getOrder.getCentration_l_pdz());
            edit_Lyfh.setText(getOrder.getCentration_l_height());
            edit_Lbvd.setText(getOrder.getCentration_l_bvd());
            String geometry_r_optimavsm=getOrder.getGeometry_r_optimavsm();
            if(geometry_r_optimavsm!=null && geometry_r_optimavsm.equals("1")){
                centerRight.setChecked(true);
                rightCheck="1";
            }
            String geometry_l_optimavsm=getOrder.getGeometry_l_optimavsm();
            if(geometry_l_optimavsm!=null && geometry_l_optimavsm.equals("1")){
                centerLeft.setChecked(true);
                leftCheck="1";
            }
        }

        SharedPreferences preferencesReader = getActivity().getSharedPreferences("hi", Context.MODE_PRIVATE);
        String serializedDataFromLens=preferencesReader.getString(PREFS_KEY,null);
        AdvanceInput advanceInput=AdvanceInput.create(serializedDataFromLens);
        if (advanceInput!=null){
            if(advanceInput.getPantoAngle()!=null){
                pAngle.setText(advanceInput.getPantoAngle());
            }
            if(advanceInput.getBowAngle()!=null){
                bAngle.setText(advanceInput.getBowAngle());
            }
            if(advanceInput.getRightFrmLegt()!=null){
                edit_framelength.setText(advanceInput.getRightFrmLegt());
            }
            if(advanceInput.getRightFrmHigt()!=null){
                edit_frameheight.setText(advanceInput.getRightFrmHigt());
            }
            if(advanceInput.getRightDbl()!=null){
                edit_dbl.setText(advanceInput.getRightDbl());
            }
            if(advanceInput.getLeftDbl()!=null){
                edit_Ldbl.setText(advanceInput.getLeftDbl());
            }
            if(advanceInput.getLeftFrmLegt()!=null){
                edit_Lframelength.setText(advanceInput.getLeftFrmLegt());
            }
            if(advanceInput.getLeftFrmHigt()!=null){
                edit_Lframeheight.setText(advanceInput.getLeftFrmHigt());
            }
            if(advanceInput.getRightPdz()!=null){
                edit_Rpdz.setText(advanceInput.getRightPdz());
            }
            if(advanceInput.getRightYfh()!=null){
                edit_Ryfh.setText(advanceInput.getRightYfh());
            }

            if(advanceInput.getRightBvd()!=null){
                edit_Rbvd.setText(advanceInput.getRightBvd());
            }
            if(advanceInput.getLeftPdz()!=null){
                edit_Lpdz.setText(advanceInput.getLeftPdz());
            }
            if(advanceInput.getLeftYfh()!=null){
                edit_Lyfh.setText(advanceInput.getLeftYfh());
            }
            if(advanceInput.getLeftBvd()!=null){
                edit_Lbvd.setText(advanceInput.getLeftBvd());
            }
            if(advanceInput.getRightCheck()!=null && advanceInput.getRightCheck().equals("1")) {

                centerRight.setChecked(true);
                rightCheck = "1";

            }else if(advanceInput.getRightCheck()!=null && advanceInput.getRightCheck().equals("0")){
                centerRight.setChecked(false);
                rightCheck = "0";
            }
            if(advanceInput.getLeftCheck()!=null && advanceInput.getLeftCheck().equals("1")) {

                centerLeft.setChecked(true);
                leftCheck = "1";

            }else if(advanceInput.getLeftCheck()!=null && advanceInput.getLeftCheck().equals("0")){
                centerLeft.setChecked(false);
                leftCheck = "0";
            }
        }
            /*Getting side from lensOrder*/
        SharedPreferences preferencesReaderA = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String serializedDataFromLensA=preferencesReaderA.getString(PREFS_KEY,null);
        LensInput lensInput = LensInput.create(serializedDataFromLensA);
        if (lensInput != null) {

            side=lensInput.getSides();
            System.out.println("getting Side in Advance"+side);

        }



        validation();
        listener();

//        edit_framelength.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//              // System.out.println("changed");
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//               // System.out.println("changed");
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().trim().length()!=0){
//                    btn_confirm.setText("orderReview");
//                     button_confirm="toOrderReview";
//                    System.out.println("name is "+button_confirm);
//                }else {
//                     button_confirm="toConfirm";
//                    System.out.println("name is "+button_confirm);
//                }
//            }
//        });

        SharedPreferences pre=getActivity().getSharedPreferences("hello",Context.MODE_PRIVATE);
        int names=pre.getInt("frameType",0);

        List<String> categories1 = new ArrayList<>();
        categories1.add("Metal Frame");
        categories1.add("Plastic Frame");
        categories1.add("Nylor");
        categories1.add("Rimless");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_type.setAdapter(dataAdapter1);
        spinner_type.setSelection(names);

        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(item.equals("Metal Frame")){
                    name="METAL";
                    selectedItem=0;
                }
                else if(item.equals("Plastic Frame")){
                    name="PLASTIC";
                    selectedItem=1;
                }

                else if(item.equals("Nylor")){
                    name="NYLOR";
                    selectedItem=2;
                }
                else {
                    name="DRILLED";
                    selectedItem=3;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

                                               /*Validation and Ranges displaying*/
    private void validation() {

        pAngle.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_individual, R.string.values_panto,pAngle,pAngle, -20.0,30.0,errorMsgIndiviual,R.string.panto_notinrange));
        bAngle.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_individual, R.string.values_panto,bAngle,bAngle, -20.0,30.0,errorMsgIndiviual,R.string.bow_notinrange));

        pAngle.setOnEditorActionListener(new Validation1(getContext(),pAngle,bAngle,pAngle,-20.0,30.0,errorMsgIndiviual,R.string.panto_notinrange));
        bAngle.setOnEditorActionListener(new Validation1(getContext(),bAngle,edit_framelength,bAngle,-20.0,30.0,errorMsgIndiviual,R.string.bow_notinrange));
       if(side.equals("right")) {


                                                     /*Validation for only Right*/

            edit_framelength.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_framelength,edit_framelength,edit_framelength, 30.0,80.0,errorMsgFrame,R.string.framelength_notinrange));
            edit_frameheight.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_frameheight,edit_frameheight,edit_frameheight, 15.0,65.0,errorMsgFrame,R.string.frameHeight_notinrange));
            edit_dbl.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_dbl,edit_dbl,edit_dbl, 0.1,40.0,errorMsgFrame,R.string.dbl_notinrange));
            edit_Rpdz.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Rpdz,edit_Rpdz, 0.0,50.0,errorMsgCentrat,R.string.pdz_notinrange));
            edit_Ryfh.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Ryfh,edit_Ryfh, 0.0,50.0,errorMsgCentrat,R.string.yfh_notinrange));
            edit_Rbvd.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Rbvd,edit_Rbvd, 0.0,50.0,errorMsgCentrat,R.string.bvd_notinrange));


            edit_framelength.setOnEditorActionListener(new Validation1(getContext(), edit_framelength, edit_frameheight, edit_framelength, 30.0, 80.0,errorMsgFrame,R.string.framelength_notinrange));
            edit_frameheight.setOnEditorActionListener(new Validation1(getContext(), edit_frameheight, edit_dbl, edit_frameheight, 15.0, 65.0,errorMsgFrame,R.string.frameHeight_notinrange));
            edit_dbl.setOnEditorActionListener(new Validation1(getContext(), edit_dbl, edit_Rpdz, edit_dbl, 0.1, 40.0,errorMsgFrame,R.string.dbl_notinrange));
            edit_Rpdz.setOnEditorActionListener(new Validation1(getContext(), edit_Rpdz, edit_Ryfh, edit_Rpdz, 0.0, 50.0,errorMsgCentrat,R.string.pdz_notinrange));
            edit_Ryfh.setOnEditorActionListener(new Validation1(getContext(), edit_Ryfh, edit_Rbvd, edit_Ryfh, 0.0, 50.0,errorMsgCentrat,R.string.yfh_notinrange));
            edit_Rbvd.setOnEditorActionListener(new Validation1(getContext(), edit_Rbvd, edit_Rbvd, edit_Rbvd, 0.0, 50.0,errorMsgCentrat,R.string.bvd_notinrange));


            edit_Lframelength.getText().clear();
            edit_Lframeheight.getText().clear();
            edit_Ldbl.getText().clear();
            edit_Lpdz.getText().clear();
            edit_Lyfh.getText().clear();
            edit_Lbvd.getText().clear();

            edit_Lframelength.setEnabled(false);
            edit_Lframelength.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Lframeheight.setEnabled(false);
            edit_Lframeheight.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Ldbl.setEnabled(false);
            edit_Ldbl.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Lpdz.setEnabled(false);
            edit_Lpdz.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Lyfh.setEnabled(false);
            edit_Lyfh.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Lbvd.setEnabled(false);
            edit_Lbvd.setBackground(getResources().getDrawable(R.drawable.unwanted));

            centerLeft.setChecked(false);
            centerLeft.setEnabled(false);
        }else if(side.equals("left")){


            /*Vaidation for only Left*/
            edit_Lframelength.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_framelength,edit_Lframelength,edit_Lframelength, 30.0,80.0,errorMsgFrame,R.string.framelength_notinrange));
            edit_Lframeheight.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_frameheight,edit_Lframeheight,edit_Lframeheight, 15.0,65.0,errorMsgFrame,R.string.frameHeight_notinrange));
            edit_Ldbl.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_dbl,edit_Ldbl,edit_Ldbl, 0.1,40.0,errorMsgFrame,R.string.dbl_notinrange));
            edit_Lpdz.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Lpdz,edit_Lpdz, 0.0,50.0,errorMsgCentrat,R.string.pdz_notinrange));
            edit_Lyfh.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Lyfh,edit_Lyfh, 0.0,50.0,errorMsgCentrat,R.string.yfh_notinrange));
            edit_Lbvd.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Lbvd,edit_Lbvd, 0.0,50.0,errorMsgCentrat,R.string.bvd_notinrange));

            edit_Lframelength.setOnEditorActionListener(new Validation1(getContext(),edit_Lframeheight,edit_Ldbl,edit_Lframeheight,15.0,65.0,errorMsgFrame,R.string.frameHeight_notinrange));
            edit_Ldbl.setOnEditorActionListener(new Validation1(getContext(),edit_Ldbl,edit_Lpdz,edit_Ldbl,0.1,40.0,errorMsgFrame,R.string.dbl_notinrange));
            edit_Lpdz.setOnEditorActionListener(new Validation1(getContext(),edit_Lpdz,edit_Lyfh,edit_Lpdz,0.0,50.0,errorMsgFrame,R.string.pdz_notinrange));
            edit_Lyfh.setOnEditorActionListener(new Validation1(getContext(),edit_Lyfh,edit_Lbvd,edit_Lyfh,0.0,50.0,errorMsgFrame,R.string.yfh_notinrange));
            edit_Lbvd.setOnEditorActionListener(new Validation1(getContext(),edit_Lbvd,edit_Lbvd,edit_Lbvd,0.0,50.0,errorMsgFrame,R.string.bvd_notinrange));

            edit_framelength.getText().clear();
            edit_frameheight.getText().clear();
            edit_dbl.getText().clear();
            edit_Rpdz.getText().clear();
            edit_Ryfh.getText().clear();
            edit_Rbvd.getText().clear();

            edit_framelength.setEnabled(false);
            edit_framelength.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_frameheight.setEnabled(false);
            edit_frameheight.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_dbl.setEnabled(false);
            edit_dbl.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Rpdz.setEnabled(false);
            edit_Rpdz.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Ryfh.setEnabled(false);
            edit_Ryfh.setBackground(getResources().getDrawable(R.drawable.unwanted));
            edit_Rbvd.setEnabled(false);
            edit_Rbvd.setBackground(getResources().getDrawable(R.drawable.unwanted));
            centerRight.setChecked(false);
            centerRight.setEnabled(false);
        }



        else {

            /*Validation for Both*/
            edit_framelength.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_framelength,edit_framelength,edit_Lframelength, 30.0,80.0,errorMsgFrame,R.string.framelength_notinrange));
            edit_frameheight.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_frameheight,edit_frameheight,edit_Lframeheight, 15.0,65.0,errorMsgFrame,R.string.frameHeight_notinrange));
            edit_dbl.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_frame, R.string.values_dbl,edit_dbl,edit_Ldbl, 0.1,40.0,errorMsgFrame,R.string.dbl_notinrange));
            edit_Rpdz.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Rpdz,edit_Lpdz, 0.0,50.0,errorMsgCentrat,R.string.pdz_notinrange));
            edit_Ryfh.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Ryfh,edit_Lyfh, 0.0,50.0,errorMsgCentrat,R.string.yfh_notinrange));
            edit_Rbvd.setOnFocusChangeListener(new RangesDisplay1(getContext(),values_centration, R.string.values_pdz_yfh,edit_Rbvd,edit_Lbvd, 0.0,50.0,errorMsgCentrat,R.string.bvd_notinrange));

            edit_framelength.setOnEditorActionListener(new Validation1(getContext(), edit_framelength, edit_frameheight, edit_Lframelength, 30.0, 80.0,errorMsgFrame,R.string.framelength_notinrange));
            edit_frameheight.setOnEditorActionListener(new Validation1(getContext(), edit_frameheight, edit_dbl, edit_Lframeheight, 15.0, 65.0,errorMsgFrame,R.string.frameHeight_notinrange));
            edit_dbl.setOnEditorActionListener(new Validation1(getContext(), edit_dbl, edit_Rpdz, edit_Ldbl, 0.1, 40.0,errorMsgFrame,R.string.dbl_notinrange));
            edit_Rpdz.setOnEditorActionListener(new Validation1(getContext(), edit_Rpdz, edit_Ryfh, edit_Lpdz, 0.0, 50.0,errorMsgFrame,R.string.pdz_notinrange));
            edit_Ryfh.setOnEditorActionListener(new Validation1(getContext(), edit_Ryfh, edit_Rbvd, edit_Lyfh, 0.0, 50.0,errorMsgFrame,R.string.yfh_notinrange));
            edit_Rbvd.setOnEditorActionListener(new Validation1(getContext(), edit_Rbvd, edit_Rbvd, edit_Lbvd, 0.0, 50.0,errorMsgFrame,R.string.bvd_notinrange));
        }


    }

    private void listener() {

        centerRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rightCheck="1";
                }else {
                    rightCheck="0";
                }
            }
        });
        centerLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    leftCheck="1";
                }else {
                    leftCheck="0";
                }
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager=getFragmentManager();

                pAng=pAngle.getText().toString().trim();
                bAng=bAngle.getText().toString().trim();

                rfLength=edit_framelength.getText().toString().trim();
                rfheight=edit_frameheight.getText().toString().trim();
                rDBL=edit_dbl.getText().toString().trim();



                lfLength=edit_Lframelength.getText().toString().trim();
                lfRight=edit_Lframeheight.getText().toString().trim();
                lDBL=edit_Ldbl.getText().toString().trim();


                rRpdz=edit_Rpdz.getText().toString().trim();
                rRyfh=edit_Ryfh.getText().toString().trim();
                rRbvd=edit_Rbvd.getText().toString().trim();

                lRpdz=edit_Lpdz.getText().toString().trim();
                lRyfh=edit_Lyfh.getText().toString().trim();
                lRbvd=edit_Lbvd.getText().toString().trim();

                shape=1;

                advanceInput=new AdvanceInput();
                advanceInput.setPantoAngle(pAng);
                advanceInput.setBowAngle(bAng);
                advanceInput.setRightFrmLegt(rfLength);
                advanceInput.setLeftFrmLegt(lfLength);
                advanceInput.setRightFrmHigt(rfheight);
                advanceInput.setLeftFrmHigt(lfRight);
                advanceInput.setRightDbl(rDBL);
                advanceInput.setLeftDbl(lDBL);
                advanceInput.setRightPdz(rRpdz);
                advanceInput.setLeftPdz(lRpdz);
                advanceInput.setRightYfh(rRyfh);
                advanceInput.setLeftYfh(lRyfh);
                advanceInput.setRightBvd(rRbvd);
                advanceInput.setLeftBvd(lRbvd);
                advanceInput.setFrame(name);
                advanceInput.setRightCheck(rightCheck);
                advanceInput.setLeftCheck(leftCheck);

                System.out.println("rightCheck "+advanceInput.getRightCheck());
                System.out.println("leftCheck "+advanceInput.getLeftCheck());


                System.out.println("pangle "+pAng);
                String serializedData=advanceInput.serialize();
                System.out.println("data from advance"+serializedData);
                SharedPreferences preferencesReader = Objects.requireNonNull(getActivity()).getSharedPreferences("hi", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesReader.edit();
                editor.putString(PREFS_KEY, serializedData);
                editor.putString("id","advance");
                editor.apply();



//                ShapeAndBevelData shapeAndBevelData=new ShapeAndBevelData();
//                shapeAndBevelData.setFrame(name);
//
//                String serializedDataFromNewField=shapeAndBevelData.serialize();
//                SharedPreferences preferencesReaderOne = getActivity().getSharedPreferences("fromShapre", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor0 = preferencesReaderOne.edit();
//                editor0.putString(PREFS_KEY, serializedDataFromNewField);
//                editor0.putString("fromShap","Shape");
//                editor0.apply();




                SharedPreferences pre=getActivity().getSharedPreferences("hello",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1=pre.edit();
                editor1.putInt("frameType",selectedItem);
              //  editor1.putString("radioButton",selectedRadioButton);
//                editor1.putString("radio",Id);
                editor1.apply();

                assert manager != null;
                manager.popBackStack();
                tooltxt.setText(R.string.lensOrder);


            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getFragmentManager();
                manager.popBackStack();
                tooltxt.setText(R.string.lensOrder);

            }
        });
    }

    public void fragmentPlace(Fragment fragment){
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}

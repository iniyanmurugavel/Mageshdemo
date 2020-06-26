package com.example.bcs.visualstore.View.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.bcs.visualstore.PojoDatas.CallBackData;
import com.example.bcs.visualstore.PojoDatas.GetOrder;
import com.example.bcs.visualstore.RangesDisplay;
import com.example.bcs.visualstore.RangesDisplay1;
import com.example.bcs.visualstore.RangesForAxis;
import com.example.bcs.visualstore.Validation;
import com.example.bcs.visualstore.Controll.CallBack;

import com.example.bcs.visualstore.Controll.ApiCalls;

import com.example.bcs.visualstore.PojoDatas.LensInput;
import com.example.bcs.visualstore.Utils.APIService;
import com.example.bcs.visualstore.Validation1;
import com.example.bcs.visualstore.View.Adapter.DemoAdapter;
import com.example.bcs.visualstore.View.Adapter.LensAdapter;
import com.example.bcs.visualstore.View.Adapter.TintAdapter;
import com.example.bcs.visualstore.Utils.ApiUtils;

import com.example.bcs.visualstore.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class   LensOrderingFragment extends Fragment {
    Button order, btn_advance,customerNameAdd,confirm,cancel;
    String consig,portfo;
    TextView single, both;
    TextView values,errorMsg;
    CheckBox check_glazing,customerCheckBox;
    Spinner spinner_lenstype;
    FrameLayout frameLayout;
    EditText firstNm,lastNm;
    String id;
    boolean mCustomerOpen=false;//0=customer data, 1=refraction, 2= lens, 3=treatments
    boolean mRefractionOpen=false;//0=customer data, 1=refraction, 2= lens, 3=treatments
    boolean mLensOpen=false;//0=customer data, 1=refraction, 2= lens, 3=treatments
    boolean mTreatmentsOpen=false;//0=customer data, 1=refraction, 2= lens, 3=treatments
    String rightAddition,leftAddition;
    Spinner portfolio;
    Spinner consignee,cutSpinner;
    LensInput lensInput=new LensInput();
    String PREFS_NAME = "MyPrefName";
    String PREFS_KEY = "MyPrefKey";
    String hard="0";
    int portfolioValues=0;
    int getCoatingCoding=1;
    int glazingValue=0;



    GetOrder getOrder=new GetOrder();
    List<String> consignee_list = new ArrayList<String>();
    List<String> portfolio_list = new ArrayList<String>();
    List<String> rightDiameter=new ArrayList<String>();
    List<String>leftDiameter=new ArrayList<String>();
    EditText edit_sphereR, edit_cylindR, edit_axisR, edit_additionR, edit_sphereL, edit_cylindL, edit_axisL, edit_additionL, edit_orderReference,edit_Rdiameter,edit_Ldiameter;
//    CheckBox cotingCheck;


    // Declaring Global Variables
    Button custData,refraction,lensTypeSelection,treatments;
    LinearLayout custView,consigneeView,refractionView,lensTypeView,treatmentsView,rTypeLnr,lTypeLnr,consumerName;

    View view,dialogView,dialogViewSpiner,coatingDialog;
    Bundle bundle;
    AdvanceOptionFragment fragment;
    CheckBox right,left;
    RadioGroup radio_group;
    TextView leftSpinnerText,rightSpinerText,employeeSpinner,coatingSpinner,tintSpinner;
    PopupWindow popupSpinnerwindow,popupCoatingWindow;
    ImageButton cancelBtn;
    EditText searchText;
    ListView lensTypeListView,coatingAndTintListView;
    RadioButton singleRadio,bothRadio,virtual;

    Spinner rDiameter,lDiameter;

    SharedPreferences prefs;


    //  LensInput lensInput=new LensInput();
    String empName;

    TintAdapter tintAdapter;

    List<CallBackData.Data> zeissZeroeslist=new ArrayList<CallBackData.Data>();
    List<CallBackData.Data> zeissOnesList=new ArrayList<CallBackData.Data>();
    List<CallBackData.Data> synchronidefaultList=new ArrayList<CallBackData.Data>();
    List<CallBackData.Data> synchroniOnceList=new ArrayList<CallBackData.Data>();
    List<CallBackData.Data> employeeNameLists=new ArrayList<CallBackData.Data>();
    List<CallBackData.Data> tintData=new ArrayList<CallBackData.Data>();
    List<CallBackData.Data> coatingLists=new ArrayList<CallBackData.Data>();


    String lensType_name,lens_code,lensTint_code,radioStringName="both",coatingCode,coatingName,tine_code,tine_name,individual;
    LensAdapter adapter;
    Switch switch1;
    DemoAdapter demoAdapter;
    ApiCalls apiCalls=new ApiCalls();
    String coatingType,switch_value="ON";
    String virtualSide;
    ImageView backBtn;
    private boolean isAdvanceFragmentTransction =false;
    private boolean isShapeAndBevels =false;

    String rdia,ldia;
    public static int selectedItem = 0;
    public static int selectItemIndex=0;


    String[] splited;
    String str;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // container.removeAllViews();
        view = (View) inflater.inflate(R.layout.fragment_lens_ordering, container, false);
        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        tooltxt.setText(R.string.lensOrder);

        backBtn=(ImageView) toolbar.findViewById(R.id.menuLogo);
        backBtn.setImageDrawable(getResources().getDrawable(R.drawable.backbtn));


        LayoutInflater inflaterConsumer = getLayoutInflater();
        LayoutInflater inflaterSpinner = getLayoutInflater();
        LayoutInflater inflaterCoating=getLayoutInflater();


        dialogView = inflaterConsumer.inflate(R.layout.popup_layout,null);
        dialogViewSpiner=inflaterSpinner.inflate(R.layout.rightspinnerserachlist,null);
        coatingDialog=inflaterCoating.inflate(R.layout.coatingandtintlistvie,null);


        lensTypeListView=(ListView)dialogViewSpiner.findViewById(R.id.lenTypeList);
        coatingAndTintListView=(ListView)coatingDialog.findViewById(R.id.coatingListView);


        cancelBtn=(ImageButton)dialogViewSpiner.findViewById(R.id.cancel);

        searchText=(EditText)dialogViewSpiner.findViewById(R.id.searchList);

        fragment= new AdvanceOptionFragment();

        frameLayout = (FrameLayout) view.findViewById(R.id.fragment_Container);
        //check_glazing = (CheckBox) view.findViewById(R.id.checkbox_glazing);


        order = (Button) view.findViewById(R.id.order);
        btn_advance = (Button) view.findViewById(R.id.btn_advanceOption);
        edit_orderReference = (EditText) view.findViewById(R.id.edit_orderReference);
//        cotingCheck = (CheckBox) view.findViewById(R.id.cotingCheck);

        rTypeLnr=(LinearLayout)view.findViewById(R.id.rtype_lnr);
        lTypeLnr=(LinearLayout)view.findViewById(R.id.ltype_lnr);
        consumerName=(LinearLayout)view.findViewById(R.id.consumer_name);


        // Changed Design 19/11/2018
        custData=(Button)view.findViewById(R.id.custData);
        refraction=(Button)view.findViewById(R.id.refraction);
        lensTypeSelection=(Button)view.findViewById(R.id.lensTypeSelection);
        treatments=(Button)view.findViewById(R.id.treatments);

        custView=(LinearLayout)view.findViewById(R.id.customer_view);
        treatmentsView=(LinearLayout)view.findViewById(R.id.treatments_view);
        consigneeView=(LinearLayout)view.findViewById(R.id.consignee_view);
        consigneeView=(LinearLayout)view.findViewById(R.id.consignee_view);
        refractionView=(LinearLayout)view.findViewById(R.id.refraction_view);
        lensTypeView=(LinearLayout)view.findViewById(R.id.lens_view);

        confirm=(Button)view.findViewById(R.id.confirm);
        cancel=(Button)view.findViewById(R.id.cancel);
        customerCheckBox=(CheckBox) view.findViewById(R.id.customer_checkBox);
        firstNm=(EditText)view.findViewById(R.id.firstName);
        lastNm=(EditText)view.findViewById(R.id.lastName);


        switch1=(Switch)view.findViewById(R.id.switch1);


             /*Switch Enable set........*/
        switch1.setEnabled(false);
        order=(Button)view.findViewById(R.id.order);

        btn_advance=(Button)view.findViewById(R.id.btn_advanceOption);
        customerNameAdd=(Button)view.findViewById(R.id.consumer_card);
//        cotingCheck=(CheckBox)view.findViewById(R.id.cotingCheck);

        coatingSpinner =(TextView)view.findViewById(R.id.coating);
        employeeSpinner=(TextView)view.findViewById(R.id.spinner2);

        single=(TextView)view.findViewById(R.id.single_text);
        both=(TextView)view.findViewById(R.id.both);

        right = (CheckBox) view.findViewById(R.id.right_checkbox);
        left = (CheckBox) view.findViewById(R.id.left_check_box);

        edit_sphereR=(EditText)view.findViewById(R.id.edit_sphere_r);
        edit_cylindR=(EditText)view.findViewById(R.id.edit_cylind_r);
        edit_axisR=(EditText)view.findViewById(R.id.edit_axis_r);
        edit_additionR=(EditText)view.findViewById(R.id.edit_addition_r);

        edit_sphereL=(EditText)view.findViewById(R.id.edit_sphere_l);
        edit_axisL=(EditText)view.findViewById(R.id.edit_axis_l);
        edit_cylindL=(EditText)view.findViewById(R.id.edit_cylind_l);
        edit_additionL=(EditText)view.findViewById(R.id.edit_addition_l);


        edit_sphereR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
        edit_cylindR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
        edit_axisR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
        edit_additionR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));



        edit_sphereL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
        edit_cylindL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
        edit_axisL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
        edit_additionL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));

        values = (TextView) view.findViewById(R.id.values);
        errorMsg=(TextView) view.findViewById(R.id.error);
        frameLayout=(FrameLayout)view.findViewById(R.id.fragment_Container);
        portfolio = (Spinner) view.findViewById(R.id.portfolio);
        consignee = (Spinner) view.findViewById(R.id.consignee);
        rightSpinerText = (TextView) view.findViewById(R.id.LenType_1);
        leftSpinnerText = (TextView) view.findViewById(R.id.LenType_2);
        tintSpinner= (TextView) view.findViewById(R.id.tint);
        order=(Button)view.findViewById(R.id.order);
        btn_advance=(Button)view.findViewById(R.id.btn_advanceOption);
        single=(TextView)view.findViewById(R.id.single_text);
        both=(TextView)view.findViewById(R.id.both);
        virtual=(RadioButton)view.findViewById(R.id.radio3);
        radio_group = (RadioGroup) view.findViewById(R.id.radio_group);
        cutSpinner = (Spinner) view.findViewById(R.id.spinner9);
        spinner_lenstype = (Spinner) view.findViewById(R.id.spinner1);

        rDiameter=(Spinner) view.findViewById(R.id.edit_Rdiameter);
        lDiameter=(Spinner) view.findViewById(R.id.edit_Ldiameter);

        singleRadio=(RadioButton)view.findViewById(R.id.singleRadio);
        bothRadio=(RadioButton)view.findViewById(R.id.radio2);



        updateOtherData();
        radiogroup();
        uncut();
        portfolio();
        consignee();
        listnerer();
        spinnerListener();
        isChecked(radioStringName);


        return view;

    }


    private void updateOtherData() {

        onClear();
        SharedPreferences preferencesReader = getActivity().getSharedPreferences("hione", Context.MODE_PRIVATE);
        String serializedDataFromLens=preferencesReader.getString(PREFS_KEY,"");
        GetOrder getOrder=GetOrder.create(serializedDataFromLens);

        if (getOrder!=null){

            Log.e("inside get","success");

            edit_orderReference.setText(getOrder.getOrder_reference());
            empName=getOrder.getEmployee_name();
            employeeSpinner.setText(getOrder.getEmployee_name());
            System.out.println("Emp"+getOrder.getEmployee_name());

            firstNm.setText(getOrder.getPatient_firstName());
            lastNm.setText(getOrder.getPatient_lastName());
            String patient_term=getOrder.getPatient_term();
            if(patient_term!=null && patient_term.equals("1")) {
                customerCheckBox.setChecked(true);
                confirm.setEnabled(true);
            }else {
                customerCheckBox.setChecked(false);
            }

            if(getOrder.getPortfolio()!=null) {
                portfolioValues = Integer.parseInt(getOrder.getPortfolio());
            }
            String radioSides=getOrder.getSide();
            if(radioSides!=null && radioSides.equals("1")){
                singleRadio.setChecked(true);
            }else if(radioSides!=null && radioSides.equals("2")){
                bothRadio.setChecked(true);

            }else if(radioSides!=null && radioSides.equals("3")){
                virtual.setChecked(true);
            }else {
                System.out.println("Null");
            }

            String lens_lr=getOrder.getLens_lr_lens();
            String sideIs=getOrder.getSide();
            System.out.println("side is "+sideIs);
//                        Log.e("portifio",getOrder.getPortfolio());



            if (sideIs!=null && sideIs.equals("1")){

                if(lens_lr!=null && lens_lr.equals("R")){
                    radioStringName="right";
                    right.setChecked(true);
                    left.setChecked(false);

                    right.setEnabled(true);
//                                left.setEnabled(true);

                    checkedFunction(sideIs);

                    System.out.println("side is "+radioStringName);

                    edit_sphereR.setText(getOrder.getLens_r_sphere());
                    edit_cylindR.setText(getOrder.getLens_r_power());
                    edit_axisR.setText(getOrder.getLens_r_axis());
                    edit_additionR.setText(getOrder.getLens_r_addition());



                    String code=getOrder.getLens_r_commercialCode();

                    Log.e("code is right  ",code);
                    //getCoating(code);
                    if (code.equals("")){

                    }else {
                        coatingLists.clear();
                        getCoating(code);

                    }

                    rightSpinerText.setText(getOrder.getLens_r_commercialName());

                    lensType_name=getOrder.getLens_r_commercialName();
                    lens_code=getOrder.getLens_r_commercialCode();

                    rTypeLnr.setVisibility(View.VISIBLE);
                    lTypeLnr.setVisibility(View.GONE);
                    leftSpinnerText.setText(" ");
                    leftSpinnerText.setEnabled(false);


                    System.out.println("dia"+getOrder.getLens_r_dia());
                    rdia=getOrder.getLens_r_dia();

                    System.out.println("Right");
                    fvs(radioStringName);
                    getDiameterFrmEdit(rdia,ldia,radioStringName,lensType_name);
                    //getDiameterFrmEdit(rdia,radioStringName);

                }else if(lens_lr!=null && lens_lr.equals("L")){

                    radioStringName="left";

                    left.setChecked(true);
                    right.setChecked(false);

                    // right.setEnabled(true);
                    left.setEnabled(true);
                    checkedFunction(sideIs);

                    System.out.println("side is "+radioStringName);
                    leftSpinnerText.setText(getOrder.getLens_l_commercialName());

                    lensType_name=getOrder.getLens_l_commercialName();
                    lens_code=getOrder.getLens_l_commercialCode();

                    String code=getOrder.getLens_l_commercialCode();
                    getCoating(code);

                    //  getCoating(code);
                    if (code.equals("")){

                    }else {
                        coatingLists.clear();
                        getCoating(code);

                    }

                    Log.e("code is left ",code);

                    rTypeLnr.setVisibility(View.GONE);
                    lTypeLnr.setVisibility(View.VISIBLE);
                    rightSpinerText.setText(" ");
                    rightSpinerText.setEnabled(false);
                    System.out.println("left");
                    edit_sphereL.setText(getOrder.getLens_l_sphere());
                    edit_cylindL.setText(getOrder.getLens_l_power());
                    edit_axisL.setText(getOrder.getLens_l_axis());
                    edit_additionL.setText(getOrder.getLens_l_addition());
                    ldia=getOrder.getLens_r_dia();

                  //  edit_Ldiameter.setText(getOrder.getLens_l_dia());
                    fvs(radioStringName);
                    getDiameterFrmEdit(rdia,ldia,radioStringName,lensType_name);
                 //  getDiameterFrmEdit(rdia,radioStringName);
                }

            } else if (sideIs!=null && sideIs.equals("2")){

                if(lens_lr!=null && lens_lr.equals("R,L")) {
                    radioStringName = "both";
                    right.setChecked(true);
                    left.setChecked(true);
//                                right.setEnabled(true);
//                                left.setEnabled(true);

                    System.out.println("side is "+radioStringName);

                    edit_sphereR.setText(getOrder.getLens_r_sphere());
                    edit_cylindR.setText(getOrder.getLens_r_power());
                    edit_axisR.setText(getOrder.getLens_r_axis());
                    edit_additionR.setText(getOrder.getLens_r_addition());

                    edit_sphereL.setText(getOrder.getLens_l_sphere());
                    edit_cylindL.setText(getOrder.getLens_l_power());
                    edit_axisL.setText(getOrder.getLens_l_axis());
                    edit_additionL.setText(getOrder.getLens_l_addition());


                    String code = getOrder.getLens_r_commercialCode();
                    Log.e("code is both ", code);
                    // getCoating(code);
                    if (code.equals("")){

                    }else {
                        getCoating(code);
                        coatingLists.clear();
                    }


                    rightSpinerText.setText(getOrder.getLens_r_commercialName());
                    leftSpinnerText.setText(getOrder.getLens_r_commercialName());
                    lensType_name = getOrder.getLens_r_commercialName();
                    lens_code=getOrder.getLens_r_commercialCode();

                    rdia=getOrder.getLens_r_dia();
                    ldia=getOrder.getLens_l_dia();
                    System.out.println("rightDia"+rdia);
                    System.out.println("leftDia"+ldia);
                    System.out.println("Both");


                    /*fvs adding*/
                    fvs(radioStringName);

                    getDiameterFrmEdit(rdia,ldia,radioStringName,lensType_name);


                }

            }else if (sideIs!=null && sideIs.equals("3")){
                radioStringName="virtual";

                if(lens_lr!=null && lens_lr.equals("R")){
                    virtualSide="virtualRight";
                    // radioStringName="right";
                    right.setChecked(true);
                    left.setChecked(false);

                    right.setEnabled(true);
                    left.setEnabled(true);

                    edit_sphereR.setText(getOrder.getLens_r_sphere());
                    edit_cylindR.setText(getOrder.getLens_r_power());
                    edit_axisR.setText(getOrder.getLens_r_axis());
                    edit_additionR.setText(getOrder.getLens_r_addition());
                   // edit_Rdiameter.setText(getOrder.getLens_r_dia());

                    edit_sphereL.setText(getOrder.getLens_l_sphere());
                    edit_cylindL.setText(getOrder.getLens_l_power());
                    edit_axisL.setText(getOrder.getLens_l_axis());
                  //  edit_additionL.setText(getOrder.getLens_l_addition());



                    String code=getOrder.getLens_r_commercialCode();

                    // getCoating(code);

                    if (code.equals("")){

                    }else {
                        getCoating(code);
                        coatingLists.clear();
                    }

                    System.out.println(getOrder.getLens_r_commercialName());
                    rightSpinerText.setText(getOrder.getLens_r_commercialName());
                    lensType_name=getOrder.getLens_r_commercialName();
                    lens_code=getOrder.getLens_r_commercialCode();

                    rTypeLnr.setVisibility(View.VISIBLE);
                    lTypeLnr.setVisibility(View.GONE);
                    leftSpinnerText.setText(" ");
                    leftSpinnerText.setEnabled(false);

                    System.out.println("Right");

                }else if(lens_lr!=null && lens_lr.equals("L")){
                    //   radioStringName="left";

                    virtualSide="virtualLeft";
                    right.setChecked(false);
                    left.setChecked(true);
                    right.setEnabled(true);
                    left.setEnabled(true);
                    leftSpinnerText.setText(getOrder.getLens_l_commercialName());
                    lensType_name=getOrder.getLens_l_commercialName();
                    lens_code=getOrder.getLens_l_commercialCode();

                    String code=getOrder.getLens_l_commercialCode();
                    getCoating(code);

                    if (code.equals("")){

                    }else {
                        getCoating(code);
                        coatingLists.clear();
                    }

                    //  getCoating(code);

                    Log.e("code is left ",code);

                    rTypeLnr.setVisibility(View.GONE);
                    lTypeLnr.setVisibility(View.VISIBLE);
                    rightSpinerText.setText(" ");
                    rightSpinerText.setEnabled(false);
                    System.out.println("left");

                    edit_sphereR.setText(getOrder.getLens_r_sphere());
                    edit_cylindR.setText(getOrder.getLens_r_power());
                    edit_axisR.setText(getOrder.getLens_r_axis());
                    edit_additionR.setText(getOrder.getLens_r_addition());
                   // edit_Rdiameter.setText(getOrder.getLens_r_dia());

                    edit_sphereL.setText(getOrder.getLens_l_sphere());
                    edit_cylindL.setText(getOrder.getLens_l_power());
                    edit_axisL.setText(getOrder.getLens_l_axis());
                    edit_additionL.setText(getOrder.getLens_l_addition());

//                                if(getOrder.getPortfolio()!=null) {
//                                    portfolioValues = Integer.parseInt(getOrder.getPortfolio());
//                                }
//                                portfolio.setSelection(portfolioValues);

                }

                System.out.println("virtual"+sideIs);

                edit_sphereR.setText(getOrder.getLens_r_sphere());
                edit_cylindR.setText(getOrder.getLens_r_power());
                edit_axisR.setText(getOrder.getLens_r_axis());
                edit_additionR.setText(getOrder.getLens_r_addition());
               // edit_Rdiameter.setText(getOrder.getLens_r_dia());
                edit_sphereL.setText(getOrder.getLens_l_sphere());
                edit_cylindL.setText(getOrder.getLens_l_power());
                edit_axisL.setText(getOrder.getLens_l_axis());
                edit_additionL.setText(getOrder.getLens_l_addition());
             //   edit_Ldiameter.setText(getOrder.getLens_l_dia());

            }

            coatingName=getOrder.getCoating_commercialCodeName();
            coatingType=getOrder.getCoating_commercialType();

            coatingSpinner.setText(coatingName);
            coatingCode=getOrder.getCoating_commercialCode();

            tine_code=getOrder.getCoating_commercialTint();
            tine_name=getOrder.getCoating_commercialTintName();

            if (getOrder.getCoating_commercialTintName().equals("null")){
                tintSpinner.setText("select");
            }else {
                tintSpinner.setText(getOrder.getCoating_commercialTintName());
            }

            System.out.println(getOrder.getCoating_commercialCode());
            System.out.println(getOrder.getCoating_commercialTintName());
            String checkHard=getOrder.getLens_hard();

            System.out.println("lens name is "+lensType_name);
            tintFromEdit(lensType_name);

//            if(checkHard!=null && checkHard.equals("0")){
//                cotingCheck.setChecked(false);
//            }else if(checkHard!=null && checkHard.equals("1")){
//                cotingCheck.setChecked(true);
//            }
            System.out.println("coatingcode"+getOrder.getCoating_commercialCode());

            if(getOrder.getFrm_model()!=null && !getOrder.getFrm_model().equals("")) {
                String shapeData = getOrder.getFrm_material();
                System.out.println("Shape datas"+shapeData);
            //    check_glazing.setChecked(true);
             //   check_glazing.setEnabled(false);
                glazingValue=1;

                order.setText("Shape & Bevel");


            }else {

            }
        }

        //    }

    }


    public void uncut() {

       final List<String> categories = new ArrayList<String>();
        categories.add("Uncut");
        categories.add("Glazing");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cutSpinner.setAdapter(dataAdapter);
        cutSpinner.setSelection(glazingValue);
        cutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String orderType=categories.get(position);
                System.out.println("OrderType"+orderType);
                if(orderType.equals("Glazing")){
                    order.setText("Shape & Bevel");
                    order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Fragment fragment = new ShapeAndBevelFragment();
                            isShapeAndBevels=true;
                            mainValidation(fragment);
                        }
                    });
                } else {
                    order.setText("Order Review");
                    order.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Fragment fragment=new OrderReviewFragment();
                            mainValidation(fragment);
                        }
                    });
                }

            }
            public void onNothingSelected(AdapterView<?> arg0) {


            }
        });
    }


    private void listnerer() {

        /*When on click on Consumer card button the view will Visible*/
        customerNameAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                consumerName.setVisibility(View.VISIBLE);

                customerNameAdd.setBackgroundResource(R.drawable.button_onclick);
                customerNameAdd.setTextColor(getResources().getColor(R.color.txt_color));

                hideKeyboard(firstNm);


            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switch1.isChecked()){
                    switch_value="ON";
                    Log.e("switch",""+switch_value);
                }else{
                    switch_value="OFF";
                    Log.e("switch",""+switch_value);
                }
            }
        });

        custData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mRefractionOpen){
                    consigneeView .setVisibility(View.GONE);
                    custView.setVisibility(View.GONE);
                    mRefractionOpen =false;
                    custData.setBackgroundResource(R.drawable.buttomshapeup);
                    custData.setTextColor(getResources().getColor(R.color.button_text));
                    customerNameAdd.setBackgroundResource(R.drawable.buttomshapeup);
                    customerNameAdd.setTextColor(getResources().getColor(R.color.button_text));
// edit_orderReference.requestFocus();
// edit_orderReference.setFocusableInTouchMode(true);
//                    InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
//                    assert imm != null;
//                    imm.hideSoftInputFromWindow(edit_orderReference.getWindowToken(), 0);

                    showKeyboard(edit_orderReference);
                }
                else {
                    consigneeView.setVisibility(View.VISIBLE);
                    custView.setVisibility(View.VISIBLE);
                    mRefractionOpen = true;
                    mCustomerOpen = false;
                    mTreatmentsOpen=false;
                    mLensOpen=false;
                    custData.setBackgroundResource(R.drawable.button_onclick);
                    custData.setTextColor(getResources().getColor(R.color.txt_color));
                    refraction.setTextColor(getResources().getColor(R.color.button_text));
                    lensTypeSelection.setTextColor(getResources().getColor(R.color.button_text));
                    treatments.setTextColor(getResources().getColor(R.color.button_text));

                    refraction.setBackgroundResource(R.drawable.buttomshapeup);
                    lensTypeSelection.setBackgroundResource(R.drawable.buttomshapeup);
                    treatments.setBackgroundResource(R.drawable.buttomshapeup);

                    hideKeyboard(edit_orderReference);
//                    edit_orderReference.requestFocus();
//                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(edit_orderReference, InputMethodManager.SHOW_FORCED);


                }


//                        consigneeView.setVisibility(View.VISIBLE);
                refractionView.setVisibility(View.GONE);
                lensTypeView.setVisibility(View.GONE);
//                        custView.setVisibility(View.VISIBLE);
                treatmentsView.setVisibility(View.GONE);
                consumerName.setVisibility(View.GONE);

            }
        });

        customerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String firstName=firstNm.getText().toString().trim();
                    String lastNamme=lastNm.getText().toString().trim();

                    if ((TextUtils.isEmpty(firstName)) || (TextUtils.isEmpty(lastNamme) )){

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setMessage("First and Last name need ");
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                        confirm.setEnabled(false);
                        customerCheckBox.setChecked(false);

                    }else {

                        customerCheckBox.setChecked(true);
                        confirm.setEnabled(true);
                    }

                }  else {
                    confirm.setEnabled(true);
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstNm.getText().clear();
                lastNm.getText().clear();

                consigneeView.setVisibility(View.VISIBLE);
                consumerName.setVisibility(View.GONE);

                customerCheckBox.setChecked(false);

                customerNameAdd.setBackgroundResource(R.drawable.buttomshapeup);
                customerNameAdd.setTextColor(getResources().getColor(R.color.button_text));

            }

        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customerCheckBox.isChecked()){

                    consigneeView.setVisibility(View.VISIBLE);
                    consumerName.setVisibility(View.GONE);

                    customerNameAdd.setBackgroundResource(R.drawable.buttomshapeup);
                    customerNameAdd.setTextColor(getResources().getColor(R.color.button_text));

                    showKeyboard(firstNm);

                }

            }
        });

        refraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showKeyboard(edit_sphereR);

                //  refraction.setBackground(getResources().getDrawable(R.drawable.buttomshapeup));
                refraction.setTextColor(getResources().getColor(R.color.onclick));

                custView.setVisibility(View.GONE);
                consigneeView.setVisibility(View.GONE);
                consumerName.setVisibility(View.GONE);
                lensTypeView.setVisibility(View.GONE);
                treatmentsView.setVisibility(View.GONE);
                if(mCustomerOpen){
                    refractionView.setVisibility(View.GONE);
                    mCustomerOpen =false;
                    refraction.setBackgroundResource(R.drawable.buttomshapeup);
                    refraction.setTextColor(getResources().getColor(R.color.button_text));
                    showKeyboard(edit_sphereR);

                }
                else {
                    refractionView.setVisibility(View.VISIBLE);
                    mCustomerOpen = true;
                    mRefractionOpen=false;
                    mTreatmentsOpen=false;
                    mLensOpen=false;
                    refraction.setBackgroundResource(R.drawable. button_onclick);
                    refraction.setTextColor(getResources().getColor(R.color.txt_color));
                    custData.setTextColor(getResources().getColor(R.color.button_text));
                    lensTypeSelection.setTextColor(getResources().getColor(R.color.button_text));
                    treatments.setTextColor(getResources().getColor(R.color.button_text));

                    custData.setBackgroundResource(R.drawable.buttomshapeup);
                    lensTypeSelection.setBackgroundResource(R.drawable.buttomshapeup);
                    treatments.setBackgroundResource(R.drawable.buttomshapeup);
                     hideKeyboard(edit_sphereR);
                }



            }
        });
        lensTypeSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                custView.setVisibility(View.GONE);
                consigneeView.setVisibility(View.GONE);
                refractionView.setVisibility(View.GONE);

                if(mLensOpen){
                    lensTypeView.setVisibility(View.GONE);
                    mLensOpen =false;
                    lensTypeSelection.setBackgroundResource(R.drawable.buttomshapeup);
                    lensTypeSelection.setTextColor(getResources().getColor(R.color.button_text));

                }
                else {
                    lensTypeView.setVisibility(View.VISIBLE);
                    mLensOpen = true;
                    mCustomerOpen = false;
                    mRefractionOpen=false;
                    mTreatmentsOpen=false;

                    lensTypeSelection.setBackgroundResource(R.drawable.button_onclick);
                    lensTypeSelection.setTextColor(getResources().getColor(R.color.txt_color));
                    custData.setTextColor(getResources().getColor(R.color.button_text));
                    refraction.setTextColor(getResources().getColor(R.color.button_text));
                    treatments.setTextColor(getResources().getColor(R.color.button_text));


                    custData.setBackgroundResource(R.drawable.buttomshapeup);
                    refraction.setBackgroundResource(R.drawable.buttomshapeup);
                    treatments.setBackgroundResource(R.drawable.buttomshapeup);

                    hideKeyboardView(view);


                }
//                        lensTypeView.setVisibility(View.VISIBLE);
                treatmentsView.setVisibility(View.GONE);
            }
        });
        treatments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                custView.setVisibility(View.GONE);
                consigneeView.setVisibility(View.GONE);
                refractionView.setVisibility(View.GONE);
                lensTypeView.setVisibility(View.GONE);
                consumerName.setVisibility(View.GONE);

                if(mTreatmentsOpen){
                    treatmentsView.setVisibility(View.GONE);
                    mTreatmentsOpen =false;
                    treatments.setBackgroundResource(R.drawable.buttomshapeup);
                    treatments.setTextColor(getResources().getColor(R.color.button_text));

                }
                else {
                    treatmentsView.setVisibility(View.VISIBLE);
                    mTreatmentsOpen = true;
                    mCustomerOpen = false;
                    mRefractionOpen=false;

                    mLensOpen=false;
                    treatments.setBackgroundResource(R.drawable.button_onclick);
                    treatments.setTextColor(getResources().getColor(R.color.txt_color));
                    custData.setTextColor(getResources().getColor(R.color.button_text));
                    lensTypeSelection.setTextColor(getResources().getColor(R.color.button_text));
                    refraction.setTextColor(getResources().getColor(R.color.button_text));

                    custData.setBackgroundResource(R.drawable.buttomshapeup);
                    lensTypeSelection.setBackgroundResource(R.drawable.buttomshapeup);
                    refraction.setBackgroundResource(R.drawable.buttomshapeup);
                }
//                        treatmentsView.setVisibility(View.VISIBLE);
            }
        });

        btn_advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment=new AdvanceOptionFragment();
                isAdvanceFragmentTransction =true;
                mainValidation(fragment);

            }
        });


        if(order.getText().toString().equals("Order Review")) {
            order.setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    Fragment fragment = new OrderReviewFragment();
                    mainValidation(fragment);
                }
            });
        }else {
            order.setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    Fragment fragment = new ShapeAndBevelFragment();
                    isShapeAndBevels=true;
                    mainValidation(fragment);
                }
            });
        }

    }

    private void radiogroup() {

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int i) {

                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.singleRadio) {
                    //disable();
                    right.setChecked(false);
                    left.setChecked(false);
                    right.setEnabled(true);
                    left.setEnabled(true);

                    clearAll();
                    enablefalseBoth();
                    right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (right.isChecked() && !left.isChecked()) {
                                radioStringName = "right";
                                Log.e("this is right ..",""+radioStringName);
                                left.setEnabled(false);
                                enablefalseright();
                                clearLeftSpinner(radioStringName);
                                isChecked(radioStringName);
                                rightSpinerText.setEnabled(true);

                                edit_sphereL.setBackground(getResources().getDrawable(R.drawable.unwanted));
                                edit_cylindL.setBackground(getResources().getDrawable(R.drawable.unwanted));
                                edit_axisL.setBackground(getResources().getDrawable(R.drawable.unwanted));
                                edit_additionL.setBackground(getResources().getDrawable(R.drawable.unwanted));

                                hideKeyboard(edit_sphereR);
//
                            } else {
                                /*disable();*/
                                left.setEnabled(true);
                                enablefalseBoth();
                            }
                        }

                    });


                    left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            if (left.isChecked() && !right.isChecked()) {
                                radioStringName ="left";
                                Log.e("this is left ..",""+radioStringName);
                                right.setEnabled(false);
                                enablefalseleft();
                                clearLeftSpinner(radioStringName);
                                leftSpinnerText.setEnabled(true);
                                isChecked(radioStringName);

                                edit_sphereR.setBackground(getResources().getDrawable(R.drawable.unwanted));
                                edit_cylindR.setBackground(getResources().getDrawable(R.drawable.unwanted));
                                edit_axisR.setBackground(getResources().getDrawable(R.drawable.unwanted));
                                edit_additionR.setBackground(getResources().getDrawable(R.drawable.unwanted));
                                hideKeyboard(edit_sphereL);

                            } else {
                                //disable();
                                right.setEnabled(true);
                                enablefalseBoth();
                            }
                        }
                    });

                } else if (id==R.id.radio3){
                    radioStringName = "virtual";

                    enablefalseBoth();

                    Log.e("this is virtual ..",""+radioStringName);

                    //clear all inputs
                    clearAll();

                    right.setEnabled(true);
                    left.setEnabled(true);
                    right.setChecked(false);
                    left.setChecked(false);

                    rTypeLnr.setVisibility(View.VISIBLE);
                    lTypeLnr.setVisibility(View.VISIBLE);

                    right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (right.isChecked()){
                                virtualSide="virtualRight";
                                Log.e("this is virtual ..",""+virtualSide);

                                left.setEnabled(false);

                                enabletrueeBoth();

                                isChecked(radioStringName);
                                leftSpinnerText.setEnabled(false);
                                rightSpinerText.setEnabled(true);
                            }else {

                                left.setEnabled(true);
                                leftSpinnerText.setEnabled(true);

                            }
                        }
                    });


                    left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (left.isChecked()){
                                //right.setEnabled(false);
                                virtualSide="virtualLeft";
                                Log.e("this is virtual ..",""+virtualSide);

                                right.setEnabled(false);
                                enabletrueeBoth();

                                leftSpinnerText.setEnabled(true);
                                rightSpinerText.setEnabled(false);

                                isChecked(radioStringName);

                            }else {
                                right.setEnabled(true);
                                rightSpinerText.setEnabled(true);
                            }
                        }
                    });

//                            clearLeftSpinner(radioStringName);
//                            isChecked(radioStringName);

                }else if (id==R.id.radio2){

                    right.setChecked(true);
                    left.setChecked(true);

                    clearAll();

                    if (right.isChecked() && left.isChecked()){

                        radioStringName ="both";
                        Log.e("this is Both ..",""+radioStringName);


                        left.setEnabled(false);
                        right.setEnabled(false);


                        edit_sphereL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        edit_cylindL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        edit_axisL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        edit_additionL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        edit_sphereR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        edit_cylindR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        edit_axisR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        edit_additionR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
                        hideKeyboard(edit_sphereR);

                        enabletrueeBoth();
                        rightSpinerText.setEnabled(true);

                        rTypeLnr.setVisibility(View.VISIBLE);
                        lTypeLnr.setVisibility(View.VISIBLE);

                        isChecked(radioStringName);
                    }
                }else {

                }


            }
        });

    }

    private void clearLeftSpinner(String name) {

        if (name.equals("right")) {

            edit_sphereL.getText().clear();
            edit_cylindL.getText().clear();
            edit_axisL.getText().clear();
            edit_additionL.getText().clear();

           // edit_Ldiameter.getText().clear();

            edit_sphereR.getText().clear();
            edit_cylindR.getText().clear();
            edit_axisR.getText().clear();
            edit_additionR.getText().clear();
         //   edit_Rdiameter.getText().clear();

            edit_sphereL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_cylindL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_axisL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_additionL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));

            edit_sphereR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_cylindR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_axisR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_additionR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));


            /*left lens view will gone*/
            rTypeLnr.setVisibility(View.VISIBLE);
            lTypeLnr.setVisibility(View.GONE);
            // leftSpinnerText.setText("");

            //  leftSpinnerText.setEnabled(false);


        } else if (name.equals("left")) {

            edit_sphereL.getText().clear();
            edit_cylindL.getText().clear();
            edit_axisL.getText().clear();
            edit_additionL.getText().clear();
           // edit_Ldiameter.getText().clear();


            edit_sphereR.getText().clear();
            edit_cylindR.getText().clear();
            edit_axisR.getText().clear();
            edit_additionR.getText().clear();
          //  edit_Rdiameter.getText().clear();

            edit_sphereR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_cylindR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_axisR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_additionR.setBackground(getResources().getDrawable(R.drawable.order_reference_button));

            edit_sphereL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_cylindL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_axisL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));
            edit_additionL.setBackground(getResources().getDrawable(R.drawable.order_reference_button));

            /*left lens view will gone*/
            rTypeLnr.setVisibility(View.GONE);
            lTypeLnr.setVisibility(View.VISIBLE);
            // rightSpinerText.setEnabled(false);
        }else if (radioStringName.equals("virtual")){

            edit_sphereR.getText().clear();
            edit_cylindR.getText().clear();
            edit_axisR.getText().clear();
            edit_additionR.getText().clear();
          //  edit_Rdiameter.getText().clear();

            edit_sphereL.getText().clear();
            edit_cylindL.getText().clear();
            edit_axisL.getText().clear();
            edit_additionL.getText().clear();
           // edit_Ldiameter.getText().clear();

            edit_sphereR.setEnabled(false);
            edit_cylindR.setEnabled(false);
            edit_axisR.setEnabled(false);
            edit_additionR.setEnabled(false);
            // edit_Rdiameter.setEnabled(false);

            edit_sphereL.setEnabled(false);
            edit_cylindL.setEnabled(false);
            edit_axisL.setEnabled(false);
            edit_additionL.setEnabled(false);
            // edit_Ldiameter.setEnabled(false);

            rightSpinerText.setText("type lens code or name");
            leftSpinnerText.setText("type lens code or name");
//                    leftSpinnerText.setEnabled(true);
//                    rightSpinerText.setEnabled(true);

        } else {

            Log.e("side is ","both");

            edit_sphereR.getText().clear();
            edit_cylindR.getText().clear();
            edit_axisR.getText().clear();
            edit_additionR.getText().clear();
          //  edit_Rdiameter.getText().clear();

            edit_sphereL.getText().clear();
            edit_cylindL.getText().clear();
            edit_axisL.getText().clear();
            edit_additionL.getText().clear();
           // edit_Ldiameter.getText().clear();

            edit_sphereR.setEnabled(true);
            edit_cylindR.setEnabled(true);
            edit_axisR.setEnabled(true);

            edit_additionR.setEnabled(true);
           // edit_Rdiameter.setEnabled(true);



            edit_sphereL.setEnabled(true);
            edit_cylindL.setEnabled(true);
            edit_axisL.setEnabled(true);

            edit_additionL.setEnabled(true);
          //  edit_Ldiameter.setEnabled(true);

            right.setChecked(true);
            left.setChecked(true);

            right.setEnabled(false);
            left.setEnabled(false);

//                    rightSpinerText.setText("type lens code or name");
//                    leftSpinnerText.setText("type lens code or name");
//                    leftSpinnerText.setEnabled(false);
//                    rightSpinerText.setEnabled(true);
//                    rTypeLnr.setVisibility(View.VISIBLE);
//                    lTypeLnr.setVisibility(View.VISIBLE);

        }


    }

    private void consignee() {
        consignee_list.clear();
        consignee_list.add("Vision Center");
        ArrayAdapter<String> adapter_cons = new ArrayAdapter<String>(getContext(), R.layout.toptext_inspinner, consignee_list);
        adapter_cons.setDropDownViewResource(R.layout.dropdown);

        consignee.setAdapter(adapter_cons);
        consignee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                consig = consignee_list.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void portfolio() {
        portfolio_list.clear();
        portfolio_list.add("ZEISS");
        portfolio_list.add("SYNCHRONY");
        ArrayAdapter<String> port = new ArrayAdapter<String>(getContext(), R.layout.toptext_inspinner, portfolio_list);
        port.setDropDownViewResource(R.layout.dropdown);

        portfolio.setAdapter(port);
        portfolio.setSelection(portfolioValues);
        portfolio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                portfo = portfolio_list.get(position);
                System.out.println("Portifio position" + portfo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void spinnerListener() {
        rightSpinerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rightAddition = edit_additionR.getText().toString().trim();

                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true;

                popupSpinnerwindow = new PopupWindow(dialogViewSpiner, width, height, focusable);
                popupSpinnerwindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                popupSpinnerwindow.setOutsideTouchable(true);

                System.out.println("radio button is" + radioStringName);
                System.out.println("Right portifio is " + portfo);
                System.out.println("Right  addition is " + rightAddition);
                apiLogicCall(radioStringName, portfo, rightAddition);


                lensTypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        CallBackData.Data rightLenTypeListView = (CallBackData.Data) parent.getItemAtPosition(position);

                        /*if both selected right lens will copied to left and it is disabled*/

                        lensType_name = rightLenTypeListView.getName();
                        lens_code = rightLenTypeListView.getLens_code();
                        lensTint_code = rightLenTypeListView.getTint();
                        individual=rightLenTypeListView.getIndividual();

                        System.out.println("name is " + lensType_name);
                        System.out.println("code is " + lens_code);
                        System.out.println("tine is " + lensTint_code);
                        System.out.println("individual is " + individual);

                        tintEnable();

                        /*if both selected right lens will copied to left and it is disabled*/
                        if(radioStringName.equals("both")){
                            rightSpinerText.setText(lensType_name);
                            leftSpinnerText.setText(lensType_name);
                            leftSpinnerText.setEnabled(false);
                            fvs(radioStringName);
                            //defaultCoating(lensType_name);

                        }else if (radioStringName.equals("virtual")){
                            if (virtualSide.equals("virtualRight")){
                                rightSpinerText.setText(lensType_name);
                                leftSpinnerText.setText(lensType_name);
                               // defaultCoating(lensType_name);
                                //leftSpinnerText.setEnabled(false);
                            }else {
                                leftSpinnerText.setText(lensType_name);
                                rightSpinerText.setText(lensType_name);
                                // rightSpinerText.setEnabled(false);
                            }
                        }else {
                            rightSpinerText.setText(lensType_name);
                            fvs(radioStringName);

                        }

                        if (!lens_code.equals("")){
                            getCoating(lens_code);

                        }
                        coatingLists.clear();
                        popupSpinnerwindow.dismiss();

                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupSpinnerwindow.dismiss();
                    }
                });

                searchText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        LensOrderingFragment.this.demoAdapter.getFilter().filter(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        leftSpinnerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(leftSpinnerText.getParent()!=null){
//                    ((ViewGroup)leftSpinnerText.getParent()).removeView(leftSpinnerText);
//                }

                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true;

                leftAddition = edit_additionL.getText().toString().trim();

                popupSpinnerwindow = new PopupWindow(dialogViewSpiner, width, height, focusable);
                popupSpinnerwindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                popupSpinnerwindow.setOutsideTouchable(true);

                apiLogicCall(radioStringName, portfo, leftAddition);

                lensTypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        CallBackData.Data rightLenTypeListView = (CallBackData.Data) parent.getItemAtPosition(position);

                        lensType_name = rightLenTypeListView.getName();
                        lens_code = rightLenTypeListView.getLens_code();
                        lensTint_code = rightLenTypeListView.getTint();
                        individual=rightLenTypeListView.getIndividual();

                        tintEnable();

                        if (radioStringName.equals("virtual")){
                            if (virtualSide.equals("virtualLeft")){
                                Log.e("left lens","");
                                rightSpinerText.setText(lensType_name);
                                leftSpinnerText.setText(lensType_name);
                                //leftSpinnerText.setEnabled(false);
                            }else {
                                leftSpinnerText.setText(lensType_name);
                                rightSpinerText.setText(lensType_name);
                                //  rightSpinerText.setEnabled(false);
                            }
                        }else if (radioStringName.equals("left")){
                            leftSpinnerText.setText(lensType_name);
                            System.out.println("lrft side"+radioStringName);
                            fvs(radioStringName);

                        }else {

                        }

                        // apiCalls.getCoating(getActivity(),lens_code);
                        if (!lens_code.equals("")){
                            getCoating(lens_code);

                        }
                        coatingLists.clear();
                        popupSpinnerwindow.dismiss();


                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupSpinnerwindow.dismiss();
                    }
                });


                searchText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        LensOrderingFragment.this.demoAdapter.getFilter().filter(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

            }
        });


        employeeSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true;
                //  LensAdapter adapter = null;
                leftAddition = edit_additionL.getText().toString().trim();

                popupSpinnerwindow = new PopupWindow(dialogViewSpiner, width, height, focusable);
                popupSpinnerwindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                popupSpinnerwindow.setOutsideTouchable(true);


                demoAdapter = new DemoAdapter(LensOrderingFragment.this, employeeNameLists);
                lensTypeListView.setAdapter(demoAdapter);


                lensTypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CallBackData.Data rightLenTypeListView = (CallBackData.Data) parent.getItemAtPosition(position);

                        empName = rightLenTypeListView.getName();
                        employeeSpinner.setText(empName);
                        popupSpinnerwindow.dismiss();

                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupSpinnerwindow.dismiss();
                    }
                });
                searchText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        lensTypeListView.setAdapter(demoAdapter);
                        demoAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        LensOrderingFragment.this.demoAdapter.getFilter().filter(s);


                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        lensTypeListView.setAdapter(demoAdapter);
                        demoAdapter.notifyDataSetChanged();


                    }
                });

            }


        });

        coatingSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true;

                popupCoatingWindow = new PopupWindow(coatingDialog, width, height, focusable);
                popupCoatingWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                popupCoatingWindow.setOutsideTouchable(true);


                tintAdapter = new TintAdapter(LensOrderingFragment.this, coatingLists);
                coatingAndTintListView.setAdapter(tintAdapter);
                tintAdapter.notifyDataSetChanged();


                coatingAndTintListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        CallBackData.Data coatingList = (CallBackData.Data) parent.getItemAtPosition(position);

                        coatingName = coatingList.getName();
                        coatingCode = coatingList.getCode();
                        coatingType=coatingList.getType();
                        coatingSpinner.setText(coatingName);
                        popupCoatingWindow.dismiss();

                    }
                });

            }
        });

        tintSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.MATCH_PARENT;
                boolean focusable = true;

                popupCoatingWindow = new PopupWindow(coatingDialog, width, height, focusable);
                popupCoatingWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                popupCoatingWindow.setOutsideTouchable(true);


                tintAdapter = new TintAdapter(LensOrderingFragment.this, tintData);
                coatingAndTintListView.setAdapter(tintAdapter);

                coatingAndTintListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CallBackData.Data rightLenTypeListView = (CallBackData.Data) parent.getItemAtPosition(position);

                        tine_code = rightLenTypeListView.getCode();

                        tine_name = rightLenTypeListView.getName();

                        tintSpinner.setText(tine_name);
                        popupCoatingWindow.dismiss();
                    }
                });

            }
        });


    }

    private void tintEnable() {

        if(lensTint_code!=null && lensTint_code.equals("0")){

            tintSpinner.setEnabled(true);
        }else {
            tintSpinner.setEnabled(false);
            tine_code="";
            System.out.println("tint code"+tine_code);
            tintSpinner.setText(R.string.select_tint);
        }

    }


    public void tintFromEdit(String lensName){
        if (lensName.contains("Finished SV") || lensName.contains("FSV")){
            tintSpinner.setEnabled(false);
        }else {
            tintSpinner.setEnabled(true);
        }
    }

                                 /*API Call logic*/

    public void apiLogicCall(String radioStringName, String portfo, String leftAddition) {

        if (portfo.equals("SYNCHRONY") && leftAddition.equals("")) {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,synchronidefaultList);
        } else if (portfo.equals("SYNCHRONY") && leftAddition.length() > 0) {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,synchroniOnceList);
        } else if (portfo.equals("ZEISS") && leftAddition.equals("")) {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,zeissZeroeslist);
        } else if (portfo.equals("ZEISS") && leftAddition.length() > 0) {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,zeissOnesList);
        } else if (radioStringName.equals("both") && portfo.equals("ZEISS") && leftAddition.equals("")) {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,zeissZeroeslist);
        } else if (radioStringName.equals("both") && portfo.equals("ZEISS") && leftAddition.length() > 0) {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,zeissOnesList);
        } else if (radioStringName.equals("both") && portfo.equals("SYNCHRONY") && leftAddition.equals("")) {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,synchronidefaultList);
        } else if (radioStringName.equals("both") && portfo.equals("SYNCHRONY") && leftAddition.length()>0){
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,synchroniOnceList);
        }else if (radioStringName.equals("virtual") && portfo.equals("ZEISS") && leftAddition.equals("")){
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,zeissZeroeslist);
        }else if (radioStringName.equals("virtual") && portfo.equals("ZEISS") && leftAddition.length()>0){
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,zeissOnesList);
        }else if (radioStringName.equals("virtual") && portfo.equals("SYNCHRONY") && leftAddition.equals("")){
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,synchronidefaultList);
        }else {
            demoAdapter=new DemoAdapter(LensOrderingFragment.this,synchroniOnceList);
        }
        lensTypeListView.setAdapter(demoAdapter);

    }


                 /*Main validation*/


    public void mainValidation(Fragment fragment) {

        String refid = edit_orderReference.getText().toString().trim();
        String firstName=firstNm.getText().toString().trim();
        String lastName=lastNm.getText().toString().trim();
        String rightSphere = edit_sphereR.getText().toString().trim();
        String rightCylind = edit_cylindR.getText().toString().trim();
        String rightAxis = edit_axisR.getText().toString().trim();

        String leftSphere = edit_sphereL.getText().toString().trim();
        String leftCylind = edit_cylindL.getText().toString().trim();
        String leftAxis = edit_axisL.getText().toString().trim();

        String rightAddition = edit_additionR.getText().toString().trim();
        String leftAddition = edit_additionL.getText().toString().trim();

       // String rightDiameter = edit_Rdiameter.getText().toString().trim();
      //  String leftDiameter = edit_Ldiameter.getText().toString().trim();

        String rightSpinner=rightSpinerText.getText().toString().trim();
        String leftSpinner=leftSpinnerText.getText().toString().trim();

        if(radioStringName.equals("right")){
            leftSpinner = "";
            ldia="";
        }

        if(radioStringName.equals("left")){
            rightSpinner = "";
            rdia="";
        }


        LensInput lensInput = new LensInput();
        lensInput.setOrderRef(refid);
        lensInput.setConFstNme(firstName);
        lensInput.setConLstNme(lastName);
        lensInput.setConsignee(consig);
        lensInput.setEmployee(empName);
        lensInput.setRightSphere(rightSphere);
        lensInput.setRightCylind(rightCylind);
        lensInput.setRightAxis(rightAxis);
        lensInput.setRightAddition(rightAddition);
        lensInput.setLeftSphere(leftSphere);
        lensInput.setLeftCylind(leftCylind);
        lensInput.setLeftAxis(leftAxis);
        lensInput.setLeftAddition(leftAddition);
        lensInput.setPortofolio(portfo);
        lensInput.setSides(radioStringName);
        lensInput.setLensTypeName(lensType_name);
        lensInput.setLensTypeCode(lens_code);
        lensInput.setRightDiameter(rdia);
        lensInput.setLeftDiamter(ldia);
        lensInput.setCoating(coatingName);
        lensInput.setCotingCode(coatingCode);
        lensInput.setCoatingType(coatingType);
        lensInput.setTint(tine_code);
        lensInput.setTintName(tine_name);
        lensInput.setHard(hard);
        lensInput.setSwitch1(switch_value);
        lensInput.setVirtualSide(virtualSide);
        lensInput.setIndividual(individual);

        System.out.println("rDiameter is "+rdia);
        System.out.println("lDiameter is "+ldia);

        String serializedData = lensInput.serialize();
        SharedPreferences preferencesReader = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesReader.edit();
        editor.putString(PREFS_KEY, serializedData);
        editor.apply();


        System.out.println("rightValue"+rightSpinner);
        if ((TextUtils.isEmpty(refid) || TextUtils.isEmpty(rightSphere)) && (TextUtils.isEmpty(refid) || TextUtils.isEmpty(leftSphere)) ) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setMessage("Reference and Sphere number mandatory");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (((TextUtils.isEmpty(rightCylind) && !TextUtils.isEmpty(rightAxis)) || (!TextUtils.isEmpty(rightCylind) && TextUtils.isEmpty(rightAxis))) || ((!TextUtils.isEmpty(leftCylind) && TextUtils.isEmpty(leftAxis)) || (TextUtils.isEmpty(leftCylind) && !TextUtils.isEmpty(leftAxis)))) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setMessage("Cylinder and Axis value mandatory");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else if (((TextUtils.isEmpty(lensType_name) || TextUtils.isEmpty(rdia) && (TextUtils.isEmpty(lensType_name) || TextUtils.isEmpty(ldia))))) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setMessage("Lens Type and Diameter values are mandatory");
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }else {
            if(isAdvanceFragmentTransction) {
                advanceFragmentTransction(fragment);
                isAdvanceFragmentTransction=false;
            }else {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        }

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        apiCalls.getDataSet(new CallBack<List<CallBackData>>() {
            @Override
            public void next(List<CallBackData.Data> result) {
                System.out.println("...."+result.size());
                for (int i=0;i<result.size();i++){
                    zeissZeroeslist.add(new CallBackData.Data(result.get(i).getLens_code(),result.get(i).getName(),result.get(i).getTint(),result.get(i).getIndividual()));
                    System.out.println("indiviul------- "+result.get(i).getIndividual());
                }
                System.out.println("revert data zeiss zero is------- "+zeissZeroeslist.size());



            }
        });

        apiCalls.getZeiiiss(new CallBack<List<CallBackData>>() {
            @Override
            public void next(List<CallBackData.Data> result) {

                for (int i=0;i<result.size();i++){
                    zeissOnesList.add(new CallBackData.Data(result.get(i).getLens_code(),result.get(i).getName(),result.get(i).getTint(),result.get(i).getIndividual()));
                    System.out.println("indiviul------- "+result.get(i).getIndividual());
                }
                System.out.println("revert data zeiss once is:-----"+zeissOnesList.size());
            }
        });

        apiCalls.getSyncronyDefault(new CallBack<List<CallBackData>>() {
            @Override
            public void next(List<CallBackData.Data> result) {

                for (int i=0;i<result.size();i++){
                    synchronidefaultList.add(new CallBackData.Data(result.get(i).getLens_code(),result.get(i).getName(),result.get(i).getTint(),result.get(i).getIndividual()));

                    System.out.println("tint code"+result.get(i).getIndividual());
                }
                System.out.println("revert data syncroni  default is:-----"+synchronidefaultList.size());

            }
        });


        apiCalls.getSyncroniOnce(new CallBack<List<CallBackData>>() {
            @Override
            public void next(List<CallBackData.Data> result) {

                for (int i=0;i<result.size();i++){
                    synchroniOnceList.add(new CallBackData.Data(result.get(i).getLens_code(),result.get(i).getName(),result.get(i).getTint(),result.get(i).getIndividual()));
                    System.out.println("indiviul------- "+result.get(i).getIndividual());
                }
                System.out.println("revert data syncroni once is:-----"+synchroniOnceList.size());

            }
        });

        apiCalls.getEmployeeName(new CallBack<List<CallBackData>>() {
            @Override
            public void next(List<CallBackData.Data> result) {
                for (int i=0;i<result.size();i++){
                    employeeNameLists.add(new CallBackData.Data(result.get(i).getName()));
                }
                System.out.println("revert data employee name is:-----"+employeeNameLists.size());
            }
        });

        apiCalls.getTintDatas(new CallBack<List<CallBackData>>() {
            @Override
            public void next(List<CallBackData.Data> result) {
                for (int i=0;i<result.size();i++){
                    tintData.add(new CallBackData.Data(result.get(i).getCode(),result.get(i).getName()));

                }
                System.out.println("revert data tint name is:-----"+tintData.size());
            }
        });


    }

    public void getCoating(String code) {
        APIService apiCoatingService = ApiUtils.getCoteService();

        Map<String, String> a = new HashMap<>();
        a.put("lens_id", code);

        apiCoatingService.coatinglist(a).enqueue(new Callback<CallBackData>() {

            List<CallBackData.Data> data;
            @Override
            public void onResponse(Call<CallBackData> call, Response<CallBackData> response) {

                coatingLists.clear();
                data=response.body().getData();
                System.out.println("respose from synchroniZeroes"+data);
                try {
                    for (int i=0;i<data.size();i++){
                        // coatingLists.add(new CallBackData.Data(data.get(i).getName(),data.get(i).getCode(),data.get(i).getType()));
                         coatingLists.add(new CallBackData.Data(data.get(i).getCode(),data.get(i).getName(),data.get(i).getType()));

                         coatingName=data.get(1).getName();
                         String coatingCode=data.get(1).getCode();
                         String coatingType=data.get(1).getType();
                         System.out.println("coating name is "+coatingName);
                         System.out.println("coating code is "+coatingCode);
                         System.out.println("coating type is "+coatingType);
                         defaultCoating(lensType_name,coatingName,coatingCode,coatingType);

                    }
                }catch (Exception e){
                    e.printStackTrace();

                }

                System.out.println("coating size"+coatingLists.size());

                System.out.println("coating name"+coatingName);


            }

            @Override
            public void onFailure(@NonNull Call<CallBackData> call, @NonNull Throwable t) {
                Log.e("error", t.getMessage());

            }
        });

    }
/*Changed in Validation*/
    public void isChecked(String side){
        if (side.equals("left")){
            System.out.println("side is "+radioStringName);

            edit_sphereL.setOnEditorActionListener(new Validation(getContext(), edit_sphereL, edit_cylindL, edit_sphereL, -20.0, 23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindL.setOnEditorActionListener(new Validation(getContext(), edit_cylindL, edit_axisL, edit_cylindL, -10.0, 10.0,errorMsg,R.string.cylind_notrange));
            edit_axisL.setOnEditorActionListener(new Validation(getContext(), edit_axisL, edit_additionL, edit_axisL, 0.0, 180.0,errorMsg,R.string.axis_notrange));
            edit_additionL.setOnEditorActionListener(new Validation1(getContext(), edit_additionL, edit_additionL, edit_additionL, 0.5, 4.0,errorMsg,R.string.addition_notrange));

            edit_sphereL.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_sphere,edit_sphereL,edit_sphereL, -20.0,23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindL.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_cylind,edit_cylindL,edit_cylindL, -10.0,10.0,errorMsg,R.string.cylind_notrange));
            edit_axisL.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_axis,edit_axisL,edit_axisL, 0.0,180.0,errorMsg,R.string.axis_notrange));
            edit_additionL.setOnFocusChangeListener(new RangesDisplay1(getContext(),values, R.string.value_addition,edit_additionL,edit_additionL,0.5,4.0,errorMsg,R.string.addition_notrange));




            /*further using this is need */


            rTypeLnr.setVisibility(View.GONE);
            lTypeLnr.setVisibility(View.VISIBLE);

        }else if (side.equals("right")){

            System.out.println("side is "+radioStringName);
            edit_sphereR.setOnEditorActionListener(new Validation(getContext(), edit_sphereR, edit_cylindR, edit_sphereR, -20.0, 23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindR.setOnEditorActionListener(new Validation(getContext(), edit_cylindR, edit_axisR, edit_cylindR, -10.0, 10.0,errorMsg,R.string.cylind_notrange));
            edit_axisR.setOnEditorActionListener(new Validation(getContext(), edit_axisR, edit_additionR, edit_axisR, 0.0, 180.0,errorMsg,R.string.axis_notrange));
            edit_additionR.setOnEditorActionListener(new Validation1(getContext(), edit_additionR, edit_additionR, edit_additionR, 0.5, 4.0,errorMsg,R.string.addition_notrange));

            edit_sphereR.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_sphere,edit_sphereR,edit_sphereR, -20.0,23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindR.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_cylind,edit_cylindR,edit_cylindR, -10.0,10.0,errorMsg,R.string.cylind_notrange));
            edit_axisR.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_axis,edit_axisR,edit_axisR,0.0,180.0,errorMsg,R.string.axis_notrange));
            edit_additionR.setOnFocusChangeListener(new RangesDisplay1(getContext(),values, R.string.value_addition,edit_additionR,edit_additionR,0.5,4.0,errorMsg,R.string.addition_notrange));


            rTypeLnr.setVisibility(View.VISIBLE);
            lTypeLnr.setVisibility(View.GONE);

        }else if (side.equals("both")){

            Log.e("radioStringName ","radioStringName");


            leftSpinnerText.setEnabled(false);
            edit_sphereR.setOnEditorActionListener(new Validation(getContext(), edit_sphereR, edit_cylindR, edit_sphereL, -20.0, 23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindR.setOnEditorActionListener(new Validation(getContext(), edit_cylindR, edit_axisR, edit_cylindL, -10.0, 10.0,errorMsg,R.string.cylind_notrange));
            edit_axisR.setOnEditorActionListener(new Validation1(getContext(), edit_axisR, edit_additionR, edit_axisL, 0.0, 180.0,errorMsg,R.string.axis_notrange));
            edit_additionR.setOnEditorActionListener(new Validation1(getContext(), edit_additionR, edit_additionR, edit_additionL, 0.5, 4.0,errorMsg,R.string.addition_notrange));


            edit_sphereR.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_sphere,edit_sphereR,edit_sphereL, -20.0,23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindR.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_cylind,edit_cylindR,edit_cylindL, -10.0,10.0,errorMsg,R.string.cylind_notrange));
            edit_axisR.setOnFocusChangeListener(new RangesForAxis(getContext(),values, R.string.value_axis,edit_axisR,edit_axisL,0.0,180.0,errorMsg,R.string.axis_notrange));
            edit_additionR.setOnFocusChangeListener(new RangesDisplay1(getContext(),values, R.string.value_addition,edit_additionR,edit_additionL,0.5,4.0,errorMsg,R.string.addition_notrange));


            edit_sphereR.setOnEditorActionListener(new Validation(getContext(), edit_sphereR, edit_cylindR, edit_sphereL, -20.0, 23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindR.setOnEditorActionListener(new Validation(getContext(), edit_cylindR, edit_axisR, edit_cylindL, -10.0, 10.0,errorMsg,R.string.cylind_notrange));
            edit_axisR.setOnEditorActionListener(new Validation(getContext(), edit_axisR, edit_additionR, edit_axisL, 0.0, 180.0,errorMsg,R.string.axis_notrange));
            edit_additionR.setOnEditorActionListener(new Validation1(getContext(), edit_additionR, edit_additionR, edit_additionL, 0.5, 4.0,errorMsg,R.string.addition_notrange));

        }else if(side.equals("virtual") && virtualSide.equals("virtualRight")){

            edit_sphereR.setOnEditorActionListener(new Validation(getContext(), edit_sphereR, edit_cylindR, edit_sphereL, -20.0, 23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindR.setOnEditorActionListener(new Validation(getContext(), edit_cylindR, edit_axisR, edit_cylindL, -10.0, 10.0,errorMsg,R.string.cylind_notrange));
            edit_axisR.setOnEditorActionListener(new Validation(getContext(), edit_axisR, edit_additionR, edit_axisL, 0.0, 180.0,errorMsg,R.string.axis_notrange));
            edit_additionR.setOnEditorActionListener(new Validation1(getContext(), edit_additionR, edit_additionR, edit_additionL, 0.5, 4.0,errorMsg,R.string.addition_notrange));



            edit_sphereR.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_sphere,edit_sphereR,edit_sphereL, -20.0,23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindR.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_cylind,edit_cylindR,edit_cylindL, -10.0,10.0,errorMsg,R.string.cylind_notrange));
            edit_axisR.setOnFocusChangeListener(new RangesForAxis(getContext(),values, R.string.value_axis,edit_axisR,edit_axisL,0.0,180.0,errorMsg,R.string.axis_notrange));
            edit_additionR.setOnFocusChangeListener(new RangesDisplay1(getContext(),values, R.string.value_addition,edit_additionR,edit_additionL,0.5,4.0,errorMsg,R.string.addition_notrange));


        }else if ((side.equals("virtual") && virtualSide.equals("virtualLeft"))){

            edit_sphereL.setOnEditorActionListener(new Validation(getContext(), edit_sphereL, edit_cylindL, edit_sphereR, -20.0, 23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindL.setOnEditorActionListener(new Validation(getContext(), edit_cylindL, edit_axisL, edit_cylindR, -10.0, 10.0,errorMsg,R.string.cylind_notrange));
            edit_axisL.setOnEditorActionListener(new Validation(getContext(), edit_axisL, edit_additionL, edit_axisR, 0.0, 180.0,errorMsg,R.string.axis_notrange));
            edit_additionL.setOnEditorActionListener(new Validation1(getContext(), edit_additionL, edit_additionL, edit_additionR, 0.5, 4.0,errorMsg,R.string.addition_notrange));

            edit_sphereL.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_sphere,edit_sphereL,edit_sphereR, -20.0,23.0,errorMsg,R.string.sphere_notrange));
            edit_cylindL.setOnFocusChangeListener(new RangesDisplay(getContext(),values, R.string.value_cylind,edit_cylindL,edit_cylindR, -10.0,10.0,errorMsg,R.string.cylind_notrange));
            edit_axisL.setOnFocusChangeListener(new RangesDisplay1(getContext(),values, R.string.value_axis,edit_axisL,edit_axisR, 0.0,180.0,errorMsg,R.string.axis_notrange));
            edit_additionL.setOnFocusChangeListener(new RangesDisplay1(getContext(),values, R.string.value_addition,edit_additionL,edit_additionR,0.5,4.0,errorMsg,R.string.addition_notrange));


        }

    }

    private void advanceFragmentTransction(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentContainer, fragment);
        transaction.addToBackStack("AdvanceFragment");
        transaction.commit();
    }



    public void enablefalseBoth(){

        edit_sphereL.setEnabled(false);
        edit_cylindL.setEnabled(false);
        edit_axisL.setEnabled(false);
        edit_additionL.setEnabled(false);

        edit_sphereR.setEnabled(false);
        edit_cylindR.setEnabled(false);
        edit_axisR.setEnabled(false);
        edit_additionR.setEnabled(false);

    }

    public void enablefalseleft(){
        edit_sphereL.setEnabled(true);
        edit_cylindL.setEnabled(true);
        edit_axisL.setEnabled(true);
        edit_additionL.setEnabled(true);
       // edit_Ldiameter.setEnabled(true);
        lDiameter.setEnabled(true);
    }


    public void enablefalseright(){
        edit_sphereR.setEnabled(true);
        edit_cylindR.setEnabled(true);
        edit_axisR.setEnabled(true);
        edit_additionR.setEnabled(true);
       // edit_Rdiameter.setEnabled(true);
        rDiameter.setEnabled(true);

    }

    public void enabletrueeBoth(){

        edit_sphereL.setEnabled(true);
        edit_cylindL.setEnabled(true);
        edit_axisL.setEnabled(true);
        edit_additionL.setEnabled(true);

        edit_sphereR.setEnabled(true);
        edit_cylindR.setEnabled(true);
        edit_axisR.setEnabled(true);
        edit_additionR.setEnabled(true);
    }


    public void clearAll(){
        edit_sphereR.getText().clear();
        edit_cylindR.getText().clear();
        edit_axisR.getText().clear();
        edit_additionR.getText().clear();
       // edit_Rdiameter.getText().clear();
        /*new addede*/
        rightSpinerText.setText("");
        edit_sphereL.getText().clear();
        edit_cylindL.getText().clear();
        edit_axisL.getText().clear();
        edit_additionL.getText().clear();
     //   edit_Ldiameter.getText().clear();

        /*new addede*/
        leftSpinnerText.setText("");
    }

    private void onClear(){
        edit_sphereR.getText().clear();
        edit_cylindR.getText().clear();
        edit_axisR.getText().clear();
        edit_additionR.getText().clear();
//        edit_Rdiameter.getText().clear();

        edit_sphereL.getText().clear();
        edit_cylindL.getText().clear();
        edit_axisL.getText().clear();
        edit_additionL.getText().clear();
    //    edit_Ldiameter.getText().clear();
    }

    public void enabletrueleft(){
        edit_sphereL.setEnabled(false);
        edit_cylindL.setEnabled(false);
        edit_axisL.setEnabled(false);
        edit_additionL.setEnabled(false);
    }

    public void checkedFunction(final String sideIs) {

        if (sideIs.equals("1")) {
            right.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (right.isChecked() && !left.isChecked()) {

                        radioStringName="right";
                        Log.e("side is ",radioStringName);
                        left.setEnabled(false);
                        enablefalseright();
                        isChecked(radioStringName);
                        rightSpinerText.setEnabled(true);
                        showKeyboard(edit_sphereR);

//                        *

                    } else {
                        /*disable();*/
                        left.setEnabled(true);
                        enablefalseBoth();
                        clearAll();
                    }
                }

            });

            left.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (left.isChecked() && !right.isChecked()) {

                        radioStringName="left";
                        Log.e("side is ",radioStringName);

                        right.setEnabled(false);
                        enablefalseleft();
                        isChecked(radioStringName);
                        leftSpinnerText.setEnabled(true);
                        showKeyboard(edit_sphereL);
                    } else {
                        //disable();
                        right.setEnabled(true);
                        enablefalseBoth();
                        clearAll();
                    }
                }
            });
        }
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//        toolbar.setTitle("VISUSTORE");
//        ((DashBoard)getActivity()).setToolbar(toolbar);
//        ((DashBoard)getActivity()).unLockNavigationDrawer();
//
//
//    }




    /* finish Sv lens type name method*/

    public void fvs(String radioStriingName){

        if (radioStriingName.equals("both") && (lensType_name.contains("Finished SV") || lensType_name.contains("FSV"))){

            Log.e("success","");

            String rShere=edit_sphereR.getText().toString().trim();
            String lSphere=edit_sphereL.getText().toString().trim();

            if (!rShere.equals("") && !lSphere.equals("")){

                System.out.println("both negative");
                rightfinishDiameter();
                leftfinishDiameter();

                rDiameter.setEnabled(true);
                lDiameter.setEnabled(true);

            }

        }else if (radioStriingName.equals("right") && ( lensType_name.contains("Finished SV") || lensType_name.contains("FSV"))){


            System.out.println("right finish");
            String rShere=edit_sphereR.getText().toString().trim();
            if (!rShere.equals("")){
                rightfinishDiameter();
                rDiameter.setEnabled(true);

            }

        }else if (radioStriingName.equals("left") && (lensType_name.contains("Finished SV") || lensType_name.contains("FSV"))){


            System.out.println("left finish");
            String lShere=edit_sphereL.getText().toString().trim();
            if (!lShere.equals("")){
                leftfinishDiameter();
                lDiameter.setEnabled(true);
            }

        }else if (radioStriingName.equals("both") && (!lensType_name.contains("Finished SV") || !lensType_name.contains("FSV"))){


            String rShere=edit_sphereR.getText().toString().trim();
            String lSphere=edit_sphereL.getText().toString().trim();

            if (!rShere.equals("") && !lSphere.equals("")){

                System.out.println("both not  finish");
                diameterRight();
                diameterLeft();
                //  lDiameter.setSelection(selectedItem);
                rDiameter.setEnabled(true);
                lDiameter.setEnabled(false);
                btn_advance.setEnabled(true);
            }

        }else if (radioStriingName.equals("left") && (!lensType_name.contains("Finished SV") || !lensType_name.contains("FSV"))){

            String lShere=edit_sphereL.getText().toString().trim();
            if (!lShere.equals("")){
                System.out.println("left not  finish");
                diameterLeft();
                lDiameter.setEnabled(true);
                btn_advance.setEnabled(true);
            }

        }else if (radioStriingName.equals("right") && (!lensType_name.contains("Finished SV") || !lensType_name.contains("FSV"))){
            String rShere=edit_sphereR.getText().toString().trim();
            if (!rShere.equals("")){
                System.out.println("right not finish");
                diameterRight();
                rDiameter.setEnabled(true);
                btn_advance.setEnabled(true);
            }

        }
    }

    public  void showKeyboard(EditText editText){
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    public void hideKeyboard(EditText editText){
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    public void defaultCoating(String lensName,String name,String code,String type){

        if (lensName.contains("Finished SV") || lensName.contains("FSV")){

                coatingSpinner.setText(name);
                coatingSpinner.setEnabled(false);
                coatingName=name;
                coatingCode=code;
                coatingType=type;

        }else {
             coatingSpinner.setEnabled(true);
            
        }
    }


    /*right diameter for spinner*/

    public void diameterRight(){

        rightDiameter.clear();

        rightDiameter.add("55");
        rightDiameter.add("60");
        rightDiameter.add("65");
        rightDiameter.add("70");
        rightDiameter.add("75");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, rightDiameter);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rDiameter.setAdapter(dataAdapter1);

        Log.e("right position is ", String.valueOf(selectedItem));

        rDiameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                switch (item) {

                    case "55":
                        selectedItem = 0;
                        rdia = item;
                        break;
                    case "60":
                        selectedItem = 1;
                        rdia = item;
                        break;
                    case "65":
                        selectedItem = 2;
                        rdia = item;
                        break;
                    case "70":
                        selectedItem = 3;
                        rdia = item;
                       // main();
                        break;
                    case "75":
                        selectedItem = 4;
                        rdia = item;
                        break;
                    default:
                        break;

                }

                if (radioStringName.equals("both") && !lensType_name.contains("Finished SV") || !lensType_name.contains("FSV")){
                   new Handler().post(new Runnable() {
                       @Override
                       public void run() {
                           lDiameter.setSelection(selectedItem);
                       }
                   });

                }else {
                    ldia="";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


            /*left diameter spinner*/

    public void diameterLeft(){

        leftDiameter.clear();

        leftDiameter.add("55");
        leftDiameter.add("60");
        leftDiameter.add("65");
        leftDiameter.add("70");
        leftDiameter.add("75");

        final ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, leftDiameter);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lDiameter.setAdapter(dataAdapter1);

       // rDiameter.setSelection(selectedItem);
       // lDiameter.setSelection(selectedItem);

        Log.e("left position is ", String.valueOf(selectedItem));
        lDiameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                switch (item) {
                    case "55":
                        selectedItem = 0;
                        ldia = item;
                        break;
                    case "60":
                        selectedItem = 1;
                        ldia = item;
                        break;
                    case "65":
                        selectedItem = 2;
                        ldia = item;
                        break;
                    case "70":
                        selectedItem = 3;
                        ldia = item;
                        break;
                    case "75":
                        selectedItem = 4;
                        ldia = item;
                        break;
                    default:
                        break;
                }
                if (radioStringName.equals("both")){
                    System.out.println("both in right diameter");
                  //  lDiameter.setSelection(selectedItem);
                }else {
                    rdia="";
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

                        /*get diameter for finish lens*/

    public void rightfinishDiameter(){

        leftDiameter.clear();

        leftDiameter.add("65");
        leftDiameter.add("70");
        leftDiameter.add("75");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, leftDiameter);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rDiameter.setAdapter(dataAdapter1);

        rDiameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                switch (item) {
                    case "65":
                        selectedItem = 0;
                        rdia = item;
                        break;
                    case "70":
                        selectedItem = 1;
                        rdia = item;
                        break;
                    case "75":
                        selectedItem = 2;
                        rdia = item;
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void leftfinishDiameter(){
        leftDiameter.clear();

        leftDiameter.add("65");
        leftDiameter.add("70");
        leftDiameter.add("75");

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, leftDiameter);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lDiameter.setAdapter(dataAdapter1);

        lDiameter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                switch (item) {
                    case "65":
                        selectedItem = 0;
                        ldia = item;
                        break;
                    case "70":
                        selectedItem = 1;
                        ldia = item;
                        break;
                    case "75":
                        selectedItem = 2;
                        ldia = item;
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

                      /*get diameter from edit*/

    public void getDiameterFrmEdit(String rdiam,String ldiam,String side,String rxLens){

        if (side.equals("both") && ( lensType_name.contains("Finished SV") || lensType_name.contains("FSV"))){

            System.out.println("lens name is finish :"+rxLens);
            System.out.println("diameter is :"+ldiam);

            if (rdiam.equals("65")){
                selectedItem=0;

            }else if (rdiam.equals("70")){
                selectedItem=1;

            }else if (rdiam.equals("75")){
                selectedItem=2;

            }
            if (ldiam.equals("65")){
                selectItemIndex=0;

            }else if (ldiam.equals("70")){
                selectItemIndex=1;

            }else {
                selectItemIndex=2;

            }

            rDiameter.setSelection(selectedItem);
            lDiameter.setSelection(selectItemIndex);

        }else if (side.equals("both") && ( !lensType_name.contains("Finished SV") || !lensType_name.contains("FSV"))){
            System.out.println("lens name not  is  :"+rxLens);
            if (rdia.equals("55")){
                selectedItem=0;
            }else if (rdia.equals("60")){
                selectedItem=1;
            }else if (rdia.equals("65")){
                selectedItem=2;
            }else if (rdia.equals("70")){
                selectedItem=3;
            }else {
                selectedItem=4;
            }
            rDiameter.setSelection(selectedItem);
            lDiameter.setSelection(selectedItem);

        }else if (side.equals("left") &&  (lensType_name.contains("Finished SV") || lensType_name.contains("FSV"))){

            if (ldiam.equals("65")){
                selectedItem=0;
            }else if (ldiam.equals("70")){
                selectedItem=1;
            }else {
                selectedItem=2;
            }
            lDiameter.setSelection(selectedItem);

        }else if (side.equals("right") &&  (lensType_name.contains("Finished SV") || lensType_name.contains("FSV"))){

            if (rdiam.equals("65")){
                selectedItem=0;
            }else if (rdiam.equals("70")){
                selectedItem=1;
            }else {
                selectedItem=2;
            }
            rDiameter.setSelection(selectedItem);

        }else if (side.equals("right") &&  (!lensType_name.contains("Finished SV") || !lensType_name.contains("FSV"))){

            if (rdiam.equals("55")){
                selectedItem=0;
            }else if (rdiam.equals("60")){
                selectedItem=1;
            }else if (rdiam.equals("65")){
                selectedItem=2;
            }else if (rdiam.equals("70")){
                selectedItem=3;
            }else {
                selectedItem=4;
            }
            rDiameter.setSelection(selectedItem);

        }else if (side.equals("left") &&  (!lensType_name.contains("Finished SV") || !lensType_name.contains("FSV"))){

            if (ldiam.equals("55")){
                selectedItem=0;
            }else if (ldiam.equals("60")){
                selectedItem=1;
            }else if (ldiam.equals("65")){
                selectedItem=2;
            }else if (ldiam.equals("70")){
                selectedItem=3;
            }else {
                selectedItem=4;
            }
            lDiameter.setSelection(selectedItem);

        }

    }
    private void hideKeyboardView(View v) {
        InputMethodManager imm = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }



                    /*new Added for crash handle in popupwindow*/
    @Override
    public void onDestroyView(){
        if (getView()!=null){
            ViewGroup parent=(ViewGroup) getView().getParent();
            parent.removeAllViews();
        }
        super.onDestroyView();
    }

}
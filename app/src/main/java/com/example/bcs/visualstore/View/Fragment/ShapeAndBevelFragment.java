package com.example.bcs.visualstore.View.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.bcs.visualstore.PojoDatas.GetOrder;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.PojoDatas.ShapeAndBevelData;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShapeAndBevelFragment extends Fragment implements android.widget.RadioButton.OnCheckedChangeListener {
    RadioButton r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16;

    Button btn_OrdR;

    EditText edit_framelength,edit_frameheight,edit_dbl,edit_Lframelength,edit_Lframeheight,edit_Ldbl,edit_Rpdz,edit_Ryfh,edit_Rbvd,edit_Lpdz,edit_Lyfh,edit_Lbvd;
//Spinner spinner_type,spinner_shape;

    String Id,name,shapeItem,typeName;
    //public static int selectedItem = 0;
    public static String selectedRadioButton="";
    ShapeAndBevelData shapeAndBevelData=new ShapeAndBevelData();

    String PREFS_NAME = "MyPrefName";
    String PREFS_KEY = "MyPrefKey";
    GetOrder getOrder;
    ImageView imageView;
    TextView textView;
    int shapeSpinner=0;
//String frameName;

    public static int shape=0;

    View view;


    @Override
    public void onCheckedChanged(CompoundButton bb, boolean isChecked ) {
        if (isChecked) {
            if (bb.getId() == R.id.imageView1  ) {
                Log.e("sada","Its in1");
                Id="1";
                selectedRadioButton="1";
                // bb.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView2  ) {
                Log.e("sada","Its in2");
                Id="7";
                selectedRadioButton="2";
                // bb.setChecked(false);
                r1.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);
            }
            if (bb.getId() == R.id.imageView3  ) {
                Log.e("sada","Its in3");
                Id="15";
                selectedRadioButton="3";
                // bb.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r4.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView4  ) {
                Log.e("sada","Its in4");
                // bb.setChecked(false);
                Id="4";
                selectedRadioButton="4";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView5  ) {
                Log.e("sada","Its in5");
                // bb.setChecked(false);
                Id="14";
                selectedRadioButton="5";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r4.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView6  ) {
                Log.e("sada","Its in6");
                Id="14";
                selectedRadioButton="6";
                // bb.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r4.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView7  ) {
                Log.e("sada","Its in7");
                Id="4";
                selectedRadioButton="7";
                // bb.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r4.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView8  ) {
                Log.e("sada","Its in8");
                Id="5";
                selectedRadioButton="8";
                // bb.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r4.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView9  ) {
                Log.e("sada","Its in9");
                // bb.setChecked(false);
                Id="15";
                selectedRadioButton="9";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r4.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView10  ) {
                Log.e("sada","Its in10");
                Id="10";
                selectedRadioButton="10";
                // bb.setChecked(false);
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r4.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView11  ) {
                Log.e("sada","Its in11");
                // bb.setChecked(false);
                Id="6";
                selectedRadioButton="11";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r4.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView12  ) {
                Log.e("sada","Its in12");
                // bb.setChecked(false);
                Id="3";
                selectedRadioButton="12";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r4.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView13  ) {
                Log.e("sada","Its in13");
                // bb.setChecked(false);
                Id="11";
                selectedRadioButton="13";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r14.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView14  ) {
                Log.e("sada","Its in14");
                // bb.setChecked(false);
                Id="12";
                selectedRadioButton="14";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r4.setChecked(false);
                r15.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView15  ) {
                Log.e("sada","Its in15");
                // bb.setChecked(false);
                Id="13";
                selectedRadioButton="15";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r4.setChecked(false);
                r16.setChecked(false);

            }
            if (bb.getId() == R.id.imageView16  ) {
                Log.e("sada","Its in16");
                // bb.setChecked(false);
                Id="15";
                selectedRadioButton="16";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(false);
                r5.setChecked(false);
                r6.setChecked(false);
                r7.setChecked(false);
                r8.setChecked(false);
                r9.setChecked(false);
                r10.setChecked(false);
                r11.setChecked(false);
                r12.setChecked(false);
                r13.setChecked(false);
                r14.setChecked(false);
                r15.setChecked(false);
                r14.setChecked(false);

            }

        }//            if (bb.getId() == R.id.imageView2) {
//                r3.setChecked(false);
//               r4.setChecked(false);
//            }
//            if (radioGroup.getId() == R.id.imageView3) {
//                r1.setChecked(false);
//                r2.setChecked(false);
//            }
    }


    public ShapeAndBevelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = (View) inflater.inflate(R.layout.fragment_shape_and_bevel, container, false);
        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        imageView=(ImageView)toolbar.findViewById(R.id.menuLogo);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.backbtn));
        tooltxt.setText("SHAPE & BEVEL");


        //spinner_type=(Spinner) view.findViewById(R.id.spinner_type);
        r1=(RadioButton)view.findViewById(R.id.imageView1);
        r2=(RadioButton)view.findViewById(R.id.imageView2);
        r3=(RadioButton)view.findViewById(R.id.imageView3);
        r4=(RadioButton)view.findViewById(R.id.imageView4);
        r5=(RadioButton)view.findViewById(R.id.imageView5);
        r6=(RadioButton)view.findViewById(R.id.imageView6);
        r7=(RadioButton)view.findViewById(R.id.imageView7);
        r8=(RadioButton)view.findViewById(R.id.imageView8);
        r9=(RadioButton)view.findViewById(R.id.imageView9);
        r10=(RadioButton)view.findViewById(R.id.imageView10);
        r11=(RadioButton)view.findViewById(R.id.imageView11);
        r12=(RadioButton)view.findViewById(R.id.imageView12);
        r13=(RadioButton)view.findViewById(R.id.imageView13);
        r14=(RadioButton)view.findViewById(R.id.imageView14);
        r15=(RadioButton)view.findViewById(R.id.imageView15);
        r16=(RadioButton)view.findViewById(R.id.imageView16);

        btn_OrdR=(Button)view.findViewById(R.id.btn_orderR);
        edit_framelength =(EditText)view.findViewById(R.id.edit_1) ;
        edit_frameheight=(EditText)view.findViewById(R.id.edit_height);
        edit_dbl=(EditText)view.findViewById(R.id.edit_Rdbl);

        edit_Lframelength=(EditText)view.findViewById(R.id.edit_Llength);
        edit_Lframeheight=(EditText)view.findViewById(R.id.edit_Lheight);
        edit_Ldbl=(EditText)view.findViewById(R.id.edit_Ldbl);
        edit_Rpdz=(EditText)view.findViewById(R.id.edit_Rpdz);
        edit_Ryfh=(EditText)view.findViewById(R.id.edit_Ryfh);
        edit_Rbvd=(EditText)view.findViewById(R.id.edit_Rbvd);
        edit_Lpdz=(EditText)view.findViewById(R.id.edit_Lpdz);
        edit_Lyfh=(EditText)view.findViewById(R.id.edit_Lyfh);
        edit_Lbvd=(EditText)view.findViewById(R.id.edit_Lbvd);

        r1.setOnCheckedChangeListener( ShapeAndBevelFragment.this);
        r2.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r3.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r4.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r5.setOnCheckedChangeListener( ShapeAndBevelFragment.this);
        r6.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r7.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r8.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r9.setOnCheckedChangeListener( ShapeAndBevelFragment.this);
        r10.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r11.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r12.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r13.setOnCheckedChangeListener( ShapeAndBevelFragment.this);
        r14.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r15.setOnCheckedChangeListener(ShapeAndBevelFragment.this);
        r16.setOnCheckedChangeListener(ShapeAndBevelFragment.this);

        // final List<String> names=new ArrayList<>();

        SharedPreferences pre=getActivity().getSharedPreferences("hello",Context.MODE_PRIVATE);
        int names=pre.getInt("frameType",0);
//
//
//        System.out.println("name is "+names);
//
//        List<String> categories1 = new ArrayList<>();
//        categories1.add("Metal Frame");
//        categories1.add("Plastic Frame");
//        categories1.add("Nylor");
//        categories1.add("Rimless");
//        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories1);
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner_type.setAdapter(dataAdapter1);
//
//            spinner_type.setSelection(names);
        String radioValue=pre.getString("radioButton","");

        if (radioValue.equals("r1")){
            r1.setChecked(true);
        }else if (radioValue.equals("r2")){
            r2.setChecked(true);
        }else if (radioValue.equals("r3")){
            r3.setChecked(true);
        }else if (radioValue.equals("r4")){
            r4.setChecked(true);
        }else if (radioValue.equals("r5")){
            r5.setChecked(true);
        }else if (radioValue.equals("r6")){
            r6.setChecked(true);
        }else if (radioValue.equals("r7")){
            r7.setChecked(true);
        }else if (radioValue.equals("r8")){
            r8.setChecked(true);
        }else if (radioValue.equals("r9")){
            r9.setChecked(true);
        }else if (radioValue.equals("r10")){
            r10.setChecked(true);
        }else if (radioValue.equals("r11")){
            r11.setChecked(true);
        }else if (radioValue.equals("r12")){
            r12.setChecked(true);
        }else if (radioValue.equals("r13")){
            r13.setChecked(true);
        }else if (radioValue.equals("r14")){
            r14.setChecked(true);
        }else if (radioValue.equals("r15")){
            r15.setChecked(true);
        }else if (radioValue.equals("r16")){
            r16.setChecked(true);
        }else {

        }

        SharedPreferences preferencesReader1 = getActivity().getSharedPreferences("hione", Context.MODE_PRIVATE);
        String serializedDataFromLens1=preferencesReader1.getString(PREFS_KEY,null);
        getOrder=GetOrder.create(serializedDataFromLens1);
        if(getOrder!=null){
//            if(getOrder.getFrm_material()!=null && !getOrder.getFrm_material().equals("")){
//                name=getOrder.getFrm_material();
//                System.out.println("slected frame_material"+name);
//                if(name.equals("METAL")){
//
//                    shapeSpinner=0;
//
//                }else if(name.equals("PLASTIC")){
//                    shapeSpinner=1;
//
//                }else if(name.equals("NYLOR")){
//                    shapeSpinner=2;
//                }else {
//                    shapeSpinner=3;
//                }
//                spinner_type.setSelection(shapeSpinner);
//                }
            if(getOrder.getFrm_model()!=null && !getOrder.getFrm_model().equals("")){
                Id=getOrder.getFrm_model();
                String selectR= GetOrder.getFrameNamedef();
                System.out.println("data"+selectR);

                System.out.println("slected frame_Model"+Id);

                if (Id.equals("1") && selectR.equals("1")){
                    r1.setChecked(true);
                }else if (Id.equals("7") && selectR.equals("2")){
                    r2.setChecked(true);
                }else if (Id.equals("15") && selectR.equals("3")){
                    r3.setChecked(true);
                }else if (Id.equals("4") && selectR.equals("4")){
                    r4.setChecked(true);
                }else if (Id.equals("14") && selectR.equals("5")){
                    r5.setChecked(true);
                }else if (Id.equals("14") && selectR.equals("6")){
                    r6.setChecked(true);
                }else if (Id.equals("4") && selectR.equals("7")){
                    r7.setChecked(true);
                }else if (Id.equals("5") && selectR.equals("8")){
                    r8.setChecked(true);
                }else if (Id.equals("15") && selectR.equals("9")){
                    r9.setChecked(true);
                }else if (Id.equals("10") && selectR.equals("10")){
                    r10.setChecked(true);
                }else if (Id.equals("6") && selectR.equals("11")){
                    r11.setChecked(true);
                }else if (Id.equals("3") && selectR.equals("12")){
                    r12.setChecked(true);
                }else if (Id.equals("11") && selectR.equals("13")){
                    r13.setChecked(true);
                }else if (Id.equals("12")&&selectR.equals("14")){
                    r14.setChecked(true);
                }else if (Id.equals("13") && selectR.equals("15")){
                    r15.setChecked(true);
                }else if (Id.equals("15") && selectR.equals("16")){
                    r16.setChecked(true);
                }else {

                }
            }
        }

//        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                if(item.equals("Metal Frame")){
//                    name="METAL";
//                    selectedItem=0;
//
//                }
//                else if(item.equals("Plastic Frame")){
//                    name="PLASTIC";
//                    selectedItem=1;
//                }
//
//                else if(item.equals("Nylor")){
//                    name="NYLOR";
//                    selectedItem=2;
//                }
//                else {
//                    name="DRILLED";
//                    selectedItem=3;
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        btn_OrdR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pre=getActivity().getSharedPreferences("hello",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1=pre.edit();
                //editor1.putInt("frameType",selectedItem);
                editor1.putString("radioButton",selectedRadioButton);
                // editor1.putString("radio",Id);
                editor1.apply();

                //   shape=1;
                /*set data */
                ShapeAndBevelData shapeAndBevelData=new ShapeAndBevelData();
                //shapeAndBevelData.setFrame(name);
                shapeAndBevelData.setId(Id);
                shapeAndBevelData.setSelectedRadioButton(selectedRadioButton);


                /*set to sharePreference */
                String serializedData=shapeAndBevelData.serialize();
                SharedPreferences preferencesReader = getActivity().getSharedPreferences("fromShapre", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesReader.edit();
                editor.putString(PREFS_KEY, serializedData);
                editor.putString("fromShap","Shape");
                editor.apply();


                /*fragment call */
                OrderReviewFragment fragment= new OrderReviewFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}


package com.example.bcs.visualstore.View.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.bcs.visualstore.PojoDatas.Sharedpreference;
import com.example.bcs.visualstore.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashBoardFragment extends Fragment {
    Toolbar toolbar;
    TextView tooltxt;
    Button newButton,completebtn,incompletebtn,sentbtn;
    ImageView backBtn;
    View view;
    String PREFS_NAME = "MyPrefName";
    String PREFS_KEY = "MyPrefKey";


    public DashBoardFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       // container.removeAllViews();
        view= inflater.inflate(R.layout.fragment_dash_board, container, false);

        android.support.v7.widget.Toolbar toolbar=(android.support.v7.widget.Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        tooltxt.setText("VISUSTORE");
        backBtn=(ImageView) toolbar.findViewById(R.id.menuLogo);


        newButton=(Button)view.findViewById(R.id.button);
        completebtn=(Button)view.findViewById(R.id.completebtn);
        incompletebtn=(Button)view.findViewById(R.id.incompletebtn);
        sentbtn=(Button)view.findViewById(R.id.sentbtn);


        listener();
        return view;
    }

    public void listener(){



        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment=new LensOrderingFragment();
                fragmentMethod(fragment);

                SharedPreferences settings = Objects.requireNonNull(getActivity()).getSharedPreferences("hione", Context.MODE_PRIVATE);
                settings.edit().clear().apply();

                SharedPreferences setting = getActivity().getSharedPreferences("hi", Context.MODE_PRIVATE);
                setting.edit().clear().apply();

                SharedPreferences removeShape=getActivity().getSharedPreferences("hello",Context.MODE_PRIVATE);
                removeShape.edit().clear().apply();
                ShapeAndBevelFragment.shape=0;
                AdvanceOptionFragment.shape=0;

                SharedPreferences toShapeAndBevel=getActivity().getSharedPreferences("fromShapre",Context.MODE_PRIVATE);
                toShapeAndBevel.edit().clear().apply();

            }
        });


        incompletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences settings = Objects.requireNonNull(getActivity()).getSharedPreferences("hione", Context.MODE_PRIVATE);
//                settings.edit().clear().apply();
                Bundle bundle = new Bundle();
                bundle.putString("status","1");
                Fragment fragment=new CartResultsFragment();
                fragment.setArguments(bundle);
                fragmentMethod(fragment);
                Sharedpreference.onStorePreference(getActivity(),Sharedpreference.Order,"1");
            }
        });

        completebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences settings = Objects.requireNonNull(getActivity()).getSharedPreferences("hione", Context.MODE_PRIVATE);
//                settings.edit().clear().apply();
                Bundle bundle = new Bundle();
                bundle.putString("status","2");
                Fragment fragment=new CartResultsFragment();
                fragment.setArguments(bundle);
                fragmentMethod(fragment);
                Sharedpreference.onStorePreference(getActivity(),Sharedpreference.Order,"2");
            }
        });

        sentbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle bundle=new Bundle();
                bundle.putString("sentOrders","sent orders");
                Fragment fragment=new SearchOrdersFragment();
                fragment.setArguments(bundle);
                fragmentMethod(fragment);
            }
        });

    }

    public void fragmentMethod(Fragment fragment){
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
        transaction.addToBackStack(null);
        // this will manage backstack
        transaction.commit();
    }

}

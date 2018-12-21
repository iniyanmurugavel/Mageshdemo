package com.example.bcs.visualstore.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bcs.visualstore.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=(View) inflater.inflate(R.layout.fragment_cart, container, false);

        Toolbar toolbar=(Toolbar)getActivity().findViewById(R.id.toolbar);
        TextView tooltxt=(TextView)toolbar.findViewById(R.id.tooltxt);
        tooltxt.setText("CARD");

        RelativeLayout search=(RelativeLayout) view.findViewById(R.id.relative);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CartResultsFragment fragment= new CartResultsFragment();
//                android.support.v4.app.FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_Container, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
//                transaction.addToBackStack(null);  // this will manage backstack
//                transaction.commit();
            }
        });

        return view;
    }

}

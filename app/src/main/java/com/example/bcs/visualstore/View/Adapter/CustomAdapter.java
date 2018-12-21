package com.example.bcs.visualstore.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bcs.visualstore.PojoDatas.Sharedpreference;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.PojoDatas.RowItem;
import com.example.bcs.visualstore.Utils.ApiUtils;
import com.example.bcs.visualstore.View.Fragment.CartResultsFragment;
import com.example.bcs.visualstore.View.Fragment.SearchOrdersFragment;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    CartResultsFragment context;
    ArrayList<String> order_u_id1, order_id, order_date,  status1, Consname,lenstype;
    ArrayList<Integer> id;
    private static LayoutInflater inflater=null;
    private RadioButton selected = null;


    private Activity activity;
//    public ListAdapter(@NonNull SearchOrdersFragment context, int 1resource, List<RowItem> items) {
//        super(context, resource,items);
//        this.context=context;
//    }

    public CustomAdapter(Activity activity1,CartResultsFragment context,ArrayList<String>order_U_id, ArrayList<String>order_id,ArrayList<String> order_date,ArrayList<String> status,ArrayList<String>CustName,ArrayList<Integer> id) {
        super();
        this.order_u_id1=order_U_id;
        this.order_id=order_id;
        this.context=context;
        this.order_date=order_date;
        this.id=id;
        this.status1=status;
        this.Consname=CustName;
        activity= activity1;
//        this.OrderType=orderType;
//        this.AddedDate=AddedDate;

        inflater = ( LayoutInflater )context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class Holder {

        TextView txtOne,txtTwo,txtThree,txtFour;

        RadioGroup radioGroup;
        RadioButton radioButton;
        CheckBox checkBox;
    }

    @Override
    public int getCount() {
        return order_u_id1.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull ViewGroup parent) {
        final Holder holder=new Holder();
        final View convertView;


        convertView = inflater.inflate(R.layout.list_items, null);
//            holder = new ViewHolder();




//        holder.radioGroup=(RadioGroup)convertView.findViewById(R.id.ss);
        holder.radioButton=(RadioButton)convertView.findViewById(R.id.check);
        holder.txtOne = (TextView) convertView.findViewById(R.id.txt_date);
        holder.txtTwo = (TextView) convertView.findViewById(R.id.txt_orderId);
        holder.txtThree = (TextView) convertView.findViewById(R.id.txt_status);
        holder.txtFour = (TextView) convertView.findViewById(R.id.txt_ConName);
//        holder.txtFive = (TextView) convertView.findViewById(R.id.textFive);
////            holder.txtSix = (TextView) convertView.findViewById(R.id.textSix);
////            holder.txtSeven = (TextView) convertView.findViewById(R.id.textSeven);
////            holder.txtEight = (TextView) convertView.findViewById(R.id.textEight);
//        holder.txtNine = (TextView) convertView.findViewById(R.id.textNine);

        holder.txtOne.setText(order_date.get(position));
        holder.txtTwo.setText(order_id.get(position));
        if(status1.get(position).equals("1")){
            holder.txtThree.setText("Incomplete");
        }else{
            holder.txtThree.setText("Complete");
        }

        holder.txtFour.setText(Consname.get(position));
        holder.radioButton.setId(id.get(position));
        ApiUtils.INCOMPLETE=0;

        if (position==getCount()){
            if (selected==null){
                holder.radioButton.setChecked(true);
                selected=holder.radioButton;
//                ApiUtils.INCOMPLETE=0;
                System.out.println("id of inccomplete1"+ApiUtils.INCOMPLETE);
            }
        }

        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected!=null){
                    selected.setChecked(false);

                    }

                    holder.radioButton.setChecked(true);
                    selected=holder.radioButton;

                    System.out.println("checked" + holder.radioButton.getId());
                    ApiUtils.INCOMPLETE= holder.radioButton.getId();
                System.out.println("api data"+ApiUtils.INCOMPLETE);
                    Sharedpreference.onStorePreference(activity,Sharedpreference.order_id,""+holder.txtTwo.getText().toString());




            }
        });


//        holder.radioButton.setOnCheckedChangeListener(
//                new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        if (b) {
//
//                            System.out.println("checked" + holder.radioButton.getId());
//                            ApiUtils.INCOMPLETE= holder.radioButton.getId();
//                            Sharedpreference.onStorePreference(activity,Sharedpreference.order_id,""+holder.txtTwo.getText().toString());
////                            Log.d("VALUES",""+holder.txtTwo.getText().toString());
//                        } else {
//                            System.out.println("unchecked");
//                        }
//                    }
//
//                });


        return convertView;
    }
}
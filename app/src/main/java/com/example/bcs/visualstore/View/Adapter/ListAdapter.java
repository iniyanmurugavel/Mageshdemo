package com.example.bcs.visualstore.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.PojoDatas.RowItem;
import com.example.bcs.visualstore.View.Fragment.SearchOrdersFragment;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    SearchOrdersFragment context;
    ArrayList<String> order_reference1, order_id, order_date, lenstype1, status1, employee1, OrderType,AddedDate;
    private static LayoutInflater inflater=null;



//    public ListAdapter(@NonNull SearchOrdersFragment context, int 1resource, List<RowItem> items) {
//        super(context, resource,items);
//        this.context=context;
//    }

    public ListAdapter(SearchOrdersFragment context, ArrayList<String> order_reference, ArrayList<String> order_id, ArrayList<String> order_date, ArrayList<String> lenstype, ArrayList<String> status, ArrayList<String> employee, ArrayList<String> orderType,ArrayList<String> AddedDate) {
        super();
        this.order_reference1=order_reference;
        this.order_id=order_id;
        this.context=context;
        this.order_date=order_date;
        this.lenstype1=lenstype;
        this.status1=status;
        this.employee1=employee;
        this.OrderType=orderType;
        this.AddedDate=AddedDate;

        inflater = ( LayoutInflater )context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class Holder {

        TextView txtOne,txtTwo,txtThree,txtFour,txtFive,txtSix,txtSeven,txtEight,txtNine;
    }

    @Override
    public int getCount() {
        return order_reference1.size();
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
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        Holder holder=new Holder();
        final View convertView;


        convertView = inflater.inflate(R.layout.listlines, null);
//            holder = new ViewHolder();
        holder.txtOne = (TextView) convertView.findViewById(R.id.textOne);
        holder.txtTwo = (TextView) convertView.findViewById(R.id.textTwo);
        holder.txtThree = (TextView) convertView.findViewById(R.id.textThree);
//            holder.txtFour = (TextView) convertView.findViewById(R.id.textFour);
        holder.txtFive = (TextView) convertView.findViewById(R.id.textFive);
//            holder.txtSix = (TextView) convertView.findViewById(R.id.textSix);
//            holder.txtSeven = (TextView) convertView.findViewById(R.id.textSeven);
//            holder.txtEight = (TextView) convertView.findViewById(R.id.textEight);
    //    holder.txtNine = (TextView) convertView.findViewById(R.id.textNine);

        holder.txtOne.setText(order_date.get(position));
        holder.txtTwo.setText(order_reference1.get(position));
        holder.txtThree.setText(order_id.get(position));
//            holder.txtFour.setText(employee1.get(position));
        holder.txtFive.setText(status1.get(position));
        //           holder.txtNine.setText(status1.get(position));





        return convertView;
    }
}
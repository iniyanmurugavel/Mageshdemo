package com.example.bcs.visualstore.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bcs.visualstore.PojoDatas.CallBackData;
import com.example.bcs.visualstore.PojoDatas.CoatingList;
import com.example.bcs.visualstore.PojoDatas.CoatingModel;
import com.example.bcs.visualstore.R;
import com.example.bcs.visualstore.View.Fragment.LensOrderingFragment;

import java.util.List;

public class TintAdapter extends BaseAdapter {


    //private final List<DataModel> list;
    private final LensOrderingFragment context;
    //  private DataModel dataModel;
    private  final List<CallBackData.Data> tint_list ;
    private static LayoutInflater inflater=null;
    CallBackData.Data coatingModel;


    public TintAdapter(LensOrderingFragment context, List<CallBackData.Data> list){
        this.context=context;
        this.tint_list=list;
        inflater = ( LayoutInflater )context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return tint_list.size();
    }

    @Override
    public Object getItem(int position) {
        return tint_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();

        coatingModel=tint_list.get(position);
        convertView = inflater.inflate(R.layout.spinnerlist, null);

         holder.name=convertView.findViewById(R.id.textOne);
         holder.name.setText(coatingModel.getName());

        return convertView;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public class Holder {
        TextView name;
    }

}
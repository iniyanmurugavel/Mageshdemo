package com.example.bcs.visualstore.View.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bcs.visualstore.PojoDatas.DataModel;
import com.example.bcs.visualstore.R;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<DataModel> {

    Context context;
    List<DataModel>list;
    DataModel rowItem;



    public EmployeeAdapter(@NonNull Context context, List<DataModel> items) {
        super(context, 0,items);
        this.context=context;
    }
    private class ViewHolder {

        TextView name;
        ImageView edit;
        ImageView bin;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;
         rowItem = getItem(position);


        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.employee_items, null);
            holder = new ViewHolder();
            holder.name=convertView.findViewById(R.id.name);
            holder.edit = (ImageView) convertView.findViewById(R.id.edit);
            holder.bin = (ImageView) convertView.findViewById(R.id.bin);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        assert rowItem != null;
        holder.name.setText(rowItem.getName());
        holder.edit.setImageResource(R.drawable.edit_icon);
        holder.bin.setImageResource(R.drawable.bin_icon);



        return convertView;
    }
}


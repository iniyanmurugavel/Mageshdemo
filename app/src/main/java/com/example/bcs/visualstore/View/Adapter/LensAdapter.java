package com.example.bcs.visualstore.View.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.bcs.visualstore.PojoDatas.LeftLenTypeListView;
import com.example.bcs.visualstore.View.Fragment.LensOrderingFragment;
import com.example.bcs.visualstore.PojoDatas.RightLenTypeListView;
import com.example.bcs.visualstore.R;

import java.util.ArrayList;
import java.util.List;

public class LensAdapter extends BaseAdapter implements Filterable {


    private final LensOrderingFragment context;
    public List<LeftLenTypeListView> Lens_list ;
    private static LayoutInflater inflater=null;
    public List<LeftLenTypeListView> orig;
    private Filter filter;
    private String firstLetter;
    LeftLenTypeListView rightLenTypeListView;


    public LensAdapter(LensOrderingFragment context,List<LeftLenTypeListView> Lens_list){
        super();
        this.context=context;
        this.orig=this.Lens_list=Lens_list;
        inflater = ( LayoutInflater )context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return Lens_list.size();
    }

    @Override
    public Object getItem(int position) {
        return Lens_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {

        if(filter == null) {
            filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    List<LeftLenTypeListView> myFilteredAppList = new ArrayList<LeftLenTypeListView>();
                    constraint = constraint.toString().toLowerCase();

                    if (constraint.length() == 0) {
                        myFilteredAppList.addAll(orig);
                    }

                    for (LeftLenTypeListView appInfo : orig) {
                        String somefield = appInfo.getName();
                        if (somefield.toLowerCase().contains(constraint.toString())) {
                            myFilteredAppList.add(appInfo);
                        }
                    }
                    results.count = myFilteredAppList.size();
                    results.values = myFilteredAppList;
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results.count==0){
                        notifyDataSetInvalidated();
                    }else {

                        Lens_list = (List<LeftLenTypeListView>)results.values;
                        notifyDataSetChanged();
                    }

                }
            };
        }
        return filter;
        // return null;
    }
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public class Holder {

        TextView name;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        Holder holder=new Holder();
        View convertView = null;
        if (convertView==null){

            convertView = inflater.inflate(R.layout.spinnerlist, null);
        }




        rightLenTypeListView= (LeftLenTypeListView) getItem(position);
        holder.name=convertView.findViewById(R.id.textOne);
      //  holder.imageView=convertView.findViewById(R.id.listCircleImageView);
        holder.name.setText(rightLenTypeListView.getName());

        firstLetter=String.valueOf(rightLenTypeListView.getName().charAt(0));
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color =generator.getColor(rightLenTypeListView.getName());
        TextDrawable drawable=TextDrawable.builder().buildRound(firstLetter,color);
       // holder.imageView.setImageDrawable(drawable);

        System.out.println("crashed    size "+holder.name.getText());

        return convertView;
    }
}
package net.a6te.lazycoder.savejourney.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.a6te.lazycoder.savejourney.R;
import net.a6te.lazycoder.savejourney.modelClass.Buss;

import java.util.ArrayList;

/**
 * Created by ashraf on 19/5/17.
 */

public class AdapterBuss extends ArrayAdapter<Buss>{
    private Context context;
    private ArrayList<Buss> bussList;
    public AdapterBuss(@NonNull Context context, ArrayList<Buss> objects) {
        super(context, R.layout.custom_buss_lv, objects);

        this.context = context;
        this.bussList = objects;
    }

    class ViewHolder{
        TextView bussNameTv;
        TextView bussAverageStarTv;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_buss_lv,parent,false);

            holder.bussNameTv = (TextView) convertView.findViewById(R.id.bussNameTv);
            holder.bussAverageStarTv = (TextView) convertView.findViewById(R.id.averageStartTv);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bussNameTv.setText(bussList.get(position).getBussName());
        holder.bussAverageStarTv.setText(bussList.get(position).getAverageStar());
        return convertView;
    }
}

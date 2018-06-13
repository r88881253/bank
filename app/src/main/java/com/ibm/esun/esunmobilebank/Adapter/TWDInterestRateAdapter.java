package com.ibm.esun.esunmobilebank.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibm.esun.esunmobilebank.R;

public class TWDInterestRateAdapter extends BaseAdapter {

    private String[][] ElementsData ;   //資料
    private LayoutInflater inflater;    //加載layout
    private int indentionBase;          //item缩排

    //優化Listview 避免重新加載
    //這邊宣告你會動到的Item元件
    static class ViewHolder{
        LinearLayout depositLinearLayout;
        TextView category;
        TextView floatingRate;
        TextView fixedRate;
    }

    //初始化
    public TWDInterestRateAdapter(String[][] data, LayoutInflater inflater){
        this.ElementsData = data;
        this.inflater = inflater;
        indentionBase = 100;
    }


    @Override
    public int getCount() {
        return ElementsData.length;
    }

    @Override
    public Object getItem(int position) {
        return  ElementsData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_twd_deposit_interest_rate, null);
            holder.category = (TextView) convertView.findViewById(R.id.deposit_category);
            holder.floatingRate = (TextView) convertView.findViewById(R.id.deposit_floating_rate);
            holder.fixedRate = (TextView) convertView.findViewById(R.id.deposit_fixed_rate);
            holder.depositLinearLayout = (LinearLayout) convertView.findViewById(R.id.deposit_linearLayout);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.category.setText(ElementsData[position][0]);
        holder.floatingRate.setText(ElementsData[position][0]);
        holder.fixedRate.setText(ElementsData[position][2]);

        return convertView;
    }


}

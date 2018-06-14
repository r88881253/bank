package com.ibm.esun.esunmobilebank.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibm.esun.esunmobilebank.R;
import com.ibm.esun.esunmobilebank.model.DepositeRate;

import java.util.List;

public class DepositAdapter extends BaseAdapter {

//    private List<String[]> data;
    private List<DepositeRate> data;
    private LayoutInflater inflater;    //加載layout

    //優化Listview 避免重新加載
    //這邊宣告你會動到的Item元件
    static class ViewHolder{
        LinearLayout depositLinearLayout;
        TextView category;
        TextView floatingRate;
        TextView fixedRate;
    }

    //初始化
    public DepositAdapter(List<DepositeRate> data, LayoutInflater inflater){
        this.data = data;
        this.inflater = inflater;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return  data.get(position);
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

        holder.category.setText(data.get(position).getTitle());
        holder.floatingRate.setText(data.get(position).getfRate());
        holder.fixedRate.setText(data.get(position).getmRate());

        return convertView;
    }


}

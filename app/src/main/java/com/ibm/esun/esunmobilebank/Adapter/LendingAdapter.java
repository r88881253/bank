package com.ibm.esun.esunmobilebank.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibm.esun.esunmobilebank.R;
import com.ibm.esun.esunmobilebank.model.LoanRate;

import java.util.List;

public class LendingAdapter extends BaseAdapter {

    private List<LoanRate> data;
    private LayoutInflater inflater;    //加載layout

    //優化Listview 避免重新加載
    //這邊宣告你會動到的Item元件
    static class ViewHolder{
        LinearLayout depositLinearLayout;
        TextView category;
        TextView interestRate;
    }

    //初始化
    public LendingAdapter(List<LoanRate> data, LayoutInflater inflater){
        this.data = data;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LendingAdapter.ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listitem_twd_lending_interest_rate, null);
            holder.category = (TextView) convertView.findViewById(R.id.lending_category);
            holder.interestRate = (TextView) convertView.findViewById(R.id.lending_rate);
            holder.depositLinearLayout = (LinearLayout) convertView.findViewById(R.id.lending_linearLayout);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.category.setText(data.get(position).getTitle());
        holder.interestRate.setText(data.get(position).getRate());

        return convertView;
    }
}

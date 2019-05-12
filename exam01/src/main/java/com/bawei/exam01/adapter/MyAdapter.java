package com.bawei.exam01.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.exam01.R;
import com.bawei.exam01.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class MyAdapter extends BaseAdapter {
    private ArrayList<Bean> list;
    private Context context;

    public MyAdapter(ArrayList<Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case 0:
                ViewHolder01 holder01 = null;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item02_layout, null);
                    holder01 = new ViewHolder01();
                    holder01.tv = convertView.findViewById(R.id.tv);
                    holder01.img02 = convertView.findViewById(R.id.img);
                    convertView.setTag(holder01);
                } else {
                    holder01 = (ViewHolder01) convertView.getTag();
                }
                Bean bean = list.get(position);
                holder01.tv.setText(bean.getName());
                Glide.with(context).load(bean.getImageUrl()).into(holder01.img02);
                break;
            case 1:
                ViewHolder02 holder02 = null;
                if (convertView == null) {
                    convertView = View.inflate(context, R.layout.item01_layout, null);
                    holder02 = new ViewHolder02();
                    holder02.tv02 = convertView.findViewById(R.id.tv01);
                    convertView.setTag(holder02);
                } else {
                    holder02 = (ViewHolder02) convertView.getTag();
                }
                holder02.tv02.setText(list.get(position).getName());
                break;
        }
        return convertView;
    }

    class ViewHolder01 {
        ImageView img02;
        TextView tv;
    }

    class ViewHolder02 {
        TextView tv02;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}

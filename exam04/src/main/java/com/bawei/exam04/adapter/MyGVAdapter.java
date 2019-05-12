package com.bawei.exam04.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bawei.exam04.R;
import com.bawei.exam04.bean.Zhong;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
 *@Auther:cln
 *@Date: 时间
 *@Description:功能
 * */
public class MyGVAdapter extends BaseAdapter {
    private ArrayList<Zhong> zlist;
    private Context context;

    public MyGVAdapter(ArrayList<Zhong> zlist, Context context) {
        this.zlist = zlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return zlist.size();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.grid_layout, null);
            holder = new ViewHolder();
            holder.img = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(zlist.get(position).getHeader()).into(holder.img);
        return convertView;
    }

    class ViewHolder {
        ImageView img;
    }
}

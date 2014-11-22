package com.apj.wkb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apj.wkb.R;
import com.apj.wkb.entity.CourserItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class HomeAdapter extends BaseAdapter {

    List<CourserItem> mData;
    Context mContext;

    public HomeAdapter(Context context, List<CourserItem> data) {
        super();
        this.mData=data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.grid_item_recommend, null, false);
            holder.grid_item_img = (ImageView) convertView.findViewById(R.id.grid_item_img);
            holder.grid_item_title = (TextView) convertView.findViewById(R.id.grid_item_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CourserItem item = mData.get(position);
        holder.grid_item_title.setText(item.getTitle());
        Picasso.with(mContext).load(item.getContentUrl()).placeholder(R.drawable.ico_no_content).centerCrop().into(holder.grid_item_img);
        return convertView;
    }

    public class ViewHolder{
        ImageView grid_item_img;
        TextView grid_item_title;
    }

}

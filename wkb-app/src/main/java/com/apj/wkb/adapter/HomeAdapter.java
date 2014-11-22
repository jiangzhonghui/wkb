package com.apj.wkb.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apj.wkb.R;
import com.apj.wkb.entity.CourserItem;
import com.squareup.picasso.Picasso;
import android.content.Context;
import android.view.LayoutInflater;
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
        return null;
    }
}

package com.apj.wkb.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apj.wkb.R;
import com.apj.wkb.VideoPlayerActivity;
import com.apj.wkb.entity.CourserItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class HomeAdapter extends BaseAdapter {

    List<CourserItem> mData;
    Context mContext;
    DisplayImageOptions options;
    public HomeAdapter(Context context, List<CourserItem> data) {
        super();
        this.mContext =  context;
        this.mData=data;
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.thumb)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public void addData(List<CourserItem> data){
        mData.clear();
        mData.addAll(data);
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
        //CourserItem item = mData.get(position);
        final CourserItem item=mData.get(position);
        holder.grid_item_title.setText(item.getTitle());
        ImageLoader.getInstance().displayImage(item.getPicUrl(), holder.grid_item_img, options);
        //Picasso.with(mContext).load(item.getPicUrl()).placeholder(R.drawable.ico_no_content).centerCrop().into(holder.grid_item_img);
        convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                     Intent intent= new Intent(mContext, VideoPlayerActivity.class);
                     intent.putExtra("url",item.getContentId());
                     intent.putExtra("title",item.getTitle());
                     mContext.startActivity(intent);
                 }
         });
        return convertView;
    }

    public class ViewHolder{
        ImageView grid_item_img;
        TextView grid_item_title;
    }

}

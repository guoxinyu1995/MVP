package com.example.day07_mvp02.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day07_mvp02.R;
import com.example.day07_mvp02.bean.UserBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PersonAdaper extends BaseAdapter {
    private List<UserBean.PostsBean> mData;
    private Context mContext;

    public PersonAdaper(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    //刷新
    public void setmData(List<UserBean.PostsBean> datas){
        mData.clear();
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    //追加
    public void addmData(List<UserBean.PostsBean> datas){
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public UserBean.PostsBean getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
            holder = new ViewHolder(convertView);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bind(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView date;
        ImageView image;

        public ViewHolder(View convertView) {
            title  = convertView.findViewById(R.id.title);
            date = convertView.findViewById(R.id.date);
            image = convertView.findViewById(R.id.image);
            convertView.setTag(this);
        }

        public void bind(UserBean.PostsBean item) {
            title.setText(item.getTitle());
            date.setText(item.getDate());
            ImageLoader.getInstance().displayImage(item.getCustom_fields().getThumb_c().get(0),image);
        }
    }
}

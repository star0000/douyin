package com.wangshifei.douyin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wangshifei.douyin.R;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class ListAdapter extends BaseAdapter {

    private Context context;
    private List<String> stringList;
    private TextView searchText;


    public ListAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public int getCount() {
        return stringList != null ? stringList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            view = View.inflate(context, R.layout.text_listview, null);
        }else{
            view = convertView;
        }
         searchText = view.findViewById(R.id.search_text);
         searchText.setText(stringList.get(position).toString());
         return view;
    }
}

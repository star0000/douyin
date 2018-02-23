package com.wangshifei.douyin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wangshifei.douyin.bean.SearchBean;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/23.
 */

public class NewAdapter extends XRecyclerView.Adapter<NewAdapter.ViewHolder> {

    public Context context;
    public List<SearchBean.CategoryListBean> categoryList;

    public NewAdapter(Context context, List<SearchBean.CategoryListBean> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.init_mostnew_xrv, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String img = categoryList.get(position).getAweme_list().get(position).getVideo().getOrigin_cover().getUrl_list().get(0);
        holder.newSimple.setImageURI(img);
    }

    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView newSimple;
        public ViewHolder(View itemView) {
            super(itemView);
            newSimple = itemView.findViewById(R.id.new_simple);
        }
    }

}

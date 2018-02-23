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

public class HotAdapter extends XRecyclerView.Adapter<HotAdapter.ViewHolder>{

    public Context context;
    public List<SearchBean.CategoryListBean> categoryList;
    private ViewHolder holder;

    public HotAdapter(Context context, List<SearchBean.CategoryListBean> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.init_mosthot_xrv, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String img = categoryList.get(position).getAweme_list().get(position).getVideo().getOrigin_cover().getUrl_list().get(0);
        holder.hotSimple.setImageURI(img);
    }

    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView hotSimple;

        public ViewHolder(View itemView) {
            super(itemView);
            hotSimple = itemView.findViewById(R.id.hot_simple);
        }
    }

}

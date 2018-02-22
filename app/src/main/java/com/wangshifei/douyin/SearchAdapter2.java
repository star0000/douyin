package com.wangshifei.douyin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wangshifei.douyin.bean.SearchBean;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class SearchAdapter2 extends XRecyclerView.Adapter<SearchAdapter2.ViewHolder> {

    private Context context;
    private List<SearchBean.CategoryListBean> categoryList;

    public SearchAdapter2(Context context, List<SearchBean.CategoryListBean> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewpage2 = View.inflate(context, R.layout.search_xrv_page2, null);
        ViewHolder viewHolder = new ViewHolder(viewpage2);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.descTv.setText(categoryList.get(position).getDesc());
        holder.chaName.setText(categoryList.get(1).getChallenge_info().getCha_name());
        holder.userCount.setText(categoryList.get(1).getChallenge_info().getUser_count()+"");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, GridLayout.HORIZONTAL, false);
        holder.searchXrv3.setLayoutManager(gridLayoutManager);
        SearchAdapter3 searchAdapter3 = new SearchAdapter3(context,categoryList);
        holder.searchXrv3.setAdapter(searchAdapter3);

        searchAdapter3.setOnItemListener(new SearchAdapter3.OnItemListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(context,VideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position",position);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList != null ? categoryList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView descTv;
        private final XRecyclerView searchXrv3;
        private final TextView chaName;
        private final TextView userCount;

        public ViewHolder(View itemView) {
            super(itemView);
            descTv = itemView.findViewById(R.id.desc_tv);
            chaName = itemView.findViewById(R.id.cha_name);
            userCount = itemView.findViewById(R.id.user_count);
            searchXrv3 = itemView.findViewById(R.id.search_xrv_3);
        }
    }

}

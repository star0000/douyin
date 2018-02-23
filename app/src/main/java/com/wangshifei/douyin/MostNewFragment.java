package com.wangshifei.douyin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wangshifei.douyin.bean.SearchBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class MostNewFragment extends Fragment {

    private View newView;
    private XRecyclerView mostNewXrv;
    private List<SearchBean.CategoryListBean> categoryList;
    private NewAdapter newAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        newView = inflater.inflate(R.layout.fragment_most_new, container, false);
        mostNewXrv = newView.findViewById(R.id.most_new_xrv);
        EventBus.getDefault().register(this);
        return newView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayout.VERTICAL, false);
        mostNewXrv.setLayoutManager(gridLayoutManager);
        newAdapter = new NewAdapter(getContext(),categoryList);
        mostNewXrv.setAdapter(newAdapter);

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getNewMessageEvent(EventBusMessage eventBusMessage){
        categoryList = eventBusMessage.CategoryList;
        Log.e("WSF2", "getHotMessageEvent: "+categoryList.size() );

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

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

public class MostHotFragment extends Fragment {

    private View hotView;
    private XRecyclerView mostHotXrv;
    private List<SearchBean.CategoryListBean> categoryList;
    private HotAdapter hotAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        hotView = inflater.inflate(R.layout.fragment_most_hot, container, false);
        mostHotXrv = hotView.findViewById(R.id.most_hot_xrv);
        EventBus.getDefault().register(this);
        return hotView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayout.VERTICAL, false);
        mostHotXrv.setLayoutManager(gridLayoutManager);
        hotAdapter = new HotAdapter(getContext(),categoryList);
        mostHotXrv.setAdapter(hotAdapter);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getHotMessageEvent(EventBusMessage eventBusMessage){
        categoryList = eventBusMessage.CategoryList;
        Log.e("WSF1", "getHotMessageEvent: "+categoryList.size() );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}

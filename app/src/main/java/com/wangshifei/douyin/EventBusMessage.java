package com.wangshifei.douyin;

import com.wangshifei.douyin.bean.SearchBean;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class EventBusMessage {

    public List<SearchBean.CategoryListBean> CategoryList;

    public EventBusMessage(List<SearchBean.CategoryListBean> categoryList) {
        this.CategoryList = categoryList;
    }

}

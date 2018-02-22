package com.wangshifei.douyin.view;

import com.wangshifei.douyin.bean.SearchBean;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public interface SearchViewInterface {

    void Searchsuccess(List<SearchBean.CategoryListBean> categoryList);
    void Searchfailed(Exception e);

}

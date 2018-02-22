package com.wangshifei.douyin.presenter;

import com.wangshifei.douyin.bean.SearchBean;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public interface SearchPresenterInterface {

    void SearchSuccess(List<SearchBean.CategoryListBean> categoryList);
    void SearchFailed(Exception e);

}

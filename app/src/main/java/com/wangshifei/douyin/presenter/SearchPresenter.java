package com.wangshifei.douyin.presenter;

import com.wangshifei.douyin.bean.SearchBean;
import com.wangshifei.douyin.model.SearchModel;
import com.wangshifei.douyin.view.SearchViewInterface;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class SearchPresenter implements SearchPresenterInterface {

    private SearchViewInterface searchViewInterface;
    private final SearchModel searchModel;

    public SearchPresenter(SearchViewInterface searchViewInterface) {
        this.searchViewInterface = searchViewInterface;
        searchModel = new SearchModel();
    }

    public void excuteQuest(){
        searchModel.doPost(this);
    }

    @Override
    public void SearchSuccess(List<SearchBean.CategoryListBean> categoryList) {
        if(categoryList != null){
            searchViewInterface.Searchsuccess(categoryList);
        }
    }

    @Override
    public void SearchFailed(Exception e) {
        searchViewInterface.Searchfailed(e);
    }

    public void catchView(){
        if(searchViewInterface != null){
            searchViewInterface = null;
        }
    }

}

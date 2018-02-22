package com.wangshifei.douyin.presenter;

import com.wangshifei.douyin.bean.SearchBannerBean;
import com.wangshifei.douyin.model.SearchBannerModel;
import com.wangshifei.douyin.view.SearchBannerViewInterface;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class SearchBannerPresenter implements SearchBannerPresenterInterface{

    private SearchBannerViewInterface searchBannerViewInterface;
    private final SearchBannerModel searchBannerModel;

    public SearchBannerPresenter(SearchBannerViewInterface searchBannerViewInterface) {
        this.searchBannerViewInterface = searchBannerViewInterface;
        searchBannerModel = new SearchBannerModel();
    }

    public void excuteQuest(){
        searchBannerModel.doPost(this);
    }



    @Override
    public void SearchBannerSuccess(List<SearchBannerBean.BannerBean> bannerBeanList) {
         if(bannerBeanList != null){
             searchBannerViewInterface.SearchBannersuccess(bannerBeanList);
         }
    }

    @Override
    public void SearchBannerFailed(Exception e) {
            searchBannerViewInterface.SearchBannerfailed(e);
    }

    public void catchView(){
        if(searchBannerViewInterface != null){
            searchBannerViewInterface = null;
        }
    }
}

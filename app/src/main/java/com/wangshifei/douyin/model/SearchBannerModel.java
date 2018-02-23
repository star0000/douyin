package com.wangshifei.douyin.model;

import com.wangshifei.douyin.api.Api;
import com.wangshifei.douyin.retrofit.RetrofitHelper;
import com.wangshifei.douyin.url.UrlClass;
import com.wangshifei.douyin.bean.SearchBannerBean;
import com.wangshifei.douyin.presenter.SearchBannerPresenterInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class SearchBannerModel {

    public void doPost(final SearchBannerPresenterInterface searchBannerPresenterInterface){
        Retrofit retrofit = RetrofitHelper.getRetrofit(UrlClass.URL_BASE);
        Api api = retrofit.create(Api.class);
        api.getSearchBannerString()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBannerBean>() {
                    @Override
                    public void accept(SearchBannerBean searchBannerBean) throws Exception {
                        if(searchBannerBean != null){
                            List<SearchBannerBean.BannerBean> bannerBeanList = searchBannerBean.getBanner();
                            searchBannerPresenterInterface.SearchBannerSuccess(bannerBeanList);
                        }
                    }
                });

    }

}

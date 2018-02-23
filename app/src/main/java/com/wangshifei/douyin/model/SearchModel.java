package com.wangshifei.douyin.model;

import com.wangshifei.douyin.api.Api;
import com.wangshifei.douyin.retrofit.RetrofitHelper;
import com.wangshifei.douyin.url.UrlClass;
import com.wangshifei.douyin.bean.SearchBean;
import com.wangshifei.douyin.presenter.SearchPresenterInterface;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class SearchModel {

    public void doPost(final SearchPresenterInterface searchPresenterInterface){
        Retrofit retrofit = RetrofitHelper.getRetrofit(UrlClass.URL_BASE);
        Api api = retrofit.create(Api.class);
        api.getSearchString()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(SearchBean searchBean) throws Exception {
                        if(searchBean != null){
                            List<SearchBean.CategoryListBean> categoryList = searchBean.getCategory_list();
                            searchPresenterInterface.SearchSuccess(categoryList);
                        }
                    }
                });
    }

}

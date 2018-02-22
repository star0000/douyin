package com.wangshifei.douyin.presenter;

import com.wangshifei.douyin.bean.SearchBannerBean;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public interface SearchBannerPresenterInterface {

    void SearchBannerSuccess(List<SearchBannerBean.BannerBean> bannerBeanList);
    void SearchBannerFailed(Exception e);

}

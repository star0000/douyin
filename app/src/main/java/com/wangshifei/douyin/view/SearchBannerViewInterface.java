package com.wangshifei.douyin.view;

import com.wangshifei.douyin.bean.SearchBannerBean;

import java.util.List;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public interface SearchBannerViewInterface {

    void SearchBannersuccess(List<SearchBannerBean.BannerBean> bannerBeanList);
    void SearchBannerfailed(Exception e);

}

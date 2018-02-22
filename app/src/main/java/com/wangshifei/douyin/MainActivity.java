package com.wangshifei.douyin;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wangshifei.douyin.bean.SearchBannerBean;
import com.wangshifei.douyin.bean.SearchBean;
import com.wangshifei.douyin.presenter.SearchBannerPresenter;
import com.wangshifei.douyin.presenter.SearchPresenter;
import com.wangshifei.douyin.view.SearchBannerViewInterface;
import com.wangshifei.douyin.view.SearchViewInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SearchBannerViewInterface,SearchViewInterface {

    @BindView(R.id.seachheadview)
    SearchHeadView seachheadview;
    @BindView(R.id.search_xrv_1)
    XRecyclerView searchXrv1;
    private SearchBannerPresenter searchBannerPresenter;
    private Toast toast;
    private SearchPresenter searchPresenter;
    private SearchAdapter1 searchAdapter1;
    private List<SearchBannerBean.BannerBean> bannerList;
    private ArrayList<String> stringList = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchBannerPresenter = new SearchBannerPresenter(this);
        searchBannerPresenter.excuteQuest();

        searchPresenter = new SearchPresenter(this);
        searchPresenter.excuteQuest();


        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

    }




    @Override
    public void SearchBannersuccess(List<SearchBannerBean.BannerBean> bannerBeanList) {
        if (bannerBeanList != null) {
            bannerList = new ArrayList<>();
            bannerList.addAll(bannerBeanList);
        }
    }

    @Override
    public void SearchBannerfailed(Exception e) {
        toast.setText("请求失败!");
        toast.show();
    }

    @Override
    public void Searchsuccess(List<SearchBean.CategoryListBean> categoryList) {
         if(categoryList != null){
             EventBus.getDefault().postSticky(new EventBusMessage(categoryList));
             seachheadview.setOnSearchClickListener(new SearchHeadView.OnSearchClickListener() {
                 @Override
                 public void onSearchClick(View v) {
                     String stringText = seachheadview.getStringText();
                     stringList.add(stringText);
                     searchAdapter1.notifyDataSetChanged();
                 }

                 @Override
                 public void onImgBackClick(View v) {

                 }
             });
             LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(MainActivity.this, GridLayout.VERTICAL, false);
             searchXrv1.setLayoutManager(linearLayoutManager1);
             searchAdapter1 = new SearchAdapter1(MainActivity.this, categoryList, bannerList,stringList);
             searchXrv1.setAdapter(searchAdapter1);
         }
    }

    @Override
    public void Searchfailed(Exception e) {
        toast.setText("请求失败!");
        toast.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (searchBannerPresenter != null) {
            searchBannerPresenter.catchView();
        }
        if (searchPresenter != null) {
            searchPresenter.catchView();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


}

package com.wangshifei.douyin.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wangshifei.douyin.EventBusMessage;
import com.wangshifei.douyin.FragmentAdapter;
import com.wangshifei.douyin.MostHotFragment;
import com.wangshifei.douyin.MostNewFragment;
import com.wangshifei.douyin.R;
import com.wangshifei.douyin.bean.SearchBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotAndNewActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.recommend_text)
    TextView recommendText;
    @BindView(R.id.user_num)
    TextView userNum;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.head_text)
    TextView headText;
    @BindView(R.id.share)
    ImageView share;
    private List<Fragment> fragments;
    private String[] title = {"最热", "最新"};
    private FragmentAdapter fragmentAdapter;
    private List<SearchBean.CategoryListBean> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_and_new);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        fragments = new ArrayList<>();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int position = bundle.getInt("position1");

        String desc = categoryList.get(position).getDesc();

        if("热门挑战".equals(desc)){
            headText.setText(categoryList.get(position).getChallenge_info().getCha_name());
            userNum.setText("-"+categoryList.get(position).getChallenge_info().getUser_count()+"人使用了-");
            recommendText.setText(categoryList.get(position).getChallenge_info().getDesc());
        }else if("热门音乐".equals(desc)){
            headText.setText(categoryList.get(position).getMusic_info().getTitle());
            userNum.setText("-"+categoryList.get(position).getMusic_info().getUser_count()+"人参加了-");
            recommendText.setText(categoryList.get(position).getMusic_info().getTitle());
        }



        MostHotFragment mostHotFragment = new MostHotFragment();
        MostNewFragment mostNewFragment = new MostNewFragment();
        fragments.add(mostHotFragment);
        fragments.add(mostNewFragment);

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(fragmentAdapter);
        tablayout.setupWithViewPager(viewpager);

        for (int i = 0; i < fragmentAdapter.getCount(); i++) {

            TabLayout.Tab tab = tablayout.getTabAt(i);
            tab.setCustomView(R.layout.text_tablayout);

            if (i == 0) {
                tab.getCustomView().findViewById(R.id.tv).setSelected(true);
            }

            TextView tv = tab.getCustomView().findViewById(R.id.tv);
            tv.setText(title[i]);
        }

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv).setSelected(true);
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getCustomView().findViewById(R.id.tv).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessageEvent(EventBusMessage eventBusMessage) {
        categoryList = eventBusMessage.CategoryList;
        Log.e("WSF3", "getMessageEvent: " + categoryList.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

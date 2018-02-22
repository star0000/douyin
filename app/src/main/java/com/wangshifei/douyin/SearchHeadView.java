package com.wangshifei.douyin;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by 墨羽 on 2018/2/22.
 */

public class SearchHeadView extends LinearLayout{

    private OnSearchClickListener onSearchClickListener;
    private ImageView imgBack;
    private ImageView search;
    private EditText editSearch;
    private ImageView imgSearch;
    private String stringText;

    public interface OnSearchClickListener{
        void onSearchClick(View v);
        void onImgBackClick(View v);
    }

    public void setOnSearchClickListener(OnSearchClickListener onSearchClickListener){
        this.onSearchClickListener = onSearchClickListener;
    }

    public SearchHeadView(Context context) {
        this(context,null);
    }

    public SearchHeadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        View.inflate(context,R.layout.search_head,this);
        imgBack = findViewById(R.id.img_back);
        search = findViewById(R.id.search);
        editSearch = findViewById(R.id.edit_search);
        imgSearch = findViewById(R.id.img_search);

        imgSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchClickListener.onSearchClick(view);
            }
        });

        imgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchClickListener.onImgBackClick(view);
            }
        });

    }


    public String getStringText(){
        stringText = editSearch.getText().toString().trim();
        editSearch.setText("");
        return stringText;
    }
}

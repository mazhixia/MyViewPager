package com.app.test.android.myviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    //初始化
    @BindView(R.id.viewpager_common)
    TextView viewpagerCommon;
    @BindView(R.id.viewpager_invent)
    TextView viewpagerInvent;
    @BindView(R.id.viewpager_depth)
    TextView viewpagerDepth;
    @BindView(R.id.viewpager_zoomout)
    TextView viewpagerZoomout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);//初始化注解控件
    }

    @OnClick(R.id.viewpager_common)
    void OnClickCommon(){
        Intent intent = new Intent(this, CommonActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.viewpager_invent)
    void OnClickInvent(){
        Intent intent = new Intent(this, InventActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.viewpager_depth)
    void OnClickDepth(){
        Intent intent = new Intent(this, DepthActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.viewpager_zoomout)
    void OnClickZoomout(){
        Intent intent = new Intent(this, ZoomoutActivity.class);
        startActivity(intent);
    }
}

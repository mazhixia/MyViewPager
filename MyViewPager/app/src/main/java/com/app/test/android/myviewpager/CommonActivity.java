package com.app.test.android.myviewpager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.os.Build.VERSION_CODES.M;

//普通无线滑动的Activity
public class CommonActivity extends AppCompatActivity {

    @BindView(R.id.common_viewpager)
    ViewPager commonViewpager;
    @BindView(R.id.ll_addView)
    LinearLayout llAddView;

    private ArrayList<ImageView> imageViews;
    private int pre_posi;//上次高亮

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            handler.sendEmptyMessageDelayed(0,10000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);

        //图片数据
        int[] imageview = {R.drawable.mango, R.drawable.orange, R.drawable.pear, R.drawable.pineapple, R.drawable.strawberry};
        imageViews = new ArrayList<>();
        //准备数据
        for (int i = 0; i < imageview.length; i++) {
            ImageView View = new ImageView(this);
            View.setBackgroundResource(imageview[i]);
            imageViews.add(View);
        }
        /*设置指示点*/
        for (int i = 0; i < imageViews.size(); i++) {
            ImageView point = new ImageView(this);
            //代码设置间距
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                         LinearLayout.LayoutParams.WRAP_CONTENT);
            if(i == 0){
                lp.setMargins(0, 0, 0, 0);
                point.setBackgroundResource(R.drawable.circle_point_red);
            }else{
                lp.setMargins(10, 0, 0, 0);
                point.setBackgroundResource(R.drawable.circle_point);
            }
            point.setLayoutParams(lp);
            llAddView.addView(point);
        }
        commonViewpager.setAdapter(new MyAdapter(CommonActivity.this));
        commonViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {//页面正在滚动的时候[滑动偏移信息]，回调

            }

            @Override
            public void onPageSelected(int position) {//View进行切换 :position 新的页面位置
                ImageView current_point = (ImageView) llAddView.getChildAt(position%imageViews.size());
                current_point.setBackgroundResource(R.drawable.circle_point_red);

                ImageView pre_point = (ImageView) llAddView.getChildAt(pre_posi%imageViews.size());
                pre_point.setBackgroundResource(R.drawable.circle_point);

                pre_posi = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {//页面状态

            }
        });

        commonViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN://按下
                        handler.removeCallbacksAndMessages(null);//移除所有消息
                        break;
                    case MotionEvent.ACTION_MOVE://移动
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP://抬起
                        handler.removeCallbacksAndMessages(null);//先移除后发送，避免消息重复
                        handler.sendEmptyMessage(0);
                        break;
                    case MotionEvent.ACTION_CANCEL://事件丢失

                        break;
                }
                return false;
            }
        });
        //一般是在网络数据请求成功后，发送此消息
        handler.sendEmptyMessage(0);
    }

    private class MyAdapter extends PagerAdapter {
        private final Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
//            return imageViews.size();
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position%imageViews.size());
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}

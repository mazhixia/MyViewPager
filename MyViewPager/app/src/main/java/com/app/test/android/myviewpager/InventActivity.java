package com.app.test.android.myviewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//viewpager无线滑动------3D切换效果
public class InventActivity extends Activity {


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ll_addView)
    LinearLayout llAddView;

    private ArrayList<ImageView> imageViews;
    private int pre_posi;//上次高亮
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invent);
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
        viewPager.setPageTransformer(true,new MyTransformation());//切换不同动画的关键
        viewPager.setAdapter(new MyAdapter(InventActivity.this));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
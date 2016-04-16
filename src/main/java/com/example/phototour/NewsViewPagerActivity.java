package com.example.phototour;

import android.annotation.SuppressLint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phototour.photoview.HackyViewPager;
import com.example.phototour.photoview.PhotoView;

public class NewsViewPagerActivity extends AppCompatActivity {

    //图片资源，这个地方可以改成从网络中获取到的图片
    private int[] images = {R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4,
            R.mipmap.img5, R.mipmap.img6};

    //图片资源
    private int[] cars = {R.mipmap.car1, R.mipmap.car2, R.mipmap.car3, R.mipmap.car4, R.mipmap.car5, R.mipmap.car6, R.mipmap.car7};

    //图片的文字描述，在实际项目中这个是从后台获取到的数据，在这里模拟一下
    private String[] descs = {
            "小周非常喜欢《变形金刚》系列科幻电影，于是就话了" +
                    "28万好不容易的买了一台二手年龄5年的雪佛兰大黄蜂，其有这和第一步《变形金刚》基本一样的造型，非常经典美观"
            ,
            "原车主也是一位典型的《变形金刚》影迷，据悉这部电影给雪佛兰科迈罗带来了几十万台的全球销量" +
                    "，小周就是其中一位，犀利的前细长的纯黑色中网显得非常精致" +
                    "，引擎盖有着最纯粹的美式肌肉感"
            ,
            "但是小周花了这么贵的价钱买下来的二手大黄蜂，因为这是一台非常纯粹的美系列肌肉车，排量达到了" +
                    "惊人的3.6L,加上已经5年的车龄，小周经常要去加油站加油，第一因为油耗太大，第二因为邮箱太小"
            ,
            "但是这台大黄蜂还是回头率达到了惊人的95%，很像雪佛兰迈锐宝的尾灯，给人强烈的质感，而粗狂的排气管则给人强烈的肌肉感"
            ,
            "来到这款大黄蜂的车厢，看到了这最新的三幅式的真皮多功能的方向盘，后面的换挡拨片给人强烈的操控感"
            ,
            "中控台非常简洁，但是车载导航却没有，这点需要吐槽，全程需要用手机"
            , "驾驶舱是纯黑色的，而内饰采用了很" +
            "高级的高级绒布材质铺就，座驾的包裹性很强，可以8向调节，按摩，座椅记忆，加热通风等功能，非常实用"};
    //photoview里面的自定义的方法  重写了onInterceptTouchEvent  onTouchEvent来处理事件
    private HackyViewPager mViewPager;
    //显示页数和当前页数
    private TextView picture_iv_index;
    //返回按钮
    private ImageView picture_iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view_pager);
        initView();
        initParams();
    }


    //初始化参数
    private void initParams() {
        picture_iv_index.setText("1/" + images.length + descs[0]);
        // 绑定适配器
        mViewPager.setAdapter(new ViewPagerAdapter());
        //设置可以滑动监听(viewpager改变的时候调用)
        mViewPager.setOnPageChangeListener(new ViewPagerChangeListener());
        mViewPager.setCurrentItem(0);
        //设置返回监听事件
        picture_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsViewPagerActivity.this.finish();

            }
        });
    }

    //初始化布局控件
    private void initView() {
        mViewPager = (HackyViewPager) findViewById(R.id.photo_vp);
        picture_iv_back = (ImageView) findViewById(R.id.picture_iv_back);
        picture_iv_index = (TextView) findViewById(R.id.picture_iv_index);
    }

    // 查看大图viewpager适配器
    private class ViewPagerAdapter extends PagerAdapter {

        @SuppressLint("InflateParams")
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = getLayoutInflater().inflate(R.layout.news_picture_item, null);
            PhotoView picture_iv_item = (PhotoView) view.findViewById(R.id.picture_iv_item);
            // 给imageview设置一个tag，保证异步加载图片时不会乱序
            picture_iv_item.setImageResource(cars[position]);
            //把view加载到父容器中
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return cars.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }


    // viewpager切换监听器
    private class ViewPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            //设置文字
            picture_iv_index.setText((arg0 + 1) + "/" + images.length + " " + descs[0+arg0]);
        }

    }


}

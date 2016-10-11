package com.autobanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.autoscrollbanner.AutoScrollBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个Activity作为一个演示的Activity ,在这个Acitivty 中你将学会如何使用这个依赖包
 */
public class MainActivity extends AppCompatActivity {
    private AutoScrollBanner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner = (AutoScrollBanner) findViewById(R.id.banner);
        initData();
        banner.setOnBannerItemClick(new AutoScrollBanner.OnBannerItemClick() {
            @Override
            public void onBannerItemClick(int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //1.修复应用程序退出后依旧会循环执行任务的问题
//    2.修复手机灭屏后在此打开屏幕轮播图不滚动的问题
//    3.解决应用使用ViewPager做TabHost菜单切换Fragment的时候发生崩溃的问题
    List<String> url = new ArrayList<>();
    List<String> title = new ArrayList<>();

    public void initData() {
        if (title.size() == 0 || url.size() == 0) {
            url.add("http://www.taikr.com/files/course/2014/05-26/1733244ce8ff068672.jpg?2.8.7");
            url.add("http://img2.yiihuu.com/upimg/manage/2014/09/28/14118903569661.jpg");
            url.add("http://img3.bitautoimg.com/autoalbum/files/20110705/187/22395618710222_1619869_7.jpg");
            url.add("http://img3.imgtn.bdimg.com/it/u=1977703135,867088076&fm=21&gp=0.jpg");
            url.add("http://imgk.zol.com.cn/samsung/3467/a3466722_s.jpg");
            url.add("http://img8.zol.com.cn/bbs/upload/14506/14505419.jpg");
            url.add("http://imgsrc.baidu.com/forum/w%3D580/sign=18c84a790ef41bd5da53e8fc61db81a0/7c7a7d1ed21b0ef47e03350cddc451da80cb3e35.jpg");
            title.add("第一个标题");
            title.add("第二个标题");
            title.add("第三个标题");
            title.add("第四个标题");
            title.add("第五个个标题");
            title.add("第六个标题");
            title.add("第七个标题");

        }
        banner.setUrls(url);
        banner.setTitles(title);

    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stop();
    }
}

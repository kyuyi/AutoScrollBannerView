package com.autobanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.autoscrollbanner.AutoScrollBanner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AutoScrollBanner banner;
    private RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = (RelativeLayout) findViewById(R.id.container);
        banner = (AutoScrollBanner) findViewById(R.id.banner);
        List<String> url = new ArrayList<>();
        List<String> title = new ArrayList<>();
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
        banner.setUrls(url);
        banner.setTitles(title);
        banner.setOnBannerItemClick(new AutoScrollBanner.OnBannerItemClick() {
            @Override
            public void onBannerItemClick(int position) {
                Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });

        AutoScrollBanner mbanner = new AutoScrollBanner.Build(this)
                .setBannerTitle(title)
                .setBannerUrl(url)
                .setLoadFail(R.drawable.url_default)
                .setLoadingl(R.drawable.url_default)
                .setPointMargin(5)
                .setPointSelector(R.drawable.point_selecor2)
                .setRelColor(getResources().getColor(R.color.colorAccent))
                .setRelHeight(28)
                .setTitleColor(getResources().getColor(R.color.color_333333))
                .setRelLayout(2)
                .setScrollTimer(1)
                .setPointSize(5)
                .setTitleSize(11)
                .Builder();
        container.addView(mbanner);

    }
}

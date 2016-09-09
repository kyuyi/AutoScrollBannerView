package com.autoscrollbanner;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by KyuYi on 2016/8/22.
 * E-Mail:kyu_yi@sina.com
 */
public class BannerApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化Frasco 第三方控件，用于加载banner中的图片
         */
        Fresco.initialize(this);
    }
}

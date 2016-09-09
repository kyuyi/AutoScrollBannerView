package com.autoscrollbanner;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by KyuYi on 2016/8/25.
 * E-Mail:kyu_yi@sina.com
 */
public class BannerAdapter extends PagerAdapter {
    List<SimpleDraweeView> simpleDraweeViews;

    public BannerAdapter(List<SimpleDraweeView> imgList) {
        this.simpleDraweeViews = imgList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int currentPosition = position % simpleDraweeViews.size();

        SimpleDraweeView mimg = simpleDraweeViews.get(currentPosition);
        container.addView(mimg);
        return mimg;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        int currentPosition = position % simpleDraweeViews.size();
        container.removeView(simpleDraweeViews.get(currentPosition));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 1000000;
    }
}

package com.autoscrollbanner;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by KyuYi on 2016/8/25.
 * E-Mail:kyu_yi@sina.com
 */
public class BannerPager extends ViewPager {
    private int timer = 3;
    private Handler mhandler;
    private RadioGroup bannerRG;
    private int pagerSize;

    public BannerPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerPager(Context context, int timer, RadioGroup indicator, int size) {
        super(context);
        this.timer = timer;
        this.bannerRG = indicator;
        this.pagerSize = size;
        mhandler = new Handler();
        mhandler.postDelayed(runnable, timer * 1000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mhandler.removeCallbacks(runnable);
                break;
            case MotionEvent.ACTION_MOVE:
                mhandler.removeCallbacks(runnable);
                break;
            case MotionEvent.ACTION_UP:
                mhandler.postDelayed(runnable, timer * 1000);
                break;
        }
        return super.onTouchEvent(ev);

    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = getCurrentItem() + 1;
            ((RadioButton) bannerRG.getChildAt(currentItem % pagerSize)).setChecked(true);
            setCurrentItem(currentItem);
            mhandler.postDelayed(this, timer * 1000);
        }
    };

}

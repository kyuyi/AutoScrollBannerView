package com.autoscrollbanner;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

/**
 * Created by KyuYi on 2016/8/24.
 * E-Mail:kyu_yi@sina.com
 */
public class BannerUtils {
    private static String TAG = "AutoBannerTest";

    public static void loge(String msg) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, msg);
    }

    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }


    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}

package com.autoscrollbanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KyuYi on 2016/8/19.
 * E-Mail:kyu_yi@sina.com
 */
public class AutoScrollBanner extends RelativeLayout {

    public static Context con;
    /**
     * 标题的字体颜色
     */
    private int title_text_color = getResources().getColor(R.color.color_333333);
    /**
     * 标题字体的大小 默认为14
     */
    private float title_text_size = 16f;
    /**
     * 标题栏的背景颜色
     */
    private int rl_bg = getResources().getColor(R.color.color_30e5e5e5);
    /**
     * 标题栏的报读,默认为25DP
     */
    private int rl_height = 25;

    /**
     * indicator的宽高,默认为：10DP
     */
    private int point_size = 10;
    /**
     * indicator之间的间隔 默认为3DP
     */
    private int point_margin = 3;
    /**
     * 标题栏的布局方式，默认为0，就是没有标题，indicator指示器在中间
     */
    private int rel_layout = 0;
    /**
     * indicator的selector,如果设置了该属性，就不需要设置 point_default 和 point_select 这两个属性
     */
    private int point_selector = R.drawable.point_selecor;
    /**
     * 设置滚动的时间，单位：s
     */
    private int scroll_timer = 3;
    /**
     * 图片加载中的图片
     */
    private int load_fail = R.drawable.fail;
    /**
     * 图片加载失败的占位图
     */
    private int load_ing = R.drawable.loadding;
    /**
     * 图片URL的集合
     */
    List<String> mlist;
    /**
     * 标题集合
     */
    List<String> bannerTitleList;
    /**
     * 标题文字文本控件
     */
    private TextView titleText;
    /**
     * 指示器内容部分的左右间隔
     */
    private int content_margin = 10;
    /**
     * 指示器indicator
     */
    private RadioGroup bannerRG;
    /**
     * banner的点击事件
     */
    private OnBannerItemClick mEvents;

    public AutoScrollBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.con = context;
        //初始化默认数据
        point_margin = BannerUtils.dp2px(context, point_margin);
        point_size = BannerUtils.dp2px(context, point_size);
        rl_height = BannerUtils.dp2px(context, rl_height);
        title_text_size = BannerUtils.dp2px(context, title_text_size);
        content_margin = BannerUtils.dp2px(con, content_margin);
        mlist = new ArrayList<>();
        initTypedArray(attrs);
//        initLayout();
    }

    public AutoScrollBanner(Context con, Build mBuild) {
        super(con);
        this.con = con;
        this.title_text_color = mBuild.title_text_color;
        this.title_text_size = mBuild.title_text_size;
        this.rl_bg = mBuild.rl_bg;
        this.rl_height = mBuild.rl_height;
        this.point_size = mBuild.point_size;
        this.point_margin = mBuild.point_margin;
        this.rel_layout = mBuild.rel_layout;
        this.point_selector = mBuild.point_selector;
        this.scroll_timer = mBuild.scroll_timer;
        this.load_fail = mBuild.load_fail;
        this.load_ing = mBuild.load_ing;
        this.mlist = mBuild.mlist;
        this.bannerTitleList = mBuild.bannerTitleList;
        point_margin = BannerUtils.dp2px(con, point_margin);
        point_size = BannerUtils.dp2px(con, point_size);
        rl_height = BannerUtils.dp2px(con, rl_height);
        title_text_size = BannerUtils.dp2px(con, title_text_size);
        content_margin = BannerUtils.dp2px(con, content_margin);
//        Log.e("TAG", title_text_color + "--"
//                + title_text_size + "--"
//                + rl_bg + "--"
//                + rl_height + "--"
//                + point_size + "--"
//                + point_margin + "--"
//                + rel_layout + "--"
//                + point_selector + "--"
//                + scroll_timer + "--"
//                + load_fail);
        initLayout();
    }

    public void initTypedArray(AttributeSet attrs) {

        TypedArray mTa = getContext().obtainStyledAttributes(attrs, R.styleable.auto_banner);
        title_text_color = mTa.getColor(R.styleable.auto_banner_title_text_color, title_text_color);  //标题的颜色
        title_text_size = mTa.getDimension(R.styleable.auto_banner_title_text_size, title_text_size);  //标题的字体大小
        rl_bg = mTa.getColor(R.styleable.auto_banner_rel_bg, rl_bg);  //标题栏的背景颜色
        rl_height = mTa.getDimensionPixelSize(R.styleable.auto_banner_rel_height, rl_height);//标题栏的高度
        point_size = mTa.getDimensionPixelSize(R.styleable.auto_banner_point_size, point_size);//indicator指示器的默认宽高
        point_margin = mTa.getDimensionPixelSize(R.styleable.auto_banner_point_margin, point_margin); //indicator指示器之间的间隔
        rel_layout = mTa.getInteger(R.styleable.auto_banner_rel_layout, rel_layout); //标题栏的布局方式
        point_selector = mTa.getResourceId(R.styleable.auto_banner_point_selector, point_selector);//indicator指示器的selector，如果你设置了该属性的话，point_select 和 point_default的设置就不会起效
        scroll_timer = mTa.getInteger(R.styleable.auto_banner_scroll_timer, scroll_timer); //滚动的时间间隔
        load_fail = mTa.getResourceId(R.styleable.auto_banner_load_fail, load_fail);//图片加载失败时的占位图片
        load_ing = mTa.getResourceId(R.styleable.auto_banner_load_ing, load_ing);
        content_margin = mTa.getDimensionPixelSize(R.styleable.auto_banner_content_margin, content_margin);

    }

    private List<SimpleDraweeView> bannerImgList = new ArrayList<>();

    public void initLayout() {
        //创建标题栏的Rel并设置属性
        RelativeLayout bannerTitleRel = new RelativeLayout(con);
        LayoutParams bannerTitleRelParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, rl_height);//标题栏的宽高
        bannerTitleRel.setBackgroundColor(rl_bg);//给标题栏设置背景属性
        bannerTitleRelParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);//靠控件的底部显示
        bannerTitleRel.setLayoutParams(bannerTitleRelParams);
        //创建指示器
        bannerRG = new RadioGroup(con);
        RelativeLayout.LayoutParams bannerRGParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bannerRG.setOrientation(LinearLayout.HORIZONTAL);
        //动态创建ViewPager并设置属性
        final ViewPager bannerPager = new BannerPager(con, scroll_timer, bannerRG, mlist.size());
        LayoutParams bannerPagerParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bannerPager.setLayoutParams(bannerPagerParams);
        for (int i = 0; i < mlist.size(); i++) {
            SimpleDraweeView bannerImg = new SimpleDraweeView(con);
            GenericDraweeHierarchyBuilder bannerBiulder = new GenericDraweeHierarchyBuilder(getResources());
            GenericDraweeHierarchy hierarchy = bannerBiulder
                    .setFailureImage(load_fail)
                    .setPlaceholderImage(load_ing)
                    .build();
            bannerImg.setHierarchy(hierarchy);
            bannerImg.setScaleType(ImageView.ScaleType.FIT_CENTER);
            bannerImg.setImageURI(mlist.get(i));
//
            bannerImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mEvents != null) {
                        mEvents.onBannerItemClick(bannerPager.getCurrentItem() % mlist.size());
                    }

                }
            });
            bannerImgList.add(bannerImg);
            //动态创建RadioButton  该控件作为indicator
            RadioButton bannerRB = new RadioButton(con);
            bannerRB.setButtonDrawable(R.color.color_00000000);
            RadioGroup.LayoutParams bannerRBParams = new RadioGroup.LayoutParams(point_size, point_size);
            bannerRBParams.setMargins(point_margin / 2, 0, point_margin / 2, 0);
            bannerRB.setBackgroundResource(point_selector);
            bannerRB.setLayoutParams(bannerRBParams);
            bannerRG.addView(bannerRB);
            if (i == 0)
                bannerRB.setChecked(true);

        }
        //设置布局方式
        switch (rel_layout) {
            case 0:
                //这种方式只有指示器没文字标题
                bannerRGParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                break;
            case 1:
                //该方式文字在左边，指示器在右边
                titleText = new TextView(con);
                bannerRGParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                bannerRGParams.addRule(RelativeLayout.CENTER_VERTICAL);
                LayoutParams titleTextParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                titleTextParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                titleTextParams.addRule(RelativeLayout.CENTER_VERTICAL);
                titleTextParams.setMargins(content_margin, 0, 0, 0);
                bannerRGParams.setMargins(0, 0, content_margin, 0);
                titleText.setLayoutParams(titleTextParams);
                titleText.setTextColor(title_text_color);
                titleText.setTextSize(BannerUtils.px2sp(con, title_text_size));
                bannerTitleRel.addView(titleText);
                break;
            case 2:
                //该方式文字在右边，指示器在左边
                titleText = new TextView(con);
                titleText.setTextColor(title_text_color);
                titleText.setTextSize(BannerUtils.px2sp(con, title_text_size));
                bannerRGParams.setMargins(content_margin, 0, 0, 0);
                bannerRGParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                bannerRGParams.addRule(RelativeLayout.CENTER_VERTICAL);
                LayoutParams titleTextParams2 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                titleTextParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                titleTextParams2.addRule(RelativeLayout.CENTER_VERTICAL);
                titleTextParams2.setMargins(0, 0, content_margin, 0);
                titleText.setLayoutParams(titleTextParams2);
                bannerTitleRel.addView(titleText);
                break;
        }
        bannerRG.setLayoutParams(bannerRGParams);


        addView(bannerPager);
        addView(bannerTitleRel);
        bannerTitleRel.addView(bannerRG);
        bannerPager.setAdapter(new BannerAdapter(bannerImgList));
        bannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentPostion = position % mlist.size();
                ((RadioButton) bannerRG.getChildAt(currentPostion)).setChecked(true);
                if (rel_layout == 1 || rel_layout == 2) {
                    titleText.setText(bannerTitleList.get(currentPostion));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bannerPager.setCurrentItem(mlist.size() * 1000000 / 40);
    }

    //   是否需要调用方法的控制变量
    private boolean isInstance = true;

    /**
     * @param mlist banner 图片的图片url
     */
    public void setUrls(List<String> mlist) {
        this.mlist = mlist;
        if (isInstance) {
            isInstance = false;
        } else {
            initLayout();
        }
    }

    /**
     * @param mlist banner的标题
     */
    public void setTitles(List<String> mlist) {
        this.bannerTitleList = mlist;
        if (isInstance) {
            isInstance = false;
        } else {
            initLayout();
        }
    }

    public interface OnBannerItemClick {
        void onBannerItemClick(int position);
    }

    public void setOnBannerItemClick(OnBannerItemClick events) {
        this.mEvents = events;
    }


    /**
     * 动态创建的方式
     */

    public static class Build {

        private float title_text_size = 16f;
        private Context context;
        private int title_text_color;
        private int rl_bg;
        private int rl_height = 25;
        private int point_size = 10;
        private int point_margin = 3;
        private int rel_layout = 0;
        private int point_selector = R.drawable.point_selecor;
        private int scroll_timer = 3;
        private int load_fail = R.drawable.fail;
        private int load_ing = R.drawable.loadding;
        private List<String> mlist;
        private List<String> bannerTitleList;

        /**
         * @param con 上下文对象
         */
        public Build(Context con) {
            this.context = con;
            //初始化默认值
            title_text_color = context.getResources().getColor(R.color.color_333333);
            rl_bg = context.getResources().getColor(R.color.color_30e5e5e5);
        }

        /**
         * 设置标题文本的颜色
         *
         * @param color 文本的颜色
         * @return Build对象
         */
        public Build setTitleColor(@Nullable int color) {
            this.title_text_color = color;
            return this;
        }

        /**
         * 设置标题文本的字体大小
         *
         * @param size 文本字体的大小
         * @return Build对象
         */
        public Build setTitleSize(@Nullable float size) {
            this.title_text_size = size;
            return this;
        }

        /**
         * 设置标题栏的背景颜色
         *
         * @param color 标题栏的背景颜色
         * @return Build对象
         */
        public Build setRelColor(@Nullable int color) {
            this.rl_bg = color;
            return this;
        }

        /**
         * 设置标题栏的高度
         *
         * @param height 标题栏的高度
         * @return Build对象
         */
        public Build setRelHeight(@Nullable int height) {
            this.rl_height = height;
            return this;
        }

        /**
         * 设置指示器的大小（宽高）
         *
         * @param size 指示器的大小（宽高）
         * @return Build对象
         */
        public Build setPointSize(@Nullable int size) {
            this.point_size = size;
            return this;
        }

        /**
         * 设置指示器之间的间隔
         *
         * @param margin 指示器之间的间隔
         * @return Build对象
         */
        public Build setPointMargin(@Nullable int margin) {
            this.point_margin = margin;
            return this;
        }

        /**
         * 设置标题栏的布局方式
         *
         * @param way 布局方式
         * @return Build对象
         */
        public Build setRelLayout(@Nullable int way) {
            this.rel_layout = way;
            return this;
        }

        /**
         * 设置自定义的Indicator 资源文件
         *
         * @param selector indicator资源ID
         * @return Build对象
         */
        public Build setPointSelector(@Nullable int selector) {
            this.point_selector = selector;
            return this;
        }

        /**
         * 设置自动滚动的时间间隔
         *
         * @param timer 滚动的时间间隔
         * @return Build对象
         */
        public Build setScrollTimer(@Nullable int timer) {
            this.scroll_timer = timer;
            return this;
        }

        /**
         * 设置图片加载失败的 站位图片
         *
         * @param resource 占位图片的资源ID
         * @return Build对象
         */
        public Build setLoadFail(@Nullable int resource) {
            this.load_fail = resource;
            return this;
        }

        /**
         * 设置正在加载时的站位图片
         *
         * @param resource 站位图片的资源ID
         * @return Build 对象
         */
        public Build setLoading(@Nullable int resource) {
            this.load_ing = resource;
            return this;
        }

        /**
         * 设置图片的路径
         *
         * @param list 图片的路径集合
         * @return Build对象
         */
        public Build setBannerUrl(@Nullable List<String> list) {
            this.mlist = list;
            return this;
        }

        /**
         * 设置需要显示的标题
         *
         * @param list 标题集合
         * @return Build对象
         */
        public Build setBannerTitle(@Nullable List<String> list) {
            this.bannerTitleList = list;
            return this;
        }

        /**
         * 实例化控件
         *
         * @return AutoScrollBanner对象
         */
        public AutoScrollBanner Builder() {
            return new AutoScrollBanner(context, this);
        }
    }
}

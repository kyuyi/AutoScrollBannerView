# AutoScrollBannerView
### 简介：
AutoScrollBannerView 是一种利用ViewPager+RadioGroup组合而成的自定义轮播图控件，该控件拥有多种自定义布局，用户可以根据自己的需求设计
出自己想要的效果
### 效果：
![](https://github.com/kyuyi/AutoScrollBannerView/blob/master/material/gif5%E6%96%B0%E6%96%87%E4%BB%B6.gif)
### 如何使用：
#### 目前只支持Gradle方式，配置方式如下：
	dependencies {
	    compile 'com.github.kyuyi:AutoScrollBannerView:v161011_15.40.41'
	}
	
	
	
	allprojects {
	    repositories {
	        maven { url "https://jitpack.io" }
	    }
	}
### 属性说明：
#### XML布局方式属性说明：
<Table>
<tr><td>属性名称</td><td>说明</td><td>例子</td><td>默认值(单位)</td></tr>
<tr><td>app:content_margin</td><td>标题栏中内容的左右间隔</td><td>app:content_margin="2dp"</td><td>10(DP)</td></tr>
<tr><td> app:load_fail</td><td>图片加载失败后的占位图</td><td>app:load_fail="@drawable/indicator_default"</td><td>R.drawable.fail</td></tr>
<tr><td> app:load_ing</td><td>图片正在加载的占位图</td><td>app:load_ing="@drawable/indicator_select"</td><td>R.drawable.fail</td></tr>
<tr><td> app:point_margin</td><td>指示器之间的间隔</td><td> app:point_margin="5dp"</td><td>3(DP)</td></tr>
<tr><td>app:point_selector</td><td>指示器的样式Selector</td><td>app:point_selector="@drawable/point_selecor"</td><td>R.drawable.point_selecor</td></tr>
<tr><td>app:point_sizen</td><td>指示器的大小</td><td>app:point_size="5dp"</td><td>10(DP)</td></tr>
<tr><td>app:rel_bg</td><td>标题栏的背景颜色</td><td>app:rel_bg="@color/color_666666"</td><td>R.color.color_30e5e5e5</td></tr>
<tr><td>app:rel_height</td><td>标题栏高度</td><td>app:rel_height="20dp"</td><td>25(DP)</td></tr>
<tr><td>app:rel_layout</td><td>标题栏的布局方式</td><td><table><tr><td>CENTERT</td><td>只有指示器，指示器在标题栏的正中间</td></tr><tr><td>TEXT_LEFT_POINT_RIGHT</td><td>文字在左边，指示器在右边</td></tr><tr><td>TEXT_RIGHT_POINT_LEFT</td><td>指示器在右边，文字在左边</td></tr></table></td><td>CENTER</td></tr>
<tr><td>app:scroll_timer</td><td>轮播图滚动的时间间隔</td><td>app:scroll_timer="1"</td><td>3(s秒)</td></tr>
<tr><td>app:title_text_color</td><td>标题栏中标题的字体颜色</td><td>app:title_text_color="@color/color_FFFFFF"</td><td>R.color.color_333333</td></tr>
<tr><td>app:title_text_size</td><td>标题栏中标题的大小</td><td>app:title_text_size="11sp"</td><td>16SP</td></tr>
</Table>
#### Builder模式设置方法说明：
        AutoScrollBanner mbanner = new AutoScrollBanner.Build(this)            //实例化对象
                .setBannerTitle(title)                                         //设置轮播图的标题
                .setBannerUrl(url)                                             //设置轮播图的URL
                .setLoadFail(R.drawable.url_default)                           //设置轮播图加载失败的占位图片
                .setLoading(R.drawable.url_default)                            //设置轮播图正在加载的占位图片
                .setPointMargin(5)                                             //设置指示器的大小 单位：DP
                .setPointSelector(R.drawable.point_selecor2)                   //设置指示器的样式Selector
                .setRelColor(getResources().getColor(R.color.colorAccent))     //设置标题栏的背景颜色
                .setRelHeight(28)                                              //设置标题栏的高度 单位:DP
                .setTitleColor(getResources().getColor(R.color.color_333333))  //设置标题栏的字体颜色
                .setRelLayout(2)                                               //设置标题栏的布局方式
                .setScrollTimer(1)                                             //设置滚动的时间间隔 单位：s
                .setPointSize(5)                                               //设置指示器的大小 单位：DP
                .setTitleSize(11)                                              //标题栏标题字体大小 单位：SP
                .Builder();                                                    //开始构建
        container.addView(mbanner);                                            //向页面中添加该控件
        
### 更多：
    其他更多详细内容请查看源码
### 注意事项：
    由于该控件中的图片加载使用的是Fresco 为了防止 依赖包冲突，请在使用过程中尽量避免其他的库中依赖fresco
    

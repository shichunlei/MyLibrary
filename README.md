# MyLibrary

    根据自己项目用到过的一些自定义控件，也有部分是网上找的，在这里根据自己的项目需求稍作修改，会陆续添加一些好用的自定义View，再此特别感谢原作者们。

**Include**

- **AlertDialog**

- **BottomDialog**

- **ProgressDialog**

- **RoundImageView**

    圆形图片、圆角图片、椭圆图片

    [原始项目地址](https://github.com/RaphetS/RoundImageView)

- **SmoothCheckBox**

    [原始项目地址](https://github.com/andyxialm/SmoothCheckBox)

- **ExpandIconView**

    [原始项目地址](https://github.com/zagum/Android-ExpandIcon)

- **FlipView**

    可翻转的View，仿支付宝-蚂蚁宝卡里流量兑换的翻转View

    [参考项目地址](https://github.com/wajahatkarim3/EasyFlipView)

- **NumberAnimTextView**

    带数字增加动画的TextView

    [原始项目地址](https://github.com/Bakumon/NumberAnimTextView)

- **LabelView**

    角标

    [原始项目地址](https://github.com/H07000223/FlycoLabelView)

- **SwipeToLoadLayout**

    SwipeToLoadLayout is a reusable pull-to-refresh and pull-to-load-more widget.

    [原始项目地址](https://github.com/Aspsine/SwipeToLoadLayout)

- **LoadingView**

    加载动画

- **ExpandTextView**

    可展开、折叠的TextView
    
    [原始项目地址](https://github.com/lcodecorex/ExpandTextView)

- **RecyclerViewBanner**

    广告轮播
    
    [原始项目地址](https://github.com/loonggg/RecyclerViewBanner)

- **SmallBang**

    twitter like animation for any view
    
    [原始项目地址](https://github.com/hanks-zyh/SmallBang)
    
- **WaveLoadingView**

    水波效果 An Android library providing to realize wave loading effect.
    
    [原始项目地址](https://github.com/tangqi92/WaveLoadingView)

- **SearchView**

    搜索View

- **RadarView**

    雷达图

    [参考项目地址](https://github.com/qstumn/RadarChart)
   
- **RangeSeekBar**

    A beautiful SeekBar that supports bidirectional range selection and normal SeekBar mode, supports scale, negative numbers and a variety of powerful custom properties (一款漂亮美观的支持双向范围选择和普通SeekBar模式的SeekBar，支持刻度、负数以及多种强大的自定义属性)
    
    [原始项目地址](https://github.com/Jay-Goo/RangeSeekBar)
    
- **SwitchButton**

    SwitchButton.An beautiful+lightweight+custom-style-easy switch widget for Android,minSdkVersion >= 11 
    
    [原始项目地址](https://github.com/zcweng/SwitchButton)

- **工具类**

    StringUtils、TimeUtils、SPUtils、JsonUtils、ViewUtils、ScreenUtils、AnimatorUitls（部分参考于SwitchLayout）、HttpUtils、FileUtils、AppUitls、IdcardUtil、StatusBarHelper、RandomUtils、ConversionUtils等

- **基类**

    BaseApplication、BaeActivity、BaseFragment、LazyFragment、CommonAdapter、CommonRecyclerAdapter

**Version**

    【0.1.5】

# Installation

Step 1. Add the JitPack repository to your build file

    Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

    dependencies {
        compile 'com.github.shichunlei:MyLibrary:0.1.5'
    }

# Usage

    参见sample示例

### Sample 中使用到的第三方框架包括

- _[Glide](https://github.com/bumptech/glide)_
- _[Gson](https://github.com/google/gson)_
- _[xUtils3](https://github.com/wyouflf/xUtils)_
- _[GreenDao](https://github.com/greenrobot/greenDAO)_
- _[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)  # 刷新_
- _[PinLockView](https://github.com/aritraroy/PinLockView) # 数字密码锁_
- _[PatternLockView](https://github.com/aritraroy/PatternLockView) # 九宫格密码锁_
- _[CompactCalendarView](https://github.com/SundeepK/CompactCalendarView) # 日历_
- _等等。。。_

# 更新日志

【0.1.5】

    添加动画工具类方法
    完善工具类方法
    添加RangeSeekBar
    添加加密算法
    添加对lanmbda表达式的支持
    添加SwitchButton

【0.1.4】

    完善TimeUtils
    添加AnimatorUitls方法
    添加雷达图library
    添加BaseApplication
    添加NumberProgressBar
    添加UpdateAppDialog
    删除GifMovieView
    删除HorizontalProgressDialog

【0.1.3】

    修改0.1.2的BUG

【0.1.2】

    添加 Glide 下的 Transformations

【0.1.1】

    添加 SearchView
    添加 GifMovieView
    添加 ConversionUtils
    修改 TimeUtils
    修改包结构

【0.1.0】

    添加 ExpandTextView 可展开、折叠的TextView
    修改自定义AlertDialog，添加更多属性
    添加 RecyclerViewBanner 广告轮播
    添加 SmallBang
    添加 WaveLoadingView 水波效果
    添加工具类AnimatorUitls、RandomUtils，完善AppUitls

【0.0.9】

    添加 LoadingView 加载动画
    更新Android 轻量级适配器，添加TextView一些属性

【0.0.8】

    RecyclerView嵌套RecyclerView；
    添加 LabelView 角标

【0.0.7】

    StringUtils、TimeUtils、ViewUtils类中添加新的方法；
    添加 SwipeToLoadLayout 下拉刷新上拉加载

【0.0.6】

    修改ProgressDialog

【0.0.5】

    添加HorizontalProgressDialog；
    添加StatusBarHelper；
    去掉枚举并转换成Integer，避免增加app的class.dex文件的大小

【0.0.4】

    添加BottomDialog和NumberAnimTextView

【0.0.3】

    添加完善工具类；
    添加自定义控件“可翻转的View，仿支付宝-蚂蚁宝卡里流量兑换的翻转View”

【0.0.2】

    添加工具类；
    ExpandIcon

【0.0.1】

    创建项目，添加了一些工具类

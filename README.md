# MyLibrary

    优秀框架、自定义控件大搜集，再此特别感谢原作者们。

[![](https://jitpack.io/v/shichunlei/MyLibrary.svg)](https://jitpack.io/#shichunlei/MyLibrary)

**Include**

- **AlertDialog**

- **BottomDialog**

- **ProgressDialog**

- **RoundImageView**

    圆形图片、圆角图片、椭圆图片

    [原始项目地址](https://github.com/RaphetS/RoundImageView)

- **RoundTextView**

    基于TextView 1.直接设置selector背景2.直接设置drawableLeft大小 3.圆角，圆形，背景/边框/文字根据状态变色
    
    [原项目地址](https://github.com/RuffianZhong/RTextView)

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

- **LoadingView**

    加载动画

- **ExpandTextView**

    可展开、折叠的TextView
    
    [原始项目地址](https://github.com/lcodecorex/ExpandTextView)

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
    
- **StateView**

    StateView 一个轻量级的控件, 继承自 View, 吸收了 ViewStub 的一些特性, 初始状态下是不可见的, 不占布局位置, 占用内存少。 当进行操作显示空/重试/加载视图后, 该视图才会被添加到布局中。
    
    [原项目地址](https://github.com/nukc/StateView)
    
- **StickyHeaderDecoration**

    A sticky header decoration, use for recyclerview
    
    [原项目地址](https://github.com/qdxxxx/StickyHeaderDecoration)
    
- **IndexBarLayout**

    Similar to the letter to the right of the phone's contacts
    
    [原项目地址](https://github.com/qdxxxx/IndexBarLayout)

- **IntegerRulerView/DecimalRulerView/RulerView**

    刻度尺

- **CircleMenu**

    CircleMenu is a simple, elegant UI menu with a circular layout and material design animations. 

    [原项目地址](https://github.com/Ramotion/circle-menu-android)

- **MarqueeView**

    俗名：可垂直跑、可水平跑的跑马灯
    学名：可垂直翻、可水平翻的翻页公告

    [原项目地址](https://github.com/sfsheng0322/MarqueeView)

- **AnimNumberTextView**

    滚动显示TextView的数字,支持自定义每个字符速度。
    
    [原项目地址](https://github.com/AndroidMsky/RandomTextView)

- **工具类**

    StringUtils、TimeUtils、SPUtils、JsonUtils、ViewUtils、ScreenUtils、AnimatorUitls（部分参考于SwitchLayout）、HttpUtils、FileUtils、AppUitls、IdcardUtil、StatusBarHelper、RandomUtils、ConversionUtils等

- **基类**

    BaseApplication、BaeActivity、BaseFragment、LazyFragment、CommonAdapter、CommonRecyclerAdapter

**Version**

    【0.2.2】

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
        compile 'com.github.shichunlei:MyLibrary:0.2.2'
    }

# Usage

    参见sample示例

===========================================================================================

##### MarqueeView 用法

<img src="/screenshot/MarqueeView.gif" style="width: 30%;">

###### 属性

| Attribute 属性          | Description 描述 | 
|:---				     |:---| 
| mvAnimDuration         | 一行文字动画执行时间 | 
| mvInterval         | 两行文字翻页时间间隔 | 
| mvTextSize         | 文字大小 | 
| mvTextColor         | 文字颜色 | 
| mvGravity         | 文字位置:left、center、right | 
| mvSingleLine         | 单行设置 |
| mvDirection        | 动画滚动方向:bottom_to_top、top_to_bottom、right_to_left、left_to_right | 

###### XML

    <chingtech.library.widget.MarqueeView
        android:id="@+id/marqueeView"
        android:layout_width="match_parent"
        android:layout_height="30dp"
        app:mvAnimDuration="1000"
        app:mvDirection="bottom_to_top"
        app:mvInterval="3000"
        app:mvTextColor="@color/white"
        app:mvTextSize="14sp"
        app:mvSingleLine="true"/>

###### 设置字符串列表数据

    MarqueeView marqueeView = (MarqueeView) findViewById(R.id.marqueeView);

    List<String> info = new ArrayList<>();
    info.add("1. 大家好，我是孙福生。");
    info.add("2. 欢迎大家关注我哦！");
    info.add("3. GitHub帐号：sfsheng0322");
    info.add("4. 新浪微博：孙福生微博");
    info.add("5. 个人博客：sunfusheng.com");
    info.add("6. 微信公众号：孙福生");
    marqueeView.startWithList(info);

    // 在代码里设置自己的动画
    marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);

###### 设置字符串数据

    String notice = "心中有阳光，脚底有力量！心中有阳光，脚底有力量！心中有阳光，脚底有力量！";
    marqueeView.startWithText(notice);
    
    // 在代码里设置自己的动画
    marqueeView.startWithText(notice, R.anim.anim_bottom_in, R.anim.anim_top_out);

###### 设置事件监听

    marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
        @Override
        public void onItemClick(int position, TextView textView) {
            Toast.makeText(getApplicationContext(), String.valueOf(marqueeView1.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
        }
    });

###### 重影问题可参考以下解决方案

    @Override
    public void onStart() {
        super.onStart(); 
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        marqueeView.stopFlipping();
    }

===========================================================================================

##### CircleMenu 用法

<img src="/screenshot/preview.gif" style="width: 30%;">

Place the `CircleMenuView` in your layout and set the icons and colors of the buttons, as shown below.

    app:button_colors="@array/colors"
    app:button_icons="@array/icons"


Example of arrays `colors` and `icons` in `res\values\buttons.xml`:

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <array name="icons">
            <item>@drawable/ic_home_white_24dp</item>
            <item>@drawable/ic_search_white_24dp</item>
            <item>@drawable/ic_notifications_white_24dp</item>
            <item>@drawable/ic_settings_white_24dp</item>
            <item>@drawable/ic_place_white_24dp</item>
        </array>
        <array name="colors">
            <item>@android:color/holo_blue_light</item>
            <item>@android:color/holo_green_dark</item>
            <item>@android:color/holo_red_light</item>
            <item>@android:color/holo_purple</item>
            <item>@android:color/holo_orange_light</item>
        </array>
    </resources>


Or use the constructor

    CircleMenuView(@NonNull Context context, @NonNull List<Integer> icons, @NonNull List<Integer> colors)

to add `CircleMenuView` and configure the buttons programmatically (in the code).

Next, connect the event handler `CircleMenuView.EventListener` as shown below,
and override the methods you need.


    final CircleMenuView menu = (CircleMenuView) findViewById(R.id.circle_menu);
    menu.setEventListener(new CircleMenuView.EventListener() {
        @Override
        public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
            Log.d("D", "onMenuOpenAnimationStart");
        }
    
        @Override
        public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
            Log.d("D", "onMenuOpenAnimationEnd");
        }
    
        @Override
        public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
            Log.d("D", "onMenuCloseAnimationStart");
        }
    
        @Override
        public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
            Log.d("D", "onMenuCloseAnimationEnd");
        }
    
        @Override
        public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
            Log.d("D", "onButtonClickAnimationStart| index: " + index);
        }
    
        @Override
        public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
            Log.d("D", "onButtonClickAnimationEnd| index: " + index);
        }
    });


Here are the attributes you can specify through XML or related setters:
* `button_icons` - Array of buttons icons.
* `button_colors` - Array of buttons colors.
* `icon_menu` - Menu default icon.
* `icon_close` - Menu closed icon.
* `icon_color` - Menu icon color.
* `duration_ring` - Ring effect duration.
* `duration_open` - Menu opening animation duration.
* `duration_close` - Menu closing animation duration.
* `distance` - Distance between center button and buttons

===========================================================================================

##### AnimNumberTextView 用法

<img src="/screenshot/3032383935.gif">

xml中定义：

    <chingtech.library.widget.AnimNumberTextView
        android:id="@+id/rtv"
        android:layout_width="match_parent"
        android:layout_height="150dp"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:gravity="center"
        android:padding="0px"
        android:text="123456"
        android:textSize="28sp" />

所有位数相同速度滚动：

    mNumberTextView.setText("876543");
    mNumberTextView.setPianyilian(AnimNumberTextView.ALL);
    mNumberTextView.start();

从左到右侧由快到慢滚动：

    mNumberTextView.setText("12313288");
    mNumberTextView.setPianyilian(AnimNumberTextView.FIRSTF_FIRST);
    mNumberTextView.start();

从左到右侧由慢到快滚动：

    mNumberTextView.setText("9078111123");
    mNumberTextView.setPianyilian(AnimNumberTextView.FIRSTF_LAST);
    mNumberTextView.start();

自定义每位数字的速度滚动（每帧滚动的像素）：

    mNumberTextView.setText("909878");
    pianyiliang[0] = 7;
    pianyiliang[1] = 6;
    pianyiliang[2] = 12;
    pianyiliang[3] = 8;
    pianyiliang[4] = 18;
    pianyiliang[5] = 10;
    mNumberTextView.setPianyilian(pianyiliang);
    mNumberTextView.start();

自定义滚动行数（默认10行）：

    mNumberTextView.setMaxLine(20);

===========================================================================================


### Sample 中使用到的第三方框架包括

- _[Gson](https://github.com/google/gson)_
- _[GreenDao](https://github.com/greenrobot/greenDAO)_
- _[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)  # 刷新_
- _[PinLockView](https://github.com/aritraroy/PinLockView) # 数字密码锁_
- _[PatternLockView](https://github.com/aritraroy/PatternLockView) # 九宫格密码锁_
- _[CompactCalendarView](https://github.com/SundeepK/CompactCalendarView) # 日历_
- _Bugly_
- _[PictureSelector](https://github.com/LuckSiege/PictureSelector) # 图片选择库_
- _butterknife_
- _okhttp+retrofit+rxjava_
- _等等。。。_

# 更新日志

【0.2.2】

    更改刷新为SmartRefresh
    更改网络加载为okhttp+retrofit+rxjava
    更改注解为butterknife
    添加RulerView
    等

【0.1.9】

    添加StateView
    添加PictureSelector使用
    添加Bugly更新使用
    添加IndexBar，InadexLayout
    添加NormalDecoration
    添加Banner的使用
    添加RoundTextView

【0.1.7】

    添加动画工具类方法
    完善工具类方法
    添加RangeSeekBar
    添加加密算法
    添加对lanmbda表达式的支持
    添加SwitchButton
    配置JitPack.io

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

    添加 Glide 下的 Transformations
    修改BUG

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

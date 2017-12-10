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

    角标,一个简单的Android标签控件.

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

    SwitchButton.An beautiful+lightweight+custom-style-easy switch widget for Android
    
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

- **ThreeStateSwitch**

    A simple three-state switch view for Android.
    
    [原项目地址](https://github.com/abbas-oveissi/ThreeStateSwitch)

- **SwitchIconView**

    Google launcher-style implementation of switch (enable/disable) icon

    [原项目地址](https://github.com/zagum/Android-SwitchIcon)

- **DescProgressView**

    带进度带描述的view

    [原项目地址](https://github.com/GuoFeilong/DescPbView)

- **WavyLineView**

    波浪线

    [原项目地址](https://github.com/andyxialm/WavyLineView)

- **CouponView**

    半圆锯齿背景虚线边框组合实现简单优惠券效果，可实现一些简单组合
    
    [原项目地址](https://github.com/dongjunkun/CouponView)

- **RippleView**

    简单的涟漪效果
    
    [原项目地址](https://github.com/LiqiNew/RippleDemo)

- **工具类**

    StringUtils、TimeUtils、SPUtils、JsonUtils、ViewUtils、ScreenUtils、AnimatorUitls（部分参考于SwitchLayout）、HttpUtils、FileUtils、AppUitls、IdcardUtil、StatusBarHelper、RandomUtils、ConversionUtils等

- **基类**

    BaseApplication、BaeActivity、BaseFragment、LazyFragment、CommonAdapter、CommonRecyclerAdapter

**Version**

    【0.3.0】

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
        compile 'com.github.shichunlei:MyLibrary:0.3.0'
    }

或者

Step 1. Add the JitPack repository to your build file

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

Step 2. Add the dependency

    <dependency>
	    <groupId>com.github.shichunlei</groupId>
	    <artifactId>MyLibrary</artifactId>
	    <version>0.3.0</version>
	</dependency>

# Usage

    参见sample示例

===========================================================================================

### MarqueeView 用法

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

### CircleMenu 用法

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

| Attribute 属性          | Description 描述 | 
|:---				     |:---| 
|button_icons|Array of buttons icons.|
|button_colors|Array of buttons colors.|
|icon_menu|Menu default icon.|
|icon_close|Menu closed icon.|
|icon_color|Menu icon color.|
|duration_ring|Ring effect duration.|
|duration_open|Menu opening animation duration.|
|duration_close|Menu closing animation duration.|
|distance|Distance between center button and buttons|

===========================================================================================

### AnimNumberTextView 用法

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

### Switch 用法

<img src="/screenshot/switch.gif">

<img src="/screenshot/device-capture.png">

XML

    <chingtech.library.widget.SwitchButton
        android:id="@+id/switch_pattern_lock"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical"
        android:layout_marginRight="20dp"
        app:sb_background="#FFF"
        app:sb_button_color="#db99c7"
        app:sb_checked_color="#A36F95"
        app:sb_checkline_color="#a5dc88"
        app:sb_shadow_color="#A36F95"
        app:sb_uncheckcircle_color="#A36F95" />

Java

    SwitchButton switchButton = (com.suke.widget.SwitchButton)
    findViewById(R.id.switch_button);

    switchButton.setChecked(true);
    switchButton.isChecked();
    switchButton.toggle();     //switch state
    switchButton.toggle(false);//switch without animation
    switchButton.setShadowEffect(true);//disable shadow effect
    switchButton.setEnabled(false);//disable button
    switchButton.setEnableEffect(false);//disable the switch animation
    switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(SwitchButton view, boolean isChecked) {
            //TODO do your job
        }
    });

More Style:

|attr|format|description|
|---|:---|:---:|
|sb_shadow_radius|dimension|阴影半径|
|sb_shadow_offset|dimension|阴影偏移|
|sb_shadow_color|color|阴影颜色|
|sb_uncheck_color|color|关闭颜色|
|sb_checked_color|color|开启颜色|
|sb_border_width|dimension|边框宽度|
|sb_checkline_color|color|开启指示器颜色|
|sb_checkline_width|dimension|开启指示器线宽|
|sb_uncheckcircle_color|color|关闭指示器颜色|
|sb_uncheckcircle_width|dimension|关闭指示器线宽|
|sb_uncheckcircle_radius|dimension|关闭指示器半径|
|sb_checked|boolean|是否选中|
|sb_shadow_effect|boolean|是否启用阴影|
|sb_effect_duration|integer|动画时间，默认300ms|
|sb_button_color|color|按钮颜色|
|sb_show_indicator|boolean|是否显示指示器，默认true：显示|
|sb_background|color|背景色，默认白色|
|sb_enable_effect|boolean|是否启用特效，默认true|

===========================================================================================

### ThreeStateSwitch 用法

<img src="/screenshot/switch_three.gif">

Add the ThreeStateSwitch in your layout file and customize it the way you like it.

    <chingtech.library.widget.ThreeStateSwitch
        android:id="@+id/threeState"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        app:background_selected_color="#5bb434"
        app:background_normal_color="#bfbfbf"
        app:text_left="左"
        app:text_right="右"
        app:text_selected_color="#5bb434"
        app:text_normal_color="#646464"
        app:text_normal_size="16sp"
        app:text_selected_size="20sp"/>

You can set a listener for state changes

    threeState.setOnChangeListener(new ThreeStateSwitch.OnStateChangeListener() {
        @Override
        public void OnStateChangeListener(int currentState) {
            //current state=  -1  0  1
            Toast.makeText(MainActivity.this, String.valueOf(currentState), Toast.LENGTH_SHORT).show();
        }
    });

You can set typeface for texts.

    threeState.setNormalTextTypeface( );
    threeState.setSelectedTextTypeface( );

Get the current state. 

    //state=  -1  0  1
    threeState.getState();

##### 属性

| Name | Type | Default | Description |
|:----:|:----:|:-------:|:-----------:|
|background_selected_color|Color|#5bb434|选中时的背景色|
|background_normal_color|Color|#bfbfbf|未选中时的背景色|
|text_normal_color|Color|#646464|未选中的字的颜色|
|text_selected_color|Color|#5bb434|选中的字的颜色|
|text_left|String||左侧文字|
|text_right|String||右侧文字|
|text_normal_size|Dp or Sp|16sp|未选中的字的大小|
|text_selected_size|Dp or Sp|16sp|选中的字的大小|

===========================================================================================

### SwitchIconView 用法

<img src="/screenshot/SwitchIconView.gif">

SwitchIconView extends from AppCompatImageView so you can set icon with  ```app:srcCompat```

Set any icon (vector or image) to SwitchIconView and enjoy switchable icon in your app :)

Use ```app:siv_tint_color``` to set color to icon. Default color is black;

Use ```app:siv_disabled_color``` to set color when icon disabled. Default color is equals with ```app:siv_tint_color```;

Use ```app:siv_disabled_alpha``` to set alpha when icon disabled. Default alpha is ```.5```;

Use ```app:siv_no_dash``` if you don't want to draw dash, when icon disabled;

Use ```app:siv_animation_duration``` if you want to change switching state animation duration;

Use ```app:siv_enabled``` to set initial icon state;

Fully customized implementation:

    <chingtech.library.widget.SwitchIconView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:padding="8dp"
        app:si_animation_duration="500"
        app:si_disabled_alpha=".3"
        app:si_disabled_color="#b7b7b7"
        app:si_tint_color="#ff3c00"
        app:si_enabled="false"
        app:si_no_dash="true"
        app:srcCompat="@drawable/ic_cloud"/>

Public methods: 

    public void setIconEnabled(boolean enabled);
    
    public void setIconEnabled(boolean enabled, boolean animate);
    
    public boolean isIconEnabled();
    
    public void switchState();
    
    public void switchState(boolean animate);

===========================================================================================

### SmoothCheckBox 用法

<img src="/screenshot/smoothcb.gif">

Attrs 属性

|attr|format|description|
|---|:---|:---:|
|duration|integer|动画持续时间|
|stroke_width|dimension|未选中时边框宽度|
|color_tick|color|对勾颜色|
|color_checked|color|选中时填充颜色|
|color_unchecked|color|未选中时填充颜色|
|color_unchecked_stroke|color|未选中时边框颜色|

    setChecked(boolean checked);                   // 默认不带动画，若需要动画 调用重载方法
    setChecked(boolean checked, boolean animate);  // 参数: animate 是否显示动画

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final SmoothCheckBox scb = (SmoothCheckBox) findViewById(R.id.scb);
        scb.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                Log.d("SmoothCheckBox", String.valueOf(isChecked));
            }
        });
    }

===========================================================================================

### DecimalRulerView 用法

<img src="/screenshot/rulerview.png">

Attrs 属性

    <attr format="float" name="rv_defaultValue" />              <!-- 默认值 -->
    <attr format="float" name="rv_minValue" />                  <!-- 最小值 -->
    <attr format="float" name="rv_maxValue" />                  <!-- 最大值 -->
    <attr format="float" name="rv_spanValue" />                 <!-- 精度，最小支持0.1 -->
    <attr format="dimension" name="rv_itemSpacing" />           <!-- 每个刻度间的宽度 -->
    <attr format="dimension" name="rv_minLineHeight" />         <!-- 最短刻度线长度 -->
    <attr format="dimension" name="rv_maxLineHeight" />         <!-- 最长刻度线长度 -->
    <attr format="dimension" name="rv_middleLineHeight" />      <!-- 中间刻度线长度 -->
    <attr format="dimension" name="rv_minLineWidth" />          <!-- 最短刻度线宽度 -->
    <attr format="dimension" name="rv_maxLineWidth" />          <!-- 最长刻度线宽度 -->
    <attr format="dimension" name="rv_middleLineWidth" />       <!-- 中间刻度线宽度 -->
    <attr format="color" name="rv_scaleTextColor" />            <!-- 刻度盘文字颜色 -->
    <attr format="color" name="rv_minLineColor" />              <!-- 最短刻度线颜色 -->
    <attr format="color" name="rv_maxLineColor" />              <!-- 最大刻度线颜色 -->
    <attr format="color" name="rv_middleLineColor" />           <!-- 中间刻度线颜色 -->
    <attr format="dimension" name="rv_scaleTextSize" />         <!-- 刻度盘文字大小 -->
    <attr format="dimension" name="rv_textMarginTop" />         <!-- 刻度盘文字距离刻度边缘距离 -->
    <attr format="color" name="rv_indcatorColor" />             <!-- 指示器颜色 -->
    <attr format="dimension" name="rv_indcatorWidth" />         <!-- 指示器宽度，形状为三角时不起作用 -->
    <attr format="dimension" name="rv_indcatorHeight" />        <!-- 指示器高度，形状为三角时不起作用 -->
    <attr format="enum" name="rv_indcatorType">                 <!-- 指示器形状 -->
        <enum name="line" value="1" />                          <!-- 线 -->
        <enum name="triangle" value="2" />                      <!-- 三角 -->
    </attr>
    <attr format="color" name="rv_resultTextColor" />           <!-- 结果文字颜色 -->
    <attr format="color" name="rv_unitTextColor" />             <!-- 单位文字颜色 -->
    <attr format="dimension" name="rv_resultTextSize" />        <!-- 结果文字大小 -->
    <attr format="dimension" name="rv_unitTextSize" />          <!-- 单位文字大小 -->
    <attr format="string" name="rv_unit" />                     <!-- 单位 -->
    <attr format="boolean" name="rv_showResult" />              <!-- 是否显示结果 -->
    <attr format="boolean" name="rv_showUnit" />                <!-- 是否显示单位 -->
    <attr format="boolean" name="rv_alphaEnable" />             <!-- 是否刻度渐变 -->

XML

    <chingtech.library.widget.ruler.DecimalRulerView
        android:id="@+id/ruler_weight"
        android:layout_width="match_parent"
        android:layout_height="120dp"
        app:rv_alphaEnable="true"
        app:rv_indcatorColor="#414"
        app:rv_indcatorType="triangle"
        app:rv_indcatorWidth="5dp"
        app:rv_itemSpacing="8dp"
        app:rv_maxLineColor="#e6e"
        app:rv_maxLineHeight="30dp"
        app:rv_maxLineWidth="3dp"
        app:rv_middleLineColor="#ee2"
        app:rv_middleLineHeight="20dp"
        app:rv_middleLineWidth="2dp"
        app:rv_minLineColor="#e62"
        app:rv_minLineHeight="10dp"
        app:rv_minLineWidth="1dp"
        app:rv_resultTextColor="#444"
        app:rv_resultTextSize="20sp"
        app:rv_scaleTextColor="#e42"
        app:rv_scaleTextSize="15sp"
        app:rv_unit="CM"
        app:rv_unitTextColor="#666"
        app:rv_unitTextSize="15sp" />

Java

    mRulerView.initViewParam(20, 0, 100f, 1f);
    mRulerView.setChooseValueChangeListener(value -> {
        
    });

===========================================================================================

### DescProgressView 用法

<img src="/screenshot/progress_view.png">

XML

    <chingtech.library.widget.DescProgressView
        android:id="@+id/dpv_test"
        android:layout_width="match_parent"
        android:layout_height="120dp"
        android:background="@color/white" />

Java

    private List<String> descs= new ArrayList<>();
    DescProgressView descProgressView = findViewById(R.id.dpv_test);
    descs.add("提交");
    descs.add("上传凭证");
    descs.add("审核凭证");
    descs.add("支付赔款");
    descs.add("审核");
    descProgressView.setProgressDescs(descs, 2);

属性

|attr|format|description|
|---|:---|:---:|
|dpv_text_normal_color|color| 未选中字体颜色 |
|dpv_text_seleced_color|color| 选中字体颜色 |
|dpv_text_size|dimension| 字体大小 |
|dev_progress_bg_color|color| 选中背景颜色 |
|dev_progress_small_circle_color|color| 未选中圆点颜色 |
|dev_progress_big_circle_color|color| 选中圆点颜色 |

===========================================================================================

### WavyLineView 用法

<img src="/screenshot/wavyline.gif">

XML

    <chingtech.library.widget.WavyLineView
        android:id="@+id/wavyLineView"
        android:layout_width="match_parent"
        android:layout_height="200dp"/>
        
Java

    final WavyLineView wavyLineView = (WavyLineView) findViewById(R.id.wavyLineView);
    float initPeriod = (float) (2 * Math.PI / 180);
    wavyLineView.setPeriod(initPeriod);
    wavyLineView.setAmplitude(25);
    wavyLineView.setColor(getResources().getColor(R.color.colorAccent));
    wavyLineView.setStrokeWidth(dp2px(this, 2));

属性

|attr|format|description|
|---|:---|:---:|
|period|float||
|strokeColor|color|线的颜色|
|amplitude|dimension|振幅|
|strokeWidth|dimension|线的宽度|

===========================================================================================

### CouponView 用法

<img src="/screenshot/couponView.png">

自定义属性说明

| 自定义属性 |格式|说明|
| :---:|:---:|:---: |
|cv_dash_line_color|color|虚线的颜色|
|cv_dash_line_gap|dimension|虚线的间隔|
|cv_dash_line_height|dimension|虚线的高度|
|cv_dash_line_length|dimension|虚线的长度|
|cv_semicircle_color|color|半圆的颜色，一般需要和背景色一致|
|cv_semicircle_gap|dimension|半圆之前的间隔|
|cv_semicircle_radius|dimension|半圆的半径|
|cv_is_semicircle_top|boolean|是否绘制顶部半圆锯齿|
|cv_is_semicircle_bottom|boolean|是否绘制底部半圆锯齿|
|cv_is_semicircle_left|boolean|是否绘制左侧半圆锯齿|
|cv_is_semicircle_right|boolean|是否绘制右侧半圆锯齿|
|cv_is_dash_line_top|boolean|是否绘制顶部虚线|
|cv_is_dash_line_bottom|boolean|是否绘制底部虚线|
|cv_is_dash_line_left|boolean|是否绘制左侧虚线|
|cv_is_dash_line_right|boolean|是否绘制右侧虚线|
|cv_dash_line_margin_top|dimension|顶部虚线距离View顶部的距离|
|cv_dash_line_margin_bottom|dimension|底部虚线距离View底部的距离|
|cv_dash_line_margin_left|dimension|左侧虚线距离View左侧的距离|
|cv_dash_line_margin_right|dimension|右侧虚线距离View右侧的距离|


添加到你的xml

    <chingtech.library.widget.CouponView
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/couponView"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:background="@color/colorAccent"
        android:orientation="vertical"
        android:padding="10dp"
        app:cv_dash_line_color="@android:color/white"
        app:cv_dash_line_gap="5dp"
        app:cv_dash_line_height="0.8dp"
        app:cv_dash_line_length="10dp"
        app:cv_dash_line_margin_bottom="5dp"
        app:cv_dash_line_margin_left="5dp"
        app:cv_dash_line_margin_right="5dp"
        app:cv_dash_line_margin_top="8dp"
        app:cv_is_dash_line_bottom="true"
        app:cv_is_dash_line_left="true"
        app:cv_is_dash_line_right="true"
        app:cv_is_dash_line_top="false"
        app:cv_is_semicircle_bottom="false"
        app:cv_is_semicircle_left="false"
        app:cv_is_semicircle_right="false"
        app:cv_is_semicircle_top="true"
        app:cv_semicircle_color="@android:color/white"
        app:cv_semicircle_gap="4dp"
        app:cv_semicircle_radius="4dp">
    </chingtech.library.widget.CouponView>

===========================================================================================

### RippleView 用法

<img src="/screenshot/RippleView.gif">

自定义属性

| 自定义属性 |格式|说明|
| :---:|:---:|:---: |
|image|reference|图片|
|sqNot|boolean|false是深浅计算，true是浅深计算|
|starWhat|boolean|是否执行效果|
|roundRimColor|color|圆边颜色设置|
|roundRimSize|integer|圆边大小设置|

===========================================================================================

### LabelView 用法

<img src="/screenshot/preview.png">

自定义属性

|name|format|description|
|:---:|:---:|:---:|
| lv_text | string | 设置文字内容 
| lv_text_color | color | 设置文字颜色,默认#ffffff
| lv_text_size | dimension | 设置文字大小,默认11sp
| lv_text_bold | boolean | 设置文字是否支持加粗,默认true
| lv_text_all_caps | boolean | 设置文字是否支持全部大写,默认true
| lv_background_color | color | 设置背景颜色,默认"#FF4081"
| lv_min_size | dimension | 设置LabelView所在矩形最小宽高,默认mFillTriangle?35dp:50dp
| lv_padding | dimension | 设置文字上下padding,默认3.5dp,mFillTriangle为true时无效
| lv_gravity | enum | 设置LabelView方向,支持左上或右上或左下或右下,默认左上
| lv_fill_triangle | boolean | 设置是否填充三角区域,默认false

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
- _[BGABanner-Android](https://github.com/bingoogolapple/BGABanner-Android) # 引导界面滑动导航 + 大于等于1页时无限轮播 + 各种切换动画轮播效果_
- _等等。。。_

# 更新日志

【0.3.0】

    add MarqueeView
    add CircleMenu
    add AnimNumberTextView
    add ThreeStateSwitch
    add SwitchIconView
    add DescProgressView
    add WavyLineView
    add CouponView
    add RippleView

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

# MyLibrary

    根据自己项目用到过的一些自定义控件，也有部分是网上找的，在这里根据自己的项目需求稍作修改

**Include**

- **AlertDialog**

- **BottomDialog**

- **ProgressDialog**

- **HorizontalProgressDialog**

- **RoundImageView**

    圆形图片、圆角图片、椭圆图片

    原始项目地址：https://github.com/RaphetS/RoundImageView

- **SmoothCheckBox**

    原始项目地址：https://github.com/andyxialm/SmoothCheckBox

- **ExpandIconView**

    原始项目地址：https://github.com/zagum/Android-ExpandIcon

- **CommonAdapter**、**CommonRecyclerAdapter**

    Android 轻量级适配器，简化使用，通吃所有的AbsListView、RecyclerView。支持Header与Footer

    参考项目地址：https://github.com/smuyyh/EasyAdapter

- **FlipView**

    可翻转的View，仿支付宝-蚂蚁宝卡里流量兑换的翻转View

    参考项目地址：https://github.com/wajahatkarim3/EasyFlipView

- **NumberAnimTextView**

    带数字增加动画的TextView

    参考项目地址：https://github.com/Bakumon/NumberAnimTextView

- **LabelView**

    角标

    原始项目地址：https://github.com/H07000223/FlycoLabelView

- **SwipeToLoadLayout**

    SwipeToLoadLayout is a reusable pull-to-refresh and pull-to-load-more widget.

    原始项目地址：https://github.com/Aspsine/SwipeToLoadLayout

- **工具类**

    StringUtils、TimeUtils、SPUtils、JsonUtils、ViewUtils、ScreenUtils、HttpUtils、FileUtils、AppUitls、IdcardUtil、StatusBarHelper等

**Version**

    【0.0.8】

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
        compile 'com.github.shichunlei:MyLibrary:0.0.7'
    }

# Usage

    参见sample示例

# 更新日志

【0.0.1】

    创建项目，添加了一些工具类

【0.0.2】

    添加工具类；
    ExpandIcon

【0.0.3】

    添加完善工具类；
    添加自定义控件“可翻转的View，仿支付宝-蚂蚁宝卡里流量兑换的翻转View”

【0.0.4】

    添加BottomDialog和NumberAnimTextView

【0.0.5】

    添加HorizontalProgressDialog；
    添加StatusBarHelper；
    去掉枚举并转换成Integer，避免增加app的class.dex文件的大小

【0.0.6】

    修改ProgressDialog

【0.0.7】

    StringUtils、TimeUtils、ViewUtils类中添加新的方法
    添加 SwipeToLoadLayout 下拉刷新上拉加载

【0.0.8】

    添加 LabelView 角标

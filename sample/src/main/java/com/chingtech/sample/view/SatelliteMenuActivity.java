package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.SatelliteMenu;
import com.chingtech.sample.R;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.sample.view
 * Description:
 * Created by 师春雷
 * Created at 17/10/28 上午10:50
 */
public class SatelliteMenuActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.layout)
    RelativeLayout layout;

    @BindView(R.id.mSatelliteMenuLeftTop)
    SatelliteMenu mSatelliteMenuLeftTop;
    @BindView(R.id.mSatelliteMenuRightTop)
    SatelliteMenu mSatelliteMenuRightTop;
    @BindView(R.id.mSatelliteMenuRightBottom)
    SatelliteMenu mSatelliteMenuRightBottom;
    @BindView(R.id.mSatelliteMenuLeftBottom)
    SatelliteMenu mSatelliteMenuLeftBottom;

    @Override
    protected void init() {
        List<Integer> imageResourceLeftTop = new ArrayList<>();//菜单图片,可根据需要设置子菜单个数
        imageResourceLeftTop.add(R.drawable.imag_msg);
        imageResourceLeftTop.add(R.drawable.imag_music);
        imageResourceLeftTop.add(R.drawable.imag_pic);
        imageResourceLeftTop.add(R.drawable.imag_take_photo);
        imageResourceLeftTop.add(R.drawable.imag_tel);
        List<String> nameMenuItem = new ArrayList<>();//菜单图片,可根据需要设置子菜单个数
        nameMenuItem.add("短信");
        nameMenuItem.add("音乐");
        nameMenuItem.add("图库");
        nameMenuItem.add("相机");
        nameMenuItem.add("电话");

        mSatelliteMenuLeftTop.getmBuilder()
                             .setMenuItemNameTexts(nameMenuItem)
                             .setMenuImage(R.drawable.menu)
                             .setMenuItemImageResource(imageResourceLeftTop)
                             .setOnMenuItemClickListener(
                                     (view, postion) -> showToast("点击了菜单:" + postion))
                             .creat();

        List<Integer> imageResourceRightTop = new ArrayList<>();//菜单图片,可根据需要设置子菜单个数
        imageResourceRightTop.add(R.drawable.imag_msg);
        imageResourceRightTop.add(R.drawable.imag_music);
        imageResourceRightTop.add(R.drawable.imag_pic);
        imageResourceRightTop.add(R.drawable.imag_take_photo);
        imageResourceRightTop.add(R.drawable.imag_tel);
        imageResourceRightTop.add(R.drawable.iv_move);

        mSatelliteMenuRightTop.getmBuilder()
                              .setMenuImage(R.drawable.menu)
                              .setMenuItemImageResource(imageResourceRightTop)
                              .setOnMenuItemClickListener(
                                      (view, postion) -> showToast("点击了菜单:" + postion))
                              .creat();

        List<Integer> imageResourceLeftBottom = new ArrayList<>();//菜单图片,可根据需要设置子菜单个数
        imageResourceLeftBottom.add(R.drawable.imag_msg);
        imageResourceLeftBottom.add(R.drawable.imag_music);
        imageResourceLeftBottom.add(R.drawable.imag_pic);
        imageResourceLeftBottom.add(R.drawable.imag_take_photo);
        imageResourceLeftBottom.add(R.drawable.imag_tel);
        imageResourceLeftBottom.add(R.drawable.iv_move);
        imageResourceLeftBottom.add(R.drawable.iv_time);

        mSatelliteMenuLeftBottom.getmBuilder()
                                .setMenuImage(R.drawable.menu)
                                .setMenuItemImageResource(imageResourceLeftBottom)
                                .setOnMenuItemClickListener(
                                        (view, postion) -> showToast("点击了菜单:" + postion))
                                .creat();

        List<Integer> imageResourceRightBottom = new ArrayList<>();//菜单图片,可根据需要设置子菜单个数
        imageResourceRightBottom.add(R.drawable.imag_msg);
        imageResourceRightBottom.add(R.drawable.imag_music);
        imageResourceRightBottom.add(R.drawable.imag_pic);
        imageResourceRightBottom.add(R.drawable.imag_take_photo);
        imageResourceRightBottom.add(R.drawable.imag_tel);
        imageResourceRightBottom.add(R.drawable.iv_move);
        imageResourceRightBottom.add(R.drawable.iv_time);
        imageResourceRightBottom.add(R.drawable.iv_mobile);

        mSatelliteMenuRightBottom.getmBuilder()
                                 .setMenuImage(R.drawable.menu)
                                 .setMenuItemImageResource(imageResourceRightBottom)
                                 .setOnMenuItemClickListener(
                                         (view, postion) -> showToast("点击了菜单:" + postion))
                                 .creat();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_satellite_menu;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("SatelliteMenu");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    @Override
    protected void loadData() {

    }
}

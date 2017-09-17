package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.bean.RadarData;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.widget.RadarView;
import com.chingtech.sample.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * MyLibrary
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 17/7/29 上午8:38
 */
@ContentView(R.layout.activity_radarview)
public class RadarViewActivity extends BaseActivity {

    @ViewInject(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewInject(R.id.tv_title)
    protected TextView tvTitle;

    @ViewInject(R.id.radarView)
    private RadarView mRadarView;

    @Override
    protected void init() {
        mRadarView.setEmptyHint("无数据");

        List<Integer> layerColor = new ArrayList<>();
        Collections.addAll(layerColor, 0x3300bcd4, 0x3303a9f4, 0x335677fc, 0x333f51b5, 0x33673ab7);
        mRadarView.setLayerColor(layerColor);

        mRadarView.setmRotateAngle(0.65);

        List<String> vertexText = new ArrayList<>();
        Collections.addAll(vertexText, "力量", "敏捷", "速度", "智力", "耐力");
        mRadarView.setVertexText(vertexText);

        List<Float> values = new ArrayList<>();
        Collections.addAll(values, 30f, 60f, 120f, 70f, 50f);
        RadarData data = new RadarData(values, false);

        List<Float> values2 = new ArrayList<>();
        Collections.addAll(values2, 70f, 10f, 40f, 20f, 80f);
        RadarData data2 = new RadarData(values2, 0x3303a9f4);
        mRadarView.addData(data);
        mRadarView.addData(data2);
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvTitle.setText("雷达图");
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.radarView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggle_rotation:
                mRadarView.setRotationEnable(!mRadarView.isRotationEnable());
                break;
            case R.id.anime_value:
                mRadarView.animeValue(2000);
                break;
            case R.id.change_layer:
                int randomInt = new Random().nextInt(6) + 1;
                mRadarView.setLayer(randomInt);
                break;
            case R.id.change_web_mode:
                if (mRadarView.getWebMode() == RadarView.WEB_MODE_POLYGON) {
                    mRadarView.setWebMode(RadarView.WEB_MODE_CIRCLE);
                } else {
                    mRadarView.setWebMode(RadarView.WEB_MODE_POLYGON);
                }
                break;
            case R.id.toggle_line_enable:
                mRadarView.setRadarLineEnable(!mRadarView.isRadarLineEnable());
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

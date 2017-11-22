package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;
import com.codbking.calendar.CalendarDateView;
import com.codbking.calendar.CalendarUtil;
import java.util.Date;

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
 * Created at 17/11/4 上午9:17
 */
public class XiaomiActivity extends BaseActivity {

    @BindView(R.id.calendarDateView)
    CalendarDateView mCalendarDateView;

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected void init() {
        mCalendarDateView.setAdapter((convertView, parentView, bean) -> {

            if (convertView == null) {
                convertView = LayoutInflater.from(parentView.getContext())
                                            .inflate(R.layout.item_xiaomi, null);
            }

            TextView chinaText = convertView.findViewById(R.id.chinaText);
            TextView text      = convertView.findViewById(R.id.text);

            text.setText("" + bean.day);
            if (bean.mothFlag != 0) {
                text.setTextColor(0xff9299a1);
            } else {
                text.setTextColor(0xff444444);
            }
            chinaText.setText(bean.chinaDay);

            return convertView;
        });

        mCalendarDateView.setOnItemClickListener((view, postion, bean) -> mTitle.setText(
                bean.year + "/" + bean.moth + "/" + bean.day));

        int[] data = CalendarUtil.getYMD(new Date());
        mTitle.setText(data[0] + "/" + data[1] + "/" + data[2]);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dingding;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {
    }
}

package com.chingtech.sample.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import com.chingtech.sample.R;
import com.necer.ncalendar.calendar.NCalendar;
import com.necer.ncalendar.listener.OnCalendarChangedListener;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

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
 * Created at 17/11/4 上午8:25
 */
public class NCalendarActivity extends BaseActivity implements OnCalendarChangedListener {

    @BindView(R.id.ncalendarrrr)
    NCalendar ncalendar;
    @BindView(R.id.tv_month)
    TextView  tv_month;
    @BindView(R.id.tv_today)
    TextView  tv_today;
    @BindView(R.id.tv_date)
    TextView  tv_date;

    @BindView(R.id.layout)
    LinearLayout layout;

    int month = -1;

    @Override
    protected void init() {
        ncalendar.setDateInterval("2017-04-02", "2018-01-01");
        ncalendar.setOnCalendarChangedListener(this);

        ncalendar.post(() -> {
            List<String> list = new ArrayList<>();
            list.add("2017-09-21");
            list.add("2017-10-21");
            list.add("2017-10-1");
            list.add("2017-10-15");
            list.add("2017-10-18");
            list.add("2017-10-26");
            list.add("2017-11-21");

            ncalendar.setPoint(list);
        });

        tv_today.setOnClickListener(view -> {
            toToday(view);
            tv_today.setVisibility(View.GONE);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ncalendar;
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected View injectTarget() {
        return layout;
    }

    @Override
    protected void loadData() {
    }

    @Override
    public void onCalendarChanged(DateTime dateTime) {
        if (month == -1) {
            month = dateTime.getMonthOfYear();
        }
        if (month != dateTime.getMonthOfYear()) {
            month = dateTime.getMonthOfYear();
        }

        LogUtils.i("TAG", "onCalendarChanged: " + dateTime.getMonthOfYear());
        tv_month.setText(dateTime.getMonthOfYear() + "月");
        tv_date.setText(dateTime.getYear()
                                + "年"
                                + dateTime.getMonthOfYear()
                                + "月"
                                + dateTime.getDayOfMonth()
                                + "日");

        LogUtils.i("TAG", +dateTime.getYear()
                + "-------"
                + dateTime.getMonthOfYear()
                + "-------"
                + dateTime.getDayOfMonth());

        if ((dateTime.getYear() + "-" + dateTime.getMonthOfYear() + "-" + dateTime.getDayOfMonth())
                != "2017-11-4") {
            tv_today.setVisibility(View.VISIBLE);
        }
    }

    public void setDate(View view) {
        ncalendar.setDate("2017-11-04");
    }

    public void toMonth(View view) {
        ncalendar.toMonth();
    }

    public void toWeek(View view) {
        ncalendar.toWeek();
    }

    public void toToday(View view) {
        ncalendar.toToday();
    }

    public void toNextPager(View view) {
        ncalendar.toNextPager();
    }

    public void toLastPager(View view) {
        ncalendar.toLastPager();
    }

    public void setPoint(View view) {
        List<String> list = new ArrayList<>();
        list.add("2017-09-21");
        list.add("2017-10-21");
        list.add("2017-10-1");
        list.add("2017-10-15");
        list.add("2017-10-18");
        list.add("2017-10-26");
        list.add("2017-11-21");

        ncalendar.setPoint(list);
    }
}

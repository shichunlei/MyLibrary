package com.chingtech.sample.view;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.utils.TimeUtils;
import com.chingtech.sample.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static chingtech.library.utils.AnimatorUitls.*;
import static chingtech.library.utils.TimeUtils.DATE_FORMAT;

/**
 * MyLibrary
 * Package com.example.compactcalendarviewtoolbar
 * Description:
 * Created by 师春雷
 * Created at 17/7/8 上午10:10
 */
@ContentView(R.layout.activity_compact)
public class CompactActivity extends BaseActivity {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @ViewInject(R.id.toolbar)
    protected Toolbar toolbar;

    @ViewInject(R.id.tv_title)
    protected TextView datePickerTextView;

    @ViewInject(R.id.date_picker_arrow)
    protected ImageView arrow;

    @ViewInject(R.id.date_picker_button)
    LinearLayout datePickerButton;

    @ViewInject(R.id.compactcalendar_view)
    private CompactCalendarView mCompactCalendarView;

    @ViewInject(R.id.layout_pop)
    LinearLayout pop;

    @ViewInject(R.id.tv_txt)
    protected TextView txt;

    private boolean isExpanded = false;

    List<Event> events = new ArrayList<>();

    @Override
    protected void init() {
        arrow.setVisibility(View.VISIBLE);
        // Force English
        mCompactCalendarView.setLocale(TimeZone.getDefault(), Locale.ENGLISH);

        mCompactCalendarView.setShouldDrawDaysHeader(true);
        mCompactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);

        mCompactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                String strDate = TimeUtils.formatDateTime(dateClicked, DATE_FORMAT);
                showToast(strDate);
                setSubtitle(strDate);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setSubtitle(dateFormat.format(firstDayOfNewMonth));
            }
        });

        // Set current date to today
        setCurrentDate(new Date());

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    ViewCompat.animate(arrow).rotation(0).start();
                    isExpanded = false;
                    ScaleToBigHorizontalOut(pop);
                } else {
                    ViewCompat.animate(arrow).rotation(180).start();
                    isExpanded = true;
                    ScaleToBigHorizontalIn(pop);
                }
            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ShakeMode(3, txt);

                // FlipUpDown(txt, true);

                FlipLeftRight(txt, true);
            }
        });
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    public void setCurrentDate(Date date) {

        setData();

        setSubtitle(dateFormat.format(date));
        if (mCompactCalendarView != null) {
            mCompactCalendarView.setCurrentDate(date);
        }
    }

    private void setData() {
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-07-03")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-07-02")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-07-04")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-07-06")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-07-09")));

        mCompactCalendarView.addEvents(events);
    }

    public void setSubtitle(String subtitle) {
        if (datePickerTextView != null) {
            datePickerTextView.setText(subtitle);
        }
    }
}

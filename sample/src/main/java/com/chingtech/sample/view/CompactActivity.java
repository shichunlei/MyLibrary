package com.chingtech.sample.view;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
 * Description: 日历使用、动画工具类使用
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

    private AnimType type = AnimType.ALPHA;

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

        datePickerButton.setOnClickListener(v -> {
            //            if (isExpanded) {
            //                ViewCompat.animate(arrow).rotation(0).start();
            //                isExpanded = false;
            //                ScaleToBigHorizontalOut(pop);
            //            } else {
            //                ViewCompat.animate(arrow).rotation(180).start();
            //                isExpanded = true;
            //                ScaleToBigHorizontalIn(pop);
            //            }

            setAnim();
        });

        txt.setOnClickListener(v -> {
            // ShakeMode(3, txt);

            // FlipUpDown(txt, true);

            FlipLeftRight(txt, true);
        });
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

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

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu_anim, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alpha:
                type = AnimType.ALPHA;
                break;
            case R.id.bottom:
                type = AnimType.BOTTOM;
                break;
            case R.id.top:
                type = AnimType.TOP;
                break;
            case R.id.left:
                type = AnimType.LEFT;
                break;
            case R.id.right:
                type = AnimType.RIGHT;
                break;
            case R.id.left_center_rota:
                type = AnimType.LEFT_CENTER_ROTA;
                break;
            case R.id.left_top_rota:
                type = AnimType.LEFT_TOP_ROTA;
                break;
            case R.id.center_rota:
                type = AnimType.CENTER_ROTA;
                break;
            case R.id.center_zoom:
                type = AnimType.CENTER_ZOOM;
                break;
            case R.id.left_top_zoom:
                type = AnimType.LEFT_TOP_ZOOM;
                break;
            case R.id.scale_horizontal:
                type = AnimType.SCALE_HORIZONTAL;
                break;
            case R.id.scale_vertical:
                type = AnimType.SCALE_VERTICAL;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAnim() {
        if (isExpanded) {
            ViewCompat.animate(arrow).rotation(0).start();
            isExpanded = false;

            switch (type) {
                case ALPHA:
                    hidenAlphaView(pop);
                    break;
                case TOP:
                    moveToTopHiden(pop);
                    break;
                case BOTTOM:
                    moveToBottomHiden(pop);
                    break;
                case LEFT:
                    fromLeftOut(pop);
                    break;
                case RIGHT:
                    fromRightOut(pop);
                    break;
                case LEFT_CENTER_ROTA:
                    RotaLeftCenterOut(pop);
                    break;
                case LEFT_TOP_ROTA:
                    RotaLeftTopOut(pop);
                    break;
                case CENTER_ROTA:
                    RotaCenterOut(pop);
                    break;
                case CENTER_ZOOM:
                    ScaleSmall(pop);
                    break;
                case LEFT_TOP_ZOOM:
                    ScaleSmallLeftTop(pop);
                    break;
                case SCALE_HORIZONTAL:
                    ScaleToBigHorizontalOut(pop);
                    break;
                case SCALE_VERTICAL:
                    ScaleToBigVerticalOut(pop);
                    break;
            }
        } else {
            ViewCompat.animate(arrow).rotation(180).start();
            isExpanded = true;
            switch (type) {
                case ALPHA:
                    showAlphaView(pop);
                    break;
                case TOP:
                    fromTopToLocationShow(pop);
                    break;
                case BOTTOM:
                    fromBottomToLocationShow(pop);
                    break;
                case LEFT:
                    fromLeftIn(pop);
                    break;
                case RIGHT:
                    fromRightIn(pop);
                    break;
                case LEFT_CENTER_ROTA:
                    RotaLeftCenterIn(pop);
                    break;
                case LEFT_TOP_ROTA:
                    RotaLeftTopIn(pop);
                    break;
                case CENTER_ROTA:
                    RotaCenterIn(pop);
                    break;
                case CENTER_ZOOM:
                    ScaleBig(pop);
                    break;
                case LEFT_TOP_ZOOM:
                    ScaleBigLeftTop(pop);
                    break;
                case SCALE_HORIZONTAL:
                    ScaleToBigHorizontalIn(pop);
                    break;
                case SCALE_VERTICAL:
                    ScaleToBigVerticalIn(pop);
                    break;
            }
        }
    }

    private enum AnimType {
        ALPHA, TOP, BOTTOM, LEFT, RIGHT, LEFT_CENTER_ROTA, LEFT_TOP_ROTA, CENTER_ROTA, CENTER_ZOOM, LEFT_TOP_ZOOM, SCALE_HORIZONTAL, SCALE_VERTICAL
    }
}

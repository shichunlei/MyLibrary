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
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.utils.TimeUtils;
import com.chingtech.sample.http.JiSuHttpManager;
import com.chingtech.sample.R;
import com.chingtech.sample.bean.JiSuBaseBean;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static chingtech.library.utils.AnimatorUtils.FlipLeftRight;
import static chingtech.library.utils.AnimatorUtils.RotaCenterIn;
import static chingtech.library.utils.AnimatorUtils.RotaCenterOut;
import static chingtech.library.utils.AnimatorUtils.RotaLeftCenterIn;
import static chingtech.library.utils.AnimatorUtils.RotaLeftCenterOut;
import static chingtech.library.utils.AnimatorUtils.RotaLeftTopIn;
import static chingtech.library.utils.AnimatorUtils.RotaLeftTopOut;
import static chingtech.library.utils.AnimatorUtils.ScaleBig;
import static chingtech.library.utils.AnimatorUtils.ScaleBigLeftTop;
import static chingtech.library.utils.AnimatorUtils.ScaleSmall;
import static chingtech.library.utils.AnimatorUtils.ScaleSmallLeftTop;
import static chingtech.library.utils.AnimatorUtils.ScaleToBigHorizontalIn;
import static chingtech.library.utils.AnimatorUtils.ScaleToBigHorizontalOut;
import static chingtech.library.utils.AnimatorUtils.ScaleToBigVerticalIn;
import static chingtech.library.utils.AnimatorUtils.ScaleToBigVerticalOut;
import static chingtech.library.utils.AnimatorUtils.fromBottomToLocationShow;
import static chingtech.library.utils.AnimatorUtils.fromLeftIn;
import static chingtech.library.utils.AnimatorUtils.fromLeftOut;
import static chingtech.library.utils.AnimatorUtils.fromRightIn;
import static chingtech.library.utils.AnimatorUtils.fromRightOut;
import static chingtech.library.utils.AnimatorUtils.fromTopToLocationShow;
import static chingtech.library.utils.AnimatorUtils.hidenAlphaView;
import static chingtech.library.utils.AnimatorUtils.moveToBottomHiden;
import static chingtech.library.utils.AnimatorUtils.moveToTopHiden;
import static chingtech.library.utils.AnimatorUtils.showAlphaView;
import static chingtech.library.utils.TimeUtils.DATE_FORMAT;
import static com.chingtech.sample.http.ApiUtils.JISU_KEY;

/**
 * MyLibrary
 * Package com.example.compactcalendarviewtoolbar
 * Description: 日历使用、动画工具类使用
 * Created by 师春雷
 * Created at 17/7/8 上午10:10
 */
public class CompactActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView datePickerTextView;

    @BindView(R.id.date_picker_arrow)
    ImageView arrow;

    @BindView(R.id.date_picker_button)
    LinearLayout datePickerButton;

    @BindView(R.id.compactcalendar_view)
    CompactCalendarView mCompactCalendarView;

    @BindView(R.id.layout_pop)
    LinearLayout pop;

    @BindView(R.id.tv_txt)
    TextView txt;

    @BindView(R.id.tv_yangli)
    TextView tvYangli;
    @BindView(R.id.tv_nongli)
    TextView tvNongli;
    @BindView(R.id.tv_huangli)
    TextView tvHuangli;
    @BindView(R.id.tv_yi)
    TextView tvYi;
    @BindView(R.id.tv_ji)
    TextView tvJi;
    @BindView(R.id.tv_caishen)
    TextView tvCaishen;
    @BindView(R.id.tv_xishen)
    TextView tvXishen;
    @BindView(R.id.tv_fushen)
    TextView tvFushen;
    @BindView(R.id.tv_xiongshen)
    TextView tvXiongshen;
    @BindView(R.id.tv_jiri)
    TextView tvJiri;
    @BindView(R.id.tv_zhiri)
    TextView tvZhiri;
    @BindView(R.id.tv_jishenyiqu)
    TextView tvJishenyiqu;
    @BindView(R.id.tv_wuxing)
    TextView tvWuxing;
    @BindView(R.id.tv_sha)
    TextView tvSha;
    @BindView(R.id.tv_chong)
    TextView tvChong;
    @BindView(R.id.tv_suici)
    TextView tvSuici;
    @BindView(R.id.tv_taishen)
    TextView tvTaishen;

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
                setSubtitle(strDate);
                loadDate(strDate);

                setAnim();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                setSubtitle(TimeUtils.formatDateTime(firstDayOfNewMonth, DATE_FORMAT));
            }
        });

        datePickerButton.setOnClickListener(v -> {
            setAnim();
        });

        txt.setOnClickListener(v -> {
            // ShakeMode(3, txt);

            // FlipUpDown(txt, true);

            FlipLeftRight(txt, true);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_compact;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    private void setData() {
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-03")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-02")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-04")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-06")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-09")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-13")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-12")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-14")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-16")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-19")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-23")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-22")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-24")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-26")));
        events.add(new Event(Color.YELLOW, TimeUtils.dateToLong("2017-09-29")));

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
    protected void loadData() {
        setData();
        String today = TimeUtils.getNowDateTime(DATE_FORMAT);

        // Set current date to today
        setSubtitle(today);
        if (mCompactCalendarView != null) {
            mCompactCalendarView.setCurrentDate(new Date());
        }

        loadDate(today);
    }

    private void loadDate(String date) {
        mStateView.showLoading();
        JiSuHttpManager.getInstance()
                       .getApiService()
                       .getCalendar(JISU_KEY, date)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .subscribe(new Observer<JiSuBaseBean<com.chingtech.sample.bean.Calendar>>() {
                           @Override
                           public void onSubscribe(Disposable d) {
                           }

                           @Override
                           public void onNext(
                                   JiSuBaseBean<com.chingtech.sample.bean.Calendar> value) {
                               if (value.getStatus().equals("0")) {
                                   LogUtils.i("TAG", value.getResult().toString());

                                   setWannianliData(value.getResult());
                               } else {
                                   mStateView.showRetry();
                               }
                           }

                           @Override
                           public void onError(Throwable e) {
                               LogUtils.d("TAG", e.getMessage().toString());
                               mStateView.showRetry();
                           }

                           @Override
                           public void onComplete() {
                               mStateView.showContent();
                           }
                       });
    }

    private void setWannianliData(com.chingtech.sample.bean.Calendar value) {
        tvYangli.setText(value.getYear() + "-" + value.getMonth() + "-" + value.getDay());
        tvNongli.setText(value.getHuangli().getNongli());
        tvHuangli.setText(value.getGanzhi()
                                  + "["
                                  + value.getShengxiao()
                                  + "]年 周"
                                  + value.getWeek()
                                  + " "
                                  + value.getStar());

        tvCaishen.setText(value.getHuangli().getCaishen());
        tvFushen.setText(value.getHuangli().getFushen());
        tvXishen.setText(value.getHuangli().getXishen());

        tvXiongshen.setText(value.getHuangli().getXiongshen());
        tvSha.setText(value.getHuangli().getSha());
        tvChong.setText(value.getHuangli().getChong());
        tvJiri.setText(value.getHuangli().getJiri());
        tvZhiri.setText(value.getHuangli().getZhiri());
        tvWuxing.setText(value.getHuangli().getWuxing());
        tvJishenyiqu.setText(value.getHuangli().getJishenyiqu());
        tvTaishen.setText(value.getHuangli().getTaishen());

        List<String> suici = value.getHuangli().getSuici();
        List<String> yi    = value.getHuangli().getYi();
        List<String> ji    = value.getHuangli().getJi();

        StringBuilder sbSuici = new StringBuilder();
        for (int i = 0; i < suici.size(); i++) {
            sbSuici.append(suici.get(i));
        }

        StringBuilder sbJi = new StringBuilder();
        for (int i = 0; i < ji.size(); i++) {
            sbJi.append(ji.get(i));
        }

        StringBuilder sbYi = new StringBuilder();
        for (int i = 0; i < yi.size(); i++) {
            sbYi.append(yi.get(i));
        }

        tvJi.setText(sbJi.toString());
        tvYi.setText(sbYi.toString());
        tvSuici.setText(sbSuici.toString());
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

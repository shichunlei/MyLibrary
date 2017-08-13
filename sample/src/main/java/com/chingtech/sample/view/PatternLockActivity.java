package com.chingtech.sample.view;

import android.util.Log;
import chingtech.library.base.activity.BaseActivity;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.chingtech.sample.R;
import java.util.List;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 17/8/13 上午8:42
 */
@ContentView(R.layout.activity_pattern_lock)
public class PatternLockActivity extends BaseActivity {

    @ViewInject(R.id.patter_lock_view)
    private PatternLockView mPatternLockView;

    private PatternLockViewListener mPatternLockViewListener = new PatternLockViewListener() {
        @Override
        public void onStarted() {
            Log.d(getClass().getName(), "Pattern drawing started");
        }

        @Override
        public void onProgress(List<PatternLockView.Dot> progressPattern) {
            Log.d(getClass().getName(),
                  "Pattern progress: " + PatternLockUtils.patternToString(mPatternLockView,
                                                                          progressPattern));
        }

        @Override
        public void onComplete(List<PatternLockView.Dot> pattern) {
            Log.d(getClass().getName(),
                  "Pattern complete: " + PatternLockUtils.patternToString(mPatternLockView,
                                                                          pattern));

            mPatternLockView.clearPattern();
            openActivity(MainActivity.class, true);
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    @Override
    protected void init() {
        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        mPatternLockView.setInStealthMode(false);
        mPatternLockView.setTactileFeedbackEnabled(true);
        mPatternLockView.setInputEnabled(true);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);
    }

    @Override
    protected void initToolBar() {
    }
}

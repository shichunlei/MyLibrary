package com.chingtech.sample.view;

import android.util.Log;
import android.view.View;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.AnimatorUitls;
import chingtech.library.utils.SPUtils;
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

    private String flag;

    private String password;

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

            String pwd = PatternLockUtils.patternToString(mPatternLockView, pattern);

            Log.d(getClass().getName(), "Pattern complete: " + pwd);

            if (flag.equals("welcome")) {
                password = (String) SPUtils.get(context, "pattern_lock", "", "DEMO");
                if (pwd.equals(password)) {
                    mPatternLockView.clearPattern();
                    openActivity(MainActivity.class, true);
                } else {
                    mPatternLockView.clearPattern();
                    showToast("密码错误");
                }
            } else if (flag.equals("setting")) {
                SPUtils.put(context, "PatternLock", true, "DEMO");
                SPUtils.put(context, "pattern_lock", pwd, "DEMO");
                finish();
            }
        }

        @Override
        public void onCleared() {
            Log.d(getClass().getName(), "Pattern has been cleared");
        }
    };

    @Override
    protected void init() {

        flag = getStringExtra("flag");

        mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
        mPatternLockView.setInStealthMode(false);
        mPatternLockView.setTactileFeedbackEnabled(true);
        mPatternLockView.setInputEnabled(true);
        mPatternLockView.addPatternLockListener(mPatternLockViewListener);
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }
}

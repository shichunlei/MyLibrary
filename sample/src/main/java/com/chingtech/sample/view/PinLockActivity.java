package com.chingtech.sample.view;

import android.util.Log;
import android.view.View;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.AnimatorUtils;
import chingtech.library.utils.SPUtils;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.chingtech.sample.R;

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
 * Created at 17/8/13 上午9:45
 */
public class PinLockActivity extends BaseActivity {

    @BindView(R.id.pin_lock_view)
    PinLockView   mPinLockView;
    @BindView(R.id.indicator_dots)
    IndicatorDots mIndicatorDots;

    private String flag;

    private String password;

    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            if (flag.equals("welcome")) {
                password = (String) SPUtils.get(context, "pin_lock", "", "DEMO");
                Log.d(getClass().getName(), "Pin complete: " + pin);
                if (pin.equals(password)) {
                    openActivity(LoginActivity.class, true);
                } else {
                    mPinLockView.resetPinLockView();
                    AnimatorUtils.ShakeMode(3, mIndicatorDots);
                }
            } else if (flag.equals("setting")) {
                SPUtils.put(context, "PinLock", true, "DEMO");
                SPUtils.put(context, "pin_lock", pin, "DEMO");
                finish();
            }
        }

        @Override
        public void onEmpty() {
            Log.d(getClass().getName(), "Pin empty");
        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Log.d(getClass().getName(), "Pin changed, new length "
                    + pinLength
                    + " with intermediate pin "
                    + intermediatePin);
        }
    };

    @Override
    protected void init() {
        flag = getStringExtra("flag");

        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        // mPinLockView.setCustomKeySet(new int[] {2, 3, 1, 5, 9, 6, 7, 0, 8, 4}); // 自定义数字键盘数字顺序
        // mPinLockView.enableLayoutShuffling(); // 数字键盘随即打乱
        // mPinLockView.setButtonBackgroundDrawable(getResources().getDrawable(R.drawable.gray_thumb));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pin_lock;
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {

    }
}

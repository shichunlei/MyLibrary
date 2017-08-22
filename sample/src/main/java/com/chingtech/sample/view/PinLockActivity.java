package com.chingtech.sample.view;

import android.util.Log;
import android.view.View;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.AnimatorUitls;
import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;
import com.chingtech.sample.R;
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
 * Created at 17/8/13 上午9:45
 */
@ContentView(R.layout.activity_pin_lock)
public class PinLockActivity extends BaseActivity {

    @ViewInject(R.id.pin_lock_view)
    private PinLockView   mPinLockView;
    @ViewInject(R.id.indicator_dots)
    private IndicatorDots mIndicatorDots;

    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Log.d(getClass().getName(), "Pin complete: " + pin);
            if (pin.equals("1234")) {
                openActivity(MainActivity.class, true);
            } else {
                mPinLockView.resetPinLockView();
                AnimatorUitls.ShakeMode(3, mIndicatorDots);
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
        mPinLockView.attachIndicatorDots(mIndicatorDots);
        mPinLockView.setPinLockListener(mPinLockListener);
        // mPinLockView.setCustomKeySet(new int[] {2, 3, 1, 5, 9, 6, 7, 0, 8, 4}); // 自定义数字键盘数字顺序
        // mPinLockView.enableLayoutShuffling(); // 数字键盘随即打乱
        // mPinLockView.setButtonBackgroundDrawable(getResources().getDrawable(R.drawable.gray_thumb));
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }
}

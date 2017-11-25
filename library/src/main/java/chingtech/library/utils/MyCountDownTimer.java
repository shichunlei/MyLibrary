package chingtech.library.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import static chingtech.library.utils.TimeUtils.formatTimeLength;
import static chingtech.library.utils.TimeUtils.showTimeCount;

public class MyCountDownTimer extends CountDownTimer {

    private TextView textview;

    private String finishText = "重新获取";

    public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView textview) {
        super(millisInFuture, countDownInterval);
        this.textview = textview;
    }

    public MyCountDownTimer(long millisInFuture, long countDownInterval, TextView textview,
            String finishText) {
        super(millisInFuture, countDownInterval);
        this.textview = textview;
        this.finishText = finishText;
    }

    /**
     * @param millisInFuture    表示以毫秒为单位 倒计时的总数
     *                          <p>
     *                          例如 millisInFuture=1000 表示1秒
     * @param countDownInterval 表示 间隔 多少微秒 调用一次 onTick 方法
     *                          <p>
     *                          例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        this.textview.setText(this.finishText);
        this.textview.setEnabled(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        LogUtils.i("TAG", showTimeCount(millisUntilFinished));
        LogUtils.i("TAG", formatTimeLength(millisUntilFinished));
        this.textview.setText(millisUntilFinished / 1000 + "秒后重新获取");
    }
}

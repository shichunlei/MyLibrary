package chingtech.library.widget;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

import chingtech.library.R;
import chingtech.library.utils.StringUtils;

/**
 * MyLibrary
 * Package chingtech.library.widget
 * Description: 数字增加动画的　TextView
 * Created by 师春雷
 * Created at 2017/4/14 17:26
 */
public class NumberAnimTextView extends TextView {

    public static final int DEFAULT_DURATION = 2000; // 动画总时间 默认 2000 毫秒

    private String mNumStart;  // 起始值 默认 0
    private String mNumEnd = ""; // 结束值
    private int mDuration;
    private String mPrefixString = ""; // 前缀
    private String mPostfixString = ""; // 后缀
    private boolean isEnableAnim; // 是否开启动画
    private boolean isInt; // 是否是整数

    public NumberAnimTextView(Context context) {
        super(context);
    }

    public NumberAnimTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumberAnimTextView, 0, 0);

        mNumStart = a.getString(R.styleable.NumberAnimTextView_start_number);
        if (StringUtils.isEmpty(mNumStart)){
            mNumStart = "0";
        }
        mNumEnd = a.getString(R.styleable.NumberAnimTextView_end_number);
        if (StringUtils.isEmpty(mNumEnd)){
            mNumEnd = "0";
        }
        mPrefixString = a.getString(R.styleable.NumberAnimTextView_prefix);
        if (StringUtils.isEmpty(mPrefixString)){
            mPrefixString = "";
        }
        mPostfixString = a.getString(R.styleable.NumberAnimTextView_postfix);
        if (StringUtils.isEmpty(mPostfixString)){
            mPostfixString = "";
        }
        mDuration = a.getInt(R.styleable.NumberAnimTextView_number_duration, DEFAULT_DURATION);
        isEnableAnim = a.getBoolean(R.styleable.NumberAnimTextView_enable_anim, true);

        a.recycle();
    }

    public void startNumber() {
        if (checkNumString(getNumStart(), getNumEnd())) {
            // 数字合法　开始数字动画
            start();
        } else {
            // 数字不合法　直接调用　setText　设置最终值
            setText(mPrefixString + getNumEnd() + mPostfixString);
        }
    }

    public String getNumStart() {
        return mNumStart;
    }

    public void setNumStart(String numStart) {
        mNumStart = numStart;
    }

    public String getNumEnd() {
        return mNumEnd;
    }

    public void setNumEnd(String numEnd) {
        mNumEnd = numEnd;
    }

    public void setEnableAnim(boolean enableAnim) {
        isEnableAnim = enableAnim;
    }

    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public void setPrefixString(String mPrefixString) {
        this.mPrefixString = mPrefixString;
    }

    public void setPostfixString(String mPostfixString) {
        this.mPostfixString = mPostfixString;
    }

    /**
     * 校验数字的合法性
     *
     * @param numberStart 　开始的数字
     * @param numberEnd   　结束的数字
     * @return 合法性
     */
    private boolean checkNumString(String numberStart, String numberEnd) {
        isInt = StringUtils.checkNumInt(numberEnd) && StringUtils.checkNumInt(numberStart);
        if (isInt) {
            BigInteger start = new BigInteger(numberStart);
            BigInteger end = new BigInteger(numberEnd);
            return end.compareTo(start) >= 0;
        }
        String regexDecimal = "-?[1-9]\\d*.\\d*|-?0.\\d*[1-9]\\d*";
        if ("0".equals(numberStart)) {
            if (numberEnd.matches(regexDecimal)) {
                BigDecimal start = new BigDecimal(numberStart);
                BigDecimal end = new BigDecimal(numberEnd);
                return end.compareTo(start) > 0;
            }
        }
        if (numberEnd.matches(regexDecimal) && numberStart.matches(regexDecimal)) {
            BigDecimal start = new BigDecimal(numberStart);
            BigDecimal end = new BigDecimal(numberEnd);
            return end.compareTo(start) > 0;
        }
        return false;
    }

    private void start() {
        if (!isEnableAnim) { // 禁止动画
            setText(mPrefixString + format(new BigDecimal(getNumEnd())) + mPostfixString);
            return;
        }
        ValueAnimator animator = ValueAnimator.ofObject(new BigDecimalEvaluator(), new BigDecimal(getNumStart()), new BigDecimal(getNumEnd()));
        animator.setDuration(mDuration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BigDecimal value = (BigDecimal) valueAnimator.getAnimatedValue();
                setText(mPrefixString + format(value) + mPostfixString);
            }
        });
        animator.start();
    }

    /**
     * 格式化 BigDecimal ,小数部分时保留两位小数并四舍五入
     *
     * @param bd 　BigDecimal
     * @return 格式化后的 String
     */
    private String format(BigDecimal bd) {
        StringBuilder pattern = new StringBuilder();
        if (isInt) {
            pattern.append("#,###");
        } else {
            int length = 0;
            String decimals = getNumEnd().split("\\.")[1];
            if (decimals != null) {
                length = decimals.length();
            }
            pattern.append("#,##0");
            if (length > 0) {
                pattern.append(".");
                for (int i = 0; i < length; i++) {
                    pattern.append("0");
                }
            }
        }
        DecimalFormat df = new DecimalFormat(pattern.toString());
        return df.format(bd);
    }

    // 不加 static 关键字，也不会引起内存泄露，因为这里也没有开启线程
    // 加上 static 关键字，是因为该内部类不需要持有外部类的引用，习惯加上
    private static class BigDecimalEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            BigDecimal start = (BigDecimal) startValue;
            BigDecimal end = (BigDecimal) endValue;
            BigDecimal result = end.subtract(start);
            return result.multiply(new BigDecimal("" + fraction)).add(start);
        }
    }
}

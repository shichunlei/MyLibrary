package chingtech.library.widget.ruler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import chingtech.library.R;
import chingtech.library.utils.LogUtils;
import chingtech.library.utils.StringUtils;

public class DecimalRulerView extends View {

    private int             mMinVelocity;
    private Scroller        mScroller;
    private VelocityTracker mVelocityTracker;
    private int             mWidth;
    private int             mHeight;

    private boolean showResult;// 是否显示结果值
    private boolean showUnit;// 是否显示单位
    private boolean mAlphaEnable;

    private static final float DEFAULT_VALUE      = 50f;  // 默认指示刻度
    private static final float MAX_VALUE          = 100f; // 最大值
    private static final float MIN_VALUE          = 0f;   // 最小值
    private static final float SPAN_VALUE         = 0.1f;
    private static final int   ITEM_MAX_HEIGHT    = 36;  //最大刻度高度
    private static final int   ITEM_MIN_HEIGHT    = 20;  //最小刻度高度
    private static final int   ITEM_MIDDLE_HEIGHT = 28;  //中间刻度高度
    private static final int   INDICATOR_WIDTH    = 6;

    private static final int LINE     = 1;
    private static final int TRIANGLE = 2;

    private int mIndicatorType;

    private int resultHeight = 0;

    private int defaultItemSpacing = 6;
    private int textMarginTop      = 8;
    private int scaleTextSize      = 15;

    private float mValue;
    private float mMaxValue;
    private float mMinValue;

    private int   mItemSpacing;
    private float mPerSpanValue;
    private int   mMaxLineHeight;
    private int   mMiddleLineHeight;
    private int   mMinLineHeight;

    private int mMinLineWidth    = 1;
    private int mMaxLineWidth    = 2;
    private int mMiddleLineWidth = 1;

    private int   mTextMarginTop;
    private float mScaleTextHeight;
    private float mResultTextHeight;
    private float mUnitTextHeight = 0;

    private int mIndcatorColor = 0xff50b586;
    private int mIndcatorWidth;
    private int mScaleTextColor = 0X80222222;
    private int mScaleTextSize;
    private int mResultTextColor = 0xff50b586;
    private int mResultTextSize  = 6;
    private int mUnitTextColor   = 0Xff666666;
    private int mUnitTextSize    = 4;
    private String unit;

    private int mMinLineColor    = 0X80222222;
    private int mMaxLineColor    = 0X80222222;
    private int mMiddleLineColor = 0X80222222;

    private Paint mScaleTextPaint; // 绘制刻度文本的画笔
    private Paint mLinePaint; // 绘制刻度的画笔
    private Paint mIndicatorPaint; // 绘制指示器的画笔
    private Paint mResultTextPaint; // 绘制结果的画笔
    private Paint mUnitTextPaint; // 绘制单位的画笔

    private int   mTotalLine;
    private int   mMaxOffset;
    private float mOffset; // 默认尺起始点在屏幕中心, offset是指尺起始点的偏移值
    private int   mLastX, mMove;
    private OnChooseResulterListener mListener;

    public DecimalRulerView(Context context) {
        this(context, null);
    }

    public DecimalRulerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        setAttr(context, attrs, 0);
    }

    public DecimalRulerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttr(context, attrs, defStyleAttr);
    }

    private void setAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = getContext().getTheme()
                                   .obtainStyledAttributes(attrs, R.styleable.DecimalRulerView,
                                                           defStyleAttr, 0);

        mValue = a.getFloat(R.styleable.DecimalRulerView_rv_defaultValue, DEFAULT_VALUE);
        mMaxValue = a.getFloat(R.styleable.DecimalRulerView_rv_maxValue, MAX_VALUE);
        mMinValue = a.getFloat(R.styleable.DecimalRulerView_rv_minValue, MIN_VALUE);
        float precision = a.getFloat(R.styleable.DecimalRulerView_rv_spanValue, SPAN_VALUE);
        mPerSpanValue = precision * 10;

        mItemSpacing = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_itemSpacing,
                                               (int) TypedValue.applyDimension(
                                                       TypedValue.COMPLEX_UNIT_DIP,
                                                       defaultItemSpacing,
                                                       getResources().getDisplayMetrics()));
        mMaxLineHeight = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_maxLineHeight,
                                                 (int) TypedValue.applyDimension(
                                                         TypedValue.COMPLEX_UNIT_DIP,
                                                         ITEM_MAX_HEIGHT,
                                                         getResources().getDisplayMetrics()));
        mMinLineHeight = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_minLineHeight,
                                                 (int) TypedValue.applyDimension(
                                                         TypedValue.COMPLEX_UNIT_DIP,
                                                         ITEM_MIN_HEIGHT,
                                                         getResources().getDisplayMetrics()));
        mMiddleLineHeight = a.getDimensionPixelSize(
                R.styleable.DecimalRulerView_rv_middleLineHeight,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, ITEM_MIDDLE_HEIGHT,
                                                getResources().getDisplayMetrics()));

        mMinLineWidth = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_minLineWidth,
                                                (int) TypedValue.applyDimension(
                                                        TypedValue.COMPLEX_UNIT_DIP, mMinLineWidth,
                                                        getResources().getDisplayMetrics()));
        mMiddleLineWidth = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_middleLineWidth,
                                                   (int) TypedValue.applyDimension(
                                                           TypedValue.COMPLEX_UNIT_DIP,
                                                           mMiddleLineWidth,
                                                           getResources().getDisplayMetrics()));
        mMaxLineWidth = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_maxLineWidth,
                                                (int) TypedValue.applyDimension(
                                                        TypedValue.COMPLEX_UNIT_DIP, mMaxLineWidth,
                                                        getResources().getDisplayMetrics()));
        mMaxLineColor = a.getColor(R.styleable.DecimalRulerView_rv_maxLineColor, mMaxLineColor);
        mMiddleLineColor = a.getColor(R.styleable.DecimalRulerView_rv_middleLineColor,
                                      mMiddleLineColor);
        mMinLineColor = a.getColor(R.styleable.DecimalRulerView_rv_minLineColor, mMinLineColor);
        mIndcatorColor = a.getColor(R.styleable.DecimalRulerView_rv_indcatorColor, mIndcatorColor);
        mIndcatorWidth = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_indcatorWidth,
                                                 (int) TypedValue.applyDimension(
                                                         TypedValue.COMPLEX_UNIT_DIP,
                                                         INDICATOR_WIDTH,
                                                         getResources().getDisplayMetrics()));
        mIndicatorType = a.getInt(R.styleable.DecimalRulerView_rv_indcatorType, LINE);
        mScaleTextColor = a.getColor(R.styleable.DecimalRulerView_rv_scaleTextColor,
                                     mScaleTextColor);
        mScaleTextSize = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_scaleTextSize,
                                                 (int) TypedValue.applyDimension(
                                                         TypedValue.COMPLEX_UNIT_SP, scaleTextSize,
                                                         getResources().getDisplayMetrics()));
        mTextMarginTop = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_textMarginTop,
                                                 (int) TypedValue.applyDimension(
                                                         TypedValue.COMPLEX_UNIT_DIP, textMarginTop,
                                                         getResources().getDisplayMetrics()));

        mResultTextColor = a.getColor(R.styleable.DecimalRulerView_rv_resultTextColor,
                                      mResultTextColor);
        mResultTextSize = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_resultTextSize,
                                                  (int) TypedValue.applyDimension(
                                                          TypedValue.COMPLEX_UNIT_SP,
                                                          mResultTextSize,
                                                          getResources().getDisplayMetrics()));
        mUnitTextColor = a.getColor(R.styleable.DecimalRulerView_rv_unitTextColor, mUnitTextColor);
        mUnitTextSize = a.getDimensionPixelSize(R.styleable.DecimalRulerView_rv_unitTextSize,
                                                (int) TypedValue.applyDimension(
                                                        TypedValue.COMPLEX_UNIT_SP,
                                                        mUnitTextSize,
                                                        getResources().getDisplayMetrics()));

        showUnit = a.getBoolean(R.styleable.DecimalRulerView_rv_showUnit, true);
        unit = a.getString(R.styleable.DecimalRulerView_rv_unit);
        if (StringUtils.isEmpty(unit)) {
            unit = "kg";
        }

        showResult = a.getBoolean(R.styleable.DecimalRulerView_rv_showResult, true);
        mAlphaEnable = a.getBoolean(R.styleable.DecimalRulerView_rv_alphaEnable, true);

        init(context);
        a.recycle();
    }

    protected void init(Context context) {
        mScroller = new Scroller(context);
        mMinVelocity = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();

        mScaleTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleTextPaint.setTextSize(mScaleTextSize);
        mScaleTextPaint.setColor(mScaleTextColor);
        mScaleTextPaint.setAntiAlias(true);
        mScaleTextHeight = getFontHeight(mScaleTextPaint);

        mResultTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mResultTextPaint.setTextSize(mResultTextSize);
        mResultTextPaint.setColor(mResultTextColor);
        mResultTextPaint.setAntiAlias(true);
        mResultTextPaint.setAlpha(255);
        mResultTextHeight = getFontHeight(mResultTextPaint);
        LogUtils.i("TAG", "结果字的高度: " + mResultTextHeight);

        if (showUnit) {
            mUnitTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mUnitTextPaint.setTextSize(mUnitTextSize);
            mUnitTextPaint.setColor(mUnitTextColor);
            mUnitTextPaint.setAntiAlias(true);
            mUnitTextPaint.setAlpha(232);
            mUnitTextHeight = getFontHeight(mUnitTextPaint);
            LogUtils.i("TAG", "单位字的高度: " + mUnitTextHeight);
        }

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND); // 线帽，即画的线条两端是否带有圆角，ROUND，圆角;BUTT，无圆角;SQUARE，矩形
        mLinePaint.setAntiAlias(true);

        mIndicatorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mIndicatorPaint.setStrokeWidth(mIndcatorWidth);
        mIndicatorPaint.setAntiAlias(true);
        mIndicatorPaint.setColor(mIndcatorColor);
        mIndicatorPaint.setStrokeCap(Paint.Cap.ROUND);

        initViewParam(mValue, mMinValue, mMaxValue, mPerSpanValue);
    }

    public void initViewParam(float defaultValue, float minValue, float maxValue,
            float perSpanValue) {
        this.mValue = defaultValue;
        this.mMaxValue = maxValue;
        this.mMinValue = minValue;
        this.mPerSpanValue = (int) (perSpanValue * 10.0f);

        this.mTotalLine = (int) ((int) (mMaxValue * 10 - mMinValue * 10) / mPerSpanValue + 1);
        mMaxOffset = -(mTotalLine - 1) * mItemSpacing;

        mOffset = (mMinValue - mValue) / mPerSpanValue * mItemSpacing * 10;
        invalidate();
        setVisibility(VISIBLE);
    }

    /**
     * @return 返回指定笔的文字高度
     */
    public static float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    /**
     * 设置用于接收结果的监听器
     *
     * @param listener
     */
    public void setChooseValueChangeListener(OnChooseResulterListener listener) {
        mListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            mWidth = w;
            mHeight = h;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        resultHeight = showResult ?
                (int) (mResultTextHeight > mUnitTextHeight ? mResultTextHeight : mUnitTextHeight)
                        + 20 : mIndcatorWidth / 2;
        drawScaleAndNum(canvas);
        drawIndicator(canvas);
        if (showResult) {
            drawResult(canvas, mValue + "");
        }
    }

    /**
     * 画指示器
     *
     * @param canvas
     */
    private void drawIndicator(Canvas canvas) {
        int srcPointX = mWidth / 2; // 默认表尺起始点在屏幕中心
        // 画中间的指示器
        if (mIndicatorType == LINE) {
            canvas.drawLine(srcPointX, resultHeight, srcPointX,
                            (mHeight - resultHeight) / 2 + resultHeight, mIndicatorPaint);
        } else if (mIndicatorType == TRIANGLE) {
            /*画一个实心三角形*/
            Path path = new Path();
            path.moveTo(srcPointX - mItemSpacing, resultHeight);
            path.lineTo(srcPointX + mItemSpacing, resultHeight);
            path.lineTo(srcPointX, mMinLineHeight + resultHeight);
            path.close();
            canvas.drawPath(path, mIndicatorPaint);
        }
    }

    /**
     * 画刻度以及刻度数值
     *
     * @param canvas
     */
    private void drawScaleAndNum(Canvas canvas) {
        float  left, height;
        String value;
        int    alpha;
        float  scale;
        int    srcPointX = mWidth / 2; // 默认表尺起始点在屏幕中心

        for (int i = 0; i < mTotalLine; i++) {
            left = srcPointX + mOffset + i * mItemSpacing;

            if (left < 0 || left > mWidth) {
                continue;
            }

            // 画刻度线
            if (i % 10 == 0) {
                height = mMaxLineHeight;
                mLinePaint.setColor(mMaxLineColor);
                mLinePaint.setStrokeWidth(mMaxLineWidth);
            } else if (i % 5 == 0) {
                height = mMiddleLineHeight;
                mLinePaint.setColor(mMiddleLineColor);
                mLinePaint.setStrokeWidth(mMiddleLineWidth);
            } else {
                height = mMinLineHeight;
                mLinePaint.setColor(mMinLineColor);
                mLinePaint.setStrokeWidth(mMinLineWidth);
            }

            if (mAlphaEnable) {
                LogUtils.i("TAG", "drawScaleAndNum: ================");
                scale = 1 - Math.abs(left - srcPointX) / srcPointX;
                Log.i("TAG", "drawScaleAndNum: scale====" + scale);
                alpha = (int) (255 * scale * scale);
                Log.i("TAG", "drawScaleAndNum: alpha===" + alpha);
                mLinePaint.setAlpha(alpha);
                mScaleTextPaint.setAlpha(alpha);
            }

            canvas.drawLine(left, resultHeight, left, height + resultHeight, mLinePaint);

            if (i % 10 == 0) { // 大指标, 要标注文字
                value = String.valueOf((int) (mMinValue + i * mPerSpanValue / 10));
                canvas.drawText(value, left - mScaleTextPaint.measureText(value) / 2,
                                resultHeight + height + mTextMarginTop + mScaleTextHeight,
                                mScaleTextPaint);
            }
        }
    }

    /**
     * 画结果和单位
     *
     * @param canvas
     * @param resultText
     */
    private void drawResult(Canvas canvas, String resultText) {
        int srcPointX = mWidth / 2; // 默认表尺起始点在屏幕中心
        LogUtils.i("TAG", "当前刻度指示值: " + resultText);
        // 画结果
        canvas.drawText(resultText, srcPointX - mResultTextPaint.measureText(resultText) / 2,
                        mResultTextHeight > mUnitTextHeight ? mResultTextHeight : mUnitTextHeight,
                        mResultTextPaint);

        LogUtils.i("TAG", "结果文字宽度: " + mResultTextPaint.measureText(resultText));

        if (showUnit) {
            // 画单位
            canvas.drawText(unit, srcPointX + mResultTextPaint.measureText(resultText) / 2 + 10,
                            mResultTextHeight > mUnitTextHeight ? mResultTextHeight :
                                    mUnitTextHeight, mUnitTextPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action    = event.getAction();
        int xPosition = (int) event.getX();

        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mScroller.forceFinished(true);
                mLastX = xPosition;
                mMove = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                mMove = (mLastX - xPosition);
                changeMoveAndValue();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                countMoveEnd();
                countVelocityTracker();
                return false;
            // break;
            default:
                break;
        }

        mLastX = xPosition;
        return true;
    }

    private void countVelocityTracker() {
        mVelocityTracker.computeCurrentVelocity(1000);
        float xVelocity = mVelocityTracker.getXVelocity();
        if (Math.abs(xVelocity) > mMinVelocity) {
            mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
        }
    }

    private void countMoveEnd() {
        mOffset -= mMove;
        if (mOffset <= mMaxOffset) {
            mOffset = mMaxOffset;
        } else if (mOffset >= 0) {
            mOffset = 0;
        }

        mLastX = 0;
        mMove = 0;

        mValue = mMinValue
                + Math.round(Math.abs(mOffset) * 1.0f / mItemSpacing) * mPerSpanValue / 10.0f;
        mOffset = (mMinValue - mValue) * 10.0f / mPerSpanValue
                * mItemSpacing; // 矫正位置,保证不会停留在两个相邻刻度之间
        notifyValueChange();
        postInvalidate();
    }

    private void changeMoveAndValue() {
        mOffset -= mMove;
        if (mOffset <= mMaxOffset) {
            mOffset = mMaxOffset;
            mMove = 0;
            mScroller.forceFinished(true);
        } else if (mOffset >= 0) {
            mOffset = 0;
            mMove = 0;
            mScroller.forceFinished(true);
        }
        mValue = mMinValue
                + Math.round(Math.abs(mOffset) * 1.0f / mItemSpacing) * mPerSpanValue / 10.0f;
        notifyValueChange();
        postInvalidate();
    }

    private void notifyValueChange() {
        if (null != mListener) {
            mListener.onChooseValueChange(mValue);
        }
    }

    public interface OnChooseResulterListener {
        void onChooseValueChange(float value);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            if (mScroller.getCurrX() == mScroller.getFinalX()) { // over
                countMoveEnd();
            } else {
                int xPosition = mScroller.getCurrX();
                mMove = (mLastX - xPosition);
                changeMoveAndValue();
                mLastX = xPosition;
            }
        }
    }
}

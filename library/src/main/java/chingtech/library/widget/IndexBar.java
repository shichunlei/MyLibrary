package chingtech.library.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class IndexBar extends View {

    private Paint               mPaint;
    private int                 textSpan;//每个index占据空间
    private IndexChangeListener listener;
    private List<String>        indexsList;
    private int textSize     = 40;
    private int selTextColor = Color.BLACK;
    private int norTextColor = Color.GRAY;
    private float yAxis;//文字y轴方向的基线

    public IndexBar(Context context) {
        this(context, null);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(norTextColor);
        mPaint.setTextSize(textSize);
        mPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float             total       = -fontMetrics.ascent + fontMetrics.descent;
        yAxis = total / 2 - fontMetrics.descent;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        textSpan = h / (indexsList.size() + 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < indexsList.size(); i++) {
            canvas.drawText(indexsList.get(i), getWidth() / 2, textSpan * (i + 1) + yAxis, mPaint);
        }
    }

    private int curPos = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPaint.setColor(selTextColor);
                invalidate();
            case MotionEvent.ACTION_MOVE:
                if (event.getY() < textSpan / 2
                        || (event.getY() - textSpan / 2) > textSpan * indexsList.size()) {
                    return true;
                }

                int position = (int) ((event.getY() - textSpan / 2) / textSpan * 1.0f);
                if (position >= 0 && position < indexsList.size()) {
                    ((IndexLayout) getParent()).drawCircle(event.getY(), indexsList.get(position));
                    if (listener != null && curPos != position) {
                        curPos = position;
                        listener.indexChanged(indexsList.get(position));
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                ((IndexLayout) getParent()).dismissCircle();
                mPaint.setColor(norTextColor);
                invalidate();
                break;
        }
        return true;
    }

    public interface IndexChangeListener {
        void indexChanged(String indexName);
    }

    public void setIndexsList(List<String> indexs) {
        this.indexsList = indexs;
    }

    public void setIndexChangeListener(IndexChangeListener listener) {
        this.listener = listener;
    }

    public void setIndexTextSize(int textSize) {
        this.textSize = textSize;
        mPaint.setTextSize(textSize);
    }

    public void setSelTextColor(int selTextColor) {
        this.selTextColor = selTextColor;
    }

    public void setNorTextColor(int norTextColor) {
        this.norTextColor = norTextColor;
        mPaint.setColor(norTextColor);
    }
}

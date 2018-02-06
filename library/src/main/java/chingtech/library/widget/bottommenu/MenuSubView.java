package chingtech.library.widget.bottommenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 弹出菜单SubView 上部图片 底部文字
 * Created by Joe on 16/12/10
 */
public class MenuSubView extends LinearLayout {

    private static final float factor = 1.2f;

    private ImageView icon;

    public ImageView getIcon() {
        return icon;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    private TextView textView;

    public MenuSubView(Context context) {
        this(context, null);
    }

    public MenuSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        icon = new ImageView(context);
        icon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        addView(icon, new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                       ViewGroup.LayoutParams.WRAP_CONTENT));

        textView = new TextView(context);
        LayoutParams tvLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                             ViewGroup.LayoutParams.WRAP_CONTENT);
        tvLp.topMargin = 10;
        addView(textView, tvLp);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        scaleViewAnimation(MenuSubView.this, factor);
                        break;
                    case MotionEvent.ACTION_UP:
                        scaleViewAnimation(MenuSubView.this, 1);
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 赋值
     *
     * @param bottomMenuItem
     */
    public void setPopMenuItem(BottomMenuItem bottomMenuItem) {
        if (bottomMenuItem == null) {
            return;
        }
        icon.setImageDrawable(bottomMenuItem.getDrawable());
        textView.setText(bottomMenuItem.getTitle());
    }

    /**
     * 缩放动画
     *
     * @param value
     */
    private void scaleViewAnimation(View view, float value) {
        view.animate().scaleX(value).scaleY(value).setDuration(80).start();
    }
}

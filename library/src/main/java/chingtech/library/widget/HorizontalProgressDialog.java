package chingtech.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.NumberFormat;

import chingtech.library.R;
import chingtech.library.utils.StringUtils;

/**
 * MyLibrary
 * Package chingtech.library.widget
 * Description: 横向的加载对话框
 * Created by 师春雷
 * Created at 2017/4/17 15:54
 */
public class HorizontalProgressDialog {

    private final static long N = 1024 * 1024;

    private Context context;

    private LinearLayout mLayout;
    private ProgressBar mTvProgress;
    private TextView mTvNumber;
    private TextView mTvPercent;
    private TextView mTvMessage;

    private Handler handler;
    private int mMax;
    private CharSequence mMessage;
    private int mProgress;

    private NumberFormat mProgressFormat;

    private Display display;

    private Dialog dialog;

    public HorizontalProgressDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();

        init();
    }

    private void init() {
        mProgressFormat = NumberFormat.getPercentInstance();
        mProgressFormat.setMaximumFractionDigits(0);
    }

    @SuppressWarnings("deprecation")
    public HorizontalProgressDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_horizontal_progress_dialog, null);

        mLayout = (LinearLayout) view.findViewById(R.id.layout_progress);
        mTvProgress = (ProgressBar) view.findViewById(R.id.progress);
        mTvNumber = (TextView) view.findViewById(R.id.progress_number);
        mTvPercent = (TextView) view.findViewById(R.id.progress_percent);
        mTvMessage = (TextView) view.findViewById(R.id.progress_message);

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                double progress = ((double) mTvProgress.getProgress() / N);
                double max = ((double) mTvProgress.getMax() / N);

                String value = String.format(context.getString(R.string.download_progress_bar),
                        StringUtils.doubleToString(progress), StringUtils.doubleToString(max));
                mTvNumber.setText(value);
                if (mProgressFormat != null) {
                    double percent = progress / max;
                    SpannableString tmp = new SpannableString(mProgressFormat.format(percent));
                    tmp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, tmp.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mTvPercent.setText(tmp);
                } else {
                    mTvPercent.setText("");
                }
            }
        };

        onProgressChanged();
        if (mMessage != null) {
            setMessage(mMessage);
        }
        if (mMax > 0) {
            setMax(mMax);
        }
        if (mProgress > 0) {
            setProgress(mProgress);
        }

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        mLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85),
                LayoutParams.WRAP_CONTENT));

        return this;
    }

    private void onProgressChanged() {
        handler.sendEmptyMessage(0);
    }

    public int getMax() {
        if (mTvProgress != null) {
            return mTvProgress.getMax();
        }
        return mMax;
    }

    public void setMax(int max) {
        if (mTvProgress != null) {
            mTvProgress.setMax(max);
            onProgressChanged();
        } else {
            mMax = max;
        }
    }

    public void setIndeterminate(boolean indeterminate) {
        if (mTvProgress != null) {
            mTvProgress.setIndeterminate(indeterminate);
        }
    }

    public void setProgress(int value) {
        this.mProgress = value;
        mTvProgress.setProgress(mProgress);
        onProgressChanged();
    }

    public void setMessage(CharSequence message) {
        if (mTvMessage != null) {
            mTvMessage.setText(message);
        } else {
            mMessage = message;
        }
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void show() {
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键才起作用
        dialog.setCanceledOnTouchOutside(false);
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
//        dialog.setCancelable(false);
        dialog.show();
    }
}

package chingtech.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import chingtech.library.R;

/**
 * MyLibrary
 * Package chingtech.library.widget
 * Description:
 * Created by 师春雷
 * Created at 17/7/9 下午8:09
 */
public class UpdateAppDialog {

    private Context context;
    private Dialog  dialog;

    private LinearLayout layoutBg;

    private TextView          mContentTextView;
    private Button            mUpdateOkButton;
    private NumberProgressBar mNumberProgressBar;
    private ImageView         mIvClose;
    private TextView          mTitleTextView;
    private LinearLayout      mLlClose;
    private ImageView         mTopIv;

    private int mMax      = 100;
    private int mProgress = 0;

    private Display display;

    public UpdateAppDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    @SuppressWarnings("deprecation")
    public UpdateAppDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_app_update_dialog, null);

        // 获取自定义Dialog布局中的控件
        layoutBg = view.findViewById(R.id.layout_bg);
        //提示内容
        mContentTextView = view.findViewById(R.id.tv_update_info);
        //标题
        mTitleTextView = view.findViewById(R.id.tv_title);
        //更新按钮
        mUpdateOkButton = view.findViewById(R.id.btn_ok);
        //进度条
        mNumberProgressBar = view.findViewById(R.id.npb);
        //关闭按钮
        mIvClose = view.findViewById(R.id.iv_close);
        //关闭按钮+线 的整个布局
        mLlClose = view.findViewById(R.id.ll_close);
        //顶部图片
        mTopIv = view.findViewById(R.id.iv_top);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        layoutBg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85),
                                                              LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public UpdateAppDialog setTitle(String title) {
        mTitleTextView.setText(title);
        return this;
    }

    public UpdateAppDialog setMsg(String message) {
        mContentTextView.setText(message);
        return this;
    }

    public UpdateAppDialog setColse(final View.OnClickListener listener) {
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    public UpdateAppDialog setDownLoad(final View.OnClickListener listener) {
        mUpdateOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onClick(v);
                }
                mUpdateOkButton.setVisibility(View.GONE);
                mNumberProgressBar.setVisibility(View.VISIBLE);
            }
        });
        return this;
    }

    public UpdateAppDialog show() {
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
        dialog.setCanceledOnTouchOutside(false);
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
        // dialog.setCancelable(false);
        dialog.show();
        return null;
    }

    public UpdateAppDialog setTopImage(int res) {
        mTopIv.setImageResource(res);
        return this;
    }

    public int getmMax() {
        return mMax;
    }

    public void setmMax(int mMax) {
        this.mMax = mMax;
        Log.i("Tag", "setmMax: " + mMax);
        mNumberProgressBar.setMax(this.mMax);
    }

    public int getmProgress() {
        return mProgress;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
        Log.i("Tag", "setmProgress: " + mProgress);
        mNumberProgressBar.setProgress(this.mProgress);
    }

    public void setReachedBarColor(int color) {
        mNumberProgressBar.setReachedBarColor(color);
    }

    public void setProgressTextColor(int color) {
        mNumberProgressBar.setProgressTextColor(color);
    }

    public void setUnreachedBarColor(int color) {
        mNumberProgressBar.setUnreachedBarColor(color);
    }

    public void dismiss() {
        dialog.dismiss();
    }
}

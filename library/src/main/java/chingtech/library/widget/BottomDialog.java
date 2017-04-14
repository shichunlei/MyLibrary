package chingtech.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chingtech.library.R;
import chingtech.library.bean.SheetItem;
import chingtech.library.interfaces.OnSheetItemClickListener;
import chingtech.library.utils.ENUM;

public class BottomDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout layoutTitle;
    private TextView txtTitle;
    private TextView txt_cancel;
    private View view_line;
    private RelativeLayout layoutTop;
    private Button btnOk;
    private Button btnCancel;
    private LinearLayout viewContent;
    private LinearLayout layoutContent;
    private ScrollView scrollContent;
    private List<SheetItem> sheetItemList;
    private Display display;
    private boolean showSheet = false;

    public BottomDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    @SuppressWarnings("deprecation")
    public BottomDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_bottom_dialog, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        // 获取自定义Dialog布局中的控件
        scrollContent = (ScrollView) view.findViewById(R.id.scroll_content);
        scrollContent.setVisibility(View.GONE);
        layoutContent = (LinearLayout) view.findViewById(R.id.layout_content);
        viewContent = (LinearLayout) view.findViewById(R.id.view_content);
        viewContent.setVisibility(View.GONE);

        layoutTitle = (LinearLayout) view.findViewById(R.id.layout_title);
        layoutTitle.setVisibility(View.GONE);
        txtTitle = (TextView) view.findViewById(R.id.txt_title);

        txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
        txt_cancel.setVisibility(View.GONE);
        view_line = view.findViewById(R.id.view_line);
        view_line.setVisibility(View.GONE);

        layoutTop = (RelativeLayout) view.findViewById(R.id.layout_top);
        layoutTop.setVisibility(View.GONE);
        btnOk = (Button) view.findViewById(R.id.btn_ok);
        btnOk.setVisibility(View.INVISIBLE);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnOk.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.BottomDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    public BottomDialog setTitle(String title) {
        setTitle(title, 0);
        return this;
    }

    public BottomDialog setTitle(String title, int gravity) {
        setTitle(title, gravity, 0);
        return this;
    }

    public BottomDialog setTitle(String title, int gravity, int color) {
        if (gravity != 0) {
            txtTitle.setGravity(gravity | Gravity.CENTER_VERTICAL);
        }
        if (color != 0) {
            txtTitle.setTextColor(ContextCompat.getColor(context, color));
        }
        layoutTitle.setVisibility(View.VISIBLE);
        txtTitle.setText(title);
        return this;
    }

    public BottomDialog setCancel(String cancel, final View.OnClickListener listener) {
        view_line.setVisibility(View.VISIBLE);
        txt_cancel.setVisibility(View.VISIBLE);
        txt_cancel.setText(cancel);

        txt_cancel.setOnClickListener(new View.OnClickListener() {
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

    public BottomDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public BottomDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public BottomDialog setView(View view) {
        viewContent.setVisibility(View.VISIBLE);
        viewContent.addView(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        return this;
    }

    public BottomDialog setPositiveButton(String text, final View.OnClickListener listener) {
        layoutTop.setVisibility(View.VISIBLE);
        btnOk.setVisibility(View.VISIBLE);
        btnOk.setText(text);
        btnOk.setOnClickListener(new View.OnClickListener() {
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

    public BottomDialog setNegativeButton(String text, final View.OnClickListener listener) {
        layoutTop.setVisibility(View.VISIBLE);
        btnCancel.setVisibility(View.VISIBLE);
        btnCancel.setText(text);
        btnCancel.setOnClickListener(new View.OnClickListener() {
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

    /**
     *
     * @param strItem
     *            条目名称
     * @param color
     *            条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public BottomDialog addSheetItem(String strItem, ENUM.SheetItemColor color,
            OnSheetItemClickListener listener) {
        showSheet = true;
        scrollContent.setVisibility(View.VISIBLE);
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }

    /** 设置条目布局 */
    @SuppressWarnings("deprecation")
    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }

        int size = sheetItemList.size();

        // 高度控制，非最佳解决办法
        // 添加条目过多的时候控制高度
        if (size >= 7) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) scrollContent.getLayoutParams();
            params.height = display.getHeight() / 2;
            scrollContent.setLayoutParams(params);
        }

        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.getName();
            ENUM.SheetItemColor color = sheetItem.getColor();
            final OnSheetItemClickListener listener = sheetItem.getItemClickListener();

            TextView textView = new TextView(context);
            textView.setBackgroundResource(R.drawable.recycler_bg);
            textView.setText(strItem);
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER);

            // 字体颜色
            if (color == null) {
                textView.setTextColor(Color.parseColor(ENUM.SheetItemColor.Blue.getName()));
            } else {
                textView.setTextColor(Color.parseColor(color.getName()));
            }

            // 高度
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height));

            // 点击事件
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    dialog.dismiss();
                }
            });

            layoutContent.addView(textView);
        }
    }

    public void show() {
        if (showSheet)
            setSheetItems();
        dialog.show();
    }
}

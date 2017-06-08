package chingtech.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chingtech.library.R;
import chingtech.library.adapter.ItemAdapter;
import chingtech.library.bean.ItemBean;
import chingtech.library.bean.SheetItem;
import chingtech.library.interfaces.OnSheetItemClickListener;
import chingtech.library.utils.StringUtils;

/**
 * MyLibrary
 * Package chingtech.library.widget
 * Description: 自定义AlertDialog
 * Created by 师春雷
 * Created at 2017/3/26
 */
public class AlertDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    /** 标头 */
    private TextView txt_title;
    /** 提示信息 */
    private TextView txt_msg;
    private LinearLayout dialog_Group;
    private LinearLayout layoutList;
    private RelativeLayout layoutTitle;
    private RelativeLayout layoutBottom;
    /** 确定按钮 */
    private Button btn_neg;
    /** 取消按钮 */
    private Button btn_pos;
    /** 取消按钮 */
    private Button btn_cancel;
    private ImageView close;
    private Display display;
    private RecyclerView recyclerView;

    private List<SheetItem> sheetItemList;

    private ItemAdapter adapter;

    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showLayout = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private boolean showList = false;
    private boolean showListView = false;
    private boolean showClose = false;
    private boolean showCancel = false;

    public AlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    @SuppressWarnings("deprecation")
    public AlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_alert_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        dialog_Group = (LinearLayout) view.findViewById(R.id.dialog_Group);
        dialog_Group.setVisibility(View.GONE);
        layoutList = (LinearLayout) view.findViewById(R.id.layout_list);
        layoutList.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_cancel.setVisibility(View.GONE);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setVisibility(View.GONE);
        layoutTitle = (RelativeLayout) view.findViewById(R.id.layout_title);
        close = (ImageView) view.findViewById(R.id.image_close);
        close.setVisibility(View.GONE);
        layoutBottom = (RelativeLayout) view.findViewById(R.id.layout_bottom);
        layoutBottom.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    public AlertDialog setBackgroundColor(int color) {
        layoutTitle.setBackgroundColor(ContextCompat.getColor(context, color));
        return this;
    }

    public AlertDialog setBackgroundResource(int resId) {
        layoutTitle.setBackgroundResource(resId);
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public AlertDialog setBackgroundDrawable(int background) {
        layoutTitle.setBackground(ContextCompat.getDrawable(context, background));
        return this;
    }

    public AlertDialog setTitle(int titleId) {
        setTitle(titleId, null, -1, -1);
        return this;
    }

    public AlertDialog setTitle(String title) {
        setTitle(-1, title, -1, -1);
        return this;
    }

    public AlertDialog setTitle(int titleId, int gravity) {
        setTitle(titleId, null, gravity, -1);
        return this;
    }

    public AlertDialog setTitle(String title, int gravity) {
        setTitle(-1, title, gravity, -1);
        return this;
    }

    public AlertDialog setTitle(String title, int gravity, int textcolor) {
        setTitle(-1, title, gravity, textcolor);
        return this;
    }

    public AlertDialog setTitle(int titleId, String title, int gravity, int textcolor) {
        showTitle = true;
        if (gravity != -1) {
            txt_title.setGravity(gravity | Gravity.CENTER_VERTICAL);
        }
        if (textcolor != -1) {
            txt_title.setTextColor(ContextCompat.getColor(context, textcolor));
        }
        if (titleId != -1) {
            txt_title.setText(context.getString(titleId));
        } else if (StringUtils.isNotEmpty(title)) {
            txt_title.setText(title);
        } else {
            txt_title.setText("Title");
        }
        return this;
    }

    public AlertDialog setMsg(int msgId) {
        setMsg(msgId, null);
        return this;
    }

    public AlertDialog setMsg(String msg) {
        setMsg(0, msg);
        return this;
    }

    public AlertDialog setMsg(int msgId, String msg) {
        if (msgId != 0) {
            showMsg = true;
            txt_msg.setText(context.getString(msgId));
        } else if (StringUtils.isNotEmpty(msg)) {
            showMsg = true;
            txt_msg.setText(msg);
        } else {
            showMsg = false;
        }
        return this;
    }

    public AlertDialog setSingleChoiceItems(String[] array) {
        setSingleChoiceItems(array, null);
        return this;
    }

    public AlertDialog setSingleChoiceItems(List<ItemBean> status) {
        setSingleChoiceItems(null, status);
        return this;
    }

    public AlertDialog setSingleChoiceItems(String[] array, List<ItemBean> status) {
        if (null == status) {
            status = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                status.add(new ItemBean(array[i], i == 0 ? true : false));
            }
        }
        showList = true;
        adapter = new ItemAdapter(status, context, recyclerView);
        recyclerView.setAdapter(adapter);
        return this;
    }

    public int getSingleChoiceItems() {
        return adapter.getSelectedPos();
    }

    public AlertDialog setView(View view) {
        showLayout = true;
        if (view == null) {
            showLayout = false;
        } else {
            dialog_Group.addView(view, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
        }
        return this;
    }

    public AlertDialog setColseImage(int image) {
        close.setImageDrawable(ContextCompat.getDrawable(context, image));
        return this;
    }

    public AlertDialog setColse(final OnClickListener listener) {
        showClose = true;

        close.setOnClickListener(new OnClickListener() {
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

    public AlertDialog setCancel(String text, final OnClickListener listener) {
        showCancel = true;
        if (StringUtils.isNotEmpty(text)) {
            btn_cancel.setText(text);
        } else {
            btn_cancel.setText("取消");
        }
        btn_cancel.setOnClickListener(new OnClickListener() {
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

    public AlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public AlertDialog setPositiveButton(int textId, OnClickListener listener) {
        setPositiveButton(null, textId, listener);
        return this;
    }

    public AlertDialog setPositiveButton(String text, OnClickListener listener) {
        setPositiveButton(text, 0, listener);
        return this;
    }

    public AlertDialog setPositiveButton(String text, int textId,
            final OnClickListener listener) {
        showPosBtn = true;
        if (textId != 0) {
            btn_pos.setText(context.getString(textId));
        } else if (StringUtils.isNotEmpty(text)) {
            btn_pos.setText(text);
        } else {
            btn_pos.setText("确定");
        }
        btn_pos.setOnClickListener(new OnClickListener() {
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

    public AlertDialog setNegativeButton(int textId, OnClickListener listener) {
        setNegativeButton(textId, null, listener);
        return this;
    }

    public AlertDialog setNegativeButton(String text, OnClickListener listener) {
        setNegativeButton(0, text, listener);
        return this;
    }

    public AlertDialog setNegativeButton(int textId, String text,
            final OnClickListener listener) {
        showNegBtn = true;
        if (textId != 0) {
            btn_neg.setText(context.getString(textId));
        } else if (StringUtils.isNotEmpty(text)) {
            btn_neg.setText(text);
        } else {
            btn_neg.setText("取消");
        }

        btn_neg.setOnClickListener(new OnClickListener() {
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
     * @param strItem 条目名称
     */
    public AlertDialog addSheetItem(String strItem, OnSheetItemClickListener listener) {
        showListView = true;
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, listener));
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("Title");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showList) {
            recyclerView.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (showLayout) {
            dialog_Group.setVisibility(View.VISIBLE);
        }

        if (showClose) {
            close.setVisibility(View.VISIBLE);
            layoutBottom.setVisibility(View.GONE);
        }

        if (showPosBtn && showNegBtn) {
            layoutBottom.setVisibility(View.VISIBLE);
            btn_pos.setVisibility(View.VISIBLE);
            btn_neg.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            layoutBottom.setVisibility(View.VISIBLE);
            btn_pos.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && showNegBtn) {
            layoutBottom.setVisibility(View.VISIBLE);
            btn_neg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn && !showClose || showCancel) {
            layoutBottom.setVisibility(View.VISIBLE);
            btn_cancel.setVisibility(View.VISIBLE);
        }

        if (showListView) {
            layoutList.setVisibility(View.VISIBLE);
            setSheetItems();
        }
    }

    public AlertDialog show() {
        setLayout();
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键还起作用
        dialog.setCanceledOnTouchOutside(false);
        // 调用这个方法时，按对话框以外的地方不起作用。按返回键也不起作用
        // dialog.setCancelable(false);
        dialog.show();
        return null;
    }

    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }

        int size = sheetItemList.size();

        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.getName();
            final OnSheetItemClickListener listener = sheetItem.getItemClickListener();

            TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(18);
            textView.setBackgroundResource(R.drawable.recycler_bg);
            textView.setGravity(Gravity.CENTER);

            // 高度
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));

            // 点击事件
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    dialog.dismiss();
                }
            });

            layoutList.addView(textView);
        }
    }
}

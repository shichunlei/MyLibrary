package chingtech.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.TextView;

import chingtech.library.R;

/**
 * MyLibrary
 * Package chingtech.library.widget
 * Description:
 * Created by 师春雷
 * Created at 2017/4/11 11:49
 */
public class ProgressDialog extends Dialog {

    private String content = "加载中...";

    public ProgressDialog(Context context) {
        super(context, R.style.ProgressDialogStyle);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if(ProgressDialog.this.isShowing())
                    ProgressDialog.this.dismiss();
                break;
        }
        return true;
    }

    private void initView(){
        setContentView(R.layout.layout_progress_dialog);
        ((TextView) findViewById(R.id.tvcontent)).setText(getContent());
        setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.9f;
        getWindow().setAttributes(attributes);
        setCancelable(false);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

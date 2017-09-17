package chingtech.library.base.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import chingtech.library.widget.ProgressDialog;
import chingtech.library.widget.StateView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.xutils.x;

/**
 * Created by leo on 2016/9/23.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /** 上下文 */
    protected Context context;

    protected ProgressDialog progress;

    private long exitTime = 0;

    protected StateView mStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        progress = new ProgressDialog(this);

        x.view().inject(this);

        mStateView = StateView.inject(injectTarget());
        // mStateView.setEmptyResource(R.layout.base_empty);
        // mStateView.setRetryResource(R.layout.base_retry);

        init();
        initToolBar();
    }

    /**
     * 声明抽象方法
     *
     * 初始化操作
     */
    protected abstract void init();

    /**
     * 声明抽象方法
     *
     * 初始化ToolBar
     */
    protected abstract void initToolBar();

    protected abstract View injectTarget();

    /**
     * 接收前一个页面传递的String值
     *
     * @param key
     * @return
     */
    protected String getStringExtra(String key) {
        Intent receive = getIntent();
        return receive.getStringExtra(key);
    }

    /**
     * 接收前一个页面传递的Integer值
     *
     * @param key
     * @return
     */
    protected Integer getIntExtra(String key) {
        Intent receive = getIntent();
        return receive.getIntExtra(key, 0);
    }

    protected Object getSerializableExtra(String key) {
        Intent receive = getIntent();
        return receive.getSerializableExtra(key);
    }

    public List<Object> getSerializable(String key) {
        Intent receive = getIntent();
        return (ArrayList<Object>) receive.getSerializableExtra(key);
    }

    protected ArrayList<Parcelable> getParcelableArrayList(String key) {
        Intent receive = getIntent();
        return receive.getExtras().getParcelableArrayList(key);
    }

    /**
     * 通过类名启动Activity，是否结束本页面
     *
     * @param pClass
     * @param tag
     */
    protected void openActivity(Class<?> pClass, int tag) {
        showActivity(pClass, null, null, null, false, tag, -1);
    }

    /**
     * 通过类名启动Activity，是否结束本页面
     *
     * @param pClass
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带数据
     *
     * @param pClass
     * @param key
     * @param value
     */
    public void openActivity(Class<?> pClass, String key, Serializable value) {
        showActivity(pClass, null, key, value, false, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带单个数据
     *
     * @param pClass
     * @param key
     * @param value
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value, boolean isfinish) {
        showActivity(pClass, null, key, value, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且携带单个数据
     *
     * @param pClass
     * @param key
     * @param value
     * @param tag
     */
    public void openActivity(Class<?> pClass, String key, Serializable value, int tag) {
        showActivity(pClass, null, key, value, false, tag, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Flags数据
     *
     * @param pClass
     * @param flags
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, int flags, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, -1, flags);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param bundle
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, Bundle bundle, boolean isfinish) {
        showActivity(pClass, bundle, null, null, isfinish, -1, -1);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     *
     * @param pClass
     * @param pBundle
     * @param tag
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, int tag) {
        showActivity(pClass, pBundle, null, null, false, tag, -1);
    }

    private void showActivity(Class<?> pClass, Bundle bundle, String key, Serializable value,
            boolean isfinish, int tag, int flags) {

        Intent intent = new Intent(context, pClass);

        if (null != key) {
            intent.putExtra(key, value);
        }

        if (null != bundle) {
            intent.putExtras(bundle);
        }

        if (flags != -1) {
            intent.setFlags(flags);
        }

        if (tag == -1) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, tag);
        }

        if (isfinish) {
            this.finish();
        }
    }

    /**
     * Toast提示
     *
     * @param text
     */
    protected void showToast(CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast("再按一次退出！");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}

package chingtech.library.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import chingtech.library.widget.ProgressDialog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.xutils.x;

/**
 * Created by leo on 2016/9/9.
 */
public abstract class BaseFragment extends Fragment {

    /** 上下文 */
    public static Context context;

    private boolean injected = false;

    public ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();

        progress = new ProgressDialog(context);

        if (!injected) {
            x.view().inject(this, this.getView());
        }
        init();
    }

    //声明抽象方法
    protected abstract void init();

    /**
     * 接收前一个页面传递的String值
     */
    protected String getStringExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getStringExtra(key);
    }

    /**
     * 接收前一个页面传递的Integer值
     */
    protected Integer getIntExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getIntExtra(key, 0);
    }

    public Object getSerializableExtra(String key) {
        Intent receive = getActivity().getIntent();
        return receive.getSerializableExtra(key);
    }

    public List<Object> getSerializable(String key) {
        Intent receive = getActivity().getIntent();
        return (ArrayList<Object>) receive.getSerializableExtra(key);
    }

    /**
     * 通过类名启动Activity
     */
    protected void openActivity(Class<?> pClass) {
        showActivity(pClass, null, null, null, false, 0);
    }

    protected void openActivity(Class<?> pClass, boolean isfinish) {
        showActivity(pClass, null, null, null, isfinish, 0);
    }

    protected void openActivity(Class<?> pClass, int requestCode) {
        showActivity(pClass, null, null, null, false, requestCode);
    }

    protected void openActivity(Class<?> pClass, String key, Serializable value, int requestCode) {
        showActivity(pClass, null, key, value, false, requestCode);
    }

    /**
     * 通过类名启动Activity
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value) {
        showActivity(pClass, null, key, value, false, 0);
    }

    /**
     * 通过类名启动Activity，并且含有Bundle数据
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle, boolean isfinish) {
        showActivity(pClass, pBundle, null, null, isfinish, 0);
    }

    /**
     * @param pClass
     * @param key
     * @param value
     * @param isfinish
     */
    protected void openActivity(Class<?> pClass, String key, Serializable value, boolean isfinish) {
        showActivity(pClass, null, key, value, isfinish, 0);
    }

    /**
     * @param pClass
     * @param bundle
     * @param key
     * @param value
     * @param isfinish
     * @param requestCode
     */
    protected void showActivity(Class<?> pClass, Bundle bundle, String key, Serializable value,
            boolean isfinish, int requestCode) {

        Intent intent = new Intent(context, pClass);

        if (null != key) {
            intent.putExtra(key, value);
        }

        if (null != bundle) {
            intent.putExtras(bundle);
        }

        if (requestCode == 0) {
            startActivity(intent);
        } else {
            startActivityForResult(intent, requestCode);
        }

        if (isfinish) {
            getActivity().finish();
        }
    }

    /**
     * Toast提示
     *
     * @param text
     */
    public void showToast(CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}

package chingtech.library.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import chingtech.library.widget.ProgressDialog;
import org.xutils.x;

/**
 * HealthPadApp-v2
 * Package com.chingtech.healthpadapp.base
 * Description:
 * Created by 师春雷
 * Created at 2017/5/31 10:13
 */
public abstract class LazyFragment extends BaseFragment {

    private boolean injected = false;

    // 是否可见
    protected boolean isVisible;
    // 标志位，标志Fragment已经初始化完成
    protected boolean isPrepared = false;
    // 是否第一次加载
    protected boolean isFirst = true;

    /**
     * 实现Fragment数据的缓加载
     *
     * @param isVisibleToUser 对用户是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void onInVisible() {
    }

    protected void onVisible() {
        //加载数据
        LazyLoad();
    }

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
        if (!injected) {
            x.view().inject(this, this.getView());
        }

        progress = new ProgressDialog(context);

        //初始化view的各控件
        isPrepared = true;
        init();

        LazyLoad();
    }

    /**
     * 初始化操作
     */
    protected abstract void init();

    /**
     * 懒加载
     */
    protected abstract void LazyLoad();
}

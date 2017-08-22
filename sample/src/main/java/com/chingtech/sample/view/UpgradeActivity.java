package com.chingtech.sample.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import chingtech.library.base.activity.BaseActivity;
import com.chingtech.sample.R;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.download.DownloadListener;
import com.tencent.bugly.beta.download.DownloadTask;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * <p>
 * *    ***********    ***********    **
 * *    ***********    ***********    **
 * *    **             **             **
 * *    **             **             **
 * *    **             **             **
 * *    ***********    **             **
 * *    ***********    **             **
 * *             **    **             **
 * *             **    **             **
 * *             **    **             **
 * *    ***********    ***********    ***********
 * *    ***********    ***********    ***********
 * </p>
 * MyLibrary
 * Package com.chingtech.sample.view
 * Description:
 * Created by 师春雷
 * Created at 17/8/17 上午11:53
 */
@ContentView(R.layout.activity_upgrade)
public class UpgradeActivity extends BaseActivity {

    @ViewInject(R.id.tv)
    private TextView tv;
    @ViewInject(R.id.title)
    private TextView title;
    @ViewInject(R.id.version)
    private TextView version;
    @ViewInject(R.id.size)
    private TextView size;
    @ViewInject(R.id.time)
    private TextView time;
    @ViewInject(R.id.content)
    private TextView content;
    @ViewInject(R.id.cancel)
    private Button   cancel;
    @ViewInject(R.id.start)
    private Button   start;

    @Override
    protected void init() {
        updateBtn(Beta.getStrategyTask());
        tv.setText(tv.getText().toString() + Beta.getStrategyTask().getSavedLength() + "");
        title.setText(title.getText().toString() + Beta.getUpgradeInfo().title);
        version.setText(version.getText().toString() + Beta.getUpgradeInfo().versionName);
        size.setText(size.getText().toString() + Beta.getUpgradeInfo().fileSize + "");
        time.setText(time.getText().toString() + Beta.getUpgradeInfo().publishTime + "");
        content.setText(Beta.getUpgradeInfo().newFeature);
        start.setOnClickListener(v -> {
            DownloadTask task = Beta.startDownload();
            updateBtn(task);
            if (task.getStatus() == DownloadTask.DOWNLOADING) {
                finish();
            }
        });

        cancel.setOnClickListener(v -> {
            Beta.cancelDownload();
            finish();
        });
        Beta.registerDownloadListener(new DownloadListener() {
            @Override
            public void onReceive(DownloadTask task) {
                updateBtn(task);
                tv.setText(task.getSavedLength() + "");
            }

            @Override
            public void onCompleted(DownloadTask task) {
                updateBtn(task);
                tv.setText(task.getSavedLength() + "");
            }

            @Override
            public void onFailed(DownloadTask task, int code, String extMsg) {
                updateBtn(task);
                tv.setText("failed");

            }
        });
    }

    @Override
    protected void initToolBar() {

    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Beta.unregisterDownloadListener();
    }


    public void updateBtn(DownloadTask task) {
        switch (task.getStatus()) {
            case DownloadTask.INIT:
            case DownloadTask.DELETED:
            case DownloadTask.FAILED: {
                start.setText("开始下载");
            }
            break;
            case DownloadTask.COMPLETE: {
                start.setText("安装");
            }
            break;
            case DownloadTask.DOWNLOADING: {
                start.setText("暂停");
            }
            break;
            case DownloadTask.PAUSED: {
                start.setText("继续下载");
            }
            break;
            default:
                break;
        }
    }
}

package com.chingtech.sample.view;

import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import com.chingtech.sample.R;
import com.chingtech.sample.fragment.PhotoFragment;
import com.chingtech.sample.fragment.QuestionFragment;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_photo_fragment)
public class PhotoFragmentActivity extends BaseActivity {

    @ViewInject(R.id.toolbar)
    protected Toolbar  toolbar;
    @ViewInject(R.id.tv_title)
    private   TextView tvTitle;

    private PhotoFragment fragment;

//    private QuestionFragment fragment;

    @Override
    protected void init() {
        // 在部分低端手机，调用单独拍照时内存不足时会导致activity被回收，所以不重复创建fragment
        // 添加显示第一个fragment
        fragment = new PhotoFragment();
//        fragment=new QuestionFragment(0);
        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.tab_content, fragment, PictureConfig.FC_TAG)
                                   .show(fragment)
                                   .commit();

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                   .subscribe(new Observer<Boolean>() {
                       @Override
                       public void onSubscribe(Disposable d) {
                       }

                       @Override
                       public void onNext(Boolean aBoolean) {
                           if (aBoolean) {
                               PictureFileUtils.deleteCacheDirFile(PhotoFragmentActivity.this);
                           } else {
                               showToast(getString(R.string.picture_jurisdiction));
                           }
                       }

                       @Override
                       public void onError(Throwable e) {
                       }

                       @Override
                       public void onComplete() {
                       }
                   });
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("图片选择");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.tab_content);
    }
}

package com.chingtech.sample.view;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.anim.Rotate3D;
import com.chingtech.sample.R;

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
 * Created at 17/12/12 上午10:36
 */
public class LoginActivity extends BaseActivity {

    Rotate3D anim;

    @BindView(R.id.account_login_ll)
    LinearLayout account_login_ll;
    @BindView(R.id.account_phone_ll)
    LinearLayout account_phone_ll;
    @BindView(R.id.parent_ll)
    LinearLayout parent_ll;
    @BindView(R.id.no_pass_login)
    TextView     no_pass_login;
    @BindView(R.id.login)
    Button       login;

    @Override
    protected void init() {
        anim = new Rotate3D.Builder(this).bindParentView(parent_ll)
                                         .bindPositiveView(account_login_ll)
                                         .bindNegativeView(account_phone_ll)
                                         .create();

        no_pass_login.setOnClickListener(v -> {
            anim.transform();
            if (anim.isOpen()) {
                no_pass_login.setText("使用免密登录");
            } else {
                no_pass_login.setText("使用账户登录");
            }
        });

        login.setOnClickListener(view -> openActivity(MainActivity.class, true));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolBar() {
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {
    }
}

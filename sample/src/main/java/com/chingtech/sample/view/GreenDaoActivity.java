package com.chingtech.sample.view;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import chingtech.library.base.activity.BaseActivity;
import chingtech.library.utils.StatusBarHelper;
import chingtech.library.utils.TimeUtils;
import com.chingtech.greendao.gen.MemberDao;
import com.chingtech.greendao.gen.UserDao;
import com.chingtech.sample.App;
import com.chingtech.sample.R;
import com.chingtech.sample.bean.Member;
import com.chingtech.sample.bean.User;
import java.util.ArrayList;
import java.util.List;

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
 * Package com.chingtech.library.view
 * Description:
 * Created by 师春雷
 * Created at 17/8/12 上午10:02
 */
public class GreenDaoActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar  toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.textView)
    TextView mContext;

    private User    mUser;
    private UserDao mUserDao;

    private MemberDao mMemberDao;

    @Override
    protected void init() {
        mUserDao = App.getInstances().getDaoSession().getUserDao();

        mMemberDao = App.getInstances().getDaoSession().getMemberDao();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_greendao;
    }

    @Override
    protected void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        assert toolbar != null;
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        tvTitle.setText("GreenDao简单使用案例");

        StatusBarHelper.tintStatusBar(this, ContextCompat.getColor(context, R.color.colorPrimary));
    }

    @Override
    protected View injectTarget() {
        return findViewById(R.id.layout);
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4})
    public void onEvent(View view) {
        switch (view.getId()) {
            case R.id.button:
                addMemberDate();
                break;
            case R.id.button2:
                deleteDate();
                break;
            case R.id.button3:
                updateDate();
                break;
            case R.id.button4:
                findMember(1);
                break;
        }
    }

    /**
     * 增加数据
     */
    private void addMemberDate() {
        Member member = new Member((long) 1, 1, "anye3", "18601952581");
        mMemberDao.insertOrReplace(member);//添加一个
        mContext.setText(member.toString());
    }

    /**
     * 增加数据
     */
    private void addDate() {
        mUser = new User((long) 1, "anye3", "18601952581", 1, 28,
                         TimeUtils.strToDate("1990-09-23"));
        mUserDao.insertOrReplace(mUser);//添加一个
        mContext.setText(mUser.toString());
    }

    /**
     * 增加多个数据
     */
    private void addDateList() {
        List<User> users = new ArrayList<>();

        users.add(new User((long) 2, "anye", "18601952581", 1, 28,
                           TimeUtils.strToDate("1990-09-23")));
        users.add(new User((long) 3, "anye", "18601952581", 1, 28,
                           TimeUtils.strToDate("1990-09-23")));
        users.add(new User((long) 4, "anye", "18601952581", 1, 28,
                           TimeUtils.strToDate("1990-09-23")));
        users.add(new User((long) 5, "anye", "18601952581", 1, 28,
                           TimeUtils.strToDate("1990-09-23")));

        mUserDao.insertInTx(users);//添加多个
    }

    /**
     * 删除数据
     */
    private void deleteDate() {
        deleteUserById(1);
    }

    /**
     * 根据主键删除User
     *
     * @param id User的主键Id
     */
    public void deleteUserById(long id) {
        mUserDao.deleteByKey(id);
    }

    /**
     * 删除所有数据
     */
    private void deleteDateAll() {
        mUserDao.deleteAll();
    }

    /**
     * 更改数据
     */
    private void updateDate() {
        mUser = new User((long) 1, "师春雷", "18601952581", 1, 28, TimeUtils.strToDate("1990-09-23"));
        mUserDao.update(mUser);
    }

    /**
     * 查找数据
     */
    private void findDateAll() {
        List<User> users = mUserDao.loadAll();
        mContext.setText("查询全部数据==>" + users.toString());
    }

    /**
     * 根据ID查找数据
     */
    private void findMember(long id) {
        Member member = mMemberDao.loadByRowId(id);
        mContext.setText("查询Id为1的数据==>" + member.toString());
    }
}

package com.chingtech.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.chingtech.sample.bean.QuestionBean;
import com.chingtech.sample.fragment.QuestionFragment;
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
 * Package com.chingtech.sample.adapter
 * Description:
 * Created by 师春雷
 * Created at 17/8/28 上午10:45
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<QuestionBean> list;
    private int total = 0;

    public ViewPagerAdapter(FragmentManager fm, List<QuestionBean> list) {
        super(fm);
        this.list = list;
        total = list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return new QuestionFragment(list.get(position), (position + 1) + "/" + total);
    }

    @Override
    public int getCount() {
        return total;
    }
}

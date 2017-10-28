package com.chingtech.sample.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ListView;
import chingtech.library.base.adapter.ablistview.BaseAbsListHolder;
import chingtech.library.base.adapter.ablistview.CommonAdapter;
import com.chingtech.sample.R;
import com.chingtech.sample.bean.OptionBean;
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
 * Created at 17/8/29 上午9:52
 */
public class OptionsListAdapter extends CommonAdapter<OptionBean> {

    private ListView listview;

    public OptionsListAdapter(Context context, List<OptionBean> list, ListView listview,
            int... layoutIds) {
        super(context, list, layoutIds);
        this.listview = listview;
    }

    @Override
    public void onUpdate(BaseAbsListHolder holder, OptionBean item, int position) {
        holder.setText(R.id.check_question_option, item.getNo());
        if (TextUtils.isEmpty(item.getOptions())) {
            if (position == 0) {
                holder.setText(R.id.tv_question_option, "正确");
            }
            if (position == 1) {
                holder.setText(R.id.tv_question_option, "错误");
            }
        } else {
            holder.setText(R.id.tv_question_option, item.getOptions());
        }

        int backgroundId;
        if (listview.isItemChecked(position)) {
            backgroundId = R.drawable.red_thumb;
        } else {
            backgroundId = R.drawable.gray_thumb;
        }

        holder.setBackgroundColorRes(R.id.check_question_option, backgroundId);
    }
}

package com.chingtech.sample.adapter;

import android.content.Context;
import android.widget.ListView;
import chingtech.library.base.adapter.ablistview.BaseAbsListHolder;
import chingtech.library.base.adapter.ablistview.CommonAdapter;
import com.chingtech.sample.R;
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
public class OptionsListAdapter extends CommonAdapter<String> {

    private String[] letter = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private ListView listview;

    public OptionsListAdapter(Context context, List<String> list, ListView listview,
            int... layoutIds) {
        super(context, list, layoutIds);
        this.listview = listview;
    }

    @Override
    public void onUpdate(BaseAbsListHolder holder, String item, int position) {
        holder.setText(R.id.check_question_option, letter[position]);
        holder.setText(R.id.tv_question_option, item);

        int backgroundId;
        if (listview.isItemChecked(position)) {
            backgroundId = R.drawable.red_thumb;
        } else {
            backgroundId = R.drawable.gray_thumb;
        }

        holder.setBackgroundColorRes(R.id.check_question_option, backgroundId);
    }
}

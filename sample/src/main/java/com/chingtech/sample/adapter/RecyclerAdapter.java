package com.chingtech.sample.adapter;

import android.content.Context;
import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import chingtech.library.base.adapter.recyclerview.CommonRecyclerAdapter;
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
 * Created at 17/8/27 上午10:06
 */
public class RecyclerAdapter extends CommonRecyclerAdapter<String> {

    public RecyclerAdapter(Context context, List<String> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    @Override
    protected void onBindData(BaseRecyclerHolder holder, String item, int position) {
        holder.setText(R.id.tv_expand, item);
    }
}

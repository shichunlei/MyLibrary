package com.chingtech.sample.adapter;

import android.content.Context;
import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import chingtech.library.base.adapter.recyclerview.CommonRecyclerAdapter;
import com.chingtech.sample.R;
import com.chingtech.sample.bean.CarBean;
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
 * Created at 17/8/27 上午10:04
 */
public class RecCarAdapter extends CommonRecyclerAdapter<CarBean> {

    public RecCarAdapter(Context context, List<CarBean> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    @Override
    protected void onBindData(BaseRecyclerHolder holder, CarBean item, int position) {
        holder.setText(R.id.item_tv, item.getName());
        holder.setImageUrl(R.id.item_iv, item.getLogoUrl());
    }
}

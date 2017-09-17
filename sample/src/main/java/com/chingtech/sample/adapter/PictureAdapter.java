package com.chingtech.sample.adapter;

import android.content.Context;
import android.view.Gravity;
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
 * Created at 17/8/27 上午10:00
 */
public class PictureAdapter extends CommonRecyclerAdapter<String> {

    public PictureAdapter(Context context, List<String> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    @Override
    protected void onBindData(BaseRecyclerHolder holder, String item, int position) {
        holder.setLabelText(R.id.labelview, "**");
        holder.setGravity(R.id.labelview, Gravity.TOP | Gravity.RIGHT);
        holder.setLabelBgColor(R.id.labelview, R.color.google_orange);
        holder.setImageUrl(R.id.image, item, R.mipmap.ic_launcher);
    }
}

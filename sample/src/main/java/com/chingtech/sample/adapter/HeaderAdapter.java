package com.chingtech.sample.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import chingtech.library.base.adapter.recyclerview.CommonRecyclerAdapter;
import com.chingtech.sample.R;
import com.chingtech.sample.bean.GirlsBean;
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
 * Created at 17/8/27 上午9:59
 */
public class HeaderAdapter extends CommonRecyclerAdapter<GirlsBean> {

    private Context context;

    public HeaderAdapter(Context context, List<GirlsBean> list, int... layoutIds) {
        super(context, list, layoutIds);
        this.context = context;
    }

    @Override
    protected void onBindData(BaseRecyclerHolder holder, GirlsBean item, int position) {
        holder.setImageUrl(R.id.iv_photo, item.getAvatar());
        holder.setText(R.id.tv_name, item.getName());
        PictureAdapter adapter = new PictureAdapter(context, item.getPictures(),
                                                    R.layout.item_picture);
        holder.setLayoutManager(R.id.recyclerview_child, new GridLayoutManager(context, 3));
        holder.setAdapter(R.id.recyclerview_child, adapter);
    }
}

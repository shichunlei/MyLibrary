package com.chingtech.sample.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import chingtech.library.base.adapter.recyclerview.BaseRecyclerHolder;
import chingtech.library.base.adapter.recyclerview.CommonRecyclerAdapter;
import chingtech.library.utils.LogUtils;
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

    public HeaderAdapter(Context context, List<GirlsBean> list, int... layoutIds) {
        super(context, list, layoutIds);
    }

    @Override
    protected void onBindData(BaseRecyclerHolder holder, GirlsBean item, int position) {
        LogUtils.i("TAG", "onBindData: " + item.toString());
        holder.setImageUrl(R.id.iv_photo, item.getAvatar());
        holder.setText(R.id.tv_name, item.getName());
        PictureAdapter adapter = new PictureAdapter(mContext, item.getPictures(),
                                                    R.layout.item_picture);
        holder.setLayoutManager(R.id.recyclerview_child, new GridLayoutManager(mContext, 3));
        holder.setAdapter(R.id.recyclerview_child, adapter);
    }
}

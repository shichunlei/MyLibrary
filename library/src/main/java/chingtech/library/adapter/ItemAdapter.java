package chingtech.library.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import chingtech.library.R;
import chingtech.library.bean.ItemBean;
import chingtech.library.widget.SmoothCheckBox;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private List<ItemBean> mDatas;
    private Context        mContext;
    private LayoutInflater mInflater;

    private int mSelectedPos = -1;

    private RecyclerView mRv;

    public ItemAdapter(List<ItemBean> datas, Context context, RecyclerView rv) {
        mDatas = datas;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mRv = rv;

        // 设置数据集时，找到默认选中的pos
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).isSelected()) {
                mSelectedPos = i;
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.ivSelect.setChecked(mDatas.get(position).isSelected());
        holder.tvCoupon.setText(mDatas.get(position).getValue());

        holder.ivSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(holder, position);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListener(holder, position);
            }
        });
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);
            if (payload.containsKey("KEY_BOOLEAN")) {
                boolean aBoolean = payload.getBoolean("KEY_BOOLEAN");
                holder.ivSelect.setChecked(aBoolean, true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    public int getSelectedPos() {
        return mSelectedPos;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private SmoothCheckBox ivSelect;
        private TextView       tvCoupon;
        private LinearLayout   layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivSelect = itemView.findViewById(R.id.ivSelect);
            tvCoupon = itemView.findViewById(R.id.tvCoupon);
            layout = itemView.findViewById(R.id.layout_item);
        }
    }

    /**
     * 设置点击事件
     *
     * @param holder
     * @param position
     */
    private void setListener(MyViewHolder holder, int position) {
        // RecyclerView 定向刷新方法：不会有白光一闪动画 也不会重复onBindVIewHolder
        MyViewHolder myHolder = (MyViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
        if (myHolder != null) {//还在屏幕里
            myHolder.ivSelect.setChecked(false, true);
        } else {//add by 2016 11 22 for 一些极端情况，holder被缓存在Recycler的cacheView里，
            //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。所以add一个异常处理
            notifyItemChanged(mSelectedPos);
        }
        if (mSelectedPos != -1) {
            mDatas.get(mSelectedPos).setSelected(false);//不管在不在屏幕里 都需要改变数据
        }
        //设置新Item的勾选状态
        mSelectedPos = position;
        mDatas.get(mSelectedPos).setSelected(true);
        holder.ivSelect.setChecked(true, true);
    }
}

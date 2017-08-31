package com.demo.massdrawlayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.massdrawlayout.GoodsBean;
import com.demo.massdrawlayout.R;

import java.util.List;

/**
 * Created by liushu on 2017/8/25.
 */

public class PurchaseCommonGoodsAdapter extends RecyclerView.Adapter<PurchaseCommonGoodsAdapter.MyViewHolder> {

    private List<GoodsBean.GoodsListBean> mDataList;
    private View.OnClickListener mListener;

    public PurchaseCommonGoodsAdapter(List<GoodsBean.GoodsListBean> dataList, View.OnClickListener listener) {
        mDataList = dataList;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_port_list, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoodsBean.GoodsListBean goodsListBean = mDataList.get(position);
        holder.mTextView.setText(goodsListBean.getName());
        if (goodsListBean.isSelected()) {
            holder.mLayout.setBackgroundResource(R.drawable.bg_port);
            holder.mImageView.setVisibility(View.VISIBLE);
        } else {
            holder.mLayout.setBackgroundResource(R.drawable.bg_port_unselect);
            holder.mImageView.setVisibility(View.GONE);
        }
        holder.mLayout.setTag(position);
        holder.mLayout.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;
        private LinearLayout mLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_name);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_check_title);
            mLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_contract);
        }
    }
}

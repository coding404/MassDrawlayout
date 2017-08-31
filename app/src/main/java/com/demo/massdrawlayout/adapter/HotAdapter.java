package com.demo.massdrawlayout.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.massdrawlayout.GoodsBean;
import com.demo.massdrawlayout.R;

import java.util.List;


/**
 * Created by liushu on 2017/8/25.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyViewHolder> {

    private List<GoodsBean.GoodsListBean> mList;
    private View.OnClickListener mListener;

    public HotAdapter(List<GoodsBean.GoodsListBean> list, View.OnClickListener listener) {
        mList = list;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_hot, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GoodsBean.GoodsListBean goodsListBean = mList.get(position);
        holder.mTextView.setText(goodsListBean.getName());
        if (goodsListBean.isSelected()) {
            //  holder.mLayout.setBackgroundResource(R.drawable.bg_port);
            holder.mTextView.setTextColor(Color.rgb(250, 148, 11));
            holder.mImageView.setVisibility(View.VISIBLE);
        } else {
            //  holder.mLayout.setBackgroundResource(R.drawable.bg_port_unselect);
            holder.mTextView.setTextColor(Color.BLACK);
            holder.mImageView.setVisibility(View.GONE);
        }
        holder.mLayout.setTag(position);
        holder.mLayout.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;
        private RelativeLayout mLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_name);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_check_title);
            mLayout = (RelativeLayout) itemView.findViewById(R.id.rl_item_contract);
        }
    }
}

package com.demo.massdrawlayout.sortList;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.demo.massdrawlayout.R;

import java.util.List;



/**
 * @author J 适配器
 */
public class SortAdapter extends RecyclerView.Adapter<SortAdapter.MyViewHolder> implements SectionIndexer {
    private List<SortModel> mList;
    private View.OnClickListener mListener;

    public SortAdapter(List<SortModel> list, View.OnClickListener listener) {
        mList = list;
        mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int section = getSectionForPosition(position);
        SortModel model = mList.get(position);
        if (position == getPositionForSection(section)) {
            holder.mTvLetter.setVisibility(View.VISIBLE);
            holder.mTvLetter.setText(model.getSortLetters());
        } else {
            holder.mTvLetter.setVisibility(View.GONE);
        }
        if (model.getSelected()) {
            holder.mTvName.setTextColor(Color.rgb(250, 148, 11));
            holder.mivCheck.setVisibility(View.VISIBLE);
        } else {
            holder.mTvName.setTextColor(Color.BLACK);
            holder.mivCheck.setVisibility(View.GONE);
        }
        holder.mTvName.setText(model.getName());
        holder.mRlitem.setTag(position);
        holder.mRlitem.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvLetter;
        private TextView mTvName;
        private ImageView mivCheck;
        private RelativeLayout mRlitem;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvLetter = (TextView) itemView.findViewById(R.id.catalog);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mivCheck = (ImageView) itemView.findViewById(R.id.iv_check);
            mRlitem = (RelativeLayout) itemView.findViewById(R.id.rl_item);
        }
    }

    /*  *
       * 得到首字母的ascii值*/
    public int getSectionForPosition(int position) {
        if (mList.size() == 0) {
            return -1;
        }
        return mList.get(position).getSortLetters().charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    public String getAlpha(String str) {
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    @Override
    public Object[] getSections() {
        return null;
    }
}
package com.demo.massdrawlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.massdrawlayout.adapter.HotAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liushu on 2017/8/28.
 */

public class HotPortsFragment extends Fragment {
    @Bind(R.id.rv_hot)
    RecyclerView mRvHot;
    private HotAdapter mAdapter;
    private List<GoodsBean.GoodsListBean> mList;
    private IChioces mChioces;

    public void setChioces(IChioces chioces) {
        mChioces = chioces;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hot_goods, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String s = FileUtil.readFromAssets(getContext(), "hotports.json");
        Gson gson = new Gson();
        final GoodsBean bean = gson.fromJson(s, GoodsBean.class);
        mList = bean.getGoodsList();
        mAdapter = new HotAdapter(mList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() instanceof Integer) {
                    int position = (int) v.getTag();
                    for (GoodsBean.GoodsListBean goodsListBean : mList) {
                        goodsListBean.setSelected(false);
                    }
                    mList.get(position).setSelected(true);
                    mAdapter.notifyDataSetChanged();
                    mChioces.portClick(mList.get(position).getName());
                }
            }
        });
        mRvHot.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvHot.setAdapter(mAdapter);
        mRvHot.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

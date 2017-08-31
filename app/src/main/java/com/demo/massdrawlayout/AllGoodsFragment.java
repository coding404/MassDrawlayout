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

import com.demo.massdrawlayout.sortList.CharacterParser;
import com.demo.massdrawlayout.sortList.PinyinComparator;
import com.demo.massdrawlayout.sortList.SortAdapter;
import com.demo.massdrawlayout.sortList.SortModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by liushu on 2017/8/25.
 */

public class AllGoodsFragment extends Fragment {

    @Bind(R.id.rv_all)
    RecyclerView mRvAll;
    private SortAdapter mAdapter; // 排序的适配器
    private List<GoodsBean.GoodsListBean> mList;
    private PinyinComparator mPinyinComparator;
    private CharacterParser characterParser;
    private List<SortModel> mSorts;
    private IChioces mChioces;

    public void setChioces(IChioces chioces) {
        mChioces = chioces;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_goods, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        characterParser = CharacterParser.getInstance();
        mPinyinComparator = new PinyinComparator();
        String s = FileUtil.readFromAssets(getContext(), "allgoods.json");
        Gson gson = new Gson();
        final GoodsBean bean = gson.fromJson(s, GoodsBean.class);
        mList = bean.getGoodsList();
        mSorts = filledData(mList);
        Collections.sort(mSorts, mPinyinComparator);
        mAdapter = new SortAdapter(mSorts, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() instanceof Integer) {
                    int position = (int) v.getTag();
                    for (SortModel sort : mSorts) {
                        sort.setSelected(false);
                    }
                    mSorts.get(position).setSelected(true);
                    mAdapter.notifyDataSetChanged();
                    mChioces.goodsClick(mSorts.get(position).getName());
                }
            }
        });
        mRvAll.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvAll.setAdapter(mAdapter);
        mRvAll.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    private List<SortModel> filledData(List<GoodsBean.GoodsListBean> data) {
        List<SortModel> mSortList = new ArrayList<SortModel>();
        for (int i = 0; i < data.size(); i++) {
            SortModel sortModel = new SortModel();
            GoodsBean.GoodsListBean goodsListBean = data.get(i);
            sortModel.setName(goodsListBean.getName());
            sortModel.setSelected(goodsListBean.isSelected());
            String pinyin = characterParser.getSelling(goodsListBean.getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

package com.demo.massdrawlayout;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.demo.massdrawlayout.adapter.PageAdapter;
import com.demo.massdrawlayout.adapter.PurchaseCommonGoodsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity implements IChioces {

    @Bind(R.id.iv_back)
    ImageView mIvBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.iv_search)
    ImageView mIvSearch;
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(R.id.iv_select)
    ImageView mIvSelect;
    @Bind(R.id.tv_first_goods)
    TextView mTvFirstGoods;
    @Bind(R.id.tv_unnormal_goods)
    TextView mTvUnnormalGoods;
    @Bind(R.id.tv_first_all)
    TextView mTvFirstAll;
    @Bind(R.id.iv_first_go)
    ImageView mIvFirstGo;
    @Bind(R.id.ll_first_go)
    LinearLayout mLlFirstGo;
    @Bind(R.id.lrv_first)
    RecyclerView mLrvFirst;
    @Bind(R.id.tv_second_port)
    TextView mTvSecondPort;
    @Bind(R.id.tv_unnormal_port)
    TextView mTvUnnormalPort;
    @Bind(R.id.tv_second_all)
    TextView mTvSecondAll;
    @Bind(R.id.iv_second_go)
    ImageView mIvSecondGo;
    @Bind(R.id.ll_second_go)
    LinearLayout mLlSecondGo;
    @Bind(R.id.lrv_second)
    RecyclerView mLrvSecond;
    @Bind(R.id.tv_second_commit)
    TextView mTvSecondCommit;
    @Bind(R.id.ll_one)
    LinearLayout mLlOne;
    @Bind(R.id.iv_portdetail_back)
    ImageView mIvPortdetailBack;
    @Bind(R.id.tv_portdetail_title)
    TextView mTvPortdetailTitle;
    @Bind(R.id.rb_portdetail_doing)
    RadioButton mRbPortdetailDoing;
    @Bind(R.id.rb_portdetail_done)
    RadioButton mRbPortdetailDone;
    @Bind(R.id.rg_portdetail_select)
    RadioGroup mRgPortdetailSelect;
    @Bind(R.id.vp_portdetail)
    ViewPager mVpPortdetail;
    @Bind(R.id.ll_port_include)
    LinearLayout mLlPortInclude;
    @Bind(R.id.iv_goodsdetail_back)
    ImageView mIvGoodsdetailBack;
    @Bind(R.id.tv_goodsdetail_title)
    TextView mTvGoodsdetailTitle;
    @Bind(R.id.rb_goodsdetail_doing)
    RadioButton mRbGoodsdetailDoing;
    @Bind(R.id.rb_goodsdetail_done)
    RadioButton mRbGoodsdetailDone;
    @Bind(R.id.rg_goodsdetail_select)
    RadioGroup mRgGoodsdetailSelect;
    @Bind(R.id.vp_goodsdetail)
    ViewPager mVpGoodsdetail;
    @Bind(R.id.ll_goods_include)
    LinearLayout mLlGoodsInclude;
    @Bind(R.id.dl_more)
    DrawerLayout mDlMore;
    private ImageView mImageView;
    private DrawerLayout mLayout;

    private PurchaseCommonGoodsAdapter mGoodsAdapter;
    private PurchaseCommonGoodsAdapter mPortsAdapter;
    private List<GoodsBean.GoodsListBean> mGoodsList;
    private List<GoodsBean.GoodsListBean> mPortsList;

    private PageAdapter mGoodsDetailAdapter;
    private List<Fragment> mGoodsDetailList;

    private PageAdapter mPortsDetailAdapter;
    private List<Fragment> mPortsDetailList;

    private String mGoods = "";
    private String mPort = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTvTitle.setText("MassDrawLayout");
        mDlMore.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initTouchListener();
        initCommonGoodsData();
        initCommonPortsData();
        initGoodsDetailData();
        intiPortsDetailData();
    }

    private void intiPortsDetailData() {
        mRgPortdetailSelect.check(R.id.rb_portdetail_doing);
        mRgPortdetailSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int indexOfChild = group.indexOfChild(group.findViewById(checkedId));
                mVpPortdetail.setCurrentItem(indexOfChild, false);
            }
        });
        mPortsDetailList = new ArrayList<>();
        HotPortsFragment hotPortsFragment = new HotPortsFragment();
        hotPortsFragment.setChioces(this);
        mPortsDetailList.add(hotPortsFragment);
        AllPortsFragment allPortsFragment = new AllPortsFragment();
        allPortsFragment.setChioces(this);
        mPortsDetailList.add(allPortsFragment);
        mPortsDetailAdapter = new PageAdapter(getSupportFragmentManager(), mPortsDetailList);
        mVpPortdetail.setAdapter(mPortsDetailAdapter);
        mVpPortdetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mRgPortdetailSelect.check(R.id.rb_portdetail_doing);
                } else if (position == 1) {
                    mRgPortdetailSelect.check(R.id.rb_portdetail_done);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initGoodsDetailData() {
        mRgGoodsdetailSelect.check(R.id.rb_goodsdetail_doing);
        mRgGoodsdetailSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int indexOfChild = group.indexOfChild(group.findViewById(checkedId));
                mVpGoodsdetail.setCurrentItem(indexOfChild, false);
            }
        });
        mGoodsDetailList = new ArrayList<>();
        HotGoodsFragment hotGoodsFragment = new HotGoodsFragment();
        hotGoodsFragment.setChioces(this);
        mGoodsDetailList.add(hotGoodsFragment);
        AllGoodsFragment allGoodsFragment = new AllGoodsFragment();
        allGoodsFragment.setChioces(this);
        mGoodsDetailList.add(allGoodsFragment);
        mGoodsDetailAdapter = new PageAdapter(getSupportFragmentManager(), mGoodsDetailList);
        mVpGoodsdetail.setAdapter(mGoodsDetailAdapter);
        mVpGoodsdetail.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mRgGoodsdetailSelect.check(R.id.rb_goodsdetail_doing);
                } else if (position == 1) {
                    mRgGoodsdetailSelect.check(R.id.rb_goodsdetail_done);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initCommonPortsData() {
        mLrvSecond.setLayoutManager(new GridLayoutManager(this, 3));
        String s = FileUtil.readFromAssets(this, "normalports.json");
        Gson gson = new Gson();
        final GoodsBean bean = gson.fromJson(s, GoodsBean.class);
        mPortsList = new ArrayList<>();
        mPortsList.addAll(bean.getGoodsList());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() instanceof Integer) {
                    int position = (int) v.getTag();
                    for (int i = 0; i < mPortsList.size(); i++) {
                        mPortsList.get(i).setSelected(false);
                    }
                    mPort = mPortsList.get(position).getName();
                    mPortsList.get(position).setSelected(true);
                    mPortsAdapter.notifyDataSetChanged();
                    mTvUnnormalPort.setText("");
                }
            }
        };
        mPortsAdapter = new PurchaseCommonGoodsAdapter(mPortsList, listener);
        mLrvSecond.setAdapter(mPortsAdapter);


    }

    private void initCommonGoodsData() {
        mLrvFirst.setLayoutManager(new GridLayoutManager(this, 3));
        String s = FileUtil.readFromAssets(this, "normalgoods.json");
        Gson gson = new Gson();
        final GoodsBean bean = gson.fromJson(s, GoodsBean.class);
        mGoodsList = new ArrayList<>();
        mGoodsList.addAll(bean.getGoodsList());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag() instanceof Integer) {
                    int position = (int) v.getTag();
                    for (int i = 0; i < mGoodsList.size(); i++) {
                        mGoodsList.get(i).setSelected(false);
                    }
                    mGoods = mGoodsList.get(position).getName();
                    mGoodsList.get(position).setSelected(true);
                    mGoodsAdapter.notifyDataSetChanged();
                    mTvUnnormalGoods.setText("");
                }
            }
        };
        mGoodsAdapter = new PurchaseCommonGoodsAdapter(mGoodsList, listener);
        mLrvFirst.setAdapter(mGoodsAdapter);
    }

    private void initTouchListener() {
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        };
        mLlOne.setOnTouchListener(touchListener);
        mLlPortInclude.setOnTouchListener(touchListener);
        mLlGoodsInclude.setOnTouchListener(touchListener);
    }

    @OnClick({R.id.iv_back, R.id.iv_select, R.id.ll_first_go, R.id.ll_second_go, R.id.iv_portdetail_back, R.id.iv_goodsdetail_back, R.id.tv_second_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_first_go:
                mLlOne.setVisibility(View.GONE);
                mLlGoodsInclude.setVisibility(View.VISIBLE);
                mLlPortInclude.setVisibility(View.GONE);
                break;
            case R.id.ll_second_go:
                mLlOne.setVisibility(View.GONE);
                mLlGoodsInclude.setVisibility(View.GONE);
                mLlPortInclude.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_select:
                mLlOne.setVisibility(View.VISIBLE);
                mLlGoodsInclude.setVisibility(View.GONE);
                mLlPortInclude.setVisibility(View.GONE);
                mDlMore.openDrawer(Gravity.RIGHT);
                break;
            case R.id.iv_goodsdetail_back:
                mLlOne.setVisibility(View.VISIBLE);
                mLlGoodsInclude.setVisibility(View.GONE);
                mLlPortInclude.setVisibility(View.GONE);
                break;
            case R.id.iv_portdetail_back:
                mLlOne.setVisibility(View.VISIBLE);
                mLlGoodsInclude.setVisibility(View.GONE);
                mLlPortInclude.setVisibility(View.GONE);
                break;
            case R.id.tv_second_commit:
                mEtContent.setText("品名：" + mGoods + ",港口：" + mPort);
                mDlMore.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.iv_search:
                break;
            default:
                break;
        }
    }

    @Override
    public void goodsClick(String goods) {
        mGoods = goods;
        mTvUnnormalGoods.setText(goods);
        mLlOne.setVisibility(View.VISIBLE);
        mLlGoodsInclude.setVisibility(View.GONE);
        mLlPortInclude.setVisibility(View.GONE);
        resetCommenData(mGoodsList, mGoodsAdapter);
    }

    @Override
    public void portClick(String port) {
        mPort = port;
        mTvUnnormalPort.setText(port);
        mLlOne.setVisibility(View.VISIBLE);
        mLlGoodsInclude.setVisibility(View.GONE);
        mLlPortInclude.setVisibility(View.GONE);
        resetCommenData(mPortsList, mPortsAdapter);
    }

    private void resetCommenData(List<GoodsBean.GoodsListBean> list, RecyclerView.Adapter adapter) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelected(false);
        }
        adapter.notifyDataSetChanged();
    }
}

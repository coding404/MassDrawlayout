package com.demo.massdrawlayout;

import java.util.List;

/**
 * Created by liushu on 2017/8/22.
 */

public class GoodsBean {


    private List<GoodsListBean> goodsList;

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
        /**
         * name : 全部
         * selected : false
         */

        private String name;
        private boolean selected;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }
}

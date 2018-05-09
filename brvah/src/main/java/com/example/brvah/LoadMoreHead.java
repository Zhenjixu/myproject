package com.example.brvah;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

/**
 * Created by Administrator
 * on 2018/5/9 0009.
 * at 北京
 */

public class LoadMoreHead extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.quick_view_load_more;
    }

    @Override
    public boolean isLoadEndGone() {
        return true;
    }

    @Override
    protected int getLoadingViewId() {
        return R.layout.isloading;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.layout.header;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}

package com.example.brvah;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator
 * on 2018/5/8 0008.
 * at 北京
 */

public class QuickAdapter extends BaseItemDraggableAdapter<DataBean, BaseViewHolder> {

    Context context;

    public QuickAdapter(@Nullable List<DataBean> data, Context context) {
        super(R.layout.recycler_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, DataBean item) {
        holder.setText(R.id.title, item.getName())
                .addOnClickListener(R.id.title)
                .linkify(R.id.title);

        Picasso.with(context).load(String.valueOf(item.getImgs())).into((ImageView) holder.getView(R.id.img));
    }


}

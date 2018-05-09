package com.example.brvah;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }

    private void getData() {
        new OkHttpClient().newCall(new Request.Builder().url("http://172.16.47.35:8080/json/day04.json").build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Type type = new TypeToken<ArrayList<JsonObject>>() {}.getType();
                        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(string, type);

                        final ArrayList<DataBean> arrayList = new ArrayList<>();
                        for (JsonObject jsonObject : jsonObjects) {
                            arrayList.add(new Gson().fromJson(jsonObject, DataBean.class));
                        }

                        final QuickAdapter adapter = new QuickAdapter(arrayList, MainActivity.this);
                        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
                        recycler.setLayoutManager(manager);
                        recycler.setAdapter(adapter);

                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
                            }
                        });

//                        动画
//                        adapter.openLoadAnimation(Anima.SCALEIN);

//                        自定义动画
                        adapter.openLoadAnimation(new BaseAnimation() {
                            @Override
                            public Animator[] getAnimators(View view) {
                                return new Animator[]{
                                        ObjectAnimator.ofFloat(view, "scaleY", 0f, 1.1f, 1),
                                        ObjectAnimator.ofFloat(view, "scaleX", 0f, 1.1f, 1)
                                };
                            }
                        });

                        adapter.isFirstOnly(false);
//                        添加头脚布局
//                        View headerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.header, null);
//                        final View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.header, null);
//
//                        adapter.addHeaderView(headerView);
//                        adapter.addFooterView(view);
//
//                        view.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                adapter.removeFooterView(view);
//                            }
//                        });

                        // 没有数据的时候默认显示该布局
//                        adapter.setEmptyView(getView());

                        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
                        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
                        itemTouchHelper.attachToRecyclerView(recycler);

                        OnItemDragListener drag = new OnItemDragListener() {
                            @Override
                            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                            }

                            @Override
                            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
                            }

                            @Override
                            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                            }

                        };

                        // open drag
                        adapter.enableDragItem(itemTouchHelper, R.id.title, true);
                        adapter.setOnItemDragListener(drag);

                        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
                            @Override
                            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                            }

                            @Override
                            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                            }

                            @Override
                            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                            }

                            @Override
                            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
                            }
                        };

//                         open slide to delete
                        adapter.enableSwipeItem();
                        adapter.setOnItemSwipeListener(onItemSwipeListener);


                        adapter.setEnableLoadMore(true);

                        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                recycler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        int mCurrentCounter = adapter.getData().size();
                                        boolean isErr = false;
                                        if (mCurrentCounter <= arrayList.size()) {
                                            //数据全部加载完毕
                                            adapter.addData(new DataBean());
                                            adapter.loadMoreEnd();
                                        } else {
                                            if (isErr) {
                                                //成功获取更多数据
                                                adapter.addData(new DataBean());
                                                mCurrentCounter = adapter.getData().size();
                                                adapter.loadMoreComplete();
                                            } else {
                                                //获取更多数据失败
                                                isErr = true;
                                                Toast.makeText(MainActivity.this, "无网络链接", Toast.LENGTH_LONG).show();
                                                adapter.loadMoreFail();

                                            }
                                        }

                                    }
                                }, 2000);
                            }
                        }, recycler);
                    }
                });
            }

        });

    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
    }
}

package com.example.chaochen.myvalyoutdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //绑定VirtualLayoutManager
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        /**
         * 设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 公共属性
        linearLayoutHelper.setMarginBottom(100);
        linearLayoutHelper.setItemCount(4);// 设置布局里Item个数
        linearLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        // linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setAspectRatio(5);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(10);
        // 设置间隔高度
        // 设置的间隔会与RecyclerView的addItemDecoration（）添加的间隔叠加.
        VlayoutAdapter adapterLinearLayout = new VlayoutAdapter(this, linearLayoutHelper, 4) {
            //可以重写onBindViewHolder 方法 设置布局属性
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150 + position % 5 * 20);
                holder.itemView.setLayoutParams(layoutParams);
            }
        };

        /**
         * 设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
        stickyLayoutHelper.setStickyStart(true);// true = 组件吸在顶部   false = 组件吸在底部
        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量
        VlayoutAdapter adapterStickLayout = new VlayoutAdapter(this, stickyLayoutHelper, 1){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.mTextView.setText("可固定" + position);
            }
        };

        /**
         * 设置可选固定布局
         */
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y
        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT, 0, 0);
        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(130);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(150);// 设置基准位置的纵向偏移量Y
        /*- SHOW_ALWAYS：与FixLayoutHelper的行为一致，固定在某个位置；
          - SHOW_ON_ENTER：默认不显示视图，当页面滚动到这个视图的位置的时候，才显示；
          - SHOW_ON_LEAVE：默认不显示视图，当页面滚出这个视图的位置的时候显示；*/
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_LEAVE);// 设置Item的显示模式
        VlayoutAdapter adapterScrollFixLayout = new VlayoutAdapter(this, scrollFixLayoutHelper, 1){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.mTextView.setText("设置可选固定布局");
            }
        };

        /**
         * 设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setWeights(new float[]{10, 20, 30, 10, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格

//        gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position > 5) {
//                    // 第7个位置后,每个Item占3个网格
//                    return 2;
//                } else {
//                    // 第7个位置前,每个Item占2个网格
//                    return 5;
//                }
//            }
//        });
        VlayoutAdapter adapterGridLayout = new VlayoutAdapter(this, gridLayoutHelper, 40);


        /**
         * 设置固定布局
         */
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y
        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT, 100, 100);
        VlayoutAdapter adapterFixLayout = new VlayoutAdapter(this, fixLayoutHelper, 2){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.mTextView.setText("悬浮" + position);
            }
        };

        /**
         * 设置浮动布局
         */
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        floatLayoutHelper.setDefaultLocation(200, 200);
        VlayoutAdapter adapterFloatLayout = new VlayoutAdapter(this, floatLayoutHelper, 1){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(300, 300);
                holder.mTextView.setLayoutParams(mLayoutParams);
                holder.mTextView.setText("浮动");
            }
        };

        /**
         * 设置兰格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
//        columnLayoutHelper.setItemCount(3);
        columnLayoutHelper.setWeights(new float[]{20, 30, 50});
        VlayoutAdapter adapterColumnLayout = new VlayoutAdapter(this, columnLayoutHelper, 3){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if (position == 1) {
                    holder.mTextView.setText("第二个Column");
                }
            }
        };

        /**
         * 通栏布局
         */
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        VlayoutAdapter adapterSingleLayout = new VlayoutAdapter(this, singleLayoutHelper, 1){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                    holder.mTextView.setText("通栏布局" + position);
            }
        };

        /**
         * 一拖N布局
         */
        // 在构造函数里传入显示的Item数
        // 最多是1拖4,即5个
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(4);
        onePlusNLayoutHelper.setAspectRatio(4);// 设置设置布局内每行布局的宽与高的比
        VlayoutAdapter adapterOnePlusNLayout = new VlayoutAdapter(this, onePlusNLayoutHelper, 4){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.mTextView.setText("OnePlusN" + position);
            }
        };

        /**
         * 瀑布流布局
         */
        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper();
        staggeredGridLayoutHelper.setLane(4);
        staggeredGridLayoutHelper.setHGap(20);
        staggeredGridLayoutHelper.setVGap(40);
        VlayoutAdapter adapterStaggeredGridLayout = new VlayoutAdapter(this, staggeredGridLayoutHelper, 30){
            @Override
            public void onBindViewHolder(ValyoutViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150 +position % 5 * 20);
                holder.itemView.setLayoutParams(layoutParams);
                holder.mTextView.setText("Staggered" + position);
            }
        };


        //将生成的LayoutHelper 交给Adapter，并绑定到RecyclerView 对象
        // 1. 设置Adapter列表（同时也是设置LayoutHelper列表）
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        // 2. 将上述创建的Adapter对象放入到DelegateAdapter.Adapter列表里
        adapters.add(adapterLinearLayout);
        adapters.add(adapterStickLayout);
        adapters.add(adapterScrollFixLayout);
        adapters.add(adapterGridLayout);
        adapters.add(adapterFixLayout);
        adapters.add(adapterFloatLayout);
        adapters.add(adapterColumnLayout);
        adapters.add(adapterSingleLayout);
        adapters.add(adapterOnePlusNLayout);
        adapters.add(adapterStaggeredGridLayout);
        // 3. 创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);

        // 4. 将DelegateAdapter.Adapter列表绑定到DelegateAdapter
        delegateAdapter.setAdapters(adapters);

        // 5. 将delegateAdapter绑定到recyclerView
        recyclerView.setAdapter(delegateAdapter);
    }


}

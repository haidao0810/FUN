package com.example.bf.kf.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.RelativeLayout;

import com.example.bf.kf.DAO.bean.ClassIficationBean;
import com.example.bf.kf.R;
import com.example.bf.kf.utils.ViewUtils;
import com.example.bf.kf.widget.adapter.ClassIficationAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCX on 2020/3/5.
 * 分类布局
 * 全部 类型1  类型2 类型3
 */
public class ClassificationLayout extends RelativeLayout {
    private Context mContext;
    private RecyclerView typeRv;
    private LinearLayoutManager layoutManager;
    private ClassIficationAdapter adapter;

    public ClassificationLayout(Context context) {
        this(context,null);
    }

    public ClassificationLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClassificationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        initView();
        initEvent();
        initData();
    }
    private void  initView(){
        View view=View.inflate(mContext, R.layout.view_class_ification_layout,null);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewUtils.dip2px(42)));
        typeRv=view.findViewById(R.id.class_ification_rv);
        layoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
        typeRv.setLayoutManager(layoutManager);
        adapter=new ClassIficationAdapter(mContext);
        typeRv.setAdapter(adapter);
        addView(view);
    }

    private void initEvent(){

    }

    /**
     * 设置显示类型数据
     */
    public void setShowTypeData(List<ClassIficationBean> data){
        adapter.setData(data);
    }
    private void initData(){

    }
}

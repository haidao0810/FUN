package com.example.bf.kf.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bf.kf.R;
import com.example.bf.kf.utils.ViewUtils;

/**
 * Created by CCX on 2020/3/2.
 * Bd title layout
 */
public class BdTitleLayout extends RelativeLayout {
    private Context context;
    private ImageView leftIv;
    private TextView titleCenterTv;
    private String titleName="";
    public BdTitleLayout(Context context) {
        this(context,null);
    }

    public BdTitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BdTitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        TypedArray array= context.obtainStyledAttributes(attrs,R.styleable.BdTitleLayout);
        titleName= array.getString(R.styleable.BdTitleLayout_title_name);
        initView();
        initEvent();
        initData();
    }

    /**
     * 初始化view
     */
    private void initView(){

        View view=View.inflate(context, R.layout.view_bd_title_layout,null);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewUtils.dip2px(42)));
        leftIv=view.findViewById(R.id.title_left_black_iv);
        titleCenterTv=view.findViewById(R.id.title_center_tv);
        titleCenterTv.setText(titleName);
        addView(view);
    }

    /**
     * 初始化事件
     */
    private void initEvent(){

    }

    /**
     * 初始化数据
     */
    private void initData(){

    }

    public void setTitleCenterTv(String titleStr){
        titleCenterTv.setText(titleStr);
    }
}

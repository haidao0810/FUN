package com.example.bf.kf.function;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.bf.kf.DAO.bean.ClassIficationBean;
import com.example.bf.kf.DAO.bean.CosmeticsBean;
import com.example.bf.kf.R;
import com.example.bf.kf.common.BaseActivity;
import com.example.bf.kf.widget.BdTitleLayout;
import com.example.bf.kf.widget.ClassificationLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCX on 2020/3/2.
 * 化妆品商品列表界面
 */
public class CosmeticsGoodsActivity extends BaseActivity {
    private BdTitleLayout titleLayout;
    private ClassificationLayout classificationLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.a_cosmetics_goods_layout);
        super.onCreate(savedInstanceState);

    }

    public static void  startActivity(Context context, CosmeticsBean bean){
        Intent intent=new Intent(context,CosmeticsGoodsActivity.class);
        intent.putExtra("data",bean);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        titleLayout=findViewById(R.id.a_title_layout);
        titleLayout.setTitleCenterTv("测试标题");
        classificationLayout=findViewById(R.id.class_type_layout);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    protected void initData() {
        super.initData();
        List<ClassIficationBean> data =new ArrayList<>();
        ClassIficationBean bean1=new ClassIficationBean();
        bean1.setClassName("面霜");
        bean1.setUuid("1");
        bean1.setSelect(true);
        ClassIficationBean bean2=new ClassIficationBean();
        bean2.setClassName("去污渍");
        bean2.setUuid("2");
        bean2.setSelect(false);
        ClassIficationBean bean3=new ClassIficationBean();
        bean3.setClassName("爽肤水/化妆水");
        bean3.setUuid("3");
        bean3.setSelect(false);

        ClassIficationBean bean4=new ClassIficationBean();
        bean4.setClassName("日本奥尔滨");
        bean4.setUuid("4");
        bean4.setSelect(false);
        data.add(bean1);
        data.add(bean2);
        data.add(bean3);
        data.add(bean4);
        classificationLayout.setShowTypeData(data);
    }
}

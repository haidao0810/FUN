package com.example.bf.kf.widget.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bf.kf.DAO.bean.ClassIficationBean;
import com.example.bf.kf.R;
import com.example.bf.kf.common.pullVIew.BaseListAdapter;
import com.example.bf.kf.common.pullVIew.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CCX on 2020/3/5.
 * 分类布局 适配器
 */
public class ClassIficationAdapter extends BaseListAdapter {
    private int selectState=101;
    private int noSelectState=100;
    private Context mContext;
    private List<ClassIficationBean> mData=new ArrayList<>();
    private ItemTypeSelect itemTypeSelect;
    public ClassIficationAdapter(Context mContext) {
        super(mContext);
        this.mContext=mContext;
    }

    @Override
    protected BaseViewHolder onCreateItemView(ViewGroup parent) {
        View view=View.inflate(mContext, R.layout.adapter_no_select_class_layout,null);
        return new NoSelectClassViewHolder(view);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==selectState){
            View view=View.inflate(mContext, R.layout.adapter_select_class_layout,null);
            return new SelectClassViewHolder(view);
        }else if(viewType==noSelectState){
            View view=View.inflate(mContext, R.layout.adapter_no_select_class_layout,null);
            return new NoSelectClassViewHolder(view);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    /**
     * 设置数据
     * @param data
     */
    public void setData(List<ClassIficationBean> data){
        mData.clear();
        if(data!=null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    protected int getDataCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
       if(position>=0&&position<mData.size()){
           ClassIficationBean bean= mData.get(position);
           if(bean.isSelect()){
               return selectState;
           }else{
               return noSelectState;
           }
       }
       return noSelectState;
    }

    /**
     * 选择类型的viewHolder
     */
    class SelectClassViewHolder extends BaseViewHolder{
        private TextView classTypeNameTv;

        public SelectClassViewHolder(View itemView) {
            super(itemView);
            classTypeNameTv=itemView.findViewById(R.id.class_type_name_tv);
        }

        @Override
        protected void onItemClick(View view, int adapterPosition) {

        }

        @Override
        protected void onBindViewHolder(BaseViewHolder holder, int position) {
                if(position>=0&&position<mData.size()){
                    ClassIficationBean bean=   mData.get(position);
                    if(bean!=null){
                        classTypeNameTv.setText(bean.getClassName());
                    }
                }
        }
    }

    /**
     * 没有选择
     */
    class NoSelectClassViewHolder extends BaseViewHolder{
        private TextView classTypeNameTv;
        public NoSelectClassViewHolder(View itemView) {
            super(itemView);
            classTypeNameTv=itemView.findViewById(R.id.class_type_name_tv);
        }

        @Override
        protected void onItemClick(View view, int adapterPosition) {
            if(adapterPosition>=0&&adapterPosition<mData.size()) {
                handleTypeClick(adapterPosition);
                if (itemTypeSelect != null) {
                    itemTypeSelect.typeSelect(mData.get(adapterPosition));
                }
            }
        }

        @Override
        protected void onBindViewHolder(BaseViewHolder holder, int position) {
            if(position>=0&&position<mData.size()){
                ClassIficationBean bean=   mData.get(position);
                if(bean!=null){
                    classTypeNameTv.setText(bean.getClassName());
                }
            }
        }
    }

    private void handleTypeClick(int position){
            ClassIficationBean bean=   mData.get(position);
            if(bean.isSelect()){

            }else{
                for(int i=0;i<mData.size();i++){
                    ClassIficationBean item=mData.get(i);
                    item.setSelect(false);
                }
                bean.setSelect(true);
            }
            notifyDataSetChanged();

    }

    public void setItemTypeSelect(ItemTypeSelect typeSelect){
        this.itemTypeSelect=typeSelect;
    }
    interface ItemTypeSelect{
        void typeSelect( ClassIficationBean bean);
    }
}

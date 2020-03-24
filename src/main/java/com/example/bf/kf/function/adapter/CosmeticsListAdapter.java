package com.example.bf.kf.function.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bf.kf.DAO.bean.CosmeticsBean;
import com.example.bf.kf.R;
import com.example.bf.kf.common.AdapterItemBackInterface;
import com.example.bf.kf.common.pullVIew.BaseListAdapter;
import com.example.bf.kf.common.pullVIew.BaseViewHolder;
import com.example.bf.kf.utils.CollectionUtils;
import com.example.bf.kf.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 品牌列表适配器
 */
public class CosmeticsListAdapter extends BaseListAdapter {
    private Context mContext;
    List<CosmeticsBean> mData=new ArrayList<>();
    private Map<String,Integer> pointMap=new HashMap<>();
    private Map<Integer,String> letterMap=new HashMap<>();
    private AdapterItemBackInterface<CosmeticsBean> itemBackInterface;
    public CosmeticsListAdapter(Context mContext) {
        super(mContext);
        this.mContext=mContext;
    }

    @Override
    protected BaseViewHolder onCreateItemView(ViewGroup parent) {
        View view=View.inflate(mContext, R.layout.adapter_cosmetics_item_view,null);
        return new CosmeticsViewHolder(view);
    }

    @Override
    protected int getDataCount() {
        return mData.size();
    }

    /**
     * 设置数据
     */
    public void setData(List<CosmeticsBean> data){
        mData.clear();
        if(CollectionUtils.isNotEmpty(data)){
            mData.addAll(data);
        }
        handleDataPY();
        notifyDataSetChanged();
    }

    /**
     * 设置选择的words
     * @param words
     */
    public int getSelectWords(String words){
        if(StringUtils.isNotBlank(words)){
          Iterator iterator= pointMap.keySet().iterator();
            while (iterator.hasNext()){
                String key= (String) iterator.next();
                if(key.equalsIgnoreCase(words)){
                    return pointMap.get(key);
                }
            }

        }else{
            return -1;
        }
        return -1;
    }


    /**
     * 处理数据首拼音
     */
    private void handleDataPY(){
        pointMap.clear();
        letterMap.clear();
        if(CollectionUtils.isNotEmpty(mData)){
            for(int i=0;i<mData.size();i++ ){
                CosmeticsBean bean=mData.get(i);
                String fStr=bean.getFirstYP();
                if(StringUtils.isNotBlank(fStr)){
                   String  firstLetter= fStr.substring(0,1);
                    if(pointMap.containsKey(firstLetter)){
                    }else{
                        pointMap.put(firstLetter,i);
                        letterMap.put(i,firstLetter);
                    }
                }
            }
        }
    }
    public void setItemBackInterface(AdapterItemBackInterface<CosmeticsBean> itemBackInterface){
        this.itemBackInterface=itemBackInterface;
    }

    class CosmeticsViewHolder extends BaseViewHolder{
        private TextView cosmeticsNameTv,firstLetterTv;
        private ImageView logoIv;
        public CosmeticsViewHolder(View itemView) {
            super(itemView);
            cosmeticsNameTv=itemView.findViewById(R.id.cosmetics_name_tv);
            firstLetterTv=itemView.findViewById(R.id.first_letter_tv);
            logoIv=itemView.findViewById(R.id.cosmetics_logo_iv);
        }

        @Override
        protected void onItemClick(View view, int adapterPosition) {
            if(itemBackInterface!=null&&adapterPosition>=0&&adapterPosition<mData.size()){
                itemBackInterface.itemClick(mData.get(adapterPosition));
            }
        }

        @Override
        protected void onBindViewHolder(BaseViewHolder holder, int position) {
            if(position>=0&&position<mData.size()){
                CosmeticsBean bean=  mData.get(position);
                firstLetterTv.setVisibility(View.GONE);
                if(bean!=null){
                    if(letterMap.containsKey(position)){
                        firstLetterTv.setVisibility(View.VISIBLE);
                        firstLetterTv.setText(letterMap.get(position));
                    }
                    cosmeticsNameTv.setText(bean.getBrandName());
                    Glide.with(mContext).load(bean.getbLogoUrl()).into(logoIv);
                }
            }
        }
    }
}

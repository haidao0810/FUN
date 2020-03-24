package com.example.bf.kf.function.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.bf.kf.DAO.bean.CosmeticsBean;
import com.example.bf.kf.DAO.parser.CosmeticsListParser;
import com.example.bf.kf.R;
import com.example.bf.kf.common.AdapterItemBackInterface;
import com.example.bf.kf.common.BaseFragment;
import com.example.bf.kf.function.CosmeticsGoodsActivity;
import com.example.bf.kf.function.adapter.CosmeticsListAdapter;
import com.example.bf.kf.net.AjaxCallBack;
import com.example.bf.kf.net.AjaxParams;
import com.example.bf.kf.net.response.AjaxListResult;
import com.example.bf.kf.net.response.AjaxResponse;
import com.example.bf.kf.utils.CollectionUtils;
import com.example.bf.kf.utils.StringUtils;
import com.example.bf.kf.widget.BdTitleLayout;
import com.example.bf.kf.widget.WordsNavigationView;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 品牌列表
 */
public class LookCosmeticsFragment extends BaseFragment {
    private BdTitleLayout titleLayout;
    private RecyclerView cosmeticsRv;
    private WordsNavigationView wordsNavigationView;
    private CosmeticsListAdapter adapter;
    private LinearLayoutManager manager;
    private Map<Integer,String> letterMap=new LinkedHashMap<>();

    @Override
    protected int getLayoutId() {
        return R.layout.f_look_cosmetics_layout;
    }
    public static LookCosmeticsFragment newInstance() {
        return new LookCosmeticsFragment();
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        titleLayout=view.findViewById(R.id.bd_title_layout);
        titleLayout.setTitleCenterTv(getString(R.string.app_name));
        cosmeticsRv=view.findViewById(R.id.cosmetics_pl);
        manager=new LinearLayoutManager(getContext());
        cosmeticsRv.setLayoutManager(manager);
        adapter=new CosmeticsListAdapter(getContext());
        cosmeticsRv.setAdapter(adapter);
        wordsNavigationView=view.findViewById(R.id.words_nv);
        initEvent();
        initData();
    }

    private void initEvent(){
        wordsNavigationView.setWordsSelectLister(new WordsNavigationView.WordsSelectLister() {
            @Override
            public void wordsChange(String words) {
                if(StringUtils.isNotBlank(words)&&adapter!=null){
                    int point=adapter.getSelectWords(words);
                    if(point>=0){
                        cosmeticsRv.scrollToPosition(point);
                        LinearLayoutManager mLayoutManager =
                                (LinearLayoutManager) cosmeticsRv.getLayoutManager();//这里的LinearLayoutManager对象只能是动态获取，不能用全局的。
                        mLayoutManager.scrollToPositionWithOffset(point, 0);
                    }
                }
            }
        });

        cosmeticsRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                int[] pos = new int[2];
                pos[0] = manager.findFirstCompletelyVisibleItemPosition();
                int point=pos[0];
                if(letterMap.containsKey(pos[0])){

                }else{
                    Iterator iterator=letterMap.keySet().iterator();
                    int beforeP=0;
                    while (iterator.hasNext()){
                        int hasPoint= (int) iterator.next();
                        if(hasPoint>point){
                            point=beforeP;
                            break;
                        }
                        beforeP=hasPoint;
                    }

                }
                String letter= letterMap.get(point);
                wordsNavigationView.setSelectLetter(letter);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        adapter.setItemBackInterface(new AdapterItemBackInterface<CosmeticsBean>() {
            @Override
            public void itemClick(CosmeticsBean cosmeticsBean) {
                if(cosmeticsBean!=null){
                    CosmeticsGoodsActivity.startActivity(getActivity(),cosmeticsBean);
                }
            }
        });
    }

    private void initData(){
        getLookCosmeticsListForNet();
    }


    @Override
    protected void onVisible() {

    }

    @Override
    protected void inVisible() {

    }

    /**
     * 获取数据列表
     */
    private void getLookCosmeticsListForNet(){
        AjaxParams params=new AjaxParams();
        getControlCapSdk().getLookCosmeticsList(params, new AjaxCallBack<AjaxListResult<CosmeticsBean>>(new CosmeticsListParser()) {
            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }

            @Override
            public void onFailure(Throwable t) {

            }

            @Override
            public void onSuccess(AjaxListResult<CosmeticsBean> cosmeticsBeanAjaxListResult) {
                int state=cosmeticsBeanAjaxListResult.getStatus();
                if(state== AjaxResponse.S_SUCCESS){
                    List<CosmeticsBean> cosmeticsList=cosmeticsBeanAjaxListResult.getList();
                    Collections.sort(cosmeticsList, new Comparator<CosmeticsBean>() {
                        @Override
                        public int compare(CosmeticsBean o1, CosmeticsBean o2) {
                            return o1.getFirstYP().compareTo(o2.getFirstYP());
                        }

                        @Override
                        public boolean equals(Object obj) {
                            return false;
                        }
                    });
                    handleDataPY(cosmeticsList);
                    adapter.setData(cosmeticsList);
                }else{
                    Toast.makeText(getContext(),cosmeticsBeanAjaxListResult.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 处理数据首拼音
     */
    private void handleDataPY(List<CosmeticsBean> mData){
        letterMap.clear();
         Map<String,Integer> pointMap=new HashMap<>();
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
}
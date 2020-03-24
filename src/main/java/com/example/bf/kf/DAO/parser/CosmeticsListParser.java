package com.example.bf.kf.DAO.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.bf.kf.DAO.bean.CosmeticsBean;
import com.example.bf.kf.exceptions.AndroidServerException;
import com.example.bf.kf.net.parser.AjaxJsonMapper;
import com.example.bf.kf.net.response.AjaxListResult;
import com.example.bf.kf.net.response.AjaxResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * 品牌列表解析
 */
public class CosmeticsListParser extends AjaxJsonMapper<AjaxListResult<CosmeticsBean>> {
    @Override
    public AjaxListResult<CosmeticsBean> jsonMapper(String json) throws AndroidServerException {
        return json2List(json);
    }

    @Override
    public AjaxListResult json2List(JSONObject json) throws AndroidServerException {
        AjaxListResult ajaxListResult=new AjaxListResult(AjaxResponse.S_SUCCESS,"ok");
        if(json.containsKey("data")){
            JSONArray dataArray=json.getJSONArray("data");
            if(dataArray!=null&&dataArray.size()>0){
                List<CosmeticsBean> cosmeticsBeanList=new ArrayList<>();
                int size=dataArray.size();
                for(int i=0;i<size;i++){
                    JSONObject itemObject=dataArray.getJSONObject(i);
                    CosmeticsBean cosmeticsBean=new CosmeticsBean();
                    if(itemObject.containsKey("brandId")){
                        cosmeticsBean.setBrandId(itemObject.getString("brandId"));
                    }
                    if(itemObject.containsKey("brandLocation")){
                        cosmeticsBean.setBrandLocation(itemObject.getString("brandLocation"));
                    }
                    if(itemObject.containsKey("brandName")){
                        cosmeticsBean.setBrandName(itemObject.getString("brandName"));
                    }
                    if(itemObject.containsKey("firstLetter")){
                        cosmeticsBean.setFirstYP(itemObject.getString("firstLetter").toUpperCase());
                    }
                    if(itemObject.containsKey("pinyin")){
                        cosmeticsBean.setAllYP(itemObject.getString("pinyin").toUpperCase());
                    }
                    if(itemObject.containsKey("brand")){
                        cosmeticsBean.setbLogoUrl(itemObject.getString("brand"));
                    }

                    cosmeticsBeanList.add(cosmeticsBean);
                }
                ajaxListResult.setList(cosmeticsBeanList);
            }
        }
        return ajaxListResult;
    }
}

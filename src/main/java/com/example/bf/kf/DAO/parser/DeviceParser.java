package com.example.bf.kf.DAO.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.bf.kf.exceptions.AndroidServerException;
import com.example.bf.kf.DAO.bean.DeviceBean;
import com.example.bf.kf.net.parser.AjaxJsonMapper;
import com.example.bf.kf.net.response.AjaxObjResult;
import com.example.bf.kf.net.response.AjaxResponse;


public class DeviceParser extends AjaxJsonMapper<AjaxObjResult<DeviceBean>> {
    @Override
    public AjaxObjResult<DeviceBean> jsonMapper(String json) throws AndroidServerException {
        return json2Obj(json);
    }

    @Override
    public AjaxObjResult json2Object(JSONObject json) throws AndroidServerException {
        AjaxObjResult ajaxObjResult=new AjaxObjResult(AjaxResponse.S_SUCCESS,"ok");
        if(json.containsKey("data")){
            try {
                JSONObject dataObject=json.getJSONObject("data");
                DeviceBean deviceBean=new DeviceBean();
                if(dataObject!=null&&dataObject.containsKey("deviceId")){
                    deviceBean.setDeviceId(dataObject.getString("deviceId"));
                }
                ajaxObjResult.setObj(deviceBean);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ajaxObjResult;
    }
}

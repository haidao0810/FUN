package com.example.bf.kf.net.parser;

import android.text.TextUtils;


import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.bf.kf.exceptions.AndroidServerException;
import com.example.bf.kf.net.response.AjaxListResult;
import com.example.bf.kf.net.response.AjaxObjResult;
import com.example.bf.kf.net.response.AjaxResponse;
import com.example.bf.kf.utils.StringUtils;

import java.io.IOException;

import okhttp3.Response;

public abstract class AjaxJsonMapper<T> extends AjaxMapper<T> {
    private SendSendApiErrorInterface sendSendApiErrorInterface;

    public abstract T jsonMapper(String json)throws AndroidServerException ;

    public AjaxListResult json2List(JSONObject json)throws AndroidServerException {
        return null;
    }
    public AjaxObjResult json2Object(JSONObject json)throws AndroidServerException{
        return null;
    }

    public AjaxJsonMapper(){

    }
    public AjaxJsonMapper(SendSendApiErrorInterface sendSendApiErrorInterface){
        this.sendSendApiErrorInterface=sendSendApiErrorInterface;
    }

    @Override
    public T resultMapper(Response response) throws AndroidServerException {
        try {
            String result = response.body().string();
            return jsonMapper(result);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AndroidServerException(e);
        }

    }



    String nextId;
    public AjaxListResult json2List(final String result) throws AndroidServerException  {

        if( TextUtils.isEmpty(result) ){
            throw  new AndroidServerException("Json Is Null!");
        }
        try {
            JSONObject json=JSONObject.parseObject(result);
            int status = AjaxResponse.S_FAILED;
            if(json.containsKey("status")){
                status=json.getIntValue("status");
            }
            String message = json.getString("message");
            if (200 != status) {
                handleSendApiError(-1,result);
                AjaxListResult ajaxListResult=    new AjaxListResult(status,message);
                if(json.containsKey("data")){
                    ajaxListResult.setOtherMessage(json.getString("data"));
                }
                return ajaxListResult;
            }

            return json2List(json);

        } catch (Exception e) {
            handleSendApiError(-1,result);
            e.printStackTrace();
            throw new AndroidServerException(e);
        }

    }

    public AjaxObjResult json2Obj(final String result) throws AndroidServerException  {
        if( TextUtils.isEmpty(result) ){
            throw  new AndroidServerException("Json Is Null!");
        }

        try {
            JSONObject json=JSONObject.parseObject(result);
            int status = AjaxResponse.S_FAILED;
            if(json.containsKey("status")){
                status=json.getIntValue("status");
            }
            String message = json.getString("message");
            if (200 != status) {
                handleSendApiError(status,result);
                AjaxObjResult ajaxObjResult=   new AjaxObjResult(status,message);
                if(json.containsKey("data")){
                    ajaxObjResult.setOtherMessage(json.getString("data"));
                }
                return ajaxObjResult;
            }
            return json2Object(json);
        } catch (JSONException e) {
            e.printStackTrace();
            handleSendApiError(-1,result);
            throw new AndroidServerException(e);
        }
    }

    /**
     * 处理上传APi错误
     */
    private void handleSendApiError(int api,String eValue){
        String apiName=getAPIName();
        boolean sureSend=false;
        if(StringUtils.isNotBlank(apiName)){
            int[] normalStatus=getStatusNormal();
            if(normalStatus!=null&&normalStatus.length>0){
                int length=normalStatus.length;
                for(int i=0;i<length;i++){
                    if(normalStatus[i]==api){
                        sureSend=false;
                        break;
                    }
                }
            }else{
                sureSend=true;
            }
        }
        if(sureSend){
            sendSendApiError(apiName,eValue);
        }

    }

    /**
     * 获取API 名称
     * @return 返回接口名
     */
    public String  getAPIName(){
        return "";
    }

    /**
     * 获取接口正常状态值
     * @return
     */
    public int[] getStatusNormal(){
        return null;
    }

    /**
     * 发送错误到服务端
     * @param apiName
     * @param eValue
     */
    public void sendSendApiError(String apiName,String eValue){
        if(sendSendApiErrorInterface!=null){
            sendSendApiErrorInterface.sendSendApiError(apiName,eValue);
        }
    }

    public interface SendSendApiErrorInterface{
        void sendSendApiError(String apiName, String eValue);
    }
}

/**
 * Copyright (c) 2012-2013, Michael Yang 杨福海 (www.yangfuhai.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.bf.kf.net;

import android.content.Context;
import android.text.TextUtils;


import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;


/**

 */
public class AjaxParams {

	private static String ENCODING = "UTF-8";

	private Object tag;
    private Context mContext;

	public void setContext(Context mContext) {
		this.mContext = mContext;
	}

	public Context getContext() {
		return mContext;
	}

	private String actionUrl;
	private String mockInfo;

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public void setMockInfo(String mockInfo) {
		this.mockInfo = mockInfo;
	}

	public String getMockInfo() {
		return mockInfo;
	}

	protected SortedMap<String, String> urlParams = new TreeMap<>();
	protected SortedMap<String, String> fileParams = new TreeMap<>();
	
	public AjaxParams(){
	}
   
	public AjaxParams addComms(Context context){
		return this;
	}
	
	public AjaxParams(Map<String, String> source) {
        for(Map.Entry<String, String> entry : source.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
	
	public AjaxParams put(String key, String value){
        if( !TextUtils.isEmpty(key) &&!TextUtils.isEmpty(value) ) {
        	 urlParams.put(key, value);
        }
       
        return this;
    }
	
	public AjaxParams put(String key, File value){
        if(  !TextUtils.isEmpty(key) && null != value)  {
        	
        }
        return this;
    }
	
	public List<BasicNameValuePair> getParamsList() {
        List<BasicNameValuePair> lparams = new LinkedList<BasicNameValuePair>();

        for(ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
			if(!TextUtils.isEmpty(entry.getKey())&&!TextUtils.isEmpty(entry.getValue())){
				lparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
        }

        return lparams;
    }
	
	

	
	/**
	 * 注意有签名加入
	 * @return
	 */
	public String getParamString() {
        return URLEncodedUtils.format(getParamsList(), ENCODING);
    }


    public Request getRequest4Post(){

		FormBody.Builder formBodyBuilder = new FormBody.Builder();
		List<BasicNameValuePair> list = getParamsList();
		for (BasicNameValuePair param : list) {
			formBodyBuilder.add(param.getName(), param.getValue());//传递键值对参数
		}

		Request request = new Request.Builder()
				.url(getActionUrl())
				.post(formBodyBuilder.build())
				.build();

		return request;
	}

	/**
	 * 上传图片
	 * @param fileName
	 * @param imagePath
     * @return
     */
	public Request getRequest4PostImage(String fileName,String imagePath){
		File file = new File(imagePath);
		RequestBody image = RequestBody.create(MediaType.parse("image/png"), file);
		RequestBody requestBody = new MultipartBody.Builder()
				.setType(MultipartBody.FORM)
				.addFormDataPart("file", fileName, image)
				.build();
		Request request = new Request.Builder()
				.url(getActionUrl())
				.post(requestBody)
				.build();
		return request;
	}



	/**
	 * 设置get连接拼接参数
	 * @return
	 */
    public Request getRequest4Get() {

    	String  param= "";
		if(urlParams != null && urlParams.size() > 0) {
			int i = 0;
			for (Map.Entry<String, String> entry : urlParams.entrySet()) {
				if(i++ == 0) {
					param = param + "?" + entry.getKey() + "=" + entry.getValue();
				} else {
					param = param + "&" + entry.getKey() + "=" + entry.getValue();
				}
			}
		}

		Request request = new Request.Builder()
				.url( getActionUrl() + param )
				.get()
				.build();

		return request;
	}

	public Request getRequest5Get(){
		Request request = new Request.Builder()
				.url( getActionUrl())
				.get()
				.build();

		return request;
	}


	public SortedMap<String, String> getUrlParams() {
		return urlParams;
	}

	public void setUrlParams(SortedMap<String, String> urlParams) {
		this.urlParams = urlParams;
	}

	public SortedMap<String, String> getFileParams() {
		return fileParams;
	}

	public void setFileParams(SortedMap<String, String> fileParams) {
		this.fileParams = fileParams;
	}

	public Object getTag() {
		return tag;
	}

	public void setTag(Object tag) {
		this.tag = tag;
	}


}
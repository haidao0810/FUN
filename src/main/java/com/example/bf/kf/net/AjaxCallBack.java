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


import com.example.bf.kf.exceptions.AndroidServerException;
import com.example.bf.kf.net.parser.AjaxMapper;
import com.example.bf.kf.net.response.AjaxResponse;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 
 * @author michael
 *
 * @param <T> 目前泛型支持 String,File, 以后扩展：JSONObject,Bitmap,byte[],XmlDom
 */
public abstract class AjaxCallBack<T extends AjaxResponse> implements Callback {

	AjaxMapper<T> mAjaxMapper;

	public abstract void onStart();
	public abstract void onStop();
	public abstract void onFailure(Throwable t);
	public abstract void onSuccess(T t);

	public AjaxCallBack(AjaxMapper<T> mAjaxMapper) {
		this.mAjaxMapper = mAjaxMapper;
	}

	/**
	 * 发送错误到服务端
	 * @param apiName
	 * @param eValue
	 */
	public void sendSendApiError(String apiName,String eValue){

	}
	@Override
	public void onFailure(Call call, IOException e) {

		NetSdk.handler.post(
				new Runnable() {
					@Override
					public void run() {
						onFailure(e);
						onStop();
					}
				}
		);
	}

	@Override
	public void onResponse(Call call, Response response) {
		try {
			T t = mAjaxMapper.resultMapper(response);

			NetSdk.handler.post(
					new Runnable() {
						@Override
						public void run() {
							onSuccess(t);
							onStop();
						}
					}
			);

		} catch (AndroidServerException e) {
			e.printStackTrace();
			NetSdk.handler.post(
					new Runnable() {
						@Override
						public void run() {
							onFailure(e);
							onStop();
						}
					}
			);
		}
	}
}

/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.example.bf.kf.log;

import android.text.TextUtils;
import android.util.Log;


/**
 * Helper class to redirect {@link LogManager#logger} to {@link Log}
 */
public class LoggerDefault implements Logger {

    @Override
    public int v(String msg) {

        return this.v(null,msg,null);

    }

    @Override
    public int v(String tag, String msg) {

        return this.v(tag,msg,null);

    }

    @Override
    public int v(String tag, String msg, Throwable tr) {

        if(!LogManager.isDEBUG() || !LogManager.v) {
            return 0;
        }

        if (TextUtils.isEmpty(tag)){
            tag = LogManager.TAG;
        }

        if (null != tr){
            return Log.v(tag, msg, tr);
        }

        return Log.v(tag, msg);
    }

    @Override
    public int d(String msg) {

        return this.d(null,msg,null);

    }

    @Override
    public int d(String tag, String msg) {

        return this.d(tag,msg,null);

    }

    @Override
    public int d(String tag, String msg, Throwable tr) {
        if(!LogManager.isDEBUG() || !LogManager.d) {
            return 0;
        }

        if (TextUtils.isEmpty(tag)){
            tag = LogManager.TAG;
        }

        if (null != tr){
            return Log.d(tag, msg, tr);
        }

        return Log.d(tag, msg);
    }

    @Override
    public int i(String msg) {
        return this.i(null, msg,null);
    }

    @Override
    public int i(String tag, String msg) {
        return this.i(tag, msg,null);
    }

    @Override
    public int i(String tag, String msg, Throwable tr) {
        if(!LogManager.isDEBUG() || !LogManager.i) {
            return 0;
        }

        if (TextUtils.isEmpty(tag)){
            tag = LogManager.TAG;
        }

        if (null != tr){
            return Log.i(tag, msg, tr);
        }

        return Log.i(tag, msg);
    }

    @Override
    public int w(String msg) {
        return this.w(null, msg,null);
    }

    @Override
    public int w(String tag, String msg) {
        return this.w(tag, msg,null);
    }

    @Override
    public int w(String tag, String msg, Throwable tr) {
        if(!LogManager.isDEBUG() || !LogManager.w) {
            return 0;
        }

        if (TextUtils.isEmpty(tag)){
            tag = LogManager.TAG;
        }

        if (null != tr){
            return Log.w(tag, msg, tr);
        }

        return Log.w(tag, msg);
    }

    @Override
    public int e(String tag, String msg) {
        return this.e(tag, msg,null);
    }

    @Override
    public int e(String tag, String msg, Throwable tr) {
        if(!LogManager.isDEBUG() || !LogManager.e) {
            return 0;
        }

        if (TextUtils.isEmpty(tag)){
            tag = LogManager.TAG;
        }

        if (null != tr){
            return Log.e(tag, msg, tr);
        }

        return Log.e(tag, msg);
    }


}

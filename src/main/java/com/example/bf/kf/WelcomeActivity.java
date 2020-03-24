package com.example.bf.kf;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bf.kf.common.BaseActivity;
import com.example.bf.kf.common.KFXmlInfo;
import com.example.bf.kf.function.HomePageActivity;
import com.example.bf.kf.function.LoginActivity;
import com.example.bf.kf.DAO.bean.DeviceBean;
import com.example.bf.kf.DAO.parser.DeviceParser;
import com.example.bf.kf.net.AjaxCallBack;
import com.example.bf.kf.net.AjaxParams;
import com.example.bf.kf.net.response.AjaxObjResult;
import com.example.bf.kf.net.response.AjaxResponse;
import com.example.bf.kf.utils.AndroidUIUtils;
import com.example.bf.kf.utils.MacUtils;
import com.example.bf.kf.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * 欢迎页
 */
public class WelcomeActivity extends BaseActivity {
    private int  REQUEST_PHONE_STATE = 10010;
    private ImageView wPageIv;
    private KFXmlInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        wPageIv = findViewById(R.id.welcome_page_iv);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        Glide.with(this).load(R.mipmap.welcome_icon).into(wPageIv);
        //Android6.0需要动态获取权限
        if (Build.VERSION.SDK_INT >= 23&&ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
            permissonItems.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "状态权限", R.drawable.permission_ic_phone));
            HiPermission.create(WelcomeActivity.this).permissions(permissonItems).msg("为了您更好的使用"+getResources().getString(R.string.app_name)+"，需要以下权限").checkMutiPermission(new PermissionCallback() {
                @Override
                public void onClose() {
                    Log.d("p","onClose");
                }

                @Override
                public void onFinish() {
                    Log.d("p","onFinish");
                }

                @Override
                public void onDeny(String permission, int position) {
                    Log.d("p","onDeny "+permission +"  "+position);
                }

                @Override
                public void onGuarantee(String permission, int position) {
                    Log.d("p","onGuarantee "+permission +"  "+position);
                }
            });
//            ActivityCompat.requestPermissions(WelcomeActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_PHONE_STATE);
        } else {
            //上传设备信息
            handleDeviceBaseMessage();
        }
    }

    /**
     * 判断设备信息
     */
    private void handleDeviceBaseMessage() {
        if (info == null) {
            info = new KFXmlInfo(getBaseContext());
        }
        String deviceId = info.getDeviceId();
        if (StringUtils.isBlank(deviceId)) {
            initDeviceMessage();
        } else {
            //判断是否登录
//            if (info.getLoginState()) {
//                Intent intent = new Intent(WelcomeActivity.this, HomePageActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
            HomePageActivity.startHomePageActivity(WelcomeActivity.this);
            finish();
        }
    }

    /**
     * 初始化设备信息
     */
    private void initDeviceMessage() {
        String mac1 = MacUtils.getMacNo1();
        String mac2 = MacUtils.getCurrentMac(this);
        TelephonyManager telephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE); //获取IMEI号

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        String IMEIStr = telephonyManager.getDeviceId();
        String deviceIdStr=  AndroidUIUtils.getDeviceAndroidId(getBaseContext());
        String device=  StringUtils.isNotBlank(deviceIdStr)?deviceIdStr:AndroidUIUtils.getGenrateDeviceId();
        AjaxParams params=new AjaxParams();
        params.put("imei",IMEIStr);
        params.put("mac1",mac1);
        params.put("mac2",mac2);
        getControlCapSdk().sendDeviceMessage(params, new AjaxCallBack<AjaxObjResult<DeviceBean>>(new DeviceParser()) {
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
            public void onSuccess(AjaxObjResult<DeviceBean> deviceBeanAjaxObjResult) {
                int states=deviceBeanAjaxObjResult.getStatus();
                if(states== AjaxResponse.S_SUCCESS){
                    DeviceBean deviceBean=  deviceBeanAjaxObjResult.getObj();
                    if(deviceBean!=null){
                        if (info == null) {
                            info = new KFXmlInfo(getBaseContext());
                        }
                        //获取设备信息
                        info.setDeviceId(deviceBean.getDeviceId());
                        HomePageActivity.startHomePageActivity(WelcomeActivity.this);
                        finish();
                    }
                }else{
                    Toast.makeText(getBaseContext(),deviceBeanAjaxObjResult.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PHONE_STATE && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            handleDeviceBaseMessage();
        }
    }
}

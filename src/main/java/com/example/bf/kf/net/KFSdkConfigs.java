package com.example.bf.kf.net;


public interface KFSdkConfigs {
//    String URL_SNS_HOST = "http://120.78.199.210:8081//";
      String URL_SNS_HOST = "http://192.168.0.124:8081//";
    /**
     * 上传设备信息
     */
    String POST_DEVICE_MESSAGE_URL=URL_SNS_HOST+"api/sys_message/v1/pos_sys_message.json";

    /**
     * 获取品牌数据列表
     */
    String GET_LOOK_COSMETICS_LIST_URL=URL_SNS_HOST+"api/makeup_message/v1/get_makeup_brand_catalogue.json";
}
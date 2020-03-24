package com.example.bf.kf.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MacUtils {
    //获取本机的Mac地址
    public static String  getCurrentMac(Context context){
        String mac="02:00:00:00:00:00";
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mac=getMacDefault(context);
        }else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&Build.VERSION.SDK_INT<Build.VERSION_CODES.N){
            mac=getMacAddress();
        }else if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            mac=getMacFromHardware();
        }
        return mac;

    }

    /**
     * 当版本小于6.0
     * @param context
     * @return
     */
    private static  String getMacDefault(Context context){
        String mac = "02:00:00:00:00:00";
        if (context == null) {
            return mac;
        }

        WifiManager wifi = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        if (wifi == null) {
            return mac;
        }
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
        }
        if (info == null) {
            return null;
        }
        mac = info.getMacAddress();
        if (!StringUtils.isBlank(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }

    /**
     * 当版本 大于等于6.0 小于7.0
     */
    private static String getMacAddress(){
        String WifiAddress = "02:00:00:00:00:00";
        try {
            WifiAddress = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WifiAddress;
    }

    /**
     * 当版本 大于等于7.0
     */
    private static String getMacFromHardware() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    public static String getMacNo1(){
        String macOfEth0 = null;
        macOfEth0 = getMacAddressFromNetworkInterface("eth1");
        if(StringUtils.isBlank(macOfEth0)) {
            macOfEth0 = getEth0MacAddressFromFile();
        }
        if(StringUtils.isBlank(macOfEth0)) {
            macOfEth0 = "00:00:00:00:00:00";
        }
        return macOfEth0;
    }

    private static String getMacAddressFromNetworkInterface(String name){
        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName(name);
            if (networkInterface == null) {
                return null;
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (Exception e) {
            return null;
        }
        return macAddress;
    }

    private static String getEth0MacAddressFromFile(){
        String macSerial = null;

        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/eth0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            String str = "";
            for (; null != str;) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {

        }
        return macSerial;
    }

}
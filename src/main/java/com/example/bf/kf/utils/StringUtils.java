package com.example.bf.kf.utils;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;

import org.json.JSONArray;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {
    public static final String EMPTY = "";
    public static final String AMOUT_0_00 = "0.00";
    public static final String NUM_ZERO = "0";

    public static boolean isBlank(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNull(String s) {
        if(!isBlank(s)){
            return "null".equals(s);
        }
        return true;
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    public static boolean check(String s, String regionExpression) {
        if (isBlank(s)) {
            return false;
        }
        if (isBlank(regionExpression)) {
            return false;
        }
        Pattern p = Pattern.compile(regionExpression);
        return p.matcher(s).matches();
    }

    public static boolean isLetter(String s) {
        return check(s, "[a-zA-Z]+");
    }

    public static boolean isNumeric(String s) {
        return check(s, "[0-9]+");
    }

    public static boolean isInteger(String s) {
        return check(s, "^-?\\d+$");
    }


    public static boolean isPhoneCheck(String s) {
        return check(s, "^(\\d{3,4}[-]{0,1})?\\d{7,8}$");
    }


    public static boolean isMobile(String s) {
        return check(s, "1[0-9]{10}");
    }

    public static boolean isEmail(String s) {
        return check(s, "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
    }

    public static String parseDouble(double d){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(d);
    }

    public static String parseDouble2(double d){
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        return decimalFormat.format(d);
    }

    public static String replaceBlank(String s) {
        if (isBlank(s)) {
            return s;
        }
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        return p.matcher(s).replaceAll("");
    }

    /**
     * @param s
     * @return
     * @desc <pre>
     * 计算字符串长度，双字节字符长度为2，单字节字符串长度为1
     * </pre>
     * @author Erich Lee
     * @date Jul 16, 2013
     */
    public static int len(String s) {
        if (isBlank(s)) {
            return 0;
        }
        Pattern p = Pattern.compile("[^\\x00-\\xff]");
        return p.matcher(s).replaceAll("aa").length();
    }

    public static String[] split(String s, String split) {
        if (isBlank(s)) {
            return new String[0];
        }
        if (isBlank(split)) {
            return new String[]{s};
        }
        return s.split(split);
    }

    public static List<String> changeList(String[] array){
        List<String> list=new ArrayList<>();
        if(array!=null&&array.length>0){
            int length=array.length;
            for(int i=0;i<length;i++){
                list.add(array[i]);
            }
        }
        return list;
    }






    /**
     *
     * @param text 目标字符串
     * @param length 截取长度
     * @param encode 采用的编码方式
     * @return
     */
    public static String substring(String text, int length, String encode) {
        if (text == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : text.toCharArray()) {
                currentLength += String.valueOf(c).getBytes(encode).length;
                if (currentLength <= length) {
                    sb.append(c);
                } else {
                    break;
                }
            }
            return sb.toString();
        }catch (Exception e){
            return null;
        }
    }


    public static boolean equals(CharSequence a, CharSequence b) {
        if (a==null||b==null){
            return false;
        }
        int length;
        if ((length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean checkStrIsValid(String str) {
        if(isBlank(str)){
            return false;
        }
        if("null".equalsIgnoreCase(str) || "(null)".equalsIgnoreCase(str)){
            return false;
        }
        return true;
    }

    public static boolean isNotBlank(String... strs) {
        if(strs.length > 0){
            for (String str : strs) {
                if(isBlank(str)){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @author Erich Lee
     * @desc <pre>
     * 中文汉字模型，包含拼音和汉语拼音首字母
     * </pre>
     * @Date Mar 27, 2013
     */
    public static class ChineseModel implements Serializable, Comparable<ChineseModel> {
        private String original;

        private String first;

        private String pinyin;

        public ChineseModel(String original) {
            this(original, EMPTY, EMPTY);
        }

        public ChineseModel(String original, String first, String pinyin) {
            this.original = original;
            this.first = first;
            this.pinyin = pinyin;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getFirst() {
            return first;
        }

        public String getPinyin() {
            return pinyin;
        }

        public int compareTo(ChineseModel another) {
            if (another == null) {
                return 1;
            }
            int ret = first.compareToIgnoreCase(another.getFirst());
            if (ret == 0) {
                ret = pinyin.compareToIgnoreCase(another.getPinyin());
            }
            if (ret == 0) {
                ret = original.compareToIgnoreCase(another.getOriginal());
            }
            return ret;
        }

        /**
         * @return
         * @desc <pre>
         * 返回首字母大写
         * </pre>
         * @author Erich Lee
         * @date May 6, 2013
         */
        public String getFirstLetter() {
            if (StringUtils.isNotBlank(first)) {
                return first.toLowerCase(Locale.getDefault());
            }
            return StringUtils.EMPTY;
        }


        public static void main(String[] args) {
            String num = "66565214";
            System.out.println(isPhoneCheck(num));
        }
    }



    /**
     * 姓式的多音字转换
     * @param polyPhone
     * @return
     */
    private static String getPolyPhoneExchanged(String polyPhone){
        if(StringUtils.isBlank(polyPhone)){
            return polyPhone;
        }
        String firstLetter = polyPhone.substring(0,1);
        String ret = "";
        if(firstLetter.equals("曾")){
            ret = "z";
        }else if(firstLetter.equals("解")){
            ret = "x";
        } else if(firstLetter.equals("仇")){
            ret = "q";
        } else if(firstLetter.equals("朴")){
            ret = "p";
        }else if(firstLetter.equals("查")){
            ret = "z";
        }else if(firstLetter.equals("能")){
            ret = "n";
        }else if(firstLetter.equals("乐")){
            ret = "y";
        }else if(firstLetter.equals("单")){
            ret = "s";
        }
        if(StringUtils.isNotBlank(ret)){
            return ret+polyPhone.substring(1);
        }else {
            return polyPhone;
        }
    }

    // 清除掉回车换行
    public static String replaceRetrunStr(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;

    }

    public static String locationSpliteLen(String location) {
        if (isBlank(location)) {
            return EMPTY;
        }
        int maxLineLen = 20;
        int len = location.length();
        int col = len / maxLineLen;
        int lastLen = len % maxLineLen;
        if (lastLen > 0) {
            col++;
        }
        StringBuffer sbAddress = new StringBuffer();
        for (int i = 0; i < col; i++) {
            if (i == col - 1) {
                sbAddress.append(location.substring(i * maxLineLen));
            } else {
                sbAddress.append(location.substring(i * maxLineLen, (i + 1) * maxLineLen) + "\n");
            }

        }
        return sbAddress.toString();
    }

    /**
     * 获取已经选择了的用户信息:names和Ids
     *
     * @param mHasSelectUser 用户的ID和Name构成的Map
     */
    public static String getSelectUserFormations(Map<Long, String> mHasSelectUser) {
        String user_names = "";
        if (!mHasSelectUser.isEmpty()) {
            Iterator iter = mHasSelectUser.entrySet().iterator();
            StringBuffer mNameSb = new StringBuffer();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                String val = (String) entry.getValue();
                mNameSb.append(val + ",");

            }
            user_names = mNameSb.toString().substring(0, mNameSb.length() - 1);
        }
        return user_names;
    }

    /**
     * 将字符串转换为列表
     *
     * @param string
     * @return
     */
    public static List<String> converStringToList(String string) {
        List<String> lStrings = new ArrayList<>();
        if (isNotBlank(string)) {
            if (string.contains(",")) {
                String[] lSplit = string.split(",");
                for (int i= 0;i<lSplit.length;i++){
                    lStrings.add(lSplit[i]);
                }
            }
            return lStrings;
        }
        return lStrings;
    }


    /**
     * 将字符串转换为列表
     *
     * @param string
     * @return
     */
    public static List<String> converStringToList(String string,String symbol) {
        List<String> lStrings = new ArrayList<>();
        if (isNotBlank(string)) {
            if (string.contains(symbol)) {
                String[] lSplit = string.split(symbol);
                for (int i= 0;i<lSplit.length;i++){
                    lStrings.add(lSplit[i]);
                }
            }else{
                lStrings.add(string);
            }
            return lStrings;
        }
        return lStrings;
    }

    /**
     * 将字符串转换为列表
     *
     * @param string
     * @return
     */
    public static List<Long> converStringToLongs(String string) {
        List<Long> lStrings = new ArrayList<>();
        if (isNotBlank(string)) {
            if (string.contains(",")) {
                String[] lSplit = string.split(",");
                for (int i= 0;i<lSplit.length;i++){
                    String value=lSplit[i];
                    try{
                        lStrings.add(Long.parseLong(value));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            return lStrings;
        }
        return lStrings;
    }

    /**
     * 将字符串转换为列表
     *
     * @param string
     * @return
     */
    public static List<String> converStrToList(String string) {
        List<String> lStrings = new ArrayList<>();
        if (isNotBlank(string)) {
            if (string.contains(",")) {
                String[] lSplit = string.split(",");
                int length=lSplit.length;
                for (int i= 0;i<length;i++){
                    lStrings.add(lSplit[i]);
                }
            }else{
                lStrings.add(string);
            }
            return lStrings;
        }
        return lStrings;
    }


    enum ValueState{
        INT_TYPE,
        STRING_TYPE
    }

    /**
     * 将String切割成数组 cuttingValueshu 数据切割值
     * @return
     */
    public static  long[] splitsStringToLongArray(String mDataValues, String cuttingValue){

        if(isNotBlank(mDataValues)&&isNotBlank(cuttingValue)){
           try{
              if(mDataValues.contains(cuttingValue)){
                String[] mStrValue= mDataValues.split(cuttingValue);
                if(mStrValue!=null&mStrValue.length>0){
                   long[] mValueArray=new long[mStrValue.length];
                   for(int i=0;i<mStrValue.length;i++){
                     if(StringUtils.isNotBlank(mStrValue[i])){
                        mValueArray[i]= Long.parseLong(mStrValue[i]);
                      }
                   }
                   return mValueArray;
                }else{
                  return new long[]{};
                }
              } else{
                long[] mValueArray=new long[1];
                mValueArray[0]= Long.parseLong(mDataValues);
                  return mValueArray;
              }
            }catch (Exception e){
                e.printStackTrace();
           }
            return new long[]{};
        }else{
            return new long[]{};
         }
    }

    /**
     * 将对象集合中的某元素用分隔符拼揍起来
     * @param list  数据集
     * @param method   元素
     * @param separator 分割符
     * @param <T>
     * @return
     */
    public static <T> String convertListToString(List<T> list, String method, String separator){
        String s = "";
        try {
            for (T t : list) {
                Method m = t.getClass().getMethod(method);
                s += m.invoke(t)+separator;
            }
            if(s.length()>0){
                s=s.substring(0,s.length()-separator.length());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 替换StringBuffer中的某个字符串
     * @param sb
     * @param oldStr
     * @param newStr
     * @return
     */
    public static StringBuffer replaceAll(StringBuffer sb, String oldStr, String newStr) {
        if(sb == null || oldStr == null || newStr == null){
            return null;
        }
        int i = sb.indexOf(oldStr);
        int oldLen = oldStr.length();
        int newLen = newStr.length();
        while (i > -1) {
            sb.delete(i, i + oldLen);
            sb.insert(i, newStr);
            i = sb.indexOf(oldStr, i + newLen);
        }
        return sb;
    }

    /**
     * 检查数字是否有效
     * @param num
     * @return
     */
    public static boolean checkNumIsValid(String num){
        if(TextUtils.isEmpty(num)){
            return false;
        }
        return !(num.equals("0") || num.equals("0.0") || num.equals("0.00"));
    }

    /**
     * 假如有小数位 并且都等于0，则转换成Int 字符串回去
     * @param num
     * @return
     */
    public static String convertIntString(String num){
        if(TextUtils.isEmpty(num)){
            return "0";
        }
        if(num.endsWith(".0") || num.endsWith("0.00") || num.endsWith(".00")){
            return num.substring(0,num.indexOf('.'));
        }
        return num;
    }

    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 获得控件宽高
     * @param view
     * @param onViewListener
     */
    public static void textViewAdaption(final View view, final OnViewListener onViewListener){
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                    if(onViewListener!=null){
                        if(view.getWidth()>0&&view.getHeight()>0){
                            onViewListener.onView(view.getWidth(),view.getHeight());
                        }
                    }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    public interface OnViewListener {
        void onView(int width, int height);
    }


}

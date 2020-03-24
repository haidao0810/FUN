/**
 *
 */
package com.example.bf.kf.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Erich Lee
 * @desc <pre></pre>
 * @Date Mar 6, 2013
 */
public class CollectionUtils {

    public static <T> boolean isEmpty(Collection<T> col) {
        return col == null || col.size() == 0;
    }

    /**
     * 添加一个modle到集合
     * @param col
     * @param model
     * @param <T>
     */
    public static <T> void add(Collection<T> col, T model) {
        if (col==null){
            col=new ArrayList<>();
        }
        if (model!=null){
            col.add(model);
        }
    }

    /**
     * 添加一个model集合到集合
     * @param col
     * @param models
     * @param <T>
     */
    public static <T> void addAll(Collection<T> col, Collection<T> models) {
        if (col==null){
            col=new ArrayList<>();
        }
        if (models!=null){
            col.addAll(models);
        }
    }

    public static <T> boolean isNotEmpty(Collection<T> col) {
        return !isEmpty(col);
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    public static <T> List<T> copy(List<T> list) {
        if (isEmpty(list)) {
            return Collections.EMPTY_LIST;
        }
        List<T> copied = new ArrayList<T>();
        copied.addAll(list);
        return copied;
    }



    private static long getDateTime(String dt) {
        if (StringUtils.isBlank(dt)) {
            return 0L;
        }
        long ms = 0L;
        Date d;
        try {
            SimpleDateFormat SDF = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
            SDF.setTimeZone(TimeZone.getTimeZone("GMT"));
            d = SDF.parse(dt);
            if (d != null) {
                ms = d.getTime();
            }
        } catch (java.text.ParseException e) {
        }
        return ms;
    }



    public static int getListCount(List pictures) {
        if (isEmpty(pictures)) return 0;
        return pictures.size();
    }

    public static boolean isEmpty(String[] texts) {
        return !(texts != null && texts.length > 0) ;
    }


//    public static Set<Integer> getOtherManagerSet(MyOrgonization org) {
//        Set<Integer> set = new HashSet<Integer>();
//        if (StringUtils.isNotBlank(org.getOtherManager())) {
//            String[] uds = org.getOtherManager().split(",");
//            for (int i = 0; i < uds.length; i++) {
//                set.add(Integer.parseInt(uds[i]));
//            }
//        } else {
//            if (StringUtils.isNotBlank(org.getOtherManager())) {
//                if (org.getOtherManager().contains(",")) {
//                    String ids[] = org.getOtherManager().split(",");
//                    if (null != ids) {
//                        for (int i = 0; i < ids.length; i++) {
//                            set.add(IntegerUtils.parse(ids[i]));
//                        }
//                    }
//                } else {
//                    set.add(IntegerUtils.parse(org.getOtherManager()));
//                }
//            }
//        }
//        return set;
//    }


}

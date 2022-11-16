package com.example.backend.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @param <S> 源对象
 * @param <T> 目标对象类
 */
@Component
public class MyBeanUtils<S, T> {

    /**
     * 源对象属性绝对覆盖到目标对象。
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        // 判断source是否为空
        if (source == null)
            return null;
        // 判断targetClass是否为空
        if (targetClass == null)
            return null;
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;//向下转型
        } catch (Exception e) {
            System.out.println("pojo copyProperties failed! source:"+source.toString());
            System.out.println("--------------------------------------------------------------");
            System.out.println("targetClass:"+targetClass.getName());
            return null;
        }
    }

    /**
     * 源对象上属性为null的，该属性不对目标进行覆盖。
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T copyNotNullProperties(Object source, Class<T> targetClass) {
        // 判断source是否为空
        if (source == null)
            return null;
        // 判断targetClass是否为空
        if (targetClass == null)
            return null;
        try {
            T target = targetClass.newInstance();
            String[] ignoreProperties = getNullPropertyNames(source);
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;//向下转型
        } catch (Exception e) {
            System.out.println("pojo copyNotNullProperties failed! source:"+source.toString());
            System.out.println("--------------------------------------------------------------");
            System.out.println("targetClass:"+targetClass.getName());
            return null;
        }
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNamesSet = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object sourceValue = src.getPropertyValue(pd.getName());
            if (sourceValue == null) {
                emptyNamesSet.add(pd.getName());
            }
        }

        String[] result = new String[emptyNamesSet.size()];
        return emptyNamesSet.toArray(result);
    }

    public static String strToASCII(String str) {
        StringBuilder sb = new StringBuilder();
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            int i1 = Integer.valueOf(ch[i]).intValue();
//            String s = Integer.toHexString(i1);
            sb.append(i1);
        }
        return sb.toString();

    }

    public static void copyNotNullProperties(Object source, Object target) {
        String[] ignoreProperties = getNullPropertyNames(source);
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

}

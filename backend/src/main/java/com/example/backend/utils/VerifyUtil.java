package com.example.backend.utils;

import com.example.backend.utils.securityUtil;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class VerifyUtil {

    private static final String REGEX_DOMAINS_NAME ="^[\u4E00-\u9FA5A-Za-z0-9_]{2,20}$";
    private static final String REGEX_USERNAME="^[A-Za-z0-9_]{3,10}$";
    private static final String REGEX_NAME="^[\u4E00-\u9FA50-9._·]{2,10}$";
    private static final String REGEX_ALIAS="^[\u4E00-\u9FA50-9._·A-Za-z]{2,20}$";
    private static final String REGEX_QQ="[1-9][0-9]{4,}";
    private static final String REGEX_FLAG2="^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{4,8}$";
    private static final String REGEX_EMAIL="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String REGEX_CODE="^[0-9A-Za-z]{6}$";
    private static final String REGEX_PHONE="^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";
    private static final String REGEX_STUDENT_NUMBER="^(20[1-2][0-9])(\\d{4})([0-3][0-9])([01]\\d{2})$";
    private static final String REGEX_IP="(\\d{1,3})([.])(\\d{1,3})([.])(\\d{1,3})([.])(\\d{1,3})";
    private static final String REGEX_FILE_TYPE="^(.*?)(([.])(.*))$";
    private static final String REGEX_UA="(?i)(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)";
    private static final Pattern REGEX_UA_PATTERN=Pattern.compile(REGEX_UA);
    private static final String REGEX_FLAG3="^([a-zA-Z0-9_]{4,16})([{])((?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9_]{4,32})([}])$";
    private static final String REGEX_FLAG="^(lr_studio)([{])((?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9_]{4,32})([}])$";
    public static void main(String[] args){
        /*
        Double score=999999*Math.pow(10.,-6);
        BigDecimal test=new BigDecimal(Double.parseDouble(String.format("%.16f",score+(Math.pow(10,10)-(int)(1659537578861L /1000))*Math.pow(10.,-16))));
        System.out.println(test);
        System.out.println(Double.parseDouble(String.format("%.16f",score+(Math.pow(10,10)-(int)(1659537578861L /1000))*Math.pow(10.,-16))));
        */
        System.out.println(isValidUsername("sadasd"));
    }

    public static Boolean isMobile(String str){
        if (str==null)return false;
        return REGEX_UA_PATTERN.matcher(str).find();
    }

    public static Boolean isValidAlias(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_ALIAS,str);
    }

    public static String getSuffix(String str){
        if (str==null) return "";
        String type=str.replaceAll(REGEX_FILE_TYPE,"$2");
        if (type.equals(str))return "";
        else return type;
    }

    public static Boolean isValidIp(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_IP,str);
    }

    public static Boolean isValidStudentNumber(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_STUDENT_NUMBER,str);
    }

    public static Boolean isValidPhone(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_PHONE,str);
    }

    public static Boolean isValidCode(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_CODE,str);
    }

    public static Boolean isValidDomainsName(String str){
        if (str==null)return false;
        return Pattern.matches(REGEX_DOMAINS_NAME,str);
    }

    public static Boolean isValidUsername(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_USERNAME,str);
    }

    public static Boolean isValidName(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_NAME,str);
    }

    public static Boolean isValidQQ(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_QQ,str);
    }


    public static Boolean isValidFlag(String str){
        if (str==null) return false;
        return Pattern.matches(REGEX_FLAG,str);
    }

    public static Boolean isValidEmail(String email){
        if (email==null) return false;
        return Pattern.matches(REGEX_EMAIL,email);
    }
    public static Boolean isValidPassword(String str){
        return securityUtil.evalPassword(str);
    }
}


package com.innovandoapps.library.kernel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by windows 8.1 on 25/02/2018.
 */

public class EncrytacionUtils {

    public static String encriptarMD5(String cadena){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[]b=md.digest(cadena.getBytes());
            int size=b.length;
            StringBuffer h=new StringBuffer(size);
            for(int i=0;i<size;i++){
                int u=b[i] & 255;
                if(u<16){
                    h.append("0"+Integer.toHexString(u));
                }else{
                    h.append(Integer.toHexString(u));
                }
            }
            return h.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

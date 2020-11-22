package com.web.util;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.util
 * @ClassName: UrlStringUtil
 * @Author: gwl
 * @Description:  访问路径截取
 * @Date: 2020/8/19 15:43
 * @Version: 1.0
 */


public class UrlStringUtil {

    public static  String Urlformatting(String url){
         StringBuffer str = new StringBuffer();
         String[] split = url.split("/");
         for (int i=0;i<split.length;i++) {
             if (i>0 && i<3){
                 str.append("/"+split[i]);
             }
         }
         return str.toString();
    }

    public static  String AppUrlformatting(String url){
        StringBuffer str = new StringBuffer();
        String[] split = url.split("/");
        for (int i=0;i<split.length;i++) {
            if (i>0 && i<2){
                str.append("/"+split[i]);
            }
        }
        return str.toString();
    }

    public static  String AppUrlVisit(String url){
        StringBuffer str = new StringBuffer();
        String[] split = url.split("/");
        for (int i=0;i<split.length;i++) {
            if (i>0 && i<4){
                str.append("/"+split[i]);
            }
        }
        return str.toString();
    }

}

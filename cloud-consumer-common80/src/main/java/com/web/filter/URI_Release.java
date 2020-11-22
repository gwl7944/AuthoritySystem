package com.web.filter;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.filter
 * @ClassName: URI_Release
 * @Author: gwl
 * @Description:  app部分特殊过滤请求
 * @Date: 2020/9/7 16:14
 * @Version: 1.0
 */


public class URI_Release {

    private static final String[] str = {"/app/LoginController/login",
                                         "/app/LoginController/CheckLoginname",
                                         "/app/LoginController/StaffRegister",
                                         "/app/ChangeAddress/getLongitudeLatitude",
                                         "/app/getId",
                                         "/app/StaffBusinessController/PushOrder",
                                         "/app/StaffBusinessController/getRiderAddress",
                                         "/app/StaffBusinessController/getRider",
                                         "/app/orderPayFailInfoSubmit",
                                         "/app/orderPayCancel",
                                         "/app/getStaffWorkTime",
                                         "/app/orderPayFailInfoSubmit",
                                         "/CommodityController/getActivityPFS",
                                         "/UserController/addLog",
                                         "/CommodityController/deletePic",
                                         "/CommodityController/deleteCommPic"};

    public static boolean checkURI(String url){
       for (String s:str){
           if (s.equals(url)){
               return true;
           }
       }
       return false;
    }

}

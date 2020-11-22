package com.web.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.sms
 * @ClassName: SendSms
 * @Author: gwl
 * @Description:  短信验证码
 * @Date: 2020/10/21 9:53
 * @Version: 1.0
 */


public class SendSms {
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou",    // 地域ID
                "LTAI4GFR9SLSirMpwEgoGrDo",    // RAM账号的AccessKey ID
                "Tsr5ADYk1c2ggRjVB4PO0yj7tq8H1u");  // RAM账号AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建API请求并设置参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18435205635");
        request.putQueryParameter("SignName", "智能送水平台");
        request.putQueryParameter("TemplateCode", "SMS_205065094");
        request.putQueryParameter("TemplateParam", "{code:123456}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}

package com.web.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayOpenOperationOpenbizmockBizQueryModel;
import com.alipay.api.request.AlipayOpenOperationOpenbizmockBizQueryRequest;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenOperationOpenbizmockBizQueryResponse;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.alipay
 * @ClassName: Alipay
 * @Author: gwl
 * @Description:
 * @Date: 2020/10/21 17:11
 * @Version: 1.0
 */


public class Alipay {

    public static void main(String[] args) throws AlipayApiException {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do",
                                                               "2016102600765987",
                                                            "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCMSnNZzUE5eMeZf6bHrQUUNQrgq1D9DQGKUoEJGZbV8pTrOKty42Y3usriuynhinj7e4Aii23dDS1PBMGs8egO3/+iSPhEaFErP93kxF1vJ5ZQsplzQjuO89zd/swKgcNjjJeaxOPQQvNiVpyPJzKJ72UKj73iBVvBUUvGEa7kqJGI8waQJLuhZslQknHXw9PkXhmbvBpSHtmx8aLfssmdZFUTW5L/DZU+WtbCyG/+tZjYwZdsmbv4j0O41K5m3Jy5xgX9sNKjcZ+fdsRUakFJ5F8PfaE7m4JVd26a7RvMGAFk92YT5/GHDM5kQaX1kr/C3ez9fbPDiYHZoXO7YpRjAgMBAAECggEAW2WFkNOv0ollQBNxzFRkIq4L0CLMOI/omPz3I76QDgJkEnpw+AA978C5vMzClDVqbCwhZl//yytygTBQHSPcUxSLrmZrl+V+O/b30Lo9RNU9kIVKg1R7CL7G/bmppI61hH1rp2DuKhf0B/T1Oi4HNuXHpgHPaxiilmcoPnn2vvA8MPTktKNHjrwLnuF8b4QrztP8OFiW8ZIXPDlYZa5MoQUP02EUcr5V2zvcV6TtFbG4L0YRfo51k5ni+lqNgG49ajDAmgADGrCIVr8MeNntDbHcE/lXSxsjZ/du4cOr+jzvEJLbL9So4yXhyqxnyvmcJww9JLmHQtD5E2SSnCyrwQKBgQC+1G6fupVxyi+ZplLhOquoPWD+BTMAsjeeq+LsGYFphALQ5SK9QukuejiFz6FrpAD7UHZRqcWnVJ+wk+kpt5kEVRgDV0f8Bo9UOYap6TzA1WddDpuqA0b3EdMmpFzBRZ4Uoqc4ohvVkG9yLn30vfaj87Nr4o1JsfSpAEWhimTX1wKBgQC8M5NJFeIywagiaJlKk2bEvrvOblu6QNOPKv0VFUxd8diQvsoIOKXcXzN9rpmU9YzdCOdy0oORMsjsAG6QqJL+XlvsCoNVX5u3hSsJDiGeVzwlUfZhS+Le/vvgNArTs9j818p4i3XofvHApk5NWoV5MnSWlrO2T2jWhT0WD2smVQKBgHqdiM89VlUdMuSpSEvMJQwfFDlkIddqq+5aR56o7MxdtGmBMYcmfSoOAk+IkBHX+YGBu+TRD11rNIiDIahafgmYm6tZ47j+hg36QJrgcNb+yEG05zSgknKik2ga6DaPgvmDYas0Dmp1OgbF9MzTWq0SI9FIarxVif0lXZhkklNVAn8T2VyvWJ+CuuoAtwdz1hgAqB0mqH0EJ9IoR2Bp2NU1E0zD91tXgqH7nA7SMpgAHeW9ajL+6TT3G2QVf/YpS541Nb58Jp+zYWIK+QCQ8+yXxpuRVBOoL1i+LCZkfxilO1KnWBsCm7LT0o+swd2ASwvIvImPWFWb5MQp2+OVcArdAoGBAJ/l8v9vMbV16OkgdaFNup5gF+N6jezqJKNTX7ti4AK9w6r/DD1yqPCGPCKOmEkC43p//SfDE1/hYKwDKpmKG9UPYV4UU0zpPzK3GTTub0FxiT3Ew+ehYVdhA1ZDSoh7TsdW/jR+vhpgh0TZARqcQPYcy76GB/TFlkPu+Xk2P4c3",
                                                              "json",
                                                             "utf-8",
                                                      "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtDAa5kC4d9DFYVG6t5kMhw/QvkzCxDRp4F2DpIr7fpaVFkUIG6gqvVUduNDNMc9P3kdde5Jw5CTB+fnE5AsMM8rHos0NdYg7dr6WdN2q/bq+bkg+4HV8ttelFOfIdU2vds7qrlhJ/bVztpi2rhhA5WAuuTPq9dY7D4ZylEMJByAbGuQNWH29WDg5kGZ7Ch7SpqEuw8iAOvmqlmDloR/d4xPPBjyqqZj41mBL/pThfxo95WXTBrku+fv5IyCXMmc2mb1q8BJZx5zgzQtFewcNhH9J2zOpFIezsDT3QvbRwMtOfbkHEcqHliVpMeNtDRMflzH4yYGHIDqsHv7ePNOyEQIDAQAB",
                                                           "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数
        //此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        AlipayOpenPublicTemplateMessageIndustryModifyResponse response = alipayClient.execute(request);
        //调用成功，则处理业务逻辑
        if(response.isSuccess()){
            //.....
        }
    }



}

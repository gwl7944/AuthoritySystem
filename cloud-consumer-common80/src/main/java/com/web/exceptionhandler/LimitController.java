package com.web.exceptionhandler;



import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.web.pojo.ResultCode;
import com.web.pojo.ResultData;
import com.web.pojo.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.exceptionhandler
 * @ClassName: LimitController
 * @Author: gwl
 * @Description:   限流兜底方法处理
 * @Date: 2020/8/7 10:43
 * @Version: 1.0
 */

public class LimitController {

    //限流降级处理
    public static JSON handlerException(@ModelAttribute User user, HttpServletRequest request,BlockException exception){

        return ResultData.getResponseData(null,ResultCode.CURRENT_LIMITING);
    }

    public static JSON GetAllRoles(BlockException exception){
        return ResultData.getResponseData(null,ResultCode.CURRENT_LIMITING);
    }

}

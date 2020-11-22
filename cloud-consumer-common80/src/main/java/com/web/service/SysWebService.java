package com.web.service;


import com.alibaba.fastjson.JSON;
import com.web.OpenFeign.ParamModel;
import com.web.exceptionhandler.FallbackService;
import com.web.pojo.*;
import com.web.util.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.service
 * @ClassName: SysWebService
 * @Author: gwl
 * @Description:
 * @Date: 2020/7/31 15:52
 * @Version: 1.0
 */
@Component
@FeignClient(value = "cloud-provider-web",fallback = FallbackService.class)
public interface SysWebService {

   
}

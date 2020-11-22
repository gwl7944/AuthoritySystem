package com.web.exceptionhandler;

import com.alibaba.fastjson.JSON;
import com.web.OpenFeign.ParamModel;
import com.web.pojo.*;
import com.web.service.SysWebService;
import com.web.util.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.exceptionhandler
 * @ClassName: FallbackServiceImpl
 * @Author: gwl
 * @Description:
 * @Date: 2020/8/7 15:53
 * @Version: 1.0
 */

@Component
@Slf4j
public class FallbackService implements SysWebService {

}

package com.web.OpenFeign;

import java.lang.annotation.*;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.OpenFeign
 * @ClassName: ParamModel
 * @Author: gwl
 * @Description:
 * @Date: 2020/8/5 11:43
 * @Version: 1.0
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamModel {
    String value() default "";
}

package com.web.OpenFeign;

import com.alibaba.fastjson.JSON;
import feign.Param;
import lombok.extern.slf4j.Slf4j;
import java.util.Map;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.OpenFeign
 * @ClassName: ModelExpander
 * @Author: gwl
 * @Description:
 * @Date: 2020/8/5 11:44
 * @Version: 1.0
 */

@Slf4j
public class ModelExpander implements Param.Expander {

    @Override
    public String expand(Object o) {
        String objectJson = JSON.toJSONString(o);
        return objectJson;
    }

    @Override
    public String expandWithName(Object value, String name) {
        String valueExpand = null;

        if(value != null){
            if(name != null) {
                try {
                    Map<String, Object> jsonMap = (Map<String, Object>)JSON.toJSON(value);

                    Object getValue = jsonMap.get(name);
                    if(getValue != null){
                        valueExpand = getValue.toString();
                    }
                } catch (Exception e) {
                    System.err.println("GET VALUE ERROR: \n"+e);
                }
            }else {
                valueExpand = value.toString();
            }
        }

        return valueExpand;
    }
}

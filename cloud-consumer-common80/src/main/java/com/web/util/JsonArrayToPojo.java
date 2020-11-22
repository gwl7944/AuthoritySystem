package com.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.web.pojo.Staff_dynamic_detailed;
import com.web.pojo.Staff_warehousing_detailed;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.util
 * @ClassName: JsonArrayToPojo
 * @Author: gwl
 * @Description:  json数组转实体
 * @Date: 2020/9/4 15:38
 * @Version: 1.0
 */


public class JsonArrayToPojo {

    /**
     * json数组转Staff_warehousing_detailed
     * */
    public static List<Staff_warehousing_detailed> getStaff_warehousing_detailed(String ArrayStr){
        System.out.println("ArrayStr---->"+ArrayStr);
        String replace = ArrayStr.replace("[[", " ");
        String replace1 = replace.replace("]]", " ");
        String replace2 = replace1.replace("},{", "};{");
        replace1.trim();
        String[] split = replace2.split(";");
        List<Staff_warehousing_detailed> list = new ArrayList<>();
        for (String str:split){
            System.out.println("replace1---->"+str);
            JSONObject jsonObject = JSONObject.parseObject(str);
            Staff_warehousing_detailed staffWarehousingDetailed = new Staff_warehousing_detailed();
            Integer swdd_commodity_id = jsonObject.getInteger("swdd_commodity_id");
            staffWarehousingDetailed.setSwdd_commodity_id(swdd_commodity_id);
            Integer swdd_barrels_empty = jsonObject.getInteger("swdd_barrels_empty");
            staffWarehousingDetailed.setSwdd_barrels_empty(swdd_barrels_empty);
            Integer swdd_id = jsonObject.getInteger("swdd_id");
            staffWarehousingDetailed.setSwdd_id(swdd_id);
            Integer swdd_number = jsonObject.getInteger("swdd_number");
            staffWarehousingDetailed.setSwdd_number(swdd_number);
            Integer swdd_staff_warehousing_id = jsonObject.getInteger("swdd_staff_warehousing_id");
            staffWarehousingDetailed.setSwdd_staff_warehousing_id(swdd_staff_warehousing_id);
            list.add(staffWarehousingDetailed);
        }
        return list;

    }

    /**
     * json字符串转getStaff_dynamic_detailed
     * */
    public static Staff_dynamic_detailed getStaff_dynamic_detailed(String string){
        JSONObject jsonObject = JSON.parseObject(string);
        Staff_dynamic_detailed staffDynamicDetailed = new Staff_dynamic_detailed();
        staffDynamicDetailed.setSddd_commodity_id(jsonObject.getInteger("sddd_commodity_id"));
        staffDynamicDetailed.setSddd_empty_barrels(jsonObject.getInteger("sddd_empty_barrels"));
        staffDynamicDetailed.setSddd_margin(jsonObject.getInteger("sddd_margin"));
        staffDynamicDetailed.setSddd_charged_barrels(jsonObject.getInteger("sddd_charged_barrels"));
        staffDynamicDetailed.setSddd_id(jsonObject.getString("sddd_id"));
        staffDynamicDetailed.setSddd_staff_dynamic_id(jsonObject.getString("sddd_staff_dynamic_id"));
        return  staffDynamicDetailed;
    }

}

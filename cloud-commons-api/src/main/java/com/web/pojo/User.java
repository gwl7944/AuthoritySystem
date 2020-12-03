package com.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ProjectName: AuthoritySystem
 * @Package: com.web.pojo
 * @ClassName: User
 * @Author: gwl
 * @Description:
 * @Date: 2020/11/23 16:17
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    

    private Integer code;
    private String login_name;
    private String login_pwssaord;
    private String name;
    private String birthday;
    private String phone;
    private String reg_time;
}

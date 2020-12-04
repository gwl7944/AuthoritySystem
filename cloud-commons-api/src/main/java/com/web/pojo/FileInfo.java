package com.web.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: AuthoritySystem
 * @Package: com.web.pojo
 * @ClassName: FileInfo
 * @Author: gwl
 * @Description:
 * @Date: 2020/12/4 14:43
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    private Integer id;
    private String filename;  //文件名称
    private String version;   //版本号
    private String uploadtime; //上传时间
    private Integer downloadnum; //下载次数
    private String suffix;  //文件后缀
    private Integer del;
}

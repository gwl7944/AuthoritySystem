package com.file.service;

import com.web.pojo.FileInfo;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * @ProjectName: AuthoritySystem
 * @Package: com.file.service
 * @ClassName: FileDataService
 * @Author: gwl
 * @Description:
 * @Date: 2020/12/4 15:12
 * @Version: 1.0
 */
public interface FileDataService {

    /**
     * 记录文件上传信息
     * */
    int insertFileInfo(FileInfo fileInfo);

    /**
     * 修改信息
     * */
    int updateFileInfo(FileInfo fileInfo);

    /**
     * 查看信息
     * */
    List<FileInfo> findAllFileInfo();

    /**
     * 查询单个文件信息
     * */
    FileInfo findFileInfoById(Integer id);
}

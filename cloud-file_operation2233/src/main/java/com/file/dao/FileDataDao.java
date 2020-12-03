package com.file.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: AuthoritySystem
 * @Package: com.file.dao
 * @ClassName: FileDataDao
 * @Author: gwl
 * @Description:
 * @Date: 2020/12/3 17:46
 * @Version: 1.0
 */
@Mapper
public interface FileDataDao {

    @Insert("insert into filedata (filename,version,uploadtime,downloadnum,del) " +
            "values(#{})")
    int insertFileDataInfo();
}

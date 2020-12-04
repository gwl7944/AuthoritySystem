package com.file.dao;

import com.web.pojo.FileInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    /**
     * 记录文件上传信息
     * */
    @Insert("insert into filedata (filename,version,uploadtime,downloadnum,del,suffix) " +
            "values(#{filename},#{version},#{uploadtime},#{downloadnum},1,#{suffix})")
    int insertFileInfo(FileInfo fileInfo);

    /**
     * 修改信息
     * */
    @Update("update filedata set filename=#{filename},version=#{version},uploadtime=#{uploadtime},downloadnum=#{downloadnum},del=#{del},suffix=#{suffix}" +
            "where id=#{id}")
    int updateFileInfo(FileInfo fileInfo);

    /**
     * 查看信息
     * */
    @Select("select * from filedata where del=1")
    List<FileInfo> findAllFileInfo();


    /**
     * 查询单个文件信息
     * */
    @Select("select * from filedata where id=#{id}")
    FileInfo findFileInfoById(Integer id);

}

package com.file.service.impl;

import com.file.dao.FileDataDao;
import com.file.service.FileDataService;
import com.web.pojo.FileInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: AuthoritySystem
 * @Package: com.file.service.impl
 * @ClassName: FileDataServiceImpl
 * @Author: gwl
 * @Description:
 * @Date: 2020/12/4 15:14
 * @Version: 1.0
 */

@Service
public class FileDataServiceImpl implements FileDataService {

    @Resource
    FileDataDao fileDataDao;

    @Override
    public int insertFileInfo(FileInfo fileInfo) {
        return fileDataDao.insertFileInfo(fileInfo);
    }

    @Override
    public int updateFileInfo(FileInfo fileInfo) {
        return fileDataDao.updateFileInfo(fileInfo);
    }

    @Override
    public List<FileInfo> findAllFileInfo() {
        return fileDataDao.findAllFileInfo();
    }

    @Override
    public FileInfo findFileInfoById(Integer id) {
        return fileDataDao.findFileInfoById(id);
    }
}

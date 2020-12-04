package com.file.controller;

import com.alibaba.fastjson.JSON;
import com.file.service.FileDataService;
import com.web.pojo.FileInfo;
import com.web.pojo.ResultCode;
import com.web.pojo.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ProjectName: AuthoritySystem
 * @Package: com.file.controller
 * @ClassName: FileDataController
 * @Author: gwl
 * @Description:
 * @Date: 2020/12/3 9:25
 * @Version: 1.0
 */

@RestController
@Slf4j
public class FileDataController {


    @Resource
    FileDataService fileDataService;


    /**
     * 查看所有文件信息
     * */
    @GetMapping("/FileDataController/findFileInfo")
    public JSON findFileInfo(){
        List<FileInfo> allFileInfo = fileDataService.findAllFileInfo();
        return ResultData.getResponseData(allFileInfo,ResultCode.QUERY_SUCCESS);
    }


    /**
     * 修改文件信息
     * */
    @PostMapping("/FileDataController/updateFileInfo")
    public JSON updateFileInfo(@ModelAttribute FileInfo fileInfo){
        int i = fileDataService.updateFileInfo(fileInfo);
        if (i>0){
            return ResultData.getResponseData(null,ResultCode.UPDATE_SUCCESS);
        }else {
            return ResultData.getResponseData(null,ResultCode.UPDATE_ERROR);
        }
    }

/**-------------------------------------------服务器本地------------------------------------------------------*/
    /**
     * 文件上传
     * */
    @PostMapping("/FileDataController/upload")
    @ResponseBody
    public JSON upload(@RequestParam("file") MultipartFile file,@RequestParam("filename") String filename,@RequestParam("version") String version){
        System.out.println("文件上传。。。。。");
        System.out.println("filename--"+filename);
        System.out.println("version--"+version);
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        // 获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路径
        String filePath = "C:/upload/";
        // 文件重命名，防止重复
        String newfileName = filePath + filename+version + suffixName;
        // 文件对象
        File dest = new File(newfileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilename(filename);
            fileInfo.setDownloadnum(0);
            fileInfo.setUploadtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            fileInfo.setVersion(version);
            fileInfo.setSuffix(suffixName);
            int i = fileDataService.insertFileInfo(fileInfo);
            if (i>0){
                return ResultData.getResponseData(null,ResultCode.FILE_SUCCESS);
            }else {
                return ResultData.getResponseData("信息保存失败！！",ResultCode.FILE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.getResponseData(null,ResultCode.FILE_ERROR);
        }

    }


    /**
     * 文件下载
     * */
    @GetMapping("/FileDataController/download/{id}")
    public void download(@PathVariable("id") Integer id, HttpServletResponse response) throws Exception {
        FileInfo fileInfoById = fileDataService.findFileInfoById(id);
        String filename = fileInfoById.getFilename()+fileInfoById.getVersion()+fileInfoById.getSuffix();
        System.out.println("filename----"+filename);
        // 文件地址，真实环境是存放在数据库中的
        File file = new File("C:\\upload\\"+filename);
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName="+filename);
        fileInfoById.setDownloadnum(fileInfoById.getDownloadnum()+1);
        int i = fileDataService.updateFileInfo(fileInfoById);
        if (i>0){
            log.info("下载次数：--"+fileInfoById);
        }else {
            log.info("下载次数变更失败！！");
        }
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }

    /**-------------------------------------------服务器本地------------------------------------------------------*/




}

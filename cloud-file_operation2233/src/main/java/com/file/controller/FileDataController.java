package com.file.controller;

import com.alibaba.fastjson.JSON;
import com.web.pojo.ResultCode;
import com.web.pojo.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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
        //String fileName = file.getOriginalFilename();
        // 获取后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件保存路径
        String filePath = "e:/upload/";
        // 文件重命名，防止重复
        //fileName = filePath + UUID.randomUUID() + fileName;
        // 文件对象
        File dest = new File(filename+version);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);

            return ResultData.getResponseData(null,ResultCode.FILE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.getResponseData(null,ResultCode.FILE_ERROR);
    }


    /**
     * 文件下载
     * */
    @GetMapping("/FileDataController/download")
    public void download(HttpServletResponse response) throws Exception {
        // 文件地址，真实环境是存放在数据库中的
        File file = new File("E:\\upload\\60c1c568-61ce-4ddd-9fbe-e80c67032158app-release.apk");
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + "60c1c568-61ce-4ddd-9fbe-e80c67032158app-release.apk");
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


    @GetMapping("/FileDataController/test/{vv}")
    public JSON test(@PathVariable("vv") String vv){
        System.out.println("value---"+vv);
        return  ResultData.getResponseData(vv,ResultCode.SYS_SUCCESS);
    }

}

package com.web.util;

import com.alibaba.nacos.client.utils.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.util
 * @ClassName: FastDFSUtils
 * @Author: gwl
 * @Description:
 * @Date: 2020/9/27 9:39
 * @Version: 1.0
 */


public class FastDFSUtils {

    private static StorageClient client1;

    static {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();
            client1 = new StorageClient(trackerServer,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static  String upload(MultipartFile file) {
        String oldName = file.getOriginalFilename();
        try {
            String[] strings = client1.upload_file(file.getBytes(), oldName.substring(oldName.lastIndexOf(".") + 1), null);
            String fileName="";
            for (String str:strings){
                //System.out.println("Name:----"+str);
                fileName+="/"+str;
            }
            return fileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     * @param group 组名 如：group1
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败,0成功
     */
    public static Integer delete_file(String group ,String storagePath){
        int result=-1;
        try {
            result = client1.delete_file(group, storagePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return  result;
    }

}

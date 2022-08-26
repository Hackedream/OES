package com.wakuwaku.oes6.controller;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class UploadController {
    private final static String utf8="utf-8";
    @RequestMapping("/upload")
    @ResponseBody
    public void upload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //分片
        response.setCharacterEncoding(utf8);
        Integer schunk =null;//当前分片数
        //总分片数
        Integer schunks=null;
        //文件的名字
        String name =null;
        //文件的存储目录
        String uploadPath="";
        //文件流
        BufferedOutputStream os =null;
        try {
            //处理接受的文件
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024);//设置缓冲区
            factory.setRepository(new File(uploadPath));//设置临时目录
            //解析传过来的文件
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(51*10241*10241*10241);//设置单个文件的大小
            upload.setSizeMax(101*10241*10241*10241);//设置总文件的大小
            List<FileItem> items = upload.parseRequest((RequestContext) request); //得到上传的所有文件信息
            for (FileItem item : items) {
                //判断是否为文件对象，如果true表示为是表单域，如果为false表示为文件对象
                if (item.isFormField()){
                    //如果为表单域，则从提交的表单中获取分片数
                    if ("chunk".equals(item.getFieldName())){
                        schunk =Integer.parseInt(item.getString(utf8));
                    }
//                        分片总数
                    if ("chunks".equals(item.getFieldName())){
                        schunks =Integer.parseInt(item.getString(utf8));
                    }
                    // 名称
                    if ("name".equals(item.getFieldName())){
                        name =item.getString(utf8);
                    }
                }
            }


            for (FileItem item : items) {
                //判断是否为文件对象，如果true表示为是表单域，如果为false表示为文件对象
                if (!item.isFormField()){
                    String temFileName=name;
                    if (name!=null){
                        if (schunk!=null){
                            temFileName= schunk+"_"+name;
                        }
                        // 传入文件
                        File temFile = new File(uploadPath, temFileName);
                        //断点续传
                        if (!temFile.exists()){
                            item.write(temFile);
                        }
                    }
                }
            }

            //文件合并
            //如果分片不等于null并且 总分片与最后一个分片相等，就证明所有分片成功
            if (schunk!=null && schunk.intValue() == schunks-1){
                File tempFile = new File(uploadPath,name);
                os=new BufferedOutputStream(new FileOutputStream(tempFile));
                for (int i=0 ;i<schunks;i++){
                    File file = new File(uploadPath,i+"_"+name);
                    while (file.exists()){
                        Thread.sleep(100);
                    }
                    //合并
                    byte[] bytes= FileUtils.readFileToByteArray(file);
                    os.write(bytes);
                    os.flush();
                    //临时文件删除
                    file.delete();
                }
                os.flush();
            }
            response.getWriter().write("上传成功");

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os!=null){
                    //关闭流
                    os.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}

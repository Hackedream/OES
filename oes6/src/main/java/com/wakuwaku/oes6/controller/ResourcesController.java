package com.wakuwaku.oes6.controller;

import com.wakuwaku.oes6.entity.Audio;
import com.wakuwaku.oes6.entity.Photo;
import com.wakuwaku.oes6.entity.Video;
import com.wakuwaku.oes6.utils.result.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/resource")
public class ResourcesController {

    private String file_address;

    /**
     * 接收上传的课程视频
     *
     * @param file
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/saveLessonVideo")
    @ResponseBody
    public R saveLessonVideo(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Video video=new Video();
        if (!file.isEmpty()) {
            //存放地址
            file_address = "H:/workspace/oes_resource/lesson_video/";
            String path = file_address;
            System.out.println("path"+path);
            //如果父文件夹不存在 则创建文件夹 文件夹为path,视频名字file.getOriginalFilename()
            File filepath = new File(path, file.getOriginalFilename());
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            File fi = new File(path + File.separator + file.getOriginalFilename());
            //下载到本地
            file.transferTo(fi);
            //获取绝对路径
            String localAddress = fi.getAbsolutePath();
            System.out.println("存入本地文件地址:" + localAddress);
            video.setLocalAddress(localAddress);
            //获取后缀名
            String suffix=localAddress.substring(localAddress.lastIndexOf("."), localAddress.length());
            System.out.println("后缀名:" + suffix);
            video.setSuffix(suffix);
        }
        else {
            return R.error().message("视频保存失败！");
        }
        return R.ok().message("视频保存成功!");
    }

    /**
     * 接收上传的课程音频
     *
     * @param file
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/saveLessonAudio")
    @ResponseBody
    public R saveLessonAudio(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Audio audio=new Audio();
        if (!file.isEmpty()) {
            //存放地址
            file_address = "H:/workspace/oes_resource/lesson_audio/";
            String path = file_address;
            System.out.println("path"+path);
            //如果父文件夹不存在 则创建文件夹 文件夹为path,音频名字file.getOriginalFilename()
            File filepath = new File(path, file.getOriginalFilename());
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            File fi = new File(path + File.separator + file.getOriginalFilename());
            //下载到本地
            file.transferTo(fi);
            //获取绝对路径
            String localAddress = fi.getAbsolutePath();
            System.out.println("存入本地文件地址:" + localAddress);
            audio.setLocalAddress(localAddress);
            //获取后缀名
            String suffix=localAddress.substring(localAddress.lastIndexOf("."), localAddress.length());
            System.out.println("后缀名:" + suffix);
            audio.setSuffix(suffix);
        }
        else {
            return R.error().message("音频保存失败！");
        }
        return R.ok().message("音频保存成功!");
    }

    /**
     * 接收上传的课程图片
     *
     * @param file
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/saveLessonPhoto")
    @ResponseBody
    public R saveLessonPhoto(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Photo photo = new Photo();
        if (!file.isEmpty()) {
            //存放地址
            file_address = "H:/workspace/oes_resource/lesson_photo/";
            String path = file_address;
            System.out.println("path"+path);
            //如果父文件夹不存在 则创建文件夹 文件夹为path,图片名字file.getOriginalFilename()
            File filepath = new File(path, file.getOriginalFilename());
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            File fi = new File(path + File.separator + file.getOriginalFilename());
            //下载到本地
            file.transferTo(fi);
            //获取绝对路径
            String localAddress = fi.getAbsolutePath();
            System.out.println("存入本地文件地址:" + localAddress);
            photo.setLocalAddress(localAddress);
            //获取后缀名
            String suffix=localAddress.substring(localAddress.lastIndexOf("."), localAddress.length());
            System.out.println("后缀名:" + suffix);
            photo.setSuffix(suffix);
        }
        else {
            return R.error().message("图片保存失败！");
        }
        return R.ok().message("图片保存成功!");
    }

    /**
     * 接收上传的讲师图片
     *
     * @param file
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/saveTeacherPhoto")
    @ResponseBody
    public R saveTeacherPhoto(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Photo photo = new Photo();
        if (!file.isEmpty()) {
            //存放地址
            file_address = "H:/workspace/oes_resource/teacher_photo/";
            String path = file_address;
            System.out.println("path"+path);
            //如果父文件夹不存在 则创建文件夹 文件夹为path,图片名字file.getOriginalFilename()
            File filepath = new File(path, file.getOriginalFilename());
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            File fi = new File(path + File.separator + file.getOriginalFilename());
            //下载到本地
            file.transferTo(fi);
            //获取绝对路径
            String localAddress = fi.getAbsolutePath();
            System.out.println("存入本地文件地址:" + localAddress);
            photo.setLocalAddress(localAddress);
            //获取后缀名
            String suffix=localAddress.substring(localAddress.lastIndexOf("."), localAddress.length());
            System.out.println("后缀名:" + suffix);
            photo.setSuffix(suffix);
        }
        else {
            return R.error().message("图片保存失败！");
        }
        return R.ok().message("图片保存成功!");
    }

    /**
     * 接收上传的用户头像
     *
     * @param file
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @PostMapping("/saveUserPortrait")
    @ResponseBody
    public R saveUserPortrait(@RequestParam("file") MultipartFile file)
            throws IllegalStateException, IOException {
        Photo photo = new Photo();
        if (!file.isEmpty()) {
            //存放地址
            file_address = "H:/workspace/oes_resource/user_portrait/";
            String path = file_address;
            System.out.println("path"+path);
            //如果父文件夹不存在 则创建文件夹 文件夹为path,图片名字file.getOriginalFilename()
            File filepath = new File(path, file.getOriginalFilename());
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            File fi = new File(path + File.separator + file.getOriginalFilename());
            //下载到本地
            file.transferTo(fi);
            //获取绝对路径
            String localAddress = fi.getAbsolutePath();
            System.out.println("存入本地文件地址:" + localAddress);
            photo.setLocalAddress(localAddress);
            //获取后缀名
            String suffix=localAddress.substring(localAddress.lastIndexOf("."), localAddress.length());
            photo.setSuffix(suffix);
            return R.ok().message("图片保存成功！").data("URL", localAddress);
        }
        else {
            return R.error().message("图片保存失败！");
        }
    }

}

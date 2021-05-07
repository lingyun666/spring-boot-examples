package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Create by Ting on 2021-05-07 09:11:28.
 */
@Controller
public class UploadController {
    // 指定文件输出路径
    private static String UPLOAD_FOLDER = "E://temp//";
    /**
     * 重定向到"upload.html"
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    /**
     * 接收一个文件
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("upload/one")
    @ResponseBody
    public String uploadImage(@RequestParam("mf") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);
        System.out.println("==============");
        // 后台上传文件至系统的指定文件夹
        byte[] bytes = multipartFile.getBytes();//获取MultipartFile文字为二进制数据流
        Path dir = Paths.get(UPLOAD_FOLDER);
        Path path = Paths.get(UPLOAD_FOLDER + multipartFile.getOriginalFilename());
        // 判断该目录是否存在,不存在,则创建一个
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        }
        // 将bytes数据写进指令路径下
        Files.write(path, bytes);
        return "success";
    }

    /**
     * 接收多个文件(不能接收视频)
     * @param multipartFiles
     * @return
     */
    @PostMapping("upload/many")
    @ResponseBody
    public String uploadMany(@RequestParam("mfs") List<MultipartFile> multipartFiles) {
        System.out.println(multipartFiles);
        System.out.println("******************");
        return "suceess";

    }
}






















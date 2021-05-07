package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Create by Ting on 2021-05-07 09:11:28.
 */
@Controller
public class UploadController {
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
    public String uploadImage(@RequestParam("mf") MultipartFile multipartFile) {
        System.out.println(multipartFile);
        System.out.println("==============");
        return "success";
    }

    /**
     * 接收多个文件(不能接收视频)
     * @param multipartFiles
     * @return
     */
    @PostMapping("upload/many")
    public String uploadMany(@RequestParam("mfs") List<MultipartFile> multipartFiles) {
        System.out.println(multipartFiles);
        System.out.println("******************");
        return "suceess";

    }
}






















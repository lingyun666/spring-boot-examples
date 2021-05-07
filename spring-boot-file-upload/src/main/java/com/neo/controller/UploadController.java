package com.neo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "E://temp//";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
        if (multipartFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a multipartFile to upload");
            return "redirect:uploadStatus";
        }

        try {
            // Get the multipartFile and save it somewhere
            byte[] bytes = multipartFile.getBytes();//获取MultipartFile文字为二进制数据流
            Path dir = Paths.get(UPLOADED_FOLDER);
            Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
            // Create parent dir if not exists
            if (!Files.exists(dir)) {// 判断该文件夹是否存在
                Files.createDirectories(dir); // 创建文件夹
            }
            Files.write(path, bytes);// 将文件流写入上述文件路径中
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + multipartFile.getOriginalFilename() + "'");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Server throw IOException");
            e.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
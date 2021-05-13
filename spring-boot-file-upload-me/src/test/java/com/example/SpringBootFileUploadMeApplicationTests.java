package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class SpringBootFileUploadMeApplicationTests {

    @Test
    void contextLoads() throws MalformedURLException {
        // att: 将String类型"storage"转为Path类
        Path path1 = Paths.get("storage");
        // att: 调用Path类,继续解析路径
        Path path2 = path1.resolve("a.png");
        // att: 返回当前path的uri(jdk8里的类)
        URI uri1 = path2.toUri();
        // att: 将uri资源转为url
        UrlResource urlResource = new UrlResource(uri1);

        System.out.println(urlResource.toString());
    }

}

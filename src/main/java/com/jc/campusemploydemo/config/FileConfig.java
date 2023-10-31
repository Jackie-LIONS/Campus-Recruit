package com.jc.campusemploydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定位各种文件，头像。
 */
@Configuration
public class FileConfig  implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 定位用户图像的地址
         */
        System.out.println("图片配置生效");
        String filePath = System.getProperty("user.dir")+"D:\\DataStorage\\IdeaData\\campusemploydemo\\campusemploydemo\\src\\main\\resources\\static\\img\\";
        System.out.println(filePath);
        registry.addResourceHandler("/img/**").addResourceLocations("file:"+filePath);


        /**
         * 定位用户附件的地址
         */
        System.out.println("附件配置生效");
        String filePath1 = System.getProperty("user.dir")+"D:\\DataStorage\\IdeaData\\campusemploydemo\\campusemploydemo\\src\\main\\resources\\static\\resume\\";
        System.out.println(filePath);
        registry.addResourceHandler("/resume/**").addResourceLocations("file:"+filePath);
    }
}

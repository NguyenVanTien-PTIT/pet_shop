package com.migi.migi_project.config;

import com.migi.migi_project.utils.FileUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfiguration implements WebMvcConfigurer {
    private static String UPLOAD_DIR = FileUtils.getResourceBasePath()+ "\\src\\main\\resources\\images\\";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+UPLOAD_DIR);
    }
}

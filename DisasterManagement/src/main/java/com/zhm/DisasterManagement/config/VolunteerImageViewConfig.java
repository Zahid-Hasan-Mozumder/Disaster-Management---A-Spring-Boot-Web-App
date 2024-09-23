package com.zhm.DisasterManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class VolunteerImageViewConfig implements WebMvcConfigurer {

    // to show the volunteer image, making photos/volunteer a static path
    private static final String UPLOAD_DIR = "photos/volunteer";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposedDirectory(UPLOAD_DIR, registry);
    }

    private void exposedDirectory(String uploadDir, ResourceHandlerRegistry registry) {
        Path path = Paths.get(uploadDir);
        registry.addResourceHandler("/volunteer-images/**")
                .addResourceLocations("file:" + path.toAbsolutePath() + "/");
    }
}

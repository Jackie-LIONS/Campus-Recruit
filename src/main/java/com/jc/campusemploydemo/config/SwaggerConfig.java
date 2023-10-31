package com.jc.campusemploydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerDemoApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jc.campusemploydemo.controller"))
//                .apis(RequestHandlerSelectors.basePackage("com.jc.campusemploydemo.config"))
                .build();
    }
    private ApiInfo swaggerDemoApiInfo(){
        return new ApiInfoBuilder()
                .contact(new Contact("叶","http://www.githup.com","2373489842@qq.com"))
                .title("校招系统")
                .description("这里是校招系统api文档")
                .version("1.0.0")
                .build();
    }
}

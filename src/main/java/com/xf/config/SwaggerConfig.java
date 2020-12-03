package com.xf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }

    public ApiInfo apiInfo(){
        Contact DEFAULT_CONTACT = new Contact("xf", "", "441378042@qq.com");
        return new ApiInfo(
                "酒店管理系统",
                "xf写的第一个管理系统",
                "1.0.0",
                "",
                DEFAULT_CONTACT,
                "Apache2.0",
                "www.apache.org/licenses/LICENSES-2.0",
                new ArrayList()
        );
    }
}

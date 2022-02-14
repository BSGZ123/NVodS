package com.bskplu.service_base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: API管理生成
 * @Author BsKPLu
 * @Date 2022/2/14
 * @Version 1.1
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig () {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("接口文档在线调试")
                .apiInfo(webApiInfo())
                .select()
                //排除指定的路径 不生成api
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo () {
        return new ApiInfoBuilder()
                .title("视频中心API文档")
                .description("视频中心微服务接口")
                .version("1.1")
                .contact(new Contact("BsKPLu", "https://www.bskplu.buzz", "1018686256@qq.com"))
                .build();
    }
}

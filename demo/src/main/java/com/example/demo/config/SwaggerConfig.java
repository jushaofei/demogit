package com.example.demo.config;

/**
 * @Auther:http://www.bjsxt.com
 * @Date:2021/9/10
 * @Description:com.example.demo.config
 * @version:1.0
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(restApiInfo())
                .select()
                // 指定包名
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo restApiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger2构建api文档")
                .description("简单优雅的restful风格")
                .termsOfServiceUrl("no terms of serviceUrl")
                .version("1.0")
                .build();
    }
}
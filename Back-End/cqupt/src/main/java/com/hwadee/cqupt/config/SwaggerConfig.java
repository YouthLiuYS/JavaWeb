package com.hwadee.cqupt.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger3的配置类
 * 默认访问地址：ip:port/swagger-ui/index.html
 * 如果使用了页面优化，地址：ip:port/doc.html
 * @author: lys
 * @date: 2023/7/29
 */
//定义为配置类
@Configuration
//开启swagger
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket userApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hwadee.cqupt.controller.user"))
                .paths(PathSelectors.any())
                .build()
                .groupName("用户相关")
                .enable(true);
    }

    @Bean
    public Docket applyApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hwadee.cqupt.controller.apply"))
                .paths(PathSelectors.any())
                .build()
                .groupName("申请相关")
                .enable(true);
    }
    @Bean
    public Docket labApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hwadee.cqupt.controller.lab"))
                .paths(PathSelectors.any())
                .build()
                .groupName("实验室管理相关")
                .enable(true);
    }
    @Bean
    public Docket facilityApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hwadee.cqupt.controller.facility"))
                .paths(PathSelectors.any())
                .build()
                .groupName("仿真设备相关")
                .enable(true);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("虚拟仿真平台api文档")
                .description("虚拟仿真平台接口的文档描述")
                .contact(new Contact("刘阳森","http://www.baidu.com","youngthliu@foxmail.com"))
                .version("1.0")
                .build();
    }
}

package cn.coreqi.config.swagger;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 在线文档配置
 * 可通过 http://host:ip/swagger-ui.html 查看在线文档
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Value("${swagger.enabled}")
    private Boolean swaggerEnable;

    /**
     * 用来配置API文档的总体信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Coreqi接口文档")
                .description("Coreqi")
                .termsOfServiceUrl("https://www.coreqi.cn")
                .version("1.0.6")
                .build();
    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //调用上面方法，生成api的基础信息
                .enable(swaggerEnable)  //控制是否启用
                .select()   //选择那些路径和api来生成文档
                .apis(RequestHandlerSelectors.basePackage("cn.coreqi.controller"))  //扫描展示api的路径包
                .paths(PathSelectors.any()) //对所有路径进行监控
                .build();   //构建
    }
}

package cn.coreqi.config.swagger;


//import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

/**
 * swagger 在线文档配置
 * swagger 2.x 可通过 http://host:ip/swagger-ui.html 查看在线文档
 * swagger 3.x 可通过 http://host:ip/swagger-ui/index.html 查看在线文档
 */

@EnableOpenApi
@Configuration
//@EnableSwagger2
//@EnableSwaggerBootstrapUI
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
                .contact(new Contact("fanqi","https://www.coreqi.cn","fanqisoft@gmail.com"))
                .termsOfServiceUrl("https://www.coreqi.cn")
                .version("1.2.0")
                .build();
    }

    @Bean
    public Docket createRestApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo()) //调用上面方法，生成api的基础信息
//                .enable(swaggerEnable)  //控制是否启用
//                .select()   //选择那些路径和api来生成文档
//                .apis(RequestHandlerSelectors.basePackage("cn.coreqi.controller"))  //扫描展示api的路径包
//                .paths(PathSelectors.any()) //对所有路径进行监控
//                .build();   //构建

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(swaggerEnable)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("cn.coreqi.controller"))
                .paths(PathSelectors.any())
                .build();
                // 支持的通讯协议集合
//                .protocols(newHashSet("https", "http"))
//
//                // 授权信息设置，必要的header token等认证信息
//                .securitySchemes(securitySchemes())
//
//                // 授权信息全局应用
//                .securityContexts(securityContexts());
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
}

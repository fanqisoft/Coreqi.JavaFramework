package cn.coreqi.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    //当前跨域请求最大有效时长，这里设置默认值为30天
    private long maxAge = 30 * 24 * 60 * 60;

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    //设置访问源地址
        corsConfiguration.addAllowedHeader("*");    //设置访问源请求头
        corsConfiguration.addAllowedMethod("*");    //设置访问源请求方法
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(maxAge);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}

package com.yxb.apigateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * Author by yanxiaobo
 * Date on 2018/12/17$.
 * cross-origin-resource-sharing
 */
@Configuration
public class CrosConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);//是否允许cookie跨域
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addAllowedMethod("*");//允许哪些方法跨域，这里允许所有
        configuration.setAllowedOrigins(Arrays.asList("*"));//允许所有的源跨域

        source.registerCorsConfiguration("/**",configuration);//对所有匹配/**的url使用configuration
        //指定的跨域配置
        return new CorsFilter(source);

    }

}

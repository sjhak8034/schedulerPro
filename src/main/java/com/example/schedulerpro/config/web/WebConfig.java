package com.example.schedulerpro.config.web;


import com.example.schedulerpro.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        // Filter 등록
        filterRegistrationBean.setFilter(new LoginFilter());
        // Filter 순서 등록
        filterRegistrationBean.setOrder(1);
        // 전체 URL 에 Filter 적용
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}

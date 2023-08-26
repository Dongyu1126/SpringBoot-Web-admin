package com.atguigu.springbootwebadmin.config;

import com.atguigu.springbootwebadmin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* 1、编写一个拦截器实现HandlerInterceptor接口
* 2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
* 3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
* */
//@EnableWebMvc//全面接管MVC
@Configuration //定制springmvc的一些功能
public class AdminWebConfig implements WebMvcConfigurer { //实现web功能

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//拿哪些 ，这里所有资源包括静态资源都被拦截了
                .excludePathPatterns("/","/login","/css/**","fonts/**","images/**","js/**");//放行哪些
    }
}

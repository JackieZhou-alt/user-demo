//package com.baizhi.config;
//
//
//import com.github.pagehelper.PageInterceptor;
//import org.apache.ibatis.plugin.Interceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Properties;
//
//@Configuration
//public class MybatisConfiguration {
//
//    @Bean
//    public Interceptor pageInterceptor(){
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("helperDialect", "mysql");
//        pageInterceptor.setProperties(properties);
//        return pageInterceptor;
//    }
//}

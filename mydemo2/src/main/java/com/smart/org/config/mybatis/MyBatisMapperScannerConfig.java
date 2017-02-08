package com.smart.org.config.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by asus on 2016/12/27.
 */
@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter({MybatisConfiguration.class})
public class MyBatisMapperScannerConfig {
    //  配置类型别名
//    @Value("${mybatis.basePackage}")
//    private String basePackage;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();

        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.smart.org.dao");
//        System.out.println("=========="+mapperScannerConfigurer);

        return mapperScannerConfigurer;
    }

}

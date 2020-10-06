package com.changshun.countsystem;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
@MapperScan("com.changshun.countsystem.dao")
public class CountsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountsystemApplication.class, args);
    }

    //将pagehelper的bean装载进spring容器中
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
               Properties properties = new Properties();
               properties.setProperty("offsetAsPageNum","true");
               properties.setProperty("rowBoundsWithCount","true");
               properties.setProperty("reasonable","true");
               properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
                pageHelper.setProperties(properties);
                return pageHelper;
    }

}

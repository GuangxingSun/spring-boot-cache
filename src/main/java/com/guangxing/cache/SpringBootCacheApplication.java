package com.guangxing.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 搭建基本环境
 * 1、导入数据库文件
 * 2、创建javaBean封装对象
 * 3、整合Mybatis操作数据库
 *      1、配置数据源信息
 *      2、使用注解版的mybatis
 *          1）、@MapperScan指定需要扫描的mapper接口所在的包
 */
@SpringBootApplication
@MapperScan(value = "com.guangxing.cache.mapper")
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}

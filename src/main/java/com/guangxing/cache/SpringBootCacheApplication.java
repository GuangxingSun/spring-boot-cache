package com.guangxing.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 *
 * 缓存：
 * 一、搭建基本环境
 * 1、导入数据库文件
 * 2、创建javaBean封装对象
 * 3、整合Mybatis操作数据库
 *      1、配置数据源信息
 *      2、使用注解版的mybatis
 *          1）、@MapperScan指定需要扫描的mapper接口所在的包
 *二、快速开启缓存
 *  步骤：
 *      1、开启基于注解的缓存
 *      2、标注缓存注解即可
 *
 * 默认使用的是ConcurrentMapCacheManage>ConcurrentMapCache;将数据保存在ConcurrentMap<Object,Object>
 *     开发中使用缓存中间件：redis、memcached、ehcache
 *
 *三、整合Redis作为缓存
 * Redis是一个开源的，内存中的数据存储系统，他可以用作数据库、缓存和消息中间件
 *
 *
 *
 * RabbitMq
 *   自动配置
 *   1、RabbitAutoConfiguration
 *   2、有自动配置了连接工厂ConnectionFactory
 *   3、RabbitProperties 封装了RabbitMQ的配置
 *   4、RabbitTemplate：给RabbitMQ发送和接受消息；
 *   5、AmqpAdmin：RabbitMQ系统管理功能组件
 *   6、
 */
@SpringBootApplication
@MapperScan(value = "com.guangxing.cache.mapper")
@EnableCaching
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}

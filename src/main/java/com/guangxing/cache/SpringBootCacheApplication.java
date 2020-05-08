package com.guangxing.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


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
 *  1、安装redis：使用docker
 *  2、引入redis的starter
 *  3、配置redis
 *  4、测试缓存
 *      原理：CacheManger==Cache缓存组件实际给缓存中存取数据
 *      1）引入redis的starter，容器中保存的是RedisCacheManager；
 *      2）redisCacheManager帮我们创建RedisCache来作为缓存组件；RedisCache通过操作redis缓存数据。
 *
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
 *      AmqpAdmin:创建和删除Quence,Exchange,Binding
 *   6、@EnableRabbit +@RabbitListener监听消息队列的内容
 */
@EnableRabbit  //启动rabbitMQ
@SpringBootApplication
@MapperScan(value = "com.guangxing.cache.mapper")
@EnableCaching
@EnableAsync //启动异步处理
@EnableScheduling  //开启定时任务
public class SpringBootCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

}

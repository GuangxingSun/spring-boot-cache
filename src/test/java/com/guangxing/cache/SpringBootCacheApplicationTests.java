package com.guangxing.cache;

import com.guangxing.cache.bean.Employee;
import com.guangxing.cache.config.MyRedisConfig;
import com.guangxing.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;


@SpringBootTest
class SpringBootCacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象

    @Autowired
    RedisTemplate<Object,Employee> empRedisTemplate;

    /**
     * Redis常见五大数据类型
     * String(字符串)、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
     * stringRedisTemplate.opsForValue():String(字符串)
     * stringRedisTemplate.opsForList:list(列表)
     * stringRedisTemplate.opsForSet():Set（集合）
     * stringRedisTemplate.opsForZSet():ZSet（有序集合）
     * stringRedisTemplate.opsForHash():Hash（散列）
     */
    @Test
    public void redisTest01(){
        //stringRedisTemplate.opsForValue().append("msg", "word");
        stringRedisTemplate.opsForList().leftPushAll("li", Arrays.asList("a","b","c"));
    }

    //测试保存对象
    @Test
    public void redisTest02(){
        Employee emp = employeeMapper.getEmpById(2);
        //默认如果保存对象，使用jdk序列化机制，序列化的数据保存到redis中
        //redisTemplate.opsForValue().set("emp-01",emp);
        //将数据以json的方式保存
        //（1）自己将对象转为json
        //（2）redisTemplate默认的序列化规则
        empRedisTemplate.opsForValue().set("emp-02",emp);
    }


    @Test
    public void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

}
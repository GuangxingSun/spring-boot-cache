package com.guangxing.cache;

import com.guangxing.cache.bean.Department;
import com.guangxing.cache.bean.Employee;
import com.guangxing.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashMap;


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

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;


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

    /**
     * 测试消息队列 点对点
     */
    @Test
    public void directTest(){
        //Massage需要自己构造一个；定义消息体内容和消息头
//        rabbitTemplate.send(exchange,routeKey,message);

        //object默认只需要传入要发送的对象，自动序列化发送给rabbitmq
//        rabbitTemplate.convertAndSend(exchange,routeKey,object);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("msg","这是第一个消息");
        hashMap.put("data", Arrays.asList("hello word", 123, true));

        //对象被默认序列化之后 发送出去
        rabbitTemplate.convertAndSend("exchange.direct","guangxing.news",hashMap);

    }

    /**
     * 测试接受消息
     */
    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("guangxing.news");
        System.out.println(o.getClass());
        System.out.println(o);

    }

    /**
     * 测试以对象形式发送消息
     * 广播形式
     */
    @Test
    public void fanOut(){
        rabbitTemplate.convertAndSend("exchange.fanout","guangxing",new Department(10,"一号部门名称"));

    }

    @Test
    public void createExchange(){
        //创建exchange
        //amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        //System.out.println("创建完成");

        //创建queue
        //amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

        //创建绑定规则
        //amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE, "amqpadmin.exchange", "amqpadmin.routingkey",null));

        //删除队列
        //amqpAdmin.declareQueue("amqpadmin.queue");
    }



    @Test
    public void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

}
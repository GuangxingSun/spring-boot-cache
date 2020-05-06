package com.guangxing.cache.service;

import com.guangxing.cache.bean.Department;
import com.guangxing.cache.mapper.DepartmentMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author apple
 * @create time 2020/4/30 3:42 下午
 **/

@CacheConfig(cacheNames = "dept",cacheManager = "cacheManager")
@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;
    
    @Autowired
    CacheManager cacheManager;

    @Cacheable(key = "#id")
    public Department getDeptById(Integer id){
        return departmentMapper.getDeptById(id);
    }

    //使用缓存管理器得到缓存，进行api调用
    public Department getDept(Integer id){
        System.out.println("查询部门"+id);
        Department dept = departmentMapper.getDeptById(id);
        //获取某个缓存  用编码方式
        Cache cache = cacheManager.getCache("dept");
        cache.put("dept01",dept);
        return dept;
    }

    /**
     * 监听rabbitMq消息
     */
    @RabbitListener(queues = "guangxing.news")
    public void receive(Department department){
        System.out.println(department);
    }

}

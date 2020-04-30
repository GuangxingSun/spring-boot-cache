package com.guangxing.cache.service;

import com.guangxing.cache.bean.Employee;
import com.guangxing.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author apple
 * @create time 2020/4/29 2:07 下午
 **/
@CacheConfig(cacheNames = "emp")
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存中获取，不用调用方法
     *
     * CacheManager管理多个Cache组件，对缓存的真正CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字
     *
     * 几个属性：
     *      cacheNames/value:指定缓存组件的名字；将方法的返回结果放在哪个缓存，可以指定多个缓存
     *      key:缓存数据使用的key;可以用他来指定。默认是使用方法参数的值， #id方法的返回值
     *          编写SpEL；#id；参数id的值    #a0  #p0  #root.args[0]
     *      keyGenerator:key的生成器；可以自己指定key的生成器的组件id
     *              key/keyGenerator:两者选一使用
     *      cacheManager:指定缓存管理器；或者cacheResolver指定获取解析器
     *      condition:指定符合条件的情况下才缓存；
     *          condition = "#a0>1":第一个参数的值>1的时候才缓存
     *      unless:否定缓存；当unless指定的条件位true,方法的返回值就不会缓存，可以获取到结果进行判断
     *            unless = "#result == null"
     *      sync: 是否使用同步模式
     *          缓存的同步 sync：
     *          在多线程环境下，某些操作可能使用相同参数同步调用。
     *          默认情况下，缓存不锁定任何资源，可能导致多次计算，而违反了缓存的目的。
     *          对于这些特定的情况，属性 sync 可以指示底层将缓存锁住，
     *          使只有一个线程可以进入计算，而其他线程堵塞，直到返回结果更新到缓存中。
     *
     * @param id
     * @return
     */
    //@Cacheable(cacheNames ={"emp"},key = "#root.methodName+'['+#id+']'")
    //@Cacheable(cacheNames ={"emp"},keyGenerator = "myKeyGenerator",condition = "#id>1",unless = "#a0==2")
    @Cacheable(cacheNames ={"emp"},key = "#id")
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * @CachePut：即调用方法，又更新缓存数据：
     * 修改数据库数据，同时更新缓存；
     * 运行时机：
     *  1、先调用目标方法
     *  2、将目标方法的结果缓存起来
     *
     * 测试步骤：
     *  1、查询1员工；查到的结果会放在缓存中；
     *      key：1  value：lastName：张三
     *  2、以后查询还是之前的结果
     *  3、更新1号员工：
     *  4、查询一号员工
     *
     *
     * @param employee
     * @return
     */
    @CachePut(/*value = "emp",*/key = "#result.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp"+employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }


    /**
     * @CacheEvict:缓存清除
     * key:指定要清除的数据
     * allEntries = true:指定清除这个缓存中所有的数据
     * beforeInvocation = false:缓存的清除是否在方法之前执行
     *      默认代表是在方法执行之后执行
     * @param id
     */
    @CacheEvict(/*value = "emp",*/key = "#id")
    public void deleteEmp(Integer id) {
        employeeMapper.deleteEmpById(id);
    }

    @Caching(
            cacheable = {
                    @Cacheable(key = "#lastName")
            },
            put = {
                    @CachePut(key = "#result.id"),
                    @CachePut(key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}

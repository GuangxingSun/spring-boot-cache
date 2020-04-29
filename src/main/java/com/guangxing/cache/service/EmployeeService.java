package com.guangxing.cache.service;

import com.guangxing.cache.bean.Employee;
import com.guangxing.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author apple
 * @create time 2020/4/29 2:07 下午
 **/
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        return employeeMapper.getEmpById(id);
    }
}

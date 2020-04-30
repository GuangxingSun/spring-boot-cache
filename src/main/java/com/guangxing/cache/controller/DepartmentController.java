package com.guangxing.cache.controller;

import com.guangxing.cache.bean.Department;
import com.guangxing.cache.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author apple
 * @create time 2020/4/30 3:46 下午
 **/
@RestController
public class DepartmentController {
    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id){
       // return deptService.getDeptById(id);
        return deptService.getDept(id);
    }

}

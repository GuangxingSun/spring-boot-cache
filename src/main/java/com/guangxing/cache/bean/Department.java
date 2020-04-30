package com.guangxing.cache.bean;

import java.io.Serializable;

/**
 * @author apple
 * @create time 2020/4/29 10:48 上午
 **/
public class Department implements Serializable {
    private Integer id;
    private String departmentName;

    public Department() {
        super();
    }

    public Department(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}

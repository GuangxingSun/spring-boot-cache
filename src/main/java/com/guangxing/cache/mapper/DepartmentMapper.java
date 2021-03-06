package com.guangxing.cache.mapper;

import com.guangxing.cache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id = #{id}")
    public Department getDeptById(Integer id);
}

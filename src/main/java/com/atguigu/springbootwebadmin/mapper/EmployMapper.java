package com.atguigu.springbootwebadmin.mapper;

import com.atguigu.springbootwebadmin.bean.Employ;
import org.apache.ibatis.annotations.Mapper;

@Mapper//mybatis所有接口要有@Mapper
public interface EmployMapper {
    public Employ getEmp(Long id);
}

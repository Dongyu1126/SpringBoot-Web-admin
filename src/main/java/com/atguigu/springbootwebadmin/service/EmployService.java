package com.atguigu.springbootwebadmin.service;

import com.atguigu.springbootwebadmin.bean.Employ;
import com.atguigu.springbootwebadmin.mapper.EmployMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployService {
    @Autowired
    EmployMapper employMapper;
    public Employ getEmpByid(Long id){
        return employMapper.getEmp(id);
    }
}

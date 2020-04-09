package com.itheima.service.impl;

import com.itheima.dao.DepartmentMapper;
import com.itheima.domain.Department;
import com.itheima.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}

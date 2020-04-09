package com.itheima.service.impl;

import com.itheima.dao.EmployeeMapper;
import com.itheima.domain.Employee;
import com.itheima.domain.EmployeeExample;
import com.itheima.domain.Msg;
import com.itheima.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDep(null);
    }

    @Override
    public Msg saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
        return Msg.success();
    }

    /**
     *
     * @param empName
     * @return 0表示数据库无这条记录，返回true
     */
    @Override
    public Boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count = employeeMapper.countByExample(example);
        return count==0;
    }

    @Override
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}

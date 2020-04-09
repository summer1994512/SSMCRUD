package com.itheima.service;

import com.itheima.domain.Employee;
import com.itheima.domain.Msg;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    Msg saveEmp(Employee employee);

    Boolean checkUser(String empName);

    Employee getEmp(Integer id);

    void updateEmp(Employee employee);

    void deleteEmp(Integer id);

    void deleteBatch(List<Integer> ids);
}

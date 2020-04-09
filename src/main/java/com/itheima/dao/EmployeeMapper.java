package com.itheima.dao;

import com.itheima.domain.Employee;
import com.itheima.domain.EmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    //自定义两个方法用于连表查询，当查询员工信息时，同时查处员工的部门信息
    List<Employee> selectByExampleWithDep(EmployeeExample example);

    Employee selectByPrimaryKeyWithDep(Integer empId);
    //

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}
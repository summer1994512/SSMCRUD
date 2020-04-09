package com.itheima.test;

import com.itheima.dao.DepartmentMapper;
import com.itheima.dao.EmployeeMapper;
import com.itheima.domain.Department;
import com.itheima.domain.DepartmentExample;
import com.itheima.domain.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    /**
     * 测试depatment
     */
    @Test
    public void testCRUD(){

//        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        DepartmentMapper departmentMapper = ac.getBean(DepartmentMapper.class);
//        System.out.println(departmentMapper);
        //1、测试插入department
//        departmentMapper.insertSelective(new Department(null,"服务部"));
//        System.out.println(departmentMapper.selectByPrimaryKey(1));
        //2、测试插入employee
//        System.out.println(new Employee());
//        employeeMapper.insertSelective(new Employee(null,"刘伟","男","511@qq.com",1));
        //3、批量插入employee:使用可以执行批量操作的sqlsession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i=0;i<1000;i++){
            String id = UUID.randomUUID().toString().substring(0, 5)+"_"+i;
            mapper.insertSelective(new Employee(null, id,"男",id+"@qq.com",1));
        }

    }
}

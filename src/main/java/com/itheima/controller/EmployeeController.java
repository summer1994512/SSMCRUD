package com.itheima.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Employee;
import com.itheima.domain.Msg;
import com.itheima.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

//    @RequestMapping("/emps")
//    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") int pn, Model model){
//        //这是一个分页查询
//        //引入pageHelper分页插件
//        //在查询之前只需要调用，传入页码，以及每页的大小
//        PageHelper.startPage(pn,5);
//        //startpage后面紧跟的这个查询就是分页查询
//        List<Employee> emps = employeeService.getAll();
//        //使用pageInfo包装查询后的结果，只需要将PageInfo交给页面就行
//        //封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
//        PageInfo<Employee> page = new PageInfo<>(emps, 5);
//        model.addAttribute("pageInfo",page);
//
//        return "success";
//    }

    //    ajax请求
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmps(@RequestParam(value = "pn", defaultValue = "1") int pn) {
        //这是一个分页查询
        //引入pageHelper分页插件
        //在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        //startpage后面紧跟的这个查询就是分页查询
        List<Employee> emps = employeeService.getAll();
        //使用pageInfo包装查询后的结果，只需要将PageInfo交给页面就行
        //封装了详细的分页信息，包括我们查询出来的数据，传入连续显示的页数
        PageInfo<Employee> page = new PageInfo<>(emps, 5);

        return Msg.success().add("PageInfo", page);
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()){
            //校验失败，应该返回失败，在模态框中显示校验失败的错误信息
            Map<String,Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error:errors){
                map.put(error.getField(),error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }else {
            return employeeService.saveEmp(employee);
        }
    }

    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public Msg checkuser(@RequestParam("empName") String empName) {
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if (!regx.matches(empName)){
            return Msg.fail().add("va_msg","用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }
        boolean b = employeeService.checkUser(empName);
        if (b){
            return Msg.success();
        }else {
            return Msg.fail().add("va_msg","用户名不可用");
        }
    }

    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }

    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 单个删除，批量删除二合一
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids") String ids){
        if (ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            for (String id:str_ids){
                del_ids.add(Integer.parseInt(id));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            employeeService.deleteEmp(Integer.parseInt(ids));
        }
        return Msg.success();
    }


}

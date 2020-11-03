package com.example.demo01.control;

import com.example.demo01.dao.DepartmentDao;
import com.example.demo01.dao.EmployeeDao;
import com.example.demo01.entities.Department;
import com.example.demo01.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.security.pkcs11.Secmod;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    //显示员工的所有信息
    @GetMapping("/emps")
    public String list(Model model){
        //通过dao层来获得信息
        Collection<Employee> employees = employeeDao.getAll();
        //把获得的信息放在请求域中
        model.addAttribute("emps",employees);
        //thymeleaf模板引擎会自动拼串
        //classpath:/templates/ (return的内容) .html
        return "emp/list";
    }

    //进入添加员工的编辑页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    //执行添加员工的操作，然后重定向到显示员工所有信息的页面
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //进入修改员工的编辑页面
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    //执行编辑员工的操作
    @PutMapping("/emp")
    public String editEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除员工的操作
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}

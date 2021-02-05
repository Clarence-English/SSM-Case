package com.dhw.ssm.controller;

import com.dhw.ssm.entity.Department;
import com.dhw.ssm.entity.Employee;
import com.dhw.ssm.helper.PageDataHelper;
import com.dhw.ssm.service.DepartmentService;
import com.dhw.ssm.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author dhw
 */
@RestController
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DepartmentService departmentService;

    @GetMapping("/getAllEmployee")
    public Map<String, Object> getAllEmployee(
            @RequestParam(defaultValue = "1", value = "page") int page,
            @RequestParam(defaultValue = "10", value = "limit") int limit) {
        PageDataHelper pageDataHelper = PageDataHelper.pagingMySQLVersion(page, limit);
        List<Employee> employeeList = employeeService.findAllEmployeeAndPage(pageDataHelper);
        Integer count = employeeService.findEmployeeCount();
        return PageDataHelper.pagingEncapsulation(employeeList, count);
    }

    @GetMapping("/getAllDepartment")
    public List<Department> getAllDepartment() {
        return departmentService.findAllDepartments();
    }

    @PostMapping(value = "/addEmployeeInfo")
    public Boolean addEmployeeInfo(Employee employee) {
        return employeeService.insertEmployeeInfo(employee);
    }

    @PutMapping("/editEmployeeInfo")
    public Boolean editEmployeeInfo(Employee employee) {
        return employeeService.updateEmployeeInfo(employee);
    }

    @DeleteMapping("/removeEmployeeInfo")
    public Boolean removeEmployeeInfo(Integer empId) {
        return employeeService.deleteEmployeeInfo(empId);
    }

    @DeleteMapping("/removeEmployeeInfoInBulk")
    public Boolean removeEmployeeInfoInBulk(String empIds) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Integer> list = null;
        try {
            list = objectMapper.readValue(empIds, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeService.deleteEmployeeInfoInBulk(list);
    }

}

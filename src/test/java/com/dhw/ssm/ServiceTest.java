package com.dhw.ssm;

import com.dhw.ssm.entity.Department;
import com.dhw.ssm.entity.Employee;
import com.dhw.ssm.entity.EmployeeExample;
import com.dhw.ssm.helper.PageDataHelper;
import com.dhw.ssm.service.DepartmentService;
import com.dhw.ssm.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * @author dhw
 * @create 2020-11-16 10:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:application.xml"}
)
public class ServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testPagination() {
        PageDataHelper pageDataHelper = PageDataHelper.pagingMySQLVersion(1, 10);
        EmployeeExample example = new EmployeeExample();
        example.setOrderByClause("emp_Id");
        List<Employee> employeeList = employeeService.findAllEmployeeAndPage(pageDataHelper);
        Integer count = employeeService.findEmployeeCount();
        Map<String, Object> objectMap = PageDataHelper.pagingEncapsulation(employeeList, count);
        System.out.println(objectMap);
    }

    @Test
    public void test1(){
        List<Department> departments = departmentService.findAllDepartments();
        System.out.println(departments);
    }


}

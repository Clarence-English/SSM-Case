package com.dhw.ssm.service.impl;

import com.dhw.ssm.entity.Employee;
import com.dhw.ssm.helper.PageDataHelper;
import com.dhw.ssm.mapper.DepartmentMapper;
import com.dhw.ssm.mapper.EmployeeMapper;
import com.dhw.ssm.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dhw
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private DepartmentMapper departmentMapper;


    @Override
    public List<Employee> findAllEmployeeAndPage(PageDataHelper pageDataHelper) {
        List<Employee> employees = employeeMapper.selectByExamplePaging(pageDataHelper);
        return employees;
    }

    @Override
    public Integer findEmployeeCount() {
        return employeeMapper.countByExample(null);
    }

    @Override
    public Boolean insertEmployeeInfo(Employee employee) {
        if (employeeMapper.insertSelective(employee) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateEmployeeInfo(Employee employee) {
        if (employeeMapper.updateByPrimaryKeySelective(employee) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteEmployeeInfo(Integer empId) {
        if (employeeMapper.deleteByPrimaryKey(empId) != 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteEmployeeInfoInBulk(List<Integer> empIds) {
        if (employeeMapper.deleteByPrimaryKeyInBulk(empIds) != 0) {
            return true;
        }
        return false;
    }


}

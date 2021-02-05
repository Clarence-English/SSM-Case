package com.dhw.ssm.service;


import com.dhw.ssm.entity.Employee;
import com.dhw.ssm.helper.PageDataHelper;

import java.util.List;

/**
 * @author dhw
 */
public interface EmployeeService {

    /**
     * 查找所有员工和页面
     *
     * @param pageDataHelper 分页参数类
     * @return 执行结果
     */
    List<Employee> findAllEmployeeAndPage(PageDataHelper pageDataHelper);

    /**
     * 获取员工总数
     *
     * @return 执行行数
     */
    Integer findEmployeeCount();

    /**
     * 插入员工信息
     *
     * @param employee 员工
     * @return
     */
    Boolean insertEmployeeInfo(Employee employee);

    /**
     * 修改员工信息
     *
     * @param employee 员工
     * @return
     */
    Boolean updateEmployeeInfo(Employee employee);

    /**
     * 删除员工信息
     * @param empId 编号
     * @return
     */
    Boolean deleteEmployeeInfo(Integer empId);
    /**
     * 批量删除员工信息
     * @param empIds 编号
     * @return
     */
    Boolean deleteEmployeeInfoInBulk(List<Integer> empIds);
}

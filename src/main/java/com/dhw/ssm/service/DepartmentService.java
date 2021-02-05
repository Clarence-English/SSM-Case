package com.dhw.ssm.service;

import com.dhw.ssm.entity.Department;

import java.util.List;

/**
 * @author dhw
 */
public interface DepartmentService {

    /**
     * 查询所有的部门信息
     *
     * @return 部门信息集合列表
     */
    List<Department> findAllDepartments();


}

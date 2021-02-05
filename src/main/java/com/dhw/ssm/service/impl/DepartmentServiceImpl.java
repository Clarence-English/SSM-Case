package com.dhw.ssm.service.impl;

import com.dhw.ssm.entity.Department;
import com.dhw.ssm.mapper.DepartmentMapper;
import com.dhw.ssm.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dhw
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public List<Department> findAllDepartments() {
        return departmentMapper.selectByExample(null);
    }
}

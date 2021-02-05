package com.dhw.ssm.entity;

import lombok.Data;

/**
 * @author
 */
@Data
public class Department {
    private Integer deptId;

    private String deptName;

    public Department() {
    }

    public Department(Integer deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }
}